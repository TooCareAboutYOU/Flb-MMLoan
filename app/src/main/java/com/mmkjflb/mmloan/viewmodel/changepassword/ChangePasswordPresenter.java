package com.mmkjflb.mmloan.viewmodel.changepassword;

import com.mmkjflb.mmloan.R;
import com.mmkjflb.mmloan.model.DataManager;
import com.mmkj.baselibrary.base.RxPresenter;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by Administrator on 2017/10/28.
 */

public class ChangePasswordPresenter extends RxPresenter<ChangePasswordContract.View> implements ChangePasswordContract.Presenter  {

	private DataManager mDataManager;

	@Inject
	public ChangePasswordPresenter(DataManager mDataManager) {
		this.mDataManager = mDataManager;
	}

	@Override
	public void updatePwd(Map<String, String> map) {
		addSubscribe(mDataManager.updatePwd(map).doOnSubscribe(subscription -> mView.showProgress()).subscribeOn(AndroidSchedulers.mainThread())
				.subscribe(baseBean -> {
					mView.updatePwdSuc(baseBean);
					mView.hideProgress();
				}, throwable -> mView.hideProgress(R.string.neterror_tryagain)));
	}
}

