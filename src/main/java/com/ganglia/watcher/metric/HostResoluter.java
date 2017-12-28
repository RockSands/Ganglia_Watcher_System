/*******************************************************************************
 * @(#)MetricResolution.java 2013-3-26
 *
 *******************************************************************************/
package com.ganglia.watcher.metric;

import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.ganglia.watcher.common.db.ibatis.BatchVO;
import com.ganglia.watcher.common.db.ibatis.IbatisDAO;
import com.ganglia.watcher.db.bean.DBBean;
import com.ganglia.watcher.db.bean.SqlOption;
import com.ganglia.watcher.db.bean.disk.DiskInfo;
import com.ganglia.watcher.db.bean.fault.FaultInfo;
import com.ganglia.watcher.db.bean.fault.FaultType;
import com.ganglia.watcher.db.bean.netcard.NetcardInfo;
import com.ganglia.watcher.db.bean.pc.ClusterNodeInfo;
import com.ganglia.watcher.db.bean.pc.PcInfo;
import com.ganglia.watcher.db.bean.pc.ShdNodeInfo;
import com.ganglia.watcher.db.bean.process.ServiceInfo;
import com.ganglia.watcher.exception.EbsWatcherException;
import com.ganglia.watcher.exception.ErrorCode;
import com.ganglia.watcher.xml.bean.HOST;
import com.ganglia.watcher.xml.bean.METRIC;
import com.ganglias.watcher.tools.Util;

/**
 * 采集分发器
 * 
 * @version $Revision 1.1 $ 2013-3-26 上午9:51:31
 */
public class HostResoluter {

	private static Logger logger = LoggerFactory.getLogger(HostResoluter.class);

	/**
	 * 数据库DAO
	 */
	private IbatisDAO dao;

	/**
	 * 线程池
	 */
	private ThreadPoolTaskExecutor hostResolvePool;

	/**
	 * Metric解析器
	 */
	private List<MetricResoluter> metricResoluters;

	/**
	 * <p>
	 * 解析HOST信息
	 * </P>
	 * 
	 * @param hosts
	 */
	public void hostsResolve(List<HOST> hosts) {
		List<PcInfo> pcInfos = getAllPCInfos();
		for (final HOST host : hosts) {
			final PcInfo pc = getPCInfoByHOST(host, pcInfos);
			if (pc != null && checkHOST(host)) {
				pcInfos.remove(pc);
				hostResolvePool.execute(hostResolvePool.createThread(new Runnable() {
					public void run() {
						try {
							InfoContainer.getInstance().clear();// 清空容器
							excuteResolve(host, pc);
						} catch (EbsWatcherException ex) {
							logger.error("PC机<" + host.getNAME() + ">解析异常\n原因：" + ex.getErrMsg(), ex);
						} catch (Exception ex) {
							logger.error(

									"PC机<" + host.getNAME() + ">解析异常", ex);
						}
					}
				}));
			}
		}
		Util.debug(logger, "解析分配完毕,对未采集PC机信息进行故障更新", null);
		saveNoCollectedPCInfo(pcInfos);
	}

	/**
	 * 验证HOST的有效性
	 * 
	 * @param host
	 * @return
	 */
	private boolean checkHOST(HOST host) {
		try {
			if (Integer.valueOf(host.getTN()) > Integer.valueOf(host.getTMAX())) {
				logger.error("HOST<" + host.getNAME() + ">监控信息已经过期！");
				return false;
			}
		} catch (Exception ex) {
			logger.error("验证HOST<" + host.getNAME() + ">出现异常！", ex);
			return false;
		}
		return true;
	}

	/**
	 * <p>
	 * 获得所有存储节点PC机名称
	 * </P>
	 * 
	 * @return
	 */
	private List<PcInfo> getAllPCInfos() {
		try {
			return dao.getData("pcInfo.getAllPcInfo", null);
		} catch (Exception e) {
			throw EbsWatcherException.wrap(ErrorCode.SQLEXCEPTION_DATABASE_EXCUTE_ERROR, "获取所有PC机 信息异常", e);
		}
	}

	/**
	 * 获得HOST对应的PC机信息
	 * 
	 * @param host
	 * @param pcInfos
	 * @return
	 */
	private PcInfo getPCInfoByHOST(HOST host, List<PcInfo> pcInfos) {
		if (host == null) {
			logger.error("采集的PC机信息为null！");
			return null;
		}
		if (Util.strIsEmpty(host.getNAME())) {
			logger.error("采集的PC机名称为空！");
			return null;
		}
		PcInfo coincidence_pc = null;
		for (PcInfo pc : pcInfos) {
			if (host.getNAME().equals(pc.getHost_name()) || host.getNAME().equals(pc.getIp_address_bussiness())
					|| host.getNAME().equals(pc.getIp_address_data_storage())) {
				coincidence_pc = pc;
				break;
			}
		}
		if (coincidence_pc == null) {
			logger.error("采集的PC机<" + host.getNAME() + ">信息，数据库无对应！");
		}
		return coincidence_pc;
	}

