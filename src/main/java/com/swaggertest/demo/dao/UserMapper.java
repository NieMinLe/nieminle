package com.swaggertest.demo.dao;

import com.swaggertest.demo.domain.po.UserPo;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface UserMapper  extends Mapper<UserPo> {

    /**
     * 用户登录
     */
    @Select("select * from user where username=#{username} and password=#{password}")
    UserPo login(UserPo user);

    UserPo find(UserPo userPo);

    @Select("select * from test")
    List<UserPo> getAllUser();
}
