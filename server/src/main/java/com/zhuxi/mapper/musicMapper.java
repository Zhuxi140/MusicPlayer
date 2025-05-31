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


    //分页查询音乐列表
    List<MusicDTO> selectMusicList(@Param("lastId") Long lastId,
                                   @Param("pageSize") int pageSize);

    //分页查询个人收藏音乐列表
    List<MusicDTO> selectMusicListByUser(@Param("lastId") Long lastId,
                                         @Param("pageSize") int pageSize,
                                         @Param("userId")  int userId);

    // 收藏音乐
    boolean addFavoriteMusic(@Param("musicId")  int musicId,  @Param("userId")  int userId);

    // 查询音乐是否已被收藏
    boolean selectMusicIsFavorite(@Param("musicId")  int musicId,  @Param("userId")  int userId);

    // 删除收藏的音乐
    boolean deleteFavoriteMusic(@Param("musicId")  int musicId,  @Param("userId")  int userId);
}
