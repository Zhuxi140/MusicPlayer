package com.zhuxi.mapper;


import com.zhuxi.DTO.MusicDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface musicMapper {

    //添加歌曲
    void addMusic(MusicDTO musicDTO);

    //分页查询歌曲列表


        /**
         * 分页查询音乐列表
         * @param lastId 上一次查询的最后一条数据的id
         * @param pageSize 每页大小
         */
        List<MusicDTO> selectMusicList(@Param("lastId") Long lastId,
                                       @Param("pageSize") int pageSize);

}
