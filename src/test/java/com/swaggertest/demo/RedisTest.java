package com.swaggertest.demo;

import com.swaggertest.demo.utils.DateUtils;
import java.util.Date;
import org.junit.Test;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisTest {

    private RedisTemplate redisTemplate;

    @Test
    public void test1(){
        String uniqRedisKey = "test";

        Boolean absent = true;
        absent = redisTemplate.opsForValue().setIfAbsent("123123123", "1");
        if(absent) {
            redisTemplate.expireAt("123123123", DateUtils.secondAddNum(new Date(), 10));
        }
    }

    @Test
    public void test2(){
        System.out.println("组队冲杀");
    }

}
