package com.mmkjflb.mmloan.viewmodel.emergencycontact;

import com.mmkj.baselibrary.base.RxPresenter;
import com.mmkjflb.mmloan.R;
import com.mmkjflb.mmloan.model.DataManager;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;


public class EmergencyContactPresenter extends RxPresenter<EmergencyContactContract.View> implements EmergencyContactContract.Presenter {
	private DataManager mDataManager;

	@Inject
	public EmergencyContactPresenter(DataManager mDataManager) {
		this.mDataManager = mDataManager;
	}

	@Override
	public void contacts(String sessionId, String contactsList, String firstContactMobile, String firstContactName, String secondContactMobile, String secondContactName) {
		addSubscribe(mDataManager.contacts(sessionId, contactsList, firstContactMobile, firstContactName, secondContactMobile, secondContactName)
				.doOnSubscribe(subscription -> mView.showProgress()).subscribeOn(AndroidSchedulers.mainThread())
				.subscribe(baseBean -> {
					mView.contactsSuc(baseBean);
					mView.hideProgress();
				}, throwable -> mView.hideProgress(R.string.neterror_tryagain)));
	}

	@Override
	public void getContactsInfo(String sessionId) {
		addSubscribe(mDataManager.getContactsInfo(sessionId)
				.doOnSubscribe(subscription -> mView.showProgress()).subscribeOn(AndroidSchedulers.mainThread())
				.subscribe(baseBean -> {
					mView.getContactsInfoSuc(baseBean);
					mView.hideProgress();
				}, throwable -> mView.hideProgress()));
	}

//	@Override
//	public void submitCallRecord(String sessionId, String records,int contactType) {
//		addSubscribe(mDataManager.submitCallRecord(sessionId, records)
//				.doOnSubscribe(subscription -> mView.showProgress()).subscribeOn(AndroidSchedulers.mainThread())
//				.subscribe(baseBean -> {
//					mView.submitCallRecordSuc(baseBean,contactType);
//					mView.hideProgress();
//				}, throwable -> mView.hideProgress(R.string.neterror_tryagain)));
//	}
}

