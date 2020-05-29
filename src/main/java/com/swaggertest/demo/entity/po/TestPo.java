package com.swaggertest.demo.entity.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Table(name = "first")
@ApiModel(value = "查询所有DTO")
public class TestPo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(name = "Sno",value = "编号", dataType = "Integer")
    private Integer Sno;

    @ApiModelProperty(name = "Sname",value = "姓名（长度10）", dataType = "String")
    private String Sname;

    @ApiModelProperty(name = "Sex",value = "性别（只能是男或女）", dataType = "String")
    private String Sex;

    @ApiModelProperty(name = "Sage",value = "年龄（长度100）", dataType = "Integer")
    private Integer Sage;

    @ApiModelProperty(name = "Sdept",value = "部门（长度4）", dataType = "String")
    private String  Sdept;

    @ApiModelProperty(name = "level",value = "权限等级", dataType = "Intefg")
    private Integer level;//用户等级: 0-超级管理员
}
