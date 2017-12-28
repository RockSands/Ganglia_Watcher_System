/*******************************************************************************
 * @(#)EbsWatcherException.java 2013-2-7
 *
 *******************************************************************************/
package com.ganglia.watcher.exception;

import java.util.HashMap;
import java.util.Map;


/**
 * 通用异常
 * @version $Revision 1.1 $ 2013-2-7 上午10:34:15
 */
public class EbsWatcherException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    
    /**
     * 错误编号
     */
    private StatusCode errorCode;
    
    /**
     * 常量:错误信息参数
     */
    private static final String errMsgPropertiesMapKey = "__errMsgProperties__";
    
    /**
     * 常量:错误信息
     */
    private static final String errMsgNameMapKey = "__errMsg__";
    
    /**
     * 承载物
     */
    private final Map<String,Object> objectMap = new HashMap<String,Object>();
    
    
    /**
     * @param errorCode
     * @param errMessage
     * @param errMsgProperties
     */
    private EbsWatcherException(StatusCode errorCode, String errMessage, String[] errMsgProperties,Throwable exception) {
        super(exception);
        this.errorCode = errorCode;
        objectMap.put(errMsgNameMapKey, errMessage);
        objectMap.put(errMsgPropertiesMapKey, errMsgProperties);
    }
    
    /**
     * @param errorCode
     * @param errMessage
     * @return
     */
    public static EbsWatcherException wrap(StatusCode errorCode,String errMessage,String[] errMsgProperties,Throwable exception) {
        if(exception instanceof EbsWatcherException){
            EbsWatcherException ex = (EbsWatcherException)exception;
            if(errorCode != null && errorCode != ex.getErrorCode()){
                return new EbsWatcherException(errorCode,errMessage,errMsgProperties,exception);
            }else{
                return ex;
            }
        }
        return new EbsWatcherException(errorCode,errMessage,errMsgProperties,exception);
    }
    
    /**
     * @param errorCode
     * @param errMessage
     * @return
     */
    public static EbsWatcherException wrap(StatusCode errorCode,String errMessage,Throwable exception) {
        if(exception instanceof EbsWatcherException){
            EbsWatcherException ex = (EbsWatcherException)exception;
            if(errorCode != null && errorCode != ex.getErrorCode()){
                return new EbsWatcherException(errorCode,errMessage,null,exception);
            }else{
                return ex;
            }
        }
        return new EbsWatcherException(errorCode,errMessage,null,exception);
    }
    
    /**
     * @param errorCode
     * @param errMessage
     * @param errMsgProperties
     * @return
     */
    public static EbsWatcherException wrap(StatusCode errorCode,String errMessage,String[] errMsgProperties) {
        return new EbsWatcherException(errorCode,errMessage,errMsgProperties,null);
    }
    
    /**
     * @param errorCode
     * @param errMessage
     * @return
     */
    public static EbsWatcherException wrap(StatusCode errorCode,String errMessage) {
        return new EbsWatcherException(errorCode,errMessage,null,null);
    }
    
    /**
     * 增加传递物
     * @param key
     * @param value
     * @return
     */
    public EbsWatcherException addObj(String key,Object value){
        objectMap.put(key, value);
        return this;
    }
    
    /**
     * 获得传递物
     * @param key
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T>T getObj(String key){
        return (T) objectMap.get(key);
    }
    
    /**
     * 重新测试错误信息
     * @param errMsg
     * @param errMsgProperties
     */
    public void resetErrMsg(String errMsg,String[] errMsgProperties){
        addObj(errMsgNameMapKey,errMsg);
        addObj(errMsgPropertiesMapKey,errMsgProperties);
    }
    
    /**
     * 获得错误信息
     * @return
     */
    public String getErrMsg(){
        return getObj(errMsgNameMapKey);
    }
    
    /**
     * 获得错误信息参数
     * @return
     */
    public String[] getErrMsgProperties(){
        return getObj(errMsgPropertiesMapKey);
    }
    
    /**
     * 获得错误编号
     * @return
     */
    public StatusCode getErrorCode(){
        return errorCode;
    }
}
