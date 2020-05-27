package com.swaggertest.demo;

import com.alibaba.fastjson.JSON;
import com.swaggertest.demo.entity.dto.TestDto;
import com.swaggertest.demo.service.testService;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.ResponseBody;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@ResponseBody
public class DemoApplicationTests {

    @Autowired
    private testService testService;

    @Test
    public void queryTest() {
        List<TestDto> testDtoList = testService.query();
        System.out.println(JSON.toJSON(testDtoList));
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
        System.out.println("this is my test");
    }

}

























