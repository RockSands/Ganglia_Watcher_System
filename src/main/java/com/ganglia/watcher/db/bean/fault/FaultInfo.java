/*******************************************************************************
 * @(#)faultInfo.java 2013-4-10
 *
 *******************************************************************************/
package com.ganglia.watcher.db.bean.fault;

import com.ganglia.watcher.db.bean.DBBean;
import com.ganglia.watcher.db.bean.SqlOption;
import com.ganglias.watcher.tools.Util;

/**
 * @version $Revision 1.1 $ 2013-4-10 下午2:31:24
 */
public class FaultInfo extends DBBean {
    /**
     * ID
     */
    private String fault_id;

    /**
     * PCID
     */
    private String pc_id;

    /**
     * 类型
     */
    private String fault_type;

    /**
     * 信息
     */
    private String fault_info;

    /**
     * 创建时间
     */
    private String create_time;
    
    /**
     * 错误默认为执行插入,语句为:faultInfo.insertFaultInfo
     */
    public FaultInfo(){
        create_time = Util.time2String(null);
        setDbOption(SqlOption.OPTION_INSERT);
        setDbSql("faultInfo.insertFaultInfo");
    }

    /**
     * @return Returns the fault_id.
     */
    public String getFault_id() {
        return fault_id;
    }

    /**
     * @param fault_id The fault_id to set.
     */
    public void setFault_id(String fault_id) {
        this.fault_id = fault_id;
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
     * @return Returns the fault_type.
     */
    public String getFault_type() {
        return fault_type;
    }

    /**
     * @param fault_type The fault_type to set.
     */
    public void setFault_type(String fault_type) {
        this.fault_type = fault_type;
    }

    /**
     * @return Returns the fault_info.
     */
    public String getFault_info() {
        return fault_info;
    }

    /**
     * @param fault_info The fault_info to set.
     */
    public void setFault_info(String fault_info) {
        this.fault_info = fault_info;
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
     * @see com.cmcc.bcebs.watcher.db.bean.DBBean#setDBBean2Update()
     */
    @Override
    public void DBBean2UpdateState() {
    }
}
