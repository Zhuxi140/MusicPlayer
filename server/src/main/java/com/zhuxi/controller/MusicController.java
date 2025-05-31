package com.zhuxi.controller;


import com.zhuxi.DTO.MusicDTO;
import com.zhuxi.DTO.PageResult;
import com.zhuxi.result.Result;
import com.zhuxi.service.MusicService;
import com.zhuxi.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jdk.jfr.Unsigned;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/music")
@RestController
@Log4j2
public class MusicController {
    private final JwtUtils jwtUtils;
    private MusicService musicService;


    public MusicController(MusicService musicService, JwtUtils jwtUtils) {
        this.musicService = musicService;
        this.jwtUtils = jwtUtils;
    }

    /**
     * 获取全部音乐列表
     * @param lastId 上次获取的id
     * @param pageSize 每页数量
     * @return 分页结果
     */
    @GetMapping("/MusicList")
    public Result getMusicList(Long lastId,
                               @RequestParam(defaultValue = "10") @Max(100) int pageSize)
    {
        PageResult<MusicDTO> musicListAfterId = musicService.getMusicListAfterId(lastId, pageSize);
        return Result.success(musicListAfterId);
    }

    /**
     *  获取用户收藏列表
     * @param lastId 上次获取的id
     * @param pageSize 每页数量
     * @param token token
     * @return 分页结果
     */
    @GetMapping("/FavoriteList")
    public Result getFavoriteList( Long lastId,
                                  @RequestParam(defaultValue="10") @Max(100) int pageSize,
                                  @RequestHeader("Authorization") String token)
    {
        Claims claims = jwtUtils.parseToken(token.replace("Bearer ", ""));
        int id = Integer.parseInt(claims.get("id").toString());

        PageResult<MusicDTO> musicListByUserId = musicService.getMusicListByUserId(lastId, pageSize, id);
        return Result.success(musicListByUserId);
    }


    //收藏音乐
    @PutMapping("/addFavoriteMusic/{musicId}")
    public Result addFavoriteMusic(@RequestHeader("Authorization") String token,
                                   @PathVariable @Min(value = 1) int musicId){

        Claims claims = jwtUtils.parseToken(token.replace("Bearer ", ""));
        int id = Integer.parseInt(claims.get("id").toString());

        int i = musicService.addFavoriteMusic(musicId, id);
        if(i==1)
            return Result.success("成功取消收藏");
        else if(i == 2)
            return Result.success("成功收藏");
        else if(i == 0)
            return Result.error("收藏失败");

        return Result.error("未知情况！");
    }
}
