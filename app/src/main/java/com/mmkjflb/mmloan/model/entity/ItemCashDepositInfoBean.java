package com.mmkjflb.mmloan.model.entity;


/**
 * @author zhangshuai
 *
 */
public class ItemCashDepositInfoBean {

    private String title;
    private String amount;

    public ItemCashDepositInfoBean(String title, String amount) {
        this.title = title;
        this.amount = amount;
    }

    public String getTitle() {
        return title;
    }

    public String getAmount() {
        return amount;
    }
}
