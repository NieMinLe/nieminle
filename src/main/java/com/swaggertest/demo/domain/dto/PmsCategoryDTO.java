package com.swaggertest.demo.domain.dto;

import lombok.Data;

import java.io.Serializable;

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


}
