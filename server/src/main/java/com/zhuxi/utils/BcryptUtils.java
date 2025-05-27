package com.zhuxi.utils;


import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BcryptUtils {

    private final BcryptProperties bcryptProperties;

    @Autowired
    public BcryptUtils(BcryptProperties bcryptProperties) {
        this.bcryptProperties = bcryptProperties;
    }

    /**
     * 密码加密
     * @param password 原始密码
     * @return 加密后的密码
     */
    public  String hashPassword(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt(bcryptProperties.getStrength()));
    }

    /**
     * 密码验证
     * @param password 输入的密码
     * @param hashPassword 数据库中的密码
     * @return 验证结果
     */
    public  boolean checkPassword(String password,  String hashPassword){
        return BCrypt.checkpw(password, hashPassword);
    }
}
