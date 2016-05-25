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
public class NominalAttribute extends Attribute {

    private String value;

    /**
     *
     * @param value
     */
    @Override
    public void setValue(String value) {
        this.value = value;
    }

    /**
     *
     * @return
     */
    public String getValue() {
        return value;
    }

}
