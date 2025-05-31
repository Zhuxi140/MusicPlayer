package com.zhuxi.service;

import com.zhuxi.DTO.MusicDTO;
import com.zhuxi.DTO.PageResult;
import com.zhuxi.VO.MusicVO;

public interface MusicService {

    // 上传音频文件
    MusicVO uploadMusic(String filePath,int uploader_id);

    // 删除音频文件
    void deleteMusic(int id);

    //  分页查询音乐列表
    PageResult<MusicDTO> getMusicListAfterId(Long lastId, int pageSize);



    // 传输音乐
}
