package com.swaggertest.demo.dao;

import com.swaggertest.demo.domain.dto.PmsCategoryDTO;
import com.swaggertest.demo.domain.po.CatePO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CateDao {

    /**
     * 科目三级下拉
     */
    List<CatePO> threeLevelPullDown(@Param("pidList") List<Long> pidList);

    void insetPullDown(PmsCategoryDTO cateDTO);

    List<PmsCategoryDTO> qryByName(String name);

}
