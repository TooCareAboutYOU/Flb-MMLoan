package com.mmkjflb.mmloan.model.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/10/28.
 */

public class CardManagerEntity {
	private List<QueryRefundInfoEntity> bankList;

	public List<QueryRefundInfoEntity> getBankList() {
		return bankList;
	}

	public void setBankList(List<QueryRefundInfoEntity> bankList) {
		this.bankList = bankList;
	}

}
