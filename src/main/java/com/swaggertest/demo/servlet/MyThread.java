package com.swaggertest.demo.servlet;

//继承Thread类，重载run方法
public class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("继承Thread接口开始线程");
        try {
            //睡眠5秒
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程睡眠10秒结束");
    }
}
