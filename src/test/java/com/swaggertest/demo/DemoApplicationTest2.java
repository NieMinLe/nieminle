package com.swaggertest.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.ResponseBody;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@ResponseBody
public class DemoApplicationTest2 {

    @Test
    public void queryTest1() {
        StringBuffer stringBuffer1 = new StringBuffer("123");
        StringBuffer stringBuffer2 = new StringBuffer("456");

        stringBuffer1 = stringBuffer2;
        stringBuffer2.append(stringBuffer1);
        System.out.println(stringBuffer1);
        System.out.println(stringBuffer2);

    }



}



















































