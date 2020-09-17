package com.swaggertest.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.swaggertest.demo.domain.dto.TestDto;
import com.swaggertest.demo.domain.po.TestPo;
import com.swaggertest.demo.domain.po.UserPo;
import com.swaggertest.demo.service.TestService;
import com.swaggertest.demo.system.enums.EnumApplyStatus;
import com.swaggertest.demo.utils.DateUtils;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.ResponseBody;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@ResponseBody
public class DemoApplicationTests {

    @Autowired
    private TestService testService;

    @Test
    public void queryTest() {
        List<TestDto> testDtoList = testService.query();

        Map<Integer,List<TestDto>> test = testDtoList.stream().collect(Collectors.groupingBy(TestDto::getSage));
        System.out.println(JSONObject.toJSONString(test));
    }

    @Test
    public void queryOneTest() {
        int id =5;
        TestDto testDtoList = testService.queryOne(id);
        System.out.println(JSON.toJSON(testDtoList));
    }

    @Test
    public void queryLikeNameTest() {
        String name = "聂";
        List<TestDto> testDtoList = testService.queryLikeName(name);
        System.out.println(JSON.toJSON(testDtoList));
    }

    @Test
    public void insertTest() {
        TestDto testDto = new TestDto();
        testDto.setSname("陈氏");
        testDto.setSex("女");
        testDto.setSage(24);
        testDto.setSdept("ZONG");
        int testDtoList = testService.insert(testDto);
        System.out.println(JSON.toJSON(testDtoList));
    }

    @Test
    public void updateTest() {
        TestDto testDto = new TestDto();
        testDto.setSname("爱女");
        testDto.setSex("女");
        int testDtoList = testService.update(testDto);
        System.out.println(JSON.toJSON(testDtoList));
    }

    @Test
    public void deleteTest() {
        int id = 7;
        int testDtoList = testService.delete(id);
        System.out.println(JSON.toJSON(testDtoList));
    }

    @Test
    public void mytest() {
        TestPo testPo = new TestPo();
        TestDto testDto = new TestDto();
        testDto.setSex("男");
        testDto.setSname("男人");
        testDto.setSage(13);
        testDto.setSdept("java");
        testDto.setSno(10);

        System.out.println(testPo);
        System.out.println(testDto);

        BeanUtils.copyProperties(testDto,testPo);

        System.out.println(testPo);
        System.out.println(testDto);

    }

    @Test
    public void test3(){
        //map转list
        Map map = new HashMap();
        map.put("1","1");
        Map<String,String> map1 = new HashMap<String,String>();
        map1.put("da","da");
        map1.put("ge","ge");
        map1.put("bie","bie");

        map.put("2",map1);

        System.out.println(map);

        JSONArray jsonArray = new JSONArray();
        jsonArray.add(map);

        System.out.println(jsonArray);
        System.out.println(jsonArray.toJSONString());

    }

    @Test
    public void test4(){

        System.out.println(EnumApplyStatus.WAIT_APPROVAL);
        System.out.println(EnumApplyStatus.WAIT_APPROVAL.getDesc());
        System.out.println(EnumApplyStatus.WAIT_APPROVAL.getStatus());
        System.out.println(EnumApplyStatus.APPROVAL_NOT_PASS.getStatus().intValue());

    }

    @Test
    public void test5(){
        TestDto updateVisitTaskMessageBody = new TestDto();
        updateVisitTaskMessageBody.setSno(1);
        updateVisitTaskMessageBody.setSdept("JAVA");
        updateVisitTaskMessageBody.setSage(13);
        updateVisitTaskMessageBody.setSname("名字");
        updateVisitTaskMessageBody.setSex("女");

        String jsob = JSONObject.toJSONString(updateVisitTaskMessageBody);
        String js = JSON.toJSONString(updateVisitTaskMessageBody);


        System.out.println(jsob);

    }

    @Test
    public void test6(){
        EnumApplyStatus test = EnumApplyStatus.valueOfCode(2);
        System.out.println(test);
    }

    @Test
    public void test7(){
        Boolean b = NumberUtils.isDigits("123");
        Boolean b1 = NumberUtils.isDigits("123a");
        Boolean b2 = NumberUtils.isDigits("-123123123123");

        System.out.println(b);
        System.out.println(b1);
        System.out.println(b2);

    }

