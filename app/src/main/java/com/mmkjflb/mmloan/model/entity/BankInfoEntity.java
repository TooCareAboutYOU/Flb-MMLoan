package com.mmkjflb.mmloan.model.entity;

/**
 * @author xuyexiang
 * @date 2018/5/15 14:43
 */

public class BankInfoEntity {
	private String account;
	private String bankName;
	private String bankCode;
	private String bankReservedPhone;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBankReservedPhone() {
		return bankReservedPhone;
	}

	public void setBankReservedPhone(String bankReservedPhone) {
		this.bankReservedPhone = bankReservedPhone;
	}
}
