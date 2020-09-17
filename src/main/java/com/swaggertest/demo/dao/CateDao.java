package com.swaggertest.demo.dao;

import com.swaggertest.demo.domain.po.CatePO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CateDao {

    /**
     * 科目三级下拉
     */
    List<CatePO> threeLevelPullDown(@Param("pidList") List<Long> pidList);

}
