package com.swaggertest.demo;

import com.alibaba.fastjson.JSONObject;
import com.swaggertest.demo.service.CateService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.ResponseBody;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@ResponseBody
public class CateTest {

    @Autowired
    private CateService cateService;

    @Test
    public void queryTest1() {
        System.out.println(JSONObject.toJSONString(cateService.threeLevelPullDown()));
        //12312312312312
    }


}



















































