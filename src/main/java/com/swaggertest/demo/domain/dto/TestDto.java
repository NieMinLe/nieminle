package com.swaggertest.demo.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
@Table(name = "first")
@ApiModel(value = "查询所有DTO")
public class TestDto implements Serializable {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(name = "Sno",value = "编号", dataType = "Integer")
    @NotNull(message = "主键ID不能为空!")
    private Integer Sno;

    @ApiModelProperty(name = "Sname",value = "姓名（长度10）", dataType = "String")
    private String Sname;

    @ApiModelProperty(name = "Sex",value = "性别（只能是男或女）", dataType = "String")
    private String Sex;

    @ApiModelProperty(name = "Sage",value = "年龄（长度100）", dataType = "Integer")
    private Integer Sage;

    @ApiModelProperty(name = "Sdept",value = "部门（长度4）", dataType = "String")
    private String  Sdept;

    private BigDecimal price;

    private List<Long> test;

    private Date startDate; //章节开始时间

    private Date endDate; //章节结束时间


}
