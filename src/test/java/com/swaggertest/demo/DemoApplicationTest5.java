package com.swaggertest.demo;

import com.swaggertest.demo.domain.dto.TestDto;
import com.swaggertest.demo.utils.SnowFlakeUtil;
import java.math.BigDecimal;
import java.util.Date;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import org.apache.commons.collections4.ListUtils;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.ResponseBody;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@ResponseBody
public class DemoApplicationTest5 {

    @Autowired
    private SnowFlakeUtil snowFlakeUtil;

    @Test
    public void test1() {

        boolean flag = Objects.isNull(null);
        System.out.println(flag);

    }

    @Test
    public void test2() {
        List<Long> orderId = Lists
            .newArrayList(1l, 2l, 3l, 4l, 5l, 6l, 7l, 8l, 9l, 10l, 11l, 12l, 13l, 14l, 15l, 16l, 17l, 18l, 19l, 20l);
        List<List<Long>> orderIds = ListUtils.partition(orderId, 10);
        System.out.println(orderId);
        System.out.println(orderIds);

        System.out.println(orderId.get(orderId.size() - 1));
    }

    @Test
    public void test3() {
        BigDecimal b1 = new BigDecimal(3);
        BigDecimal b2 = new BigDecimal(2);
        System.out.println(b1);
        System.out.println(b2);
        if (b1.compareTo(b2) < 0) {
            System.out.println("b1真的小于b2");
        }
    }

    @Test
    public void test4() {
        BigDecimal a = BigDecimal.ZERO;
        if (true) {
            a = new BigDecimal(1231);
        }
        System.out.println(a);
    }

    @Test
    public void test5() {

        List<TestDto> testDtos = new ArrayList();
        TestDto testDto1 = new TestDto();
        testDto1.setPrice(new BigDecimal(1));

        TestDto testDto2 = new TestDto();
        testDto2.setPrice(new BigDecimal(2));

        TestDto testDto3 = new TestDto();
        testDto3.setPrice(new BigDecimal(3));

        testDtos.add(testDto1);
        testDtos.add(testDto2);
        testDtos.add(testDto3);

        BigDecimal test = testDtos.stream().max(Comparator.comparing(TestDto::getPrice)).get().getPrice();
        System.out.println(test);

    }

    @Test
    public void test6() {
        String deptChina = "1001-2004-4563-4564";

        String[] test = deptChina.split("-");

        Long te = Long.valueOf(test[test.length - 2]);
        System.out.println(te);
    }

    @Test
    public void test7() {
        TestDto test = this.ts(23);
        System.out.println(test);
    }

    public static TestDto ts(Integer age) {
        TestDto te = new TestDto();
        te.setSage(age);
        return te;
    }

    @Test
    public void test8() {
        Long test = snowFlakeUtil.generateMessageId();
        System.out.println(test);
    }

    @Test
    public void test9() {
        System.out.println("当前的时间"+new Date());
        System.out.println("当前的时间"+new Date(System.currentTimeMillis()+1000));
    }


}



















































