package com.zhuxi.mapper;

import com.zhuxi.entity.users;
import org.apache.ibatis.annotations.*;


/**
 * 用户数据库操作
 */

@Mapper
public interface usersMapper {

    // 根据id查询用户
    @Select("SELECT id,username,password,avatar,role FROM users WHERE id=${id}")
    users selectUserById( int id);

    //删除用户
    @Delete("DELETE FROM users WHERE id=${id}")
    boolean deleteUserById( int id);

    //登录
    @Select("SELECT id,username,password,avatar,role FROM users WHERE username=${username}")
    users loginByName(String username);


}
