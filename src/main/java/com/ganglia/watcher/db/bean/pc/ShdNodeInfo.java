/*******************************************************************************
 * @(#)ShdNodeInfo.java 2013-4-10
 *
 *******************************************************************************/
package com.ganglia.watcher.db.bean.pc;

import com.ganglia.watcher.db.bean.DBBean;
import com.ganglia.watcher.db.bean.SqlOption;
import com.ganglias.watcher.tools.Util;

/**
 * 存储节点
 * @version $Revision 1.1 $ 2013-4-10 上午9:25:03
 */
public class ShdNodeInfo extends DBBean{
    /**
     * 服务节点ID
     */
    private String service_node_id;
    /**
     * 存储节点ID
     */
    private String shd_node_id;
    /**
     * PC机ID
     */
    private String pc_id;
    /**
     * 状态
     */
    private String status_startup;
    
    /**
     * PC机创建时间
     */
    private String create_time;

    /**
     * PC机更新时间
     */
    private String update_time;
    
    /**
     * @return Returns the service_node_id.
     */
    public String getService_node_id() {
        return service_node_id;
    }
    /**
     * @param service_node_id The service_node_id to set.
     */
    public void setService_node_id(String service_node_id) {
        this.service_node_id = service_node_id;
    }
    /**
     * @return Returns the shd_node_id.
     */
    public String getShd_node_id() {
        return shd_node_id;
    }
    /**
     * @param shd_node_id The shd_node_id to set.
     */
    public void setShd_node_id(String shd_node_id) {
        this.shd_node_id = shd_node_id;
    }
    /**
     * @return Returns the pc_id.
     */
    public String getPc_id() {
        return pc_id;
    }
    /**
     * @param pc_id The pc_id to set.
     */
    public void setPc_id(String pc_id) {
        this.pc_id = pc_id;
    }
    /**
     * @return Returns the status_startup.
     */
    public String getStatus_startup() {
        return status_startup;
    }
    /**
     * @param status_startup The status_startup to set.
     */
    public void setStatus_startup(String status_startup) {
        this.status_startup = status_startup;
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

    /* (non-Javadoc)
     * @see com.cmcc.bcebs.watcher.db.bean.DBBean#setDBBean2UpdateState()
     */
    @Override
    public void DBBean2UpdateState() {
        this.setUpdate_time(Util.time2String(null));
        this.setDbOption(SqlOption.OPTION_UPDATE);
        this.setDbSql("pcInfo.updateShdNodeInfo");
    }
}
