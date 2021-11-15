package com.swaggertest.demo.controller;

import com.swaggertest.demo.aop.MyAuth;
import com.swaggertest.demo.domain.dto.PmsDTO;
import com.swaggertest.demo.service.PmsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
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
    public List<PmsDTO> threeLevelPullDown(PmsDTO pmsDTO){
        List<PmsDTO> categories = pmsService.threeLevelPullDown(pmsDTO);

        Integer max = categories.stream().mapToInt(PmsDTO::getCatLevel).min().getAsInt();

        Long test = categories.stream().min(Comparator.comparing(PmsDTO::getCatLevel)).get().getCatLevel().longValue();
        Integer sum = categories.stream().mapToInt(PmsDTO::getCatLevel).sum();
        Integer maxa = categories.stream().max(Comparator.comparing(PmsDTO::getCatLevel)).get().getCatLevel();

        //利用stream流中的函数获取相关数值信息
        IntSummaryStatistics summaryStatistics = categories.stream().mapToInt(PmsDTO::getCatLevel).summaryStatistics();
        System.out.println("summaryStatistics函数获取数量:"+summaryStatistics.getCount());
        System.out.println("summaryStatistics函数获取平均值:"+summaryStatistics.getAverage());
        System.out.println("summaryStatistics函数获取最大值:"+summaryStatistics.getMax());
        System.out.println("summaryStatistics函数获取最小值:"+summaryStatistics.getMin());
        System.out.println("summaryStatistics函数获取和"+summaryStatistics.getSum());

        return categories.stream().filter(v -> v.getCatLevel().equals(max) )
                .peek(v -> v.setLowerLevel(this.testa(v,categories))).collect(Collectors.toList());
    }

    public List<PmsDTO> testa(PmsDTO pmsDTO,List<PmsDTO> pmsDTOS){
        return pmsDTOS.stream().filter(v -> v.getParentCid().equals(pmsDTO.getCatId()))
                .peek(v -> v.setLowerLevel(this.testa(v,pmsDTOS)))
                .collect(Collectors.toList());
    }



}





























