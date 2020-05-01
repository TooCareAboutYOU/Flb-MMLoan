package com.mmkjflb.mmloan.viewmodel.maimaiborrow;

import com.mmkj.baselibrary.base.BasePresenter;
import com.mmkj.baselibrary.base.BaseView;
import com.mmkj.baselibrary.model.entitry.BaseBean;

import java.util.Map;

public interface MaiMaiBorrowContract {
	interface View extends BaseView {
		void applyLoanSuc(BaseBean baseBean);

	}

	interface Presenter extends BasePresenter<MaiMaiBorrowContract.View> {
		void applyLoan(Map<String, Object> map);
	}
}
