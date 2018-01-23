package com.hdh.redpacket.core.exception;

import lombok.Data;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.text.MessageFormat;

/**
 * 通用业务异常基类
 */
@Data
public class BizException extends RuntimeException implements Serializable {

    protected int code;

    protected String msg;

    protected BizException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg == null ? "" : msg;
    }

    public BizException format(Object... args) {
        return this.newInstance(MessageFormat.format(this.msg, args));
    }

    public BizException newInstance(String msg) {
        try {
            Class<?> clazz = this.getClass();
            Constructor<?> constructor;
            constructor = clazz.getDeclaredConstructor(new Class[] { int.class, String.class });
            constructor.setAccessible(true);
            BizException be;
            be = (BizException) constructor.newInstance(this.code, msg);
            return be;
        } catch (Exception e) {
            throw SysException.NEW_EXCEPTION_INSTANCE_FAILED.format("创建业务异常新实例失败");
        }
    }
}
