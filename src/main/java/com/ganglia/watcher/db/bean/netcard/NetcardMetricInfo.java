/*******************************************************************************
 * @(#)NetcardMetricInfo.java 2013-3-25
 *
 *******************************************************************************/
package com.ganglia.watcher.db.bean.netcard;

import java.util.Date;

import com.ganglia.watcher.db.bean.DBBean;
import com.ganglia.watcher.db.bean.SqlOption;
import com.ganglias.watcher.tools.Util;

/**
 * 网卡监控信息
 * @version $Revision 1.1 $ 2013-3-25 下午4:54:43
 */
public class NetcardMetricInfo extends DBBean{
    /**
     * ID
     */
    private String netcard_metric_id;
    /**
     * 网卡ID
     */
    private String netcard_id;
    /**
     * 上报时间
     */
    private String report_time;
    /**
     * 输入量
     */
    private String bytesIn;
    /**
     * 输出量
     */
    private String bytesOut;
    /**
     * 创建时间
     */
    private String create_time;
    /**
     * @return Returns the netcard_metric_id.
     */
    public String getNetcard_metric_id() {
        return netcard_metric_id;
    }
    /**
     * @param netcard_metric_id The netcard_metric_id to set.
     */
    public void setNetcard_metric_id(String netcard_metric_id) {
        this.netcard_metric_id = netcard_metric_id;
    }
    /**
     * @return Returns the netcard_id.
     */
    public String getNetcard_id() {
        return netcard_id;
    }
    /**
     * @param netcard_id The netcard_id to set.
     */
    public void setNetcard_id(String netcard_id) {
        this.netcard_id = netcard_id;
    }
    /**
     * @return Returns the report_time.
     */
    public String getReport_time() {
        return report_time;
    }
    /**
     * @param report_time The report_time to set.
     */
    public void setReport_time(String report_time) {
        this.report_time = report_time;
    }

    /**
     * @return Returns the bytesIn.
     */
    public String getBytesIn() {
        return bytesIn;
    }
    /**
     * @param bytesIn The bytesIn to set.
     */
    public void setBytesIn(String bytesIn) {
        this.bytesIn = bytesIn;
    }
    /**
     * @return Returns the bytesOut.
     */
    public String getBytesOut() {
        return bytesOut;
    }
    /**
     * @param bytesOut The bytesOut to set.
     */
    public void setBytesOut(String bytesOut) {
        this.bytesOut = bytesOut;
    }
    /**
     * @return Returns the create_time.
     */
    public String getCreate_time() {
        return create_time;
    }
    /**
     * @param create_time The create_time to set.
     */
    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }
    
    public NetcardMetricInfo(Date time){
    	setDbOption(SqlOption.OPTION_INSERT);
    	setDbSql("netcardMetricInfo.insertNetcardMetricInfo");
        create_time = Util.time2String(time);
        report_time = Util.time2String(time);
    }    

    /* (non-Javadoc)
     * @see com.cmcc.bcebs.watcher.db.bean.DBBean#setDBBean2UpdateState()
     */
    @Override
    public void DBBean2UpdateState() {
    }
}
