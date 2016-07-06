/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.mleiria.machinelearning;

import junit.framework.Assert;
import junit.framework.TestCase;
import org.apache.log4j.Logger;
import pt.mleiria.LogTypes;
import pt.mleiria.machinelearning.neuralnet.LMbpn;

/**
 *
 * @author manuel
 */
public class LMbpnTest extends TestCase {
    private static final Logger mlearningLog = Logger.getLogger(LogTypes.MLEARNING_LOG);
    int i ;
    int[] inp= {1, 0, 0,};
    int[] out = {0, 0};
    
    public void testTrain(){
        for(i = 0; i < 500; i++){
            LMbpn.train(1);
        }
        mlearningLog.info(LogTypes.formatLog("Trained Epochs:{0}", new Integer[]{LMbpn.trainedEpochs}));
        //mlearningLog.info("Trained Epochs: " + LMbpn.trainedEpochs);
        LMbpn.test(inp, out);
        Assert.assertEquals(1, out[0]);
        Assert.assertEquals(0, out[1]);
    }
    
}
