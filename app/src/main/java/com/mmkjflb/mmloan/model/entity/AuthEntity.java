package com.mmkjflb.mmloan.model.entity;

/**
 * Created by Administrator on 2017/11/1.
 */

public class AuthEntity {
	/**
	 * req_data : {"acct_name":"何强","card_no":"6217850800014602443","id_no":"320723199011103231","oid_partner":"201710160001025194","risk_item":"cesgu","sign":"0ae2aa3086c14e314dac6e355a8dd11c","sign_type":"MD5","user_id":"201711031527420026"}
	 */

	private ReqDataBean req_data;

	public ReqDataBean getReq_data() {
		return req_data;
	}

	public void setReq_data(ReqDataBean req_data) {
		this.req_data = req_data;
	}

	public static class ReqDataBean {
		/**
		 * acct_name : 何强
		 * card_no : 6217850800014602443
		 * id_no : 320723199011103231
		 * oid_partner : 201710160001025194
		 * risk_item : cesgu
		 * sign : 0ae2aa3086c14e314dac6e355a8dd11c
		 * sign_type : MD5
		 * user_id : 201711031527420026
		 */

		private String acct_name;
		private String card_no;
		private String id_no;
		private String oid_partner;
		private String risk_item;
		private String sign;
		private String sign_type;
		private String user_id;

		public String getAcct_name() {
			return acct_name;
		}

		public void setAcct_name(String acct_name) {
			this.acct_name = acct_name;
		}

		public String getCard_no() {
			return card_no;
		}

		public void setCard_no(String card_no) {
			this.card_no = card_no;
		}

		public String getId_no() {
			return id_no;
		}

		public void setId_no(String id_no) {
			this.id_no = id_no;
		}

		public String getOid_partner() {
			return oid_partner;
		}

		public void setOid_partner(String oid_partner) {
			this.oid_partner = oid_partner;
		}

		public String getRisk_item() {
			return risk_item;
		}

		public void setRisk_item(String risk_item) {
			this.risk_item = risk_item;
		}

		public String getSign() {
			return sign;
		}

		public void setSign(String sign) {
			this.sign = sign;
		}

		public String getSign_type() {
			return sign_type;
		}

		public void setSign_type(String sign_type) {
			this.sign_type = sign_type;
		}

		public String getUser_id() {
			return user_id;
		}

		public void setUser_id(String user_id) {
			this.user_id = user_id;
		}
	}
}
