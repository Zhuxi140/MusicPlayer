package com.zhuxi.service;


import com.zhuxi.entity.users;
import org.springframework.stereotype.Service;

public interface usersService {
    void login(String username, String password);

    void deleteUser(int id);
}
