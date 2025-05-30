package com.zhuxi.mapper;


import com.zhuxi.DTO.MusicDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface musicMapper {

    //添加歌曲
    void addMusic(MusicDTO musicDTO);

    //分页查询歌曲列表

    /**
     *
     * @param pageNum 分页页码
     * @param pageSize 分页大小
     */
    List<MusicDTO> selectMusicList(int pageNum, int pageSize);
}
