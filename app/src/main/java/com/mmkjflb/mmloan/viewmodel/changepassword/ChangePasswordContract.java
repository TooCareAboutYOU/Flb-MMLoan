package com.mmkjflb.mmloan.viewmodel.changepassword;

import com.mmkj.baselibrary.base.BasePresenter;
import com.mmkj.baselibrary.base.BaseView;
import com.mmkj.baselibrary.model.entitry.BaseBean;

import java.util.Map;

/**
 * Created by Administrator on 2017/10/28.
 */

public class ChangePasswordContract {
	interface View extends BaseView {
		void updatePwdSuc(BaseBean baseBean);


	}

	interface Presenter extends BasePresenter<ChangePasswordContract.View> {
		void updatePwd(Map<String, String> map);
	}
}
