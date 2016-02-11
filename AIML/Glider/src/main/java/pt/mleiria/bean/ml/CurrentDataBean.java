/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.mleiria.bean.ml;

import java.io.IOException;
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

    public int getInstances(){
        LOG.info("Instance:" + hom);
        if (null != hom){
            return hom.getInstances();
        }else{
            return 0;
        }
    }
    
    public int getAttributes(){
        if (null != hom){
            return hom.getAttributes();
        }else{
            return 0;
        }
    }
    
    public String getFileName(){
        if(null != fileName){
            return fileName;
        }else{
            return "None";
        }
    }
    
}
