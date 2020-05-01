package com.mmkjflb.mmloan.viewmodel.login;

import com.mmkjflb.mmloan.model.entity.LoginBean;
import com.mmkj.baselibrary.base.BasePresenter;
import com.mmkj.baselibrary.base.BaseView;
import com.mmkj.baselibrary.model.entitry.BaseBean;

import java.util.Map;

public interface LoginContract {
	interface View extends BaseView {
		void loginSuccess(BaseBean<LoginBean> baseBean);


	}

	interface Presenter extends BasePresenter<LoginContract.View> {
		void userLogin(String tel, String password);
		void deviceInfo(Map<String, String> map);
	}
}
