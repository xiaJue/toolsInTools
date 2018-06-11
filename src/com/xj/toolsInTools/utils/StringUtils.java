package com.xj.toolsInTools.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class StringUtils {
	public static String  getNowDate() {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(System.currentTimeMillis()); 
		Date date = c.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-hh:mm:ss");
		return sdf.format(date);
	}
}
