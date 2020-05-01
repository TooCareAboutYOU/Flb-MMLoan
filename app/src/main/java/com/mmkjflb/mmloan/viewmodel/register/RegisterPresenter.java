package com.mmkjflb.mmloan.viewmodel.register;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.mmkjflb.mmloan.R;
import com.mmkjflb.mmloan.model.DataManager;
import com.mmkj.baselibrary.base.RxPresenter;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by Administrator on 2017/8/10.
 */

public class RegisterPresenter extends RxPresenter<RegisterContract.View> implements RegisterContract.Presenter {

	private DataManager mDataManager;

	@Inject
	public RegisterPresenter(DataManager mDataManager) {
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
	public void register(Map<String, Object> map) {
		addSubscribe(mDataManager.register(map).doOnSubscribe(subscription -> mView.showProgress()).subscribeOn(AndroidSchedulers.mainThread())
				.subscribe(baseBean -> {
					mView.registerSuc(baseBean);
					mView.hideProgress();
				}, throwable -> mView.hideProgress(R.string.neterror_tryagain)));
	}

	@Override
	public void vCode(String mobile) {
		addSubscribe(mDataManager.getVerifycode(mobile).doOnSubscribe(subscription -> mView.showProgress()).subscribeOn(AndroidSchedulers.mainThread())
				.subscribe(responseBody -> {
					Bitmap bitmap = BitmapFactory.decodeStream(responseBody.byteStream());
					mView.showVCode(bitmap);
					mView.hideProgress();
				}, throwable -> mView.hideProgress(R.string.neterror_tryagain)));
	}

}
