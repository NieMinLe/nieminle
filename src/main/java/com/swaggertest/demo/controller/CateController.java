package com.swaggertest.demo.controller;

import com.swaggertest.demo.aop.MyAuth;
import com.swaggertest.demo.dao.TestMapper;
import com.swaggertest.demo.domain.dto.CateDTO;
import com.swaggertest.demo.domain.dto.TestDto;
import com.swaggertest.demo.service.CateService;
import com.swaggertest.demo.webApi.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@Api(value = "cateController", tags = "科目测试")
public class CateController {

    @Autowired
    private CateService cateService;

    @Autowired
    private Test test;

    @ApiOperation("查询科目的三级下拉")
    @GetMapping("/threeLevelPullDown")
    @MyAuth(name = "好")
    public ApiResult threeLevelPullDown(){
        List<CateDTO> list = cateService.threeLevelPullDown();

//        test.test();
        return ApiResult.success(list);
    }


}





























