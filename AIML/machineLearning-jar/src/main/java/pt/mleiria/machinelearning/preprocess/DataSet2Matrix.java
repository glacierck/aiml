/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.mleiria.machinelearning.preprocess;

import java.util.List;
import pt.mleiria.machinelearning.matrixAlgebra.Matrix;
import pt.mleiria.ml.core.DataType;
import pt.mleiria.ml.feature.Feature;
import pt.mleiria.ml.attribute.NumericalAttribute;

/**
 *
 * @author manuel
 */
public class DataSet2Matrix {
    
    public static Matrix transform(final List<Feature> featureList){
        for (Feature f : featureList){
            if (!f.getFeatureType().equals(DataType.NUMERIC)){
                throw new IllegalArgumentException("Only Numeric Data Types allowed");
            }
        }
        int rows = featureList.get(0).getAttList().size();
        int cols = featureList.size();
        double[][] components = new double[rows][cols];
        for(int i = 0; i < cols; i++){
            
            for(int j = 0; j < rows; j++){
                double value = ((NumericalAttribute)featureList.get(i).getAttList().get(j)).getValue();
                components[j][i] = value;
            }
        }
        
        return new Matrix(components);
        
    }
    
}
