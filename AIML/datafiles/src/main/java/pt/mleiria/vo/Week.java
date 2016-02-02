/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.mleiria.vo;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author manuel
 */
public class Week {
    
    private static final Map<Integer, String> weekName = new HashMap<Integer, String>();
    
    static{
        weekName.put(1, "Segunda Feira");
        weekName.put(2, "Terça Feira");
        weekName.put(3, "Quarta Feira");
        weekName.put(4, "Quinta Feira");
        weekName.put(5, "Sexta Feira");
        weekName.put(6, "Sábado");
        weekName.put(7, "Domingo");
    }
    
    public static String getWeekName(final int weekDay){
        if(weekDay == 1){
            return weekName.get(7);
        }
        
        return weekName.get(weekDay - 1);
    }
    
    
    
}
