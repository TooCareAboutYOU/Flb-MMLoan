package com.mmkjflb.mmloan.viewmodel.refundeposit;

import com.mmkj.baselibrary.base.RxPresenter;
import com.mmkjflb.mmloan.R;
import com.mmkjflb.mmloan.model.DataManager;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by Administrator on 2017/8/10.
 */

public class RefundDepositPresenter extends RxPresenter<RefundDepositContract.View> implements RefundDepositContract.Presenter {

	private DataManager mDataManager;

	@Inject
	public RefundDepositPresenter(DataManager mDataManager) {
		this.mDataManager = mDataManager;
	}

	@Override
	public void getWithdrawChannel() {
		addSubscribe(mDataManager.getWithdrawChannel().doOnSubscribe(subscription -> mView.showProgress()).subscribeOn(AndroidSchedulers.mainThread())
				.subscribe(baseBean -> {
					mView.getWithdrawChannelSuc(baseBean);
					mView.hideProgress();
				}, throwable -> mView.hideProgress(R.string.neterror_tryagain)));
	}

	@Override
	public void getRefundInfo(String sessionId) {
		addSubscribe(mDataManager.getRefundInfo(sessionId).doOnSubscribe(subscription -> mView.showProgress()).subscribeOn(AndroidSchedulers.mainThread())
				.subscribe(baseBean -> {
					mView.getRefundInfoSuc(baseBean);
					mView.hideProgress();
				}, throwable -> mView.hideProgress(R.string.neterror_tryagain)));
	}

	@Override
	public void refundDeposit(String sessionId, String channel) {
		addSubscribe(mDataManager.refundDeposit(sessionId,channel).doOnSubscribe(subscription -> mView.showProgress()).subscribeOn(AndroidSchedulers.mainThread())
				.subscribe(baseBean -> {
					mView.refundDeposit(baseBean);
					mView.hideProgress();
				}, throwable -> mView.hideProgress(R.string.neterror_tryagain)));
	}

}
