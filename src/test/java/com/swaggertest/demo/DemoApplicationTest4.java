package com.swaggertest.demo;

import com.google.common.base.Preconditions;
import com.swaggertest.demo.system.enums.EnumApplyStatus;
import com.swaggertest.demo.utils.RedisUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;
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
        List<String> te = new ArrayList<>();
        Preconditions.checkArgument(!te.isEmpty(),"不能为空");
        System.out.println("不能为空啊");
    }

    @Test
    public void test4(){
        Boolean flag = EnumApplyStatus.isRefund((byte)9);
        if(flag) {
            System.out.println("确实能找到");
        }

    }

    @Test
    public void test5(){
        System.out.println("回退代码");
    }

}



















































