package com.mmkjflb.mmloan.viewmodel.idcertification;

import com.mmkjflb.mmloan.R;
import com.mmkjflb.mmloan.model.DataManager;
import com.mmkj.baselibrary.base.RxPresenter;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by Administrator on 2017/10/26.
 */

public class IdCertificationPresenter extends RxPresenter<IdCertificationContract.View> implements IdCertificationContract.Presenter {

	private DataManager mDataManager;

	@Inject
	public IdCertificationPresenter(DataManager mDataManager) {
		this.mDataManager = mDataManager;
	}


	@Override
	public void idcheck(Map<String, Object> request) {
		addSubscribe(mDataManager.idcheck(request)
				.subscribe(baseBean -> {
					mView.idcheckSuc(baseBean);
					mView.hideProgress();
				}, throwable -> mView.hideProgress(R.string.neterror_tryagain)));
	}

	@Override
	public void getBaseInfo(String sessionId) {
		addSubscribe(mDataManager.getBaseInfo(sessionId).doOnSubscribe(subscription -> mView.showProgress()).subscribeOn(AndroidSchedulers.mainThread())
				.subscribe(baseBean -> {
					mView.getBaseInfoSuc(baseBean);
					mView.hideProgress();
				}, throwable -> {
					mView.hideProgress();
					mView.onError();
				}));
	}

	@Override
	public void getQiniuToken(String sessionId) {
		addSubscribe(mDataManager.getQiniuToken(sessionId).doOnSubscribe(subscription -> mView.showProgress()).subscribeOn(AndroidSchedulers.mainThread())
				.subscribe(baseBean -> {
					mView.getQiniuTokenSuc(baseBean);
				}, throwable -> mView.hideProgress(R.string.neterror_tryagain)));
	}

	@Override
	public void idCardCheck(Map<String, RequestBody> partMap, MultipartBody.Part multipartPart) {
		addSubscribe(mDataManager.idCardCheck(partMap,multipartPart).doOnSubscribe(subscription -> mView.showProgress()).subscribeOn(AndroidSchedulers.mainThread())
				.subscribe(baseBean -> {
					mView.idCardCheckSuc(baseBean);
					mView.hideProgress();
				}, throwable -> mView.hideProgress()));
	}
}