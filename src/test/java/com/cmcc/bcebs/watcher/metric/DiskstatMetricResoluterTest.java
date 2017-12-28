/*******************************************************************************
 * @(#)DiskstatMetricResoluterTest.java 2013-4-11
 *
 *******************************************************************************/
package com.cmcc.bcebs.watcher.metric;

import java.math.BigInteger;

import org.junit.Test;

import com.ganglia.watcher.db.bean.disk.DiskInfo;
import com.ganglia.watcher.db.bean.disk.DiskMetricInfo;
import com.ganglia.watcher.db.bean.pc.ClusterNodeInfo;
import com.ganglia.watcher.db.bean.pc.PcInfo;
import com.ganglia.watcher.db.bean.pc.ShdNodeInfo;
import com.ganglia.watcher.metric.DiskstatMetricResoluter;
import com.ganglia.watcher.metric.InfoContainer;
import com.ganglia.watcher.xml.bean.METRIC;
import com.ganglias.watcher.tools.Util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @version $Revision 1.1 $ 2013-4-11 上午10:17:56
 */
public class DiskstatMetricResoluterTest {
	
	DiskstatMetricResoluter diskstat = new DiskstatMetricResoluter();
	
	private static final String METRIC_SWAP = "diskStatus_sda_swap";
	private static final String METRIC_HEALTH = "diskStatus_sda_health";
	private static final String METRIC_ERROR = "diskStatus_sda_error";
	private static final String DISK = "sda";

    /**
     * <p>测试校验METRIC信息超时</P>
     */
    @Test
    public void testCanResolveOutTime(){
    	// 构造METRIC
    	METRIC metric = mock(METRIC.class);
    	when(metric.getNAME()).thenReturn(METRIC_SWAP);
    	when(metric.getTN()).thenReturn(new BigInteger("1000"));
        when(metric.getTMAX()).thenReturn(new BigInteger("100"));
        
    	// 构造PcInfo
    	PcInfo pcInfo = new PcInfo();
    	pcInfo.setPc_id("pc");
    	InfoContainer.getInstance().addObject(PcInfo.class.getName(), pcInfo);
    	
    	// 构造ShdNodeInfo
    	InfoContainer.getInstance().addObject(ShdNodeInfo.class.getName(), null);
    	
    	diskstat.excuteResolve(metric);
    	DiskMetricInfo diskMetric = InfoContainer.getInstance().getObject(
        		DiskMetricInfo.class.getName() + Util.STRING_SPLIT_MARK + DISK);
    	assertNull(diskMetric);
    } 
	
	/**
     * <p>测试METRIC信息NAME不为磁盘状态</P>
     */
    @Test
    public void testCanResolveNameError(){
    	METRIC metric = mock(METRIC.class);
    	when(metric.getNAME()).thenReturn(METRIC_SWAP.toUpperCase());
    	when(metric.getTN()).thenReturn(new BigInteger("100"));
        when(metric.getTMAX()).thenReturn(new BigInteger("1000"));
        
    	// 构造PcInfo
    	PcInfo pcInfo = new PcInfo();
    	pcInfo.setPc_id("pc");
    	InfoContainer.getInstance().addObject(PcInfo.class.getName(), pcInfo);
    	
    	// 构造ShdNodeInfo
    	InfoContainer.getInstance().addObject(ShdNodeInfo.class.getName(), null);
    	
    	diskstat.excuteResolve(metric);
    	DiskMetricInfo diskMetric = InfoContainer.getInstance().getObject(
        		DiskMetricInfo.class.getName() + Util.STRING_SPLIT_MARK + DISK);
    	assertNull(diskMetric);
    }
    
    /**
     * <p>测试磁盘信息不存在</P>
     */
    @Test
    public void testResolveDiskNull(){
    	// 构造METRIC
    	METRIC metric = mock(METRIC.class);
    	when(metric.getNAME()).thenReturn(METRIC_SWAP);
    	when(metric.getTN()).thenReturn(new BigInteger("100"));
        when(metric.getTMAX()).thenReturn(new BigInteger("1000"));
    	
    	// 构造PcInfo
    	PcInfo pcInfo = new PcInfo();
    	pcInfo.setPc_id("pc");
    	InfoContainer.getInstance().addObject(PcInfo.class.getName(), pcInfo);
    	
    	// 构造ShdNodeInfo
    	InfoContainer.getInstance().addObject(ShdNodeInfo.class.getName(), null);
    	
    	diskstat.excuteResolve(metric);
    	DiskMetricInfo diskMetric = InfoContainer.getInstance().getObject(
        		DiskMetricInfo.class.getName() + Util.STRING_SPLIT_MARK + DISK);
    	assertNull(diskMetric);
    }
    
