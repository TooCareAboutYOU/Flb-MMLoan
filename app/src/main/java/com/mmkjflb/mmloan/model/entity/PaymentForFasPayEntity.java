package com.mmkjflb.mmloan.model.entity;

import java.util.List;

/**
 * @author xuyexiang
 * @date 2018/5/25 16:04
 */

public class PaymentForFasPayEntity {
	/**
	 * trx_id : 3213240100000018
	 * bill_items : [{"amount":"60210000","product":"Uang Express","tenor":"00","qty":"1","merchant_id":"32132","payment_plan":"1"}]
	 * response_code : 00
	 * response_desc : Sukses
	 * response : Transmisi Info Detil Pembelian
	 * merchant : Uang Express
	 * bill_no : 86B20180525161841191502427
	 * merchant_id : 32132
	 * redirect_url : https://dev.faspay.co.id/pws/100003/0830000010100000/7644e24dc0c82a7824be5a11113454a0bb7a3cc5?trx_id=3213240100000018&merchant_id=32132&bill_no=86B20180525161841191502427
	 */

	private String trx_id;
	private String response_code;
	private String response_desc;
	private String response;
	private String merchant;
	private String bill_no;
	private String merchant_id;
	private String redirect_url;
	private List<BillItemsBean> bill_items;

	public String getTrx_id() {
		return trx_id;
	}

	public void setTrx_id(String trx_id) {
		this.trx_id = trx_id;
	}

	public String getResponse_code() {
		return response_code;
	}

	public void setResponse_code(String response_code) {
		this.response_code = response_code;
	}

	public String getResponse_desc() {
		return response_desc;
	}

	public void setResponse_desc(String response_desc) {
		this.response_desc = response_desc;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public String getMerchant() {
		return merchant;
	}

	public void setMerchant(String merchant) {
		this.merchant = merchant;
	}

	public String getBill_no() {
		return bill_no;
	}

	public void setBill_no(String bill_no) {
		this.bill_no = bill_no;
	}

	public String getMerchant_id() {
		return merchant_id;
	}

	public void setMerchant_id(String merchant_id) {
		this.merchant_id = merchant_id;
	}

	public String getRedirect_url() {
		return redirect_url;
	}

	public void setRedirect_url(String redirect_url) {
		this.redirect_url = redirect_url;
	}

	public List<BillItemsBean> getBill_items() {
		return bill_items;
	}

	public void setBill_items(List<BillItemsBean> bill_items) {
		this.bill_items = bill_items;
	}

	public static class BillItemsBean {
		/**
		 * amount : 60210000
		 * product : Uang Express
		 * tenor : 00
		 * qty : 1
		 * merchant_id : 32132
		 * payment_plan : 1
		 */

		private String amount;
		private String product;
		private String tenor;
		private String qty;
		private String merchant_id;
		private String payment_plan;

		public String getAmount() {
			return amount;
		}

		public void setAmount(String amount) {
			this.amount = amount;
		}

		public String getProduct() {
			return product;
		}

		public void setProduct(String product) {
			this.product = product;
		}

		public String getTenor() {
			return tenor;
		}

		public void setTenor(String tenor) {
			this.tenor = tenor;
		}

		public String getQty() {
			return qty;
		}

		public void setQty(String qty) {
			this.qty = qty;
		}

		public String getMerchant_id() {
			return merchant_id;
		}

		public void setMerchant_id(String merchant_id) {
			this.merchant_id = merchant_id;
		}

		public String getPayment_plan() {
			return payment_plan;
		}

		public void setPayment_plan(String payment_plan) {
			this.payment_plan = payment_plan;
		}
	}
}
