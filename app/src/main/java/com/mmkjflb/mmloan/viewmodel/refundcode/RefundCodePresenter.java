package com.mmkjflb.mmloan.viewmodel.refundcode;

import com.mmkj.baselibrary.base.RxPresenter;
import com.mmkj.baselibrary.model.entitry.BaseBean;
import com.mmkjflb.mmloan.R;
import com.mmkjflb.mmloan.model.DataManager;
import com.mmkjflb.mmloan.model.entity.RefundCodeEntity;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

/**
 * Created by Administrator on 2017/8/10.
 */

public class RefundCodePresenter extends RxPresenter<RefundCodeContract.View> implements RefundCodeContract.Presenter {

	private DataManager mDataManager;

	@Inject
	public RefundCodePresenter(DataManager mDataManager) {
		this.mDataManager = mDataManager;
	}


	@Override
	public void getRefundCode(String sissionid) {
		addSubscribe(mDataManager.getRefundCode(sissionid).doOnSubscribe(subscription -> mView.showProgress()).subscribeOn(AndroidSchedulers.mainThread())
				.subscribe(new Consumer<BaseBean<RefundCodeEntity>>() {
					@Override
					public void accept(BaseBean<RefundCodeEntity> baseBean) throws Exception {
						mView.getRefundCodeSuc(baseBean);
						mView.hideProgress();
					}
				}, new Consumer<Throwable>() {
					@Override
					public void accept(Throwable throwable) throws Exception {
						mView.hideProgress(R.string.neterror_tryagain);
					}
				}));
	}
}
