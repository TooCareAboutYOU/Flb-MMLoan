package com.mmkjflb.mmloan.viewmodel.changephone;

import com.mmkj.baselibrary.base.BasePresenter;
import com.mmkj.baselibrary.base.BaseView;
import com.mmkj.baselibrary.model.entitry.BaseBean;

import java.util.Map;

public interface ChangePhoneOneContract {
	interface View extends BaseView {


		void checkUserInfoSuc(BaseBean baseBean);
	}

	interface Presenter extends BasePresenter<ChangePhoneOneContract.View> {



		void checkUserInfo(Map<String, Object> map);
	}
}
