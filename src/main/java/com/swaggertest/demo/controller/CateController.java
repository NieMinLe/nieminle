package com.swaggertest.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.swaggertest.demo.domain.dto.CateDTO;
import com.swaggertest.demo.service.CateService;
import com.swaggertest.demo.webApi.ApiResult;
import com.swaggertest.demo.webApi.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@Api(value = "cateController", tags = "科目测试")
public class CateController {

    @Autowired
    private CateService cateService;


    @ApiOperation("查询科目的三级下拉")
    @GetMapping("/threeLevelPullDown")
    public ApiResult threeLevelPullDown(){
        List<CateDTO> list = cateService.threeLevelPullDown();
        return ApiResult.success(list);
    }

}





























