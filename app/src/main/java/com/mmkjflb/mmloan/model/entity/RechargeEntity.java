package com.mmkjflb.mmloan.model.entity;

/**
 * Created by Administrator on 2017/10/28.
 */

public class RechargeEntity {

	/**
	 * req_data : {"acct_name":"何强","busi_partner":"101001","dt_order":"20171103153301","id_no":"320723199011103231","info_order":"手机端充值0.01","money_order":"0.01","name_goods":"账户充值","no_order":"3X20171103152742002620171103153301630517","notify_url":"http://180.168.203.226:9090/async/orderNotify","oid_partner":"201710160001025194","sign":"45a1fb0fcff88d57540df74c6bb84c6e","sign_type":"MD5","url_return":"http://180.168.203.226:9090/async/orderNotify","user_id":"201711031527420026"}
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
		 * busi_partner : 101001
		 * dt_order : 20171103153301
		 * id_no : 320723199011103231
		 * info_order : 手机端充值0.01
		 * money_order : 0.01
		 * name_goods : 账户充值
		 * no_order : 3X20171103152742002620171103153301630517
		 * notify_url : http://180.168.203.226:9090/async/orderNotify
		 * oid_partner : 201710160001025194
		 * sign : 45a1fb0fcff88d57540df74c6bb84c6e
		 * sign_type : MD5
		 * url_return : http://180.168.203.226:9090/async/orderNotify
		 * user_id : 201711031527420026
		 */
		private String risk_item;

		public String getRisk_item() {
			return risk_item;
		}

		public void setRisk_item(String risk_item) {
			this.risk_item = risk_item;
		}

		private String acct_name;
		private String busi_partner;
		private String dt_order;
		private String id_no;
		private String info_order;
		private String money_order;
		private String name_goods;
		private String no_order;
		private String notify_url;
		private String oid_partner;
		private String sign;
		private String sign_type;
		private String url_return;
		private String user_id;

		public String getAcct_name() {
			return acct_name;
		}

		public void setAcct_name(String acct_name) {
			this.acct_name = acct_name;
		}

		public String getBusi_partner() {
			return busi_partner;
		}

		public void setBusi_partner(String busi_partner) {
			this.busi_partner = busi_partner;
		}

		public String getDt_order() {
			return dt_order;
		}

		public void setDt_order(String dt_order) {
			this.dt_order = dt_order;
		}

		public String getId_no() {
			return id_no;
		}

		public void setId_no(String id_no) {
			this.id_no = id_no;
		}

		public String getInfo_order() {
			return info_order;
		}

		public void setInfo_order(String info_order) {
			this.info_order = info_order;
		}

		public String getMoney_order() {
			return money_order;
		}

		public void setMoney_order(String money_order) {
			this.money_order = money_order;
		}

		public String getName_goods() {
			return name_goods;
		}

		public void setName_goods(String name_goods) {
			this.name_goods = name_goods;
		}

		public String getNo_order() {
			return no_order;
		}

		public void setNo_order(String no_order) {
			this.no_order = no_order;
		}

		public String getNotify_url() {
			return notify_url;
		}

		public void setNotify_url(String notify_url) {
			this.notify_url = notify_url;
		}

		public String getOid_partner() {
			return oid_partner;
		}

		public void setOid_partner(String oid_partner) {
			this.oid_partner = oid_partner;
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

		public String getUrl_return() {
			return url_return;
		}

		public void setUrl_return(String url_return) {
			this.url_return = url_return;
		}

		public String getUser_id() {
			return user_id;
		}

		public void setUser_id(String user_id) {
			this.user_id = user_id;
		}
	}
}
