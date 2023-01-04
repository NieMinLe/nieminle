package com.swaggertest.demo.service;

import com.swaggertest.demo.domain.dto.CateDTO;
import com.swaggertest.demo.domain.dto.PmsCategoryDTO;

import java.util.List;

public interface CateService {

    /**
     * 获取科目的三级下拉
     * @return
     */
    List<CateDTO> threeLevelPullDown();

    void insetPullDown(PmsCategoryDTO cateDTO);

    List<PmsCategoryDTO> qryByName(PmsCategoryDTO name);

    void qryAll(String name);

    List<PmsCategoryDTO> qryDate(Integer day);

}
