/*******************************************************************************
 * @(#)DiskInof.java 2013-1-12
 *
 *******************************************************************************/
package com.ganglia.watcher.db.bean.disk;

import com.ganglia.watcher.db.bean.DBBean;
import com.ganglia.watcher.db.bean.SqlOption;
import com.ganglias.watcher.tools.Util;

/**
 * 磁盘信息
 * @version $Revision 1.1 $ 2013-1-12 下午8:13:05
 */
public class DiskInfo extends DBBean{
    /**
     * 磁盘ID
     */
    private String disk_id;
    /**
     * 主机ID
     */
    private String pc_id;
    /**
     * Raid设备ID
     */
    private String raid_id;
    /**
     * 磁盘SERIAL
     */
    private String serial;
    /**
     * 磁盘盘符
     */
    private String kernel;
    /**
     * 磁盘插槽
     */
    private String channel;
    /**
     * 磁盘路径
     */
    private String path;
    /**
     * 磁盘总空�?单位:MB)
     */
    private String size;
    /**
     * 磁盘插拔状�?(0:拔出;1:插入)
     */
    private String status_swap;
    /**
     * 磁盘使用状态(0:未使用1:已使用)
     */
    private String status_used;
    /**
     * 磁盘健康状态(0:故障;1:健康)
     */
    private String status_health;
    /**
     * 创建时间
     */
    private String create_time;
    /**
     * 更新时间
     */
    private String update_time;
    /**  
     * 获取disk_id  
     * @return disk_id disk_id  
     */
    public String getDisk_id() {
        return disk_id;
    }
    /**  
     * 设置disk_id  
     * @param disk_id disk_id  
     */
    public void setDisk_id(String disk_id) {
        this.disk_id = disk_id;
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
     * 获取raid_id  
     * @return raid_id raid_id  
     */
    public String getRaid_id() {
        return raid_id;
    }
    /**  
     * 设置raid_id  
     * @param raid_id raid_id  
     */
    public void setRaid_id(String raid_id) {
        this.raid_id = raid_id;
    }
    /**  
     * 获取serial  
     * @return serial serial  
     */
    public String getSerial() {
        return serial;
    }
    /**  
     * 设置serial  
     * @param serial serial  
     */
    public void setSerial(String serial) {
        this.serial = serial;
    }
    /**  
     * 获取kernel  
     * @return kernel kernel  
     */
    public String getKernel() {
        return kernel;
    }
    /**  
     * 设置kernel  
     * @param kernel kernel  
     */
    public void setKernel(String kernel) {
        this.kernel = kernel;
    }
    /**  
     * 获取channel  
     * @return channel channel  
     */
    public String getChannel() {
        return channel;
    }
    /**  
     * 设置channel  
     * @param channel channel  
     */
    public void setChannel(String channel) {
        this.channel = channel;
    }
    /**  
     * 获取path  
     * @return path path  
     */
    public String getPath() {
        return path;
    }
    /**  
     * 设置path  
     * @param path path  
     */
    public void setPath(String path) {
        this.path = path;
    }
    /**  
     * 获取size  
     * @return size size  
     */
    public String getSize() {
        return size;
    }
    /**  
     * 设置size  
     * @param size size  
     */
    public void setSize(String size) {
        this.size = size;
    }
    /**  
     * 获取status_swap  
     * @return status_swap status_swap  
     */
    public String getStatus_swap() {
        return status_swap;
    }
    /**  
     * 设置status_swap  
     * @param status_swap status_swap  
     */
    public void setStatus_swap(String status_swap) {
        this.status_swap = status_swap;
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
     * 获取status_health  
     * @return status_health status_health  
     */
    public String getStatus_health() {
        return status_health;
    }
    /**  
     * 设置status_health  
     * @param status_health status_health  
     */
    public void setStatus_health(String status_health) {
        this.status_health = status_health;
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
        setUpdate_time(Util.time2String(null));
        setDbOption(SqlOption.OPTION_UPDATE);
        setDbSql("diskInfo.updateDiskInfo");
    }

}
