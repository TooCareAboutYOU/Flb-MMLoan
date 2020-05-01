package com.mmkjflb.mmloan.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2018/6/14.
 */

public class QueryRefundInfoEntity implements Parcelable {
	private String account;
	private String accountName;
	private String bankReservedPhone;
	private String branchBankName;
	private String bankCode;
	private String bankName;

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	private double amount;

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

	public String getBranchBankName() {
		return branchBankName;
	}

	public void setBranchBankName(String branchBankName) {
		this.branchBankName = branchBankName;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}


	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.account);
		dest.writeString(this.accountName);
		dest.writeString(this.bankReservedPhone);
		dest.writeString(this.branchBankName);
		dest.writeString(this.bankCode);
		dest.writeString(this.bankName);
		dest.writeDouble(this.amount);
	}

	public QueryRefundInfoEntity() {
	}

	protected QueryRefundInfoEntity(Parcel in) {
		this.account = in.readString();
		this.accountName = in.readString();
		this.bankReservedPhone = in.readString();
		this.branchBankName = in.readString();
		this.bankCode = in.readString();
		this.bankName = in.readString();
		this.amount = in.readDouble();
	}

	public static final Parcelable.Creator<QueryRefundInfoEntity> CREATOR = new Parcelable.Creator<QueryRefundInfoEntity>() {
		@Override
		public QueryRefundInfoEntity createFromParcel(Parcel source) {
			return new QueryRefundInfoEntity(source);
		}

		@Override
		public QueryRefundInfoEntity[] newArray(int size) {
			return new QueryRefundInfoEntity[size];
		}
	};
}
