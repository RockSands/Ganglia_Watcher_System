/*******************************************************************************
 * @(#)DiskstatMetricResoluter.java 2013-3-31
 *
 *******************************************************************************/
package com.ganglia.watcher.metric;

import java.util.Date;

import com.ganglia.watcher.common.IdCreator;
import com.ganglia.watcher.db.bean.disk.DiskInfo;
import com.ganglia.watcher.db.bean.disk.DiskMetricInfo;
import com.ganglia.watcher.db.bean.fault.FaultType;
import com.ganglia.watcher.db.bean.pc.ClusterNodeInfo;
import com.ganglia.watcher.db.bean.pc.PcInfo;
import com.ganglia.watcher.db.bean.pc.ShdNodeInfo;
import com.ganglia.watcher.exception.EbsWatcherException;
import com.ganglia.watcher.exception.ErrorCode;
import com.ganglia.watcher.xml.bean.METRIC;
import com.ganglias.watcher.tools.Util;

/**
 * 磁盘状态解析
 */
public class DiskstatMetricResoluter extends MetricResoluter {

    /**
     * 起始字符串
     */
    private String startWidthStr = "diskStatus_";

    /**
     * 插拔状态
     */
    private static final String SWAP = "swap";

    /**
     * 健康状态
     */
    private static final String HEALTH = "health";

    /*
     * (non-Javadoc)
     * @see
     * com.cmcc.bcebs.watcher.metric.MetricResoluter#canResolve(com.cmcc.bcebs.watcher.xml.bean.
     * METRIC)
     */
    @Override
    protected boolean canResolve(METRIC metric) {
        if (metric.getNAME() != null && metric.getNAME().startsWith(startWidthStr)) {
            return true;
        }
        return false;
    }

    /*
     * (non-Javadoc)
     * @see
     * com.cmcc.bcebs.watcher.metric.MetricResoluter#resolut(com.cmcc.bcebs.watcher.xml.bean.METRIC)
     */
    @Override
    protected void resolve(METRIC metric) {
        String diskName = metric.getNAME().split(METRIC_SPLIT_MARK)[1];
        String metricType = metric.getNAME().split(METRIC_SPLIT_MARK)[2];

        // 获取磁盘信息
        DiskInfo diskInfo = InfoContainer.getInstance().getObject(
                DiskInfo.class.getName() + Util.STRING_SPLIT_MARK + diskName);
        if (diskInfo == null) {
            throw EbsWatcherException.wrap(ErrorCode.DISKINFO_IS_NOT_EXIST, "磁盘<" + diskName
                    + ">在数据库中无记录！");
        }

        // 获取磁盘Metric信息
        DiskMetricInfo diskMetric = InfoContainer.getInstance().getObject(
                DiskMetricInfo.class.getName() + Util.STRING_SPLIT_MARK + diskName);
        if (diskMetric == null) {
            diskMetric = new DiskMetricInfo(new Date());
            diskMetric.setDisk_id(diskInfo.getDisk_id());
            diskMetric.setDisk_metric_id(IdCreator.getId("Disk_Metric_"));

            InfoContainer.getInstance().addObject(
                    DiskMetricInfo.class.getName() + Util.STRING_SPLIT_MARK + diskName, diskMetric);
        }
        // 更新磁盘Metric信息
        diskMetric.updateProperty(metricType, metric.getVAL());
        // 更新磁盘信息和磁盘Metric信息
        if (SWAP.equals(metricType) || HEALTH.equals(metricType)) {
            diskInfo.updateProperty("status_" + metricType, metric.getVAL());
            // 磁盘插拔状态为0（拔出），或者健康状态为0（不健康）,记录故障信息
            if ("0".equals(metric.getVAL())) {
                // 当SHD节点和集群节点运行状态不为2：停止时，将之置为3：故障
                ShdNodeInfo shdNode = InfoContainer.getInstance().getObject(
                        ShdNodeInfo.class.getName());
                ClusterNodeInfo cluster = InfoContainer.getInstance().getObject(
                        ClusterNodeInfo.class.getName());
                if (null != shdNode && !"2".equals(shdNode.getStatus_startup())) {
                    shdNode.updateProperty("status_startup", "3");
                }
                if (null != cluster && !"2".equals(cluster.getStatus_startup())) {
                    cluster.updateProperty("status_startup", "3");
                }

                // 记录磁盘状态故障信息
                PcInfo pcInfo = InfoContainer.getInstance().getObject(PcInfo.class.getName());
                createPCFault(FaultType.FAULT_TYPE_DISK, "PC机<" + pcInfo.getHost_name() + ">磁盘<"
                        + diskName + ">" + metricType + "状态异常！", null);
            }
        }
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

}
