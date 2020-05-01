package com.mmkj.baselibrary.util;


import android.content.Context;
import android.content.res.AssetManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

public class GsonUtils {
	public static String getJson(Context context, String fileName) {
		StringBuilder stringBuilder = new StringBuilder();
		try {
			AssetManager assetManager = context.getAssets();
			BufferedReader bf = new BufferedReader(new InputStreamReader(assetManager.open(fileName)));
			String line;
			while ((line = bf.readLine()) != null) {
				stringBuilder.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return stringBuilder.toString();
	}
	/**
	 * 将实体转换成json字符串
	 *
	 * @param object 实体类(List或Map)
	 * @return
	 */
	public static String createGsonString(Object object) {
//		Gson gson = new Gson();
		Gson gson = new GsonBuilder().serializeNulls().create();
		String gsonString = gson.toJson(object);
		return gsonString;
	}

	/**
	 * 将json字符串转换成实体类
	 *
	 * @param gsonString
	 * @param cls
	 * @return
	 */

	public static <T> T changeGsonToBean(String gsonString, Class<T> cls) {
//		Gson gson = new Gson();
		Gson gson = new GsonBuilder().serializeNulls().create();
		T t = gson.fromJson(gsonString, cls);
		return t;
	}
	public static <T> List<T> changeGsonToList(String gsonString, Class<T> cls) {
//		Gson gson = new Gson();
		Gson gson = new GsonBuilder().serializeNulls().create();
		List<T> list = gson.fromJson(gsonString, new TypeToken<List<T>>() {
		}.getType());
		return list;
	}


	public static <T> List<Map<String, T>> changeGsonToListMaps(String gsonString) {
		List<Map<String, T>> list = null;
//		Gson gson = new Gson();
		Gson gson = new GsonBuilder().serializeNulls().create();
		list = gson.fromJson(gsonString, new TypeToken<List<Map<String, T>>>() {}.getType());
		return list;
	}

	public static <T> Map<String, T> changeGsonToMaps(String gsonString) {
		Map<String, T> map = null;
//		Gson gson = new Gson();
		Gson gson = new GsonBuilder().serializeNulls().create();
		map = gson.fromJson(gsonString, new TypeToken<Map<String, T>>() {
		}.getType());
		return map;
	}


}
