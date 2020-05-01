package com.mmkj.baselibrary.base;

/**
 * Presenter基类
 */
public interface BasePresenter<V extends BaseView> {
	void attachView(V view);

	void detachView();
}
