package com.mmkjflb.mmloan.viewmodel.emergencycontact;

import com.mmkjflb.mmloan.model.entity.ContactsEntity;
import com.mmkj.baselibrary.base.BasePresenter;
import com.mmkj.baselibrary.base.BaseView;
import com.mmkj.baselibrary.model.entitry.BaseBean;

public interface EmergencyContactContract {
	interface View extends BaseView {
		void contactsSuc(BaseBean baseBean);
		void getContactsInfoSuc(BaseBean<ContactsEntity> baseBean);
//		void submitCallRecordSuc(BaseBean baseBean,int contactType);
	}

	interface Presenter extends BasePresenter<EmergencyContactContract.View> {
		void contacts(String sessionId,  String contactsList,String firstContactMobile, String firstContactName, String secondContactMobile, String secondContactName);
		void getContactsInfo(String sessionId);
//		void submitCallRecord(String sessionId, String records,int contactType);
	}
}
