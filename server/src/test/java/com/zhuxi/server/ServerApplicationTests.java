package com.zhuxi.server;

import com.zhuxi.DTO.MusicDTO;
import com.zhuxi.utils.AudioDateUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ServerApplicationTests {
    private AudioDateUtils audioDateUtils;

    @Autowired
    public ServerApplicationTests(AudioDateUtils audioDateUtils) {
        this.audioDateUtils = audioDateUtils;
    }
    @Test
    void contextLoads() {
        MusicDTO musicDTO = audioDateUtils.extractMetadata("D:/qq音乐/Music/一等情事 - 许一鸣.flac");
        System.out.println(musicDTO);
    }

}
