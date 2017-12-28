/*******************************************************************************
 * @(#)ServicesMetricINfo.java 2013-3-25
 *
 *******************************************************************************/
package com.ganglia.watcher.db.bean.process;

import java.util.Date;

import com.ganglia.watcher.db.bean.DBBean;
import com.ganglia.watcher.db.bean.SqlOption;
import com.ganglias.watcher.tools.Util;


/**
 * 服务进程监控信息
 * @version $Revision 1.1 $ 2013-3-25 下午4:58:06
 */
public class ServicesMetricInfo extends DBBean{
    /**
     * ID
     */
    private String service_metric_id;
    /**
     * 服务ID
     */
    private String service_id;
    /**
     * 上报时间
     */
    private String report_time;
    /**
     * 状态
     */
    private String status;
    /**
     * 创建时间
     */
    private String create_time;
    
    public ServicesMetricInfo(){
        
    }
    
    public ServicesMetricInfo(Date time){
    	setDbOption(SqlOption.OPTION_INSERT);
    	setDbSql("servicesMetricInfo.insertServicesMetricInfo");
        create_time = Util.time2String(time);
        report_time = Util.time2String(time);
    }
    
    /**
     * @return Returns the service_metric_id.
     */
    public String getService_metric_id() {
        return service_metric_id;
    }
    /**
     * @param service_metric_id The service_metric_id to set.
     */
    public void setService_metric_id(String service_metric_id) {
        this.service_metric_id = service_metric_id;
    }
    /**
     * @return Returns the service_id.
     */
    public String getService_id() {
        return service_id;
    }
    /**
     * @param service_id The service_id to set.
     */
    public void setService_id(String service_id) {
        this.service_id = service_id;
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
     * @return Returns the status.
     */
    public String getStatus() {
        return status;
    }
    /**
     * @param status The status to set.
     */
    public void setStatus(String status) {
        this.status = status;
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

    /* (non-Javadoc)
     * @see com.cmcc.bcebs.watcher.db.bean.DBBean#DBBean2UpdateState()
     */
    @Override
    public void DBBean2UpdateState() {
    }
}
