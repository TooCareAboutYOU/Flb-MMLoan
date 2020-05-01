package com.mmkjflb.mmloan.viewmodel.modifygcash;

import com.mmkj.baselibrary.base.BasePresenter;
import com.mmkj.baselibrary.base.BaseView;
import com.mmkj.baselibrary.model.entitry.BaseBean;

/**
 * Created by Administrator on 2017/10/28.
 */

public class ModifyGCashContract {
	interface View extends BaseView {
		void sendSmsSuc(BaseBean baseBean);
		void updateGCashSuc(BaseBean baseBean);


	}

	interface Presenter extends BasePresenter<ModifyGCashContract.View> {
		void sendSms(String mobile, String smsCode);
		void updateGCash(String sessionId,String userGCashAccount,String captcha);

	}
}
