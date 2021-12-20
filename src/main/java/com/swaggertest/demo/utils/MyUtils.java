package com.swaggertest.demo.utils;

import com.google.common.base.Strings;

public class MyUtils {

    //手机号脱敏
    public static String desensitizedPhoneNumber(String phoneNumber) {
        return Strings.isNullOrEmpty(phoneNumber) ? phoneNumber : phoneNumber.replaceAll("(\\w{3})\\w*(\\w{4})", "$1****$2");
    }
}
