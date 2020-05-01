package com.mmkj.baselibrary.model.entitry;

import com.google.gson.Gson;

import java.util.List;

/**
 * Data: 2018/9/5 15:49
 * Author: Xuyexiang
 * Title:
 */
public class WithdrawalNumberEntity {
    /**
     * amount : 2000.0
     * sender : Jinshan Pang
     * source : EEC PHILS
     * status : 1
     * withdrawalNumber : SK0776a211620
     */

    private double amount;//取款金额
    private String sender;//打款人
    private String source;//打款来源
    private int status;//-1失效 0处理中（-1 0 借款还款主界面弹窗不显示） 1可用（弹窗） -2取消（弹窗）
    private String withdrawalNumber;//取款码
    private String withdrawChannel;
    private List<String> imgList;  //支付渠道图片
    private List<String> imgCode; //支付渠道编号

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getWithdrawalNumber() {
        return withdrawalNumber;
    }

    public void setWithdrawalNumber(String withdrawalNumber) {
        this.withdrawalNumber = withdrawalNumber;
    }

    public String getWithdrawChannel() {
        return withdrawChannel;
    }

    public void setWithdrawChannel(String withdrawChannel) {
        this.withdrawChannel = withdrawChannel;
    }

    public List<String> getImgList() {
        return imgList;
    }

    public void setImgList(List<String> imgList) {
        this.imgList = imgList;
    }

    public List<String> getImgCode() {
        return imgCode;
    }

    public void setImgCode(List<String> imgCode) {
        this.imgCode = imgCode;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
