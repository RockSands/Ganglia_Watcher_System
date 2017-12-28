/*******************************************************************************
 * @(#)PCInfo.java 2013-2-18
 *
 *******************************************************************************/
package com.ganglia.watcher.db.bean.pc;

import com.ganglia.watcher.db.bean.DBBean;
import com.ganglia.watcher.db.bean.SqlOption;
import com.ganglias.watcher.tools.Util;



/**
 * PC机信息
 * @version $Revision 1.1 $ 2013-2-18 上午10:13:00
 */
public class PcInfo extends DBBean{
    /**
     * PC机唯一标识
     */
    private String pc_id;

    /**
     * PC机主机名
     */
    private String host_name;

    /**
     * PC机业务通信IP，包括iSCSI以及管理流
     */
    private String ip_address_bussiness;

    /**
     * PC机数据同步IP
     */
    private String ip_address_data_storage;
    
    /**
     * 使用状态(0:未使用;1:使用)
     */
    private String status_used;
    
    /**
     * 有效状态(0:故障;1:有效)
     */
    private String status_available;
    
    /**
     * PC机EBS系统用户名
     */
    private String shh_user;

    /**
     * PC机EBS系统用户登录密码
     */
    private String ssh_password;

    /**
     * PC机创建时间
     */
    private String create_time;

    /**
     * PC机更新时间
     */
    private String update_time;

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

    /**  
     * 获取host_name  
     * @return host_name host_name  
     */
    public String getHost_name() {
        return host_name;
    }

    /**  
     * 设置host_name  
     * @param host_name host_name  
     */
    public void setHost_name(String host_name) {
        this.host_name = host_name;
    }

    /**  
     * 获取ip_address_bussiness  
     * @return ip_address_bussiness ip_address_bussiness  
     */
    public String getIp_address_bussiness() {
        return ip_address_bussiness;
    }

    /**  
     * 设置ip_address_bussiness  
     * @param ip_address_bussiness ip_address_bussiness  
     */
    public void setIp_address_bussiness(String ip_address_bussiness) {
        this.ip_address_bussiness = ip_address_bussiness;
    }

    /**  
     * 获取ip_address_data_storage  
     * @return ip_address_data_storage ip_address_data_storage  
     */
    public String getIp_address_data_storage() {
        return ip_address_data_storage;
    }

    /**  
     * 设置ip_address_data_storage  
     * @param ip_address_data_storage ip_address_data_storage  
     */
    public void setIp_address_data_storage(String ip_address_data_storage) {
        this.ip_address_data_storage = ip_address_data_storage;
    }

    /**  
     * 获取status_used  
     * @return status_used status_used  
     */
    public String getStatus_used() {
        return status_used;
    }

    /**  
     * 设置status_used  
     * @param status_used status_used  
     */
    public void setStatus_used(String status_used) {
        this.status_used = status_used;
    }

    /**  
     * 获取status_available  
     * @return status_available status_available  
     */
    public String getStatus_available() {
        return status_available;
    }

    /**  
     * 设置status_available  
     * @param status_available status_available  
     */
    public void setStatus_available(String status_available) {
        this.status_available = status_available;
    }

    /**  
     * 获取shh_user  
     * @return shh_user shh_user  
     */
    public String getShh_user() {
        return shh_user;
    }

    /**  
     * 设置shh_user  
     * @param shh_user shh_user  
     */
    public void setShh_user(String shh_user) {
        this.shh_user = shh_user;
    }

    /**  
     * 获取ssh_password  
     * @return ssh_password ssh_password  
     */
    public String getSsh_password() {
        return ssh_password;
    }

    /**  
     * 设置ssh_password  
     * @param ssh_password ssh_password  
     */
    public void setSsh_password(String ssh_password) {
        this.ssh_password = ssh_password;
    }

    /**  
     * 获取create_time  
     * @return create_time create_time  
     */
    public String getCreate_time() {
        return create_time;
    }

    /**  
     * 设置create_time  
     * @param create_time create_time  
     */
    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    /**  
     * 获取update_time  
     * @return update_time update_time  
     */
    public String getUpdate_time() {
        return update_time;
    }

    /**  
     * 设置update_time  
     * @param update_time update_time  
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
        this.setDbSql("pcInfo.updatePcInfo");
    }
}