	/**
	 * <p>
	 * 执行解析
	 * </P>
	 * 
	 * @param metrices
	 * @param pc
	 * @return
	 */
	private void excuteResolve(HOST host, PcInfo pc) {
		if (initPCInfo(pc)) {
			Util.debug(logger, "获取PC机<" + pc.getHost_name() + ">基本信息，开始解析！", null);
			for (METRIC metric : host.getMETRIC()) {
				for (MetricResoluter resoluter : metricResoluters) {
					resoluter.excuteResolve(metric);
				}
			}
			Util.debug(logger, "PC机信息<" + pc.getHost_name() + ">构建完成，开始保存！", null);
			saveInfo();
			Util.debug(logger, "PC机信息<" + pc.getHost_name() + ">保存完成，解析完毕！", null);
		}
	}

	/**
	 * <p>
	 * 更新未采集到的PC机信息
	 * </P>
	 * 
	 * @param hostNames
	 */
	private void saveNoCollectedPCInfo(List<PcInfo> pcInfos) {
		String pcHostsStr = "";
		try {
			if (pcInfos != null && !pcInfos.isEmpty()) {
				String now = Util.time2String(null);
				List<BatchVO> vos = new LinkedList<BatchVO>();
				StringBuffer pcHostsBuffer = new StringBuffer(128);
				for (PcInfo pc : pcInfos) {
					pcHostsBuffer.append("," + pc.getHost_name());
					if (pc != null) {
						// PC机
						pc.setStatus_available("0");// 故障
						pc.setUpdate_time(now);
						// 存储节点
						ShdNodeInfo shdNode = dao.getSingleRecord("pcInfo.querySHDByPcId", pc.getPc_id());
						// 集群节点
						ClusterNodeInfo cluster = dao.getSingleRecord("pcInfo.queryClusterNodeByPcId", pc.getPc_id());
						if ((shdNode != null && "5".equals(shdNode.getStatus_startup()))
								|| (cluster != null && "5".equals(cluster.getStatus_startup()))) {// 停止监控
							continue;

						}
						if (shdNode != null && "1".equals(shdNode.getStatus_startup())) {
							shdNode.updateProperty("status_startup", "3");
							vos.add(new BatchVO(shdNode.getDbOption().toString(), shdNode.getDbSql(), shdNode));
						}
						if (cluster != null && "1".equals(cluster.getStatus_startup())) {
							cluster.updateProperty("status_startup", "3");
							vos.add(new BatchVO(cluster.getDbOption().toString(), cluster.getDbSql(), cluster));
						}
						// 错误消息
						FaultInfo fault = new FaultInfo();
						fault.setFault_info("PC机<" + pc.getHost_name() + ">未采集到最新信息，脱离监控!");
						fault.setPc_id(pc.getPc_id());
						fault.setFault_type(FaultType.FAULT_TYPE_PC.toString());
						fault.setCreate_time(now);
						vos.add(new BatchVO(SqlOption.OPTION_UPDATE.toString(), "pcInfo.updatePcInfo", pc));
						vos.add(new BatchVO(SqlOption.OPTION_INSERT.toString(), "faultInfo.insertFaultInfo", fault));
					}
				}
				if (!vos.isEmpty()) {
					dao.updateBatchData(vos);
				}
				if (pcHostsStr.length() > 0) {
					pcHostsStr = pcHostsBuffer.substring(1);
				}
				Util.debug(logger, "对未采集PC机信息" + pcHostsStr + "进行了状态更新:故障", null);
			}
		} catch (Exception ex) {
			logger.error("对未采集PC机" + pcHostsStr + "进行故障更新失败", ex);
		}
	}

	/**
	 * <p>
	 * 保存
	 * </P>
	 */
	private void saveInfo() {
		List<BatchVO> vos = new LinkedList<BatchVO>();
		BatchVO vo = null;
		for (Entry<String, Object> entry : InfoContainer.getInstance().entrySet()) {
			vo = getBatchVO(entry.getValue());
			if (vo != null) {
				vos.add(vo);
			}
		}
		try {
			// synchronized(this){//防止数据库死锁
			dao.updateBatchData(vos);
			// }
		} catch (Exception e) {
			throw EbsWatcherException.wrap(ErrorCode.SQLEXCEPTION_DATABASE_EXCUTE_ERROR, "PC机信息和监控信息数据保存失败", e);
		}
	}

