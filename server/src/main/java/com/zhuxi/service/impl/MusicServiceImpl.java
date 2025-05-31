package com.zhuxi.service.impl;

import com.zhuxi.DTO.MusicDTO;
import com.zhuxi.DTO.PageResult;
import com.zhuxi.VO.MusicVO;
import com.zhuxi.mapper.musicMapper;
import com.zhuxi.service.MusicService;
import com.zhuxi.utils.AudioDateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class MusicServiceImpl implements MusicService {
    private final AudioDateUtils audioDateUtils;
    private final musicMapper musicMapper;


    public MusicServiceImpl(AudioDateUtils audioDateUtils, musicMapper musicMapper){
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
            musicDTO.setUploaderId(uploader_id);
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
    public PageResult<MusicDTO> getMusicListAfterId(Long lastId, int pageSize) {
        if(lastId == null)
             lastId = 0L;

        List<MusicDTO> items = musicMapper.selectMusicList(
                lastId,
                pageSize + 1
        );

        // 判断分页状态
        boolean hasMore = false;
        if(items.size() == pageSize + 1){
            hasMore = true;
            items = items.subList(0, pageSize);
        }

        Long nextCursor = null;
        if(! items.isEmpty()){
            nextCursor = items.get(items.size() - 1).getId();
        }

        boolean hasPrevious = lastId != null ;

        return new PageResult<>(items, nextCursor, hasMore,  hasPrevious);
    }
}
