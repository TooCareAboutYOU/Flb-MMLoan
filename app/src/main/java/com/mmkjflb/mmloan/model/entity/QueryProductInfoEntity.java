package com.mmkjflb.mmloan.model.entity;

/**
 * Created by Administrator on 2018/6/28.
 */

public class QueryProductInfoEntity {
	/**
	 * cashDeposit : 600000
	 * promoteAmount : 1200000.0
	 * promoteDeadline : 14
	 * promoteUnit : 0
	 */
	private double cashDeposit;  //保证金金额
	private double promoteAmount; //提升后额度
	private int promoteDeadline;
	private int promoteUnit;

	public double getCashDeposit() {
		return cashDeposit;
	}

	public void setCashDeposit(double cashDeposit) {
		this.cashDeposit = cashDeposit;
	}

	public double getPromoteAmount() {
		return promoteAmount;
	}

	public void setPromoteAmount(double promoteAmount) {
		this.promoteAmount = promoteAmount;
	}

	public int getPromoteDeadline() {
		return promoteDeadline;
	}

	public void setPromoteDeadline(int promoteDeadline) {
		this.promoteDeadline = promoteDeadline;
	}

	public int getPromoteUnit() {
		return promoteUnit;
	}

	public void setPromoteUnit(int promoteUnit) {
		this.promoteUnit = promoteUnit;
	}
}
