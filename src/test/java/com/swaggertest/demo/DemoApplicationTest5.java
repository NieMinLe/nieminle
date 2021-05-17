package com.swaggertest.demo;

import com.swaggertest.demo.domain.dto.TestDto;
import com.swaggertest.demo.utils.SnowFlakeUtil;
import com.tz.edu.vip.power.stub.enums.OpenAccessChannel;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.apache.commons.collections4.ListUtils;
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
public class DemoApplicationTest5 {

    @Autowired
    private SnowFlakeUtil snowFlakeUtil;

    @Test
    public void test1() {

        boolean flag = Objects.isNull(null);
        System.out.println(flag);

    }

    @Test
    public void test2() {
        List<Long> orderId = Lists
            .newArrayList(1l, 2l, 3l, 4l, 5l, 6l, 7l, 8l, 9l, 10l, 11l, 12l, 13l, 14l, 15l, 16l, 17l, 18l, 19l, 20l);
        List<List<Long>> orderIds = ListUtils.partition(orderId, 10);
        System.out.println(orderId);
        System.out.println(orderIds);

        System.out.println(orderId.get(orderId.size() - 1));
    }

    @Test
    public void test3() {
        BigDecimal b1 = new BigDecimal(3);
        BigDecimal b2 = new BigDecimal(2);
        System.out.println(b1);
        System.out.println(b2);
        if (b1.compareTo(b2) < 0) {
            System.out.println("b1真的小于b2");
        }
    }

    @Test
    public void test4() {
        BigDecimal a = BigDecimal.ZERO;
        if (true) {
            a = new BigDecimal(1231);
        }
        System.out.println(a);
    }

    @Test
    public void test5() {

        List<TestDto> testDtos = new ArrayList();
        TestDto testDto1 = new TestDto();
        testDto1.setPrice(new BigDecimal(1));

        TestDto testDto2 = new TestDto();
        testDto2.setPrice(new BigDecimal(2));

        TestDto testDto3 = new TestDto();
        testDto3.setPrice(new BigDecimal(3));

        testDtos.add(testDto1);
        testDtos.add(testDto2);
        testDtos.add(testDto3);

        BigDecimal test = testDtos.stream().max(Comparator.comparing(TestDto::getPrice)).get().getPrice();
        System.out.println(test);

    }

    @Test
    public void test6() {
        String deptChina = "1001-2004-4563-4564";

        String[] test = deptChina.split("-");

        Long te = Long.valueOf(test[test.length - 2]);
        System.out.println(te);
    }

    @Test
    public void test7() {
        TestDto test = this.ts(23);
        System.out.println(test);
    }

    public static TestDto ts(Integer age) {
        TestDto te = new TestDto();
        te.setSage(age);
        return te;
    }

    @Test
    public void test8() {
        Long test = snowFlakeUtil.generateMessageId();
        System.out.println(test);
    }

    @Test
    public void test9() {
        System.out.println("当前的时间"+new Date());
        System.out.println("当前的时间"+new Date(System.currentTimeMillis()+1000));
    }

    @Test
    public void test10(){
        Long good = 12312313L;
        Integer test1 = 2;
        if(test1 != null && test1.equals(OpenAccessChannel.PURCHASE_AND_LAUNCH.code())){
            good = null;
        }

        String nickName = "";
        if(good == null){
            nickName = "系统";
        }else if(good.equals(1231231L)){
            nickName = "学员";
        }

        System.out.println(good);
        System.out.println(nickName);
    }

    @Test
    public void test11() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Long date = System.currentTimeMillis();
        List<TestDto> test = new ArrayList<>();
        TestDto t1 = new TestDto();
        t1.setStartDate(sdf.parse("2021-04-23 19:59:00"));
        t1.setEndDate(sdf.parse("2021-04-23 22:54:00"));

        TestDto t2 = new TestDto();
        t2.setStartDate(sdf.parse("2021-05-14 11:35:00"));
        t2.setEndDate(sdf.parse("2021-05-14 12:00:00"));

        test.add(t1);
        test.add(t2);

        List<Date> wt = test.stream().filter(v -> v.getEndDate().getTime() < date).map(TestDto::getEndDate).collect(
            Collectors.toList()); //取出已结束的章节数

        Integer chapterStatus = 0;
        for (TestDto v : test) {
            //如果当前时间在直播中的话
            if(date > v.getStartDate().getTime() && date < v.getEndDate().getTime()){
                chapterStatus =1 ; //直播中
            }else if(wt.size() == test.size()){
                chapterStatus = 3; //如果没有要上课的章节的话，待更新
            }else{
                chapterStatus = 2; //其余情况是待直播
            }
        }

        System.out.println(test.size()); //总章节数
        System.out.println(wt.size());   //已结束章节数
        System.out.println(chapterStatus); //直播状态

    }

    @Test
    public void test12(){

        List<TestDto> test = new ArrayList<>();
        List<Integer> integers = test.stream().filter(v -> v.getSage() != 0).map(TestDto::getSage).collect(Collectors.toList());
        System.out.println(integers);
    }




}



















































