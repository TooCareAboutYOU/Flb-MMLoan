package com.mmkjflb.mmloan.viewmodel.bankcard;

import com.mmkjflb.mmloan.model.entity.CardManagerEntity;
import com.mmkj.baselibrary.base.BasePresenter;
import com.mmkj.baselibrary.base.BaseView;
import com.mmkj.baselibrary.model.entitry.BaseBean;

public interface BankCardContract {
	interface View extends BaseView {

		void queryUserBankCardSuc(BaseBean<CardManagerEntity> baseBean);

	}

	interface Presenter extends BasePresenter<BankCardContract.View> {
		void queryUserBankCard(String sessionId);

	}
}
