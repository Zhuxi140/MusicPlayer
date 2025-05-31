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
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
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

    /**
     * 分页查询音乐列表
     * @param lastId  上次查询的id
     * @param pageSize  每页数量
     * @return 分页结果
     */
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

        boolean hasPrevious = lastId != 0L ;

        return new PageResult<>(items, nextCursor, hasMore,  hasPrevious);
    }

    /**
     *  分页查询用户的个人收藏音乐列表
     * @param lastId  上次查询的id
     * @param pageSize  每页数量
     * @param userId  用户id
     * @return 分页结果
     */
    @Override
     public PageResult<MusicDTO> getMusicListByUserId(Long lastId, int pageSize, int userId) {

        if(lastId == null)
            lastId = 0L;

        List<MusicDTO> items = musicMapper.selectMusicListByUser(lastId, pageSize + 1, userId);

         // 获取分页状态
        boolean hasMore = false;
        if(items.size() == pageSize + 1){
             hasMore = true;
              items = items.subList(0, pageSize);
        }

        Long nextCursor = null;
        if(! items.isEmpty())
             nextCursor = items.get(items.size() - 1).getId();

        boolean hasPrevious = lastId != 0L ;

        return new PageResult<>( items, nextCursor, hasMore,  hasPrevious);

     }

    /**
     *  收藏音乐
     * @param musicId  音乐id
     * @param userId  用户id
     */
    @Override
    @Transactional
    public int addFavoriteMusic(int musicId, int userId) {

        if(musicMapper.selectMusicIsFavorite(musicId, userId)){
            log.info("{}的音乐已收藏,执行取消收藏操作", musicId);
            if (musicMapper.deleteFavoriteMusic(musicId, userId)) {
                log.info("取消收藏成功");
                return 1;
            }
        }else{
             log.info("{}的音乐未收藏,执行收藏操作", musicId);
            if (musicMapper.addFavoriteMusic(musicId, userId)) {
                log.info("收藏成功");
                return 2;
            }
        }

        return 0;
    }



}
