package com.swaggertest.demo.service;

import com.swaggertest.demo.domain.po.UserPo;
import java.util.List;

public interface UserService {
    UserPo find(UserPo userPo);

    //查询所有用户
    List<UserPo> getAllUser();

    //用户注册
    void insertUser(UserPo user);

    //用户登录
    boolean login(UserPo user);

}
