/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.mleiria.ml.attribute;

/**
 *
 * @author manuel
 */
public class NumericalAttribute extends Attribute {

    private double value;

    /**
     *
     * @return
     */
    public double getValue() {
        return value;
    }

    /**
     *
     * @param value
     */
    public void setValue(double value) {
        this.value = value;
    }

    /**
     *
     * @param value
     */
    @Override
    public void setValue(final String value) {
        try{
            this.value = Double.parseDouble(value);
        }catch(NumberFormatException nfe){
            this.value = AttributeMatcher.convertToDouble(value);
        }
    }
}
