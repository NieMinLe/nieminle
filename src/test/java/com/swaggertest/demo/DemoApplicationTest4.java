package com.swaggertest.demo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.swaggertest.demo.domain.dto.TestDto;
import com.swaggertest.demo.utils.DateUtil;
import com.swaggertest.demo.utils.RedisUtil;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
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
    public void test11() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date test = sdf.parse("2021-06-11");
        System.out.println(test);
        System.out.println(new Date());
        Long day = DateUtil.dateDiff(new Date(),test );
        System.out.println("日期相对比"+day);
        if( day < 0L){
            System.out.printf("确实如此");
        }else{
            System.out.println("绝非如此");
        }
    }

    @Test
    public void test12(){
        Long test = 1L;
        Long test2 = test + 1;
        Long test3 = test + 1L;
        System.out.println(test);
        System.out.println(test2);
        System.out.println(test3);
    }

    @Test
    public void test13(){

        String test = null;
        for(int i=0;i<=10;i++){
            System.out.println(i);
            test = "这是测试数据";
        }

        System.out.println(test);

    }

    @Test
    public void test14(){
        String test = "1231";
        Integer t = Integer.valueOf(test);
    }

    @Test
    public void test15(){
        List<TestDto> list = new ArrayList<>();

        TestDto testDto1 = new TestDto();
        testDto1.setSno(1);
        testDto1.setSage(13);
        testDto1.setSname("我是13");

        TestDto testDto2 = new TestDto();
        testDto2.setSno(2);
        testDto2.setSage(13);
        testDto2.setSname("我是13");

        TestDto testDto4 = new TestDto();
        testDto2.setSno(4);
        testDto4.setSage(13);
        testDto4.setSname("我是13");

        TestDto testDto3 = new TestDto();
        testDto3.setSno(3);
        testDto3.setSage(14);
        testDto3.setSname("我是14");

        list.add(testDto1);
        list.add(testDto2);
        list.add(testDto3);
        list.add(testDto4);

//        List<TestDto> list1 = list.stream().filter(distinctByKey1(s -> s.getAge()))
//                .forEach(System.out::println);


        Map<Integer,String> map = list.stream().collect(Collectors.toMap(TestDto::getSage,TestDto::getSname,(k,v) -> v));

        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }

        list.stream().collect(Collectors.groupingBy(TestDto::getSage));
        List<TestDto> distinctClass = list.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(o -> o.getSage() + ";" + o.getSname()))), ArrayList::new));

        System.out.println(list);
        System.out.println(distinctClass);
//        System.out.println(map);

    }

    // 次方法来源于：https://blog.csdn.net/haiyoung/article/details/80934467
    static <T> Predicate<T> distinctByKey1(Function<? super T, ?> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

}



















































