package com.mmkjflb.mmloan.model.entity;

import com.bigkoo.pickerview.model.IPickerViewData;

/**
 * @author xuyexiang
 * @date 2018/5/25 13:57
 */

public class QueryPaymentChannelEntity implements IPickerViewData {
	/**
	 * pg_code : 707
	 * pg_name : ALFAGROUP
	 */

	private String pg_code;
	private String pg_name;

	public String getPg_code() {
		return pg_code;
	}

	public void setPg_code(String pg_code) {
		this.pg_code = pg_code;
	}

	public String getPg_name() {
		return pg_name;
	}

	public void setPg_name(String pg_name) {
		this.pg_name = pg_name;
	}

	@Override
	public String getPickerViewText() {
		return pg_name;
	}
}
