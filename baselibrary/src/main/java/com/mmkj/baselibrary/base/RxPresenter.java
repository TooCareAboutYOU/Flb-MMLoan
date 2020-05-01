package com.mmkj.baselibrary.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * 基于Rx的Presenter封装,控制订阅的生命周期
 */
public class RxPresenter<V extends BaseView> implements BasePresenter<V> {
	protected V mView;
	private CompositeDisposable mCompositeDisposable;

	@Override
	public void attachView(V view) {
		this.mView = view;
	}

	@Override
	public void detachView() {
		this.mView = null;
		unSubscribe();
	}

	private void unSubscribe() {
		if (mCompositeDisposable != null) {
			mCompositeDisposable.clear();
		}
	}

	protected void addSubscribe(Disposable subscription) {
		if (mCompositeDisposable == null) {
			mCompositeDisposable = new CompositeDisposable();
		}
		mCompositeDisposable.add(subscription);
	}
}
