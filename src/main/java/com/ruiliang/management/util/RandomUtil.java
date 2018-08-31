package com.ruiliang.management.util;

import java.util.Random;

public class RandomUtil {

	/**
	 * 生成length位的字母数字组合串
	 * 
	 * @param length
	 * @return
	 */
	public static String getRandomString(int length) {
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	/**
	 * 生成length位的随机数字
	 * 
	 * @param length
	 * @return
	 */
	public static String getRandomNum(int length) {
		String code = null;
		int js = (int) Math.pow(10.0D, length - 1);
		code = String.valueOf((int) ((Math.random() * 9 + 1) * js));
		return code;
	}
}
