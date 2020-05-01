package com.mmkjflb.mmloan.viewmodel.coupon;

import com.mmkjflb.mmloan.R;
import com.mmkjflb.mmloan.model.DataManager;
import com.mmkj.baselibrary.base.RxPresenter;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by
 * Date: On 2018/1/15
 * Author: wangjieyan
 * Email: wangjieyan9308@163.com
 */

public class CouponNewPresenter extends RxPresenter<CouponNewContract.View> implements CouponNewContract.Presenter{

    private DataManager mDataManager;

    @Inject
    public CouponNewPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void querycoupon(String sessionId) {
		addSubscribe(mDataManager.querycoupon(sessionId).doOnSubscribe(subscription -> mView.showProgress()).subscribeOn(AndroidSchedulers.mainThread())
				.subscribe(baseBean -> {
					mView.querycouponSuc(baseBean);
					mView.hideProgress();
				}, throwable -> mView.hideProgress(R.string.neterror_tryagain)));
    }
}
