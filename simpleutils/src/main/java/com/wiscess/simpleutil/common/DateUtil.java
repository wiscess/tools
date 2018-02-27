package com.wiscess.simpleutil.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by liuBo
 * 2018/2/27.
 */
public class DateUtil {

    public static int getYear(Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.YEAR);
    }

    public static int getMonth(Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MONTH)+1;
    }
    public static String getFirstDayByMonth(int year,int month){
        Calendar c = Calendar.getInstance();
        c.set(year, month-1, 1);
        return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
    }
    public static String getLastDayByMonth(int year,int month){
        Calendar c = Calendar.getInstance();
        c.set(year, month, 1);
        c.add(Calendar.DATE, -1);
        return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
    }
}
