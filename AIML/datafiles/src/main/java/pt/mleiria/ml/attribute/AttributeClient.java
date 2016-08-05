/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.mleiria.ml.attribute;

import pt.mleiria.ml.core.DataType;

/**
 * This is the client of the factory
 *
 * @author manuel
 */
public class AttributeClient {

    AttributeFactory factory;
    /**
     * 
     * @param factory 
     */
    public AttributeClient(AttributeFactory factory) {
        this.factory = factory;
    }
    /**
     * 
     * @param dType
     * @return 
     */
    public Attribute getAttribute(DataType dType){
        return factory.createAttribute(dType);
    }
    
    public DataType discover(final String att){
        return factory.discover(att);
    }
    
    
}
