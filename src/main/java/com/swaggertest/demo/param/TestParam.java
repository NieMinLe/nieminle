package com.swaggertest.demo.param;

import io.swagger.annotations.ApiModelProperty;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

@Data
public class TestParam {
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
}
