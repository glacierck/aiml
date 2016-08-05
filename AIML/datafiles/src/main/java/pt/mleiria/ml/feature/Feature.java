/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.mleiria.ml.feature;

import java.util.ArrayList;
import java.util.List;
import pt.mleiria.ml.attribute.Attribute;
import pt.mleiria.ml.core.DataType;

/**
 *
 * @author manuel
 */
public class Feature implements IFeature{

    private String featureName;
    private DataType featureType;
    

    private List<Attribute> attList;

    public Feature() {
        attList = new ArrayList<>();
    }

    /**
     *
     * @param att
     */
    public void addAttribute(final Attribute att) {
        this.attList.add(att);
    }

    /**
     *
     * @param attList
     */
    public void setAttList(List<Attribute> attList) {
        this.attList = attList;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Attribute> getAttList() {
        return attList;
    }

    /**
     *
     * @return
     */
    @Override
    public String getFeatureName() {
        return featureName;
    }

    /**
     *
     * @param featureName
     */
    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    /**
     *
     * @return
     */
    @Override
    public DataType getFeatureType() {
        return featureType;
    }

    /**
     *
     * @param featureType
     */
    public void setFeatureType(DataType featureType) {
        this.featureType = featureType;
    }

}
