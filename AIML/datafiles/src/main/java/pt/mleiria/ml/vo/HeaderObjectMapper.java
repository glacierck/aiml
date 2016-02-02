/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.mleiria.ml.vo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author manuel
 */
public class HeaderObjectMapper {
    
    private final String[] header;
    private final List<GenericObj> lst;
    private final int instances;
    private final int attributes;

    public HeaderObjectMapper(String[] header, List<GenericObj> lst) {
        this.header = header;
        this.lst = lst;
        this.instances = lst.size();
        this.attributes = header.length;
        
    }

    public int getInstances() {
        return instances;
    }

    public int getAttributes() {
        return attributes;
    }

    public String[] getHeader() {
        return header;
    }

    public List<GenericObj> getLst() {
        return lst;
    }

    
    
    
    
    
    
}
