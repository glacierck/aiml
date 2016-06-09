/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.mleiria.ml.core;

import java.util.List;

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
