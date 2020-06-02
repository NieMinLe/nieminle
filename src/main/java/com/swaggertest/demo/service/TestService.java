package com.swaggertest.demo.service;

import com.swaggertest.demo.domain.dto.TestDto;
import java.util.List;

public interface TestService {

    List<TestDto> query();

    TestDto queryOne(int id);

    List<TestDto> queryLikeName(String name);

    int insert(TestDto testDto);

    int update(TestDto testDto);

    int delete(int id);

}
