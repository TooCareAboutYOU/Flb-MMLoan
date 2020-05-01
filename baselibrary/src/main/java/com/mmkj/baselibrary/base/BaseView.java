package com.mmkj.baselibrary.base;

import android.view.View;

/**
 * 基类（在activity中负责显示）
 */
public interface BaseView {
	//loading
	void showLoading(); //刚进入页面的加载

	void hideLoading(); //网络请求成功或失败loading消失

	void loadingEmpty(int emptyImg);

	void loadingError(View.OnClickListener onClickListener); //网络连接失败，提示（比如 点击提交）

	//progress
	void showProgress();//点击提交转圈（不是刚进入的那种）

	void showProgress(int msg);

	void hideProgress();//成功与失败

	void hideProgress(int msg);

	void hideProgress(String msg);

	//toast
	void toast(int msg);

	void toast(String msg);

	//snack
	void snackbar(int msg);

	void snackbar(String msg);

	//neterror
	void onError(int resId);

	void onError();
}
