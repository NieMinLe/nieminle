package com.swaggertest.demo.controller.testa;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.swaggertest.demo.domain.dto.TestDto;
import com.swaggertest.demo.service.TestService;
import com.swaggertest.demo.servlet.MyRunnable;
import com.swaggertest.demo.servlet.MyThread;
import com.swaggertest.demo.utils.RedisUtil;
import com.swaggertest.demo.webApi.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@Slf4j
@Api(value = "testController", tags = "简单测试")
public class TestController {

    @Autowired
    private TestService testService;

//    @Autowired
//    private RedisUtil redisUtil;

    @ApiOperation("多线程查询数据")
    @GetMapping("/query1")
    public ApiResult queryMultithreading(){
        Long zhiqian = System.currentTimeMillis();
        List<TestDto> testDtos = testService.queryAll();
        Long zhihou = System.currentTimeMillis();
        Long miao = (zhihou - zhiqian) / 1000;
        System.out.println("这里是相差的秒数=-=-=-=-="+miao);
        return ApiResult.success(testDtos);
    }

    @ApiOperation("简单的查询所有数据")
    @GetMapping("/query2")
    public Page<TestDto> queryAll(PageRequest pageRequest){
//        Page<TestDto> page = PageHelper.getLocalPage(pageRequest, () -> {
//            return testService.queryAllThis();
//        });
        return null;
    }

    @ApiOperation("查询表所有数据")
    @GetMapping("/query")
    public ApiResult query(Integer page, Integer limit){
        log.info("打印入参的值=-=page={},limit={}", page,limit);
        // redisUtil.setCache("three","you is women");
        // redisUtil.setCacheExpireTime("three","you is wo",2L, TimeUnit.HOURS);
        Integer index = (page - 1) * limit;
        List<TestDto> list = testService.query(index,limit);
        List<Integer> sno = list.stream().map(TestDto::getSno).collect(Collectors.toList());
        // redisUtil.setRedisBitMap(sno);
        return ApiResult.success(list,testService.queryCount());
    }


    //list分页
    public List<TestDto> fenye(List<TestDto> list, int pageIndex, int pageSize){

        List<TestDto> currentPageList = new ArrayList<>();

        if (list != null && list.size() > 0) {
            int currIdx = (pageIndex > 1 ? (pageIndex - 1) * pageSize : 0);
            for (int i = 0; i < pageSize && i < list.size() - currIdx; i++) {
                TestDto data = list.get(currIdx + i);
                currentPageList.add(data);
            }
        }

        return currentPageList;

    }


    @GetMapping("/queryOne")
    @ApiOperation("通过ID查询数据")
    @ApiImplicitParams(
        @ApiImplicitParam(name = "id",value = "主键ID")
    )
    public TestDto queryOne(int id) {
        TestDto testDto = testService.queryOne(id);
        return testDto;
    }

    @GetMapping("/queryLikeName")
    @ApiOperation("通过名字模糊查询")
    @ApiImplicitParams(
        @ApiImplicitParam(name = "name",value = "查询名字",required = true)
    )
    public List<TestDto> queryLikeName(String name) {
        List<TestDto> testDtos = testService.queryLikeName(name);
        TestDto t = testDtos.get(10);
        return testDtos;
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

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ApiOperation("删除数据")
    @ApiImplicitParams(
        @ApiImplicitParam(name = "sno",value = "删除ID",required = true)
    )
    public int delete(@RequestParam int sno){
        return testService.delete(sno);
    }

    @GetMapping("/findAllPage")
    @ApiOperation("分页查询")
    public String findAllPage(Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<TestDto> countries = testService.query(1,2);

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

    @GetMapping("/thread")
    @ApiOperation("继承Thread接口线程")
    public String thread(){
        //start启动线程
        new MyThread().start();
        return "thread";
    }

    @GetMapping("/runnable")
    @ApiOperation("实现Runnable方法线程")
    public String runnable(){
        //new 一个runnable，start任务
        MyRunnable a=  new MyRunnable();
        new  Thread(a).start();
        return "runnable";
    }
}
