package com.swaggertest.demo.service.impl;

import com.swaggertest.demo.dao.UserMapper;
import com.swaggertest.demo.domain.po.UserPo;
import com.swaggertest.demo.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserPo find(UserPo userPo) {
        return userMapper.find(userPo);
    }

    @Override
    public List<UserPo> getAllUser() {
        return userMapper.getAllUser();
    }

    @Override
    public void insertUser(UserPo user) {
        userMapper.insert(user);
    }

    @Override
    public boolean login(UserPo user) {
        UserPo userPo = userMapper.login(user);
        if (userPo == null){
            return false;
        }
        return true;
    }
}
