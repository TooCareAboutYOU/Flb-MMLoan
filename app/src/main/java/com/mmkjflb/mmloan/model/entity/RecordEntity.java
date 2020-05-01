package com.mmkjflb.mmloan.model.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/10/28.
 */

public class RecordEntity {
	/**
	 * groupDate : 2017-10
	 * recordVos : [{"amount":"1.01","borrowDate":"2017-10-30","groupDate":"2017-10","id":3,"period":"共1日","status":"借款已发放"}]
	 */

	private String groupDate;
	private List<RecordVosBean> recordVos;

	public String getGroupDate() {
		return groupDate;
	}

	public void setGroupDate(String groupDate) {
		this.groupDate = groupDate;
	}

	public List<RecordVosBean> getRecordVos() {
		return recordVos;
	}

	public void setRecordVos(List<RecordVosBean> recordVos) {
		this.recordVos = recordVos;
	}

	public static class RecordVosBean {

		/**
		 * amount : 2.00
		 * borrowDate : 2017-11-01
		 * groupDate : 2017-11
		 * id : 9
		 * overdueMark : 0
		 * period : 共1日
		 * repaymentDate : 2017-11-02
		 * status : 借款申请成功
		 * statusFlag : 0
		 */

		private String amount;
		private String borrowDate;
		private String groupDate;
		private int overdueMark;
		private String period;
		private String repaymentDate;
		private String status;
		private int statusFlag;

		public String getAmount() {
			return amount;
		}

		public void setAmount(String amount) {
			this.amount = amount;
		}

		public String getBorrowDate() {
			return borrowDate;
		}

		public void setBorrowDate(String borrowDate) {
			this.borrowDate = borrowDate;
		}

		public String getGroupDate() {
			return groupDate;
		}

		public void setGroupDate(String groupDate) {
			this.groupDate = groupDate;
		}

		public int getOverdueMark() {
			return overdueMark;
		}

		public void setOverdueMark(int overdueMark) {
			this.overdueMark = overdueMark;
		}

		public String getPeriod() {
			return period;
		}

		public void setPeriod(String period) {
			this.period = period;
		}

		public String getRepaymentDate() {
			return repaymentDate;
		}

		public void setRepaymentDate(String repaymentDate) {
			this.repaymentDate = repaymentDate;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public int getStatusFlag() {
			return statusFlag;
		}

		public void setStatusFlag(int statusFlag) {
			this.statusFlag = statusFlag;
		}
	}
}
