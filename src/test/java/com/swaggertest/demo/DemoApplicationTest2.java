package com.swaggertest.demo;

import com.alibaba.fastjson.JSON;
import com.swaggertest.demo.domain.dto.TestDto;
import com.swaggertest.demo.utils.DateUtil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.plaf.synth.SynthOptionPaneUI;
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
    public void test1() throws ParseException {
        String day = "2001-02-04";
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(day);
        }catch (ParseException e){
            throw e;
        }
        System.out.println(DateUtil.monthDay(date));
    }

    @Test
    public void test2() {
        List<Integer> rejectTypes = Lists.newArrayList(20, 10, 9, 8);
        String test = JSON.toJSONString(rejectTypes);

        System.out.println(rejectTypes);
        System.out.println(rejectTypes.toString());
        System.out.println(test);

    }

    @Test
    public void test3() {
        List<TestDto> collect1 = new ArrayList<>();
        TestDto t1 = new TestDto();
        t1.setSname("我是1");

        TestDto t2 = new TestDto();
        t2.setSname("我是2");
        t2.setSage(1);

        TestDto t3 = new TestDto();
        t3.setSname("我是3");
        t3.setSage(2);

        collect1.add(t1);
        collect1.add(t2);
        collect1.add(t3);
        System.out.println(collect1);
        collect1.forEach(v -> {
            if (v.getSage() == null) {
                v.setSage(0);
            }
        });

        System.out.println(collect1);
        List<TestDto> collect = collect1.stream().sorted(Comparator.comparingInt(TestDto::getSage).reversed())
            .collect(Collectors.toList());

        System.out.println(collect);


    }

    @Test
    public void test4() {
        List<Integer> test = Lists.newArrayList(1, 2, 3);
        List<Integer> test2 = Lists.newArrayList(2, 3, 4);
        test.addAll(test2);
        test = test.stream().distinct().collect(Collectors.toList());
        System.out.println(test);

    }


}



















































