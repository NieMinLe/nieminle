package com.swaggertest.demo;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.swaggertest.demo.domain.dto.FollowTrafficTrendDTO;
import com.swaggertest.demo.domain.dto.TestDto;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.ResponseBody;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@ResponseBody
public class DemoApplicationTest3 {

    @Test
    public void test1() {
        Integer test = 0;
        String str = "";
        switch (test) {
            case 0:
                str += "这里是0\n";
            case 1:
                str += "这里是1\n";
                break;
            case 2:
                str += "这里是2";
                break;
            default:
                str += "这里是最后";
                break;
        }

        System.out.println(str);
    }

    @Test
    public void test2() {
        List<String> attach = Lists.newArrayList("https://res.shiguangkey.com/taoli/2020/11/8/e763bc6205/16048164600675791.jpeg,asdasdqweqwtrqwqwdwqwd");
        String attachStr = "asdasd,xcvbxcgfh,36dfgdfgh";
        List<String> f = Arrays.asList(attachStr.split(","));
        System.out.println(f);

        // if (attach != null) {
        //     for (String url : attach) {
        //         attachStr += url + ",";
        //     }
        // }
        // System.out.println(attachStr);
    }

    @Test
    public void test3() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time = sdf.parse("2020-11-20 14:13:00");
        Long interval = cn.hutool.core.date.DateUtil.between(new Date(), time, DateUnit.SECOND);//不满x小时算x小时，所以23-x
        System.out.println(interval);
    }

    @Test
    public void test4() {

        List<Integer> integers = Lists.newArrayList(1, 2, 3, 4, 5);
        for (Integer integer : integers) {
            if (integer == 3) {
                break;
            }
            System.out.println(integer);
        }

    }

    //补0
    @Test
    public void test5() throws ParseException {
        int days = 7;
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(1605863519438L);
        List<FollowTrafficTrendDTO> result = new ArrayList<>();
        FollowTrafficTrendDTO dto;
        for (int i = 0; i < days; i++) {
            c.add(Calendar.DAY_OF_MONTH, i > 0 ? 1 : 0);
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH) + 1;
            int day = c.get(Calendar.DAY_OF_MONTH);

            StringBuilder sb = new StringBuilder();
            sb.append(year).append("-").append(month < 10 ? "0" + month : "" + month)
                .append("-").append(day < 10 ? "0" + day : "" + day);
            String dayStr = sb.toString();
            dto = new FollowTrafficTrendDTO();
            dto.setNum(0);
            dto.setDay(dayStr);
            result.add(dto);

        }

        System.out.println(JSONObject.toJSONString(result));


    }

    //数据
    @Test
    public void test6() {
        for (int i = 30000; i < 40000; i++) {
            System.out.println("insert into first(sname,sex,sage,sdept) values('A" + i + "','男',10,'d1" + i + "');");
        }
    }

    @Test
    public void test7() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("昨天：" + DateUtil.yesterday());
        System.out.println("今天：" + sdf.format(new Date()));
        System.out.println("这个是时间：" + DateUtil.beginOfDay(DateUtil.yesterday()));
        System.out.println("这个是时间：" + DateUtil.endOfDay(DateUtil.yesterday()));

        System.out.println("这个是时间：" + DateUtil.beginOfDay(new Date()));

        System.out.println("今天的最后一刻：" + DateUtil.endOfDay(new Date()));
        System.out.println("这个月的最后一天：" + DateUtil.endOfMonth(new Date()));
        System.out.println("这个月的最后一天：" + DateUtil.endOfMonth(new Date()));
    }

    @Test
    public void test8() {
        String dateFormat = "yyyy-MM-dd";
        Date today = DateUtil.parse(DateUtil.format(new Date(), dateFormat), dateFormat);

        Date t1 = DateUtil.beginOfDay(new Date());
        System.out.println(today);
        System.out.println(t1);

    }

    @Test
    public void test9() {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            System.out.println(random.nextInt(5) + 5);
        }
    }

    @Test
    public void test10() {
        List<String> list = Lists
            .newArrayList("123asdasd", null, "936475298734502983475029384752093847520934857203485", null, "three");
        list.removeAll(Collections.singleton(null));

        list.forEach(v -> {
            if (NumberUtils.isParsable(v)) {
                System.out.println("这个能转数字=-=" + v);
            }
        });
    }

    @Test
    public void test11() {

        Map<Long, String> map = new HashMap<>();
        map.put(123L, null);
        map.put(1234L, null);
        map.put(1235L, null);
        String te = map.get(123L);
        System.out.println(te);

    }

    @Test
    public void test12() {
        StringBuilder why = new StringBuilder();
        why.append("【退款单已确认、退款协议已盖章、当前订单无退款中状态，即可申请退款】");
        if (StringUtils.isNotBlank(why.toString())) {
            System.out.println("这个不为空哦");
        }

    }

    @Test
    public void test13() {
        List<Long> te = new ArrayList<>();
        te.add(1L);
        te.add(2L);
        te.add(3L);

        List<TestDto> te2 = new ArrayList<>();
        List<TestDto> te1 = te.stream().map(v ->{
            TestDto testDto = new TestDto();
            testDto.setSage(v.intValue());
            return testDto;
        }).collect(Collectors.toList());

        te2.addAll(te1);
        System.out.println(te2);

    }

    @Test
    public void test14(){
        List<TestDto> te = new ArrayList<>();
        TestDto t1 = new TestDto();
        t1.setSage(12);

        TestDto t2 = new TestDto();
        t2.setSage(13);

        TestDto t3 = new TestDto();
        t3.setSage(14);

        te.add(t1);
        te.add(t2);
        te.add(t3);

        Boolean flag = te.stream().anyMatch(v -> v.getSage() == 12);
        System.out.println(flag);
        System.out.println(te);

    }

    @Test
    public void test15(){

        List<Long> l1 = Lists.newArrayList(1L,2L,3L);
        List<Long> l2 = Lists.newArrayList(6L,4L,5L);
        List<Long> intersection = l1.stream().filter(item -> l2.contains(item)).collect(Collectors.toList());
        System.out.println(intersection);

        System.out.println(l1.retainAll(l2));
    }


}



















































