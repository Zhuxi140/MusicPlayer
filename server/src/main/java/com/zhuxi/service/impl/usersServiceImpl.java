package com.zhuxi.service.impl;

import com.zhuxi.entity.users;
import com.zhuxi.exception.userException.userLogin;
import com.zhuxi.mapper.usersMapper;
import com.zhuxi.service.usersService;
import com.zhuxi.utils.BcryptUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class usersServiceImpl implements usersService {

    private final BcryptUtils  bcryptUtils;
    private final usersMapper userMapper;

    @Autowired
    public usersServiceImpl(BcryptUtils bcryptUtils,  usersMapper userMapper) {
        this.bcryptUtils = bcryptUtils;
        this.userMapper = userMapper;
    }


    @Override
    public void login(String username, String password) {

        users user = userMapper.loginByName(username);
        if(user != null){
            if(bcryptUtils.checkPassword(password, user.getPassword()))
                return;
            else
                throw new userLogin("用户名/密码错误");
        }else
            throw new userLogin("用户不存在");

    }

    @Override
    public void deleteUser(int id) {

    }
}
