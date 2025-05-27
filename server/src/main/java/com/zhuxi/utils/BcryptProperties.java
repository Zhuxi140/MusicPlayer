package com.zhuxi.utils;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "bcrypt")
@Data
public class BcryptProperties {
    private int strength; // 工作因子
}
