package com.mmkjflb.mmloan.model.entity;

/**
 * Created by Administrator on 2017/10/28.
 */

public class LoanEntity {

	private double amount;//借款金额
	private int deadlineUnit;
	private double feeProcedurePay;
	private double feeProcedureRepay;
	private double feeRate;//服务费
	private double grossInterest;
	private int loanDate;//借款期限
	private int productId;
	private double realAmount;
	private String repaymentTime;

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getDeadlineUnit() {
		return deadlineUnit;
	}

	public void setDeadlineUnit(int deadlineUnit) {
		this.deadlineUnit = deadlineUnit;
	}

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

	public double getFeeRate() {
		return feeRate;
	}

	public void setFeeRate(double feeRate) {
		this.feeRate = feeRate;
	}

	public double getGrossInterest() {
		return grossInterest;
	}

	public void setGrossInterest(double grossInterest) {
		this.grossInterest = grossInterest;
	}

	public int getLoanDate() {
		return loanDate;
	}

	public void setLoanDate(int loanDate) {
		this.loanDate = loanDate;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public double getRealAmount() {
		return realAmount;
	}

	public void setRealAmount(double realAmount) {
		this.realAmount = realAmount;
	}

	public String getRepaymentTime() {
		return repaymentTime;
	}

	public void setRepaymentTime(String repaymentTime) {
		this.repaymentTime = repaymentTime;
	}





}
