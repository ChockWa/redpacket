package com.hdh.redpacket.core.utils;

import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Md5工具类。<br>
 * 注意，所有返回的MD5字符串为小写字母。
 *
 * @Description Md5工具类
 */
public class Md5Util {

	/**
	 * 生成MD5，默认采用UTF-8编码处理字符串。<br>
	 * 如果失败，返回空字符串
	 * 
	 * @param value
	 * @return
	 */
	public static String md5(String value) {
		return md5(value, "UTF-8");
	}

	/**
	 * 生成MD5字符创
	 * 
	 * @param value 原值
	 * @param charsetName 字符串编码格式
	 * @return
	 */
	public static String md5(String value, String charsetName) {
		try {
			byte[] strTemp = value.getBytes(charsetName);
			return ByteUtils.bytesToHexString(md5(strTemp));
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 生成 MD5 摘要.如果生成失败，返回：new byte[] {}
	 * 
	 * @param value
	 * @return
	 */
	public static byte[] md5(byte[] value) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(value);
			return md.digest();
		} catch (NoSuchAlgorithmException e) {
			return new byte[] {};
		}
	}

	/**
	 * 对流生成MD5摘要。如果失败，返回空字符串;
	 * 
	 * @param stream
	 * @return
	 */
	public String md5(InputStream stream) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] buffer = new byte[524288]; //512K
			int length;
			while ((length = stream.read(buffer)) != -1) {
				md.update(buffer, 0, length);
			}
			return ByteUtils.bytesToHexString(md.digest());
		} catch (Exception e) {
			return "";
		}
	}
}