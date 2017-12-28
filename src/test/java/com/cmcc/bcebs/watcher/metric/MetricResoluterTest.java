/*******************************************************************************
 * @(#)MetricResoluterTest.java 2013-4-11
 *
 *******************************************************************************/
package com.cmcc.bcebs.watcher.metric;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigInteger;

import org.junit.Test;

import com.ganglia.watcher.db.bean.fault.FaultInfo;
import com.ganglia.watcher.db.bean.fault.FaultType;
import com.ganglia.watcher.db.bean.pc.PcInfo;
import com.ganglia.watcher.metric.InfoContainer;
import com.ganglia.watcher.metric.MetricResoluter;
import com.ganglia.watcher.xml.bean.METRIC;

/**
 * @version $Revision 1.1 $ 2013-4-11 下午2:54:30
 */
public class MetricResoluterTest {
    private MetricResoluter resoluter = null;
    
    private class TestMetricResoluter extends MetricResoluter{

        /* (non-Javadoc)
         * @see com.cmcc.bcebs.watcher.metric.MetricResoluter#canResolve(com.cmcc.bcebs.watcher.xml.bean.METRIC)
         */
        @Override
        public boolean canResolve(METRIC metric) {
            return false;
        }

        /* (non-Javadoc)
         * @see com.cmcc.bcebs.watcher.metric.MetricResoluter#resolve(com.cmcc.bcebs.watcher.xml.bean.METRIC)
         */
        @Override
        public void resolve(METRIC metric) {
            InfoContainer.getInstance().addObject(METRIC.class.getName(), metric);
        }
        
    }
    
    /**
     * <p>canResolve返回false</P>
     */
    @Test
    public void canResolveFalseTest(){
        PcInfo pc = new PcInfo();
        pc.setPc_id("pcid");
        pc.setHost_name("host_name");
        InfoContainer.getInstance().addObject(PcInfo.class.getName(), pc);
        resoluter = new TestMetricResoluter();
        METRIC metric = new METRIC();
        metric.setNAME("METRIC_NAME");
        metric.setTN(new BigInteger("100"));
        metric.setTMAX(new BigInteger("1000"));
        resoluter.canResolve(metric);
        assertTrue(!InfoContainer.getInstance().contain(METRIC.class.getName()));
    }
    
    /**
     * <p>正常流</P>
     */
    @Test
    public void excuteResolveTrueTest(){
        PcInfo pc = new PcInfo();
        pc.setPc_id("pcid");
        pc.setHost_name("host_name");
        InfoContainer.getInstance().addObject(PcInfo.class.getName(), pc);
        resoluter = mock(TestMetricResoluter.class);
        when(resoluter.canResolve(any(METRIC.class))).thenReturn(true);
        doCallRealMethod().when(resoluter).resolve(any(METRIC.class));
        doCallRealMethod().when(resoluter).excuteResolve(any(METRIC.class));
        METRIC metric = new METRIC();
        metric.setNAME("METRIC_NAME");
        metric.setTN(new BigInteger("100"));
        metric.setTMAX(new BigInteger("1000"));
        resoluter.excuteResolve(metric);
        assertTrue(InfoContainer.getInstance().contain(METRIC.class.getName()));
    }
    
    /**
     * <p>createPCFault方法</P>
     */
    @Test
    public void createPCFaultTest(){
        PcInfo pc = new PcInfo();
        pc.setPc_id("pcid");
        pc.setHost_name("host_name");
        InfoContainer.getInstance().addObject(PcInfo.class.getName(), pc);
        resoluter = new TestMetricResoluter();
        FaultInfo fault = resoluter.createPCFault(FaultType.FAULT_TYPE_PC, "faultInfo", null);
        if(InfoContainer.getInstance().contain(fault.toString())){
            assertEquals("faultInfo",fault.getFault_info());
        }else{
            assertTrue(false);
        }
    }
}
