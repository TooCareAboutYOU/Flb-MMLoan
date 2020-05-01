package com.mmkjflb.mmloan.viewmodel.bankinform;

import com.mmkjflb.mmloan.model.entity.BankInfoEntity;
import com.mmkjflb.mmloan.model.entity.BankListEntity;
import com.mmkj.baselibrary.base.BasePresenter;
import com.mmkj.baselibrary.base.BaseView;
import com.mmkj.baselibrary.model.entitry.BaseBean;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/10/28.
 */

public class BankInformContract {
	interface View extends BaseView {
		void bankListSuc(BaseBean<List<BankListEntity>> baseBean);
		void bankinfoSuc(BaseBean baseBean);
		void getBankInfoSuc(BaseBean<BankInfoEntity> baseBean);

	}
	interface Presenter extends BasePresenter<BankInformContract.View> {
		void bankList(String sessionId);
		void bankinfo( Map<String, Object> params);
		void getBankInfo(String sessionId);
	}
}
