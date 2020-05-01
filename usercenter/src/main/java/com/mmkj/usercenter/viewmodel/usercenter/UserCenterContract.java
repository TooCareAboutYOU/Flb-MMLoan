package com.mmkj.usercenter.viewmodel.usercenter;


import com.mmkj.baselibrary.base.BasePresenter;
import com.mmkj.baselibrary.base.BaseView;
import com.mmkj.baselibrary.model.entitry.BaseBean;
import com.mmkj.baselibrary.model.entitry.GCashDetailBean;

/**
 * Author: wendjia
 * Time: 2018\7\31
 * Description: 选择职业
 */
public class UserCenterContract {

	/**
	 * The interface View.
	 */
	public interface View extends BaseView {
		void getGCashDetailSuc(BaseBean<GCashDetailBean> baseBean);
	}

	/**
	 * The interface Presenter.
	 */
	public interface Presenter extends BasePresenter<View> {
		void getGCashDetail(String sessionId);

	}

}
