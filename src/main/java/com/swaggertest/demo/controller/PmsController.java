package com.swaggertest.demo.controller;

import com.swaggertest.demo.aop.MyAuth;
import com.swaggertest.demo.domain.dto.PmsDTO;
import com.swaggertest.demo.service.PmsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@Api(value = "pmsController", tags = "商品三级分类")
public class PmsController {

    @Autowired
    private PmsService pmsService;

    @ApiOperation("商品三级分类")
    @GetMapping("/pmsController")
    @MyAuth(name = "好")
    public List<PmsDTO> threeLevelPullDown(){
        List<PmsDTO> categories = pmsService.threeLevelPullDown();

        return categories.stream()
                .filter(o -> o.getParentCid() == 0)
                // 给每个一级分类加子分类
                .peek(o -> o.setLowerLevel(getChildCategoryList(o, categories)))
                // 排序
//                .sorted(Comparator.comparingInt(PmsDTO::getSort))
                // 收集
                .collect(Collectors.toList());

//        return ApiResult.success(list);
    }

    private List<PmsDTO> getChildCategoryList(PmsDTO currMenu, List<PmsDTO> categories) {
//        return categories.stream().filter(o -> o.getParentCid().equals(currMenu.getCatId()))
//                .peek(o -> o.setLowerLevel(getChildCategoryList(o, categories)))
////                .sorted(Comparator.comparingInt(PmsDTO::getSort))
//                .collect(Collectors.toList());
        return null;
    }


}





























