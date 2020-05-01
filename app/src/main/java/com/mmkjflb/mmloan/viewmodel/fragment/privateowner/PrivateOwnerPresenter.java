package com.mmkjflb.mmloan.viewmodel.fragment.privateowner;

import com.mmkjflb.mmloan.R;
import com.mmkjflb.mmloan.model.DataManager;
import com.mmkj.baselibrary.base.RxPresenter;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by
 * Date: On 2018/3/5
 * Author: wangjieyan
 * Email: wangjieyan9308@163.com
 */

public class PrivateOwnerPresenter extends RxPresenter<PrivateOwnerContract.View> implements PrivateOwnerContract.Presenter{

    private DataManager mDataManager;

    @Inject
    public PrivateOwnerPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void getIslandsInfo(String sessionId) {
		addSubscribe(mDataManager.getIslandsInfo(sessionId).doOnSubscribe(subscription -> {
			if (mView != null) {
				mView.showProgress();
			}
		}).subscribeOn(AndroidSchedulers.mainThread())
				.subscribe(baseBean -> {
					if (mView != null) {
						mView.getIslandsInfoSuc(baseBean);
						mView.hideProgress();
					}
				}, throwable -> {
					if (mView != null) {
						mView.hideProgress(R.string.neterror_tryagain);
					}
				}));
    }

    @Override
    public void selfemployinfo(Map<String, RequestBody> params, MultipartBody.Part companyFront) {
		addSubscribe(mDataManager.selfemployinfo(params, companyFront).doOnSubscribe(subscription -> {
			if (mView != null) {
				mView.showProgress();
			}
		}).subscribeOn(AndroidSchedulers.mainThread())
				.subscribe(baseBean -> {
					if (mView != null) {
						mView.selfemployinfoSuc(baseBean);
						mView.hideProgress();
					}
				}, throwable -> {
					if (mView != null) {
						mView.hideProgress(R.string.neterror_tryagain);
					}
				}));
    }

    @Override
    public void getSelfemployInfo(String sessionId) {
		addSubscribe(mDataManager.getSelfemployInfo(sessionId).doOnSubscribe(subscription -> {
			if (mView != null) {
				mView.showProgress();
			}
		}).subscribeOn(AndroidSchedulers.mainThread())
				.subscribe(baseBean -> {
					if (mView != null) {
						mView.getSelfemployInfoSuc(baseBean);
						mView.hideProgress();
					}
				}, throwable -> {
					if (mView != null) {
						mView.hideProgress();
					}
				}));
    }
}
