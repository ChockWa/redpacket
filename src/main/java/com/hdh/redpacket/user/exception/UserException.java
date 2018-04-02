package com.hdh.redpacket.user.exception;

import com.hdh.redpacket.core.exception.BizException;

/**
 * 用户业务相关异常(1000开头)
 */
public class UserException extends BizException {

    /** 用户已存在 */
    public static final UserException USER_ISEXIST_ERROR = new UserException(10000001,"用户不存在");

    /** 用户未登录 */
    public static final UserException USER_NOT_LOGIN_ERROR = new UserException(10000002,"用户未登录");

    /** 参数有误 */
    public static final UserException PARAMS_ERROR = new UserException(10000003,"参数有误");

    /** 邮箱已存在 */
    public static final UserException EMAIL_ISEXIST_ERROR = new UserException(10000004,"邮箱已存在");

    /** 用户已存在 */
    public static final UserException NAME_ISEXIST_ERROR = new UserException(10000005,"用户已存在");

    /** 用户状态不正常,不能进行登录 */
    public static final UserException USER_STATE_ERROR = new UserException(10000006,"用户状态不正常,不能进行登录");

    /** 登录密码错误 */
    public static final UserException LOGIN_PASSWORD_ERROR = new UserException(10000007,"登录密码错误");

    /** 邀请码所属用户不存在 */
    public static final UserException INVITECODE_USER_NOT_EXIST = new UserException(10000008,"邀请码所属用户不存在");


    protected UserException(int code, String msg) {
        super(code, msg);
    }
}
