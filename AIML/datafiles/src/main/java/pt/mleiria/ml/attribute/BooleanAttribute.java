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
public class BooleanAttribute extends Attribute{
    
    private boolean value;

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }
    
    @Override
    public void setValue(String value) {
        this.value = Boolean.parseBoolean(value);
    }
    
}
