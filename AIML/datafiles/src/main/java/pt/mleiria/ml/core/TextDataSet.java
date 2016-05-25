/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.mleiria.ml.core;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    private int instances;
    /**
     * 
     * @param separator
     * @param pathToFile 
     */
    public TextDataSet(final String separator, final String pathToFile) {
        this.separator = separator;
        this.pathToFile = pathToFile;
    }
    /**
     * Using default file separator  ,
     * @param pathToFile 
     */
    public TextDataSet(final String pathToFile) {
        this.separator = ",";
        this.pathToFile = pathToFile;
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
    public void loadData() {
        try (BufferedReader br = new BufferedReader(new FileReader(pathToFile))) {
            String line;
            int lineCount = 0;
            instances = 0;
            while ((line = br.readLine()) != null) {
                if (lineCount == 0) {
                    /**
                     * Get Feature Names
                     */
                    featureNames = line.split(separator);
                    /**
                     * Initialize data structure
                     */
                    featureList = new ArrayList<>(featureNames.length);
                    int cols = featureNames.length;
                    for (int i = 0; i < cols; i++) {
                        final Feature f = new Feature();
                        f.setFeatureName(featureNames[i]);
                        featureList.add(f);
                    }

                } else if (lineCount == 1) {
                    /**
                     * Get type of attributes
                     */
                    final String[] aux = line.split(separator);
                    featureTypes = new DataType[aux.length];
                    for (int i = 0; i < aux.length; i++) {
                        featureTypes[i] = DataType.valueOf(aux[i].toUpperCase());
                        featureList.get(i).setFeatureType(featureTypes[i]);
                    }
                } else {
                    /**
                     * Process Data
                     */
                    final String[] rawData = line.split(separator);
                    processData(rawData);
                }
                lineCount++;
            }
            instances = lineCount - 2;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TextDataSet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TextDataSet.class.getName()).log(Level.SEVERE, null, ex);
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
    
    
}
