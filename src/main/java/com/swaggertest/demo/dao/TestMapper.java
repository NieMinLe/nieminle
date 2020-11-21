package com.swaggertest.demo.dao;

import com.swaggertest.demo.domain.dto.TestDto;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface TestMapper extends Mapper<TestDto> {

    List<TestDto> queryLikeName(String Sname);

    List<TestDto> queryBySno(@Param("snos") List<Long> snos);

    List<TestDto> queryAll(@Param("page") Integer page,@Param("limit") Integer limit);

    List<TestDto> queryEvery();

    Integer queryCount();

}
