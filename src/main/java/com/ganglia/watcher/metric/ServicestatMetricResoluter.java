/*******************************************************************************
 * @(#)ServicestatMetricResoluter.java 2013-3-31
 *
 *******************************************************************************/
package com.ganglia.watcher.metric;

import java.sql.SQLException;
import java.util.Date;

import com.ganglia.watcher.common.IdCreator;
import com.ganglia.watcher.common.db.ibatis.IbatisDAO;
import com.ganglia.watcher.db.bean.SqlOption;
import com.ganglia.watcher.db.bean.fault.FaultType;
import com.ganglia.watcher.db.bean.pc.ClusterNodeInfo;
import com.ganglia.watcher.db.bean.pc.PcInfo;
import com.ganglia.watcher.db.bean.pc.ShdNodeInfo;
import com.ganglia.watcher.db.bean.process.ServiceInfo;
import com.ganglia.watcher.db.bean.process.ServicesMetricInfo;
import com.ganglia.watcher.xml.bean.METRIC;
import com.ganglias.watcher.tools.Util;

/**
 * @version $Revision 1.1 $ 2013-3-31 下午10:44:28
 */
public class ServicestatMetricResoluter extends MetricResoluter {

    /**
     * 起始字符串
     */
    private String startWidthStr = "service_";
    
    /**
     * 数据库DAO
     */
    private IbatisDAO dao;

    /*
     * (non-Javadoc)
     * @see
     * com.cmcc.bcebs.watcher.metric.MetricResoluter#canResolve(com.cmcc.bcebs.watcher.xml.bean.
     * METRIC)
     */
    @Override
    protected boolean canResolve(METRIC metric) {
        // 必须为存储节点
        if (metric.getNAME() != null && metric.getNAME().startsWith(startWidthStr)
                && InfoContainer.getInstance().contain(ShdNodeInfo.class.getName())) {
            return true;
        }
        return false;
    }

    @Override
    protected void resolve(METRIC metric) {
        String serviceName = metric.getNAME().substring(startWidthStr.length());
        ServiceInfo service = InfoContainer.getInstance().getObject(
                ServiceInfo.class.getName() + Util.STRING_SPLIT_MARK + serviceName);
        String now = Util.time2String(null);
        PcInfo pcInfo = InfoContainer.getInstance().getObject(PcInfo.class.getName());
        // 如果service不存在视为未入库,需新建入库
        if (service == null) {
            service = new ServiceInfo();
            service.setDbSql("serviceInfo.insertServiceInfo");
            service.setDbOption(SqlOption.OPTION_INSERT);
            service.setCreate_time(now);
            service.setUpdate_time(now);
            service.setName(serviceName);
            service.setPc_id(pcInfo.getPc_id());
            service.setService_id(IdCreator.getId("service_"));
            service.setStatus(metric.getVAL());
            InfoContainer.getInstance().addObject(
                    ServiceInfo.class.getName() + Util.STRING_SPLIT_MARK + serviceName, service);
        }
        ServicesMetricInfo serviceMetric = new ServicesMetricInfo(new Date());
        serviceMetric.setService_id(service.getService_id());
        serviceMetric.setService_metric_id(IdCreator.getId("service_metric"));
        serviceMetric.setStatus(metric.getVAL());
        //
        ClusterNodeInfo cluster = InfoContainer.getInstance().getObject(
                ClusterNodeInfo.class.getName());
        if ("0".equals(metric.getVAL())) {//如果是服务进程被停止
            //当服务不是zookeeper服务或者集群节点中zookeeper停止,则为故障
            if(!"zookeeper".equalsIgnoreCase(serviceName) || ("zookeeper".equalsIgnoreCase(serviceName) && cluster != null)){
                ShdNodeInfo shd = InfoContainer.getInstance().getObject(ShdNodeInfo.class.getName());
                try {
					if("1".equals(shd.getStatus_startup()) && checkStopNow()){
					    shd.updateProperty("status_startup", "3");
					}
					if (cluster != null && "1".equals(cluster.getStatus_startup()) && checkStopNow()) {
						cluster.updateProperty("status_startup", "3");
					}
                } catch (SQLException e) {
                	logger.error("查询是否存在正在停止的PC机名称异常", e);
				}
                createPCFault(FaultType.FAULT_TYPE_SERVICE, "PC机<" + pcInfo.getHost_name() + ">服务进程<"
                        + serviceName + ">停止服务！", null);
            }
        }
        //service更新属性
        service.updateProperty("status", metric.getVAL());
        InfoContainer.getInstance().addObject(
                ServicesMetricInfo.class.getName() + Util.STRING_SPLIT_MARK + serviceName,
                serviceMetric);
    }

    private boolean checkStopNow() throws SQLException {
		if ("0".equals(dao.getData("pcInfo.queryStopNowPC", null))) {
			return true;	
		}
		return false;
	}

	/**
     * 获取startWidthStr
     * @return startWidthStr startWidthStr
     */
    public String getStartWidthStr() {
        return startWidthStr;
    }

    /**
     * 设置startWidthStr
     * @param startWidthStr startWidthStr
     */
    public void setStartWidthStr(String startWidthStr) {
        this.startWidthStr = startWidthStr;
    }


    /**
     * @return Returns the dao.
     */
    public IbatisDAO getDao() {
        return dao;
    }

    /**
     * @param dao The dao to set.
     */
    public void setDao(IbatisDAO dao) {
        this.dao = dao;
    }
    
}
