package com.swaggertest.demo.service;

import com.swaggertest.demo.domain.dto.CateDTO;
import java.util.List;

public interface CateService {

    /**
     * 获取科目的三级下拉
     * @return
     */
    List<CateDTO> threeLevelPullDown();

}
