package com.swaggertest.demo.domain.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "first")
@Data
@ApiModel(value = "查询所有DTO")
public class TestDto1 implements Serializable {
    private String test1;
    private String test2;
    private String test3;
    private String test4;

    public void setTest4(String t1,String t2,String t3) {
        this.test4 = t1+t2+t3;
    }
}
