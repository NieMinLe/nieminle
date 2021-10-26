package com.swaggertest.demo.utils;

import org.apache.commons.codec.binary.Hex;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

    public static String md5(String data) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return Hex.encodeHexString(md.digest(data.getBytes(StandardCharsets.UTF_8)));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 解密算法 执行一次加密，两次解密
     */
    public static String convertMD5(String inStr){

        char[] a = inStr.toCharArray();
        for (int i = 0; i < a.length; i++){
            a[i] = (char) (a[i] ^ 't');
        }
        String s = new String(a);
        return s;

    }

    public static void main(String[] args) {
        String password = "nieminle";
        String md5HexStr = md5(password);
        System.out.println("==> MD5 加密前: " + password);
        System.out.println("==> MD5 加密后: " + md5HexStr);
        System.out.println("==> MD5 解密后: " + convertMD5(convertMD5(password)));
    }

//    /***
//     * MD5加密 生成32位md5码
//     */
//    public static String string2MD5(String inStr){
//        MessageDigest md5 = null;
//        try{
//            md5 = MessageDigest.getInstance("MD5");
//        }catch (Exception e){
//            System.out.println(e.toString());
//            e.printStackTrace();
//            return "";
//        }
//        char[] charArray = inStr.toCharArray();
//        byte[] byteArray = new byte[charArray.length];
//
//        for (int i = 0; i < charArray.length; i++){
//            byteArray[i] = (byte) charArray[i];}
//        byte[] md5Bytes = md5.digest(byteArray);
//        StringBuffer hexValue = new StringBuffer();
//        for (int i = 0; i < md5Bytes.length; i++){
//            int val = ((int) md5Bytes[i]) & 0xff;
//            if (val < 16){
//                hexValue.append("0");}
//            hexValue.append(Integer.toHexString(val));
//        }
//        return hexValue.toString();
//
//    }




}



















