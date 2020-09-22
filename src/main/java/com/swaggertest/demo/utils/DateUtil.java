package com.swaggertest.demo.utils;

import java.util.Calendar;

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



}
