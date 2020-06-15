package com.swaggertest.demo.domain.dto;


import javax.persistence.Table;
import lombok.Data;

@Data
@Table(name = "first")
public class First {

  private Integer sno;
  private String sname;
  private String sex;
  private Integer sage;
  private String sdept;

}
