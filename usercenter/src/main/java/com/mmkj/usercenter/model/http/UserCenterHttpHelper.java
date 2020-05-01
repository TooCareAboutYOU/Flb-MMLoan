package com.mmkj.usercenter.model.http;


import com.mmkj.baselibrary.model.entitry.BaseBean;
import com.mmkj.baselibrary.model.entitry.GCashDetailBean;
import com.mmkj.baselibrary.model.entitry.WithdrawalNumberEntity;
import com.mmkj.baselibrary.model.http.HttpHelper;
import com.mmkj.usercenter.model.entity.CashDepositInfoEntity;
import com.mmkj.usercenter.model.entity.CreditManagerEntity;
import com.mmkj.usercenter.model.entity.CustomerServiceData;
import com.mmkj.usercenter.model.entity.LoanListData;
import com.mmkj.usercenter.model.entity.PaymentData;
import com.mmkj.usercenter.model.entity.PaymentListData;
import com.mmkj.usercenter.model.entity.RepayChannelBean;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.Query;

public interface UserCenterHttpHelper extends HttpHelper {

	Flowable<BaseBean> confirmIncreaseCashDeposit(String productCode,String sessionId);

	Flowable<BaseBean<CashDepositInfoEntity>> getDepositInfo(String sessionId);

	Flowable<BaseBean> loginOut(String sessionId);
	Flowable<BaseBean<PaymentData>> getPaymentCode(String sessionId);
	Flowable<BaseBean<PaymentListData>> getBillsDetails(String sessionId, String billId);
    Flowable<BaseBean<List<LoanListData>>> getLoanRecords(String sessionId, int type);
    Flowable<BaseBean<List<CustomerServiceData>>> getCustomerService();

	Flowable<BaseBean<WithdrawalNumberEntity>> getWithdrawalNumber(String sessionId);

	Flowable<BaseBean<CreditManagerEntity>> queryCreditManager(String sessionId);

	Flowable<BaseBean<RepayChannelBean>> getRepayChannelByUser(String sessionId);
	Flowable<BaseBean<GCashDetailBean>> getGCashDetail(String sessionId);

}
