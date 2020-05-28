package com.swaggertest.demo.dao;

import com.swaggertest.demo.entity.dto.TestDto;
import java.util.List;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Description: 测试Mapper
 * @author 野乐 2020/4/29 - 15:05
 **/
@Repository
public interface TestMapper extends Mapper<TestDto> {

    List<TestDto> queryLikeName(String Sname);

    List<TestDto> selectPage();

}
