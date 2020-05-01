package com.mmkjflb.mmloan.model.entity;

import java.util.List;

/**
 * Created by
 * Date: On 2018/3/7
 * Author: wangjieyan
 * Email: wangjieyan9308@163.com
 */

public class MyBorrowBean {
	private boolean isVa;

	public boolean isVa() {
		return isVa;
	}

	public void setVa(boolean va) {
		isVa = va;
	}

	private List<MyBorrowEntity> historyLoan;
	private MyBorrowEntity currentLoan;
	private List<MyBorrowEntity> noPaymentLoan;

	public List<MyBorrowEntity> getHistoryLoan() {
		return historyLoan;
	}

	public void setHistoryLoan(List<MyBorrowEntity> historyLoan) {
		this.historyLoan = historyLoan;
	}

	public MyBorrowEntity getCurrentLoan() {
		return currentLoan;
	}

	public void setCurrentLoan(MyBorrowEntity currentLoan) {
		this.currentLoan = currentLoan;
	}

	public List<MyBorrowEntity> getNoPaymentLoan() {
		return noPaymentLoan;
	}

	public void setNoPaymentLoan(List<MyBorrowEntity> noPaymentLoan) {
		this.noPaymentLoan = noPaymentLoan;
	}

	@Override
	public String toString() {
		return "MyBorrowBean{" +
				"historyLoan=" + historyLoan +
				", currentLoan=" + currentLoan +
				", noPaymentLoan=" + noPaymentLoan +
				'}';
	}
}
