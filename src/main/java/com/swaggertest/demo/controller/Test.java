package com.swaggertest.demo.controller;

import java.util.concurrent.TimeUnit;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class Test {

    public static void main(String[] args) {
        System.out.println(numbera(50));
    }

    public static int numbera(int j){
        int count = j;

        if(j>=3){
            j=j/3;
            count += numbera(j);
        }
        return count;
    }

    @Async
    public void test (){
        try {
            TimeUnit.MILLISECONDS.sleep(5000);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("给我睡");

    }
}
