package com.mmkjflb.mmloan.model.entity;

/**
 * Created by Administrator on 2017/10/24.
 */

public class AuthentStatusEntity {
	/**
	 "contact": 0, 联系人认证 0 未认证 1认证通过
	 "verified": 0, 身份信息认证标记 0:没认证状态 1:通过认证
	 "employ": 0,   就业认证 0:没认证状态 1:通过认证
	 "basic": 0,    用户基本信息 0:未填写 1:已经填写
//	 "social": 0,   社交信息 0:未填写 1:已经填写
	 "asserts": 0,  资产信息 0:未填写 1:已经填写
//	 "bank": 0      银行信息 0:未填写 1:已经填写
//	 "isOcr" 是否需要去活体识别 0 需要 1不需要
	 "extraCredit"  额外信用  0:未认证 1:已认证
	 "auditStatus" 是否驳回  0:第一次，1:驳回
	 */
	private int contact;
	private int verified;
	private int employ;
	private int basic;
	private int asserts;
//	private int bank;
	private int performance;
//	private int isOcr;
	private int extraCredit;
	private int auditStatus;

	public int getContact() {
		return contact;
	}

	public void setContact(int contact) {
		this.contact = contact;
	}

	public int getVerified() {
		return verified;
	}

	public void setVerified(int verified) {
		this.verified = verified;
	}

	public int getEmploy() {
		return employ;
	}

	public void setEmploy(int employ) {
		this.employ = employ;
	}

	public int getBasic() {
		return basic;
	}

	public void setBasic(int basic) {
		this.basic = basic;
	}

	public int getAsserts() {
		return asserts;
	}

	public void setAsserts(int asserts) {
		this.asserts = asserts;
	}

	public int getExtraCredit() {
		return extraCredit;
	}

	public void setExtraCredit(int extraCredit) {
		this.extraCredit = extraCredit;
	}

	public int getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(int auditStatus) {
		this.auditStatus = auditStatus;
	}

	public int getPerformance() {
		return performance;
	}

	public void setPerformance(int performance) {
		this.performance = performance;
	}

}
