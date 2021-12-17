package com.swaggertest.demo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Preconditions;
import com.swaggertest.demo.domain.dto.TestDto;
import com.swaggertest.demo.utils.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

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
        Byte flag = 1;

        if(flag.equals(1)){
            System.out.println("进来equelas");
        }
    }

    @Test
    public void test5(){

        List<TestDto> list1 = new ArrayList<>();
        TestDto t1 = new TestDto();
        t1.setSdept("JAVA");
        t1.setSage(13);

        TestDto t2 = new TestDto();
        t2.setSdept("C++");
        t2.setSage(13);

        TestDto t4 = new TestDto();
        t4.setSdept("PHP");
        t4.setSage(14);

        TestDto t3 = new TestDto();
        t3.setSdept("GOODMAN");
        t3.setSage(14);

        list1.add(t1);
        list1.add(t2);
        list1.add(t3);
        list1.add(t4);

        List<Integer> te = list1.stream().map(TestDto::getSage).collect(Collectors.toList());
        Set<Integer> te2 = list1.stream().map(TestDto::getSage).collect(Collectors.toSet());

        System.out.println(te);
        System.out.println(te2);


    }

    @Test
    public void test6(){
        String str = null;
        String[] te = str.split(",");
        System.out.println(te);
    }

    @Test
    public void test7(){
        Map<Long,String> user = new HashMap<>();
        JSONArray staffInfos = new JSONArray();
        JSONObject staffInfo = new JSONObject();
        staffInfo.put("userId", 1);
        staffInfo.put("userNick", 2);
        staffInfo.put("account", 3);
        staffInfo.put("workWeChatNick",4);
        staffInfos.add(staffInfo);

        System.out.println(user.get(1));

    }

    @Test
    public void test8(){
        BigDecimal consultMoney = new BigDecimal(0.00);

        List<BigDecimal> bigDecimals = new ArrayList<>();
        bigDecimals.add(null);

        for (BigDecimal bigDecimal : bigDecimals) {
            System.out.println("进来循环");
            consultMoney = consultMoney.add(bigDecimal);
        }

        System.out.println(consultMoney);

    }

    @Test
    public void test9(){

        List<Integer> list = Lists.newArrayList(1,2,3,4,5,6,7,7,8,9,null);
        System.out.println(list);
        list.removeAll(Collections.singleton(null));
        System.out.println(list);

        String str = StringUtils.join(list,",");
        System.out.println(str);
    }

    @Test
    public void test10(){
        String str = "";
        String str1 = null;
        String str2 = "      ";

        System.out.println(StringUtils.isBlank(str));
        System.out.println(StringUtils.isBlank(str1));
        System.out.println(StringUtils.isBlank(str2));

    }

    @Test
    public void test11(){
        StringBuilder sqieIds = new StringBuilder();
        System.out.println(sqieIds.length());
        sqieIds.append("test");
        System.out.println(sqieIds.length());

        if(sqieIds.length() == 0){
            System.out.println("这个是空的");
        }

    }

    @Test
    public void test12(){
        String itemDesign = "E";
        if(Objects.nonNull(itemDesign) && (itemDesign.equals("E") || itemDesign.equals("D"))){
            System.out.println("输入E或者D了");
        }

    }

    @Test
    public void test13() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//格式化规则
        Date n1 = new Date();
        Date n2 = sdf.parse("2021-11-5");
        System.out.println(sdf.format(n1));
        System.out.println(sdf.format(n2));
        System.out.println(n1.compareTo(n2));


    }

    @Test
    public void test14(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //获取当年最后一天
//        Date one = DateUtil.getYearOneDay(DateUtil.getYear(new Date(),1));
//        //获取当年第一天
//        Date last = DateUtil.getYearLastDay(DateUtil.getYear(new Date(),1));
//        //获取明天的天数
//        Date tomorrow = DateUtil.getYearOneAdd(new Date(),1);

//        System.out.println(formatter.format(one));
//        System.out.println(formatter.format(last));
//        System.out.println(formatter.format(new Date()));
//        System.out.println(formatter.format(tomorrow));
    }

    @Test
    public void test15(){
        Set<Long> longSet = new HashSet<>();
        longSet.add(1L);
        longSet.add(2L);
        longSet.add(3L);

        List<Long> longList = Lists.newArrayList(longSet);

        System.out.println(longSet);
        System.out.println(longList);

    }

    @Test
    public void test16(){
        Set<String> test = new HashSet<>();

        System.out.println(test);
        System.out.println(StringUtils.join(test,","));

    }



}



















































