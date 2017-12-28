package com.ganglias.watcher.tools;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import com.ganglia.watcher.exception.EbsWatcherException;
import com.ganglia.watcher.exception.ErrorCode;


/**
 * XML工具类
 */
public final class XML2beanUtil {
    //缓存
    private static Map<String, JAXBContext> jaxbMap = new HashMap<String, JAXBContext>();

    private XML2beanUtil() {
    }

    private static JAXBContext getJAXBContext(Class<? extends Object> clazz) throws JAXBException{
        JAXBContext context = null;
        context = jaxbMap.get(clazz.getName());
        if(context == null){
            context = JAXBContext.newInstance(clazz);
            jaxbMap.put(clazz.getName(), context);
        }
        return context;
    }

    /**
     * 解析请求报文体xml
     * @param body
     * @return
     * @throws BodyXMLException
     */
    public static Object getReqBodyDomain(Class clazz, InputStream stream, String schemaLocation){
        Object resBody = null;
        try {
            JAXBContext jc = getJAXBContext(clazz);
            Unmarshaller unMarshaller = jc.createUnmarshaller();
            Schema schema = getSchema(schemaLocation);
            unMarshaller.setSchema(schema);
            resBody = unMarshaller.unmarshal(stream);
        } catch (Exception e) {
            throw EbsWatcherException.wrap(ErrorCode.XML_RESOLVE_ERROR,"xml解析错误",e);
        }
        return resBody;
    }
    
    /**
     * 解析请求报文体xml
     * @param body
     * @return
     * @throws BodyXMLException
     */
    public static Object getReqBodyDomain(Class<? extends Object> clazz, String xmlLocation, String schemaLocation){
        Object resBody = null;
        try {
            JAXBContext jc = getJAXBContext(clazz);
            InputStream stream = new FileInputStream(new File(System.getProperty("user.dir")+xmlLocation));
            Unmarshaller unMarshaller = jc.createUnmarshaller();
            Schema schema = getSchema(schemaLocation);
            unMarshaller.setSchema(schema);
            resBody = unMarshaller.unmarshal(stream);
        } catch (Exception e) {
            throw EbsWatcherException.wrap(ErrorCode.XML_RESOLVE_ERROR,"xml解析错误",e);
        }
        return resBody;
    }

    /**
     * 创建schema校验
     * @param schemaURI
     * @return
     * @throws Exception
     */
    private static Schema getSchema(String schemaLocation){

        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = null;
        try {
            schema = schemaFactory.newSchema(XML2beanUtil.class.getClassLoader().getResource(schemaLocation));
        } catch (Exception e) {
            throw EbsWatcherException.wrap(ErrorCode.XML_RESOLVE_ERROR,"schema文件解析失败",e);
        }
        return schema;
    }

    /**
     * 根据逻辑处理结果组装body XML
     * @param body
     * @return
     * @throws JAXBException
     * @throws UnsupportedEncodingException
     */
    public static String getRspBody(String namespace, Object rspBody){
        String reqBodyString = null;
        String standalone = " standalone=\"yes\"";
        try {
            // 组装BODY体
            JAXBContext context = jaxbMap.get(namespace);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            ByteArrayOutputStream fw = new ByteArrayOutputStream();
            marshaller.marshal(rspBody, fw);
            reqBodyString = new String(fw.toByteArray(), "UTF-8"); //$NON-NLS-1$
            reqBodyString = reqBodyString.replace(standalone, ""); //$NON-NLS-1$
        } catch (Exception e) {
            throw EbsWatcherException.wrap(ErrorCode.XML_RESOLVE_ERROR,"组装XML失败",e);
        }
        return reqBodyString;
    }
}
