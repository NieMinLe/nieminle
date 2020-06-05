package com.swaggertest.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.swaggertest.demo.domain.dto.TestDto;
import com.swaggertest.demo.domain.po.TestPo;
import com.swaggertest.demo.service.TestService;
import com.swaggertest.demo.system.enums.EnumApplyStatus;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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


}

























