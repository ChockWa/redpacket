package com.hdh.redpacket.common.exception;

import com.hdh.redpacket.core.exception.BizException;

/**
 * 系统异常(9998开头)
 */
public class CommonException extends  BizException {

    /** 调用方法不存在 */
    public static final BizException METHOD_NOT_FIND_ERROR = new CommonException(99980001, "调用方法不存在");

    /** 用户未登录 */
    public static final BizException USER_NOT_LOGIN_ERROR = new CommonException(99980002, "用户未登录");

    /** token过期 */
    public static final BizException TOKEN_EXPIRE = new CommonException(99980003, "token过期");

    protected CommonException(int code, String msg) {
        super(code, msg);
    }
}
