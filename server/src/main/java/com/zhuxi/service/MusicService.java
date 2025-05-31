package com.zhuxi.service;

import com.zhuxi.DTO.MusicDTO;
import com.zhuxi.DTO.PageResult;
import com.zhuxi.VO.MusicVO;

public interface MusicService {

    // 上传音频文件
    MusicVO uploadMusic(String filePath,int uploader_id);

    // 删除音频文件
    void deleteMusic(int id);

    //  分页查询全部音乐列表
    PageResult<MusicDTO> getMusicListAfterId(Long lastId, int pageSize);


    // 查询个人喜欢/收藏歌曲列表
    PageResult<MusicDTO> getMusicListByUserId(Long lastId, int pageSize, int userId);

    // 收藏音乐
    int addFavoriteMusic(int musicId, int userId);

    // 传输音乐
}
