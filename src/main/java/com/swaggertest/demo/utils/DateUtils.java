package com.swaggertest.demo.utils;

import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期处理util
 *
 * @author: nieminle
 */
public class DateUtils {

    public static final String TIME_BEGIN = " 00:00:00";
    public static final String TIME_END = " 23:59:59";
    public static final String MONTH_PATTERN = "yyyy-MM";
    public static final String TIME_PATTERN = "MM-dd HH:mm";
    public static final String DEFAULT_PATTERN = "yyyyMMdd";
    public static final String DOT_PATTERN = "yyyy.MM.dd";
    public static final String FULL_PATTERN = "yyyyMMddHHmmss";
    public static final String FULL_STANDARD_PATTERN = "yyyyMMdd HH:mm:ss";
    public static final String CHINESE_PATTERN = "yyyy-MM-dd";
    public static final String FULL_CHINESE_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String FULL_CHINESE_PATTERNT = "yyyy-MM-DD HH:MM:SS";

    /**
     * 将时间戳转换成日期
     *
     * @param millis
     * @return
     */
    public static Date formatDate(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }

    /**
     * 将时间戳转换成日期
     *
     * @param millis
     * @return
     */
    public static Date formatDateBegin(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        String beginDate = new SimpleDateFormat(CHINESE_PATTERN + TIME_BEGIN).format(calendar.getTime());
        return parseDate(beginDate, FULL_CHINESE_PATTERN);

    }

    /**
     * 将时间戳转换成日期
     *
     * @param millis
     * @return
     */
    public static Date formatDateEnd(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        String beginDate = new SimpleDateFormat(CHINESE_PATTERN + TIME_END).format(calendar.getTime());
        return parseDate(beginDate, FULL_CHINESE_PATTERN);

    }

    /**
     * 将时间戳转换成日期
     *
     * @param millis
     * @return
     */
    public static Date formatDate(long millis, String format) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }

    /**
     * 将时间转换为字符
     *
     * @param date
     * @param format
     * @return
     */
    public static String formatDate(Date date, String format) {
        if ((null == date) || (StringUtils.isEmpty(format))) {
            return null;
        }
        return new SimpleDateFormat(format).format(date);
    }

    /**
     * 将字符转换为时间
     *
     * @param sDate
     * @return
     */
    public static Date parseDate(String sDate) {
        return parseDate(sDate, FULL_CHINESE_PATTERN, null);
    }

    /**
     * 将字符转换为时间
     *
     * @param sDate
     * @param format
     * @return
     */
    public static Date parseDate(String sDate, String format) {
        return parseDate(sDate, format, null);
    }

    /**
     * 将字符转换为时间
     *
     * @param sDate
     * @param format
     * @param defaultValue
     * @return
     */
    public static Date parseDate(String sDate, String format, Date defaultValue) {
        if ((StringUtils.isEmpty(sDate)) || (StringUtils.isEmpty(format))) {
            return defaultValue;
        }
        DateFormat formatter = new SimpleDateFormat(format);
        try {
            return formatter.parse(sDate);
        } catch (ParseException e) {
        }
        return defaultValue;
    }

    /**
     * @param time 时间
     * @param num  加的数，-num就是减去
     * @return 减去相应的数量的秒的时间
     */
    public static Date secondAddNum(Date time, Integer num) {
        //SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //Date date = format.parse(time);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        calendar.add(Calendar.SECOND, num);
        Date newTime = calendar.getTime();
        return newTime;
    }

    /**
     * 获取当前日期上一季度 开始时间
     *
     * @return
     */
    public static Date getStartQuarter(Date date) {
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(date);
        startCalendar.set(Calendar.MONTH, ((int) startCalendar.get(Calendar.MONTH) / 3 - 1) * 3);
        startCalendar.set(Calendar.DAY_OF_MONTH, 1);
        setMinTime(startCalendar);
        return startCalendar.getTime();
    }

    /**
     * 获取当前日期上一季度 结束时间
     *
     * @return
     */
    public static Date getLastQuarter(Date date) {
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(date);
        endCalendar.set(Calendar.MONTH, ((int) endCalendar.get(Calendar.MONTH) / 3 - 1) * 3 + 2);
        endCalendar.set(Calendar.DAY_OF_MONTH, endCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        setMaxTime(endCalendar);

        return endCalendar.getTime();
    }
    /**
     * 最小时间
     *
     * @param calendar
     */
    private static void setMinTime(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }

    /**
     * 最大时间
     *
     * @param calendar
     */
    private static void setMaxTime(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getActualMaximum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getActualMaximum(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND, calendar.getActualMaximum(Calendar.MILLISECOND));
    }
}
