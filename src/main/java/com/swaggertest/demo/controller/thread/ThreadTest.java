package com.swaggertest.demo.controller.thread;

public class ThreadTest extends Thread {
    @Override
    public void run() {
        System.out.println("this thread name is:"+Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        ThreadTest t = new ThreadTest();
        t.setName("myTestThread");
        t.start();
    }

}
