package com.mmkjflb.mmloan.model.entity;

public class FreelanceEntity {

    /**
     * "id": 31,
     * "userId": 201805151154000067,
     * "createTime": 1526437276000,
     * "updateTime": 1526453821000,
     * "monthIncome": 5899999999,
     * "repaySource": "dghj"
     */

    private int id;
    private long userId;
    private long createTime;
    private long updateTime;
    private long monthIncome;
    private String repaySource;

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

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public long getMonthIncome() {
        return monthIncome;
    }

    public void setMonthIncome(long monthIncome) {
        this.monthIncome = monthIncome;
    }

    public String getRepaySource() {
        return repaySource;
    }

    public void setRepaySource(String repaySource) {
        this.repaySource = repaySource;
    }

    @Override
    public String toString() {
        return "FreelanceEntity{" +
                "id=" + id +
                ", userId=" + userId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", monthIncome=" + monthIncome +
                ", repaySource='" + repaySource + '\'' +
                '}';
    }
}
