/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.mleiria.preprocess;

import java.util.Arrays;
import java.util.List;
import pt.mleiria.machinelearning.statistics.StatisticalMoments;
import pt.mleiria.ml.attribute.Attribute;
import pt.mleiria.ml.core.DataType;
import pt.mleiria.ml.feature.Feature;
import pt.mleiria.ml.attribute.NumericalAttribute;
import pt.mleiria.ml.feature.DummyFeature;

/**
 *
 * @author manuel
 */
public class CurrentDataStats {

    private final Feature f;
    private List<Attribute> attList;
    private final StatisticalMoments sm;
    private double min;
    private double max;
    private double[] col;
    private DummyFeature df;

    public CurrentDataStats(Feature f) {
        this.f = f;
        sm = new StatisticalMoments();
        if (!f.getFeatureType().equals(DataType.NOMINAL)) {
            processData();
        }else{
            this.df = new DummyFeature(f);
        }
    }

    /**
     *
     * @return
     */
    public Feature getF() {
        return f;
    }

    /**
     *
     * @return
     */
    public DummyFeature getDf() {
        return df;

    }

    /**
     *
     */
    private void processData() {
        this.attList = f.getAttList();
        int N = attList.size();
        col = new double[N];
        for (int i = 0; i < N; i++) {
            final double value = ((NumericalAttribute) attList.get(i)).getValue();
            sm.accumulate(value);
            col[i] = value;
        }
        Arrays.sort(col);
        this.min = col[0];
        this.max = col[N - 1];
    }

    /**
     *
     * @param a
     * @return
     */
    private double roundOff(final double a) {
        return Math.round(a * 10000.0) / 10000.0;
    }

    /**
     *
     * @return
     */
    public double getAttMean() {
        return roundOff(sm.average());
    }

    /**
     *
     * @return
     */
    public double getAttStDev() {
        return roundOff(sm.standardDeviation());
    }

    /**
     *
     * @return
     */
    public String getAttName() {
        return f.getFeatureName();
    }

    /**
     *
     * @return
     */
    public String getAttType() {
        return f.getFeatureType().toString();
    }

    /**
     *
     * @return
     */
    public double getAttMin() {
        return min;
    }

    /**
     *
     * @return
     */
    public double getAttMax() {
        return max;
    }

    /**
     *
     * @return
     */
    public double[] getCol() {
        return col;
    }

}
