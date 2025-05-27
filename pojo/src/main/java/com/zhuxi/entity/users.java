package com.zhuxi.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class users {
    private int id;
    private String username;
    private String password;
    private String avatar;
    private role role;

}
