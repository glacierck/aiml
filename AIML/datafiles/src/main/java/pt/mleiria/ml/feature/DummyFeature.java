/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.mleiria.ml.feature;

import pt.mleiria.ml.attribute.NominalAttribute;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import pt.mleiria.ml.attribute.Attribute;
import pt.mleiria.ml.core.DataType;

/**
 *
 * @author manuel
 */
public class DummyFeature extends Feature{
    
    private final Map<String, Integer[]> dummyMap;

    public DummyFeature(final Feature feature) {
        setFeatureType(DataType.NOMINAL);
        dummyMap = new HashMap<>();
        convertToDummyY(feature);
    }
    /**
     * 
     */
    private void convertToDummyY(Feature feature) {

        final List<Attribute> attList = feature.getAttList();
        int dummyValue = 1;
        /**
         * String the real name of the attribute 
         * Integer[0] the dummy value
         * Integer[1] the number of instances of this attribute
         */
        for (Attribute att : attList) {
            final String key = ((NominalAttribute) att).getValue();
            
            Integer[] value = new Integer[2];
            if (dummyMap.containsKey(key)) {
                value[0] = dummyMap.get(key)[0];
                value[1] = dummyMap.get(key)[1] + 1;
                dummyMap.put(key, value);
                ((NominalAttribute) att).setValue(value[0].toString());
            } else {
                dummyMap.put(key, new Integer[]{dummyValue, 1});
                ((NominalAttribute) att).setValue(String.valueOf(dummyValue));
                dummyValue++;
            }
        }
    }
    /**
     * 
     * @param featureName
     * @return the number of instances of this feature
     */
    public int getInstances(final String featureName){
        return dummyMap.get(featureName)[1];
    }
    /**
     * 
     * @param featureName
     * @return the dummy value associated with this feature
     */
    public int getDummyValue(final String featureName){
        return dummyMap.get(featureName)[0];
    }
    /**
     * 
     * @return a set of the attribute names
     */
    public Set<String> getAttributeNames(){
        return dummyMap.keySet();
    }
    /**
     * 
     * @return 
     */
    public Map<String, Integer[]> getDummyMap() {
        return dummyMap;
    }
    
    
}
