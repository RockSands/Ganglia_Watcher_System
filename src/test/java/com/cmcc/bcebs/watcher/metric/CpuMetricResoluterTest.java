/*******************************************************************************
 * @(#)CpuMetricResoluterTest.java 2013-4-28
 *
 *******************************************************************************/
package com.cmcc.bcebs.watcher.metric;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigInteger;

import org.junit.Test;

import com.ganglia.db.bean.cpu.CpuMetricInfo;
import com.ganglia.watcher.db.bean.pc.PcInfo;
import com.ganglia.watcher.metric.CpuMetricResoluter;
import com.ganglia.watcher.metric.InfoContainer;
import com.ganglia.watcher.xml.bean.METRIC;
import com.ganglias.watcher.tools.Util;

/**
 * @version $Revision 1.1 $ 2013-4-28 下午2:16:10
 */
public class CpuMetricResoluterTest {
    private static final String METRIC_NAME = "cpu_useRate";
    
    /**
     * <p>Metric中Name为空时</P>
     */
    @Test
    public void canResolveNameNullTest(){
        PcInfo pc = new PcInfo();
        pc.setPc_id("pcid");
        pc.setHost_name("host_name");
        InfoContainer.getInstance().addObject(PcInfo.class.getName(), pc);
        CpuMetricResoluter resoluter = new CpuMetricResoluter();
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
        CpuMetricResoluter resoluter = new CpuMetricResoluter();
        METRIC metric = mock(METRIC.class);
        when(metric.getNAME()).thenReturn(METRIC_NAME.toUpperCase());
        assertEquals(false,resoluter.canResolve(metric));
    }

    /**
     * <p>数据有效性验证</P>
     */
    @Test
    public void canResolveCompareToTest(){
        CpuMetricResoluter resoluter = new CpuMetricResoluter();
        PcInfo pc = new PcInfo();
        pc.setPc_id("pcid");
        pc.setHost_name("host_name");
        InfoContainer.getInstance().addObject(PcInfo.class.getName(), pc);
        METRIC metric = new METRIC();
        metric.setNAME("METRIC_NAME");
        metric.setTN(new BigInteger("100"));
        metric.setTMAX(new BigInteger("1000"));
        resoluter.excuteResolve(metric);
        assertEquals(false,resoluter.canResolve(metric));
    }
    /**
     * <p>canResolve通过验证</P>
     */
    @Test
    public void canResolveTest(){
        PcInfo pc = new PcInfo();
        pc.setPc_id("pcid");
        pc.setHost_name("host_name");
        InfoContainer.getInstance().addObject(PcInfo.class.getName(), pc);
        CpuMetricResoluter resoluter = new CpuMetricResoluter();
        METRIC metric = mock(METRIC.class);
        when(metric.getNAME()).thenReturn(METRIC_NAME);
        when(metric.getTN()).thenReturn(new BigInteger("100"));
        when(metric.getTMAX()).thenReturn(new BigInteger("1000"));
        PcInfo pcInfo = new PcInfo();
        pcInfo.setHost_name("host_name");
        InfoContainer.getInstance().addObject(PcInfo.class.getName(), pcInfo);
        assertEquals(true,resoluter.canResolve(metric));
    }
    
    @Test
    public void resolveCpuMetricTest(){
        CpuMetricResoluter resoluter = new CpuMetricResoluter();
        PcInfo pc = new PcInfo();
        pc.setPc_id("pcid");
        pc.setHost_name("host_name");
        InfoContainer.getInstance().addObject(PcInfo.class.getName(), pc);
        METRIC metric = new METRIC();
        metric.setNAME(METRIC_NAME);
        metric.setTN(new BigInteger("100"));
        metric.setTMAX(new BigInteger("1000"));
        metric.setVAL("1");
        resoluter.resolve(metric);
        assertEquals(true,InfoContainer.getInstance().contain(CpuMetricInfo.class.getName() + Util.STRING_SPLIT_MARK + "CPU"));
    }
}
