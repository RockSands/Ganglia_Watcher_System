/*******************************************************************************
 * @(#)MemoryMetricResoluter.java 2013-4-28
 *
 *******************************************************************************/
package com.ganglia.watcher.metric;

import java.util.Date;

import com.ganglia.watcher.common.IdCreator;
import com.ganglia.watcher.db.bean.memory.MemoryMetricInfo;
import com.ganglia.watcher.db.bean.pc.PcInfo;
import com.ganglia.watcher.xml.bean.METRIC;
import com.ganglias.watcher.tools.Util;

/**
 * 内存采集信息处理
 * @version $Revision 1.1 $ 2013-4-28 上午10:40:18
 */
public class MemoryMetricResoluter extends MetricResoluter {
    /**
     * 起始字符串
     */
    private String startWidthStr = "memory_";

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
     * com.cmcc.bcebs.watcher.metric.MetricResoluter#resolve(com.cmcc.bcebs.watcher.xml.bean.METRIC)
     */
    @Override
    protected void resolve(METRIC metric) {
        String metricType = metric.getNAME().substring(startWidthStr.length());
        PcInfo pcInfo = InfoContainer.getInstance().getObject(PcInfo.class.getName());
        // 获取内存 Metric信息
        MemoryMetricInfo memoryMetric = InfoContainer.getInstance().getObject(
                MemoryMetricInfo.class.getName() + Util.STRING_SPLIT_MARK + "MEMORY");
        if (memoryMetric == null) {
            memoryMetric = new MemoryMetricInfo(new Date());
            memoryMetric.setPcId(pcInfo.getPc_id());
            memoryMetric.setMemoryMetricId(IdCreator.getId("Memory_Metric_"));
            InfoContainer.getInstance().addObject(
                    MemoryMetricInfo.class.getName() + Util.STRING_SPLIT_MARK + "MEMORY",
                    memoryMetric);
        }
        memoryMetric.updateProperty(metricType, metric.getVAL());
    }

    /**
     * @return Returns the startWidthStr.
     */
    public String getStartWidthStr() {
        return startWidthStr;
    }

    /**
     * @param startWidthStr The startWidthStr to set.
     */
    public void setStartWidthStr(String startWidthStr) {
        this.startWidthStr = startWidthStr;
    }

}
