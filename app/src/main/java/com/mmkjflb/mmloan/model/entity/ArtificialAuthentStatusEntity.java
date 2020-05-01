package com.mmkjflb.mmloan.model.entity;

import com.google.gson.Gson;

/**
 * Created by Administrator on 2017/10/27.
 */

public class ArtificialAuthentStatusEntity {
	/**
	 * auditStatus  auditStatus:1 等待页面 2 成功 3.失败  7 驳回   100: 重新审核(生成取款码，7天后未取款)
	 */
	private int auditStatus;
	private int machineStatus;
	private boolean   isPaid;  //是否缴纳保证金
	private boolean isVa; //是否显示va状态码
	private String auditSuggest;


	public boolean isPaid() {
		return isPaid;
	}

	public void setPaid(boolean paid) {
		isPaid = paid;
	}

	public int getMachineStatus() {
		return machineStatus;
	}

	public void setMachineStatus(int machineStatus) {
		this.machineStatus = machineStatus;
	}

	public int getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(int auditStatus) {
		this.auditStatus = auditStatus;
	}

	public String getAuditSuggest() {
		return auditSuggest;
	}

	public void setAuditSuggest(String auditSuggest) {
		this.auditSuggest = auditSuggest;
	}

	public boolean isVa() {
		return isVa;
	}

	public void setVa(boolean va) {
		isVa = va;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
}
