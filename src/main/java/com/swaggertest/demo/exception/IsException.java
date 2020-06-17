package com.swaggertest.demo.exception;

public class IsException extends RuntimeException {

    public int errorCode;
    public String msg;

    public IsException(int errorCode) {
        super();
        this.errorCode = errorCode;
    }

    public IsException(String msg) {
        super(msg);
    }

    public IsException(int errorCode,String msg){
        super(msg);
        this.errorCode = errorCode;
    }

}
