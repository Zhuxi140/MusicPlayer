package com.zhuxi.utils.Properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "bcrypt")
@Data
public class BcryptProperties {
    private int strength; // 工作因子
}
