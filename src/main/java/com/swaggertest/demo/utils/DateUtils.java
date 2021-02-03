package com.swaggertest.demo.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.springframework.util.StringUtils;

/**
 * 日期处理util
 *
 * @author: zengkai
 * @since: 2019/9/11 13:27
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

}
