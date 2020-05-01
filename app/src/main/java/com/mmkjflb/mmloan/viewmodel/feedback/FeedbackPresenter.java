package com.mmkjflb.mmloan.viewmodel.feedback;

import com.mmkjflb.mmloan.R;
import com.mmkjflb.mmloan.model.DataManager;
import com.mmkj.baselibrary.base.RxPresenter;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by Administrator on 2017/10/28.
 */

public class FeedbackPresenter  extends RxPresenter<FeedbackContract.View> implements FeedbackContract.Presenter  {

	private DataManager mDataManager;

	@Inject
	public FeedbackPresenter(DataManager mDataManager) {
		this.mDataManager = mDataManager;
	}

	@Override
	public void applySuggest(Map<String, String> map) {
		addSubscribe(mDataManager.applySuggest(map).doOnSubscribe(subscription -> mView.showProgress()).subscribeOn(AndroidSchedulers.mainThread())
				.subscribe(loginBeanBaseBean -> {
					mView.applySuggestSuc(loginBeanBaseBean);
					mView.hideProgress();
				}, throwable -> mView.hideProgress(R.string.neterror_tryagain)));
	}
}
