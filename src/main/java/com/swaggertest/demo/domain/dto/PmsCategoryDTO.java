package com.swaggertest.demo.domain.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 科目层级下拉框PO.
 *
 * @author Riefu
 */
@Data
public class PmsCategoryDTO implements Serializable {

    private Long catId;
    private String name;
    private Integer catLevel;
    private BigDecimal money;
    private String productUnit;
    private Integer tier = 0;

    /**
     * 上一级的名字
     */
    private String subName;

    private BigDecimal upMoney;
    private Date createDate;
    private List<PmsCategoryDTO> children;

}
