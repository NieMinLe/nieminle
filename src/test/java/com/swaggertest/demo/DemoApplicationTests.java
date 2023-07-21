package com.swaggertest.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.swaggertest.demo.domain.dto.PmsDTO;
import com.swaggertest.demo.domain.dto.TestDto;
import com.swaggertest.demo.domain.po.TestPo;
import com.swaggertest.demo.domain.po.UserPo;
import com.swaggertest.demo.service.TestService;
import com.swaggertest.demo.system.enums.EnumApplyStatus;
import com.swaggertest.demo.utils.DateUtils;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
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
        List<Long> snos = Lists.newArrayList(1L,2L,3L,4L,5L,7L,8L,9L);
        List<TestDto> testDtoList = testService.query(1,2);

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
        //useAffectedRows=true，返回受影响的行数
        //useAffectedRows=false，返回找到数据行数
        TestDto testDto = new TestDto();
        testDto.setSage(89);
        testDto.setSno(2);
        int testDtoList = testService.update(testDto);
        System.out.println(testDtoList);
    }

    @Test
    public void deleteTest() {
        int id = 7;
        int testDtoList = testService.delete(id);
        System.out.println(JSON.toJSON(testDtoList));
    }

    @Test
    public void mytest() {
        //前端传过来的值
        PmsDTO pmsDTO = new PmsDTO();
        //TestDto是我要更新表的实体类
        List<TestDto> testDto = new ArrayList<>();
        pmsDTO.getCatIds().forEach(item ->{
            TestDto testDto1 = new TestDto();
            BeanUtils.copyProperties(item,testDto1);
            testDto1.setCatId(item);
            testDto.add(testDto1);
        });
        //进行批量更新
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
    public void test5() {
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

        Boolean flag ;

        List<Integer> l1 = list1.stream().map(TestDto::getSage).collect(Collectors.toList());
        String l2 = StringUtils.join(l1,",");
        System.out.println("l1================"+l1);
        System.out.println("l2================"+l2);

        flag = list1.stream().filter(v ->  v.getSage() == 13).findAny().isPresent();
        System.out.println("findAny=-="+flag);
        flag = list1.stream().anyMatch(v -> v.getSage() == 13);
        System.out.println("anyMatch=-="+flag);

        if(list1.stream().anyMatch(v -> v.getSage() == 13)){
            System.out.println("这里面有个年龄为13的人");
        }

        if(list1.stream().noneMatch(v -> v.getSage() == 15)){
            System.out.println("这里面有一个人的年龄不是15");
        }

        if(list1.stream().findAny().get().getSage() == 15){
            System.out.println("一个意思");
        }

        Map<Integer,String> c1 = list1.stream().collect(Collectors.toMap(TestDto::getSage,TestDto::getSdept,(k,v) -> v));

        Map<Integer,TestDto> c2 = list1.stream().collect(Collectors.toMap(TestDto::getSage, Function.identity(),(k,v) -> v));

        Map<Integer,List<TestDto>> c3 = list1.stream().collect(Collectors.groupingBy(TestDto::getSage));

        System.out.println("c1=="+c1);
        System.out.println("c2=="+c2);
        System.out.println("c3=="+c3);

        Map<Integer, List<TestDto>> listMap = list1.stream().collect(Collectors.groupingBy(TestDto::getSage));
        Map<Integer, String> map = list1.stream().collect(Collectors.toMap(TestDto::getSage, TestDto::getSdept, (k, z) -> z));
        System.out.println(listMap);
        System.out.println(map);

        Integer sum = list1.stream().mapToInt(TestDto::getSage).sum();
        Double average = list1.stream().mapToDouble(TestDto::getSage).average().getAsDouble();
        Integer max = list1.stream().mapToInt(TestDto::getSage).max().getAsInt();

        //获取年龄最大的对象
        TestDto dto = list1.stream().max(Comparator.comparing(TestDto::getSage)).get();
        System.out.println("年龄最大的对象哈哈"+dto);

        list1.sort(Comparator.comparing(TestDto::getSage).reversed());

        Integer min = list1.stream().mapToInt(TestDto::getSage).min().getAsInt();
        System.out.println("sum============"+sum);
        System.out.println("average=============="+average);
        System.out.println("max=============="+max);
        System.out.println("min=============="+min);

        List<Integer> a1 = list1.stream().sorted((x,y) -> x.getSage() - y.getSage()).map(TestDto::getSage).distinct().collect(Collectors.toList());
        Integer s = a1.stream().mapToInt(Integer::intValue).sum();
        Integer m = a1.stream().mapToInt(Integer::intValue).max().getAsInt();
        Integer in = a1.stream().mapToInt(Integer::intValue).min().getAsInt();
        Double av = a1.stream().mapToDouble(Integer::intValue).average().getAsDouble();
        System.out.println(a1);
        System.out.println(s);
        System.out.println(m);
        System.out.println(in);
        System.out.println(av);

    }
    @Test
    public void test6(){
        EnumApplyStatus test = EnumApplyStatus.valueOfCode(2);
        System.out.println(test);
    }

    @Test
    public void test7(){
        Double distanceDubbo = 0.00000000000;

        if(distanceDubbo.equals(0.00000000000)){
            System.out.println("客户暂无定位信息");
        }

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
        userPo.setS1("名字1");
        userPo.setS2("密码1");

        List<UserPo> list1 = Lists.newArrayList(userPo);

        Map<Integer,List<UserPo>> map = Maps.newHashMap();
        map.put(1,list1);
        System.out.println(map);

        UserPo userPo2 = new UserPo();
        userPo2.setS1("名字2");
        userPo2.setS2("密码2");

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
        List<String> sa = Lists.newArrayList("a","b","c","d","",null);
        //list去空
        List sa1 =  sa.stream().filter(StringUtils::isNotEmpty).collect(Collectors.toList());
        List sa2 = sa.stream().filter(StringUtils::isNotBlank).collect(Collectors.toList());

        String te = StringUtils.join(sa.stream().filter(StringUtils::isNotBlank).collect(Collectors.toList()), ",");

        System.out.println(sa);
        System.out.println(sa1);
        System.out.println(sa2);
        System.out.println(te);

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

    @Test
    public void test17() throws InterruptedException {
            System.out.println("当前的时间：" + System.currentTimeMillis());
            TimeUnit.MINUTES.sleep(1);
            TimeUnit.MILLISECONDS.sleep(300);
            System.out.println("睡眠3秒的时间：" + System.currentTimeMillis());
    }


}



















































