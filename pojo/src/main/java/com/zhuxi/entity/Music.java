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
public class Music {

    private long id;
    private String title; // 标题
    private String singer; // 歌手
    private String lyric; //   作词
    private String composition; // 作曲
    private String company; // 唱片公司
    private String album; //  专辑
    private String lyric_data; // 歌词文件路径
    private float duration; // 时长
    private String file_path; // 文件路径
    private int uploader_id; // 上传者id
    private int bitrate; // 比特率
    private String codeType; // 编码格式
    private String format; // 文件格式
}
