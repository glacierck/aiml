/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.mleiria.machinelearning.iterations;

import pt.mleiria.machinelearning.matrixAlgebra.Matrix;

/**
 *
 * @author manuel
 */
public class GradientDescent extends IteratorProcessor {

    private Matrix featuresX;
    private Matrix outputY;
    private Matrix theta;
    private double alpha;
    private final int dataSize;
    private final double[] costHistory;
    /**
     * 
     * @param featuresX
     * @param outputY
     * @param alpha
     * @param numIter 
     */
    public GradientDescent(Matrix featuresX, Matrix outputY, double alpha, final int numIter) {
        this.featuresX = featuresX;
        this.outputY = outputY;
        this.alpha = alpha;
        this.dataSize = outputY.rows();
        setMaximumIterations(numIter);
        setDesiredPrecision(0.00001);
        costHistory = new double[numIter + 1];
    }
    /**
     * 
     */
    @Override
    public void initializeIterations() {
        setFeaturesX(this.getFeaturesX().addOnes());
        setTheta(new Matrix(getFeaturesX().columns(), 1));
        computeCost();
    }
    /**
     * 
     */
    private void computeCost() {
        double coeff = 1.0 / (2.0 * dataSize);
        final Matrix a = featuresX.multiply(theta);
        final Matrix b = a.subtract(outputY);
        final Matrix c = b.transpose();
        final Matrix d = c.multiply(b);
        final Matrix result = d.multiply(coeff);
        costHistory[iterations] = result.component(0, 0);
    }

    /**
     * theta = theta - (alpha / m) * X' * (X * theta - y);
     *
     * b = X * theta 
     * c = b - y 
     * d = X' * c 
     * e = (alpha/m) * d
     *
     * @return
     */
    @Override
    public double evaluateIteration() {
        double coeff = (alpha / (double) dataSize);
        Matrix b = featuresX.multiply(theta);
        Matrix c = b.subtract(outputY);
        Matrix d = featuresX.transpose();
        Matrix e = d.multiply(c);
        Matrix f = e.multiply(coeff);
        theta = theta.subtract(f);
        computeCost();
        double precision = costHistory[iterations] - costHistory[iterations - 1];
        return Math.abs(precision);
    }

    /**
     * @return the featuresX
     */
    public Matrix getFeaturesX() {
        return featuresX;
    }

    /**
     * @param featuresX the featuresX to set
     */
    public void setFeaturesX(Matrix featuresX) {
        this.featuresX = featuresX;
    }

    /**
     * @return the outputY
     */
    public Matrix getOutputY() {
        return outputY;
    }

    /**
     * @param outputY the outputY to set
     */
    public void setOutputY(Matrix outputY) {
        this.outputY = outputY;
    }

    /**
     * @return the theta
     */
    public Matrix getTheta() {
        return theta;
    }

    /**
     * @param theta the theta to set
     */
    public void setTheta(Matrix theta) {
        this.theta = theta;
    }

    /**
     * @return the alpha
     */
    public double getAlpha() {
        return alpha;
    }

    /**
     * @param alpha the alpha to set
     */
    public void setAlpha(double alpha) {
        this.alpha = alpha;
    }

    public double[] getCostHistory() {
        return costHistory;
    }

}
