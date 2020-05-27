package com.swaggertest.demo.service.impl;

import com.swaggertest.demo.exception.MyException;
import com.swaggertest.demo.dao.TestMapper;
import com.swaggertest.demo.entity.dto.TestDto;
import com.swaggertest.demo.service.testService;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class testServiceImpl implements testService {

    @Resource
    private TestMapper testMapper;

    @Override
    public List<TestDto> query() {
        return testMapper.selectAll();
    }

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
            if(testDto.getSex().length()>1 || (!testDto.getSex().equals("男") && !testDto.getSex().equals("女"))){
                throw new MyException("性别只能为男或女");
            }
        }
        return testMapper.updateByPrimaryKeySelective(testDto);
    }

    @Override
    public int delete(int id) {
        return testMapper.deleteByPrimaryKey(id);
    }
}
