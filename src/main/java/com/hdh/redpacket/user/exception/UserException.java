package com.hdh.redpacket.user.exception;

import com.hdh.redpacket.core.exception.BizException;

/**
 * 用户业务相关异常(1000开头)
 */
public class UserException extends BizException {

    /** 用户已存在 */
    public static final UserException USER_ISEXIST_ERROR = new UserException(10000001,"用户已存在");

    protected UserException(int code, String msg) {
        super(code, msg);
    }
}
