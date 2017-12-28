/*******************************************************************************
 * @(#)NetCard.java 2013-1-12
 *
 *******************************************************************************/
package com.ganglia.watcher.db.bean.netcard;

import com.ganglia.watcher.db.bean.DBBean;
import com.ganglia.watcher.db.bean.SqlOption;
import com.ganglias.watcher.tools.Util;


/**
 * 网卡信息
 * @version $Revision 1.1 $ 2013-1-12 下午8:53:48
 */
public class NetcardInfo extends DBBean{
    /**
     * 网卡ID
     */
    private String netcard_id;
    /**
     * 主机ID 
     */
    private String pc_id;
    /**
     * 网卡名称
     */
    private String name;
    /**
     * 使用目的(0:通用;1:业务;2:数据;3:未使用
     */
    private String use_type;
    /**
     * 网卡IP
     */
    private String ip;
    /**
     * 创建时间
     */
    private String create_time;
    /**
     * 更新时间
     */
    private String update_time;
    /**  
     * 获取netcard_id  
     * @return netcard_id netcard_id  
     */
    public String getNetcard_id() {
        return netcard_id;
    }
    /**  
     * 设置netcard_id  
     * @param netcard_id netcard_id  
     */
    public void setNetcard_id(String netcard_id) {
        this.netcard_id = netcard_id;
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
    /**  
     * 获取name  
     * @return name name  
     */
    public String getName() {
        return name;
    }
    /**  
     * 设置name  
     * @param name name  
     */
    public void setName(String name) {
        this.name = name;
    }
    /**  
     * 获取use_type  
     * @return use_type use_type  
     */
    public String getUse_type() {
        return use_type;
    }
    /**  
     * 设置use_type  
     * @param use_type use_type  
     */
    public void setUse_type(String use_type) {
        this.use_type = use_type;
    }
    /**  
     * 获取ip  
     * @return ip ip  
     */
    public String getIp() {
        return ip;
    }
    /**  
     * 设置ip  
     * @param ip ip  
     */
    public void setIp(String ip) {
        this.ip = ip;
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
     * @see com.cmcc.bcebs.watcher.db.bean.DBBean#setDBBean2Update()
     */
    @Override
    public void DBBean2UpdateState() {
        this.setUpdate_time(Util.time2String(null));
        this.setDbOption(SqlOption.OPTION_UPDATE);
        this.setDbSql("netcardInfo.updateNetcardInfo");
    }

}
