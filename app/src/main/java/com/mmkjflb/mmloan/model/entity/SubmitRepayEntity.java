package com.mmkjflb.mmloan.model.entity;

/**
 * Created by Administrator on 2017/10/29.
 */

public class SubmitRepayEntity {


	/**
	 * req_data : {"oid_partner":"201710160001025194","repayment_no":"2017-10-31B10","no_order":"1B20171031132302895422068","busi_partner":"101001","sign_type":"RSA","notify_url":"http://180.168.203.226:9090/async/orderNotify","acct_name":"许叶祥","money_order":"1.11","risk_item":{"user_info_dt_register":"20171030114923"},"sign":"joPKGGkmXz85AwB542A8wn/AQ05fAGb7LMgsVUR+8WWfKoOsmjg6wuwci54scMiS2lGCLkpeSB+kVlQRdwxw6miMdvYvk44pt2RGDtUpQeGd4E6vsYi8rR59P5wMzojshx7udhqMav8Z/600GPRWrjTieh6PzAcyvRYrkeMj9wg=","id_type":"0","repayment_plan":"{\"repaymentPlan\":[{\"amount\":1.11,\"date\":\"2017-11-01\"}]}","user_id":"3","dt_order":"20171031134019","sms_param":"{\"contract_type\":\"深圳大黄蜂征信有限公司\",\"contact_way\":\"021-61109270\"}","id_no":"340826199109022614"}
	 */

	private ReqDataBean req_data;
	private int pay_type;
	private String order_no;

	public String getOrder_no() {
		return order_no;
	}

	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}

	public int getPay_type() {
		return pay_type;
	}

	public void setPay_type(int pay_type) {
		this.pay_type = pay_type;
	}

	public ReqDataBean getReq_data() {
		return req_data;
	}

	public void setReq_data(ReqDataBean req_data) {
		this.req_data = req_data;
	}

	public static class ReqDataBean {
		/**
		 * oid_partner : 201710160001025194
		 * repayment_no : 2017-10-31B10
		 * no_order : 1B20171031132302895422068
		 * busi_partner : 101001
		 * sign_type : RSA
		 * notify_url : http://180.168.203.226:9090/async/orderNotify
		 * acct_name : 许叶祥
		 * money_order : 1.11
		 * risk_item : {"user_info_dt_register":"20171030114923"}
		 * sign : joPKGGkmXz85AwB542A8wn/AQ05fAGb7LMgsVUR+8WWfKoOsmjg6wuwci54scMiS2lGCLkpeSB+kVlQRdwxw6miMdvYvk44pt2RGDtUpQeGd4E6vsYi8rR59P5wMzojshx7udhqMav8Z/600GPRWrjTieh6PzAcyvRYrkeMj9wg=
		 * id_type : 0
		 * repayment_plan : {"repaymentPlan":[{"amount":1.11,"date":"2017-11-01"}]}
		 * user_id : 3
		 * dt_order : 20171031134019
		 * sms_param : {"contract_type":"深圳大黄蜂征信有限公司","contact_way":"021-61109270"}
		 * id_no : 340826199109022614
		 */
		private String card_no;

		public String getCard_no() {
			return card_no;
		}

		public void setCard_no(String card_no) {
			this.card_no = card_no;
		}

		private String oid_partner;
		private String repayment_no;
		private String no_order;
		private String busi_partner;
		private String sign_type;
		private String notify_url;
		private String acct_name;
		private String money_order;
		private String risk_item;
		private String sign;
		private String id_type;
		private String repayment_plan;
		private String user_id;
		private String dt_order;
		private String sms_param;
		private String id_no;

		public String getOid_partner() {
			return oid_partner;
		}

		public void setOid_partner(String oid_partner) {
			this.oid_partner = oid_partner;
		}

		public String getRepayment_no() {
			return repayment_no;
		}

		public void setRepayment_no(String repayment_no) {
			this.repayment_no = repayment_no;
		}

		public String getNo_order() {
			return no_order;
		}

		public void setNo_order(String no_order) {
			this.no_order = no_order;
		}

		public String getBusi_partner() {
			return busi_partner;
		}

		public void setBusi_partner(String busi_partner) {
			this.busi_partner = busi_partner;
		}

		public String getSign_type() {
			return sign_type;
		}

		public void setSign_type(String sign_type) {
			this.sign_type = sign_type;
		}

		public String getNotify_url() {
			return notify_url;
		}

		public void setNotify_url(String notify_url) {
			this.notify_url = notify_url;
		}

		public String getAcct_name() {
			return acct_name;
		}

		public void setAcct_name(String acct_name) {
			this.acct_name = acct_name;
		}

		public String getMoney_order() {
			return money_order;
		}

		public void setMoney_order(String money_order) {
			this.money_order = money_order;
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

		public String getId_type() {
			return id_type;
		}

		public void setId_type(String id_type) {
			this.id_type = id_type;
		}

		public String getRepayment_plan() {
			return repayment_plan;
		}

		public void setRepayment_plan(String repayment_plan) {
			this.repayment_plan = repayment_plan;
		}

		public String getUser_id() {
			return user_id;
		}

		public void setUser_id(String user_id) {
			this.user_id = user_id;
		}

		public String getDt_order() {
			return dt_order;
		}

		public void setDt_order(String dt_order) {
			this.dt_order = dt_order;
		}

		public String getSms_param() {
			return sms_param;
		}

		public void setSms_param(String sms_param) {
			this.sms_param = sms_param;
		}

		public String getId_no() {
			return id_no;
		}

		public void setId_no(String id_no) {
			this.id_no = id_no;
		}
	}
}
