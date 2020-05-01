package com.mmkj.usercenter.model.entity;

import com.google.gson.Gson;

/**
 * Author: wendjia
 * Time: 2018\9\5 0005
 * Description:
 **/
public class PaymentListData {
	private Long id;

	private long createTime;

	private long updateTime;

	private Long bidsId;

	private Long userId;

	private Integer periods;

	private long repaymentTime;

	private double repaymentCorpus;

	private double feeRate;

	private double repaymentInterest;

	private Integer status;

	private Integer urgeStatus;

	private String merBillNo;

	private String repaymentBillNo;

	private long realRepaymentTime;

	private double realRepaymentCorpus;

	private double realRepaymentInterest;

	private Integer overdueMark;

	private long markOverdueTime;

	private double overdueFine;

	private long paymentTime;

	private Integer version;

	private Integer overdueDay;

	private Integer loanIntention;

	private Integer billType;

	private Integer extendPeriod;

	private String orderNo;

	private double payableAmout;

	private double feeProcedurePay; //取款手续费

	private double feeProcedureRepay; //还款手续费

	public double getFeeProcedurePay() {
		return feeProcedurePay;
	}

	public void setFeeProcedurePay(double feeProcedurePay) {
		this.feeProcedurePay = feeProcedurePay;
	}

	public double getFeeProcedureRepay() {
		return feeProcedureRepay;
	}

	public void setFeeProcedureRepay(double feeProcedureRepay) {
		this.feeProcedureRepay = feeProcedureRepay;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(long updateTime) {
		this.updateTime = updateTime;
	}

	public Long getBidsId() {
		return bidsId;
	}

	public void setBidsId(Long bidsId) {
		this.bidsId = bidsId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getPeriods() {
		return periods;
	}

	public void setPeriods(Integer periods) {
		this.periods = periods;
	}

	public long getRepaymentTime() {
		return repaymentTime;
	}

	public void setRepaymentTime(long repaymentTime) {
		this.repaymentTime = repaymentTime;
	}

	public double getRepaymentCorpus() {
		return repaymentCorpus;
	}

	public void setRepaymentCorpus(double repaymentCorpus) {
		this.repaymentCorpus = repaymentCorpus;
	}

	public double getFeeRate() {
		return feeRate;
	}

	public void setFeeRate(double feeRate) {
		this.feeRate = feeRate;
	}

	public double getRepaymentInterest() {
		return repaymentInterest;
	}

	public void setRepaymentInterest(double repaymentInterest) {
		this.repaymentInterest = repaymentInterest;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getUrgeStatus() {
		return urgeStatus;
	}

	public void setUrgeStatus(Integer urgeStatus) {
		this.urgeStatus = urgeStatus;
	}

	public String getMerBillNo() {
		return merBillNo;
	}

	public void setMerBillNo(String merBillNo) {
		this.merBillNo = merBillNo;
	}

	public String getRepaymentBillNo() {
		return repaymentBillNo;
	}

	public void setRepaymentBillNo(String repaymentBillNo) {
		this.repaymentBillNo = repaymentBillNo;
	}

	public long getRealRepaymentTime() {
		return realRepaymentTime;
	}

	public void setRealRepaymentTime(long realRepaymentTime) {
		this.realRepaymentTime = realRepaymentTime;
	}

	public double getRealRepaymentCorpus() {
		return realRepaymentCorpus;
	}

	public void setRealRepaymentCorpus(double realRepaymentCorpus) {
		this.realRepaymentCorpus = realRepaymentCorpus;
	}

	public double getRealRepaymentInterest() {
		return realRepaymentInterest;
	}

	public void setRealRepaymentInterest(double realRepaymentInterest) {
		this.realRepaymentInterest = realRepaymentInterest;
	}

	public Integer getOverdueMark() {
		return overdueMark;
	}

	public void setOverdueMark(Integer overdueMark) {
		this.overdueMark = overdueMark;
	}

	public long getMarkOverdueTime() {
		return markOverdueTime;
	}

	public void setMarkOverdueTime(long markOverdueTime) {
		this.markOverdueTime = markOverdueTime;
	}

	public double getOverdueFine() {
		return overdueFine;
	}

	public void setOverdueFine(double overdueFine) {
		this.overdueFine = overdueFine;
	}

	public long getPaymentTime() {
		return paymentTime;
	}

	public void setPaymentTime(long paymentTime) {
		this.paymentTime = paymentTime;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getOverdueDay() {
		return overdueDay;
	}

	public void setOverdueDay(Integer overdueDay) {
		this.overdueDay = overdueDay;
	}

	public Integer getLoanIntention() {
		return loanIntention;
	}

	public void setLoanIntention(Integer loanIntention) {
		this.loanIntention = loanIntention;
	}

	public Integer getBillType() {
		return billType;
	}

	public void setBillType(Integer billType) {
		this.billType = billType;
	}

	public Integer getExtendPeriod() {
		return extendPeriod;
	}

	public void setExtendPeriod(Integer extendPeriod) {
		this.extendPeriod = extendPeriod;
	}

	public double getPayableAmout() {
		return payableAmout;
	}

	public void setPayableAmout(double payableAmout) {
		this.payableAmout = payableAmout;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
}
