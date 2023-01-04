package com.swaggertest.demo.service.impl;

import com.google.common.base.Preconditions;
import com.swaggertest.demo.dao.TestMapper;
import com.swaggertest.demo.domain.dto.TestDto;
import com.swaggertest.demo.exception.IsException;
import com.swaggertest.demo.service.TestService;
import com.swaggertest.demo.system.enums.EnumApplyStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestMapper testMapper;

    @Override
    public List<TestDto> query(Integer page,Integer limit){
        return testMapper.queryAll(page,limit);
    }

    @Override
    public List<TestDto> queryAllThis(){
        return testMapper.queryEvery();
    }

    @Override
    public List<TestDto> queryAll(){
        Integer listSize  = testMapper.queryCount();
        Integer runSize  =20;

        // 一个线程处理数据条数，如果库中有100条数据，开启20个线程，那么每一个线程执行的条数就是5条
        int count = listSize / runSize;

        // 创建一个线程池，数量和开启线程的数量一样
        ExecutorService executor = Executors.newFixedThreadPool(runSize);

        List<TestDto> listThis = new ArrayList<>();

        for (int i = 0; i < runSize; i++) {
            int index = i * count;
            int num = count;
            //开启线程
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        //查询的结果如何保存下来，会不会存在覆盖的问题
                        List<TestDto> list = testMapper.queryAll(index , num);
                        listThis.addAll(list);
                    } catch (Exception e) {
                        System.out.println("查询失败" + e);
                    }
                }
            });
        }
        //关闭线程
        executor.shutdown();
        return listThis;
    }

    @Override
    public Integer queryCount(){
        return testMapper.queryCount();
    }

    //轮询
    // public List<TestDto> query() {
    //     int pageSize = 2;
    //     List<TestDto> studentIdList = new ArrayList<>();
    //     if (snos.size() <= pageSize) {
    //         studentIdList= testMapper.queryBySno(snos);
    //     } else {
    //         int pageIndex = 1;
    //         int totalPage = snos.size() % pageSize == 0 ? snos.size() / pageSize : (snos.size() / pageSize + 1);
    //         while (pageIndex <= totalPage) {
    //             int fromIndex = (pageIndex - 1) * pageSize;
    //             int toIndex = fromIndex + pageSize;
    //             if (toIndex > snos.size()) {
    //                 toIndex = snos.size();
    //             }
    //             List<Long> test = snos.subList(fromIndex, toIndex);
    //             List<TestDto> onePageClassIds = testMapper.queryBySno(test);
    //             studentIdList.addAll(onePageClassIds);
    //             pageIndex++;
    //         }
    //         studentIdList = studentIdList.stream().distinct().collect(Collectors.toList());
    //     }
    //     return studentIdList;
    // }

    @Override
    public TestDto queryOne(int id) {
        return testMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<TestDto> queryLikeName(String name) {
        return testMapper.queryLikeName(name);
    }

    @Override
    public int insert(TestDto testDto) {
        return testMapper.insert(testDto);
    }

    @Override
    public int update(TestDto testDto){
        if(testDto.getSex()!=null){
            Preconditions.checkArgument(testDto.getSex().length()==1 ,"性别只能为男或女a" );
            if(!testDto.getSex().equals("男") && !testDto.getSex().equals("女")){
                throw new IsException(EnumApplyStatus.APPROVAL_IN.getStatus(),"性别只能为男或女");
            }
        }
        return testMapper.updateByPrimaryKeySelective(testDto);
    }

    @Override
    public int delete(int id) {
        return testMapper.deleteByPrimaryKey(id);
    }
}
