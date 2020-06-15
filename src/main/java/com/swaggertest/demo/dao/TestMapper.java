package com.swaggertest.demo.dao;

import com.swaggertest.demo.domain.dto.TestDto;
import java.util.List;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface TestMapper extends Mapper<TestDto> {

    List<TestDto> queryLikeName(String Sname);

}
