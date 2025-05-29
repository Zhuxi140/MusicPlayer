package com.zhuxi.utils.Properties;


import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import javax.crypto.SecretKey;


/**
 * jwt 配置类
 */
@ConfigurationProperties(prefix = "jwt")
@Data
public class JwtProperties {

    private  String defaultSecret;
    private  long defaultExpireTime = 60 * 60 * 24 * 30;  // 默认过期时间 30天


    /**
     * 获取密钥
     * @return SecretKey
     */
    public SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(defaultSecret)); // 获取密钥
    }
}
