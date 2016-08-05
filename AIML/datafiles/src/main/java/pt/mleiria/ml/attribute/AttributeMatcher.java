/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.mleiria.ml.attribute;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author manuel
 */
public class AttributeMatcher {

    /**
     * e.g. "2,856.2"
     */
    
    private static final Pattern rMCDUD = Pattern.compile("\"\\d+,\\d+.\\d+\"");
    private static final Pattern rMCDUD1 = Pattern.compile("\\d+,\\d+.\\d+");
    
    public static void validateRawLine(String line){
        
    }
    
    
    
    /**
     * 
     * @param value
     * @param pattern
     * @return 
     */
    public static double convertToDouble(final String value, final String pattern){
        final Pattern r = Pattern.compile(pattern);
        final Matcher m = r.matcher(value);
        if(m.find()){
            String str = m.group(0);
            str = str.replaceAll("\"", "").replaceAll(",", "");
            return Double.valueOf(str);
        }
        throw new IllegalArgumentException("Error converting:["+value+"] to Double");
    }
    /**
     * 
     * @param value
     * @return 
     */
    public static boolean isConvertableToDouble(final String value){
        final Matcher m = rMCDUD1.matcher(value);
        return m.find();
    }
    /**
     * 
     * @param value
     * @return 
     */
    public static double convertToDouble(final String value){
        final Matcher m = rMCDUD1.matcher(value);
        if(m.find()){
            String str = m.group(0);
            str = str.replaceAll(",", "");
            return Double.valueOf(str);
        }
        throw new IllegalArgumentException("Error converting:["+value+"] to Double");
    }
}