    @Test
    public void test8(){

        List<Integer> list = new ArrayList<>();

       String[] s =  StringUtils.split("-123,ab,c123,as,21312,dfs", ",");
        for (String s2: s) {
            if (StringUtils.isNumeric(s2)) {
                list.add(Integer.parseInt(s2));
            }
        }

        System.out.println(list);

    }

    @Test
    public void test9(){

        UserPo userPo = new UserPo();
        userPo.setUsername("名字1");
        userPo.setPassword("密码1");

        List<UserPo> list1 = Lists.newArrayList(userPo);

        Map<Integer,List<UserPo>> map = Maps.newHashMap();
        map.put(1,list1);
        System.out.println(map);

        UserPo userPo2 = new UserPo();
        userPo2.setUsername("名字2");
        userPo2.setPassword("密码2");

        map.get(1).add(userPo2);
        System.out.println(map);

    }

    @Test
    public void test10(){
        Long ru = 1592870449000L;
        Long now = System.currentTimeMillis();

        Long xianzai = now - ru;

        Long xiaoshi = xianzai/1000/60/60;

        if(xiaoshi.intValue()>24){
            Long tian = (xiaoshi/24);
            Long shi = xiaoshi - tian*24;
            System.out.println(tian+"天"+shi+"小时");
        }else{
            System.out.println(xiaoshi+"小时");
        }

    }

    /**
     * 循环map，entrySet,
     */
    @Test
    public void test11(){
        Map<Integer,String> map = new HashMap<>();

        map.put(1,"1");
        map.put(2,"2");
        map.put(3,"3");
        map.put(4,"4");
        map.put(5,"5");

        map.forEach((k,v) ->{
            System.out.println("k:v="+k+":"+v);
        });

        map.entrySet().forEach(v ->{
            System.out.println("k:v="+v.getKey()+":"+v.getValue()+"::::::::::::::::::::::::::"+v);
        });

        for (Map.Entry<Integer,String> v: map.entrySet()) {
            System.out.println("map=k:v"+v.getKey()+":"+v.getValue());
        }

        for (String value:map.values()) {
            System.out.println("value=="+value);
        }

    }

    @Test
    public void test12(){
        List<List<Integer>> listList = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        listList.add(list);
        System.out.println(listList);
        list.add(7);
        System.out.println(listList);


        List<Long> longList = list.stream().distinct().map(Integer::longValue).collect(Collectors.toList());
        // List<Integer> intList = longList.stream().mapToInt(Long::intValue).boxed().collect(Collectors.toList());

        System.out.println("longlista=="+JSONArray.toJSONString(longList));

        list.forEach(v ->{
            System.out.println(v);
        });

        list.stream().anyMatch(s ->{
            System.out.println("anyMatch"+s);
            return s.equals(5);
        });

        list.stream().filter(v ->{
            System.out.println("filter"+v);
            return v.equals(5);
        }).findAny();

    }

    @Test
    public void test13(){
        List<String> sa = Arrays.asList("a","b","c","d","",null);
        //list去空
        List sa1 =  sa.stream().filter(StringUtils::isNotEmpty).collect(Collectors.toList());
        List sa2 = sa.stream().filter(StringUtils::isNotBlank).collect(Collectors.toList());

        System.out.println(sa);
        System.out.println(sa1);
        System.out.println(sa2);

    }

    @Test
    public void test14(){
        Long now = System.currentTimeMillis();
        Date da = DateUtils.formatDateEnd(now);

        System.out.println(da);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String daString = simpleDateFormat.format(da);

        System.out.println(daString);


    }

    @Test
    public void test15(){

        System.out.println(Integer.MAX_VALUE*4);
        System.out.println(Integer.MIN_VALUE*4);
        String run = "areyouok.goodman";

        List list = Arrays.asList(run.split("."));

        System.out.println(list);


    }

    @Test
    public void  test16(){
        Date ds = new Date(1599645037155L);
        Date endDate = DateUtils.formatDate(1599645037155L);
        System.out.println(ds);
        System.out.println(endDate);
        System.out.println(ds.getTime());

    }



}



















































