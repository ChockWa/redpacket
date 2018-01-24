package com.hdh.redpacket.core.utils;

import java.util.Date;

/**
 * 类型工具
 */
public abstract class TypeUtil {
	/**
	 * 是否是基础类型。如果是，则返回true，否则返回false
	 * 
	 * @param o
	 * @return
	 */
	public static boolean isBaseType(Object o) {
		return (
			   o instanceof Byte || o instanceof Short || o instanceof Integer || o instanceof Long
			|| o instanceof Float || o instanceof Double
			|| o instanceof Character 
			|| o instanceof Boolean 
			|| o instanceof String
			|| o instanceof Date
		);
	}
}
