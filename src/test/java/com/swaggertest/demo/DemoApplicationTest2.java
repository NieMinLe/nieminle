package com.swaggertest.demo;

import com.alibaba.fastjson.JSON;
import com.swaggertest.demo.utils.DateUtil;
import java.util.List;
import org.assertj.core.util.Lists;
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

    @Test
    public void test1(){

        Long startTime = DateUtil.dayToDayStart30(System.currentTimeMillis());
        Long endTime = DateUtil.dealDayToDayEnd(System.currentTimeMillis());
        System.out.println(startTime);
        System.out.println(endTime);
    }

    @Test
    public void test2(){
        List<Integer> rejectTypes = Lists.newArrayList(20,10,9,8);
        String test = JSON.toJSONString(rejectTypes);

        System.out.println(rejectTypes);
        System.out.println(rejectTypes.toString());
        System.out.println(test);

    }




}



















































