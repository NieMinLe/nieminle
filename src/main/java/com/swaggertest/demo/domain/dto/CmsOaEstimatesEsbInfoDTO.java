package com.swaggertest.demo.domain.dto;

import lombok.Data;

/**
 * SOA esbInfo
 */
@Data
public class CmsOaEstimatesEsbInfoDTO {

    /**
     * 请求到达时间
     */
    private String requestTime;

    /**
     * 请求结束时间
     */
    private String responseTime;

    /**
     * 实例ID，传SRM表中的主键ID
     */
    private String instId;

    /**
     * 返回编码
     */
    private String returnCode;

    /**
     * 返回信息
     */
    private String returnMsg;

    /**
     * 返回状态，W 验证不通过，S 成功 E 失败
     */
    private String returnStatus;

    /**
     * 备用字段1
     */
    private String attr1;

    /**
     * 备用字段2
     */
    private String attr2;

    /**
     * 备用字段3
     */
    private String attr3;

}
