package com.mmkjflb.mmloan.viewmodel.maimaiborrow;

import com.mmkjflb.mmloan.R;
import com.mmkjflb.mmloan.model.DataManager;
import com.mmkj.baselibrary.base.RxPresenter;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by Administrator on 2017/8/10.
 */

public class MaiMaiBorrowPresenter extends RxPresenter<MaiMaiBorrowContract.View> implements MaiMaiBorrowContract.Presenter {

	private DataManager mDataManager;

	@Inject
	public MaiMaiBorrowPresenter(DataManager mDataManager) {
		this.mDataManager = mDataManager;
	}


	@Override
	public void applyLoan(Map<String, Object> map) {
		addSubscribe(mDataManager.applyLoan(map).doOnSubscribe(subscription -> mView.showProgress()).subscribeOn(AndroidSchedulers.mainThread())
				.subscribe(baseBean -> {
					mView.applyLoanSuc(baseBean);
					mView.hideProgress();
				}, throwable -> mView.hideProgress(R.string.neterror_tryagain)));
	}
}
