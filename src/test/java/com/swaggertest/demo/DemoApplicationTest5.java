package com.swaggertest.demo;

import cn.hutool.json.JSONObjectIter;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.swaggertest.demo.dao.PmsMapper;
import com.swaggertest.demo.domain.dto.CmsMoreDeliveryResultInfoDTO;
import com.swaggertest.demo.domain.dto.PmsDTO;
import com.swaggertest.demo.domain.dto.TestDto;
import com.swaggertest.demo.service.PmsService;
import com.swaggertest.demo.utils.DateUtil;
import org.apache.commons.lang3.StringEscapeUtils;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@ResponseBody
public class DemoApplicationTest5 {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PmsMapper pmsService;

    @Test
    public void test1() {
        BigDecimal t1 = new BigDecimal(1300);
        BigDecimal t2 = new BigDecimal(1600);

        BigDecimal item = t2.subtract(t1).divide(t1, 4, RoundingMode.HALF_UP).multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP);

        System.out.println(item);
    }

    @Test
    public void test2() {
        String quotationColumnValue = "REQUIRED";
        String supQuotationColumnValue = null;
        Assert.isTrue(!"REQUIRED".equals(quotationColumnValue) || null != supQuotationColumnValue, "error.quotation_detail.necessary_field.failed");
    }

    @Test
    public void test3() {
        BigDecimal test = new BigDecimal(123.89);
        Long te1 = test.longValue();
        System.out.println(test);
        System.out.println(te1);
    }

    @Test
    public void test4() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time = sdf.parse("2022-11-20 14:13:00");

        List<TestDto> list1 = new ArrayList<>();
        TestDto t1 = new TestDto();
        t1.setNml(time);
        list1.add(t1);

        List<TestDto> list2 = new ArrayList<>();
        TestDto t2 = new TestDto();
        t2.setNml(new Date());
        list2.add(t2);
        list1.addAll(list2);

        List<TestDto> fixedLinesLast = list1.stream().sorted(Comparator.comparing(TestDto::getNml)).collect(Collectors.toList());
        System.out.println(fixedLinesLast);
        for (int i = 1; i < fixedLinesLast.size(); i++) {
            //获取上一条数据的失效日期
            Date previous = fixedLinesLast.get(i - 1).getNml();
            //获取失效日期+1天
            Date tomorrow = DateUtil.getYearOneAdd(previous, 1);
            //获取当前数据的生效时间
            Date date = fixedLinesLast.get(i).getNml();

            if (date.compareTo(tomorrow) != 0) {
                System.out.println("输入当前生效日期不等于上一条数据的失效日期+1天");
            }

            System.out.println(sdf.format(previous));
            System.out.println(sdf.format(tomorrow));
            System.out.println(sdf.format(date));
        }

    }

    @Test
    public void test5() {
        List<Object> mainData;
        List<Object> test = new ArrayList<>();
        test.add("test");
        mainData = test;
        System.out.println(test);
        System.out.println(mainData);
    }

   @Test
    public void test6() {

        String str = "{\"esbInfo\":{\"requestTime\":\"2021-12-07T11:09:48.456+08:00\",\"instId\":\"<N3f578fc2.N74b8a023.0.17d92628435.N7d15>\",\"returnCode\":\"E\",\"responseTime\":\"2021-12-07T11:09:48.959+08:00\",\"returnStatus\":\"E\"},\"resultInfo\":{\"xReturnCode\":\"E\",\"xResponseData\":\"{\\\"supplierSchedulings\\\":[{\\\"sourceId\\\":242,\\\"itemNumber\\\":\\\"3924568\\\"\\r\\n,\\\"vendorNumber\\\":\\\"CO00048604\\\"\\r\\n,\\\"vendorSiteCode\\\":\\\"\\\\u95F4\\\\u6750\\\"\\r\\n,\\\"processStatus\\\":\\\"E\\\"\\r\\n,\\\"processMessage\\\":\\\"\\\\u4F9B\\\\u5E94\\\\u5546( CO00048604)\\\\u65E0\\\\u6548\\\\u6216\\\\u5728\\\\u7CFB\\\\u7EDF\\\\u4E2D\\\\u4E0D\\\\u5B58\\\\u5728\\\\u3002\\\"\\r\\n}]}\"}}";
        JSONObject jsonObject = JSONObject.parseObject(str);
        System.out.println("jsonObject=-=-=-=" + jsonObject);

        String resultInfoReturn = jsonObject.getJSONObject("resultInfo").getString("xReturnCode");

        if(resultInfoReturn.equals("E")){
            String resultInfo = jsonObject.getJSONObject("resultInfo").getString("xResponseData");
            String paylod = StringEscapeUtils.unescapeJava(resultInfo);
            //json格式转成具体返回的类
            CmsMoreDeliveryResultInfoDTO responseInfoDTO = new CmsMoreDeliveryResultInfoDTO();
            try {
                responseInfoDTO = (CmsMoreDeliveryResultInfoDTO)this.objectMapper.readValue(paylod, CmsMoreDeliveryResultInfoDTO.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String string = responseInfoDTO.getSupplierSchedulings().get(0).getProcessMessage();
            System.out.println("输出最终想要的数据=-=-=" + string);
        }


    }

    @Test
    public void test7(){
        int [] a = new int[]{1,3,5,6,8};
        int b = 8;
        System.out.println("之前=-=-=" + JSONObject.toJSONString(a));
        //如果有
        for (int i=0;i < a.length; i++){
            if(a[i] == b){
                System.out.println("下标=" + i);
            }
        }

        int a2[] = new int[a.length+1];
        //如果所有数据都比b小
        int max = a[a.length-1];
        if(max < b){
            for (int i=0;i<a.length;i++){
                a2[i] = a[i];
            }
            a2[a.length] = b;
            System.out.println("下标=="+a2.length);
        }

        //如果所有数据都比b大
        int min = a[0];
        if(min > b){
            for (int i=0;i<a.length;i++){
                a2[i+1] = a[i];
            }
            a2[0] = b;
            System.out.println("下标=="+ 0);
        }

        //否则
        int z = 0;
        if(b > min && b < max){
            for (int i = 0 ; i< a.length ; i++) {
                if(a[i] >= b){
                    z = i;
                    break;
                }
            }

            int j = 0;
            for (int i = 0 ; i< a.length ; i++) {
                a2[i] = a[i];
                if(i == z){
                    j = a[i];
                    a2[z] = b;
                    a2[i+1] = j;
                    break;
                }
            }

            for (int i = z ; i< a.length ; i++) {
                a2[i+1] = a[i];
            }
        }


        //设置一个新数组
        System.out.println("输入中间的下标Z="+ z);
        System.out.println("输出新数组a2="+ JSONObject.toJSONString(a2));

    }

    @Test
    public void test8(){

        Map categoryFour = new HashMap();
        categoryFour.put("purchase_agent_id","一个");
        categoryFour.put("purchase_agent_code","二个");

        StringBuilder test1 = new StringBuilder();
        test1.append("一个");
        StringBuilder test2 = new StringBuilder();
        test2.append("二个");
        System.out.println(test1.toString());
        System.out.println(test2.toString());

        String purchaseAgentId = (String) categoryFour.get("purchase_agent_id");
        String purchaseAgentCode = (String) categoryFour.get("purchase_agent_code");
        //查询StringBuilder中是否有重复数据
        int purchaseAgentIdFlag = test1.indexOf(purchaseAgentId);
        int purchaseAgentCodeFlag = test2.indexOf(purchaseAgentCode);

        if(purchaseAgentIdFlag == -1 && purchaseAgentCodeFlag == -1){
            test1.append((String) categoryFour.get("purchase_agent_id")).append(",");
            test2.append((String) categoryFour.get("purchase_agent_code")).append(",");
        }

        System.out.println(test1.toString());
        System.out.println(test2.toString());




    }

}



















































