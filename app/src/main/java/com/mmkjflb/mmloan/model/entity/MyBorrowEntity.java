package com.mmkjflb.mmloan.model.entity;

/**
 * Created by
 * Date: On 2018/3/7
 * Author: wangjieyan
 * Email: wangjieyan9308@163.com
 */

public class MyBorrowEntity {

//            "bidId":2,
//            "billsId":2,
//            "amount":null,
//            "loadDate":1520352000000,
//            "loadPeriod":"7个月",
//            "loadType": "0",
//            "periodValue": "0",
//            "repaymentDate":1521093696000,
//            "status":3,
//            "statusShow":null
    private long bidId;
    private int billsId;
    private double amount;
    private long loadDate;
    private String loadPeriod;
    private int loadType;
    private int periodValue;
    private long repaymentDate;
    private int status;
    private String statusShow;

    public int getLoadType() {
        return loadType;
    }

    public void setLoadType(int loadType) {
        this.loadType = loadType;
    }

    public int getPeriodValue() {
        return periodValue;
    }

    public void setPeriodValue(int periodValue) {
        this.periodValue = periodValue;
    }

    public long getBidId() {
        return bidId;
    }

    public void setBidId(long bidId) {
        this.bidId = bidId;
    }

    public int getBillsId() {
        return billsId;
    }

    public void setBillsId(int billsId) {
        this.billsId = billsId;
    }

    public long getLoadDate() {
        return loadDate;
    }

    public void setLoadDate(long loadDate) {
        this.loadDate = loadDate;
    }

    public String getLoadPeriod() {
        return loadPeriod;
    }

    public void setLoadPeriod(String loadPeriod) {
        this.loadPeriod = loadPeriod;
    }

    public long getRepaymentDate() {
        return repaymentDate;
    }

    public void setRepaymentDate(long repaymentDate) {
        this.repaymentDate = repaymentDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusShow() {
        return statusShow;
    }

    public void setStatusShow(String statusShow) {
        this.statusShow = statusShow;
    }

    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "MyBorrowEntity{" +
                "bidId=" + bidId +
                ", billsId=" + billsId +
                ", amount=" + amount +
                ", loadDate=" + loadDate +
                ", loadPeriod='" + loadPeriod + '\'' +
                ", loadType=" + loadType +
                ", periodValue=" + periodValue +
                ", repaymentDate=" + repaymentDate +
                ", status=" + status +
                ", statusShow='" + statusShow + '\'' +
                '}';
    }
}
