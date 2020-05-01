package com.mmkj.usercenter.viewmodel.loanlist;

import com.mmkj.baselibrary.base.BasePresenter;
import com.mmkj.baselibrary.base.BaseView;
import com.mmkj.baselibrary.model.entitry.BaseBean;
import com.mmkj.usercenter.model.entity.LoanListData;

import java.util.List;

/**
 *
 * @author wendjia
 * @date 2017/10/24
 */

public interface LoanListContract {
	interface View extends BaseView {
        void getLoanRecordsSuc(BaseBean<List<LoanListData>> baseBean);
		void getCurrentLoanSuc(BaseBean<List<LoanListData>> baseBean);

	}


	interface Presenter extends BasePresenter<View> {
		void getLoanRecords(String sessionId);
		void getCurrentLoan(String sessionId);

	}
}