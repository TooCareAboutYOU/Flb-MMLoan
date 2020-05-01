package com.mmkj.usercenter.model.http;

import com.mmkj.baselibrary.model.entitry.BaseBean;
import com.mmkj.baselibrary.model.entitry.GCashDetailBean;
import com.mmkj.baselibrary.model.entitry.WithdrawalNumberEntity;
import com.mmkj.baselibrary.util.RxUtil;
import com.mmkj.usercenter.model.entity.CashDepositInfoEntity;
import com.mmkj.usercenter.model.entity.CreditManagerEntity;
import com.mmkj.usercenter.model.entity.CustomerServiceData;
import com.mmkj.usercenter.model.entity.LoanListData;
import com.mmkj.usercenter.model.entity.PaymentData;
import com.mmkj.usercenter.model.entity.PaymentListData;
import com.mmkj.usercenter.model.entity.RepayChannelBean;
import com.mmkj.usercenter.model.http.api.UserCenterApiService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import retrofit2.http.Query;


public class UserCenterRetrofitHelper implements UserCenterHttpHelper {

	private UserCenterApiService mApiHelper;

	@Inject
	public UserCenterRetrofitHelper(UserCenterApiService apiHelper) {
		this.mApiHelper = apiHelper;
	}

	@Override
	public Flowable<BaseBean> confirmIncreaseCashDeposit(String productCode, String sessionId) {
		return mApiHelper.confirmIncreaseCashDeposit(productCode,sessionId).compose(RxUtil.rxSchedulerHelper());
	}

	@Override
	public Flowable<BaseBean<CashDepositInfoEntity>> getDepositInfo(String sessionId) {
		return mApiHelper.getDepositInfo(sessionId).compose(RxUtil.rxSchedulerHelper());
	}

	@Override
	public Flowable<BaseBean> loginOut(String sessionId) {
		return mApiHelper.loginOut(sessionId).compose(RxUtil.rxSchedulerHelper());
	}

	@Override
	public Flowable<BaseBean<PaymentData>> getPaymentCode(String sessionId) {
		return mApiHelper.getPaymentCode(sessionId).compose(RxUtil.rxSchedulerHelper());
	}

	@Override
	public Flowable<BaseBean<PaymentListData>> getBillsDetails(String sessionId, String billId) {
		return mApiHelper.getBillsDetails(sessionId,billId).compose(RxUtil.rxSchedulerHelper());
	}

    @Override
    public Flowable<BaseBean<List<LoanListData>>> getLoanRecords(String sessionId, int type) {
        return mApiHelper.getLoanRecords(sessionId, type).compose(RxUtil.rxSchedulerHelper());
    }

	@Override
	public Flowable<BaseBean<List<CustomerServiceData>>> getCustomerService() {
		return mApiHelper.getCustomerService().compose(RxUtil.rxSchedulerHelper());
	}

	@Override
	public Flowable<BaseBean<WithdrawalNumberEntity>> getWithdrawalNumber(String sessionId) {
		return mApiHelper.getWithdrawalNumber(sessionId).compose(RxUtil.rxSchedulerHelper());
	}

	@Override
	public Flowable<BaseBean<CreditManagerEntity>> queryCreditManager(String sessionId) {
		return mApiHelper.queryCreditManager(sessionId).compose(RxUtil.rxSchedulerHelper());
	}

	@Override
	public Flowable<BaseBean<RepayChannelBean>> getRepayChannelByUser(String sessionId) {
		return mApiHelper.getRepayChannelByUser(sessionId).compose(RxUtil.rxSchedulerHelper());

	}

	@Override
	public Flowable<BaseBean<GCashDetailBean>> getGCashDetail(String sessionId) {
		return mApiHelper.getGCashDetail(sessionId).compose(RxUtil.rxSchedulerHelper());
	}
}
