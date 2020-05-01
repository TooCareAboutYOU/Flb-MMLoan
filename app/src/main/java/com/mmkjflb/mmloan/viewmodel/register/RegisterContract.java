package com.mmkjflb.mmloan.viewmodel.register;

import android.graphics.Bitmap;

import com.mmkj.baselibrary.base.BasePresenter;
import com.mmkj.baselibrary.base.BaseView;
import com.mmkj.baselibrary.model.entitry.BaseBean;

import java.util.Map;

public interface RegisterContract {
	interface View extends BaseView {

		void sendCodeSuc(BaseBean baseBean);

		void registerSuc(BaseBean baseBean);

		void showVCode(Bitmap bitmap);

	}

	interface Presenter extends BasePresenter<RegisterContract.View> {

		void sendCode(String mobile, String smsCode);

		void register(Map<String, Object> map);

		void vCode(String mobile);
	}

}
