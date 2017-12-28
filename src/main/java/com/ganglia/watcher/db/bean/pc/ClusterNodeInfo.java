/*******************************************************************************
 * @(#)clusterNode.java 2013-4-10
 *
 *******************************************************************************/
package com.ganglia.watcher.db.bean.pc;

import com.ganglia.watcher.db.bean.DBBean;
import com.ganglia.watcher.db.bean.SqlOption;
import com.ganglias.watcher.tools.Util;

/**
 * 集群节点
 * @version $Revision 1.1 $ 2013-4-10 上午9:18:52
 */
public class ClusterNodeInfo extends DBBean{
    /**
     * 集群节点ID
     */
    private String cluster_node_id;
    /**
     * pc机ID
     */
    private String pc_id;
    /**
     * zookeeper服务ID
     */
    private String zoo_server_id;
    /**
     * PC机创建时间
     */
    private String create_time;

    /**
     * PC机更新时间
     */
    private String update_time;
    
    /**
     * 状态
     */
    private String status_startup;

    /**
     * @return Returns the cluster_node_id.
     */
    public String getCluster_node_id() {
        return cluster_node_id;
    }

    /**
     * @param cluster_node_id The cluster_node_id to set.
     */
    public void setCluster_node_id(String cluster_node_id) {
        this.cluster_node_id = cluster_node_id;
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
     * @return Returns the zoo_server_id.
     */
    public String getZoo_server_id() {
        return zoo_server_id;
    }

    /**
     * @param zoo_server_id The zoo_server_id to set.
     */
    public void setZoo_server_id(String zoo_server_id) {
        this.zoo_server_id = zoo_server_id;
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

    /* (non-Javadoc)
     * @see com.cmcc.bcebs.watcher.db.bean.DBBean#setDBBean2UpdateState()
     */
    @Override
    public void DBBean2UpdateState() {
        this.setUpdate_time(Util.time2String(null));
        this.setDbOption(SqlOption.OPTION_UPDATE);
        this.setDbSql("pcInfo.updateClusterNodeInfo");
    }
}
