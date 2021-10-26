package com.swaggertest.demo.controller;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

@Service
public class Test {

    public static void main(String[] args) {
        System.out.println(getMD5("nieminle"));
        System.out.println(byteToHexString(getMD5("nieminle").getBytes()));
    }

    public static int numbera(int j){
        int count = j;

        if(j>=3){
            j=j/3;
            count += numbera(j);
        }
        return count;
    }

    @Async
    public void test (){
        try {
            TimeUnit.MILLISECONDS.sleep(3000);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("给我睡");

    }

    /**
     * md5加密
     * @param str
     * @return
     */
    public static String getMD5(String str){
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(str.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            return new BigInteger(1, md.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static final String HEX_NUMS_STR = "0123456789ABCDEF";
    public static byte[] hexStringToByte(String hex) {
        int len = (hex.length() / 2);
        byte[] result = new byte[len];
        char[] hexChars = hex.toCharArray();
        for (int i = 0; i < len; i++) {
            int pos = i * 2;
            result[i] = (byte) (HEX_NUMS_STR.indexOf(hexChars[pos]) << 4
                    | HEX_NUMS_STR.indexOf(hexChars[pos + 1]));
        }
        return result;
    }

    public static String byteToHexString(byte[] b) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = "0" + hex;
            }
            hexString.append(hex.toUpperCase());
        }
        return hexString.toString();
    }
}
