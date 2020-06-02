package com.swaggertest.demo.domain.po;

import java.time.LocalDateTime;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author LIZEJIN
 * @Description TODO
 * @Date 2020/5/6
 * @Time 15:49
 * @Version V1.0.0
 */
@Builder
@Data
@Table(name = "t_open_user")
@AllArgsConstructor
@NoArgsConstructor
public class OpenUserPO {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;  //主键
  private LocalDateTime createTime;//创建时间
  private LocalDateTime updateTime;//更新时间
  private Long operatorId;//操作员ID

  private String name;//登陆用户名称:个人名称，公司名称
  private String password;//登陆密码:密文
  private Integer type;//用户类型:1-个人，2-企业
  private Integer authentication;//是否完成实名认证:0-否，1-是。变为0时，影响通行证、token、密钥的有效性
  private Integer level;//用户等级: 0-超级管理员
  private Integer status;//状态：0-已注销，1-已冻结，2-已删除，3-正常。此状态影响通行证、token、密钥的有效性

}
