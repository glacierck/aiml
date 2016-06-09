/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.mleiria.bean.ml;

import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import pt.mleiria.EnvSettings;
import pt.mleiria.ml.core.TextDataSet;

/**
 *
 * @author manuel
 */
@ManagedBean(name = "preProcessorBean")
@SessionScoped
public class PreProcessorBean extends MachineLearningBean {

    protected final String path = EnvSettings.DATA_DIR;
    protected String fileName;

    public PreProcessorBean() {
    }

    public void loadFile(final String fileName) throws IOException {
        this.fileName = fileName;
        ds = new TextDataSet(path + fileName);
        ds.loadData();
        setDataLoaded(true);
    }

}
