package com.mmkj.baselibrary.util;

import java.util.List;

/**
 * Author: wendjia
 * Time: 2018\8\1 0001
 * Description:
 */
public class ArrayUtils {


	/**
	 * 是否为空数组.
	 *
	 * @param list the list
	 * @return the boolean
	 */
	public static boolean isEmpty(List list) {
		return list == null || list.size() <= 0;
	}


	/**
	 * 获取元素
	 *
	 * @param <T>  the type parameter
	 * @param list the list
	 * @param i    the
	 * @return the t
	 */
	// 后期这里可以扩展成 支持i为负数的可能性

	public static <T> T getItem(List<T> list, int i) {
		if (isEmpty(list)) {
			return null;
		} else {
			if (i >= list.size()) {
				return null;
			} else {
				return list.get(i);
			}
		}
	}
}
