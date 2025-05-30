package com.zhuxi.service.impl;

import com.zhuxi.DTO.MusicDTO;
import com.zhuxi.VO.MusicVO;
import com.zhuxi.mapper.musicMapper;
import com.zhuxi.service.musicService;
import com.zhuxi.utils.AudioDateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class musicServiceImpl implements musicService {
    private final AudioDateUtils audioDateUtils;
    private final musicMapper musicMapper;

    @Autowired
    public musicServiceImpl(AudioDateUtils audioDateUtils,  musicMapper musicMapper){
        this.audioDateUtils = audioDateUtils;
        this.musicMapper = musicMapper;

    }

    /**
     * 上传音乐
     * @param filePath 上传路径
     * @param  uploader_id 上传者id
     * @return 音乐信息
     */
    @Override
    public MusicVO uploadMusic(String filePath,int uploader_id) {
        MusicVO musicVO = new MusicVO();
        if(filePath != null){
            MusicDTO musicDTO = audioDateUtils.extractMetadata(filePath);
            musicDTO.setUploader_id(uploader_id);
            musicMapper.addMusic(musicDTO);
            BeanUtils.copyProperties(musicDTO,musicVO);
            return musicVO;

        }else{
            log.error("上传文件路径为空");
            return null;
        }

    }

    @Override

    public void deleteMusic(int id) {

    }

    @Override
    public MusicVO selectMusicList(int pageNum, int pageSize) {

        return null;
    }
}
