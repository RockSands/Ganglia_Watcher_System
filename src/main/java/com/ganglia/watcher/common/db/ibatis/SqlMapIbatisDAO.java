package com.ganglia.watcher.common.db.ibatis;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * 实现数据库存储操作
 * @version $Revision 1.1 $ 2009-4-12 上午09:47:40
 */
public class SqlMapIbatisDAO extends SqlMapClientDaoSupport implements IbatisDAO {

    @SuppressWarnings("unchecked")
    public <T>T getData(String statement, Object business) throws SQLException {
        return (T) getSqlMapClientTemplate().queryForList(statement, business);
    }

    /*
     * (non-Javadoc)
     * insertBusiness(java.lang.Object)
     */
    @SuppressWarnings("unchecked")
    public <T>T insertData(String statement, T business) throws SQLException {
        return (T) getSqlMapClientTemplate().insert(statement, business);

    }

    /*
     * (non-Javadoc)
     * updateBusiness(java.lang.Object)
     */
    public int updateData(String statement, Object business) throws SQLException {
        return getSqlMapClientTemplate().update(statement, business);

    }

    /*
     * (non-Javadoc)
     * deleteBusiness(java.lang.String)
     */
    public int deleteData(String statement, Object id) throws SQLException {
        return getSqlMapClientTemplate().delete(statement, id);

    }

    /*
     * (non-Javadoc)
     */
    public void updateBatchData(List<BatchVO> list) throws SQLException {
        SqlMapClient sqlMapClient = getSqlMapClient();
        try {
            sqlMapClient.startTransaction();
            for (Iterator<BatchVO> it = list.iterator(); it.hasNext();) {
                BatchVO batchVO = it.next();
                String operate = batchVO.getOperate();
                if ("ADD".equals(operate)) {
                    sqlMapClient.insert(batchVO.getString(), batchVO.getObject());
                } else if ("DEL".equals(operate)) {
                    sqlMapClient.delete(batchVO.getString(), batchVO.getObject());
                } else if ("MOD".equals(operate)) {
                    sqlMapClient.update(batchVO.getString(), batchVO.getObject());
                } else {
                    throw new UnsupportedOperationException("Invalidate operator '" + operate
                            + "' in batch VO");
                }
            }
            sqlMapClient.commitTransaction();
        } finally {
            sqlMapClient.endTransaction();
        }
    }

    @SuppressWarnings("unchecked")
    public <T>T getSingleRecord(String statement, Object business) throws SQLException {
        return (T)getSqlMapClientTemplate().queryForObject(statement, business);
    }

    public int getCount(String statement, Object obj) {
        Object result =getSqlMapClientTemplate().queryForObject(statement, obj);
        if(null == result){
            throw new RuntimeException(
                    "The result is null.Please check the sql statement.tip:Use NVL function");
        }
        return ((Integer) result).intValue();
    }
}
