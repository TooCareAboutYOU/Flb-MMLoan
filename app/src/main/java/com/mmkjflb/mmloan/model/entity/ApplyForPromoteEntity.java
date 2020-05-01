package com.mmkjflb.mmloan.model.entity;

/**
 * Created by Administrator on 2018/6/29.
 */

public class ApplyForPromoteEntity {
	private double payMentAmount;//支付金额

	private double cashDepositAmount;//保证金金额

	private double auditDepositAmount;//信审金金额

	public double getPayMentAmount() {
		return payMentAmount;
	}

	public void setPayMentAmount(double payMentAmount) {
		this.payMentAmount = payMentAmount;
	}

	public double getCashDepositAmount() {
		return cashDepositAmount;
	}

	public void setCashDepositAmount(double cashDepositAmount) {
		this.cashDepositAmount = cashDepositAmount;
	}

	public double getAuditDepositAmount() {
		return auditDepositAmount;
	}

	public void setAuditDepositAmount(double auditDepositAmount) {
		this.auditDepositAmount = auditDepositAmount;
	}
}
