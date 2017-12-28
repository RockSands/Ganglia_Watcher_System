/*******************************************************************************
 * @(#)ServicestatMetricResoluterTest.java 2013-4-11
 *
 *******************************************************************************/
package com.cmcc.bcebs.watcher.metric;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigInteger;

import org.junit.Test;

import com.ganglia.watcher.db.bean.pc.PcInfo;
import com.ganglia.watcher.db.bean.pc.ShdNodeInfo;
import com.ganglia.watcher.db.bean.process.ServiceInfo;
import com.ganglia.watcher.db.bean.process.ServicesMetricInfo;
import com.ganglia.watcher.metric.InfoContainer;
import com.ganglia.watcher.metric.ServicestatMetricResoluter;
import com.ganglia.watcher.xml.bean.METRIC;
import com.ganglias.watcher.tools.Util;

/**
 * @version $Revision 1.1 $ 2013-4-11 上午10:18:31
 */
public class ServicestatMetricResoluterTest {
    private static final String METRIC_NAME = "service_zookeeper";
    
    /**
     * <p>Metric中Name为空时</P>
     */
    @Test
    public void canResolveNameNullTest(){
        PcInfo pc = new PcInfo();
        pc.setPc_id("pcid");
        pc.setHost_name("host_name");
        InfoContainer.getInstance().addObject(PcInfo.class.getName(), pc);
        ServicestatMetricResoluter resoluter = new ServicestatMetricResoluter();
        METRIC metric = mock(METRIC.class);
        when(metric.getNAME()).thenReturn(null);
        assertEquals(false,resoluter.canResolve(metric));
    }
    /**
     * <p>Metric不符合前缀规则</P>
     */
    @Test
    public void canResolveNameErrorTest(){
        PcInfo pc = new PcInfo();
        pc.setPc_id("pcid");
        pc.setHost_name("host_name");
        InfoContainer.getInstance().addObject(PcInfo.class.getName(), pc);
        ServicestatMetricResoluter resoluter = new ServicestatMetricResoluter();
        METRIC metric = mock(METRIC.class);
        when(metric.getNAME()).thenReturn(METRIC_NAME.toUpperCase());
        assertEquals(false,resoluter.canResolve(metric));
    }

    /**
     * <p>数据有效性炎症</P>
     */
    @Test
    public void canResolveCompareToTest(){
        ServicestatMetricResoluter resoluter = new ServicestatMetricResoluter();
        PcInfo pc = new PcInfo();
        pc.setPc_id("pcid");
        pc.setHost_name("host_name");
        InfoContainer.getInstance().addObject(PcInfo.class.getName(), pc);
        InfoContainer.getInstance().addObject(ShdNodeInfo.class.getName(),new ShdNodeInfo());
        METRIC metric = new METRIC();
        metric.setNAME("METRIC_NAME");
        metric.setTN(new BigInteger("100"));
        metric.setTMAX(new BigInteger("1000"));
        resoluter.excuteResolve(metric);
        assertEquals(false,resoluter.canResolve(metric));
    }
    /**
     * <p>canResolve通过炎症</P>
     */
    @Test
    public void canResolveTest(){
        PcInfo pc = new PcInfo();
        pc.setPc_id("pcid");
        pc.setHost_name("host_name");
        InfoContainer.getInstance().addObject(PcInfo.class.getName(), pc);
        ServicestatMetricResoluter resoluter = new ServicestatMetricResoluter();
        METRIC metric = mock(METRIC.class);
        when(metric.getNAME()).thenReturn(METRIC_NAME);
        when(metric.getTN()).thenReturn(new BigInteger("100"));
        when(metric.getTMAX()).thenReturn(new BigInteger("1000"));
        PcInfo pcInfo = new PcInfo();
        pcInfo.setHost_name("host_name");
        InfoContainer.getInstance().addObject(PcInfo.class.getName(), pcInfo);
        InfoContainer.getInstance().addObject(ShdNodeInfo.class.getName(),null);
        assertEquals(true,resoluter.canResolve(metric));
    }
    /**
     * <p></P>
     */
    @Test
    public void resolveServiceInfoTest(){
        ServicestatMetricResoluter resoluter = new ServicestatMetricResoluter();
        PcInfo pc = new PcInfo();
        pc.setPc_id("pcid");
        pc.setHost_name("host_name");
        InfoContainer.getInstance().addObject(PcInfo.class.getName(), pc);
        InfoContainer.getInstance().addObject(ShdNodeInfo.class.getName(),new ShdNodeInfo());
        METRIC metric = new METRIC();
        metric.setNAME(METRIC_NAME);
        metric.setTN(new BigInteger("100"));
        metric.setTMAX(new BigInteger("1000"));
        metric.setVAL("1");
        String serviceName = metric.getNAME().substring(resoluter.getStartWidthStr().length());
        resoluter.resolve(metric);
        assertEquals(true,InfoContainer.getInstance().contain(ServiceInfo.class.getName() + Util.STRING_SPLIT_MARK + serviceName));
    }
    @Test
    public void resolveServiceMetricTest(){
        ServicestatMetricResoluter resoluter = new ServicestatMetricResoluter();
        PcInfo pc = new PcInfo();
        pc.setPc_id("pcid");
        pc.setHost_name("host_name");
        InfoContainer.getInstance().addObject(PcInfo.class.getName(), pc);
        InfoContainer.getInstance().addObject(ShdNodeInfo.class.getName(),new ShdNodeInfo());
        METRIC metric = new METRIC();
        metric.setNAME(METRIC_NAME);
        metric.setTN(new BigInteger("100"));
        metric.setTMAX(new BigInteger("1000"));
        metric.setVAL("1");
        String serviceName = metric.getNAME().substring(resoluter.getStartWidthStr().length());
        resoluter.resolve(metric);
        assertEquals(true,InfoContainer.getInstance().contain(ServicesMetricInfo.class.getName() + Util.STRING_SPLIT_MARK + serviceName));
    }
    
}
