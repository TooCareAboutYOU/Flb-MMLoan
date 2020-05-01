package com.mmkjflb.mmloan.model.entity;

import java.util.List;

/**
 * date: 2018/3/8 19:36
 * author: xuyexiang
 * title:
 */

public class PayCodeEntity {
    private List<AtmOtcBean> atm;
    private List<AtmOtcBean> otc;

    public List<AtmOtcBean> getAtm() {
        return atm;
    }

    public void setAtm(List<AtmOtcBean> atm) {
        this.atm = atm;
    }

    public List<AtmOtcBean> getOtc() {
        return otc;
    }

    public void setOtc(List<AtmOtcBean> otc) {
        this.otc = otc;
    }

    public static class AtmOtcBean {
        private int id;
        private long userId;
        private String payClient;
        private String paymentCode;
        private long createTime;
        private long endTime;
        private int status;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public long getUserId() {
            return userId;
        }

        public void setUserId(long userId) {
            this.userId = userId;
        }

        public String getPayClient() {
            return payClient;
        }

        public void setPayClient(String payClient) {
            this.payClient = payClient;
        }

        public String getPaymentCode() {
            return paymentCode;
        }

        public void setPaymentCode(String paymentCode) {
            this.paymentCode = paymentCode;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public long getEndTime() {
            return endTime;
        }

        public void setEndTime(long endTime) {
            this.endTime = endTime;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
