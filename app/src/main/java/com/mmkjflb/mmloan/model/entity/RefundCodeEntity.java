package com.mmkjflb.mmloan.model.entity;

import java.math.BigDecimal;

public class RefundCodeEntity {


    /**
     * withdrawChannel : string
     * amount : 0
     * sender : string
     * withdrawalNumberStatus : 0
     * withdrawalNumber : string
     * source : string
     */
    private String withdrawChannel;
    private BigDecimal amount;
    private String sender;
    private int withdrawalNumberStatus;//-2-取消 -1-已使用 0-处理中 1-可用
    private String withdrawalNumber;
    private String source;

    public void setWithdrawChannel(String withdrawChannel) {
        this.withdrawChannel = withdrawChannel;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setWithdrawalNumberStatus(int withdrawalNumberStatus) {
        this.withdrawalNumberStatus = withdrawalNumberStatus;
    }

    public void setWithdrawalNumber(String withdrawalNumber) {
        this.withdrawalNumber = withdrawalNumber;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getWithdrawChannel() {
        return withdrawChannel;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getSender() {
        return sender;
    }

    public int getWithdrawalNumberStatus() {
        return withdrawalNumberStatus;
    }

    public String getWithdrawalNumber() {
        return withdrawalNumber;
    }

    public String getSource() {
        return source;
    }
}
