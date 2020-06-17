package com.swaggertest.demo.servlet;

//实现Runnable接口，重载run方法
public class MyRunnable implements Runnable{
    @Override
    public void run() {
        System.out.println("实现Runnable线程开始");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程睡眠10秒结束");
    }
}
