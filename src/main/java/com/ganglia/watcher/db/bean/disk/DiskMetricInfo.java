/*******************************************************************************
 * @(#)DiskMetricInfo.java 2013-3-25
 *
 *******************************************************************************/
package com.ganglia.watcher.db.bean.disk;


import java.util.Date;

import com.ganglia.watcher.db.bean.DBBean;
import com.ganglia.watcher.db.bean.SqlOption;
import com.ganglias.watcher.tools.Util;


/**
 * 磁盘监控信息
 * @version $Revision 1.1 $ 2013-3-25 下午4:39:23
 */
public class DiskMetricInfo extends DBBean{
    /**
     * ID
     */
    private String disk_metric_id = null;
    /**
     * 磁盘ID
     */
    private String disk_id = null;
    /**
     * 上报时间
     */
    private String report_time = null;
    /**
     * 磁盘标识
     */
    private String kernel = null;
    /**
     * 插拔信息
     */
    private String swap = null;
    /**
     * 健康
     */
    private String health = null;
    /**
     * 磁盘读速率
     */
    private String readBytes = null;
    /**
     * 磁盘写速率
     */
    private String writeBytes = null;
    /**
     * 完成的读请求的个数
     */
    private String reads = null;
    /**
     * 完成的写请求的个数
     */
    private String writes = null;
    /**
     * 温度
     */
    private String temperature = null;
    /**
     * 气流温度
     */
    private String airflow_temperature = null;
    /**
     * 底层数据读取错误率
     */
    private String raw_read_error_rate = null;
    /**
     * 不稳定的扇区的数量
     */
    private String current_pending_sector_counte = null;
    /**
     * 重定位扇区计数
     */
    private String reallocated_sector_count = null;
    /**
     * 无法校正的扇区计数
     */
    private String uncorrectable_sector_count = null;
    /**
     * 终端校验出错
     */
    private String end_to_end_error = null;
    /**
     * 无法连接至硬盘而终止操作的统计数
     */
    private String command_timeout = null;
    /**
     * 寻道错误率
     */
    private String seek_error_rate = null;
    /**
     * 电机起转重试计数
     */
    private String spin_retry_count = null;
    /**
     * 创建时间
     */
    private String create_time = null;
    
    public DiskMetricInfo(){
    };
    
