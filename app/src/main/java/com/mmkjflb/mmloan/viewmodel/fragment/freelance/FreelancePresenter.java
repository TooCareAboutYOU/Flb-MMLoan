package com.mmkjflb.mmloan.viewmodel.fragment.freelance;

import com.mmkjflb.mmloan.R;
import com.mmkjflb.mmloan.model.DataManager;
import com.mmkj.baselibrary.base.RxPresenter;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by
 * Date: On 2018/3/5
 * Author: wangjieyan
 * Email: wangjieyan9308@163.com
 */

public class FreelancePresenter extends RxPresenter<FreelanceContract.View> implements FreelanceContract.Presenter {

	private DataManager mDataManager;

	@Inject
	public FreelancePresenter(DataManager mDataManager) {
		this.mDataManager = mDataManager;
	}


	@Override
	public void freelanceinfo(Map<String, Object> params) {
		addSubscribe(mDataManager.freelanceinfo(params).doOnSubscribe(subscription -> {
			if (mView != null) {
				mView.showProgress();
			}
		}).subscribeOn(AndroidSchedulers.mainThread()).subscribe(baseBean -> {
			if (mView != null) {
				mView.freelanceinfoSuc(baseBean);
				mView.hideProgress();
			}
		}, throwable -> {
			if (mView != null) {
				mView.hideProgress(R.string.neterror_tryagain);
			}
		}));
	}

	@Override
	public void getFreelanceInfo(String sessionId) {
		addSubscribe(mDataManager.getFreelanceInfo(sessionId).doOnSubscribe(subscription -> {
			if (mView != null) {
				mView.showProgress();
			}
		})
				.subscribeOn(AndroidSchedulers.mainThread())
				.subscribe(baseBean -> {
					if (mView != null) {
						mView.getFreelanceInfoSuc(baseBean);
						mView.hideProgress();
					}
				}, throwable -> {
					if (mView != null) {
						mView.hideProgress();
					}
				}));
	}
}
