package com.ruiliang.management.upload;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarUtil {
	public static String getFormatDateTime(Date date, String format) {
		try {
			return new SimpleDateFormat(format).format(date);
		} catch (Exception e) {
		}
		return null;
	}

	public static Date parse(String tm, String format) {
		try {
			return new SimpleDateFormat(format).parse(tm);
		} catch (Exception e) {
		}
		return null;
	}
	
	public static String format(Date d, String format) {
		return new SimpleDateFormat(format).format(d);
	}

	public static int getNowHour() {
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.HOUR_OF_DAY);
	}
}
