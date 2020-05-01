package com.mmkjflb.mmloan.model.entity;

import java.io.Serializable;

/**
 * Date: 2018/1/17 11:06
 * Author: xuyexiang
 * Title:
 */

public class QueryInviteActivityEntity implements Serializable{

    /**
     * activityLogo : http://118.178.105.178:8080/pic/6106a3f8306845a7b693856c42303dd5.jpg
     * activityName : 邀请好友
     * address : http://172.16.20.163:9090/h5/mmdInvite
     * couponId : 3
     * finishTime : 1515810906000
     * id : 17
     * isUse : 1
     * remark : 邀请好友活动
     * sort : 6
     * startTime : 1515724504000
     * status : 2
     * userId : 201801170930200061
     */

    private String activityName;
    private String address;
    private int id;
    private String remark;
    private long userId;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
