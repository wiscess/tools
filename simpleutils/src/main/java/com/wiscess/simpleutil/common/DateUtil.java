package com.wiscess.simpleutil.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期相关的工具方法
 * Created by liuBo
 * 2018/2/27.
 */
public class DateUtil {

    /**
     * 得到当前年
     * @param date
     * @return
     */
    public static int getYear(Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.YEAR);
    }

    /**
     * 得到当前月
     * @param date
     * @return
     */
    public static int getMonth(Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MONTH)+1;
    }

    /**
     * 某年某月的第一天
     * @param year
     * @param month
     * @return
     */
    public static String getFirstDayByMonth(int year,int month){
        Calendar c = Calendar.getInstance();
        c.set(year, month-1, 1);
        return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
    }

    /**
     * 某年某月的最后一天
     * @param year
     * @param month
     * @return
     */
    public static String getLastDayByMonth(int year,int month){
        Calendar c = Calendar.getInstance();
        c.set(year, month, 1);
        c.add(Calendar.DATE, -1);
        return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
    }
}
