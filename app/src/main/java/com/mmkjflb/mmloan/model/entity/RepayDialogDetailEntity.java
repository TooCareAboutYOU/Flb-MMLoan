package com.mmkjflb.mmloan.model.entity;

import com.google.gson.Gson;

import java.math.BigDecimal;

import androidx.annotation.LayoutRes;
import androidx.annotation.StringRes;

/**
 * @author zhangshuai
 * 还款——详情弹框
 */
public class RepayDialogDetailEntity {


    private @StringRes int title;
    private String num;
    private int txtColorType;

    public RepayDialogDetailEntity(int title, String num, int txtColorType) {
        this.title = title;
        this.num = num;
        this.txtColorType = txtColorType;
    }

    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public int getTxtColorType() {
        return txtColorType;
    }

    public void setTxtColorType(int txtColorType) {
        this.txtColorType = txtColorType;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
