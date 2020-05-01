package com.mmkjflb.mmloan.model.entity;

/**
 * Created by Administrator on 2017/10/26.
 */

public class OcrCheckEntity {




	private int code;
	private DataBean data;
	private String info;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public DataBean getData() {
		return data;
	}

	public void setData(DataBean data) {
		this.data = data;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public static class DataBean {
		private String name;
		private String certid;
		private String issue_authority;
		private String vaild_priod;

		public String getIssue_authority() {
			return issue_authority;
		}

		public void setIssue_authority(String issue_authority) {
			this.issue_authority = issue_authority;
		}

		public String getVaild_priod() {
			return vaild_priod;
		}

		public void setVaild_priod(String vaild_priod) {
			this.vaild_priod = vaild_priod;
		}


		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getCertid() {
			return certid;
		}

		public void setCertid(String certid) {
			this.certid = certid;
		}

	}
}
