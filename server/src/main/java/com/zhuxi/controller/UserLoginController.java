package com.zhuxi.controller;


import com.zhuxi.DTO.UsersDTO;
import com.zhuxi.VO.UsersVO;
import com.zhuxi.mapper.usersMapper;
import com.zhuxi.result.Result;
import com.zhuxi.service.UsersService;
import com.zhuxi.utils.JwtUtils;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
@Log4j2
public class UserLoginController {

    private final UsersService usersService;

    public UserLoginController(UsersService usersService, JwtUtils jwtUtils, usersMapper usersMapper) {
        this.usersService = usersService;
    }

    /**
     * 登录
     * @param usersDTO
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestBody UsersDTO usersDTO, HttpServletResponse response){
        try {
            UsersVO login = usersService.login(usersDTO);
            String token = login.getToken();
            if(token != null && !token.isEmpty()){
                response.setHeader("Authorization","Bearer " + token);  // 设置响应头
            }
            log.warn("{}登录成功 Token{}",login.getUsername(),(token != null ? "已生成" : "未生成"));
            return Result.success(login);
        }catch (Exception e){
            log.warn("{}登录失败",usersDTO.getUsername());
            return Result.error(e.getMessage());
        }

    }

}
