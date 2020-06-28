package com.swaggertest.demo.controller;

public class Demo1 {

    public static void main(String[] args) {
        new Ticket().start();
        new Ticket().start();
        new Ticket().start();
        new Ticket().start();
    }

}

class Ticket extends Thread {
    private static int ticket = 100;
    //private static Object obj = new Object();		//如果用引用数据类型成员变量当作锁对象,必须是静态的
    @Override
    public void run() {
        while(true) {
            synchronized(Ticket.class) {
                if(ticket <= 0) {
                    break;
                }

                //Thread.sleep(10)，模拟有多段代码执行
                try {
                    Thread.sleep(10);	//可能造成：线程1睡,线程2睡,线程3睡,线程4睡
                } catch (InterruptedException e) {

                    e.printStackTrace();
                }
                System.out.println(ticket);
                System.out.println(getName() + "...这是第" + ticket-- + "号票");
            }
        }
    }
}