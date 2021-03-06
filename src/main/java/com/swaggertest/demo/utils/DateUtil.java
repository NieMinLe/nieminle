package com.swaggertest.demo.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * @Author LIZEJIN
 * @Date 2020/9/8
 * @Time 14:12
 * @Version V1.0.0
 */
public class DateUtil {

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



















