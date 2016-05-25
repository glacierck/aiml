/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.mleiria.ml.core;

import java.util.List;

/**
 *
 * @author manuel
 */
public abstract class GenericDataSet {
    /**
     * 
     * Each Feature has a List with Attributes
     * @return A List with all Features
     */
    public abstract List<Feature> getFeatureList();
    /**
     * 
     * @return The number of Features
     */
    public abstract int getFeatureCount();
    /**
     * 
     * @return A Stirng array with the names of the features
     */
    public abstract String[] getFeatureNames();
    /**
     * 
     * @return A DataType array with the feature types (NOMINAL, NUMERIC, ...)
     */
    public abstract DataType[] getFeatureTypes();
    /**
     * We assume that all Features have the same instances
     * @return The total rows in the data
     */
    public abstract int getInstancesCount();
    /**
     * Loads the Data
     */
    public abstract void loadData();
}
