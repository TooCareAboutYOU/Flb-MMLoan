package com.mmkj.usercenter.viewmodel.setting;

import com.mmkj.baselibrary.base.RxPresenter;
import com.mmkj.usercenter.R;
import com.mmkj.usercenter.model.UserCenterDataManager;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by Administrator on 2017/10/24.
 */

public class SettingPresenter extends RxPresenter<SettingContract.View> implements SettingContract.Presenter {

	private UserCenterDataManager mDataManager;

	@Inject
	public SettingPresenter(UserCenterDataManager mDataManager) {
		this.mDataManager = mDataManager;
	}

	@Override
	public void loginOut(String sessionId) {
		addSubscribe(mDataManager.loginOut(sessionId)
				.doOnSubscribe(subscription -> mView.showProgress()).subscribeOn(AndroidSchedulers.mainThread())
				.subscribe(baseBean -> {
					mView.loginOutSuc(baseBean);
					mView.hideProgress();
				}, throwable -> mView.hideProgress(R.string.neterror_tryagain)));
	}
}
