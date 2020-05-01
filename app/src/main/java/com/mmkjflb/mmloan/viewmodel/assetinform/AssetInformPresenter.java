package com.mmkjflb.mmloan.viewmodel.assetinform;

import com.mmkjflb.mmloan.R;
import com.mmkjflb.mmloan.model.DataManager;
import com.mmkj.baselibrary.base.RxPresenter;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by Administrator on 2017/8/10.
 */

public class AssetInformPresenter extends RxPresenter<AssetInformContract.View> implements AssetInformContract.Presenter {
	private DataManager mDataManager;

	@Inject
	public AssetInformPresenter(DataManager mDataManager) {
		this.mDataManager = mDataManager;
	}

	@Override
	public void assertsinfo(Map<String, Object> partMap) {
		addSubscribe(mDataManager.assertsinfo(partMap)
				.subscribe(baseBean -> {
					mView.assertsinfoSuc(baseBean);
					mView.hideProgress();
				}, throwable -> mView.hideProgress(R.string.neterror_tryagain)));
	}

	@Override
	public void getAssertsInfo(String sessionId) {
		addSubscribe(mDataManager.getAssertsInfo(sessionId).doOnSubscribe(subscription -> mView.showProgress()).subscribeOn(AndroidSchedulers.mainThread())
				.subscribe(baseBean -> {
					mView.getAssertsInfoSuc(baseBean);
					mView.hideProgress();
				}, throwable -> mView.hideProgress()));
	}
	@Override
	public void getQiniuToken(String sessionId) {
		addSubscribe(mDataManager.getQiniuToken(sessionId).doOnSubscribe(subscription -> mView.showProgress()).subscribeOn(AndroidSchedulers.mainThread())
				.subscribe(baseBean -> {
					mView.getQiniuTokenSuc(baseBean);
				}, throwable -> mView.hideProgress(R.string.neterror_tryagain)));
	}
}
