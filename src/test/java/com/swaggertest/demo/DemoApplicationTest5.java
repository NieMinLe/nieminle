package com.swaggertest.demo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Preconditions;
import com.swaggertest.demo.domain.dto.TestDto;
import com.swaggertest.demo.utils.DateUtil;
import com.swaggertest.demo.utils.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@ResponseBody
public class DemoApplicationTest5 {

    @Test
    public void test1() {
        BigDecimal t1 = new BigDecimal(1300);
        BigDecimal t2 = new BigDecimal(1600);

        BigDecimal item = t2.subtract(t1).divide(t1, 4, RoundingMode.HALF_UP).multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP);

        System.out.println(item);
    }

    @Test
    public void test2() {
        String quotationColumnValue = "REQUIRED";
        String supQuotationColumnValue = null;
        Assert.isTrue(!"REQUIRED".equals(quotationColumnValue) || null != supQuotationColumnValue, "error.quotation_detail.necessary_field.failed");
    }

    @Test
    public void test3(){
    }

    @Test
    public void test4() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time = sdf.parse("2022-11-20 14:13:00");

        List<TestDto> list1 = new ArrayList<>();
        TestDto t1 = new TestDto();
        t1.setNml(time);
        list1.add(t1);

        List<TestDto> list2 = new ArrayList<>();
        TestDto t2 = new TestDto();
        t2.setNml(new Date());
        list2.add(t2);
        list1.addAll(list2);

        List<TestDto> fixedLinesLast = list1.stream().sorted(Comparator.comparing(TestDto::getNml)).collect(Collectors.toList());
        System.out.println(fixedLinesLast);
        for (int i = 1 ;i < fixedLinesLast.size() ; i++) {
            //获取上一条数据的失效日期
            Date previous = fixedLinesLast.get(i-1).getNml();
            //获取失效日期+1天
            Date tomorrow = DateUtil.getYearOneAdd(previous,1);
            //获取当前数据的生效时间
            Date date = fixedLinesLast.get(i).getNml();

            if(date.compareTo(tomorrow) != 0){
                System.out.println("输入当前生效日期不等于上一条数据的失效日期+1天");
            }

            System.out.println(sdf.format(previous));
            System.out.println(sdf.format(tomorrow));
            System.out.println(sdf.format(date));
        }

    }

    @Test
    public void test5(){

        Integer test = null;
        if(("1").equals(test)){
            System.out.println("这里真的很是");
        }

    }


}



















































