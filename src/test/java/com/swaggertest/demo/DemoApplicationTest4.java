package com.swaggertest.demo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Preconditions;
import com.swaggertest.demo.domain.dto.TestDto;
import com.swaggertest.demo.utils.RedisUtil;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
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

        List<Integer> te = list1.stream().sorted(Comparator.comparing(TestDto::getSage).reversed()).map(TestDto::getSage).collect(Collectors.toList());
        Set<Integer> te2 = list1.stream().sorted(Comparator.comparing(TestDto::getSage).reversed()).map(TestDto::getSage).collect(Collectors.toSet());

        List<Long> listInteger = list1.stream().map(v -> v.getSage().longValue()).collect(Collectors.toList());

        System.out.println(te);
        System.out.println(te2);
        System.out.println("listInteger=-=-="+listInteger);

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
        //课程ID
        List<Long> courseIdsLong = new ArrayList<>();
        courseIdsLong.add(null);
        List<Integer> courseIds = courseIdsLong.stream().map(Long::intValue).collect(Collectors.toList());

        System.out.println(courseIds);
    }

    @Test
    public void test12(){
        //课程ID
        List<Integer> courseIdsLong = new ArrayList<>();

        courseIdsLong.add(1);
        courseIdsLong.add(2);
        courseIdsLong.add(4);
        courseIdsLong.add(3);
        courseIdsLong.add(6);
        courseIdsLong.add(5);
        courseIdsLong.add(7);
        courseIdsLong.add(8);

        System.out.println("1=-=-=-="+courseIdsLong);
        List<Integer> test = courseIdsLong.stream().sorted(Comparator.comparing(Integer::intValue)).collect(Collectors.toList());
        System.out.println("2=-=-=-="+test);
        List<Integer> test1 = courseIdsLong.stream().sorted().collect(Collectors.toList());
        System.out.println("3=-=-=-="+test1);


        test.forEach(v ->{
            if(v == 6){
                System.out.println(courseIdsLong.indexOf(v));
            }
        });
    }

    @Test
    public void test13(){
        JSONArray test = new JSONArray();

        JSONObject te = new JSONObject();
        te.put("谭","傻🐕");
        te.put("你","吃饭了吗");

        JSONObject te2 = new JSONObject();
        te2.put("是","没吃饭我给你");
        te2.put("？","拉一点");

        test.add(te);
        test.add(te2);
        System.out.println(te);
        System.out.println(te2);
        System.out.println(test);
    }




}



















































