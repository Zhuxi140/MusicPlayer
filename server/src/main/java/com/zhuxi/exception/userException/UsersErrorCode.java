package com.zhuxi.exception.userException;


/**
 * 用户错误码
 * @author zhuxi
 * @date 2021/12/28 16:04
 */

public enum UsersErrorCode {
    USER_NOT_EXIST(1001,"用户不存在"),
    USER_PASSWORD_ERROR(1003,"用户名或密码错误"),
    USER_PASSWORD_ERROR_TIMES(1004,"密码错误次数过多，请稍后再试"),
    USER_USERNAME_EMPTY(1005,"用户名不能为空"),
    USER_PASSWORD_EMPTY(1006,"密码不能为空"),
    USER_USERNAME_EXIST(1007,"用户名已存在"),
    USER_USERNAME_NOT_EXIST(1008,"用户名不存在");


    private int code;
    private String message;

    UsersErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
