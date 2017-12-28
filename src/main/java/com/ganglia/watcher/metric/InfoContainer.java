/*******************************************************************************
 * @(#)InfoContainer.java 2013-3-31
 *
 *******************************************************************************/
package com.ganglia.watcher.metric;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 保存数据,应保存一个线程对应一个PC机
 * @version $Revision 1.1 $ 2013-3-31 下午5:16:39
 */
public class InfoContainer {
    
    private static ThreadLocal<InfoContainer> local;
    /**
     * 容器
     */
    private Map<String,Object> data = new HashMap<String,Object>();
    
    private InfoContainer(){}
    
    public synchronized static InfoContainer getInstance(){
        if(local == null){
            local = new ThreadLocal<InfoContainer>();
        }
        InfoContainer container = local.get();
        if(container == null){
            container = new InfoContainer();
            local.set(container);
        }
        return container;
    }
    
    @SuppressWarnings("unchecked")
    public <T>T getObject(String key){
        if(!data.containsKey(key)){
            return null;
        }
        return (T) data.get(key);
    };
    
    /**
     * <p>增加对象<p>
     * key格式建议保存为:类名_唯一标识
     * @param key
     * @param o
     */
    public void addObject(String key,Object o){
        data.put(key, o);
    }
    
    /**
     * 是否包含
     * @param key
     */
    public boolean contain(String key){
        return data.containsKey(key);
    }
    
    /**
     * <p>获得所有</P>
     */
    public Set<Entry<String,Object>> entrySet(){
        return data.entrySet();
    }
    
    /**
     * <p>清空</P>
     */
    public void clear(){
        data.clear();
    }

}
