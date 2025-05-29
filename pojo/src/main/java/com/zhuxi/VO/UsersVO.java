package com.zhuxi.VO;

import com.zhuxi.entity.role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersVO {
    private int id;
    private String username;
    private String avatar;
    private role role;
    private String token;



   /* public static UsersVO  formUsers(Users user) {
        return new UsersVO(user.getId(), user.getUsername(), user.getAvatar(), user.getRole(), null);
    }*/
}
