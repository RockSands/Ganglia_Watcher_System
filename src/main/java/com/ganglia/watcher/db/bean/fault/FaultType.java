/*******************************************************************************
 * @(#)FaultType.java 2013-4-10
 *
 *******************************************************************************/
package com.ganglia.watcher.db.bean.fault;

/**
 * @version $Revision 1.1 $ 2013-4-10 下午3:33:47
 */
public enum FaultType {
    /**
     * 进程错误
     */
    FAULT_TYPE_SERVICE("SERVICE"),
    /**
     * 磁盘错误
     */
    FAULT_TYPE_DISK("DISK"),
    /**
     * 网卡错误
     */
    FAULT_TYPE_NETCARD("NETCARD"),
    /**
     * PC错误
     */
    FAULT_TYPE_PC("PC");
    
    private final String code;

    FaultType(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }
}
