package com.mmkjflb.mmloan.model.entity;

public class RefundDepositEntity {

    /**
     * msg : You haven't paid the deposit yet
     * status : -1
     */
    private String msg;
    private int status;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public int getStatus() {
        return status;
    }
}
