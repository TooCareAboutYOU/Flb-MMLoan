package com.mmkj.baselibrary.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/10/29.
 */

public class DateUtils {

	private static final SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
	public static final SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
	public static final SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 格式：2016-06-07
	 */
	public static String showYMDTime(long date) {
		if (date == 0){
			return "";
		}
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Long time = new Long(date);
		String d = format.format(time);
		return d;
	}

	public static String showYMDTime(Date date) {//可根据需要自行截取数据显示
		if (date == null){
			return "";
		}
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(date);
	}

	public static String getYMDHMSTime(Date date) {//可根据需要自行截取数据显示
		if (date == null){
			return "";
		}
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format2.format(date);
	}
	/**
	 * 格式：2016-06-07 20:45:00
	 */
	public static String getYMDHMSTime(long date) {
		if (date == 0){
			return "";
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Long time = new Long(date);
		String d = format.format(time);
		return d;
	}

}
