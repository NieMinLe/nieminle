package com.swaggertest.demo.domain.dto;

import com.swaggertest.demo.system.enums.EnumOfDimensionDrillDown;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 * 科目层级下拉框PO.
 *
 * @author Riefu
 */
@Data
public class CateDTO implements Serializable {

    /**
     * 科目Id.
     */
    private Long subjectId;

    /**
     * 父级科目id.
     */
    private Long pid;

    /**
     * 科目名称.
     */
    private String subjectName;

    /**
     * 层级.
     */
    private EnumOfDimensionDrillDown drillDown;

    /**
     * 下级科目列表.
     */
    private List<CateDTO> lowerLevel;

}
