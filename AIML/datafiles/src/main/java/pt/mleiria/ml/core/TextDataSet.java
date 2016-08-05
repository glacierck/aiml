/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.mleiria.ml.core;

import pt.mleiria.ml.feature.Feature;
import pt.mleiria.ml.attribute.AttributeClient;
import pt.mleiria.ml.attribute.AttributeFactory;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import pt.mleiria.LogTypes;
import pt.mleiria.ml.attribute.Attribute;
import pt.mleiria.ml.attribute.AttributeMatcher;
import pt.mleiria.utils.viewutils.ViewUtils;

/**
 *
 * @author manuel
 */
public class TextDataSet extends GenericDataSet {

    private List<Feature> featureList;
    private String[] featureNames;
    private DataType[] featureTypes;

    private final String separator;
    private final String pathToFile;
    private boolean isYNominal;
    private int instances;

    /**
     *
     * @param separator
     * @param pathToFile
     */
    private TextDataSet(TextDataSetBuilder builder) {
        this.separator = builder.separator;
        this.pathToFile = builder.pathToFile;
        this.featureNames = builder.featureNames;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Feature> getFeatureList() {
        return featureList;
    }

    /**
     *
     * @param featureList
     */
    public void setFeatureList(List<Feature> featureList) {
        this.featureList = featureList;
    }

    @Override
    public int getFeatureCount() {
        return featureList.size();
    }

    @Override
    public String[] getFeatureNames() {
        return featureNames;
    }

    @Override
    public boolean isYNominal() {
        return isYNominal;
    }

    @Override
    public void loadData() {
        try (BufferedReader br = new BufferedReader(new FileReader(pathToFile))) {
            String line;
            int lineCount = 0;
            instances = 0;
            if(null != featureNames && featureNames.length != 0){
                addFeatureNames();
                lineCount++;
            }
            while ((line = br.readLine()) != null) {
                if (lineCount == 0) {
                    /**
                     * Get Feature Names
                     */
                    featureNames = line.split(separator);
                    /**
                     * Initialize data structure
                     */
                    addFeatureNames();
                } else if (lineCount == 1) {
                    /**
                     * Get type of attributes
                     */
                    final String[] aux = line.split(separator);
                    featureTypes = new DataType[aux.length];
                    for (int i = 0; i < aux.length; i++) {
                        try{
                            featureTypes[i] = DataType.valueOf(aux[i].toUpperCase());
                        }catch(IllegalArgumentException e){
                            final DataType dt = new AttributeClient((new AttributeFactory())).discover(aux[i].toUpperCase());
                            featureTypes[i] = dt;
                            datafilesLog.debug("Value:["+aux[i].toUpperCase() +"] Data Type:[" + dt + "]");
                        }
                        
                        featureList.get(i).setFeatureType(featureTypes[i]);
                    }

                    if (featureTypes[featureTypes.length - 1].equals(DataType.NOMINAL)) {
                        isYNominal = true;
                    }
                } else {
                    /**
                     * Process Data
                     */
                    final String[] rawData = line.split(separator);
                    if(rawData.length != featureTypes.length){
                        datafilesLog.debug("Error Line:["+line+"]");
                    }else{
                        processData(rawData);
                    }
                    
                }
                lineCount++;
            }
            instances = lineCount - 2;
            //datafilesLog.info("File:" + pathToFile + " loaded with: " + instances + " insances.");
            datafilesLog.info(LogTypes.formatLog("File:{0} loaded with:{1} instances", new Object[]{pathToFile, instances}));
        } catch (FileNotFoundException ex) {
            datafilesLog.error("File Not Found:", ex);
        } catch (IOException ex) {
            datafilesLog.error("General IO Error:", ex);
        }

    }

    private void addFeatureNames() {
        featureList = new ArrayList<>(featureNames.length);
        int cols = featureNames.length;
        for (int i = 0; i < cols; i++) {
            final Feature f = new Feature();
            f.setFeatureName(featureNames[i]);
            featureList.add(f);
        }
    }

    private void processData(final String[] rawData) {
        final AttributeClient ac = new AttributeClient(new AttributeFactory());
        Attribute att;
        for (int i = 0; i < rawData.length; i++) {
            att = ac.getAttribute(featureTypes[i]);
            att.setValue(rawData[i]);
            featureList.get(i).addAttribute(att);
        }

    }

    @Override
    public DataType[] getFeatureTypes() {
        return featureTypes;
    }

    @Override
    public int getInstancesCount() {
        return instances;
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }

    public static class TextDataSetBuilder {

        private String separator;
        private final String pathToFile;
        private String[] featureNames;

        /**
         *          *
         * @param pathToFile
         */
        public TextDataSetBuilder(final String pathToFile) {
            this.separator = ",";
            this.pathToFile = pathToFile;
        }

        public TextDataSetBuilder separator(final String separator) {
            this.separator = separator;
            return this;
        }

        public TextDataSetBuilder featureNames(final String[] featureNames) {
            this.featureNames = featureNames;
            return this;
        }

        public TextDataSet build() {
            return new TextDataSet(this);
        }
    }

}
