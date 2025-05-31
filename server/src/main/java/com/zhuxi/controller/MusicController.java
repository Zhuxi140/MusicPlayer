package com.zhuxi.controller;


import com.zhuxi.DTO.MusicDTO;
import com.zhuxi.DTO.PageResult;
import com.zhuxi.result.Result;
import com.zhuxi.service.MusicService;
import jakarta.validation.constraints.Max;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/music")
@RestController
@Log4j2
public class MusicController {
    private MusicService musicService;


    public MusicController(MusicService musicService) {
        this.musicService = musicService;
    }

    //分页查询获取音乐列表
    @GetMapping("/MusicList")
    public Result getMusicList(Long lastId, @RequestParam(defaultValue = "10") @Max(100) int pageSize) {
        PageResult<MusicDTO> musicListAfterId = musicService.getMusicListAfterId(lastId, pageSize);
        return Result.success(musicListAfterId);
    }
}
