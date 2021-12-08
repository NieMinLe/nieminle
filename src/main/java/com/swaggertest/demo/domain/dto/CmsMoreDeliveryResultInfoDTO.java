package com.swaggertest.demo.domain.dto;

import lombok.Data;

import java.util.List;

/**
 * SOA requestInfo
 */
@Data
public class CmsMoreDeliveryResultInfoDTO {

    /**
     * 接收返回值
     */
    private List<CmsDeliveryResultInfoDTO> supplierSchedulings;

    public CmsMoreDeliveryResultInfoDTO() {
    }
}
