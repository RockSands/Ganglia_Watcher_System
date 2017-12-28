/*******************************************************************************
 * @(#)LocalPort.java 2013-2-7
 *
 *******************************************************************************/
package com.ganglia.watcher.common.source;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import com.ganglia.watcher.exception.EbsWatcherException;
import com.ganglia.watcher.exception.ErrorCode;

/**
 * 网络数据源
 * @version $Revision 1.1 $ 2013-2-7 下午4:02:32
 */
public class SocketAddressDataSource implements DataSource {
    /**
     * ip地址
     */
    private String ip;
    /**
     * 端口
     */
    private Integer port;
    
    /**
     * socket连接
     */
    private Socket socketConnect;
    /**
     * socket地址
     */
    private InetSocketAddress address;
    
    /**
     * 采集连接时间
     */
    private int collectTimeOut = 10000;
    /**
     * 输入流
     */
    private InputStream in;
    
    /**
     * 获得信息流
     * @return
     * @throws EbsWatcherException
     */
    public InputStream getInputStream() throws EbsWatcherException {
        try {
            //如果关闭重新连接
            if(in == null || socketConnect.isClosed()){
                socketConnect = new Socket();
                address = new InetSocketAddress(ip,port);
                socketConnect.connect(address, collectTimeOut);
                in = socketConnect.getInputStream();
            }
            return in;
        } catch (Exception e) {
            streamClose();
            throw EbsWatcherException.wrap(ErrorCode.SOCKET_DATASOURCE_NOT_READ, "获取采集数据错误 IP:"+ip+" port:"+port,e);
        }
    }

    /**
     * 关闭连接流，使用在finally关闭
     */
    public void streamClose(){
        if(in != null){
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        if(socketConnect != null && !socketConnect.isClosed()){
            try {
                socketConnect.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @return Returns the ip.
     */
    public String getIp() {
        return ip;
    }

    /**
     * @param ip The ip to set.
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * @return Returns the port.
     */
    public Integer getPort() {
        return port;
    }

    /**
     * @param port The port to set.
     */
    public void setPort(Integer port) {
        this.port = port;
    }

    /**
     * @param collectTimeOut The collectTimeOut to set.
     */
    public void setCollectTimeOut(int collectTimeOut) {
        this.collectTimeOut = collectTimeOut;
    }
}
