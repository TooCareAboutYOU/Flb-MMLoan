package com.mmkjflb.mmloan.viewmodel.bindgcash;

import com.mmkj.baselibrary.base.BasePresenter;
import com.mmkj.baselibrary.base.BaseView;
import com.mmkj.baselibrary.model.entitry.BaseBean;
import com.mmkj.baselibrary.model.entitry.GCashDetailBean;
import com.mmkjflb.mmloan.model.entity.AmountTypeEntity;
import com.mmkjflb.mmloan.model.entity.ProductInfoBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;

/**
 * Created by Administrator on 2017/10/28.
 */

public class BindGCashContract {
	interface View extends BaseView {
		void getAmountTypeSuc(BaseBean<AmountTypeEntity> baseBean);
		void sendSmsSuc(BaseBean baseBean);
		void getGCashDetailSuc(BaseBean<GCashDetailBean> baseBean);
		void updateGCashSuc(BaseBean baseBean);
		void deviceInfoSuc(BaseBean baseBean);
		void getProductInfoSuc(BaseBean<List<ProductInfoBean>> baseBean);
		void updateProductCodeSuc(BaseBean baseBean);
	}

	interface Presenter extends BasePresenter<BindGCashContract.View> {
		void getAmountType(String sessionId);
		void sendSms(String mobile, String smsCode);
		void getGCashDetail(String sessionId);
		void updateGCash(String sessionId,String userGCashAccount,String captcha);
		void deviceInfo(Map<String, String> map);
		void getProductInfo(String sessionId,int type);
		void updateProductCode(String sessionId, int productType);
	}
}
