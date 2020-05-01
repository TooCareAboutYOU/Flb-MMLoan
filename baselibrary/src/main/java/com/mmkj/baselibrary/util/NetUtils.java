package com.mmkj.baselibrary.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;

import com.mmkj.baselibrary.app.IBaseApplition;


/**
 * Created by Administrator on 2017/7/20.
 */

public class NetUtils {
	/**
	 * 检查是否有可用网络
	 */
	@SuppressLint("MissingPermission")
	public static boolean isNetworkConnected() {
		ConnectivityManager connectivityManager = (ConnectivityManager) IBaseApplition.getiBaseInstance()
				.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
		return connectivityManager.getActiveNetworkInfo() != null;
	}
}
