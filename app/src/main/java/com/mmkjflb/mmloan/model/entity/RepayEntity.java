package com.mmkjflb.mmloan.model.entity;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by Administrator on 2017/10/29.
 */

public class RepayEntity {

    private List<InfoBean> info;
    private int status;
    private PayDetail products;
    private int isCode;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<InfoBean> getInfo() {
        return info;
    }

    public void setInfo(List<InfoBean> info) {
        this.info = info;
    }

    public PayDetail getProducts() {
        return products;
    }

    public void setProducts(PayDetail products) {
        this.products = products;
    }

    public static class PayDetail {

        /**
         * id : 2
         * updateTime : 1520238105000
         * createTime : 1507622864000
         * productCode : sevencode
         * productName : null
         * briefIntro :
         * nameImageFilename : null
         * amount : 2500
         * interestRate : 5.0E-4
         * productDeadline : 7
         * deadlineUnit : 0
         * feeRate : 500
         * feeProcedurePay : 140
         * feeProcedureRepay : 22.5
         * extendAmount : null
         * cashDeposit : null
         * isUse : true
         */

        private int id;
        private long updateTime;
        private long createTime;
        private String productCode;
        private Object productName;
        private String briefIntro;
        private Object nameImageFilename;
        private int amount;
        private double interestRate;
        private int productDeadline;
        private int deadlineUnit;
        private int feeRate;
        private double feeProcedurePay;
        private double feeProcedureRepay;
        private Object extendAmount;
        private Object cashDeposit;
        private boolean isUse;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getProductCode() {
            return productCode;
        }

        public void setProductCode(String productCode) {
            this.productCode = productCode;
        }

        public Object getProductName() {
            return productName;
        }

        public void setProductName(Object productName) {
            this.productName = productName;
        }

        public String getBriefIntro() {
            return briefIntro;
        }

        public void setBriefIntro(String briefIntro) {
            this.briefIntro = briefIntro;
        }

        public Object getNameImageFilename() {
            return nameImageFilename;
        }

        public void setNameImageFilename(Object nameImageFilename) {
            this.nameImageFilename = nameImageFilename;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public double getInterestRate() {
            return interestRate;
        }

        public void setInterestRate(double interestRate) {
            this.interestRate = interestRate;
        }

        public int getProductDeadline() {
            return productDeadline;
        }

        public void setProductDeadline(int productDeadline) {
            this.productDeadline = productDeadline;
        }

        public int getDeadlineUnit() {
            return deadlineUnit;
        }

        public void setDeadlineUnit(int deadlineUnit) {
            this.deadlineUnit = deadlineUnit;
        }

        public int getFeeRate() {
            return feeRate;
        }

        public void setFeeRate(int feeRate) {
            this.feeRate = feeRate;
        }


        public void setFeeProcedurePay(int feeProcedurePay) {
            this.feeProcedurePay = feeProcedurePay;
        }

        public double getFeeProcedureRepay() {
            return feeProcedureRepay;
        }

        public void setFeeProcedureRepay(double feeProcedureRepay) {
            this.feeProcedureRepay = feeProcedureRepay;
        }

        public Object getExtendAmount() {
            return extendAmount;
        }

        public void setExtendAmount(Object extendAmount) {
            this.extendAmount = extendAmount;
        }

        public Object getCashDeposit() {
            return cashDeposit;
        }

        public void setCashDeposit(Object cashDeposit) {
            this.cashDeposit = cashDeposit;
        }

        public boolean isIsUse() {
            return isUse;
        }

        public void setIsUse(boolean isUse) {
            this.isUse = isUse;
        }

        public double getFeeProcedurePay() {
            return feeProcedurePay;
        }

        public void setFeeProcedurePay(double feeProcedurePay) {
            this.feeProcedurePay = feeProcedurePay;
        }

        @Override
        public String toString() {
            return new Gson().toJson(this);
        }
    }

    public static class InfoBean {

        private int bidsId;
        private int billType;
        private long createTime;
        private int extendPeriod;
        private int feeRate;
        private int id;
        private int loanIntention;
        private int overdueDay;
        private double overdueFine;
        private int overdueMark;
        private double payableAmout;
        private int periods;
        private double realRepaymentCorpus;
        private double realRepaymentInterest;
        private String repaymentBillNo;
        private double repaymentCorpus;
        private double repaymentInterest;
        private long repaymentTime;
        private int status;
        private long updateTime;
        private int urgeStatus;
        private long userId;
        private int version;

        public int getBidsId() {
            return bidsId;
        }

        public void setBidsId(int bidsId) {
            this.bidsId = bidsId;
        }


        public int getFeeRate() {
            return feeRate;
        }

        public void setFeeRate(int feeRate) {
            this.feeRate = feeRate;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public double getOverdueFine() {
            return overdueFine;
        }

        public void setOverdueFine(double overdueFine) {
            this.overdueFine = overdueFine;
        }


        public double getPayableAmout() {
            return payableAmout;
        }

        public void setPayableAmout(double payableAmout) {
            this.payableAmout = payableAmout;
        }


        public double getRepaymentCorpus() {
            return repaymentCorpus;
        }

        public void setRepaymentCorpus(double repaymentCorpus) {
            this.repaymentCorpus = repaymentCorpus;
        }

        public double getRepaymentInterest() {
            return repaymentInterest;
        }

        public void setRepaymentInterest(double repaymentInterest) {
            this.repaymentInterest = repaymentInterest;
        }

        public long getRepaymentTime() {
            return repaymentTime;
        }

        public void setRepaymentTime(long repaymentTime) {
            this.repaymentTime = repaymentTime;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }


        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        public double getRealRepaymentCorpus() {
            return realRepaymentCorpus;
        }

        public void setRealRepaymentCorpus(double realRepaymentCorpus) {
            this.realRepaymentCorpus = realRepaymentCorpus;
        }

        public double getRealRepaymentInterest() {
            return realRepaymentInterest;
        }

        public void setRealRepaymentInterest(double realRepaymentInterest) {
            this.realRepaymentInterest = realRepaymentInterest;
        }

        @Override
        public String toString() {
            return new Gson().toJson(this);
        }
    }

    public int getIsCode() {
        return isCode;
    }

    public void setIsCode(int isCode) {
        this.isCode = isCode;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
