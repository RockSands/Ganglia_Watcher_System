/*******************************************************************************
 * @(#)ErrorCode.java 2013-2-7
 *
 *******************************************************************************/
package com.ganglia.watcher.exception;

/**
 * 错误编码
 * @version $Revision 1.1 $ 2013-2-7 上午10:37:39
 */
public enum ErrorCode implements StatusCode {
    /**
     * 数据库访问异常
     */
    SQLEXCEPTION_DATABASE_EXCUTE_ERROR("0000"),
    
    /**
     * socket读取数据错误
     */
    SOCKET_DATASOURCE_NOT_READ("0001"),
    
    /**
     * inputStream关闭失败
     */
    INPUTSTREAM_CLOSE_ERROR("0002"),
    
    /**
     * 文件路径错误
     */
    FILE_PATH_ERROR("0003"),
    
    /**
     * 没有收集到信息
     */
    COLLECT_DATA_NULL("0004"),
    
    /**
     * 项目启动失败
     */
    SYSTEM_START_ERROR("-1"),
    
    /**
     * 采集数据错误
     */
    COLLECT_DATA_ERROR("0005"),
    
    /**
     * XML解析错误
     */
    XML_RESOLVE_ERROR("0006"),
    
    /**
     * METRIC解析错误
     */
    METRIC_RESOLVE_ERROR("0007"),
    
    /**
     * METRIC信息过时
     */
    METRIC_IS_TIMEOUT("0008"),
    
    /**
     * PC机信息不存在
     */
    PCINFO_IS_NOT_EXIST("0009"),
    
    /**
     * 运行异常
     */
    SYSTEM_RUNTIME_ERROR("0010"),
    
    /**
     * 磁盘信息不存在
     */
    DISKINFO_IS_NOT_EXIST("0011"),
    
    /**
     * 指标名称不合法
     */
    METRIC_NAME_ERROR("0012"),
    
    /**
     * 网卡信息不存在
     */
    NETCARD_IS_NOT_EXIST("0013"),
    
    /**
     * 文件写入错误
     */
    FILE_WRITE_ERROR("0014"),
    
    /**
     * 文件关闭错误
     */
    FILE_CLOSE_ERROR("0015");
    
    private final String code;

    ErrorCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }
}
