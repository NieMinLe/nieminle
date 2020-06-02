package com.swaggertest.demo.controller;

import com.swaggertest.demo.domain.po.UserPo;
import com.swaggertest.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@CrossOrigin
@Api(value = "UserController", tags = "用户")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("查询表所有数据")
    @GetMapping("/findUser")
    public String findUser(Model model){
        List<UserPo> list = userService.getAllUser();
        model.addAttribute("list",list);
        return "index.html";
    }

    @ApiOperation("用户注册")
    @PostMapping(value = "/insertUser")
    public String insert(@RequestBody UserPo userPo,Model model){

        if (userPo.getUsername() != null && userPo.getPassword() != null){
            // UserPo user = new UserPo();
            // user.setUsername(username);
            // user.setPassword(password);

            userService.insertUser(userPo);
            model.addAttribute("pass",1);
            return "1";
        }

        return "0";

    }

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public String login(UserPo user) {
        System.out.println("表现层：用户登录");
        // 调用注入的 usersService 调用 login 方法
        if(userService.login(user)){
            return "successlogin.html";
        }else{
            return "falselogin.html";
        }
    }


}

















