package com.swaggertest.demo.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author LIZEJIN
 * @Date 2020/9/8
 * @Time 14:12
 * @Version V1.0.0
 */
public class DateUtil {

    /**
     * 获取当前年份的最后一天
     * @param year
     * @return
     */
    public static String getYearLast(int year){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//格式化规则
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        Date currYearLast = calendar.getTime();

        return sdf.format(currYearLast);
    }

    /**
     * 获取某年最后一天日期
     * @param year 年份
     * @return Date
     */
    public static Date getYearLastDay(int year){
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);

        return calendar.getTime();
    }

    /**
     * 获取某年第一天日期
     * @param year 年份
     * @return Date
     */
    public static Date getYearOneDay(int year){
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(Calendar.YEAR, year);
        int last = cal.getActualMinimum(Calendar.DAY_OF_YEAR);
        cal.set(Calendar.DAY_OF_YEAR, last);
        return cal.getTime();
    }

    /**
     * 获取某年第一天日期
     * @return Date
     */
    public static Date getYearOneAdd(Date time,int day){
        Calendar cal = Calendar.getInstance();
        cal.setTime(time);
        cal.add(Calendar.DAY_OF_YEAR,day);
        return cal.getTime();
    }



    /**
     * 获取当前时间的年份
     * @param date
     * @param year 需要加多少年
     * @return
     */
    public static int getYear(Date date,int year){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.YEAR,year);
        return c.get(Calendar.YEAR);
    }


    public static Long dealDayToDayEnd(Long date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTimeInMillis();
    }

    public static Long dayToDayStart30(Long date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date);
        calendar.set(Calendar.DAY_OF_MONTH, -29);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTimeInMillis();
    }

    //今年到今天过去了多少天
    public static int days(Date date) {
        Calendar cal = Calendar.getInstance();
        //      将日期设置给Calendat
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DATE);
        //      定义累加器储存天数
        int num = 0;
        //      遍历月份，求每个月份的天数和
        for (int i = 1; i < month; i++) {
            switch (i) {
                //          当月为大月时累加31
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    num += 31;
                    break;
                //          当月为二月时闰年累加29，平年累加28
                case 2:
                    num += (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) ? 29 : 28;
                    break;
                default:
                    num += 30;
                    break;
            }
        }
        //      加上日
        num += day;
        return num;
    }

    //这个月多少天
    public static int monthDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH)+1;
        int num = 0;

        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                num += 31;
                break;
            case 2:
                num += (year % 4 == 0 ? 29 : 28);
                break;
            default:
                num += 30;
                break;
        }

        return num;

    }


}



















