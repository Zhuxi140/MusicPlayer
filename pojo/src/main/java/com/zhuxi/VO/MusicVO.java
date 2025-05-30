package com.zhuxi.VO;

import lombok.Data;

@Data
public class MusicVO {

    private int id;
    private String title;
    private String singer;
    private String lyric;
    private String composition;
    private String company;
    private String album;
    private String lyric_data;
    private float duration;

}
