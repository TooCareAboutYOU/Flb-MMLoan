package com.mmkjflb.mmloan.model.entity;

/**
 * Date: 2018/1/16 15:30
 * Author: xuyexiang
 * Title:
 */

public class QueryActivityEntity {
    /**
     * activityLogo : http://118.178.105.178:8080/pic/6106a3f8306845a7b693856c42303dd5.jpg
     * activityName : 活动测试1
     * address : http://ljqb.dhfzhengxin.com:8081/loans/index
     * couponId : 3
     * finishTime : 1515810906000
     * id : 19
     * isUse : 1
     * remark : 活动测试1
     * sort : 1
     * startTime : 1515724504000
     * status : 2
     */

    private String activityLogo;
    private String activityName;
    private String address;
    private int couponId;
    private long finishTime;
    private int id;
    private int isUse;
    private String remark;
    private int sort;
    private long startTime;
    private int status;

    public String getActivityLogo() {
        return activityLogo;
    }

    public void setActivityLogo(String activityLogo) {
        this.activityLogo = activityLogo;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCouponId() {
        return couponId;
    }

    public void setCouponId(int couponId) {
        this.couponId = couponId;
    }

    public long getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(long finishTime) {
        this.finishTime = finishTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIsUse() {
        return isUse;
    }

    public void setIsUse(int isUse) {
        this.isUse = isUse;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
