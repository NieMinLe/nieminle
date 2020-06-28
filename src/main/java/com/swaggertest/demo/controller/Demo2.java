package com.swaggertest.demo.controller;

public class Demo2 {

    public static void main(String[] args) {
        new Ticket2().start();
        new Ticket2().start();
        new Ticket2().start();
        new Ticket2().start();
    }

}

class Ticket2 extends Thread {
    private static int ticket = 100;

    @Override
    public void run() {
        while(true) {

            if(ticket <= 0) {
                break;
            }

            //Thread.sleep(10)，模拟有多段代码执行
            try {
                Thread.sleep(10);	//可能造成：线程1睡,线程2睡,线程3睡,线程4睡
            } catch (InterruptedException e) {

                e.printStackTrace();
            }
            System.out.println("线程名"+getName() + "...这是第" + ticket-- + "号票");

        }
    }
}