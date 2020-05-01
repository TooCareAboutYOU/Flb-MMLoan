package com.mmkjflb.mmloan.viewmodel.refundeposit;

import com.mmkj.baselibrary.base.BasePresenter;
import com.mmkj.baselibrary.base.BaseView;
import com.mmkj.baselibrary.model.entitry.BaseBean;
import com.mmkjflb.mmloan.model.entity.RefundDepositEntity;
import com.mmkjflb.mmloan.model.entity.RefundInfoEntity;

import java.util.Map;

public interface RefundDepositContract {
	interface View extends BaseView {
		void getWithdrawChannelSuc(BaseBean<Map<String, Object>> baseBean);

		void getRefundInfoSuc(BaseBean<RefundInfoEntity> baseBean);

		void refundDeposit(BaseBean<RefundDepositEntity> baseBean);
	}

	interface Presenter extends BasePresenter<View> {
		void getWithdrawChannel();

		void getRefundInfo(String sessionId);

		void refundDeposit(String sessionId,String channel);
	}

}
