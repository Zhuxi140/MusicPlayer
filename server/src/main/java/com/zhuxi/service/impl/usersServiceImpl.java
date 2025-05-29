package com.zhuxi.service.impl;

import com.zhuxi.DTO.UsersDTO;
import com.zhuxi.VO.UsersVO;
import com.zhuxi.entity.Users;
import com.zhuxi.exception.userException.UserException;
import com.zhuxi.exception.userException.UsersErrorCode;
import com.zhuxi.mapper.usersMapper;
import com.zhuxi.service.usersService;
import com.zhuxi.utils.BcryptUtils;
import com.zhuxi.utils.JwtUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhuxi
 * @Description TODO
 */

@Service
@Log4j2
public class usersServiceImpl implements usersService {

    private final BcryptUtils  bcryptUtils;
    private final usersMapper userMapper;
    private final JwtUtils jwtUtils;

    public usersServiceImpl(BcryptUtils bcryptUtils,  usersMapper userMapper,JwtUtils jwtUtils) {
        this.bcryptUtils = bcryptUtils;
        this.userMapper = userMapper;
        this.jwtUtils = jwtUtils;
    }


    /**
     *
     * @param usersDTO d
     * @return UsersVO
     */
    @Override
    public UsersVO login(UsersDTO usersDTO) {
        checkUser(usersDTO);

        Users exitUsers = userMapper.selectUserByName(usersDTO.getUsername());
        if(exitUsers == null){
            throw new UserException(UsersErrorCode.USER_NOT_EXIST, "用户不存在");
        }else
        if(!bcryptUtils.checkPassword(usersDTO.getPassword(), exitUsers.getPassword())){
            log.info("{}登录失败",usersDTO.getUsername());
            throw new UserException(UsersErrorCode.USER_PASSWORD_ERROR, "用户名或密码错误");
        }else {
            log.info("{}登录成功",usersDTO.getUsername());
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", exitUsers.getId());
            claims.put("username", exitUsers.getUsername());
            claims.put("role", exitUsers.getRole());
            UsersVO usersVO = new UsersVO();
            BeanUtils.copyProperties(exitUsers, usersVO);
            usersVO.setToken(jwtUtils.createTokenDefault(claims));
            return usersVO;
        }

    }


    /**
     * 删除用户
     * @param id
     */
    @Override
    public void deleteUser(int id) {
        if(userMapper.selectUserById(id) != null){
            userMapper.deleteUserById(id);
        }
    }

    /**
     * 添加用户
     * @param userDTO
     */
    @Override
    public void addUser(UsersDTO userDTO) {
        checkUser(userDTO);
        if(userMapper.selectUserByName(userDTO.getUsername()) !=  null)
            throw new UserException(UsersErrorCode.USER_USERNAME_EXIST, "用户名已存在");


        userDTO.setPassword(bcryptUtils.hashPassword(userDTO.getPassword()));
        userMapper.addUser(userDTO);
        log.info("添加用户{}成功",userDTO.getUsername());

    }

    /**
     * 根据id查询用户信息
     * @param id(token中获取)
     * @return
     */
    @Override
    public UsersVO selectUserById(int id) {

        UsersVO users = userMapper.selectUserById(id);
        if(users == null)
            throw new UserException(UsersErrorCode.USER_NOT_EXIST, "用户不存在");
        return users;
    }

    @Override
    public void updateUser(UsersVO user) {
        checkUser(user);
        if(userMapper.selectUserByName(user.getUsername()) != null && userMapper.selectUserByName(user.getUsername()).getId() != user.getId())
            throw new UserException(UsersErrorCode.USER_USERNAME_EXIST, "用户名已存在");

        userMapper.updateUser(user);

    }

    /**
     * 验证字段是否为空
     *
     */
    private void checkUser(UsersDTO user) {
        if(user.getUsername() == null || user.getUsername().isEmpty())
            throw new UserException(UsersErrorCode.USER_USERNAME_EMPTY, "用户名不能为空");

        if(user.getPassword() == null || user.getPassword().isEmpty())
            throw new UserException(UsersErrorCode.USER_PASSWORD_EMPTY, "密码不能为空");
    }



    private void checkUser(UsersVO user) {
        if(user.getUsername() == null || user.getUsername().isEmpty())
            throw new UserException(UsersErrorCode.USER_USERNAME_EMPTY, "用户名不能为空");
    }
}
