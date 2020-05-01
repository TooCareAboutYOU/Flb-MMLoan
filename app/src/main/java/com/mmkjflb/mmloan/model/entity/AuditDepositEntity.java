package com.mmkjflb.mmloan.model.entity;

public class AuditDepositEntity {
	//	account：账号,accountName :户名 ,bankReservedPhone:银行预留手机号 , amount：退款金额
//	status:-1.失败,1.成功,2.处理中,3.拒绝(没资格),4.转保证金
	private int status;
	private String account;
	private String accountName;
	private String bankReservedPhone;
	private double amount;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getBankReservedPhone() {
		return bankReservedPhone;
	}

	public void setBankReservedPhone(String bankReservedPhone) {
		this.bankReservedPhone = bankReservedPhone;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
}
