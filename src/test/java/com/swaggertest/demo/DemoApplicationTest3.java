package com.swaggertest.demo;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
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
        List<String> attach = Lists
            .newArrayList("https://res.shiguangkey.com/taoli/2020/11/8/e763bc6205/16048164600675791.jpeg,asdasdqweqwtrqwqwdwqwd");
        String attachStr = "asdasd,xcvbxcgfh,36dfgdfgh";
        List<String> f= Arrays.asList(attachStr.split(","));
        System.out.println(f);

        if (attach != null) {
            for (String url : attach) {
                attachStr += url + ",";
            }
        }
        // System.out.println(attachStr);
    }

    @Test
    public void test3() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time = sdf.parse("2020-11-07 17:10:58");
        Long interval = cn.hutool.core.date.DateUtil.between(new Date(), time, DateUnit.DAY);//不满x小时算x小时，所以23-x
        System.out.println(interval);
    }

    @Test
    public void test4(){

        List<Integer> integers = Lists.newArrayList(1,2,3,4,5);
        for (Integer integer : integers) {
            if(integer ==3){
                break;
            }
            System.out.println(integer);
        }

    }

}



















































