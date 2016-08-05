/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.mleiria.ml.attribute;

import pt.mleiria.ml.core.DataType;

/**
 *
 * @author manuel
 */
public class AttributeFactory {

    public Attribute createAttribute(DataType dType) {
        Attribute att;
        if (dType.equals(DataType.NUMERIC)) {
            att = new NumericalAttribute();
            return att;
        }
        if (dType.equals(DataType.NOMINAL)) {
            att = new NominalAttribute();
            return att;
        }
        if (dType.equals(DataType.BOOLEAN)) {
            att = new BooleanAttribute();
            return att;
        }
        if (dType.equals(DataType.INTEGER)) {
            att = new IntegerAttribute();
            return att;
        }
        return null;
    }
    
    public DataType discover(final String att){
        /**
         * Parse NUMERIC
         */
        try{
            final double d = Double.parseDouble(att);
            return DataType.NUMERIC;
        }catch(NumberFormatException nfe){
            //TODO Melhorar
            return DataType.NOMINAL;
        }
    }
}
