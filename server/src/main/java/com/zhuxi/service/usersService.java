package com.zhuxi.service;


import com.zhuxi.DTO.UsersDTO;
import com.zhuxi.VO.UsersVO;

public interface usersService {

    //登录
    UsersVO login(UsersDTO usersDTO);

    //删除用户
    void deleteUser(int id);

    //增加用户
    void  addUser(UsersDTO usersDTO);

    //查询用户信息
    UsersVO selectUserById(int id);

    //修改用户信息
    void updateUser(UsersVO user);

}
