package com.swaggertest.demo.domain.dto;


import java.io.Serializable;
import java.util.List;
import javax.persistence.Table;
import lombok.Data;

@Data
@Table(name = "first")
public class First implements Serializable{

  private Integer sno;
  private String sname;
  private String sex;
  private Integer sage;
  private String sdept;

  /**
   * 课程信息
   */
  private List<courseInfo> courseInfo;

  @Data
  public static class courseInfo implements Serializable {
    /**
     * 课程ID
     */
    private Long courseId;

    /**
     * 课程名称
     */
    private String courseName;
  }

}
