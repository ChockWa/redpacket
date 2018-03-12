package com.hdh.redpacket.core.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 随机字符串构造器
 */
public class RandomUtils {
	public static final String UpperLetter = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String LowerLetter = "abcdefghijklmnopqrstuvwxyz";
	public static final String DigitLetter = "0123456789";
	public static final String UpperAndLower = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	public static final String UpperAndDigit = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	public static final String LowerAndDigit = "abcdefghijklmnopqrstuvwxyz0123456789";
	public static final String AllLetter = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	
	
	private String codeChars = AllLetter;

	/**
	 * 默认随机字符集合 A-Za-z0-9
	 */
	public RandomUtils() { }
	
	public RandomUtils(String randomCharSet) {
		if (randomCharSet != null && !randomCharSet.equals("")) {
			this.codeChars = randomCharSet;
		}
	}

	/**
	 * 产生随机字符串
	 * @param len 产生的随机字符串长度
	 * @return
	 */
	public String generate(int len) {
		StringBuilder result = new StringBuilder(len);
		char[] chars = codeChars.toCharArray();
		Random rnd = new Random();
		for (int i = 0; i < len; i++) {			
			result.append(String.valueOf(chars[rnd.nextInt(chars.length)]));
		}
		return result.toString();
	}

	/**
	 * 根据时间获取唯一的场次编号
	 * @return
	 */
	public static String getPlayNoByTime() {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
		String newDate=sdf.format(new Date());
		String result="";
		Random random=new Random();
		for(int i=0;i<3;i++){
			result+=random.nextInt(10);
		}
		return newDate+result;
	}
}
