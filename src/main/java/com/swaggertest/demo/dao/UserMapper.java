package com.swaggertest.demo.dao;

import com.swaggertest.demo.domain.po.UserPo;
import java.util.List;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface UserMapper  extends Mapper<UserPo> {

    /**
     * 用户登录
     */
    @Select("select * from user where username=#{username} and password=#{password}")
    UserPo login(UserPo user);

    UserPo find(UserPo userPo);

    @Select("select * from user")
    List<UserPo> getAllUser();
}
