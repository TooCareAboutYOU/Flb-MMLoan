package com.mmkj.usercenter.model;


import com.mmkj.baselibrary.model.entitry.BaseBean;
import com.mmkj.baselibrary.model.entitry.GCashDetailBean;
import com.mmkj.baselibrary.model.entitry.WithdrawalNumberEntity;
import com.mmkj.baselibrary.model.prefs.AppPreferencesHelper;
import com.mmkj.baselibrary.model.prefs.PreferencesHelper;
import com.mmkj.usercenter.model.entity.CashDepositInfoEntity;
import com.mmkj.usercenter.model.entity.CreditManagerEntity;
import com.mmkj.usercenter.model.entity.CustomerServiceData;
import com.mmkj.usercenter.model.entity.LoanListData;
import com.mmkj.usercenter.model.entity.PaymentData;
import com.mmkj.usercenter.model.entity.PaymentListData;
import com.mmkj.usercenter.model.entity.RepayChannelBean;
import com.mmkj.usercenter.model.http.UserCenterHttpHelper;

import java.util.List;

import javax.inject.Singleton;

import io.reactivex.Flowable;
import retrofit2.http.Query;

@Singleton
public class UserCenterDataManager implements UserCenterHttpHelper, PreferencesHelper {

	UserCenterHttpHelper mHttpHelper;
	private PreferencesHelper mPreferencesHelper;

	public UserCenterDataManager(UserCenterHttpHelper apiService, AppPreferencesHelper preferencesHelper) {
		mHttpHelper = apiService;
		mPreferencesHelper = preferencesHelper;
	}

	@Override
	public boolean isFirstCome() {
		return mPreferencesHelper.isFirstCome();
	}

	@Override
	public void setFirstCome(boolean state) {
		mPreferencesHelper.setFirstCome(state);
	}
	@Override
	public Flowable<BaseBean<RepayChannelBean>> getRepayChannelByUser(String sessionId) {
		return mHttpHelper.getRepayChannelByUser(sessionId);
	}
	@Override
	public Flowable<BaseBean> confirmIncreaseCashDeposit(String productCode, String sessionId) {
		return mHttpHelper.confirmIncreaseCashDeposit(productCode,sessionId);
	}

	@Override
	public Flowable<BaseBean<CashDepositInfoEntity>> getDepositInfo(String sessionId) {
		return mHttpHelper.getDepositInfo(sessionId);
	}

	@Override
	public Flowable<BaseBean> loginOut(String sessionId) {
		return mHttpHelper.loginOut(sessionId);
	}

	@Override
	public Flowable<BaseBean<PaymentData>> getPaymentCode(String sessionId) {
		return mHttpHelper.getPaymentCode(sessionId);
	}

	@Override
	public Flowable<BaseBean<PaymentListData>> getBillsDetails(String sessionId, String billId) {
		return mHttpHelper.getBillsDetails(sessionId,billId);
	}
	@Override
	public Flowable<BaseBean<GCashDetailBean>> getGCashDetail(String sessionId) {
		return mHttpHelper.getGCashDetail(sessionId);
	}

    @Override
    public Flowable<BaseBean<List<LoanListData>>> getLoanRecords(String sessionId, int type) {
        return mHttpHelper.getLoanRecords(sessionId, type);
    }

	@Override
	public Flowable<BaseBean<List<CustomerServiceData>>> getCustomerService() {
		return mHttpHelper.getCustomerService();
	}

	@Override
	public Flowable<BaseBean<WithdrawalNumberEntity>> getWithdrawalNumber(String sessionId) {
		return mHttpHelper.getWithdrawalNumber(sessionId);
	}
	@Override
	public Flowable<BaseBean<CreditManagerEntity>> queryCreditManager(String sessionId) {
		return mHttpHelper.queryCreditManager(sessionId);
	}


}
