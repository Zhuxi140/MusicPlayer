package com.zhuxi.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import com.zhuxi.service.usersService;

@Controller
public class userLoginController {

    private final usersService usersService;

    @Autowired
    public userLoginController(usersService usersService) {
        this.usersService = usersService;
    }

    //登录
    @PostMapping("/login")
    public String login(String username, String password){
        usersService.login(username, password);
        return "成功登陆";
    }
}
