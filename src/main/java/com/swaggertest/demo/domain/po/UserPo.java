package com.swaggertest.demo.domain.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Table(name = "user")
@ApiModel(value = "查询所有DTO")
public class UserPo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(name = "id",value = "编号", dataType = "Integer")
    private Integer id;

    @ApiModelProperty(name = "username",value = "名字", dataType = "String")
    private String username;

    @ApiModelProperty(name = "password",value = "密码", dataType = "String")
    private String password;

}
