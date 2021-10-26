package com.swaggertest.demo;

import com.google.common.base.Preconditions;
import com.swaggertest.demo.domain.dto.TestDto;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@ResponseBody
public class DemoApplicationTests5 {

    @Test
    public void test1(){
        List<Long> test=  new ArrayList<>();

        test.add(1L);
        test.add(2L);
        test.add(3L);
        test.add(4L);

        System.out.println(test);
        String te = StringUtils.join(test,",");
        System.out.println(te);
    }

    @Test
    public void test2(){
        Map test = new HashMap();
        System.out.println(test.get("test"));
    }

    @Test
    public void test3(){
        //校验装机和备件有且只能填一个，且必须是1或0
        Integer installedFlag = 2;
        Integer partsFlag = 1;

        if(!(installedFlag.equals(1)&&partsFlag.equals(0))  && !(installedFlag.equals(0)&&partsFlag.equals(1))){
            System.out.println("装机和备件有且只能填一个，且必须是1或0");
        }else{
            System.out.println("这才对嘛");
        }

    }

    @Test
    public void test4(){
        String test = "2";
        if(Objects.isNull(test) || (!test.equals("3") && !test.equals("4"))){
            System.out.println("不是品类3和4");
        }

    }

}



















































