package com.hdh.redpacket.core.response;

import lombok.Data;

/**
 * 响应返回结果类
 * @param <T>
 */
@Data
public class Result<T> {

    public static final int SUCCESS_CODE = 0;

    // 错误码 0-成功
    private int code = SUCCESS_CODE;

    private String msg = "";

    private T data;

    /**
     * 只返回错误码
     *
     * @param code code
     */
    public Result(Integer code) {
        this.code = code;
    }

    /**
     * 只有返回数据的(验证成功)
     *
     * @param data data
     */
    public Result(T data) {
        this.data = data;
    }

    /**
     * 只有错误码和错误信息的
     *
     * @param code code
     * @param msg    msg
     */
    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 全部参数
     *
     * @param code code
     * @param msg    msg
     * @param data       data
     */
    public Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    // 成功
    public static Result SUCCESS = new Result(0,"");

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
