package com.mmkjflb.mmloan.model.entity;

import java.io.Serializable;

public class PaymentShopListEntity implements Serializable {

    public PaymentShopListEntity(int id, boolean isFlag) {
        this.id = id;
        this.isFlag = isFlag;
    }

    /**
     * id : 1
     * paymentPlatform : skypay
     * withdrawalsShop : 1
     * imgurl : null
     * status : 0
     * channelName : M Lhuillier
     * createTime : 1565161514000
     * updateTime : null
     */



    private int id;
    private String paymentPlatform;
    private int withdrawalsShop;
    private String imgurl;
    private String selectedImgurl;
    private int status;
    private String channelName;
    private long createTime;
    private String updateTime;
    private boolean isFlag;
    private int imgDefault;
    private int imgSelected;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPaymentPlatform() {
        return paymentPlatform;
    }

    public void setPaymentPlatform(String paymentPlatform) {
        this.paymentPlatform = paymentPlatform;
    }

    public int getWithdrawalsShop() {
        return withdrawalsShop;
    }

    public void setWithdrawalsShop(int withdrawalsShop) {
        this.withdrawalsShop = withdrawalsShop;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getSelectedImgurl() {
        return selectedImgurl;
    }

    public void setSelectedImgurl(String selectedImgurl) {
        this.selectedImgurl = selectedImgurl;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public boolean isFlag() {
        return isFlag;
    }

    public void setFlag(boolean flag) {
        isFlag = flag;
    }

    public int getImgDefault() {
        return imgDefault;
    }

    public void setImgDefault(int imgDefault) {
        this.imgDefault = imgDefault;
    }

    public int getImgSelected() {
        return imgSelected;
    }

    public void setImgSelected(int imgSelected) {
        this.imgSelected = imgSelected;
    }
}
