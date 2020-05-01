package com.mmkjflb.mmloan.model.entity;

import java.util.List;

/**
 * date: 2018/3/8 16:34
 * author: xuyexiang
 * title:
 */

public class BankTypeEntity {
    private List<String> bankType;
    private List<String> payType;

    public List<String> getBankType() {
        return bankType;
    }

    public void setBankType(List<String> bankType) {
        this.bankType = bankType;
    }

    public List<String> getPayType() {
        return payType;
    }

    public void setPayType(List<String> payType) {
        this.payType = payType;
    }
}
