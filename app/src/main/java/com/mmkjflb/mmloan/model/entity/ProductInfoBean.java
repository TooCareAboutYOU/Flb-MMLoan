package com.mmkjflb.mmloan.model.entity;

import com.google.gson.Gson;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * amount: 借款金额,
 * feeRate: 服务费,
 * feeProcedurePay: 打款手续费,
 * feeProcedureRepay: 还款手续费,
 * productDeadline: 周期,
 * actualArrivalAmount: 实际到账金额
 */
public class ProductInfoBean implements Serializable {
    private double amount;
    private double feeRate;
    private double feeProcedurePay;
    private double feeProcedureRepay;
    private int productDeadline;
    private double actualArrivalAmount;
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getFeeRate() {
        return feeRate;
    }

    public void setFeeRate(double feeRate) {
        this.feeRate = feeRate;
    }

    public double getFeeProcedurePay() {
        return feeProcedurePay;
    }

    public void setFeeProcedurePay(double feeProcedurePay) {
        this.feeProcedurePay = feeProcedurePay;
    }

    public double getFeeProcedureRepay() {
        return feeProcedureRepay;
    }

    public void setFeeProcedureRepay(double feeProcedureRepay) {
        this.feeProcedureRepay = feeProcedureRepay;
    }

    public int getProductDeadline() {
        return productDeadline;
    }

    public void setProductDeadline(int productDeadline) {
        this.productDeadline = productDeadline;
    }

    public double getActualArrivalAmount() {
        return actualArrivalAmount;
    }

    public void setActualArrivalAmount(double actualArrivalAmount) {
        this.actualArrivalAmount = actualArrivalAmount;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
