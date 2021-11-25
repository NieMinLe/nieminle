package com.swaggertest.demo.controller;

import com.swaggertest.demo.domain.dto.CateDTO;
import com.swaggertest.demo.domain.dto.PmsCategoryDTO;
import com.swaggertest.demo.service.CateService;
import com.swaggertest.demo.webApi.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ApiResult threeLevelPullDown(){
        List<CateDTO> list = cateService.threeLevelPullDown();
//        test.test();
        return ApiResult.success(list);
    }

    @ApiOperation("插入数据到三级下拉表")
    @PostMapping("/insetPullDown")
    public ApiResult insetPullDown(@RequestBody PmsCategoryDTO cateDTO){
        cateService.insetPullDown(cateDTO);
        return ApiResult.success();
    }


}





























