/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.mleiria.machinelearning.interfaces;

/**
 *
 * @author manuel
 */
public interface OneVarFunction {

    /**
     * Returns the value of the function for the specified variable value.
     * @param x
     * @return a function of the form f(x)
     */
    public double value(double x);

}
