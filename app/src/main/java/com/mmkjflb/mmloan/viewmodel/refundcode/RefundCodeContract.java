package com.mmkjflb.mmloan.viewmodel.refundcode;

import com.mmkj.baselibrary.base.BasePresenter;
import com.mmkj.baselibrary.base.BaseView;
import com.mmkj.baselibrary.model.entitry.BaseBean;
import com.mmkjflb.mmloan.model.entity.RefundCodeEntity;

public interface RefundCodeContract {
	interface View extends BaseView {
		void getRefundCodeSuc(BaseBean<RefundCodeEntity> baseBean);
	}

	interface Presenter extends BasePresenter<View> {
		void getRefundCode(String sissionid);

	}

}
