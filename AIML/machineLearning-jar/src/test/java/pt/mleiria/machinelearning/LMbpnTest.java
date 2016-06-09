/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.mleiria.machinelearning;

import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.Assert;
import junit.framework.TestCase;
import pt.mleiria.machinelearning.neuralnet.LMbpn;

/**
 *
 * @author manuel
 */
public class LMbpnTest extends TestCase {
    private final static Logger LOGGER = Logger.getLogger(LMbpnTest.class.getName());
    int i ;
    int[] inp= {1, 0, 0,};
    int[] out = {0, 0};
    
    public void testTrain(){
        for(i = 0; i < 500; i++){
            LMbpn.train(1);
        }
        LOGGER.log(Level.INFO, "Trained Epochs:{0}", LMbpn.trainedEpochs);
        LMbpn.test(inp, out);
        Assert.assertEquals(1, out[0]);
        Assert.assertEquals(0, out[1]);
    }
    
}
