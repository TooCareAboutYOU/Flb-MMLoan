package com.mmkj.usercenter.model.entity;

import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Author: wendjia
 * Time: 2018\9\5 0005
 * Description:
 **/
public class PaymentData {
	private ArrayList<PaymentListData> returnedBills;
	private CurrentPaymentData shouldBills;

	public ArrayList<PaymentListData> getReturnedBills() {
		return returnedBills;
	}

	public void setReturnedBills(ArrayList<PaymentListData> returnedBills) {
		this.returnedBills = returnedBills;
	}

	public CurrentPaymentData getShouldBills() {
		return shouldBills;
	}

	public void setShouldBills(CurrentPaymentData shouldBills) {
		this.shouldBills = shouldBills;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
}
