package com.zhuxi.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 音乐表类
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class music {

    private int id;
    private String title;
    private String singer;
    private String lyric;
    private String composition;
    private String producer;
    private String company;
    private String album;
    private int duration;
    private String file_path;
    private int uploader_id;
    private int bitrate;
}
