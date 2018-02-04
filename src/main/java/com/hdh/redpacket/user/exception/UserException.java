package com.hdh.redpacket.user.exception;

import com.hdh.redpacket.core.exception.BizException;

/**
 * 用户业务相关异常(1000开头)
 */
public class UserException extends BizException {

    /** 用户已存在 */
    public static final UserException USER_ISEXIST_ERROR = new UserException(10000001,"用户已存在");

    /** 用户已存在 */
    public static final UserException USER_NOT_LOGIN_ERROR = new UserException(10000002,"用户未登录");

    /** 参数有误 */
    public static final UserException PARAMS_ERROR = new UserException(10000003,"参数有误");

    /** 邮箱已存在 */
    public static final UserException EMAIL_ISEXIST_ERROR = new UserException(10000004,"邮箱已存在");

    /** 用户已存在 */
    public static final UserException NAME_ISEXIST_ERROR = new UserException(10000005,"用户已存在");


    protected UserException(int code, String msg) {
        super(code, msg);
    }
}
