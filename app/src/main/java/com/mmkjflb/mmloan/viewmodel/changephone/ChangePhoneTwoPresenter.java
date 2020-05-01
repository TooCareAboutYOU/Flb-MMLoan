package com.mmkjflb.mmloan.viewmodel.changephone;

import com.mmkjflb.mmloan.R;
import com.mmkjflb.mmloan.model.DataManager;
import com.mmkj.baselibrary.base.RxPresenter;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by Administrator on 2017/8/10.
 */

public class ChangePhoneTwoPresenter extends RxPresenter<ChangePhoneTwoContract.View> implements ChangePhoneTwoContract.Presenter {

	private DataManager mDataManager;

	@Inject
	public ChangePhoneTwoPresenter(DataManager mDataManager) {
		this.mDataManager = mDataManager;
	}

	@Override
	public void sendCode(String mobile, String smsCode) {
		addSubscribe(mDataManager.sendSms(mobile, smsCode).doOnSubscribe(subscription -> mView.showProgress()).subscribeOn(AndroidSchedulers.mainThread())
				.subscribe(baseBean -> {
					mView.sendCodeSuc(baseBean);
					mView.hideProgress();
				}, throwable -> mView.hideProgress(R.string.neterror_tryagain)));
	}

	@Override
	public void renewalMobilePhone(Map<String, Object> map) {
		addSubscribe(mDataManager.renewalMobilePhone(map).doOnSubscribe(subscription -> mView.showProgress()).subscribeOn(AndroidSchedulers.mainThread())
				.subscribe(baseBean -> {
					mView.renewalMobilePhoneSuc(baseBean);
					mView.hideProgress();
				}, throwable -> mView.hideProgress(R.string.neterror_tryagain)));
	}

}
