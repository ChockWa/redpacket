package com.hdh.redpacket.core.utils;

public class SecrityUtils {
	
	/**
	 * 设置用户加密密码
	 * 
	 * @param salt
	 * @param password
	 */
	public static String md5Pwd(String salt, String password) {
		if (password != null && password.length() > 0) {
			return Md5Util.md5(salt + password + salt);
		}
		return "";
	}
}
