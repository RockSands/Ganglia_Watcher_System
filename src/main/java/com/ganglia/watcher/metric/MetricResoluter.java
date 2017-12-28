/*******************************************************************************
 * @(#)MetricResolution.java 2013-3-29
 *
 *******************************************************************************/
package com.ganglia.watcher.metric;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ganglia.watcher.db.bean.fault.FaultInfo;
import com.ganglia.watcher.db.bean.fault.FaultType;
import com.ganglia.watcher.db.bean.pc.PcInfo;
import com.ganglia.watcher.exception.EbsWatcherException;
import com.ganglia.watcher.exception.ErrorCode;
import com.ganglia.watcher.xml.bean.METRIC;
import com.ganglias.watcher.tools.Util;

/**
 * 采集信息解析者
 * @version $Revision 1.1 $ 2013-3-29 下午4:47:48
 */
public abstract class MetricResoluter {

	protected static Logger logger = LoggerFactory.getLogger(MetricResoluter.class);

    /**
     * 采集信息分隔符
     */
    protected static final String METRIC_SPLIT_MARK = "_";

    /**
     * <p>
     * 执行解析
     * </P>
     * @param metric
     */
    public final void excuteResolve(METRIC metric) {
        PcInfo pcInfo = InfoContainer.getInstance().getObject(PcInfo.class.getName());
        if (metric != null && pcInfo != null) {
            try {
                if (canResolve(metric)) {
                    if (metric.getTN().compareTo(metric.getTMAX()) > 0) {
                        logger.error("PC机<" + pcInfo.getHost_name()
                                + ">中Metric<" + metric.getNAME() + ">采集信息过期，脱离监控！");
                    } else {
                        resolve(metric);
                    }
                }
            } catch (EbsWatcherException ex) {
                logger.error("PC机<" + pcInfo.getHost_name() + ">中Metric<"
                        + metric.getNAME() + ">解析异常,原因：" + ex.getErrMsg(), ex);
            } catch (Exception ex) {
                logger.error("PC机<" + pcInfo.getHost_name()
                        + ">中Metric<" + metric.getNAME() + ">解析异常", ex);
            }
        }
    }

    /**
     * <p>
     * 判断是否能被解析
     * </P>
     * @return
     */
    protected abstract boolean canResolve(METRIC metric);

    /**
     * <p>
     * 解析
     * </P>
     */
    protected abstract void resolve(METRIC metric);

    /**
     * <p>
     * 创建一个入库错误
     * </P>
     * @param faultInfo
     * @param faultType
     * @param code
     */
    protected FaultInfo createPCFault(FaultType faultType, String faultInfo, ErrorCode code) {
        PcInfo pcInfo = InfoContainer.getInstance().getObject(PcInfo.class.getName());
        FaultInfo fault = new FaultInfo();
        fault.setFault_info(faultInfo);
        fault.setPc_id(pcInfo.getPc_id());
        fault.setFault_type(faultType.toString());
        InfoContainer.getInstance().addObject(fault.toString(), fault);
        if (code != null) {
            logger.error(faultInfo);
        }
        return fault;
    }
}
