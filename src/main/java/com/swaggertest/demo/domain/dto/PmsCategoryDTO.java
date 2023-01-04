package com.swaggertest.demo.domain.dto;

import lombok.Data;

import java.io.Serializable;
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
    private Integer tier;
    private String subName;
    private String upMoney;
    private String productUnit;
    private String money;
    private List<PmsCategoryDTO> children;


}
