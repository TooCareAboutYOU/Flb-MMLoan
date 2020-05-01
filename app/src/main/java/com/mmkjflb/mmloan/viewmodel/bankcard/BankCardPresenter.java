package com.mmkjflb.mmloan.viewmodel.bankcard;

import com.mmkjflb.mmloan.model.DataManager;
import com.mmkj.baselibrary.base.RxPresenter;
import com.mmkj.baselibrary.util.PreferenceUtils;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by Administrator on 2017/8/10.
 */

public class BankCardPresenter extends RxPresenter<BankCardContract.View> implements BankCardContract.Presenter {

	private DataManager mDataManager;

	@Inject
	public BankCardPresenter(DataManager mDataManager) {
		this.mDataManager = mDataManager;
	}


	@Override
	public void queryUserBankCard(String sessionId) {
		addSubscribe(mDataManager.queryUserBankCard(sessionId).doOnSubscribe(subscription -> mView.showLoading()).subscribeOn(AndroidSchedulers.mainThread())
				.subscribe(baseBean -> {
					mView.queryUserBankCardSuc(baseBean);
					mView.hideLoading();
				}, throwable -> mView.loadingError(v -> queryUserBankCard(PreferenceUtils.getString(PreferenceUtils.USER_SESSIONID, "")))));
	}

}
