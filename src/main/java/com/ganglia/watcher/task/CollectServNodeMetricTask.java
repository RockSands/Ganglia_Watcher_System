/*******************************************************************************
 * @(#)collectServNodeMetricTimer.java 2013-2-7
 *
 *******************************************************************************/
package com.ganglia.watcher.task;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ganglia.watcher.common.source.DataSource;
import com.ganglia.watcher.exception.EbsWatcherException;
import com.ganglia.watcher.exception.ErrorCode;
import com.ganglia.watcher.metric.HostResoluter;
import com.ganglia.watcher.xml.bean.GANGLIAXML;
import com.ganglias.watcher.tools.Util;
import com.ganglias.watcher.tools.XML2beanUtil;

/**
 * 采集定时任务
 * 
 * @version $Revision 1.1 $ 2013-3-29 下午2:44:38
 */
public class CollectServNodeMetricTask extends TimerTask {

	private static Logger logger = LoggerFactory.getLogger(CollectServNodeMetricTask.class);

	/**
	 * 数据源
	 */
	private DataSource dataSource;

	/**
	 * PC解析器
	 */
	private HostResoluter processor;

	/**
	 * 数据是否写入文件
	 */
	private int dateWriteFileFlg = 1;

	/**
	 * 数据写入路径
	 */
	private String dateWriteFilePath = "../GANGLIAXML/";

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.TimerTask#run()
	 */
	public void run() {
		try {
			logger.info("采集开始");
			GANGLIAXML xmlObj = getCollectData();
			if (xmlObj == null) {
				throw EbsWatcherException.wrap(ErrorCode.COLLECT_DATA_NULL, "未收集到数据");
			} else {
				processor.hostsResolve(xmlObj.getCLUSTER().getHOST());
			}
		} catch (EbsWatcherException ex) {
			logger.error("系统异常，该轮次终止\n原因：" + ex.getErrMsg(), ex);
		} catch (Exception ex) {
			logger.error("系统异常，该轮次终止。", ex);
		}
	}

	/**
	 * <p>
	 * 获得采集数据
	 * </P>
	 * 
	 * @return
	 * @throws IOException
	 * @throws EbsWatcherException
	 */
	private GANGLIAXML getCollectData() throws IOException {
		GANGLIAXML xmlObj = null;
		if (dateWriteFileFlg == 0) {
			try {
				xmlObj = (GANGLIAXML) XML2beanUtil.getReqBodyDomain(GANGLIAXML.class, dataSource.getInputStream(),
						"conf/PCMetricInfo.xsd");
			} finally {
				if (dataSource != null) {
					dataSource.streamClose();
				}
			}
		} else {
			Date date = new Date();
			File xmlFile = new File(dateWriteFilePath + new SimpleDateFormat("yyyy-MM-dd").format(date) + "/");
			xmlFile.mkdirs();
			xmlFile = new File(dateWriteFilePath + new SimpleDateFormat("yyyy-MM-dd").format(date) + "/dataSource_"
					+ new SimpleDateFormat("HHmmss").format(date) + ".xml");
			FileInputStream in = null;
			try {
				if (xmlFile.createNewFile()) {
					Util.streamToFile(dataSource.getInputStream(), xmlFile);
				}
				in = new FileInputStream(xmlFile);
				xmlObj = (GANGLIAXML) XML2beanUtil.getReqBodyDomain(GANGLIAXML.class, in, "conf/PCMetricInfo.xsd");
			} finally {
				if (in != null) {
					in.close();
				}
				if (dataSource != null) {
					dataSource.streamClose();
				}
			}
		}
		return xmlObj;

	}

	/**
	 * @return Returns the dataSource.
	 */
	public DataSource getDataSource() {
		return dataSource;
	}

	/**
	 * @param dataSource
	 *            The dataSource to set.
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * @return Returns the processor.
	 */
	public HostResoluter getProcessor() {
		return processor;
	}

	/**
	 * @param processor
	 *            The processor to set.
	 */
	public void setProcessor(HostResoluter processor) {
		this.processor = processor;
	}

	public int getDateWriteFileFlg() {
		return dateWriteFileFlg;
	}

	public void setDateWriteFileFlg(int dateWriteFileFlg) {
		this.dateWriteFileFlg = dateWriteFileFlg;
	}
}
