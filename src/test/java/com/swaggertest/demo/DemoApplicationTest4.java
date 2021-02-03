package com.swaggertest.demo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Preconditions;
import com.swaggertest.demo.domain.dto.TestDto;
import com.swaggertest.demo.utils.RedisUtil;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
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


}



















































