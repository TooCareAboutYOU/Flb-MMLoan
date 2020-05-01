package com.mmkjflb.mmloan.viewmodel.bankinform;

import com.mmkjflb.mmloan.R;
import com.mmkjflb.mmloan.model.DataManager;
import com.mmkj.baselibrary.base.RxPresenter;
import com.mmkj.baselibrary.model.entitry.BaseBean;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

/**
 * Created by Administrator on 2017/10/28.
 */

public class BankInformPresenter extends RxPresenter<BankInformContract.View> implements BankInformContract.Presenter  {

	private DataManager mDataManager;

	@Inject
	public BankInformPresenter(DataManager mDataManager) {
		this.mDataManager = mDataManager;
	}


	@Override
	public void bankList(String sessionId) {
		addSubscribe(mDataManager.bankList(sessionId).doOnSubscribe(subscription -> mView.showProgress()).subscribeOn(AndroidSchedulers.mainThread())
				.subscribe(loginBeanBaseBean -> {
					mView.bankListSuc(loginBeanBaseBean);
					mView.hideProgress();
				}, throwable -> mView.hideProgress(R.string.neterror_tryagain)));

	}

	@Override
	public void bankinfo(Map<String, Object> params) {
		addSubscribe(mDataManager.bankinfo(params).doOnSubscribe(subscription -> mView.showProgress()).subscribeOn(AndroidSchedulers.mainThread())
				.subscribe(baseBean -> {
					mView.bankinfoSuc(baseBean);
					mView.hideProgress();
				}, throwable -> mView.hideProgress(R.string.neterror_tryagain)));
	}

	@Override
	public void getBankInfo(String sessionId) {
		addSubscribe(mDataManager.getBankInfo(sessionId).doOnSubscribe(subscription -> mView.showProgress()).subscribeOn(AndroidSchedulers.mainThread())
				.subscribe((Consumer<BaseBean>) baseBean -> {
					mView.getBankInfoSuc(baseBean);
					mView.hideProgress();
				}, throwable -> mView.hideProgress()));

	}
}
