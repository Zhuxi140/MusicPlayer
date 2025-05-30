package com.zhuxi.controller;


import com.zhuxi.DTO.UsersDTO;
import com.zhuxi.VO.UsersVO;
import com.zhuxi.result.Result;
import com.zhuxi.service.usersService;
import com.zhuxi.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.zhuxi.service.musicService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/admin")
@Log4j2
public class AdminController {

    private final usersService usersService;
    private final JwtUtils jwtUtils;
    private final musicService musicService;

    @Value("${spring.servlet.multipart.location}")
    private String filePath;

    @Value("${spring.servlet.multipart.max-file-size}")
    private String maxFiles;

    public AdminController(usersService usersService, JwtUtils jwtUtils, musicService musicService) {
        this.usersService = usersService;
        this.jwtUtils = jwtUtils;
        this.musicService = musicService;
    }

    /**
     * 获取用户信息
     * @param token
     * @return
     */
    @GetMapping("/info")
    public Result info(@RequestHeader("Authorization") String token){


        Claims claims = jwtUtils.parseToken(token.replace("Bearer ", ""));
        int id = Integer.parseInt(claims.get("id").toString());
        String username = claims.get("username").toString();
        try {
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


    @PostMapping("/upload")
    public Result uploadUser(@RequestParam("file")MultipartFile[] files, @RequestHeader("Authorization") String token){

        Claims claims = jwtUtils.parseToken(token.replace("Bearer ", ""));
        int id = Integer.parseInt(claims.get("id").toString());

        // 基础验证
        if(files == null || files.length == 0)
            return Result.error("请选择文件");

        long fileSize = 0;
        for(MultipartFile file : files){
            fileSize = + file.getSize();
        }
        // maxFiles为 200MB  将其转换为long类型 即 200*1024*1024
        maxFiles = maxFiles.replace("MB", "");
        long LongMaxFiles = Long.parseLong(maxFiles) * 1024 * 1024;
        if(fileSize > LongMaxFiles)
            return Result.error("文件数量不能超过"+ maxFiles);

        File dir = new File(filePath);
        if(!dir.exists())
            dir.mkdirs();


        List<String> successFiles = new ArrayList<>();
        List<String> emptyFiles = new ArrayList<>();
        List<String> failedFiles = new ArrayList<>();

        Path path = null;
        for (MultipartFile multipartFile : files){
            String FileName = multipartFile.getOriginalFilename();
            if(multipartFile.isEmpty()){
                emptyFiles.add(FileName);
                log.warn("文件为空:{}",FileName);
                continue;
            }
            path = Paths.get(filePath, multipartFile.getOriginalFilename());

            try {
                multipartFile.transferTo(path.toFile());
                successFiles.add(FileName);
                musicService.uploadMusic(path.toString(),id);
            } catch (IOException e) {
                log.error("{}文件上传失败",FileName,e);
                failedFiles.add(FileName + e + "("+ e.getMessage() +")");
            }
        }

        Map<String,Object> result = new HashMap<>();

        result.put("success",successFiles);
        result.put("empty",emptyFiles);
        result.put("failed",failedFiles);

        if(successFiles.isEmpty() && emptyFiles.isEmpty() && !failedFiles.isEmpty()) {
            return Result.error("所有文件上传失败"+ result);
        }
        else if(successFiles.isEmpty() && !emptyFiles.isEmpty() && failedFiles.isEmpty()) {
            return Result.error("所有文件均为空文件"+ result);
        }
        else {
            String message = String.format("上传完成: 成功%d个, 空文件%d个, 失败%d个",
                    successFiles.size(), emptyFiles.size(), failedFiles.size());
            return Result.success(message + result);
        }

    }

}
