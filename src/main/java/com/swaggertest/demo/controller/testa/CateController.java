package com.swaggertest.demo.controller.testa;

import com.swaggertest.demo.domain.dto.CateDTO;
import com.swaggertest.demo.domain.dto.PmsCategoryDTO;
import com.swaggertest.demo.service.CateService;
import com.swaggertest.demo.webApi.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin
@Api(value = "cateController", tags = "科目测试")
public class CateController {

    @Autowired
    private CateService cateService;

    @Autowired
    private Test test;

    @ApiOperation("查询所有数据")
    @GetMapping("/qryAll")
    public ApiResult qryAll(String name){
        System.out.println("查询所有数据=-=-={}"+name);
        List<String> nameList = Arrays.asList(name.split(","));
        List<PmsCategoryDTO> returnList = new ArrayList<>();

        //创建多个线程
//        List<CompletableFuture<List<PmsCategoryDTO>>> taskList = nameList.stream()
//                .map(item -> CompletableFuture.supplyAsync(() -> cateService.qryAll(item))).collect(Collectors.toList());

        //执行线程,join阻塞线程，获取CompletableFuture异步之后的返回值
//        CompletableFuture.allOf(taskList.toArray(new CompletableFuture[taskList.size()])).join();
//        //获取返回结果,getNow(null)方法在CompletableFuture完成的情况下会返回结果
//        taskList.stream().map(future -> future.getNow(null)).forEach(returnList::addAll);
//        taskList.forEach(task ->{
//            List<PmsCategoryDTO> tt = task.getNow(null);
//            returnList.addAll(tt);
//        });
        return ApiResult.success(returnList);
    }

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
        System.out.println(cateDTO);
        return ApiResult.success();
    }


}





























