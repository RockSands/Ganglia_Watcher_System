/*******************************************************************************
 * @(#)MemoryMetricInfo.java 2013-4-28
 *
 *******************************************************************************/
package com.ganglia.watcher.db.bean.memory;

import java.util.Date;

import com.ganglia.watcher.db.bean.DBBean;
import com.ganglia.watcher.db.bean.SqlOption;
import com.ganglias.watcher.tools.Util;

/**
 * 内存信息
 * @version $Revision 1.1 $ 2013-4-28 上午10:10:06
 */
public class MemoryMetricInfo extends DBBean {
    /**
     * ID
     */
    private String memoryMetricId = null;
    /**
     * PCID
     */
    private String pcId = null;
    
    /**
     * 使用率
     */
    private String useRate = null;    
    
    /**
     * BUFFER
     */
    private String buffers = null; 
    
    /**
     * CACHED
     */
    private String cached = null;    
    
    /**
     * FREE
     */
    private String memFree = null;     
    
    /**
     * TOTAL
     */
    private String memTotal = null;      
    
    /**
     * 上报时间
     */
    private String reportTime = null; 
    /**
     * 创建时间
     */
    private String createTime = null;  
    
    

    /**
     * @return Returns the memoryMetricId.
     */
    public String getMemoryMetricId() {
        return memoryMetricId;
    }

    /**
     * @param memoryMetricId The memoryMetricId to set.
     */
    public void setMemoryMetricId(String memoryMetricId) {
        this.memoryMetricId = memoryMetricId;
    }

    /**
     * @return Returns the pcId.
     */
    public String getPcId() {
        return pcId;
    }

    /**
     * @param pcId The pcId to set.
     */
    public void setPcId(String pcId) {
        this.pcId = pcId;
    }

    /**
     * @return Returns the useRate.
     */
    public String getUseRate() {
        return useRate;
    }

    /**
     * @param useRate The useRate to set.
     */
    public void setUseRate(String useRate) {
        this.useRate = useRate;
    }

    /**
     * @return Returns the buffers.
     */
    public String getBuffers() {
        return buffers;
    }

    /**
     * @param buffers The buffers to set.
     */
    public void setBuffers(String buffers) {
        this.buffers = buffers;
    }

    /**
     * @return Returns the cached.
     */
    public String getCached() {
        return cached;
    }

    /**
     * @param cached The cached to set.
     */
    public void setCached(String cached) {
        this.cached = cached;
    }



    /**
     * @return Returns the memFree.
     */
    public String getMemFree() {
        return memFree;
    }

    /**
     * @param memFree The memFree to set.
     */
    public void setMemFree(String memFree) {
        this.memFree = memFree;
    }

    /**
     * @return Returns the memTotal.
     */
    public String getMemTotal() {
        return memTotal;
    }

    /**
     * @param memTotal The memTotal to set.
     */
    public void setMemTotal(String memTotal) {
        this.memTotal = memTotal;
    }

    /**
     * @return Returns the reportTime.
     */
    public String getReportTime() {
        return reportTime;
    }

    /**
     * @param reportTime The reportTime to set.
     */
    public void setReportTime(String reportTime) {
        this.reportTime = reportTime;
    }

    /**
     * @return Returns the createTime.
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime The createTime to set.
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /* (non-Javadoc)
     * @see com.cmcc.bcebs.watcher.db.bean.DBBean#DBBean2UpdateState()
     */
    @Override
    public void DBBean2UpdateState() {
 
    }
    
    public MemoryMetricInfo(){
        
    }
    
    public MemoryMetricInfo(Date time){
    	setDbOption(SqlOption.OPTION_INSERT);
    	setDbSql("memoryMetricInfo.insertMemoryMetricInfo");
        createTime = Util.time2String(time);
        reportTime = Util.time2String(time);
    };    

}
