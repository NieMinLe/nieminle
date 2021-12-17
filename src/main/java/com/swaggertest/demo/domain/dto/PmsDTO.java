package com.swaggertest.demo.domain.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 商品三级分类DTO.
 */
@Data
public class PmsDTO implements Serializable {

    /**
     * 科目Id.
     */
    private List<Long> catIds;

    /**
     * 父级科目id.
     */
    private String name;

    /**
     * 夫级ID.
     */
    private Long parentCid;

    /**
     * 层级.
     */
    private Integer catLevel;

    /**
     * 数量单位.
     */
    private String productCount;

    /**
     * 排序.
     */
    private Integer sort;

    /**
     * 下级科目列表.
     */
    private List<PmsDTO> lowerLevel;

}
