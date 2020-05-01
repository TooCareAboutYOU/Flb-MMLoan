package com.mmkj.usercenter.viewmodel.repaydetail;


import com.mmkj.baselibrary.base.RxPresenter;
import com.mmkj.baselibrary.util.DateUtils;
import com.mmkj.baselibrary.util.StringUtils;
import com.mmkj.usercenter.R;
import com.mmkj.usercenter.model.UserCenterDataManager;
import com.mmkj.usercenter.model.entity.PaymentListData;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 *
 * @author wendjia
 * @date 2017/10/24
 */

public class RepayDetailPresenter extends RxPresenter<RepayDetailContract.View> implements RepayDetailContract.Presenter {

	private UserCenterDataManager mDataManager;
	private  PaymentListData paymentListData;

	@Inject
	public RepayDetailPresenter(UserCenterDataManager mDataManager) {
		this.mDataManager = mDataManager;
	}


	public void initPaymentData(PaymentListData paymentListData){
		this.paymentListData = paymentListData;
	}

	public String showRealRepay() {
		if (paymentListData != null) {
			return StringUtils.cutOutLastThree(StringUtils.doubleZheng(paymentListData.getRealRepaymentCorpus() + paymentListData.getRealRepaymentInterest()));
		}
		return "";
	}

	public String showBillNo(){
		if (paymentListData!=null){
			return paymentListData.getOrderNo();
		}
		return "";
	}

	public String showRepayDate(){
		if (paymentListData!=null){
			return DateUtils.showYMDTime(paymentListData.getRealRepaymentTime());
		}
		return "";
	}
	public String showLoanAmount(){
		if (paymentListData!=null){
			return StringUtils.cutOutLastThree(StringUtils.doubleZheng(paymentListData.getRealRepaymentCorpus()));
		}
		return "";
	}
	public String showLoanService(){
		if (paymentListData!=null){
			return StringUtils.cutOutLastThree(StringUtils.doubleZheng(paymentListData.getFeeRate()));
		}
		return "";
	}

	public String showLoanFee(){
		if (paymentListData!=null){
//			return paymentListData.getrea+"";
			return StringUtils.cutOutLastThree(StringUtils.doubleZheng(paymentListData.getFeeProcedurePay()));
		}
		return "";
	}

	public String showRepayFee(){
		if (paymentListData!=null){
			return StringUtils.cutOutLastThree(StringUtils.doubleZheng(paymentListData.getFeeProcedureRepay()));
		}
		return "";
	}

	public String showInterest(){
		if (paymentListData!=null){
			return StringUtils.cutOutLastThree(StringUtils.doubleZheng(paymentListData.getRepaymentInterest()));
		}
		return "";
	}

	public String showLoanDate(){
		if (paymentListData!=null){
			return DateUtils.showYMDTime(paymentListData.getCreateTime());
		}
		return "";
	}

	public String showOverdue() {
		if (paymentListData != null) {
			return StringUtils.cutOutLastThree(StringUtils.doubleZheng(paymentListData.getOverdueFine()));
		}
		return "";
	}

	public boolean isShowOverdue() {
		if (paymentListData != null) {
			return paymentListData.getOverdueFine() > 0;
		}
		return false;
	}
	@Override
	public void getBillDetails(String sessionId, String billId) {
		addSubscribe(mDataManager.getBillsDetails(sessionId,billId).doOnSubscribe(subscription -> mView.showProgress()).subscribeOn(AndroidSchedulers.mainThread())
				.subscribe(baseBean -> {
					mView.getBillDetailsSuc(baseBean);
					mView.hideProgress();
				}, throwable -> mView.hideProgress(R.string.neterror_tryagain)));
	}
}

