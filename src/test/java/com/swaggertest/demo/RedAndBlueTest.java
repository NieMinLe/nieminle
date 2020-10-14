package com.swaggertest.demo;

import com.swaggertest.demo.controller.RedAndBlue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import org.junit.Test;

public class RedAndBlueTest {

    private RedAndBlue redAndBlue;

    @Test
    public void test1(){
        for (int i = 0; i < 5; i++) {
            Random r = new Random();
            List<Integer> red = new ArrayList<>();
            while (red.size()<6){
                if(red.contains(r.nextInt(33)+1)) continue;
                red.add(r.nextInt(33)+1);
            }
            Collections.sort(red);
            Integer blue = new Random().nextInt(16)+1;
            System.out.println("红球："+red);
            System.out.println("篮球："+blue);
        }

    }


}
