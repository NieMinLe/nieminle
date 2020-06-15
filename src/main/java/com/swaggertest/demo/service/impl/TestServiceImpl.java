package com.swaggertest.demo.service.impl;

import com.google.common.base.Preconditions;
import com.swaggertest.demo.dao.TestMapper;
import com.swaggertest.demo.domain.dto.TestDto;
import com.swaggertest.demo.exception.IsException;
import com.swaggertest.demo.service.TestService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
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
            Preconditions.checkArgument(testDto.getSex().length()==1 ,"性别只能为男或女a" );
            if(!testDto.getSex().equals("男") && !testDto.getSex().equals("女")){
                throw new IsException("性别只能为男或女");
            }
        }
        return testMapper.updateByPrimaryKeySelective(testDto);
    }

    @Override
    public int delete(int id) {
        return testMapper.deleteByPrimaryKey(id);
    }
}
