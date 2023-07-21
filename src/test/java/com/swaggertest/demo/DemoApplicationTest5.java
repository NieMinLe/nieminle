package com.swaggertest.demo;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONObjectIter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.swaggertest.demo.annotation.ColumnConf;
import com.swaggertest.demo.dao.PmsMapper;
import com.swaggertest.demo.domain.dto.*;
import com.swaggertest.demo.domain.po.CatePO;
import com.swaggertest.demo.service.PmsService;
import com.swaggertest.demo.system.enums.EnumApplyStatus;
import com.swaggertest.demo.utils.DateUtil;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
        t1.setSage(1);
        t1.setSname("我是名字1");
        list1.add(t1);

        List<TestDto> list2 = new ArrayList<>();
        TestDto t2 = new TestDto();
        t2.setNml(new Date());
        t2.setSage(1);
        t2.setSname("我是名字2");
        list2.add(t2);
        list1.addAll(list2);

        List<TestDto> fixedLinesLast = list1.stream().sorted(Comparator.comparing(TestDto::getNml)).collect(Collectors.toList());
        TestDto tt = list1.stream().min(Comparator.comparing(TestDto::getSage)).get();
        System.out.println("tt====="+tt);
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

    @Test
    public void test9(){
        //之前所有发送过的
        List<TestDto> t1 = new ArrayList<>();
        TestDto tt1 = new TestDto();
        tt1.setSname("张1");
        tt1.setCatId(1L);
        t1.add(tt1);

        TestDto tt2 = new TestDto();
        tt2.setSname("张1");
        tt2.setCatId(2L);
        t1.add(tt2);

        //集合的交集
        List<String> test = t1.stream().map(TestDto::getSname).collect(Collectors.toList()).stream().distinct().collect(Collectors.toList());

        System.out.println(""+test);
    }

    @Test
    public void test10(){
        List<Integer> nums = Lists.newArrayList(1,2,3,4,5,6,7,8);
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() ->{
           int sum = 0;
            for (Integer num : nums) {
                sum += num;
            }
            return sum;
        });
        future.thenAccept(s -> System.out.println(s));
        CompletableFuture<Integer> test = future.thenApply((s) ->{
            System.out.println("这是获取所有的结果之后的值");
            return 1;
        });


    }

    @Test
    public void test11(){

        double radLat1 = Math.toRadians(Double.parseDouble("30.267023"));
        double radLat2 =  - Math.toRadians(Double.parseDouble("113.11794000000"));
        double a = radLat1 - radLat2;
        double b = Math.toRadians(Double.parseDouble("120.107822")) - Math.toRadians(Double.parseDouble("28.20227600000"));

        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * 6371.393;
        s = Math.round(s * 10000) / 10000;
        System.out.println(s);
        DecimalFormat df = new DecimalFormat("######0.00");
        Double f = Double.parseDouble(df.format(s));
        if (f > 1000) {
            double d = f / 1000;
            System.out.println("距你" + String.format("%.1f", d) + "km");
        } else {
            System.out.println("距你" + f.intValue() + "m");
        }

    }

    @Test
    public void test13(){
        double kjingdu1 = Double.parseDouble("112.938814") * Math.PI / 180.0;                  //用户经度
        double jingdu1 = Double.parseDouble("113.117984") * Math.PI / 180.0;            //当前经度
        double b = kjingdu1 - jingdu1;

        double kweidu1 = Double.parseDouble("28.202277") * Math.PI / 180.0;                    //用户纬度
        double weidu1 = Double.parseDouble("28.228209") * Math.PI / 180.0;              //当前纬度
        double a = kweidu1 - weidu1;

        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(kweidu1) * Math.cos(weidu1) * Math.pow(Math.sin(b / 2), 2)));
        s = s * 6371000;
        s = Math.round(s * 10000) / 10000;
        DecimalFormat df = new DecimalFormat("######0.00");
        Double f = Double.parseDouble(df.format(s));
        if (f > 1000) {
            double d = f / 1000;
            System.out.println(String.format("%.1f", d) + "km");
        } else {
            System.out.println(f.intValue() + "m");
        }

    }

    @Test
    public void test1231(){
        double test = 4.28;
        String sa = "";
        if(test >= 1000){
            sa = new DecimalFormat("#.00").format(test / 1000) + "km";
        }else{
            sa = test + "m";
        }
        System.out.println(sa);
    }

    @Test
    public void test14(){
//        String path = "o2o/19d4646f773b41a6b191df6d4fc8f2fc.png";
        String path = "C:\\Users\\TMX121\\AppData\\Local\\Temp\\tomcat.3946511636785510675.8010\\work\\Tomcat\\localhost\\ROOT\\upload_a967958d_b962_4cdf_b59d_47f71c2a4059_00000000.tmp";
//        String path = "C:\\Users\\TMX121\\AppData\\Local\\Temp\\tomcat.4292607592358074842.8010\\work\\Tomcat\\localhost\\ROOT";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            FileInputStream fis = new FileInputStream(path);
            byte[] buffer = new byte[1024];
            int length = -1;
            while ((length = fis.read(buffer)) != -1) {
                md.update(buffer, 0, length);
            }
            fis.close();
            byte[] digest = md.digest();
            BigInteger bigInt = new BigInteger(1, digest);
            System.out.println(bigInt.toString(16));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test15(){
        TestDto t1 = new TestDto();
        t1.setSno(1);
        t1.setSage(1);

        if(Objects.nonNull(t1.getInta())){
            System.out.println("inta不为空");
        }else if(Objects.nonNull(t1.getSage())){
            System.out.println("年龄不为空");
        }else if(Objects.nonNull(t1.getSno())){
            System.out.println("编号不为空");
        }

        if(Objects.nonNull(t1.getSage())){
            System.out.println("年龄不为空2");
        }
        if(Objects.nonNull(t1.getSno())){
            System.out.println("编号不为空2");
        }
        if(Objects.nonNull(t1.getInta())){
            System.out.println("inta不为空2");
        }

    }

    @Test
    public void test16(){
        List<Map<String, String>> list = new ArrayList<>();
        Field[] fields = TestDto.class.getDeclaredFields();
        for (Field field : fields) {
            Map<String, String> map = new ConcurrentHashMap<>();
            ColumnConf cc = field.getAnnotation(ColumnConf.class);
            if (cc == null) {
                continue;
            }
            map.put("code", field.getName());
            map.put("title", cc.value());
            list.add(map);
        }

        System.out.println(JSONObject.toJSONString(list));

    }
    @Test
    public void test17(){
//        String json = "{Sname:John,Sage:30,Sdept:我是这个部门的}";
//        String json = "{\"Sname\":\"聂闽乐\", \"Sage\":30,\"Sdept\":\"我是这个部门的\"}";
        String json = "{\"sage\":30,\"sdept\":\"我是这个部门的\",\"sname\":\"聂闽乐\"}";

        //JSON转实体类
        TestDto person = JSON.parseObject(json,TestDto.class);
        System.out.println(person);

        //实体类转JSON
        String jsat = JSONObject.toJSONString(person);
        System.out.println(jsat);
        //修改数据

    }

    @Test
    public void test18(){

        List<TestDto> list = new ArrayList<>();
        TestDto t1 = new TestDto();
        t1.setSdept("部门1");
        t1.setSage(12);

        TestDto t2 = new TestDto();
        t2.setSdept("部门2");
        t2.setSage(14);
        list.add(t1);
        list.add(t2);

        //list实体类转JSON
        String json = JSONObject.toJSONString(list);
        System.out.println(json);

        List<TestDto> personList = new ArrayList<>();
        //JSON转list实体类
        try {
            personList = objectMapper.readValue(json, new TypeReference<List<TestDto>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(personList);
    }

    @Test
    public void test19() throws IllegalAccessException {
        CustomerCollectDetail customerCollectDetail = new CustomerCollectDetail();
        customerCollectDetail.setCustomerName("我是客户名称啊");
        customerCollectDetail.setCustomerType("我是客户类型啊");
        List<First> businessScopes = new ArrayList<>();
        First f1 = new First();
        f1.setSno(1);
        First f2 = new First();
        f2.setSno(2);
        businessScopes.add(f1);
        businessScopes.add(f2);
        customerCollectDetail.setBusinessScopes(businessScopes);

        Map<String,String> stringMap = customerCollectDetail.test(customerCollectDetail);

        CustomerCollectDetail c1 = new CustomerCollectDetail();
        c1.setCustomerName("我是老值");
        Map<String,String> stringMap2 = customerCollectDetail.test(c1);
        List<TestDto> tt = new ArrayList<>();
        stringMap.forEach((k,v) ->{
            TestDto t1 = new TestDto();
            t1.setSex(k);
            t1.setSdept(v);
            t1.setSname(stringMap2.get(k));
            if(k.equals("主营产品种类")){
                t1.setSno(1);
            }
            tt.add(t1);
        });
        System.out.println(stringMap);
        System.out.println(tt);
    }

    @Test
    public void test20(){
        BigDecimal test = new BigDecimal(23.545);
        BigDecimal test1 = new BigDecimal(12.32);
        System.out.println(test.multiply(test1));
        System.out.println(test.multiply(test1).setScale(0,RoundingMode.CEILING));
    }

    @Test
    public void test22(){
        List<TestDto> test = new ArrayList<>();
        TestDto t1 = new TestDto();
        t1.setSex("男");
        t1.setSname("我的名字");
        TestDto t2 = new TestDto();
        t2.setSex("女");
        t2.setSname("你的名字");
        test.add(t1);
        test.add(t2);
        String[] newString = new String[test.size()];
        for (int i = 0; i < test.size(); i++) {
            newString[i] = test.get(i).getSname();
        }

        // 打印数组元素
        System.out.println(newString);
        System.out.println(JSONObject.toJSONString(newString));
    }

    @Test
    public void test23(){
        List<CityDto> cityDtoList = new ArrayList<>();
        CityDto cityDto1 = new CityDto();
        cityDto1.setProvinceCode("230000");
        cityDto1.setCityCode("230000");
        cityDto1.setAreaCode("230000");
        cityDto1.setTownCode("2300001");

        CityDto cityDto2 = new CityDto();
        cityDto2.setProvinceCode("230000");
        cityDto2.setCityCode("230000");
        cityDto2.setAreaCode("230000");
        cityDto2.setTownCode("2300002");

        CityDto cityDto3 = new CityDto();
        cityDto3.setProvinceCode("230000");
        cityDto3.setCityCode("230000");
        cityDto3.setAreaCode("230000");
        cityDto3.setTownCode("2300003");

        cityDtoList.add(cityDto1);
        cityDtoList.add(cityDto2);
        cityDtoList.add(cityDto3);

        List<CityDto> newCity = new ArrayList<>();
        Boolean customerNameFlag = cityDtoList.stream().filter(v -> "1".equals(v.getProvinceCode())).findAny().isPresent();
        if(customerNameFlag){
            CityDto c1 = cityDtoList.stream().filter(v -> "1".equals(v.getProvinceCode())).collect(Collectors.toList()).get(0);
            newCity.add(c1);
        }else{
            //先把同一个省份的数据拿过来进行遍历
            Map<String,List<CityDto>> provinceMap = cityDtoList.stream().filter(e -> e.getCityCode() != null).collect(Collectors.groupingBy(CityDto::getProvinceCode));
            Set<Map.Entry<String,List<CityDto>>> provinceMapEntries = provinceMap.entrySet();
            for (Map.Entry<String, List<CityDto>> provinceEntry : provinceMapEntries) {
                //再把同一个市的数据拿过来进行遍历,key是市code,如果有市code=1,说明选择了暂不选择
                Map<String,List<CityDto>> cityMap = provinceEntry.getValue().stream().filter(e -> e.getCityCode() != null).collect(Collectors.groupingBy(CityDto::getCityCode));
                Set<Map.Entry<String,List<CityDto>>> cityMapEntries = cityMap.entrySet();
                for (Map.Entry<String, List<CityDto>> cityMapEntry : cityMapEntries) {
                    if("1".equals(cityMapEntry.getKey())){
                        //如果是暂不选择就把其他同省的数据清空,只留下暂不选择的省
                        newCity.removeIf(v -> v.getProvinceCode().equals(cityMapEntry.getValue().get(0).getProvinceCode()));
                        //把这条暂不选择的数据留下
                        newCity.add(cityMapEntry.getValue().get(0));
                        break;
                    }else{
                        //再把同一个区的数据拿过来进行遍历,key是区code,如果有区code=1,说明选择了暂不选择
                        Map<String,List<CityDto>> areaMap = cityMapEntry.getValue().stream().filter(e -> e.getAreaCode() != null).collect(Collectors.groupingBy(CityDto::getAreaCode));
                        Set<Map.Entry<String,List<CityDto>>> areaMapEntries = areaMap.entrySet();
                        for (Map.Entry<String, List<CityDto>> areaMapEntry : areaMapEntries) {
                            if("1".equals(areaMapEntry.getKey())){
                                //如果是暂不选择就把其他同市的数据清空,只留下暂不选择的市
                                newCity.removeIf(v -> v.getCityCode().equals(areaMapEntry.getValue().get(0).getCityCode()));
                                newCity.add(areaMapEntry.getValue().get(0));
                                break;
                            }else{
                                //再把同一个镇的数据拿过来进行遍历,key是镇code,如果有镇code=1,说明选择了暂不选择
                                Map<String,List<CityDto>> townMap = areaMapEntry.getValue().stream().filter(e -> e.getTownCode() != null).collect(Collectors.groupingBy(CityDto::getTownCode));
                                Set<Map.Entry<String,List<CityDto>>> townMapEntries = townMap.entrySet();
                                for (Map.Entry<String, List<CityDto>> townMapEntry : townMapEntries) {
                                    if("1".equals(townMapEntry.getKey())){
                                        //如果是暂不选择就把其他同区的数据清空,只留下暂不选择的区
                                        newCity.removeIf(v -> areaMapEntry.getValue().get(0).getAreaCode().equals(v.getAreaCode()));
                                        newCity.add(townMapEntry.getValue().get(0));
                                        break;
                                    }else{
                                        newCity.addAll(townMapEntry.getValue());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        System.out.println("最后的数据"+JSONObject.toJSONString(newCity));

    }

    @Test
    public void test24(){
        List<CityDto> lastData = new ArrayList<>();
        CityDto cityDto1 = new CityDto();
        cityDto1.setProvinceCode("230000");
        cityDto1.setCityCode("230300");
        cityDto1.setAreaCode("230305");
        cityDto1.setTownCode("1");

        CityDto cityDto2 = new CityDto();
        cityDto2.setProvinceCode("140000");
        cityDto2.setCityCode("1");

        CityDto cityDto3 = new CityDto();
        cityDto3.setProvinceCode("430000");
        cityDto3.setCityCode("430100");
        cityDto3.setAreaCode("430121");
        cityDto3.setTownCode("4310213");

        CityDto cityDto4 = new CityDto();
        cityDto4.setProvinceCode("430000");
        cityDto4.setCityCode("430100");
        cityDto4.setAreaCode("430121");
        cityDto4.setTownCode("4301211");

        CityDto cityDto5 = new CityDto();
        cityDto5.setProvinceCode("430000");
        cityDto5.setCityCode("430100");
        cityDto5.setAreaCode("430121");
        cityDto5.setTownCode("1");

        lastData.add(cityDto1);
        lastData.add(cityDto2);
        lastData.add(cityDto3);
        lastData.add(cityDto4);
        lastData.add(cityDto5);
        System.out.println("原来的值"+lastData);

        List<CityDto> lastDataList = new ArrayList<>();
        Boolean customerNameFlag = lastData.stream().filter(v -> "1".equals(v.getProvinceCode())).findAny().isPresent();
        if(customerNameFlag){
            CityDto c1 = lastDataList.stream().filter(v -> "1".equals(v.getProvinceCode())).collect(Collectors.toList()).get(0);
            lastDataList.add(c1);
        }else{
            List<String> test = new ArrayList<>();
            lastData.forEach(item ->{
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(item.getProvinceCode());
                if(StringUtils.isNotBlank(item.getCityCode()) && !"1".equals(item.getCityCode())){
                    stringBuilder.append("-").append(item.getCityCode());
                }
                if(StringUtils.isNotBlank(item.getAreaCode()) && !"1".equals(item.getAreaCode())){
                    stringBuilder.append("-").append(item.getAreaCode());
                }
                if(StringUtils.isNotBlank(item.getTownCode()) && !"1".equals(item.getTownCode())){
                    stringBuilder.append("-").append(item.getTownCode());
                }
                test.add(stringBuilder.toString());
            });
            List<String> newString = new ArrayList<>();
            test.forEach(item ->{
                //两次循环,判断字符串是否包含
                test.forEach(item2 -> {
                    //
                    if(!item2.equals(item) && item2.contains(item)){
                        newString.add(item2);
                    }
                });
            });
            System.out.println("原来的值转成String后"+JSONObject.toJSONString(test));
            System.out.println("包含其他字符串的值"+JSONObject.toJSONString(newString));

            List<String> last = test.stream().filter(v -> !newString.contains(v)).collect(Collectors.toList());
            System.out.println("原来的值去除包含其他字符串的值后"+JSONObject.toJSONString(last));

            last.forEach(item ->{
                lastDataList.add(CityDto.from(item,lastData));
            });
            System.out.println("最后返回的city"+JSONObject.toJSONString(lastDataList));
        }
    }

}



















































