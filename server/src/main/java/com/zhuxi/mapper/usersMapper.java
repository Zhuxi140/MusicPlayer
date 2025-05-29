package com.zhuxi.mapper;

import com.zhuxi.DTO.UsersDTO;
import com.zhuxi.VO.UsersVO;
import com.zhuxi.entity.Users;
import org.apache.ibatis.annotations.*;


/**
 * 用户数据库操作
 */

@Mapper
public interface usersMapper {

    // 根据id查询用户
    @Select("SELECT id,username,password,avatar,role FROM users WHERE id=#{id}")
    UsersVO selectUserById(int id);

    //删除用户
    @Delete("DELETE FROM users WHERE id=#{id}")
    boolean deleteUserById( int id);

    //添加
    @Insert("INSERT INTO users(username, password, role) VALUE (#{username},#{password},#{role})")
    void addUser(UsersDTO usersDTO);

    //登录
/*    @Select("SELECT id,username,password,role FROM users WHERE username=#{username}")
    UsersVO loginByNameAndPassword(UsersDTO usersDTO);*/

    @Select("SELECT id,username,password,role FROM users WHERE username=#{username}")
    Users selectUserByName(String username);


    //修改
    boolean updateUser(UsersVO user);


}
