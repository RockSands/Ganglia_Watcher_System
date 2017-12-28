/*******************************************************************************
 * @(#)ServiceInfo.java 2013-2-18
 *
 *******************************************************************************/
package com.ganglia.watcher.db.bean.process;

import com.ganglia.watcher.db.bean.DBBean;
import com.ganglia.watcher.db.bean.SqlOption;
import com.ganglias.watcher.tools.Util;

/**
 * 服务进程信息
 * @version $Revision 1.1 $ 2013-2-25 上午9:48:30
 */
public class ServiceInfo extends DBBean{
    
    /**
     * pc机ID
     */
    private String pc_id;
    
    /**
     * ID
     */
    private String service_id;
    
    /**
     * 名称
     */
    private String name;
    
    /**
     * 状态
     */
    private String status;
    
    /**
     * 创建时间
     */
    private String create_time;
    
    /**
     * 更新时间
     */
    private String update_time;
    /**
     * @return Returns the name.
     */
    public String getName() {
        return name;
    }
    /**
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
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
    /**
     * @return Returns the update_time.
     */
    public String getUpdate_time() {
        return update_time;
    }
    /**
     * @param update_time The update_time to set.
     */
    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }
    /**  
     * 获取pc_id  
     * @return pc_id pc_id  
     */
    public String getPc_id() {
        return pc_id;
    }
    /**  
     * 设置pc_id  
     * @param pc_id pc_id  
     */
    public void setPc_id(String pc_id) {
        this.pc_id = pc_id;
    }
    /* (non-Javadoc)
     * @see com.cmcc.bcebs.watcher.db.bean.DBBean#DBBean2UpdateState()
     */
    @Override
    public void DBBean2UpdateState() {
        this.setUpdate_time(Util.time2String(null));
        this.setDbOption(SqlOption.OPTION_UPDATE);
        this.setDbSql("serviceInfo.updateServiceInfo");
    }
}
