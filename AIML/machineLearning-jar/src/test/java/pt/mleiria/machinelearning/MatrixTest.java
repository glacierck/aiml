/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.mleiria.machinelearning;

import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.Assert;
import junit.framework.TestCase;
import pt.mleiria.machinelearning.matrixAlgebra.Matrix;
import pt.mleiria.machinelearning.preprocess.FeatNormMeanStdev;
import pt.mleiria.machinelearning.preprocess.FeatNormMinMax;



/**
 *
 * @author manuel
 */
public class MatrixTest extends TestCase {
    
    /**
     * Logger
     */
    private final static Logger LOGGER = Logger.getLogger(MatrixTest.class.getName());
    private Matrix a;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        final double[][] components = new double[2][2];
        components[0][0] = 1;
        components[0][1] = 2;
        components[1][0] = 3;
        components[1][1] = 4;
        a = new Matrix(components);
        LOGGER.log(Level.INFO, "\n{0}", a.toString());
    }
    
    
    public void testMatrixAddition() {
        double[][] components = new double[2][2];
        components[0][0] = 1;
        components[0][1] = 2;
        components[1][0] = 3;
        components[1][1] = 4;
        Matrix b = new Matrix(components);

        Matrix c = a.add(b);
        Assert.assertEquals(2.0, c.component(0, 0));
        Assert.assertEquals(4.0, c.component(0, 1));
        Assert.assertEquals(6.0, c.component(1, 0));
        Assert.assertEquals(8.0, c.component(1, 1));
        LOGGER.log(Level.INFO, "\n{0}", c.toString());
    }

    public void testMatrixSubtraction() {
        double[][] components = new double[2][2];
        components[0][0] = 1;
        components[0][1] = 1;
        components[1][0] = 1;
        components[1][1] = 2;
        Matrix b = new Matrix(components);
        Matrix c = a.subtract(b);
        Assert.assertEquals(0.0, c.component(0, 0));
        Assert.assertEquals(1.0, c.component(0, 1));
        Assert.assertEquals(2.0, c.component(1, 0));
        Assert.assertEquals(2.0, c.component(1, 1));
        Matrix d = new Matrix(c.subtract(b).toComponents());
        Assert.assertEquals(-1.0, d.component(0, 0));
        Assert.assertEquals(0.0, d.component(0, 1));
        Assert.assertEquals(1.0, d.component(1, 0));
        Assert.assertEquals(0.0, d.component(1, 1));

    }

    public void testMatrixMultiplyScalar() {
        double x = 5.0;
        Matrix b = a.multiply(x);
        Assert.assertEquals(5.0, b.component(0, 0));
        Assert.assertEquals(10.0, b.component(0, 1));
        Assert.assertEquals(15.0, b.component(1, 0));
        Assert.assertEquals(20.0, b.component(1, 1));
        LOGGER.log(Level.INFO, "\n{0}", b.toString());
    }

    public void testMatrixMultiply() {
        double[][] components = new double[2][2];
        components[0][0] = 5;
        components[0][1] = 6;
        components[1][0] = 7;
        components[1][1] = 8;
        Matrix b = new Matrix(components);
        Matrix c = a.multiply(b);
        Assert.assertEquals(19.0, c.component(0, 0));
        Assert.assertEquals(22.0, c.component(0, 1));
        Assert.assertEquals(43.0, c.component(1, 0));
        Assert.assertEquals(50.0, c.component(1, 1));
        LOGGER.log(Level.INFO, "\n{0}", c.toString());
    }
    public void testMatrixIdentity() {
        double[][] components = new double[2][2];
        components[0][0] = 1;
        components[0][1] = 0;
        components[1][0] = 0;
        components[1][1] = 1;
        Matrix aa = new Matrix(components);
        Matrix b = new Matrix(2);
        b.identity();
        Assert.assertEquals(aa.component(0, 0), b.component(0, 0));
        Assert.assertEquals(aa.component(0, 1), b.component(0, 1));
        Assert.assertEquals(aa.component(1, 0), b.component(1, 0));
        Assert.assertEquals(aa.component(1, 1), b.component(1, 1));
        LOGGER.log(Level.INFO, "Eye matrix: \n{0}", aa.toString());
    }
    
    public void testMatrixTranspose(){
        double[][] components = new double[4][4];
        components[0][0] = 16;
        components[0][1] = 2;
        components[0][2] = 3;
        components[0][3] = 13;
        components[1][0] = 5;
        components[1][1] = 11;
        components[1][2] = 10;
        components[1][3] = 8;
        components[2][0] = 9;
        components[2][1] = 7;
        components[2][2] = 6;
        components[2][3] = 12;
        components[3][0] = 4;
        components[3][1] = 14;
        components[3][2] = 15;
        components[3][3] = 1;
        Matrix a = new Matrix(components);
        LOGGER.log(Level.INFO, "Matrix a: \n{0}", a.toString());
        Matrix b = a.transpose();
        double[][] componentsTranspose = new double[4][4];
        componentsTranspose[0][0] = 16;
        componentsTranspose[0][1] = 5;
        componentsTranspose[0][2] = 9;
        componentsTranspose[0][3] = 4;
        componentsTranspose[1][0] = 2;
        componentsTranspose[1][1] = 11;
        componentsTranspose[1][2] = 7;
        componentsTranspose[1][3] = 14;
        componentsTranspose[2][0] = 3;
        componentsTranspose[2][1] = 10;
        componentsTranspose[2][2] = 6;
        componentsTranspose[2][3] = 15;
        componentsTranspose[3][0] = 13;
        componentsTranspose[3][1] = 8;
        componentsTranspose[3][2] = 12;
        componentsTranspose[3][3] = 1;
        Matrix c = new Matrix(componentsTranspose);
        LOGGER.log(Level.INFO, "Matrix a transposed: \n{0}", b.toString());
        Assert.assertFalse(!b.equals(c));
    }
    
    public void testFeatureNormalizationMeanStdev(){
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
        Matrix a = new Matrix(comps);
        LOGGER.log(Level.INFO, "Matrix To Normalize: \n{0}", a.toString());
        FeatNormMeanStdev fn = new FeatNormMeanStdev();
        Matrix normalizedMatrix = fn.normalize(a);
        LOGGER.log(Level.INFO, "Matrix Normalized: \n{0}", normalizedMatrix.toString());
        LOGGER.log(Level.INFO, "Mean Column 0: \n{0}", fn.getMean()[0]);
        LOGGER.log(Level.INFO, "Mean Column 1: \n{0}", fn.getMean()[1]);
        LOGGER.log(Level.INFO, "Stdev Column 0: \n{0}", fn.getStdev()[0]);
        LOGGER.log(Level.INFO, "Stdev Column 1: \n{0}", fn.getStdev()[1]);
        Assert.assertEquals(2104.0, fn.getMean()[0]);
        Assert.assertEquals(3.0, fn.getMean()[1]);
    }
    public void testFeatureNormalizationMinMax(){
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
        Matrix a = new Matrix(comps);
        LOGGER.log(Level.INFO, "Matrix To Normalize: \n{0}", a.toString());
        FeatNormMinMax fn = new FeatNormMinMax();
        Matrix normalizedMatrix = fn.normalize(a);
        LOGGER.log(Level.INFO, "Matrix Normalized: \n{0}", normalizedMatrix.toString());
        Assert.assertEquals(0.43434343434343436, normalizedMatrix.component(0, 0));
        Assert.assertEquals(0.5, normalizedMatrix.component(0, 1));
    }
    
    public void testMatrixSplit(){
        Matrix[] result = a.split(1);
        LOGGER.log(Level.INFO, "Matrix To Split: \n{0}", a.toString());
        LOGGER.log(Level.INFO, "Matrix[0]: \n{0}", result[0].toString());
        LOGGER.log(Level.INFO, "Matrix[1]: \n{0}", result[1].toString());
        Assert.assertEquals(1.0, result[0].component(0, 0));
        Assert.assertEquals(2.0, result[1].component(0, 0));
        
        
        
    }

}
