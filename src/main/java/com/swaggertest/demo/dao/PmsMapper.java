package com.swaggertest.demo.dao;

import com.swaggertest.demo.domain.dto.PmsDTO;
import com.swaggertest.demo.domain.dto.TestDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface PmsMapper{

    List<PmsDTO> queryAll(PmsDTO pmsDTO);


}
