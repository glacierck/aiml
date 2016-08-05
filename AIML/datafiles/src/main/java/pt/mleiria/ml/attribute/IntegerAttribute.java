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
public class IntegerAttribute extends Attribute{
    
    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
    
    

    @Override
    public void setValue(String value) {
        this.value = Integer.parseInt(value);
    }
    
}
