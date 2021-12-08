package com.swaggertest.demo.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * SOA esbInfo
 */
@Data
public class CmsErpProportionResultInfoDTO {

    /**
     * ERP传过来的值
     */

    @JsonProperty("xReturnCode")
    private String xReturnCode;

    /**
     * 返回数据信息
     */
    @JsonProperty("xResponseData")
    private CmsMoreDeliveryResultInfoDTO xResponseData;

    public CmsErpProportionResultInfoDTO() {
    }
}
