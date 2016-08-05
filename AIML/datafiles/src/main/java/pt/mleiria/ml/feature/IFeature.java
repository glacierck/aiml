/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.mleiria.ml.feature;

import java.util.List;
import pt.mleiria.ml.attribute.Attribute;
import pt.mleiria.ml.core.DataType;

/**
 *
 * @author manuel
 */
public interface IFeature {
   
    /**
     * 
     * @return 
     */
    List<Attribute> getAttList();
    /**
     * 
     * @return 
     */
    String getFeatureName();
    /**
     * 
     * @return 
     */
    DataType getFeatureType();
    
    
}
