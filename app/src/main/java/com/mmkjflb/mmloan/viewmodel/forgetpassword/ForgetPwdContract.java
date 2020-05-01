package com.mmkjflb.mmloan.viewmodel.forgetpassword;

import android.graphics.Bitmap;

import com.mmkj.baselibrary.base.BasePresenter;
import com.mmkj.baselibrary.base.BaseView;
import com.mmkj.baselibrary.model.entitry.BaseBean;

import java.util.Map;

public interface ForgetPwdContract {
	interface View extends BaseView {
		void sendCodeSuc(BaseBean baseBean);

		void forgotPwdSuc(BaseBean baseBean);

		void showVCode(Bitmap bitmap);
	}

	interface Presenter extends BasePresenter<ForgetPwdContract.View> {
		void sendCode(String tel,String smsCode);
		void forgotPwd(Map<String, String> map);
        void vCode(String mobile);
	}
}
