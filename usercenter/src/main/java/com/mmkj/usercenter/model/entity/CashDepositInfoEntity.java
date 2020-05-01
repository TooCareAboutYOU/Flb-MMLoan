package com.mmkj.usercenter.model.entity;

import java.math.BigDecimal;

/**
 * @author zhangshuai
 *
 */
public class CashDepositInfoEntity {
    private BigDecimal amount;
    private BigDecimal feeRate;
    private BigDecimal feeProcedurePay;
    private BigDecimal feeProcedureRepay;
    private int productDeadline;
    private BigDecimal actualArrivalAmount;
    private String code;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getFeeRate() {
        return feeRate;
    }

    public void setFeeRate(BigDecimal feeRate) {
        this.feeRate = feeRate;
    }

    public BigDecimal getFeeProcedurePay() {
        return feeProcedurePay;
    }

    public void setFeeProcedurePay(BigDecimal feeProcedurePay) {
        this.feeProcedurePay = feeProcedurePay;
    }

    public BigDecimal getFeeProcedureRepay() {
        return feeProcedureRepay;
    }

    public void setFeeProcedureRepay(BigDecimal feeProcedureRepay) {
        this.feeProcedureRepay = feeProcedureRepay;
    }

    public int getProductDeadline() {
        return productDeadline;
    }

    public void setProductDeadline(int productDeadline) {
        this.productDeadline = productDeadline;
    }

    public BigDecimal getActualArrivalAmount() {
        return actualArrivalAmount;
    }

    public void setActualArrivalAmount(BigDecimal actualArrivalAmount) {
        this.actualArrivalAmount = actualArrivalAmount;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
