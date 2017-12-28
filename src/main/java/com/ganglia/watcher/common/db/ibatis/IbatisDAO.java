package com.ganglia.watcher.common.db.ibatis;

import java.sql.SQLException;
import java.util.List;

/**
 * <p>
 * Description:数据库操作接口
 * </p>
 * @version Revision: 1.0
 * @since Date: 2009-3-20 下午02:47:07
 */
public interface IbatisDAO {

    /**
     * 获取多行数据
     * @param statement sql 声明ID
     * @param business 输入参数
     * @return
     * @throws SQLException
     */
    public <T>T getData(String statement, Object business) throws SQLException;

    /**
     * 获取单行数据
     * @param statement
     * @param business
     * @return
     * @throws SQLException
     */
    <T>T getSingleRecord(String statement, Object business) throws SQLException;

    /**
     * 插入单行记录
     * @param statement
     * @param business
     * @return
     * @throws SQLException
     */
    <T>T insertData(String statement, T business) throws SQLException;

    /**
     * 更新单条记录
     * @param statement
     * @param business
     * @return
     * @throws SQLException
     */
    int updateData(String statement, Object business) throws SQLException;

    /**
     * 删除单条记录
     * @param statement
     * @param id
     * @return
     * @throws SQLException
     */
    int deleteData(String statement, Object id) throws SQLException;

    /**
     * 批量执行多条语句
     * @param statement
     * @param id
     * @return
     * @throws SQLException
     */
    void updateBatchData(List<BatchVO> list) throws SQLException;

    /**
     * 获取总记录数
     * @param clazz
     * @param obj
     * @return
     * @throws SQLException
     */
    int getCount(String statement, Object obj) throws SQLException;
}