    /**
     * <p>测试监控指标名称不合法</P>
     */
    @Test
    public void testResolveKeyError(){
    	// 构造METRIC
    	METRIC metric = mock(METRIC.class);
    	when(metric.getNAME()).thenReturn(METRIC_ERROR);
    	when(metric.getTN()).thenReturn(new BigInteger("100"));
        when(metric.getTMAX()).thenReturn(new BigInteger("1000"));

    	// 构造PcInfo
    	PcInfo pcInfo = new PcInfo();
    	pcInfo.setPc_id("pc");
    	InfoContainer.getInstance().addObject(PcInfo.class.getName(), pcInfo);
    	
    	// 构造ShdNodeInfo
    	InfoContainer.getInstance().addObject(ShdNodeInfo.class.getName(), null);
    	
    	//构造DiskInfo
    	DiskInfo diskInfo = new DiskInfo();
    	InfoContainer.getInstance().addObject(
    			DiskInfo.class.getName() + Util.STRING_SPLIT_MARK + DISK, diskInfo);
    	
    	diskstat.excuteResolve(metric);
    	DiskMetricInfo diskMetric = InfoContainer.getInstance().getObject(
        		DiskMetricInfo.class.getName() + Util.STRING_SPLIT_MARK + DISK);
    	assertNotNull(diskMetric);
    	assertNull(diskMetric.getSwap());
    	assertNull(diskMetric.getHealth());
    }
    
    /**
     * <p>测试插拔状态正常</P>
     */
    @Test
    public void testResolveSwapNormal(){
    	// 构造METRIC
    	METRIC metric = mock(METRIC.class);
    	when(metric.getNAME()).thenReturn(METRIC_SWAP);
    	when(metric.getVAL()).thenReturn("1");
    	when(metric.getTN()).thenReturn(new BigInteger("100"));
        when(metric.getTMAX()).thenReturn(new BigInteger("1000"));
        
    	// 构造PcInfo
    	PcInfo pcInfo = new PcInfo();
    	pcInfo.setPc_id("pc");
    	InfoContainer.getInstance().addObject(PcInfo.class.getName(), pcInfo);
        
    	// 构造ShdNodeInfo
    	InfoContainer.getInstance().addObject(ShdNodeInfo.class.getName(), null);
    	
    	//构造DiskInfo
    	DiskInfo diskInfo = new DiskInfo();
    	InfoContainer.getInstance().addObject(
    			DiskInfo.class.getName() + Util.STRING_SPLIT_MARK + DISK, diskInfo);
    	
    	diskstat.excuteResolve(metric);
    	DiskMetricInfo diskMetric = InfoContainer.getInstance().getObject(
        		DiskMetricInfo.class.getName() + Util.STRING_SPLIT_MARK + DISK);
    	assertEquals("1", diskInfo.getStatus_swap());
    	assertEquals("1", diskMetric.getSwap());
    }
    
    /**
     * <p>测试健康状态正常</P>
     */
    @Test
    public void testResolveHealthNormal(){
    	// 构造METRIC
    	METRIC metric = mock(METRIC.class);
    	when(metric.getNAME()).thenReturn(METRIC_HEALTH);
    	when(metric.getVAL()).thenReturn("1");
    	when(metric.getTN()).thenReturn(new BigInteger("100"));
        when(metric.getTMAX()).thenReturn(new BigInteger("1000"));
    	  
    	// 构造PcInfo
    	PcInfo pcInfo = new PcInfo();
    	pcInfo.setPc_id("pc");
    	InfoContainer.getInstance().addObject(PcInfo.class.getName(), pcInfo);
        
    	// 构造ShdNodeInfo
    	InfoContainer.getInstance().addObject(ShdNodeInfo.class.getName(), null);
    	
    	//构造DiskInfo
    	DiskInfo diskInfo = new DiskInfo();
    	InfoContainer.getInstance().addObject(
    			DiskInfo.class.getName() + Util.STRING_SPLIT_MARK + DISK, diskInfo);
    	
    	diskstat.excuteResolve(metric);   	
    	DiskMetricInfo diskMetric = InfoContainer.getInstance().getObject(
        		DiskMetricInfo.class.getName() + Util.STRING_SPLIT_MARK + DISK);
    	assertEquals("1", diskInfo.getStatus_health());
    	assertEquals("1", diskMetric.getHealth());
    }
    
