package com.mmkj.usercenter.model.entity;

import com.google.gson.Gson;
import com.mmkj.baselibrary.util.DateUtils;
import com.mmkj.baselibrary.util.StringUtils;

/**
 * Author: wendjia
 * Time: 2018\9\5 0005
 * Description:
 **/
public class CurrentPaymentData {
	private long userId;    //用户id
	private long billId;    //账单id
	private long repaymentTime; //最后还款日
	private String paymentCode; //还款码
	private double amount;  //还款金额
	private String biller;
	private int withDrawStatus;//1：未取款状态取款码生成中；2 为取款状态
	private int payClient;  //还款渠道

	public int getWithDrawStatus() {
		return withDrawStatus;
	}

	public void setWithDrawStatus(int withDrawStatus) {
		this.withDrawStatus = withDrawStatus;
	}

	public String getBiller() {
		return biller;
	}

	public void setBiller(String biller) {
		this.biller = biller;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getBillId() {
		return billId;
	}

	public void setBillId(long billId) {
		this.billId = billId;
	}

	public long getRepaymentTime() {
		return repaymentTime;
	}

	public void setRepaymentTime(long repaymentTime) {
		this.repaymentTime = repaymentTime;
	}

	public String getPaymentCode() {
		return paymentCode;
	}

	public void setPaymentCode(String paymentCode) {
		this.paymentCode = paymentCode;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String showRepayDate() {
		try {
			return DateUtils.showYMDTime(repaymentTime);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public String showAmount() {
		return StringUtils.cutOutLastThree(StringUtils.doubleZheng(amount));
	}

	public int getPayClient() {
		return payClient;
	}

	public void setPayClient(int payClient) {
		this.payClient = payClient;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
}
