package com.mmkjflb.mmloan.model.entity;

/**
 * Created by Administrator on 2018/6/30.
 */

public class IsPaidEntity {
	private boolean isPaid;  //是否缴纳保证金
	private boolean isVa;  //是否生成过Va码

	public boolean isPaid() {
		return isPaid;
	}

	public void setPaid(boolean paid) {
		isPaid = paid;
	}

	public boolean isVa() {
		return isVa;
	}

	public void setVa(boolean va) {
		isVa = va;
	}
}
