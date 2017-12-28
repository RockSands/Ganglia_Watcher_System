/*******************************************************************************
 * @(#)NetcardMetricResoluter.java 2013-3-31
 *
 *******************************************************************************/
package com.ganglia.watcher.metric;

import java.util.Date;

import com.ganglia.watcher.common.IdCreator;
import com.ganglia.watcher.db.bean.SqlOption;
import com.ganglia.watcher.db.bean.netcard.NetcardInfo;
import com.ganglia.watcher.db.bean.netcard.NetcardMetricInfo;
import com.ganglia.watcher.db.bean.pc.PcInfo;
import com.ganglia.watcher.exception.EbsWatcherException;
import com.ganglia.watcher.exception.ErrorCode;
import com.ganglia.watcher.xml.bean.METRIC;
import com.ganglias.watcher.tools.Util;

/**
 * @version $Revision 1.1 $ 2013-3-31 下午10:59:26
 */
public class NetcardIOMetricResoluter extends MetricResoluter {
    /**
     * 起始字符串
     */
    private String startWidthStr = "network_";

    /*
     * (non-Javadoc)
     * @see
     * com.cmcc.bcebs.watcher.metric.MetricResoluter#canResolve(com.cmcc.bcebs.watcher.xml.bean.
     * METRIC)
     */
    @Override
    protected boolean canResolve(METRIC metric) {
        return metric.getNAME() != null && metric.getNAME().startsWith(startWidthStr);
    }

    /* (non-Javadoc)
     * @see com.cmcc.bcebs.watcher.metric.MetricResoluter#resolve(com.cmcc.bcebs.watcher.xml.bean.METRIC)
     */
    @Override
    protected void resolve(METRIC metric) {
        String netcardName = metric.getNAME().split(METRIC_SPLIT_MARK)[1];
        String metricType = metric.getNAME().split(METRIC_SPLIT_MARK)[2];

        // 获取网卡信息
        NetcardInfo netcardInfo = InfoContainer.getInstance().getObject(
                NetcardInfo.class.getName() + Util.STRING_SPLIT_MARK + netcardName);
        if (netcardInfo == null) {
        	throw EbsWatcherException.wrap(ErrorCode.NETCARD_IS_NOT_EXIST, "网卡<" + netcardName
                    + ">在数据库中无记录！");
        }

        // 获取网卡Metric信息
        NetcardMetricInfo netcardMetric = InfoContainer.getInstance().getObject(
                NetcardMetricInfo.class.getName() + Util.STRING_SPLIT_MARK + netcardName);
        if (netcardMetric == null) {
            netcardMetric = new NetcardMetricInfo(new Date());
            netcardMetric.setNetcard_id(netcardInfo.getNetcard_id());
            netcardMetric.setNetcard_metric_id(IdCreator.getId("Netcard_Metric_"));

            InfoContainer.getInstance().addObject(
                    NetcardMetricInfo.class.getName() + Util.STRING_SPLIT_MARK + netcardName,
                    netcardMetric);
        }

        // 更新网卡Metric信息
        netcardMetric.updateProperty(metricType, metric.getVAL());
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
