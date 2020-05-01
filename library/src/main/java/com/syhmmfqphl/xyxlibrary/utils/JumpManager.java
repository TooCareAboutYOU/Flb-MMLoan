package com.syhmmfqphl.xyxlibrary.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Administrator on 2016/8/29.
 * 跟ui有关的基本工具类
 */
public class JumpManager {

	/**
	 * 跳转不销毁页面
	 */
//	public static void jumpTo(Activity from, Class clzz) {
//		jumpToClose(from, clzz, false);
//	}

	/**
	 * 切记只使用与activity，不能用于fragment 不然fragment中onActivityResult无效
	 * @param from
	 * @param clzz
	 * @param callbackKey
	 */
//	public static void jumpToForResult(Activity from, Class clzz,int callbackKey) {
//		from.startActivityForResult(new Intent(from,clzz),callbackKey);
//	}
	/**
	 * 跳转页面销毁
	 */
//	public static void jumpToClose(Activity from, Class clzz) {
//		jumpToClose(from, clzz, true);
//	}

	/**
	 * 跳转页面并关闭当前页面
	 */
//	public static void jumpToClose(Activity from, Class clzz, boolean isFinish) {
//		Intent startIntent = new Intent(from, clzz);
//		from.startActivity(startIntent);
//		if (isFinish) {
//			from.finish();
//		}
//	}
	/**
	 * 跳转不销毁页面传值1个 (传值一个比较多)
	 */
//	public static void jumpToKey1Close(Activity from, Class clzz, Object keyone) {
//		Intent startIntent = new Intent(from, clzz);
//		if (keyone instanceof String) {
//			startIntent.putExtra(ExtraKeys.Key_Msg1, (String) keyone);
//		} else if (keyone instanceof Integer) {
//			startIntent.putExtra(ExtraKeys.Key_Msg1, (Integer) keyone);
//		} else if (keyone instanceof Boolean) {
//			startIntent.putExtra(ExtraKeys.Key_Msg1, (Boolean) keyone);
//		}
//		from.startActivity(startIntent);
//		from.finish();
//	}
	/**
	 * 跳转不销毁页面传值1个 (传值一个比较多)
	 */
//	public static void jumpToKey1(Activity from, Class clzz, Object keyone) {
//		Intent startIntent = new Intent(from, clzz);
//		if (keyone instanceof String) {
//			startIntent.putExtra(ExtraKeys.Key_Msg1, (String) keyone);
//		} else if (keyone instanceof Integer) {
//			startIntent.putExtra(ExtraKeys.Key_Msg1, (Integer) keyone);
//		} else if (keyone instanceof Boolean) {
//			startIntent.putExtra(ExtraKeys.Key_Msg1, (Boolean) keyone);
//		}
//		from.startActivity(startIntent);
//	}
	/**
	 * 传值
	 */
//	public static void jumpToKey12(Activity from, Class clzz, Object keyone,Object keytwo) {
//		Intent startIntent = new Intent(from, clzz);
//		if (keyone instanceof String) {
//			startIntent.putExtra(ExtraKeys.Key_Msg1, (String) keyone);
//		} else if (keyone instanceof Integer) {
//			startIntent.putExtra(ExtraKeys.Key_Msg1, (Integer) keyone);
//		} else if (keyone instanceof Boolean) {
//			startIntent.putExtra(ExtraKeys.Key_Msg1, (Boolean) keyone);
//		}
//		if (keytwo instanceof String) {
//			startIntent.putExtra(ExtraKeys.Key_Msg2, (String) keytwo);
//		} else if (keytwo instanceof Integer) {
//			startIntent.putExtra(ExtraKeys.Key_Msg2, (Integer) keytwo);
//		} else if (keytwo instanceof Boolean) {
//			startIntent.putExtra(ExtraKeys.Key_Msg2, (Boolean) keytwo);
//		}
//		from.startActivity(startIntent);
//	}
	/**
	 * 传值
	 */
//	public static void jumpToKey12Close(Activity from, Class clzz, Object keyone,Object keytwo) {
//		Intent startIntent = new Intent(from, clzz);
//		if (keyone instanceof String) {
//			startIntent.putExtra(ExtraKeys.Key_Msg1, (String) keyone);
//		} else if (keyone instanceof Integer) {
//			startIntent.putExtra(ExtraKeys.Key_Msg1, (Integer) keyone);
//		} else if (keyone instanceof Boolean) {
//			startIntent.putExtra(ExtraKeys.Key_Msg1, (Boolean) keyone);
//		}
//		if (keytwo instanceof String) {
//			startIntent.putExtra(ExtraKeys.Key_Msg2, (String) keytwo);
//		} else if (keytwo instanceof Integer) {
//			startIntent.putExtra(ExtraKeys.Key_Msg2, (Integer) keytwo);
//		} else if (keytwo instanceof Boolean) {
//			startIntent.putExtra(ExtraKeys.Key_Msg2, (Boolean) keytwo);
//		}
//		from.startActivity(startIntent);
//		from.finish();
//	}


	/**
	 * 页面跳转
	 */
//	public static void jumpActivity(Context context, Intent it) {
//		context.startActivity(it);
//	}
}
