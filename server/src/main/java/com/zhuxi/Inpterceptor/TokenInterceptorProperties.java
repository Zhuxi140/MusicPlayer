package com.zhuxi.Inpterceptor;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;

@ConfigurationProperties(prefix ="interceptor")
@Data
public class TokenInterceptorProperties {

    private final ArrayList<String> loginExcludePath; // 放行路径

}
