package com.mmkjflb.mmloan.model.entity;

/**
 * Created by Administrator on 2017/10/26.
 */

public class ZhiMaAuthEntity {

	/**
	 * idNumber :
	 * mobile :
	 * name :
	 * openId :
	 * success : true
	 * time :
	 * type : 5
	 * userId : 31
	 */

	private String openId;
	private boolean success;

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
}
