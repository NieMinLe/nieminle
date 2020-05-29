package com.swaggertest.demo.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.swaggertest.demo.entity.dto.TestDto;
import com.swaggertest.demo.service.TestService;
import com.swaggertest.demo.utils.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@Api(value = "testController", tags = "简单测试")
public class TestController {

    @Autowired
    private TestService testService;

    @Autowired
    private RedisUtil redisUtil;

    @ApiOperation("查询表所有数据")
    @GetMapping("/query")
    public List<TestDto> query(){
        // redisUtil.setCache("three","you is women");
        // redisUtil.setCacheExpireTime("three","you is wo",2L, TimeUnit.HOURS);
        // redisUtil.del("first");
        // redisUtil.del("three");
        return testService.query();
    }

    @GetMapping("/queryOne")
    @ApiOperation("通过ID查询数据")
    @ApiImplicitParams(
        @ApiImplicitParam(name = "id",value = "主键ID",required = true)
    )
    public TestDto queryOne(int id) {
        return testService.queryOne(id);
    }

    @GetMapping("/queryLikeName")
    @ApiOperation("通过名字模糊查询")
    @ApiImplicitParams(
        @ApiImplicitParam(name = "name",value = "查询名字",required = true)
    )
    public List<TestDto> queryLikeName(String name) {
        return testService.queryLikeName(name);
    }

    @ApiOperation("插入数据")
    @PostMapping("/insert")
    public int insert(TestDto testDto){
        return testService.insert(testDto);
    }

    @PutMapping("/update")
    @ApiOperation("修改数据")
    @ApiImplicitParams(
        @ApiImplicitParam(name = "sno",value = "主键ID",required = true)
    )
    public int update(TestDto testDto){
        return testService.update(testDto);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除数据")
    @ApiImplicitParams(
        @ApiImplicitParam(name = "sno",value = "删除ID",required = true)
    )
    public int delete(int sno){
        return testService.delete(sno);
    }

    @GetMapping("/findAllPage")
    @ApiOperation("分页查询")
    public String findAllPage(Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<TestDto> countries = testService.query();

        PageInfo<TestDto> page = new PageInfo<>(countries);
        System.out.println("当前页：" + page.getPageNum());
        System.out.println("每页条数：" + page.getPageSize());
        System.out.println("总页数：" + page.getPages());
        System.out.println("总条数：" + page.getTotal());
        System.out.println("数据：" + page.getList());

        System.out.println("下一页：" + page.getNextPage());
        System.out.println("上一页：" + page.getPrePage());
        System.out.println("起始页：" + page.getNavigateFirstPage());
        System.out.println("结尾页：" + page.getNavigateLastPage());

        System.out.println(JSON.toJSONString(countries));
        System.out.println(JSON.toJSONString(page));
        return JSON.toJSONString(page);
    }

}
