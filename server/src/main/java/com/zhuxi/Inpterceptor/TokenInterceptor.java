package com.zhuxi.Inpterceptor;

import com.zhuxi.result.Result;
import com.zhuxi.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import java.util.ArrayList;
import java.util.Collections;


@Log4j2
@Component
public class TokenInterceptor implements HandlerInterceptor {

    private JwtUtils  jwtUtils;
    private TokenInterceptorProperties tokenInterceptorProperties;


    public TokenInterceptor(JwtUtils jwtUtils, TokenInterceptorProperties tokenInterceptorProperties){
        this.jwtUtils = jwtUtils;
        this.tokenInterceptorProperties = tokenInterceptorProperties;
    }


    /**
     * Token拦截器
     * @param request 请求
     * @param response 响应
     * @param handler 处理器
     * @return 是否放行
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String requestURI = request.getRequestURI();
        log.info("TokenInterceptor处理url:{}", requestURI);

        ArrayList<String> headerNames = Collections.list(request.getHeaderNames());
 /*       for (String headerName : headerNames) {
            log.info("headerName: {} = {}", headerName, request.getHeader(headerName));
        }*/

        //放行登录请求或其他请求
        ArrayList<String> excludePath = tokenInterceptorProperties.getLoginExcludePath();
        if(excludePath.contains(requestURI)&& request.getMethod().equals("POST")){
            log.info("放行请求:{}",requestURI);
            return true;
        }

        //获取token 并验证Token
        String authorization = request.getHeader("Authorization");
        if(authorization != null && authorization.startsWith("Bearer ") ){
                String token = authorization.substring(7);
                if(!jwtUtils.validateToken(token)){
                    log.info("token验证失败");
                }
                log.info("token验证成功");
        }else{
            log.warn("请求未携带token");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); //  401
            return false;
        }

        return true;
    }
}
