package com.swaggertest.demo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Preconditions;
import com.swaggertest.demo.domain.dto.TestDto;
import com.swaggertest.demo.utils.DateUtil;
import com.swaggertest.demo.utils.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @Test
    public void test1() {
        BigDecimal t1 = new BigDecimal(1300);
        BigDecimal t2 = new BigDecimal(1600);

        BigDecimal item = t2.subtract(t1).divide(t1,4, RoundingMode.HALF_UP).multiply(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_HALF_UP);

        System.out.println(item);
    }

    @Test
    public void test2(){
        String quotationColumnValue = "REQUIRED";
        String supQuotationColumnValue = null;
        Assert.isTrue(!"REQUIRED".equals(quotationColumnValue) || null != supQuotationColumnValue, "error.quotation_detail.necessary_field.failed");
    }





}



















































