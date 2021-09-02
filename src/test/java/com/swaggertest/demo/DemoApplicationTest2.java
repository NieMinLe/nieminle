package com.swaggertest.demo;

import com.alibaba.fastjson.JSONObject;
import com.swaggertest.demo.domain.dto.TestDto;
import com.swaggertest.demo.utils.DateUtil;
import com.swaggertest.demo.utils.MyUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.CollectionUtils;
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
        String str = "12312&456&456";
        System.out.println(str);
        System.out.println("分割线-----------------------------------------");
        str = str.replaceAll("&", "\n");
        System.out.println(str);
        // List<Integer> rejectTypes = Lists.newArrayList(20, 10, 9, 8);
        // String test = JSON.toJSONString(rejectTypes);
        //
        // System.out.println(rejectTypes);
        // System.out.println(rejectTypes.toString());
        // System.out.println(test);

    }

    @Test
    public void test3() {
        List<TestDto> collect1 = new ArrayList<>();
        TestDto t1 = new TestDto();
        t1.setSname("我是2");
        t1.setSage(2);
        t1.setSdept("sex1");

        TestDto t2 = new TestDto();
        t2.setSname("我是2");
        t2.setSage(2);
        t2.setSdept("sex1");

        TestDto t3 = new TestDto();
        t3.setSname("我是3");
        t3.setSage(3);
        t3.setSdept("sex3");

        collect1.add(t1);
        collect1.add(t2);
        collect1.add(t3);

        System.out.println("之前"+ JSONObject.toJSONString(collect1));
        collect1 = collect1.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(o -> o.getSage() + ";" + o.getSname() + ";" + o.getSdept()))), ArrayList::new));
        System.out.println("之后"+JSONObject.toJSONString(collect1));

//        System.out.println(collect1);
//        List<Integer> integers = collect1.stream().map(TestDto::getSage).collect(Collectors.toList());
//        Set<Integer> set = collect1.stream().map(TestDto::getSage).collect(Collectors.toSet());
//
//        System.out.println("integers=-=-=-="+integers);
//        System.out.println("set=-=-=-=-="+set);
//
//        integers.removeIf(Objects::isNull);
//        set.removeIf(Objects::isNull);
//
//        System.out.println("integers去空后=-=-=-="+integers);
//        System.out.println("set去空后=-=-=-=-="+set);

    }

    @Test
    public void test4() {
        List<Integer> test = Lists.newArrayList(1, 2, 3);
        List<Integer> test2 = Lists.newArrayList(2, 3, 4);
        test.addAll(test2);
        test = test.stream().distinct().collect(Collectors.toList());
        System.out.println(test);

    }

    @Test
    public void test5(){
        String content = "你有一条转课申请待审核，学员账号为：%s，申请转课的原订单号为：%s。请登录桃李系统，在转课管理查看并审核";

        content = String.format(content,"t0340187210","D201006763168767302701056");

        System.out.println(content);


    }

    @Test
    public void test6(){
        List<Long> list = new ArrayList<>();
        if(CollectionUtils.isEmpty(list)){
            System.out.println("里边是空的");
        }

        list.add(123L);
        list.add(456L);
        list.add(789L);

        if(list.contains(123L)){
            System.out.println("对的");
        }

    }

    @Test
    public void test7() throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = sdf.parse("2020-07-01 00:00:00");
        System.out.println(now);
    }

    @Test
    public void test8(){
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(3);
        list.add(2);

        System.out.println(list);
        list.sort(Integer::compareTo);
        System.out.println(list);

    }

    @Test
    public void test9(){
        //脱敏
        String tuo = MyUtils.desensitizedPhoneNumber("1234");
        System.out.println(tuo);
    }





}



















































