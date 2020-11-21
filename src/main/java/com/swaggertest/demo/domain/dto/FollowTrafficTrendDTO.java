package com.swaggertest.demo.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;

/**
 * @ClassName FollowTrafficTrendDTO
 * @Description: 每日流量申请统计
 * @Author 野乐
 * @Date 2020/3/26
 **/
@Data
@ApiModel(value = "每日流量申请统计DTO")
public class FollowTrafficTrendDTO implements Serializable {

    @ApiModelProperty(value = "日期")
    private String day;

    @ApiModelProperty(value = "数量")
    private Integer num;

}
