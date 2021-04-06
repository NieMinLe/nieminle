package com.swaggertest.demo;

import java.util.List;
import java.util.Objects;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.ResponseBody;
import org.apache.commons.collections4.ListUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@ResponseBody
public class DemoApplicationTest5 {

    @Test
    public void test1(){

        boolean flag = Objects.isNull(null);
        System.out.println(flag);

    }

    @Test
    public void test2(){

        List<Long> orderId = Lists.newArrayList(1l,2l,3l,4l,5l,6l,7l,8l,9l,10l,11l,12l,13l,14l,15l,16l,17l,18l,19l,20l);

        List<List<Long>> orderIds = ListUtils.partition(orderId,10);
        System.out.println(orderId);
        System.out.println(orderIds);

    }


}



















































