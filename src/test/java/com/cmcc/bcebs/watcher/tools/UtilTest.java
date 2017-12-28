/*******************************************************************************
 * @(#)Test.java 2013-4-9
 *
 *******************************************************************************/
package com.cmcc.bcebs.watcher.tools;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.ganglia.watcher.db.bean.pc.PcInfo;
import com.ganglias.watcher.tools.Util;



/**
 * @version $Revision 1.1 $ 2013-4-9 上午11:30:35
 */
public class UtilTest {

    @Test
    public void setPropertyTest() {
        PcInfo pc = new PcInfo();
        Util.setProperty("pc_id","test",pc);
        assertTrue("test".equals(pc.getPc_id()));
    }

}
