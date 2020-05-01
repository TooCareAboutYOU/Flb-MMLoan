package com.mmkjflb.mmloan.model.entity.juxinli;

import java.io.Serializable;


public class FinishAnhorizedBean implements Serializable {


    /**
     * code : 20000
     * data : null
     * message : Invoke api successful
     */

    private int code;
    private Object data;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
