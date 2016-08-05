/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.mleiria.datafiles;

import junit.framework.TestCase;
import pt.mleiria.EnvSettings;
import static pt.mleiria.datafiles.LoadFileTest.path;
import pt.mleiria.ml.core.GenericDataSet;
import pt.mleiria.ml.core.TextDataSet;

/**
 *
 * @author manuel
 */
public class LoadCsvFileTest extends TestCase {
    
    final static String path = EnvSettings.TORNADO_DATA;
    private GenericDataSet ds;

    @Override
    protected void setUp() throws Exception {
        String separator = ",";

        ds = new TextDataSet.TextDataSetBuilder(path)
                .separator(separator)
                .build();
        
        ds.loadData();
    }
    
    public void testInstances(){
        assertEquals(59944, ds.getInstancesCount());
    }
    
    
    
    
}
