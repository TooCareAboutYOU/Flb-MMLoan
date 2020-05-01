package com.mmkj.usercenter.viewmodel.repaydetail;

import com.mmkj.baselibrary.base.BasePresenter;
import com.mmkj.baselibrary.base.BaseView;
import com.mmkj.baselibrary.model.entitry.BaseBean;
import com.mmkj.usercenter.model.entity.PaymentListData;

/**
 *
 * @author wendjia
 * @date 2017/10/24
 */

public interface RepayDetailContract {
	interface View extends BaseView {
		void getBillDetailsSuc(BaseBean<PaymentListData> baseBean);
	}


	interface Presenter extends BasePresenter<View> {
		void getBillDetails(String sessionId,String billId);
	}
}