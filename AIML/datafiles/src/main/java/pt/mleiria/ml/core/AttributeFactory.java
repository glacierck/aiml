/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.mleiria.ml.core;

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
        return null;
    }
}
