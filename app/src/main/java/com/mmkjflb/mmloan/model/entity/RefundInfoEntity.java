package com.mmkjflb.mmloan.model.entity;

import java.math.BigDecimal;

public class RefundInfoEntity {


    /**
     * amount : 2500
     * payFee : 140
     * repayFee : 25.2
     * realRefundAmount : 2334.8
     */
    private BigDecimal amount;
    private BigDecimal payFee;
    private BigDecimal repayFee;
    private BigDecimal realRefundAmount;

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setPayFee(BigDecimal payFee) {
        this.payFee = payFee;
    }

    public void setRepayFee(BigDecimal repayFee) {
        this.repayFee = repayFee;
    }

    public void setRealRefundAmount(BigDecimal realRefundAmount) {
        this.realRefundAmount = realRefundAmount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getPayFee() {
        return payFee;
    }

    public BigDecimal getRepayFee() {
        return repayFee;
    }

    public BigDecimal getRealRefundAmount() {
        return realRefundAmount;
    }
}
