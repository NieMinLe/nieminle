package com.swaggertest.demo.utils;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author nieminle
 * @Date 2020/9/8
 * @Time 14:12
 * @Version V1.0.0
 */
public class DateUtil {

    public static void classOfSrcResult(Object source, Object target) {
        String srcValue = getClassValue(source, "Sname") == null ? "真的吗" : getClassValue(source, "Sname").toString();
        System.out.println(srcValue);
    }


    private static Object getClassValue(Object obj, String fieldName) {
        if (obj == null) {
            return null;
        } else {
            try {
                Class<? extends Object> beanClass = obj.getClass();
                Method[] ms = beanClass.getMethods();
                Method[] var4 = ms;
                int var5 = ms.length;

                for(int var6 = 0; var6 < var5; ++var6) {
                    Method m = var4[var6];
                    if (m.getName().startsWith("get")) {
                        Object objValue;
                        try {
                            objValue = m.invoke(obj);
                        } catch (Exception var10) {
                            continue;
                        }

                        if (objValue != null && (m.getName().toUpperCase().equals(fieldName.toUpperCase()) || m.getName().substring(3).toUpperCase().equals(fieldName.toUpperCase()))) {
                            return objValue;
                        }
                    }
                }

                return null;
            } catch (Exception var11) {
                return null;
            }
        }
    }

    /**
     * 获取相加的N天
     * @return Date
     */
    public static Date getYearOneAdd(Date time,int day){
        Calendar cal = Calendar.getInstance();
        cal.setTime(time);
        cal.add(Calendar.DAY_OF_YEAR,day);
        return cal.getTime();
    }

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

    public static Boolean DateCompare(Date time1, Date time2, int numYear) {
        Date time3 = add(time1, Calendar.YEAR, numYear);
        time3 = add(time3, Calendar.DATE, -1);
        if (time3.getTime() < time2.getTime()) {
            return true;
        }
        return false;
    }

    public static Date add(final Date date, final int calendarField, final int amount) {
        if (date == null) {
            return null;
        }
        final Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(calendarField, amount);
        return c.getTime();
    }

}



