    public DiskMetricInfo(Date time){
    	setDbOption(SqlOption.OPTION_INSERT);
    	setDbSql("diskMetricInfo.insertDiskMetricInfo");
        create_time = Util.time2String(time);
        report_time = Util.time2String(time);
    };
    /**
     * @return Returns the disk_metric_id.
     */
    public String getDisk_metric_id() {
        return disk_metric_id;
    }
    /**
     * @param disk_metric_id The disk_metric_id to set.
     */
    public void setDisk_metric_id(String disk_metric_id) {
        this.disk_metric_id = disk_metric_id;
    }
    /**
     * @return Returns the disk_id.
     */
    public String getDisk_id() {
        return disk_id;
    }
    /**
     * @param disk_id The disk_id to set.
     */
    public void setDisk_id(String disk_id) {
        this.disk_id = disk_id;
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
     * @return Returns the kernel.
     */
    public String getKernel() {
        return kernel;
    }
    /**
     * @param kernel The kernel to set.
     */
    public void setKernel(String kernel) {
        this.kernel = kernel;
    }
    /**
     * @return Returns the swap.
     */
    public String getSwap() {
        return swap;
    }
    /**
     * @param swap The swap to set.
     */
    public void setSwap(String swap) {
        this.swap = swap;
    }
    /**
     * @return Returns the health.
     */
    public String getHealth() {
        return health;
    }
    /**
     * @param health The health to set.
     */
    public void setHealth(String health) {
        this.health = health;
    }
    /**
     * @return Returns the reads.
     */
    public String getReads() {
        return reads;
    }
    /**
     * @param reads The reads to set.
     */
    public void setReads(String reads) {
        this.reads = reads;
    }
    /**
     * @return Returns the writes.
     */
    public String getWrites() {
        return writes;
    }
    /**
     * @param writes The writes to set.
     */
    public void setWrites(String writes) {
        this.writes = writes;
    }
    /**
     * @return Returns the temperature.
     */
    public String getTemperature() {
        return temperature;
    }
    /**
     * @param temperature The temperature to set.
     */
    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }
    /**
     * @return Returns the airflow_temperature.
     */
    public String getAirflow_temperature() {
        return airflow_temperature;
    }
    /**
     * @param airflow_temperature The airflow_temperature to set.
     */
    public void setAirflow_temperature(String airflow_temperature) {
        this.airflow_temperature = airflow_temperature;
    }
    /**
     * @return Returns the raw_read_error_rate.
     */
    public String getRaw_read_error_rate() {
        return raw_read_error_rate;
    }
    /**
     * @param raw_read_error_rate The raw_read_error_rate to set.
     */
    public void setRaw_read_error_rate(String raw_read_error_rate) {
        this.raw_read_error_rate = raw_read_error_rate;
    }
    /**
     * @return Returns the current_pending_sector_count.
     */
    public String getCurrent_pending_sector_counte() {
        return current_pending_sector_counte;
    }
    /**
     * @param current_pending_sector_count The current_pending_sector_count to set.
     */
    public void setCurrent_pending_sector_counte(String current_pending_sector_counte) {
        this.current_pending_sector_counte = current_pending_sector_counte;
    }
    /**
     * @return Returns the reallocated_sector_count.
     */
    public String getReallocated_sector_count() {
        return reallocated_sector_count;
    }
    /**
     * @param reallocated_sector_count The reallocated_sector_count to set.
     */
    public void setReallocated_sector_count(String reallocated_sector_count) {
        this.reallocated_sector_count = reallocated_sector_count;
    }
    /**
     * @return Returns the uncorrectable_sector_count.
     */
    public String getUncorrectable_sector_count() {
        return uncorrectable_sector_count;
    }
    /**
     * @param uncorrectable_sector_count The uncorrectable_sector_count to set.
     */
    public void setUncorrectable_sector_count(String uncorrectable_sector_count) {
        this.uncorrectable_sector_count = uncorrectable_sector_count;
    }
    /**
     * @return Returns the end_to_end_error.
     */
    public String getEnd_to_end_error() {
        return end_to_end_error;
    }
    /**
     * @param end_to_end_error The end_to_end_error to set.
     */
    public void setEnd_to_end_error(String end_to_end_error) {
        this.end_to_end_error = end_to_end_error;
    }
    /**
     * @return Returns the command_timeout.
     */
    public String getCommand_timeout() {
        return command_timeout;
    }
    /**
     * @param command_timeout The command_timeout to set.
     */
    public void setCommand_timeout(String command_timeout) {
        this.command_timeout = command_timeout;
    }
    /**
     * @return Returns the seek_error_rate.
     */
    public String getSeek_error_rate() {
        return seek_error_rate;
    }
    /**
     * @param seek_error_rate The seek_error_rate to set.
     */
    public void setSeek_error_rate(String seek_error_rate) {
        this.seek_error_rate = seek_error_rate;
    }
    /**
     * @return Returns the spin_retry_count.
     */
    public String getSpin_retry_count() {
        return spin_retry_count;
    }
    /**
     * @param spin_retry_count The spin_retry_count to set.
     */
    public void setSpin_retry_count(String spin_retry_count) {
        this.spin_retry_count = spin_retry_count;
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

	public String getReadBytes() {
		return readBytes;
	}

	public void setReadBytes(String readBytes) {
		this.readBytes = readBytes;
	}

	public String getWriteBytes() {
		return writeBytes;
	}

	public void setWriteBytes(String writeBytes) {
		this.writeBytes = writeBytes;
	}
	
    /* (non-Javadoc)
     * @see com.cmcc.bcebs.watcher.db.bean.DBBean#setDBBean2Update()
     */
    @Override
    public void DBBean2UpdateState() {
    }

}
