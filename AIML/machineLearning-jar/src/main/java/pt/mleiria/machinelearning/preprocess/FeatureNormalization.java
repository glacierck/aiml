/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.mleiria.machinelearning.preprocess;

import pt.mleiria.machinelearning.matrixAlgebra.Matrix;

/**
 *
 * @author manuel
 */
public interface FeatureNormalization {
    
    Matrix normalize(final Matrix matrix);
    
}
