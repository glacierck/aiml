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
public class LIxo {
    
    public static void main(String[] args) {
        double[][] comps = new double[5][2];
        comps[0][0] = 2104;
        comps[1][0] = 1600;
        comps[2][0] = 2400;
        comps[3][0] = 1416;
        comps[4][0] = 3000;
        comps[0][1] = 3;
        comps[1][1] = 3;
        comps[2][1] = 3;
        comps[3][1] = 2;
        comps[4][1] = 4;
        Matrix m = new Matrix(comps);
        FeatNormMinMax fn = new FeatNormMinMax();
        Matrix res = fn.normalize(m);
        System.out.println(res.toString());
        
    }
    
    public static void menormaior(double[] columns) {
        for (int i = 0; i < columns.length; i++) {
            System.out.println(columns[i]);
        }
    }
}
