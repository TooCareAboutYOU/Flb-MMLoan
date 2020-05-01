package com.mmkj.usercenter.viewmodel.loanlist;


import com.mmkj.baselibrary.base.RxPresenter;
import com.mmkj.usercenter.R;
import com.mmkj.usercenter.model.UserCenterDataManager;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 *
 * @author wendjia
 * @date 2017/10/24
 */

public class LoanListPresenter extends RxPresenter<LoanListContract.View> implements LoanListContract.Presenter {

	private UserCenterDataManager mDataManager;


	@Inject
	public LoanListPresenter(UserCenterDataManager mDataManager) {
		this.mDataManager = mDataManager;
	}


    @Override
	public void getLoanRecords(String sessionId) {
		addSubscribe(mDataManager.getLoanRecords(sessionId, 1).doOnSubscribe(subscription -> mView.showProgress()).subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(baseBean -> {
                    mView.getLoanRecordsSuc(baseBean);
                    mView.hideProgress();
                }, throwable -> mView.hideProgress(R.string.neterror_tryagain)));
    }

	@Override
	public void getCurrentLoan(String sessionId) {
		addSubscribe(mDataManager.getLoanRecords(sessionId, 0).doOnSubscribe(subscription -> mView.showProgress()).subscribeOn(AndroidSchedulers.mainThread())
				.subscribe(baseBean -> {
					mView.getCurrentLoanSuc(baseBean);
					mView.hideProgress();
				}, throwable -> mView.hideProgress(R.string.neterror_tryagain)));
	}


}

