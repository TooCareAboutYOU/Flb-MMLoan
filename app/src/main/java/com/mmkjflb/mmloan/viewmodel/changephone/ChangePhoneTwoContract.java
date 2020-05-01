package com.mmkjflb.mmloan.viewmodel.changephone;

import com.mmkj.baselibrary.base.BasePresenter;
import com.mmkj.baselibrary.base.BaseView;
import com.mmkj.baselibrary.model.entitry.BaseBean;

import java.util.Map;

public interface ChangePhoneTwoContract {
	interface View extends BaseView {

		void sendCodeSuc(BaseBean baseBean);

		void renewalMobilePhoneSuc(BaseBean baseBean);
	}

	interface Presenter extends BasePresenter<ChangePhoneTwoContract.View> {


		void sendCode(String mobile, String smsCode);

		void renewalMobilePhone(Map<String, Object> map);
	}
}
