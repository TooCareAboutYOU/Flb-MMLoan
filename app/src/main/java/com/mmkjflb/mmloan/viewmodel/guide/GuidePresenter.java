package com.mmkjflb.mmloan.viewmodel.guide;

import com.mmkjflb.mmloan.model.DataManager;
import com.mmkj.baselibrary.base.RxPresenter;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by Administrator on 2017/8/16.
 */

public class GuidePresenter extends RxPresenter<GuideContract.View> implements GuideContract.Presenter {

	private DataManager mDataManager;

	@Inject
	public GuidePresenter(DataManager mDataManager) {
		this.mDataManager = mDataManager;
	}


	@Override
	public void getGoogleAdvise(Map<String, Object> options) {
		addSubscribe(mDataManager.getGoogleAdvise(options).subscribeOn(AndroidSchedulers.mainThread())
				.subscribe(baseBean -> {
				}, throwable -> {
				}));

	}
}