package com.hflh.demo.util;
public class StringUtils {


	/**
	 * 判断字符串是否为空 为空即true
	 *
	 * @param str 字符串
	 * @return
	 */
	public static boolean isNullString(String str) {
		return str == null || str.length() == 0 || "null".equals(str);
	}

}