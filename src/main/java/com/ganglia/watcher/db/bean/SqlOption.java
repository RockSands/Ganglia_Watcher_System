/*******************************************************************************
 * @(#)SqlOption.java 2013-4-3
 *
 *******************************************************************************/
package com.ganglia.watcher.db.bean;

/**
 * @version $Revision 1.1 $ 2013-4-3 下午2:49:53
 */
public enum SqlOption {
    /**
     * 新增
     */
    OPTION_INSERT("ADD"),
    /**
     * 更新
     */
    OPTION_UPDATE("MOD"),
    /**
     * 删除
     */
    OPTION_DELETE("DEL"),
    /**
     * 禁止数据库操作
     */
    OPTION_BAN("BAN");
    
    private final String option;

    SqlOption(String option) {
        this.option = option;
    }

    @Override
    public String toString() {
        return option;
    } 
}
