package com.mmkjflb.mmloan.model.entity;

import com.google.gson.Gson;

/**
 * Data: 2018/9/4 18:29
 * Author: Xuyexiang
 * Title:
 */
public class SurelyEntity {


    private int status;// 1 成功, -1业务失败 -2 系统异常
    private String msg;
    private String userStatus; // “0”正常可借“1”重新认证



    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
