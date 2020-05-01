package com.mmkj.usercenter.viewmodel.setting;

import com.mmkj.baselibrary.base.BasePresenter;
import com.mmkj.baselibrary.base.BaseView;
import com.mmkj.baselibrary.model.entitry.BaseBean;

/**
 * Created by Administrator on 2017/10/24.
 */

public interface SettingContract {
	interface View extends BaseView {
		void loginOutSuc(BaseBean baseBean);
	}


	interface Presenter extends BasePresenter<SettingContract.View> {
		void loginOut(String sessionId);
	}
}