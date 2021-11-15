package com.swaggertest.demo.service;

import com.swaggertest.demo.domain.dto.CateDTO;
import com.swaggertest.demo.domain.dto.PmsDTO;

import java.util.List;

public interface PmsService {

    /**
     * 获取科目的三级下拉
     * @return
     */
    List<PmsDTO> threeLevelPullDown(PmsDTO pmsDTO);

}
