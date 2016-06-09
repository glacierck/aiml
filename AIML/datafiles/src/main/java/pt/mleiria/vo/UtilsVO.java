/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.mleiria.vo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import pt.mleiria.utils.ioutils.GenericObj;
import pt.mleiria.utils.ioutils.HeaderObjectMapper;

/**
 *
 * @author manuel
 */
public class UtilsVO {
    
    public static String yesNo(final int i){
        if(i == 0){
            return "Não";
        }else if(i == 1){
            return "Sim";
        }else{
            throw new IllegalArgumentException("Esperado 0 ou 1 mas veio:" + i);
        }
    }
    
    public static String[] putTodos(String[] s){
        String[] todos = new String[s.length+1];
        todos[0] = Constants.TODOS;
        System.arraycopy(s, 0, todos, 1, s.length);
        return todos;
    }
    /**
     * load a file into a matrix, 1.34,2.34,0.3 13.45,0.2231,1.33
     *
     * @param pathToFile
     * @param separator
     * @param hasHeader
     * @param hasQualifier
     * @return
     * @throws java.io.FileNotFoundException
     */
    public static HeaderObjectMapper loadFileToObjMapper(final String pathToFile, final String separator, final boolean hasHeader, final boolean hasQualifier) throws FileNotFoundException, IOException {

        final BufferedReader br = new BufferedReader(new FileReader(pathToFile));
        String line;
        final List<String[]> rows = new ArrayList<String[]>();
        
        while ((line = br.readLine()) != null) {
            rows.add(line.split(separator));
        }
        br.close();
        String[] header = null;
        String[] qualifier = null;
        int i = 0;
        if(hasHeader){
            header = rows.get(0);
            i++;
        }
        if (hasQualifier){
            qualifier = rows.get(1);
            i++;
        }
        List<GenericObj> lst = new ArrayList<GenericObj>(rows.size() - i);
        for(int j = i; j < rows.size(); j++){
            GenericObj go = new GenericObj(rows.get(j));
            lst.add(go);
        }
        return new HeaderObjectMapper(header, qualifier, lst);
        
        
    }
    
}
