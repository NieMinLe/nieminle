package com.swaggertest.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.ResponseBody;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@ResponseBody
public class DemoApplicationTest3 {

   @Test
    public void test1(){
       Integer test = 0;
       String str = "";
       switch (test){
           case 0:
               str += "这里是0\n";
           case 1:
               str += "这里是1\n";
               break;
           case 2:
               str += "这里是2";
               break;
           default:
               str += "这里是最后";
               break;
       }

       System.out.println(str);
   }


}



















































