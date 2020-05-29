package com.swaggertest.demo.exception;

public class MyException extends RuntimeException {

    private String msg;

    private String code;

    public MyException(String msg,String code){
        this.msg = msg;

        this.code = code;
    }

}
