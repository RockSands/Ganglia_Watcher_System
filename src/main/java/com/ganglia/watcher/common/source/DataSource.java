/*******************************************************************************
 * @(#)DataSource.java 2013-2-7
 *
 *******************************************************************************/
package com.ganglia.watcher.common.source;

import java.io.InputStream;

import com.ganglia.watcher.exception.EbsWatcherException;

/**
 * 数据源接口
 * @version $Revision 1.1 $ 2013-2-7 下午2:06:35
 */
public interface DataSource {
    /**
     * 获得信息源
     * @return
     * @throws EbsWatcherException
     */
    public InputStream getInputStream() throws EbsWatcherException;
    
    /**
     * 关闭信息源,准则:谁调用谁关闭
     * @throws EbsWatcherException
     */
    public void streamClose() throws EbsWatcherException;
}
