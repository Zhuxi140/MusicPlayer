package com.zhuxi.controller;


import com.zhuxi.DTO.UsersDTO;
import com.zhuxi.VO.UsersVO;
import com.zhuxi.result.Result;
import com.zhuxi.service.UsersService;
import com.zhuxi.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.zhuxi.service.MusicService;

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

    private final UsersService usersService;
    private final JwtUtils jwtUtils;
    private final MusicService musicService;

    @Value("${spring.servlet.multipart.location}")
    private String filePath;

    @Value("${spring.servlet.multipart.max-file-size}")
    private String maxFiles;

    @Value("${spring.servlet.multipart.max-request-size}")
    private String sunMaxFile;



    public AdminController(UsersService usersService, JwtUtils jwtUtils, MusicService musicService) {
        this.usersService = usersService;
        this.jwtUtils = jwtUtils;
        this.musicService = musicService;
    }

    /**
     * 获取用户信息
     * @param token  token
     * @return r
     */
    @GetMapping("/info")
    public Result info(@RequestHeader("Authorization") String token){


        Claims claims = jwtUtils.parseToken(token.replace("Bearer ", ""));
        int id = Integer.parseInt(claims.get("id").toString());
        String username = claims.get("username").toString();
        try {
            UsersVO usersVO = usersService.selectUserById(id);
            log.info("userService 执行查询成功");
            log.warn("{}获取用户信息成功", usersVO.getUsername());
            return Result.success(usersVO);
        }catch (Exception e){
            log.warn("获取用户信息失败 id:{}  username:{}", id, username);
            return Result.error(e.getMessage());
        }

    }


    /**
     * 注册用户
     * @param userDTO  用户信息
     * @return a
     */
    @PostMapping("/register")
    public Result addUser(@RequestBody  UsersDTO userDTO){
        usersService.addUser(userDTO);
        return Result.success();
    }


    /**
     * 上传音乐文件
     * @param files   文件
     * @param token   token
     * @return  Result
     */
    @PostMapping("/upload")
    public Result uploadUser(@RequestParam("file")MultipartFile[] files, @RequestHeader("Authorization") String token) {

        Claims claims = jwtUtils.parseToken(token.replace("Bearer ", ""));
        int id = Integer.parseInt(claims.get("id").toString());

        // 基础验证
        if (files == null || files.length == 0)
            return Result.error("请选择文件");

        long fileSize = 0;
        maxFiles = maxFiles.replace("MB", "");
        long MaxSize = Long.parseLong(maxFiles);
        //  校检有效大小的音乐文件
        int[] fileTag = new int[files.length];
        int tag = 0;
        for (int i = 0; i < files.length;  i++) {
            tag++;
            fileSize += files[i].getSize();
            if (files[i].getSize() < 20 * 1024 * 1024) {
                fileTag[tag] = 1;
            } else if (files[i].getSize() > MaxSize * 1024 * 1024)
                fileTag[tag] = 2;
            else
                fileTag[tag] = 0;
        }

        sunMaxFile = sunMaxFile.replace("MB", "");
        long LongMaxFiles = Long.parseLong(sunMaxFile) * 1024 * 1024;
        if (fileSize > LongMaxFiles)
            return Result.error("文件总大小不能超过" + sunMaxFile + "MB");

        File dir = new File(filePath);
        if (!dir.exists()) {
            boolean mkdirs = dir.mkdirs();
            if (!mkdirs) {
                log.error("音乐存储路径创建失败");
                return Result.error("音乐存储路径创建失败");
            }
        }


        List<String> successFiles = new ArrayList<>();
        List<String> emptyFiles = new ArrayList<>();
        List<String> failedFiles = new ArrayList<>();
        List<String> tooSmallFile = new ArrayList<>();
        List<String> tooLargeFile = new ArrayList<>();


        Path path = null;
        int fileNumber = 0;
        for (MultipartFile multipartFile : files) {
            String FileName = multipartFile.getOriginalFilename();
            // 记录空文件
            if (multipartFile.isEmpty()) {
                emptyFiles.add(FileName);
                log.warn("文件为空:{}", FileName);
                continue;
            }

            // 记录过小文件
            if (fileTag[fileNumber] == 1) {
                log.warn("文件过小:{}", FileName);
                tooSmallFile.add(FileName);
                continue;
            }

            //记录过大文件
            if (fileTag[fileNumber] == 2) {
                log.warn("文件过大:{}", FileName);
                tooLargeFile.add(FileName);
                continue;
            }

            path = Paths.get(filePath, multipartFile.getOriginalFilename());

            try {
                multipartFile.transferTo(path.toFile());
                successFiles.add(FileName);
                musicService.uploadMusic(path.toString(), id);
            } catch (IOException e) {
                log.error("{}文件上传失败", FileName, e);
                failedFiles.add(FileName + e + "(" + e.getMessage() + ")");
            }
        }

        Map<String, Object> result = new HashMap<>();

        result.put("success", successFiles);
        result.put("empty", emptyFiles);
        result.put("failed", failedFiles);

        if (successFiles.isEmpty()) {
            if (!emptyFiles.isEmpty()) {
                return Result.error("所有音乐均为空文件" + result);
            } else if (!tooLargeFile.isEmpty() || !tooSmallFile.isEmpty()) {
                return Result.error("所有音乐均过大或过小" + result);
            } else {
                return Result.error("所有音乐上传失败" + result);
            }
        } else {
            String message = String.format("上传完成: 成功%d个, 空文件%d个, 失败%d个, 过大%d个, 过小%d个",
                    successFiles.size(), emptyFiles.size(), failedFiles.size(),
                    tooLargeFile.size(), tooSmallFile.size());
            return Result.success(message + result);
        }
    }
}
