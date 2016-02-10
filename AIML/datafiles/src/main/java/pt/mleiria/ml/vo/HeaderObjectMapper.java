/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.mleiria.ml.vo;

import java.util.List;

/**
 *
 * @author manuel
 */
public class HeaderObjectMapper {
    /**
     * O nome das colunas
     */
    private final String[] header;
    /**
     * O tipo de dados
     * Pode ser numeric ou nominal
     */
    private final String[] qualifier;
    /**
     * Os dados
     */
    private final List<GenericObj> lst;
    /**
     * A quantidade de dados
     */
    private final int instances;
    /**
     * O número de colunas
     */
    private final int attributes;

    public HeaderObjectMapper(String[] header,String[] qualifier, List<GenericObj> lst) {
        this.header = header;
        this.lst = lst;
        this.instances = lst.size();
        this.attributes = header.length;
        this.qualifier = qualifier;
    }
    /**
     * 
     * @return data size
     */
    public int getInstances() {
        return instances;
    }
    /**
     * 
     * @return number of columns
     */
    public int getAttributes() {
        return attributes;
    }
    /**
     * 
     * @return names of the columns
     */
    public String[] getHeader() {
        return header;
    }
    /**
     * 
     * @return the data
     */
    public List<GenericObj> getLst() {
        return lst;
    }
    /**
     * 
     * @return data type (nominal or numeric)
     */
    public String[] getQualifier() {
        return qualifier;
    }

    
    
    
    
    
    
    
}
