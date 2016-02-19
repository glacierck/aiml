/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.mleiria.bean.ml;

import java.io.IOException;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author manuel
 */
@ManagedBean
@SessionScoped
public class CurrentDataBean extends PreProcessorBean{

    /**
     * Creates a new instance of CurrentDataBean
     */
    public CurrentDataBean() {
    }

    @Override
    public void loadFile(String fileName) throws IOException {
        super.loadFile(fileName); 
        setShowFiles(false);
    }

    @Override
    public void setShowFiles(boolean showFiles) {
        super.setShowFiles(showFiles);
    }
    
    /**
     * 
     * @return 
     */
    public int getInstances(){
        if (null != hom){
            return hom.getInstances();
        }else{
            return 0;
        }
    }
    /**
     * 
     * @return 
     */
    public int getAttributes(){
        if (null != hom){
            return hom.getAttributes();
        }else{
            return 0;
        }
    }
    /**
     * 
     * @return 
     */
    public String getFileName(){
        if(null != fileName){
            return fileName;
        }else{
            return "None";
        }
    }
    /**
     * 
     * @return 
     */
    public String[] getAttributesList(){
        if(null != hom){
            return hom.getHeader();
        }else{
            return new String[1];
        }
    }
    
}
