package com.hdh.redpacket.core.exception;

/**
 * 系统异常(9999开头)
 */
public class SysException extends  BizException {

    /** 创建业务异常新实例失败 */
    public static final BizException NEW_EXCEPTION_INSTANCE_FAILED = new BizException(99990001, "创建业务异常新实例失败");

    protected SysException(int code, String msg) {
        super(code, msg);
    }
}
