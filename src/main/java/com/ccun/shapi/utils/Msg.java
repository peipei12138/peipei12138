package com.ccun.shapi.utils;


import java.util.HashMap;
import java.util.Map;

/**
 * 链式方法
 * 返回json数据通用的类
 */
public class Msg {

    private Integer status;

    private String msg;

    private Map<String, Object> extend = new HashMap<>();

    public static Msg success(){
        Msg result = new Msg();
        result.status = 200;
        result.msg  = "success";
        return result;
    }

    public static Msg fail(){
        Msg result = new Msg();
        result.status = 500;
        result.msg  = "fail";
        return result;
    }

    public static Msg fail(String msg){
        Msg result = new Msg();
        result.status = 500;
        result.msg  = msg;
        return result;
    }

    public Msg add(String key, Object value){
        this.getExtend().put(key, value);
        return this;
    }

    public Integer getCode() {
        return status;
    }

    public void setCode(Integer code) {
        this.status = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getExtend() {
        return extend;
    }

    public void setExtend(Map<String, Object> extend) {
        this.extend = extend;
    }
}
