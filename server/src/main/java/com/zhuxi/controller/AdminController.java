package com.zhuxi.controller;


import com.zhuxi.DTO.UsersDTO;
import com.zhuxi.VO.UsersVO;
import com.zhuxi.result.Result;
import com.zhuxi.service.usersService;
import com.zhuxi.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/admin")
@Log4j2
public class AdminController {

    private final usersService usersService;
    private final JwtUtils jwtUtils;

    public AdminController(usersService usersService, JwtUtils jwtUtils) {
        this.usersService = usersService;
        this.jwtUtils = jwtUtils;
    }

    /**
     * 获取用户信息
     * @param token
     * @return
     */
    @GetMapping("/info")
    public Result info(@RequestHeader("Authorization") String token){


        Claims claims = jwtUtils.parseToken(token.replace("Bearer ", ""));
        log.info("进入try前-------------------");
        int id = Integer.parseInt(claims.get("id").toString());
        String username = claims.get("username").toString();
        try {
            log.info("进入try-------------------");
            if (claims != null) {
                UsersVO usersVO = usersService.selectUserById(id);
                log.info("userService 执行查询成功");
                log.warn("｛｝获取用户信息成功", usersVO.getUsername());
                return Result.success(usersVO);
            }
        }catch (Exception e){
            log.warn("获取用户信息失败 id:{}  username:{}", id, username);
            return Result.error(e.getMessage());
        }

        return Result.error();
    }


    /**
     * 注册用户
     * @param userDTO
     * @return
     */
    @PostMapping("/register")
    public Result addUser(@RequestBody  UsersDTO userDTO){
        usersService.addUser(userDTO);
        return Result.success();
    }
}
