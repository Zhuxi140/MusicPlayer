package com.zhuxi.exception.userException;


import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserException extends RuntimeException{
        private final UsersErrorCode code; // 枚举定义错误类型

        public UserException(UsersErrorCode code, String message) {
            super(message);
            this.code = code;
        }
}
