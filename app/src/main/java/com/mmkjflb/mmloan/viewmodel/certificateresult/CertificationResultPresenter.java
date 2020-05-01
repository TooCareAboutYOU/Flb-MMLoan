package com.mmkjflb.mmloan.viewmodel.certificateresult;

import com.mmkjflb.mmloan.R;
import com.mmkjflb.mmloan.model.DataManager;
import com.mmkj.baselibrary.base.RxPresenter;
import com.mmkj.baselibrary.util.PreferenceUtils;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by Administrator on 2017/8/10.
 */

public class CertificationResultPresenter extends RxPresenter<CertificationResultContract.View> implements CertificationResultContract.Presenter {

	private DataManager mDataManager;

	@Inject
	public CertificationResultPresenter(DataManager mDataManager) {
		this.mDataManager = mDataManager;
	}

	@Override
	public void queryCredit(String sessionId) {
		addSubscribe(mDataManager.queryCredit(sessionId).doOnSubscribe(subscription -> mView.showLoading()).subscribeOn(AndroidSchedulers.mainThread())
				.subscribe(baseBean -> {
					mView.queryCreditSuc(baseBean);
					mView.hideLoading();
				}, throwable -> mView.loadingError(v -> queryCredit(PreferenceUtils.getUserSessionid()))));
	}

	@Override
	public void rollbackStatus(String sessionId) {
		addSubscribe(mDataManager.rollbackStatus(sessionId).doOnSubscribe(subscription -> mView.showLoading()).subscribeOn(AndroidSchedulers.mainThread())
				.subscribe(baseBean -> {
					mView.rollbackStatusSuc(baseBean);
					mView.hideLoading();
				}, throwable -> mView.hideProgress(R.string.neterror_tryagain)));
	}

}
