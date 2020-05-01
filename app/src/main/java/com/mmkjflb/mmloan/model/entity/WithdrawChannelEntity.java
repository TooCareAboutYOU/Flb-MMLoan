package com.mmkjflb.mmloan.model.entity;

/**
 * Data: 2018/9/4 14:22
 * Author: Xuyexiang
 * Title:
 */
public class WithdrawChannelEntity {
    /**
     * msg : Permintaan berhasil
     * code : 1
	 * data : {}
     */

    private String msg;
    private String code;
    private DataBean data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
	}


	/**
	 * msg : Permintaan berhasil
	 * code : 1
	 * data : {"1":"M.Lhuillier","2":"Cebuana Lhuillier","3":"Palawan Pawnshop"}
	 */


}
