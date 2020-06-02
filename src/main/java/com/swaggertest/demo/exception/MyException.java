package com.swaggertest.demo.exception;

public class MyException extends RuntimeException {

    private String code;

    private String msg;


    public MyException(String code,String msg){
        this.msg = msg;

        this.code = code;
    }

}
