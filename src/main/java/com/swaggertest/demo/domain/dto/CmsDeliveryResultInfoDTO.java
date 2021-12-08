package com.swaggertest.demo.domain.dto;

import lombok.Data;

/**
 * SOA requestInfo
 */
@Data
public class CmsDeliveryResultInfoDTO {

    /**
     * 单据ID
     */
    private Long sourceId;

    /**
     * 物料编码
     */
    private String itemNumber;

    /**
     * 供应商
     */
    private String vendorNumber;

    /**
     * 业务类型
     */
    private String vendorSiteCode;

    /**
     * 返回状态
     */
    private String processStatus;

    /**
     * 返回报错
     */
    private String processMessage;

}
