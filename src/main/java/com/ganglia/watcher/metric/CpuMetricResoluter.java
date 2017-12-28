/*******************************************************************************
 * @(#)CpuMetricResoluter.java 2013-4-28
 *
 *******************************************************************************/
package com.ganglia.watcher.metric;

import java.util.Date;

import com.ganglia.db.bean.cpu.CpuMetricInfo;
import com.ganglia.watcher.common.IdCreator;
import com.ganglia.watcher.db.bean.pc.PcInfo;
import com.ganglia.watcher.xml.bean.METRIC;
import com.ganglias.watcher.tools.Util;

/**
 * cpu采集信息处理
 * @version $Revision 1.1 $ 2013-4-28 上午10:40:00
 */
public class CpuMetricResoluter extends MetricResoluter {
    /**
     * 起始字符串
     */
    private String startWidthStr = "cpu_";

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
        // 获取CPU Metric信息
        CpuMetricInfo cpuMetric = InfoContainer.getInstance().getObject(
                CpuMetricInfo.class.getName() + Util.STRING_SPLIT_MARK + "CPU");
        if (cpuMetric == null) {
            cpuMetric = new CpuMetricInfo(new Date());
            cpuMetric.setPcId(pcInfo.getPc_id());
            cpuMetric.setCpuMetricId(IdCreator.getId("Cpu_Metric_"));

            InfoContainer.getInstance().addObject(
                    CpuMetricInfo.class.getName() + Util.STRING_SPLIT_MARK + "CPU", cpuMetric);
        }
        cpuMetric.updateProperty(metricType, metric.getVAL());
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
