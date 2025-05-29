package com.zhuxi.result;

import lombok.Data;

/*
 *
 * code:200 成功
 * code:0   失败
 *
 * */

@Data
public class Result {
    private Integer code;
    private String msg;
    private Object data;

    public static Result success(){
        Result result = new Result();
        result.setCode(200);
        result.setMsg("success");
        return result;
    }
    public static Result success(String msg){
        Result result = new Result();
        result.setCode(200);
        result.setMsg(msg);
        return result;
    }

    public static Result success(Object data){
        Result result = new Result();
        result.setCode(200);
        result.setMsg("success");
        result.setData(data);
        return result;
    }

    public static Result error(){
        Result result = new Result();
        result.setCode(0);
        result.setMsg("error");
        return result;
    }
    public static Result error(String msg){
        Result result = new Result();
        result.setCode(0);
        result.setMsg(msg);
        return result;
    }
}
