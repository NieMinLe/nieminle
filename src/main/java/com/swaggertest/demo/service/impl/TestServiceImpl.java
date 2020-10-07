package com.swaggertest.demo.service.impl;

import com.google.common.base.Preconditions;
import com.swaggertest.demo.dao.TestMapper;
import com.swaggertest.demo.domain.dto.TestDto;
import com.swaggertest.demo.exception.IsException;
import com.swaggertest.demo.service.TestService;
import com.swaggertest.demo.system.enums.EnumApplyStatus;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestMapper testMapper;

    @Override
    public List<TestDto> query(List<Long> snos) {
        int pageSize = 2;
        List<TestDto> studentIdList = new ArrayList<>();
        if (snos.size() <= pageSize) {
            studentIdList= testMapper.queryBySno(snos);
        } else {
            int pageIndex = 1;
            int totalPage = snos.size() % pageSize == 0 ? snos.size() / pageSize : (snos.size() / pageSize + 1);
            while (pageIndex <= totalPage) {
                int fromIndex = (pageIndex - 1) * pageSize;
                int toIndex = fromIndex + pageSize;
                if (toIndex > snos.size()) {
                    toIndex = snos.size();
                }
                List<Long> test = snos.subList(fromIndex, toIndex);
                List<TestDto> onePageClassIds = testMapper.queryBySno(test);
                studentIdList.addAll(onePageClassIds);
                pageIndex++;
            }
            studentIdList = studentIdList.stream().distinct().collect(Collectors.toList());
        }
        return studentIdList;
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
