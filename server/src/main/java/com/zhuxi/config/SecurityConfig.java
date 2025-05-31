package com.zhuxi.config;


import com.zhuxi.Inpterceptor.TokenInterceptorProperties;
import com.zhuxi.utils.Properties.BcryptProperties;
import com.zhuxi.utils.Properties.JwtProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

@Configuration
    @EnableConfigurationProperties({
    JwtProperties.class,
    BcryptProperties.class,
    TokenInterceptorProperties.class
})
public class SecurityConfig {

}
