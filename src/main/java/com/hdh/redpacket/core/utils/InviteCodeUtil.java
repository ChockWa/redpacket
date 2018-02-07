package com.hdh.redpacket.core.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 分享码工具类。 根据一个长整形生成一个字符串，或将一个字符串解析成一个长整形
 */
public class InviteCodeUtil {

	/** 所有进制字符串 */
	private static final char[] RadixElements = "abcdefghijkmnpqrstuvwxyz123456789ABCDEFGHIJKLMNPQRSTUVWXYZ"
			.toCharArray();

	/** 岗哨值,用于分割原值和随机值 */
	private static final char Sentry = 'o';
	/** 最小长度 */
	private static final int MinLen = 6;

	private static final Map<Character, Integer> valueMap = new HashMap<Character, Integer>();

	static {
		for (int i = 0; i < RadixElements.length; i++) {
			valueMap.put(RadixElements[i], i);
		}
	}

	/**
	 * 将数字id转化为字符串
	 * 
	 * @param id
	 * @return
	 */
	public static String idToCode(long id) {
		if (id < 0) {
			return "";
		}
		int radix = RadixElements.length;

		StringBuffer result = new StringBuffer();

		// 将数值转化为字符值
		do {
			result.append(RadixElements[(int) (id % radix)]);
			id = id / radix;
		} while (id > 0);
		result.reverse();
		// 随机补齐剩余内容
		if (result.length() < MinLen) {
			// 添加分割哨兵
			result.append(Sentry);
			Random rnd = new Random();
			while (result.length() < MinLen) {
				result.append(RadixElements[rnd.nextInt(radix)]);
			}
		}

		return result.toString();

	}

	/**
	 * 将邀请码字符串转化为数字ID
	 * 
	 * @param code
	 * @return
	 */
	public static long codeToId(String code) {
		if (code == null || code.length() == 0) {
			return 0;
		}
		char[] chars = code.toCharArray();
		long result = 0;
		int radix = RadixElements.length;
		for (int pos = 0; pos < chars.length; pos++) {
			if (chars[pos] == Sentry) {
				break;
			}
			if (valueMap.get(chars[pos]) == null) {
				return 0;
			}
			result = result * radix + valueMap.get(chars[pos]);
		}
		return result;
	}
}