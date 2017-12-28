/*******************************************************************************
 * @(#)DBBean.java 2013-4-3
 *
 *******************************************************************************/
package com.ganglia.watcher.db.bean;

import com.ganglias.watcher.tools.Util;

/**
 * @version $Revision 1.1 $ 2013-4-3 下午2:24:03
 */
public abstract class DBBean {
	private SqlOption dbOption = null;
	private String dbSql = null;

	/**
	 * @return Returns the dbOption.
	 */
	public SqlOption getDbOption() {
		return dbOption;
	}

	/**
	 * @param dbOption
	 */
	public void setDbOption(SqlOption dbOption) {
		if (SqlOption.OPTION_BAN != dbOption) {
			this.dbOption = dbOption;
		}
	}

	/**
	 * @return Returns the dbSql.
	 */
	public String getDbSql() {
		return dbSql;
	}

	/**
	 * @param dbSql
	 */
	public void setDbSql(String dbSql) {
		this.dbSql = dbSql;
	}

	/**
	 * <P>禁止数据库操作<P>
	 * 该状态只在整体数据提交时生效,但不阻止类属性值的变更
	 */
	public void banDBOption() {
		dbOption = SqlOption.OPTION_BAN;
	}
	
	/**
	 * 查看是否可以禁止数据库操作
	 * @return
	 */
	public boolean isBanDBOption() {
		return dbOption == SqlOption.OPTION_BAN;
	}

	/**
	 * <p>
	 * 更新属性值并设置为更新状态
	 * </P>
	 * 
	 * @param propertyName
	 * @param propertyValue
	 */
	public void updateProperty(String propertyName, Object propertyValue) {
		if (Util.setProperty(propertyName, propertyValue, this)
				&& SqlOption.OPTION_BAN != dbOption 
				&& SqlOption.OPTION_INSERT != dbOption) {
			DBBean2UpdateState();
		}
	}

	/**
	 * <p>
	 * 实现更新状态
	 * </P>
	 */
	public abstract void DBBean2UpdateState();
}
