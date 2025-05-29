package com.zhuxi.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayHistory {
    private int id;
    private int user_id;
    private int music_id;
    private int play_time;
}
