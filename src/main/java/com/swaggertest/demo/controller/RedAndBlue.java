package com.swaggertest.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RedAndBlue {

    public static void main(String[] args) {
        Random r = new Random();
        List<Integer> red = new ArrayList<>();
        while (red.size()<6){
            Integer no = r.nextInt(33)+1;
            if(red.contains(no)){
                continue;
            }
            red.add(no);
        }

        Integer blue = r.nextInt(16)+1;

        System.out.println("红球："+red);
        System.out.println("篮球："+blue);
    }
}
