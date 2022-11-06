package com.hiyacate.ffmpeg.entity;

import lombok.Data;

/**
 * @author hiya
 * @date 2022/9/3 19:47
 * @description
 */
@Data
public class R {

    private String code;
    private String message;
    private Object data;

    public static R success(Object data){
        R r = new R();
        r.setCode("200");
        r.setMessage("成功");
        r.setData(data);
        return r;

    }

    public static R error(Object e){
        R r = new R();
        r.setData("400");
        r.setMessage("error");
        r.setData(e);
        return r;
    }

    public static R error(String message){
        R r = new R();
        r.setData("400");
        r.setMessage(message);
        r.setData(null);
        return r;
    }
}
