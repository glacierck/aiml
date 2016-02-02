/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.mleiria.vo;

/**
 *
 * @author manuel
 */
public class Phrase {
    
    
    
    public static String getGreeting(){
        DayOfWeek dow = new DayOfWeek();
        final int hh = Integer.parseInt(dow.getCurrentHour());
        if(hh > 6 && hh < 12){
            return "bom dia ";
        }else if(hh >= 12 && hh < 20){
            return "boa tarde ";
        }else{
            return "boa noite ";
        }
    }
    
}
