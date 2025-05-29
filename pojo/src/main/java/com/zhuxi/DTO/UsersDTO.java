package com.zhuxi.DTO;


import com.zhuxi.entity.role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UsersDTO {
    private int id;
    private String username;
    private String password;
    private role role;
    private String avatar;
    private String token;
}
