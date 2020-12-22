package com.swaggertest.demo;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.swaggertest.demo.domain.dto.First;
import com.swaggertest.demo.domain.dto.TestDto;
import com.swaggertest.demo.service.CateService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;
import javax.jws.soap.SOAPBinding;
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
public class CateTest {

    @Autowired
    private CateService cateService;

    @Test
    public void queryTest1() {
        System.out.println(JSONObject.toJSONString(cateService.threeLevelPullDown()));
        //List<DepartmentTreeDTO> digui(List<DepartmentTreeDTO> list ){
        //         List<DepartmentTreeDTO> returnList = new ArrayList<>();
        //         for(DepartmentTreeDTO dto : list){
        //             if(dto.getState()==1){
        //                 returnList.add(dto);
        //                 List<DepartmentTreeDTO> childrenList = dto.getChildren();
        //                 if(CollectionUtils.isNotEmpty(childrenList)){
        //                     digui(childrenList);
        //                 }
        //             }
        //         }
        //         return returnList;
        //     }
    }

    @Test
    public void test1(){

        List<Long> uids = Lists.newArrayList(123L,456L);
        List<Long> uids2 = Lists.newArrayList(123L,789L) ;
        uids.removeAll(uids2);
        uids.addAll(uids2);
        System.out.println(uids);

        uids.retainAll(uids2);
        System.out.println(uids);


        boolean test = uids.stream().anyMatch(v -> uids2.contains(v));
        boolean test1 = uids2.stream().anyMatch(v -> uids.contains(v));
        System.out.println(test);
        System.out.println(test1);

    }

    @Test
    public void test2(){
        List<Long> list1 = Lists.newArrayList(1L,2L,3L);
        List<Long> list2 = Lists.newArrayList(3L,3L,4L);
        System.out.println(list2);
        list2.retainAll(list1);
        System.out.print(list2);
    }

    @Test
    public void test3(){
        List<String> strings = Lists.newArrayList("abc", "","bc","efg","abcd","","jkl");
        List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());

        System.out.println("筛选列表: " + filtered);

        String te = StringUtils.join(strings,",");
        System.out.println("简单的没去空："+te);
        String mergedString = strings.stream().filter(v -> !v.isEmpty()).collect(Collectors.joining(","));
        System.out.println("合并字符串: " + mergedString);
    }

    @Test
    public void test4(){
        List<Integer> numbers = Lists.newArrayList(3, 2, 1, 3, 10, 3, 5);
        Integer max = numbers.stream().max(Comparator.comparingInt(Integer::intValue)).get();
        Integer min = numbers.stream().min(Comparator.comparingInt(Integer::intValue)).get();
        Integer sum = numbers.stream().mapToInt(Integer::intValue).sum();
        double ave = numbers.stream().mapToInt(Integer::intValue).average().getAsDouble();
        long count = numbers.stream().filter(v ->v == 3).count();
        System.out.println(max);
        System.out.println(min);
        System.out.println(sum);
        System.out.println(ave);
        System.out.println(count);


        IntSummaryStatistics stats = numbers.stream().mapToInt((x) -> x).summaryStatistics();

        // System.out.println("列表中最大的数 : " + stats.getMax());
        // System.out.println("列表中最小的数 : " + stats.getMin());
        // System.out.println("所有数之和 : " + stats.getSum());
        // System.out.println("平均数 : " + stats.getAverage());
    }

    @Test
    public void test5(){
        List<TestDto> course = JSONObject.parseArray("[{'Sno':'19111','Sname':'楚门测试9录播','Sex':'女'}]", TestDto.class);
        System.out.println(JSONObject.toJSONString(course));
    }

}



















































