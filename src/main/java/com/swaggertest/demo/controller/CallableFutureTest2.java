package com.swaggertest.demo.controller;

import java.util.concurrent.*;

public class CallableFutureTest2 {

        public static void main(String[] args) {
            ExecutorService test = Executors.newCachedThreadPool();
            xianchegn1 test1 = new xianchegn1();
            xianchegn2 test2 = new xianchegn2();
            Future<Boolean> t1 = test.submit(test1);
            Future<Boolean> t2 = test.submit(test2);

            try {
                Boolean b1 = t1.get();
                Boolean b2 = t2.get();
                if(b1 && b2){
                    xianchegn3 test3 = new xianchegn3();
                    test.submit(test3);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            //关闭线程池
            test.shutdown();
        }
}

//线程1
class xianchegn1 implements Callable<Boolean>{
    @Override
    public Boolean call() throws Exception {
        try{
            System.out.println("执行线程1");
            Thread.sleep(3000);
            System.out.println("线程1执行好了");
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return true;
    }
}

//线程2
class xianchegn2 implements Callable<Boolean>{
    @Override
    public Boolean call() throws Exception {
        try{
            System.out.println("执行线程2");
            Thread.sleep(1000);
            System.out.println("线程2执行好了");
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        return true;
    }
}

//执行线程3
class xianchegn3 implements Callable<Boolean>{
    @Override
    public Boolean call() throws Exception {
        try{
            System.out.println("执行线程池3");
            Thread.sleep(5000);
            System.out.println("线程池3执行好了");
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return true;
    }
}