	/**
	 * <p>
	 * 获得执行sql
	 * </P>
	 * 
	 * @param obj
	 * @return
	 */
	private BatchVO getBatchVO(Object obj) {
		if (DBBean.class.isInstance(obj)) {
			DBBean dbBean = (DBBean) obj;
			if (dbBean.getDbOption() != null && SqlOption.OPTION_BAN != dbBean.getDbOption()
					&& !Util.strIsEmpty(dbBean.getDbSql())) {
				return new BatchVO(dbBean.getDbOption().toString(), dbBean.getDbSql(), dbBean);
			}
		}
		return null;
	}

	/**
	 * <p>
	 * 初始PC机信息
	 * </P>
	 * 
	 * @param hostName
	 * @return
	 */
	private boolean initPCInfo(PcInfo pc) {
		try {
			// PC机
			pc.updateProperty("status_available", "1");// PC状态为正常
			InfoContainer.getInstance().addObject(PcInfo.class.getName(), pc);
			// 节点信息
			ShdNodeInfo shdNode = dao.getSingleRecord("pcInfo.querySHDByPcId", pc.getPc_id());
			if (shdNode != null) {
				InfoContainer.getInstance().addObject(ShdNodeInfo.class.getName(), shdNode);
				// 当节点状态不是运行及故障时,不允许数据库操作
				if ("5".equals(shdNode.getStatus_startup())) {// 状态为停止监控
					return false;
				} else if (!"1".equals(shdNode.getStatus_startup()) && !"3".equals(shdNode.getStatus_startup())) {
					shdNode.banDBOption();
				} else {
					shdNode.updateProperty("status_startup", "1");// 默认节点状态为正常
				}
			}
			ClusterNodeInfo cluster = dao.getSingleRecord("pcInfo.queryClusterNodeByPcId", pc.getPc_id());
			if (cluster != null) {
				InfoContainer.getInstance().addObject(ClusterNodeInfo.class.getName(), cluster);
				// 当节点状态不是运行及故障时,不允许数据库操作
				if ("5".equals(cluster.getStatus_startup())) {// 状态为停止监控
					return false;
				} else if (!"1".equals(cluster.getStatus_startup()) && !"3".equals(cluster.getStatus_startup())) {
					cluster.banDBOption();
				} else {
					cluster.updateProperty("status_startup", "1");// 默认节点状态为正常
				}

			}
			// 磁盘
			List<DiskInfo> diskList = dao.getData("diskInfo.getDiskListByPCID", pc.getPc_id());
			for (DiskInfo disk : diskList) {
				InfoContainer.getInstance().addObject(DiskInfo.class.getName() + Util.STRING_SPLIT_MARK
						+ disk.getPath().substring(disk.getPath().lastIndexOf("/") + 1), disk);
			}
			// 网卡
			NetcardInfo netQuery = new NetcardInfo();
			netQuery.setPc_id(pc.getPc_id());
			List<NetcardInfo> netcards = dao.getData("netcardInfo.getNetcardListByPCID", pc.getPc_id());
			for (NetcardInfo netcard : netcards) {
				InfoContainer.getInstance()
						.addObject(NetcardInfo.class.getName() + Util.STRING_SPLIT_MARK + netcard.getName(), netcard);
			}
			// 服务进程
			List<ServiceInfo> services = dao.getData("serviceInfo.getServiceListByPCID", pc.getPc_id());
			for (ServiceInfo service : services) {
				InfoContainer.getInstance()
						.addObject(ServiceInfo.class.getName() + Util.STRING_SPLIT_MARK + service.getName(), service);
			}
			return true;
		} catch (Exception e) {
			throw EbsWatcherException.wrap(ErrorCode.SQLEXCEPTION_DATABASE_EXCUTE_ERROR, "获取PC机信息异常！", e);
		}
	}

	/**
	 * @return Returns the metricResoluters.
	 */
	public List<MetricResoluter> getMetricResoluters() {
		return metricResoluters;
	}

	/**
	 * @param metricResoluters
	 *            The metricResoluters to set.
	 */
	public void setMetricResoluters(List<MetricResoluter> metricResoluters) {
		this.metricResoluters = metricResoluters;
	}

	/**
	 * @return Returns the dao.
	 */
	public IbatisDAO getDao() {
		return dao;
	}

	/**
	 * @param dao
	 *            The dao to set.
	 */
	public void setDao(IbatisDAO dao) {
		this.dao = dao;
	}

	/**
	 * @return Returns the hostResolvePool.
	 */
	public ThreadPoolTaskExecutor getHostResolvePool() {
		return hostResolvePool;
	}

	/**
	 * @param hostResolvePool
	 *            The hostResolvePool to set.
	 */
	public void setHostResolvePool(ThreadPoolTaskExecutor hostResolvePool) {
		this.hostResolvePool = hostResolvePool;
	}
}
