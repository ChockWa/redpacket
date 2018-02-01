package com.hdh.redpacket.core.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 基础类型转换工具
 */
public abstract class ConvertUtil {
	
	public static Integer toInt(Object val) {
		if (val == null) {
			return null;
		}
		if (val instanceof Integer) {
			return (Integer) val;
		}
		try {
			return Integer.parseInt(val.toString());
		} catch (Exception e) {
			return null;
		}
	}
	
	public static Double toDouble(Object val) {
		if (val == null) {
			return null;
		}
		if (val instanceof Double) {
			return (Double) val;
		}
		try {
			return Double.parseDouble(val.toString());
		} catch (Exception e) {
			return null;
		}
	}
	
	public static Float toFloat(Object val) {
		if (val == null) {
			return null;
		}
		if (val instanceof Float) {
			return (Float) val;
		}
		try {
			return Float.parseFloat(val.toString());
		} catch (Exception e) {
			return null;
		}
	}

	public static Boolean toBoolean(Object val) {
		if (val == null) {
			return null;
		}
		if (val instanceof Boolean) {
			return (Boolean) val;
		}
		try {
			return Boolean.parseBoolean(val.toString());
		} catch (Exception e) {
			return null;
		}
	}
	
	
	public static Byte toByte(Object val) {
		if (val == null) {
			return null;
		}
		if (val instanceof Byte) {
			return (Byte) val;
		}
		try {
			return Byte.parseByte(val.toString());
		} catch (Exception e) {
			return null;
		}
	}
	
	public static Character toChar(Object val) {
		if (val == null) {
			return null;
		}
		if (val instanceof Character) {
			return (Character) val;
		}
		try {
			return val.toString().charAt(0);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static Short toShort(Object val) {
		if (val == null) {
			return null;
		}
		if (val instanceof Short) {
			return (Short) val;
		}
		try {
			return Short.parseShort(val.toString());
		} catch (Exception e) {
			return null;
		}
	}

	public static Long toLong(Object val) {
		if (val == null) {
			return null;
		}
		if (val instanceof Long) {
			return (Long) val;
		}
		try {
			return Long.parseLong(val.toString());
		} catch (Exception e) {
			return null;
		}
	}
	
	public static String toStr(Object val) {
		if (val == null) {
			return null;
		}
		return val.toString();
	}
	
	/**
	 * <pre>
	 * 将对象转化为列表
	 * 1、如果value是空对象，则返回长度为0的列表。
	 * 2、如果value是一个数组，则将数组中的每个对象放入列表中。
	 * 3、如果value是一个列表，则直接返回列表
	 * 4、如果value是一个普通对象，则将对象放入到列表的第一个位置。列表长度为1；
	 * </pre>
	 * @param value 数组或者对象
	 * @param t 列表中存放的对象类型
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> toList(Object value, Class<T> t) {
		if (value == null) {
			return new ArrayList<T>();
		}
		if (value instanceof List<?>) {
			return ((List<T>) value);
		}
		try {
			if (value.getClass().isArray()) {
				T[] tmpResult = (T[]) value;
				List<T> result = new ArrayList<T>(tmpResult.length);
				for (T e : tmpResult) {
					result.add(e);
				}
				return result;
			}
			else {
				List<T> ret = new ArrayList<T>();
				ret.add((T) value);
				return ret;
			}
		} catch (Throwable e) {
			return new ArrayList<T>();
		}
	}
	
}
