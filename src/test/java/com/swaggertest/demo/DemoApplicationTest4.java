package com.swaggertest.demo;

import com.swaggertest.demo.utils.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.ResponseBody;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@ResponseBody
public class DemoApplicationTest4 {

    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void test1() {
        redisUtil.setRedisBitMap();
    }

    @Test
    public void test2() {
        Boolean flag = redisUtil.getRedisBitMap(6L);
        System.out.println(flag);
    }

    @Test
    public void test3(){
        //测试代码回退
    }

}



















































