package com.mmkjflb.mmloan.model.entity;

public class SpecialMenuEntity {
	private Boolean isCashDeposit;//是否交纳保证金
	private Boolean isCashDepositVa;//是否生成保证金Va码
	private Boolean isAuditDeposit;//是否交纳信审金

	public Boolean getCashDeposit() {
		return isCashDeposit;
	}

	public void setCashDeposit(Boolean cashDeposit) {
		isCashDeposit = cashDeposit;
	}

	public Boolean getCashDepositVa() {
		return isCashDepositVa;
	}

	public void setCashDepositVa(Boolean cashDepositVa) {
		isCashDepositVa = cashDepositVa;
	}

	public Boolean getAuditDeposit() {
		return isAuditDeposit;
	}

	public void setAuditDeposit(Boolean auditDeposit) {
		isAuditDeposit = auditDeposit;
	}
}
