/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.mleiria.datafiles;

import java.util.logging.Logger;
import junit.framework.TestCase;
import pt.mleiria.EnvSettings;
import pt.mleiria.ml.core.GenericDataSet;
import pt.mleiria.ml.core.TextDataSet;

/**
 *
 * @author manuel
 */
public class LoadFileTest extends TestCase {

    private final static Logger LOGGER = Logger.getLogger(LoadFileTest.class.getName());
    final static String path = EnvSettings.EX1DATA1;
    private GenericDataSet ds;

    @Override
    protected void setUp() throws Exception {
        String separator = ",";
        ds = new TextDataSet(separator, path);
        ds.loadData();
    }

    public void testFeatureNames() {
        assertEquals("cityPopulation", ds.getFeatureNames()[0]);
        assertEquals("profit", ds.getFeatureNames()[1]);
    }

    public void testAttributesCount() {
        assertEquals(ds.getFeatureList().get(0).getAttList().size(), 97);
    }

    public void testFeaturesCount() {
        assertEquals(ds.getFeatureCount(), 2);
    }
    
    public void testFeatureNamesinFeature(){
        assertEquals("cityPopulation", ds.getFeatureList().get(0).getFeatureName());
    }
    
    public void testIsYNumeric(){
        assertEquals(true, ds.isYNumeric());
    }
    
    public void testIsYNominal(){
        assertEquals(false, ds.isYNominal());
    }

}
