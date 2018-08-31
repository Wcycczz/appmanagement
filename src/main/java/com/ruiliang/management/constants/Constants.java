package com.ruiliang.management.constants;

public class Constants {
	public static final String SESSION_KEY = "loginUser";
	public static final String PAGE_SIZE = "10";
	public static final String PAGE_INDEX = "0";

	public static final String URL_IMAGE_SERVER = "http://image.rl-telecom.com";

	public static String getImageUrl(String path) {
		return URL_IMAGE_SERVER.concat(path);
	}
}
