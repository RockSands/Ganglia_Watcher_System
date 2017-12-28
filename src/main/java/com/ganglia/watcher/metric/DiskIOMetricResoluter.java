/*******************************************************************************
 * @(#)DiskInfoMetricResoluter.java 2013-3-29
 *
 *******************************************************************************/
package com.ganglia.watcher.metric;

import java.util.Date;

import com.ganglia.watcher.common.IdCreator;
import com.ganglia.watcher.db.bean.disk.DiskInfo;
import com.ganglia.watcher.db.bean.disk.DiskMetricInfo;
import com.ganglia.watcher.exception.EbsWatcherException;
import com.ganglia.watcher.exception.ErrorCode;
import com.ganglia.watcher.xml.bean.METRIC;
import com.ganglias.watcher.tools.Util;

/**
 * 磁盘基础信息处理
 * @version $Revision 1.1 $ 2013-3-29 下午4:53:51
 */
public class DiskIOMetricResoluter extends MetricResoluter {
    /**
     * 起始字符串
     */
    private String startWidthStr = "diskIO_";

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

    /* (non-Javadoc)
     * @see com.cmcc.bcebs.watcher.metric.MetricResoluter#resolve(com.cmcc.bcebs.watcher.xml.bean.METRIC)
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
