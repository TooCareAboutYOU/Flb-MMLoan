package com.mmkjflb.mmloan.model.entity;

import com.bigkoo.pickerview.model.IPickerViewData;

/**
 * date: 2018/3/10 17:06
 * author: xuyexiang
 * title:
 */

public class BankListEntity implements IPickerViewData {

    /**
     * id : 101
     * bankName : BANK CHINATRUST INDONESIA
     * bankCode : BANK CHINATRUST INDONESIA
     */

    private int id;
    private String bankName;
    private String bankCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    @Override
    public String getPickerViewText() {
        return bankName;
    }
}
