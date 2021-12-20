package com.swaggertest.demo.controller;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestNewCachedThreadPool {

    public static void main(String[] args) {
        //定义一个缓存线程池
        ExecutorService executorService1 = Executors.newCachedThreadPool();

        for (int i = 1; i <= 10; i++) {
            final int index = i;
            try {
                Thread.sleep(index * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            executorService1.execute(new Runnable() {
                @Override
                public void run() {
                    String threadName = Thread.currentThread().getName();
                    System.out.println("执行：" + index + "，线程名称：" + threadName);
                }
            });
        }
    }

}
