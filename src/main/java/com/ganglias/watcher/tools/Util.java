/*******************************************************************************
 * @(#)StringUtil.java 2013-2-7
 *
 *******************************************************************************/
package com.ganglias.watcher.tools;

import java.beans.PropertyDescriptor;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 工具类
 * @version $Revision 1.1 $ 2013-2-7 下午1:27:55
 */
public class Util {
	
	private static Logger logger = LoggerFactory.getLogger(Util.class);
    
    public static final String STRING_SPLIT_MARK = "_";
    
    /**
     * 字符串是否为空
     * @param str
     * @return
     */
    public static boolean strIsEmpty(String str) {
        return str == null || "".equals(str.trim()) || "null".equalsIgnoreCase(str);
    }

    /**
     * <p>将属性值放入对象对应属性中</P>
     * True,设置成功 False,设置失败
     * @param propertyName
     * @param value
     * @param obj
     * @return
     */
    public static boolean setProperty(String propertyName, Object value, Object obj) {
        try {
            PropertyDescriptor property = new PropertyDescriptor(propertyName, obj.getClass());
            property.getWriteMethod().invoke(obj, value);
            return true;
        } catch (Exception ex) {
            return false;
        }

    }
    
    /**
     * <p>时间转换字符串</P>
     * 返回格式:yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String time2String(Date date){
        if(date == null){
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        }
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }
    
    /**
     * <p>字符串转化时间</P>
     * 字符串格式:yyyy-MM-dd HH:mm:ss
     * @param str
     * @return
     * @throws ParseException
     */
    public static Date string2Time(String str) throws ParseException{
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str);
    }
    
    /**
     * 日志debug输出
     * @param logger
     * @param message
     * @param t
     */
    public static void debug(Logger logger,String message,Throwable t){
        if(logger != null && logger.isDebugEnabled() && !strIsEmpty(message)){
            logger.debug(message, t);
        }
    }
    
    /**
     * 将字符流转换为字符串
     * @param stdout 输入的字符流
     * @return 返回转换后的字符串
     * @throws IOException 
     */
    public static String streamToString(InputStream stdout) throws IOException {
    	BufferedReader stdoutReader = new BufferedReader(new InputStreamReader(stdout));
        StringBuffer result = new StringBuffer();
        try {
        	String line = null;
            while (true) {
                line = null;
                line = stdoutReader.readLine();
                if (line == null) break;
                result.append(line);
                result.append("\n");
            }
        } catch (IOException e) {
            logger.error("将字符流转换为字符串异常", e);
        } finally {
            if (stdoutReader != null) {
                try {
                    stdoutReader.close();
                } catch (IOException e) {
                    logger.error("将字符流转换为字符串异常", e);
                }
            }
        }
        return result.toString();
    }
    
	/**
	 * 读取输入流写入文本并关闭输入流
	 * @param in
	 * @param file
	 * @throws IOException
	 */
	public static void streamToFile(InputStream in, File file)
			throws IOException {
		if (file.canWrite() && in != null) {
			FileOutputStream out = null;
			try {
				out = new FileOutputStream(file);
				BufferedReader stdoutReader = new BufferedReader(new InputStreamReader(in));
				String line = null;
				while (true) {
					line = stdoutReader.readLine();
	                if (line == null) break;
	                out.write(line.getBytes());
				}
			} finally {
				if (out != null) {
					try {
						out.close();
					} catch (IOException e) {
						logger.error(
								"文件[" + file.getPath() + "]关闭异常", e);
					}
				}
				in.close();
			}
		}
	}
}