    /**
     * <p>测试插拔状态故障</P>
     */
    @Test
    public void testResolveSwapFault(){
    	// 构造METRIC
    	METRIC metric = mock(METRIC.class);
    	when(metric.getNAME()).thenReturn(METRIC_SWAP);
    	when(metric.getVAL()).thenReturn("0");
    	when(metric.getTN()).thenReturn(new BigInteger("100"));
        when(metric.getTMAX()).thenReturn(new BigInteger("1000"));
        
    	// 构造PcInfo
    	PcInfo pcInfo = new PcInfo();
    	pcInfo.setPc_id("pc");
    	InfoContainer.getInstance().addObject(PcInfo.class.getName(), pcInfo);
  	
    	// 构造ShdNodeInfo
        ShdNodeInfo shdNodeInfo = new ShdNodeInfo();
    	InfoContainer.getInstance().addObject(ShdNodeInfo.class.getName(), shdNodeInfo);
   	
    	//构造DiskInfo
    	DiskInfo diskInfo = new DiskInfo();
    	InfoContainer.getInstance().addObject(
    			DiskInfo.class.getName() + Util.STRING_SPLIT_MARK + DISK, diskInfo);
    	
    	diskstat.excuteResolve(metric);   
    	DiskMetricInfo diskMetric = InfoContainer.getInstance().getObject(
        		DiskMetricInfo.class.getName() + Util.STRING_SPLIT_MARK + DISK);
    	assertEquals("0", diskInfo.getStatus_swap());
    	assertEquals("0", diskMetric.getSwap());
    	assertEquals("3", shdNodeInfo.getStatus_startup());
    }
    
    /**
     * <p>测试健康状态故障</P>
     */
    @Test
    public void testResolveHealthFault(){
    	// 构造METRIC
    	METRIC metric = mock(METRIC.class);
    	when(metric.getNAME()).thenReturn(METRIC_HEALTH);
    	when(metric.getVAL()).thenReturn("0");
    	when(metric.getTN()).thenReturn(new BigInteger("100"));
        when(metric.getTMAX()).thenReturn(new BigInteger("1000"));
        
    	// 构造PcInfo
    	PcInfo pcInfo = new PcInfo();
    	pcInfo.setPc_id("pc");
    	InfoContainer.getInstance().addObject(PcInfo.class.getName(), pcInfo);
    	
    	// 构造ShdNodeInfo
        ShdNodeInfo shdNodeInfo = new ShdNodeInfo();
    	InfoContainer.getInstance().addObject(ShdNodeInfo.class.getName(), shdNodeInfo);
    	
    	//构造DiskInfo
    	DiskInfo diskInfo = new DiskInfo();
    	InfoContainer.getInstance().addObject(
    			DiskInfo.class.getName() + Util.STRING_SPLIT_MARK + DISK, diskInfo);
    	
    	diskstat.excuteResolve(metric);   
    	DiskMetricInfo diskMetric = InfoContainer.getInstance().getObject(
        		DiskMetricInfo.class.getName() + Util.STRING_SPLIT_MARK + DISK);
    	assertEquals("0", diskInfo.getStatus_health());
    	assertEquals("0", diskMetric.getHealth());
    	assertEquals("3", shdNodeInfo.getStatus_startup());
    }
    
    /**
     * <p>测试集群节点所在PC机监控到故障</P>
     */
    @Test
    public void testResolveClusterFault(){
    	// 构造METRIC
    	METRIC metric = mock(METRIC.class);
    	when(metric.getNAME()).thenReturn(METRIC_SWAP);
    	when(metric.getVAL()).thenReturn("0");
    	when(metric.getTN()).thenReturn(new BigInteger("100"));
        when(metric.getTMAX()).thenReturn(new BigInteger("1000"));

    	// 构造PcInfo
    	PcInfo pcInfo = new PcInfo();
    	pcInfo.setPc_id("pc");
    	InfoContainer.getInstance().addObject(PcInfo.class.getName(), pcInfo);
    	
    	// 构造ShdNodeInfo
        ShdNodeInfo shdNodeInfo = new ShdNodeInfo();
    	InfoContainer.getInstance().addObject(ShdNodeInfo.class.getName(), shdNodeInfo);

    	// 构造ClusterNodeInfo
    	ClusterNodeInfo cluster = new ClusterNodeInfo();
    	InfoContainer.getInstance().addObject(ClusterNodeInfo.class.getName(), cluster);
    	
    	//构造DiskInfo
    	DiskInfo diskInfo = new DiskInfo();
    	InfoContainer.getInstance().addObject(
    			DiskInfo.class.getName() + Util.STRING_SPLIT_MARK + DISK, diskInfo);
    	
    	diskstat.excuteResolve(metric);   
    	DiskMetricInfo diskMetric = InfoContainer.getInstance().getObject(
        		DiskMetricInfo.class.getName() + Util.STRING_SPLIT_MARK + DISK);
    	assertEquals("0", diskInfo.getStatus_swap());
    	assertEquals("0", diskMetric.getSwap());
    	assertEquals("3", shdNodeInfo.getStatus_startup());
    	assertEquals("3", cluster.getStatus_startup());
    }
}
