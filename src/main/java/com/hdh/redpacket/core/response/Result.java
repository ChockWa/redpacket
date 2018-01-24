package com.hdh.redpacket.core.response;

import com.hdh.redpacket.core.utils.BeanUtils;
import com.hdh.redpacket.core.utils.TypeUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 响应返回结果类
 */
public class Result {

    public static final int SUCCESS_CODE = 0;

    // 错误码 0-成功
    private int code = SUCCESS_CODE;

    private String msg = "";

    private Map<String,Object> data = new HashMap<>();

    /**
     * 只返回错误码
     *
     * @param code code
     */
    public Result(Integer code) {
        this.code = code;
    }

    /**
     * 只有错误码和错误信息的
     * @param code
     * @param msg
     */
    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Map<String,Object> getData(){
        return this.data;
    }

    public Object getData(String key){
        return this.data.get(key);
    }

    public Result setData(String key, Object value) {
        if (value == null || value instanceof Map || TypeUtil.isBaseType(value)) {
            this.data.put(key, value);
        } else if (value.getClass().isArray()) {
            this.data.put(key, BeanUtils.beanToMapList((Object[]) value));
        } else if (value instanceof List) {
            this.data.put(key, BeanUtils.beanToMapList((List<?>) value));
        } else {
            this.data.put(key, BeanUtils.beanToMap(value));
        }
        return this;
    }

    public Result setData(Object bean) {
        if (bean != null) {
            BeanUtils.copyToMap(bean, this.getData());
        }
        return this;
    }

    // 成功
    public static Result SUCCESS = new Result(0,"");

    // 失败
    public static Result FAIL(int code,String msg){
        return new Result(code, msg);
    }

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

}
