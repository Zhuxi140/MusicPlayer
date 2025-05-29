package com.zhuxi.utils;


import com.zhuxi.utils.Properties.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;


import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

@Component
public class JwtUtils {

    private final JwtProperties  jwtProperties;


    public JwtUtils(JwtProperties jwtProperties){
        this.jwtProperties = jwtProperties;
    }

    /**
     * 生成token(自定义过期时间)
     *
     * @param claims     自定义属性
     * @param expiration Token有效期(秒)
     * @return 生成的JWT字符串
     */

    public String createToken(Map<String, Object> claims, long expiration) {
        return Jwts.builder()             //  创建jwt的builder对象
                /*.addClaims(claims)       //  添加自定义属性
                .setIssuedAt(new Date(System.currentTimeMillis()))  //  设置token的生成时间
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))  // 设置token的过期时间
                .signWith(SignatureAlgorithm.HS512, jwtProperties.getDefaultSecret())  // 使用HS512算法加密
                .compact();  //  生成token*/
                .claims(claims) //  添加自定义属性
                .issuedAt(new Date(System.currentTimeMillis()))  //  设置token的生成时间
                .expiration(new Date(System.currentTimeMillis() + expiration * 1000)) //  设置token的过期时间
                .signWith(jwtProperties.getSecretKey()) // 使用HS512算法加密
                .compact();
    }

    /**
     * 生成token(使用默认过期时间)
     * @param claims 自定义属性
     * @return 生成的JWT字符串
     */
    public String createTokenDefault(Map<String, Object> claims) {
        return Jwts.builder()             //  创建jwt的builder对象
                /*.addClaims(claims)       //  添加自定义属性
                .setIssuedAt(new Date(System.currentTimeMillis()))  //  设置token的生成时间
                .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getDefaultExpireTime()))  // 设置token的过期时间
                .signWith(SignatureAlgorithm.HS512, jwtProperties.getDefaultSecret())  // 使用HS512算法加密
                .compact();  //  生成token*/
                .claims(claims) //  添加自定义属性
                .issuedAt(new Date(System.currentTimeMillis()))  //  设置token的生成时间
                .expiration(new Date(System.currentTimeMillis() + jwtProperties.getDefaultExpireTime())) //  设置token的过期时间
                .signWith(jwtProperties.getSecretKey()) // 使用HS512算法加密
                .compact();
    }

    /**
     * 解析Token
     * @param token  token
     * @return  解析后的Claims
     */

    public Claims parseToken(String token) {
        return Jwts.parser()                   //  创建jwt的parser对象
                /*.setSigningKey(jwtProperties.getDefaultSecret())
                .build()//  设置密钥
                .parseClaimsJws(token)  //  解析token
                .getBody();  //  获取自定义属性*/

                .verifyWith(jwtProperties.getSecretKey()) //  设置密钥
                .build()  //  构建解析器
                .parseSignedClaims(token) //  解析token
                .getPayload();  //  获取自定义属性
    }

    /**
     * 验证Token有效性
     * @param token  token
     * @return  true:有效  false:无效
     */

    public boolean validateToken(String token){
        return parseToken(token) != null;
    }
}


