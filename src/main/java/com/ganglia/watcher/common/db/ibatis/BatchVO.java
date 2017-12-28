package com.ganglia.watcher.common.db.ibatis;

/*
 * @(#)BatchVO.java
 *
 * Short Message Internet Access Solution Management System
 */


/**
 * <p>
 * 批处理VO列表：用于事务控制多次的修改。
 * </p>
 * @version Revision: 1.1 Date: 2005-7-15 13:19:12
 */

public class BatchVO {
    // 操作类型:ADD,DEL,MOD
    private String operate;

    // 实际操作的DAO的传入VO
    private Object object;

    // 调用ibatis的sql语句ID
    private String string;

    /**
     * @return Returns the operate.
     */
    public String getOperate() {
        return operate;
    }

    /**
     * @param operate The operate to set.
     */
    public void setOperate(String operate) {
        this.operate = operate;
    }

    /**
     * @param operate
     * @param object
     * @param string
     */
    public BatchVO(String operate, String string, Object object) {
        super();
        this.operate = operate;
        this.object = object;
        this.string = string;
    }

    /**
     * @return Returns the object.
     */
    public Object getObject() {
        return object;
    }

    /**
     * @param object The object to set.
     */
    public void setObject(Object object) {
        this.object = object;
    }

    /**
     * @return Returns the string.
     */
    public String getString() {
        return string;
    }

    /**
     * @param string The string to set.
     */
    public void setString(String string) {
        this.string = string;
    }
}
