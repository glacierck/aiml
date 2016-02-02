/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.mleiria.ml.vo;

import java.text.DecimalFormat;

/**
 *
 * @author manuel
 */
public class BasicStatisticsVO {

    private double average;
    private double standardDeviation;
    private double mode;
    private double median;
    private double kurtosis;
    private double skewness;
    
    private final DecimalFormat df = new DecimalFormat("#.00");

    private double roundOff(final double a){
        return Math.round(a * 10000.0) / 10000.0;
    }
    
    /**
     * @return the mode
     */
    public double getMode() {
        return mode;
    }

    /**
     * @param mode the mode to set
     */
    public void setMode(double mode) {
        this.mode = roundOff(mode);
    }

    /**
     * @return the median
     */
    public double getMedian() {
        return median;
    }

    /**
     * @param median the median to set
     */
    public void setMedian(double median) {
        this.median = roundOff(median);
    }

    /**
     * @return the kurtosis
     */
    public double getKurtosis() {
        return kurtosis;
    }

    /**
     * @param kurtosis the kurtosis to set
     */
    public void setKurtosis(double kurtosis) {
        this.kurtosis = roundOff(kurtosis);
    }

    /**
     * @return the skewness
     */
    public double getSkewness() {
        return skewness;
    }

    /**
     * @param skewness the skewness to set
     */
    public void setSkewness(double skewness) {
        this.skewness = roundOff(skewness);
    }

    /**
     * @return the average
     */
    public double getAverage() {
        return average;
    }

    /**
     * @param average the average to set
     */
    public void setAverage(double average) {
        this.average = roundOff(average);
    }

    /**
     * @return the standardDeviation
     */
    public double getStandardDeviation() {
        return standardDeviation;
    }

    /**
     * @param standardDeviation the standardDeviation to set
     */
    public void setStandardDeviation(double standardDeviation) {
        this.standardDeviation = roundOff(standardDeviation);
    }
    
    
}
