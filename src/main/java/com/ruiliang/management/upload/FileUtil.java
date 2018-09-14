package com.ruiliang.management.upload;

public class FileUtil {

	/**
	 * 返回文件的扩展名
	 */
	public static String getExtention(String filename) {
		int pos = filename.lastIndexOf(".");
		return filename.substring(pos + 1);
	}

	/**
	 * 特殊字符转
	 */
	public static String htmlEncode(String str) {
		if (str == null) {
			str = "";
			return str;
		}
		str = str.trim(); // 去掉首尾空格
		/*
		 * // 正则表达式替换 // 定义替换模板 Pattern pattern = Pattern.compile("\\"); // 定义替换的原始主字符串
		 * Matcher matcher = pattern.matcher(str); str = matcher.replaceAll("/");
		 */
		str = replace(str, "\\", "/");
		return str;
	}

	/**
	 * 字符串转换方法
	 * 
	 * @param sourceString
	 * @param oldString
	 * @param newString
	 * @return String
	 */
	public static String replace(String source, String oldString, String newString) {

		StringBuffer output = new StringBuffer();

		int lengthOfsource = source.length(); // 源字符串长度
		int lengthOfold = oldString.length(); // 老字符串长度

		int posStart = 0; // 开始搜索位置
		int pos; // 搜索到的老字符串的位置

		// source.indexOf(oldString,posStart)检索某子串在字符串postStart以后第一次出现的位置,如果未找到就返回一个-1。
		while ((pos = source.indexOf(oldString, posStart)) >= 0) { // 得到字符串的位置(eg:如果有<br>就执行，没有就跳出，不要处理。)

			// 将以posStart起始以pos-1结束之间的内容拷贝到另一个字符串中。因为posStar从0开始的。
			output.append(source.substring(posStart, pos)); // append方法将文本添加到当前StringBuffer对象内容的结尾。
			output.append(newString); // 替换成新字符串

			posStart = pos + lengthOfold; // 位置也变为找到了之后的位置,pos为得到第一次出现字符的位置，lengthold为字符的长度
		}

		if (posStart < lengthOfsource) {
			// source.substring(posStart)以lengthOfsource开始的字符串拷贝到列一个字符串中
			output.append(source.substring(posStart));
		}
		// 这个方法将其内容转换成一个可以被用于输出的字符串对象。它允许操作对应的文本用于输出或数据存储。
		return output.toString();
	}

}
