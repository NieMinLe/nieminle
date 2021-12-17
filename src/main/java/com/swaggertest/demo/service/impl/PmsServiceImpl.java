package com.swaggertest.demo.service.impl;

import com.google.common.base.Preconditions;
import com.swaggertest.demo.dao.PmsMapper;
import com.swaggertest.demo.dao.TestMapper;
import com.swaggertest.demo.domain.dto.PmsDTO;
import com.swaggertest.demo.domain.dto.TestDto;
import com.swaggertest.demo.exception.IsException;
import com.swaggertest.demo.service.PmsService;
import com.swaggertest.demo.service.TestService;
import com.swaggertest.demo.system.enums.EnumApplyStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class PmsServiceImpl implements PmsService {

    @Autowired
    private PmsMapper pmsMapper;

    @Override
    public List<PmsDTO> threeLevelPullDown(){
        return pmsMapper.queryAll();
    }

}
