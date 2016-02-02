/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.mleiria.vo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author manuel
 */
public class DayOfWeek {

    private int day;
    private Calendar calendar = Calendar.getInstance();

    public DayOfWeek() {

    }

    public String getCurrentDayOfWeek() {
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        return Week.getWeekName(day);
    }
    
    public String getCurrentDayOfWeekPlusOne() {
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        return Week.getWeekName(day + 1);
    }

    /**
     *
     * @return current hour in HH:mm:ss
     */
    public String getCurrentHourMinSec() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(new Date());
    }
    
    public String getCurrentHour(){
        final String hhmmss = getCurrentHourMinSec();
        final String hh = hhmmss.split(":")[0];
        return hh;
    }

}
