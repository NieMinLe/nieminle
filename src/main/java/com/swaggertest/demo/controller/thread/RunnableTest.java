package com.swaggertest.demo.controller.thread;

public class RunnableTest implements Runnable {
    @Override
    public void run() {
        System.out.println("this thread name is:"+Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        System.out.println("这个是"+Thread.currentThread().getName());
        RunnableTest r = new RunnableTest();
        Thread t = new Thread(r);
        t.start();
    }
}
