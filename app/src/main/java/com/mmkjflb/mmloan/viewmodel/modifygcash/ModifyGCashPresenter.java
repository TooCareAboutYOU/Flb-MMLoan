package com.mmkjflb.mmloan.viewmodel.modifygcash;

import com.mmkj.baselibrary.base.RxPresenter;
import com.mmkjflb.mmloan.R;
import com.mmkjflb.mmloan.model.DataManager;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

/**
 * Created by Administrator on 2017/10/28.
 */

public class ModifyGCashPresenter extends RxPresenter<ModifyGCashContract.View> implements ModifyGCashContract.Presenter  {

	private DataManager mDataManager;

	@Inject
	public ModifyGCashPresenter(DataManager mDataManager) {
		this.mDataManager = mDataManager;
	}

	@Override
	public void sendSms(String mobile, String smsCode) {
		addSubscribe(mDataManager.sendSms(mobile,smsCode).doOnSubscribe(subscription -> mView.showProgress()).subscribeOn(AndroidSchedulers.mainThread())
				.subscribe(baseBean -> {
					mView.sendSmsSuc(baseBean);
					mView.hideProgress();
				}, throwable -> mView.hideProgress(R.string.neterror_tryagain)));
	}

	@Override
	public void updateGCash(String sessionId, String userGCashAccount, String captcha) {
		addSubscribe(mDataManager.updateGCash(sessionId,userGCashAccount,captcha).doOnSubscribe(subscription -> mView.showProgress()).subscribeOn(AndroidSchedulers.mainThread())
				.subscribe(baseBean -> {
					mView.updateGCashSuc(baseBean);
					mView.hideProgress();
				}, new Consumer<Throwable>() {
					@Override
					public void accept(Throwable throwable) throws Exception {
						mView.hideProgress(R.string.neterror_tryagain);
					}
				}));
	}
}

