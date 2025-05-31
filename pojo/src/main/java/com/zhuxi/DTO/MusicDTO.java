package com.zhuxi.DTO;


import lombok.Data;

@Data
public class MusicDTO {
    private long id;
    private String title;
    private String singer;
    private String lyric;
    private String composition;
    private String company;
    private String album;
    private String lyricData;
    private float duration;
    private String filePath;
    private int uploaderId;
    private int bitrate;
    private String codeType; // 编码格式
    private String format; // 文件格式

}
