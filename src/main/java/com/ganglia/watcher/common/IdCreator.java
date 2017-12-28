/*******************************************************************************
 * @(#)IdCreator.java 2012-1-6
 *
 *******************************************************************************/
package com.ganglia.watcher.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @version $Revision 1.1 $ 2012-1-6 下午04:35:00
 */
public class IdCreator {
    private static int currentId = 0;

    private static String lastTime = "";

    private static String id = "";

    /**
     * 支持每秒1000并发
     */
    private static final int MAX = 999;

    /**
     * ID产生方式为：12位时间戳+静态序列号 如：120107193520，其中序列号最大为999，时间戳不同时序列号归零
     * @param value 生成ID前缀,可以为空
     * @return id
     */
    public static synchronized String getId(String value) {
        String timestamp = new SimpleDateFormat("yyMMddHHmmss").format(
                Calendar.getInstance().getTime()).toString();
        if (!lastTime.equals(timestamp) || currentId >= MAX) {
            currentId = 0;
        } else {
            currentId++;
        }
        lastTime = timestamp;
        if (null == value || value.isEmpty()) {
            id = timestamp + currentId;
        } else {
            id = value + timestamp + currentId;
        }
        return id;
    }
}