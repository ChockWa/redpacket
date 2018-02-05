package com.hdh.redpacket.system.exception;

import com.hdh.redpacket.core.exception.BizException;

/**
 * 安全验证相关异常。前缀：1404
 * @author hezhuohua
 *
 */
public class SafeException extends BizException {
	
	private static final long serialVersionUID = -3408100707213417537L;

	/** 参数有误 */
	public static final SafeException PARAMS_ERROR = new SafeException(14040001, "参数有误");
	
	/** 验证码不正确 */
	public static final SafeException VERIFYCAODE_ERROR = new SafeException(14040002, "验证码不正确");
	
	protected SafeException(int code, String msg) {
		super(code, msg);
	}
}
