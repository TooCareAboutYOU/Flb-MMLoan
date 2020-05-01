package com.mmkjflb.mmloan.model.entity;

import com.google.gson.Gson;

import java.util.List;

/**
 * @author zhangshuai
 */
public class RepaymentShopEntity {
    private List<Integer> shops;
    private String desc;
    private int payClient;

    public List<Integer> getShops() {
        return shops;
    }

    public void setShops(List<Integer> shops) {
        this.shops = shops;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getPayClient() {
        return payClient;
    }

    public void setPayClient(int payClient) {
        this.payClient = payClient;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
