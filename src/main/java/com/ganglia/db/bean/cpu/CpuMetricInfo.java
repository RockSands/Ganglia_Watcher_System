/*******************************************************************************
 * @(#)CpuMetricInfo.java 2013-4-28
 *
 *******************************************************************************/
package com.ganglia.db.bean.cpu;

import java.util.Date;

import com.ganglia.watcher.db.bean.DBBean;
import com.ganglia.watcher.db.bean.SqlOption;
import com.ganglias.watcher.tools.Util;

/**
 * cpu信息
 * @version $Revision 1.1 $ 2013-4-28 上午9:42:06
 */
public class CpuMetricInfo extends DBBean {
    /**
     * ID
     */
    private String cpuMetricId = null;

    /**
     * PCID
     */
    private String pcId = null;

    /**
     * 使用率
     */
    private String user = null;

    /**
     * 上报时间
     */
    private String reportTime = null;

    /**
     * 创建时间
     */
    private String createTime = null;

    /**
     * @return Returns the cpuMetricId.
     */
    public String getCpuMetricId() {
        return cpuMetricId;
    }

    /**
     * @param cpuMetricId The cpuMetricId to set.
     */
    public void setCpuMetricId(String cpuMetricId) {
        this.cpuMetricId = cpuMetricId;
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
     * @return Returns the user.
     */
    public String getUser() {
        return user;
    }

    /**
     * @param user The user to set.
     */
    public void setUser(String user) {
        this.user = user;
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

    /*
     * (non-Javadoc)
     */
    @Override
    public void DBBean2UpdateState() {
    }

    public CpuMetricInfo() {

    }

    public CpuMetricInfo(Date time) {
        setDbOption(SqlOption.OPTION_INSERT);
        setDbSql("cpuMetricInfo.insertCpuMetricInfo");
        createTime = Util.time2String(time);
        reportTime = Util.time2String(time);
    };

}
