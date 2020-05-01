package com.mmkj.usercenter.viewmodel.reypaylist;

import com.mmkj.baselibrary.base.BasePresenter;
import com.mmkj.baselibrary.base.BaseView;
import com.mmkj.baselibrary.model.entitry.BaseBean;
import com.mmkj.usercenter.model.entity.PaymentData;

/**
 * Created by Administrator on 2017/10/24.
 */

public interface RepayListContract {
	interface View extends BaseView {
		void getPaymentCodeSuc(BaseBean<PaymentData> baseBean);
	}


	interface Presenter extends BasePresenter<View> {
		void getPaymentCode(String sessionId);
	}
}