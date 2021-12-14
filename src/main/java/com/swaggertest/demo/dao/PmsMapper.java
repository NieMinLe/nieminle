package com.swaggertest.demo.dao;

import com.swaggertest.demo.domain.dto.PmsDTO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PmsMapper{

    List<PmsDTO> queryAll(PmsDTO pmsDTO);


}
