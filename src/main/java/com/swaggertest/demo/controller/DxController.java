package com.swaggertest.demo.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 测试短信DEMO
 * @Author Sans
 * @CreateTime 2019/4/2 09:39
 */
@RestController
@RequestMapping("/sms")
@Api(value = "testController", tags = "短信测试")
public class DxController {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 单发短信测试
     * @Author: Sans
     * @CreateTime: 2019/4/2 10:06
     */
    @ApiOperation("单发短信")
    @RequestMapping(value = "/sendsmsTest",method = RequestMethod.GET)
    public String sendsmsTest(){
        //单发短信API
        String url = "https://open.ucpaas.com/ol/sms/sendsms";
        JSONObject jsonObject = new JSONObject();
        //基础配置，在开发平台认证后获取
        jsonObject.put("sid","0636ff96c3eafc48b078f8d0d1bacdea");
        jsonObject.put("token","d1c7fd42660de1d01d589593e1110e9e");
        jsonObject.put("appid","2c9c14c7dc0c49938d174217ff7c4c64");
        //模板ID，在开发平台创建模板对应的模板ID
        jsonObject.put("templateid", "552687");
        //模板对应的参数，参数之间拼接用逗号作为间隔符
        jsonObject.put("param", "1234");
        //要发送的手机号
        jsonObject.put("mobile", "18347722891");
        //用户透传ID，随状态报告返回,可以不填写
        jsonObject.put("uid","");
        String json = JSONObject.toJSONString(jsonObject);
        //使用restTemplate进行访问远程服务
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> httpEntity = new HttpEntity<String>(json, headers);
        String result = restTemplate.postForObject(url, httpEntity, String.class);
        return result;
    }

    /**
     * 群发短信测试
     * @Author: Sans
     * @CreateTime: 2019/4/2 11:23
     */
    @ApiOperation("群发短信")
    @RequestMapping(value = "/sendBatchsmsTest",method = RequestMethod.GET)
    public String sendBatchsmsTest(){
        //群发短信API
        String url = "https://open.ucpaas.com/ol/sms/sendsms_batch";
        JSONObject jsonObject = new JSONObject();
        //基础配置，在开发平台认证后获取
        jsonObject.put("sid","0636ff96c3eafc48b078f8d0d1bacdea");
        jsonObject.put("token","d1c7fd42660**********593e1110e9e");
        jsonObject.put("appid","0ceaca4708****************76ec45f");
        //模板ID，在开发平台创建模板对应的模板ID
        jsonObject.put("templateid", "432116");
        //模板对应的参数，参数之间拼接用逗号作为间隔符
        jsonObject.put("param", "1315,500");
        //群发多个手机号之间要用逗号作为间隔符
        jsonObject.put("mobile", "用户的手机号A,用户的手机号B");
        //用户透传ID，随状态报告返回,可以不填写
        jsonObject.put("uid","");
        String json = JSONObject.toJSONString(jsonObject);
        //使用restTemplate进行访问远程服务
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> httpEntity = new HttpEntity<String>(json, headers);
        String result = restTemplate.postForObject(url, httpEntity, String.class);
        return result;
    }
}