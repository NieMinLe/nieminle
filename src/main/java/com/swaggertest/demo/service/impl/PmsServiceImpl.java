package com.swaggertest.demo.service.impl;

import com.swaggertest.demo.dao.PmsMapper;
import com.swaggertest.demo.domain.dto.PmsDTO;
import com.swaggertest.demo.service.PmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PmsServiceImpl implements PmsService {

    @Autowired
    private PmsMapper pmsMapper;

    @Override
    public List<PmsDTO> threeLevelPullDown(){
        return pmsMapper.queryAll();
    }

}
