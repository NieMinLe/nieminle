package com.swaggertest.demo.domain.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.swaggertest.demo.annotation.ColumnConf;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@Table(name = "first")
@ApiModel(value = "查询所有DTO")
public class TestDto implements Serializable {
    @JsonFormat(pattern = "yyyy.MM.dd")
    @NotNull(message = "时间")
    private LocalDate dayOrMonth;
    private Long catId;
    private Date test1;
    private Date test2;
    private Integer inta;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(name = "Sno",value = "编号", dataType = "Integer")
    @NotNull(message = "主键ID不能为空!")
    private Integer Sno;

    @ApiModelProperty(name = "Sname",value = "姓名（长度10）", dataType = "String")
    @ColumnConf("姓名")
    private String Sname;

    @ApiModelProperty(name = "Sex",value = "性别（只能是男或女）", dataType = "String")
    @ColumnConf("性别")
    private String Sex;

    @ApiModelProperty(name = "Sage",value = "年龄（长度100）", dataType = "Integer")
    private Integer Sage;

    @ApiModelProperty(name = "Sdept",value = "部门（长度4）", dataType = "String")
    private String  Sdept;

    @ApiModelProperty(name = "Sdept",value = "部门（长度4）", dataType = "String")
    private Date nml;

    private List<Long> test;

    private Boolean flag;

    @JSONField(name = "VBELN")
    private String VBELN;

}
