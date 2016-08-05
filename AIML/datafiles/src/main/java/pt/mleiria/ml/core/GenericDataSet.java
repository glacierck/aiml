/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.mleiria.ml.core;

import java.util.HashMap;
import pt.mleiria.ml.feature.Feature;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import pt.mleiria.LogTypes;

/**
 *
 * @author manuel
 */
public abstract class GenericDataSet {

    protected static final Logger datafilesLog = Logger.getLogger(LogTypes.DATAFILES_LOG);
    /**
     * 
     */
    protected Map<Integer, String> errorLines = new HashMap<>();

    /**
     *
     * Each Feature has a List with Attributes
     *
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
     * @return A String array with the names of the features
     */
    public abstract String[] getFeatureNames();

    /**
     *
     * @return A DataType array with the feature types (NOMINAL, NUMERIC, ...)
     */
    public abstract DataType[] getFeatureTypes();

    /**
     * We assume that all Features have the same instances
     *
     * @return The total rows in the data
     */
    public abstract int getInstancesCount();

    /**
     * Loads the Data
     */
    public abstract void loadData();

    /**
     *
     * @return
     */
    public boolean isYNominal() {
        return getYFeature().getFeatureType().equals(DataType.NOMINAL);

    }

    /**
     *
     * @return
     */
    public boolean isYNumeric() {
        return !isYNominal();
    }

    /**
     *
     * @return
     */
    public Feature getYFeature() {
        return getFeatureList().get(getFeatureCount() - 1);
    }

    /**
     *
     * @param fname
     * @return
     */
    public Feature getFeatureFromList(final String fname) {

        for (Feature f : getFeatureList()) {
            if (f.getFeatureName().equals(fname)) {
                return f;
            }
        }
        throw new IllegalArgumentException("Feature name:" + fname + " not found.");
    }
    /**
     * Lines not processed
     * @param index
     * @param line 
     */
    public void addErrorLine(int index, String line){
        errorLines.put(index, line);
    }
}
