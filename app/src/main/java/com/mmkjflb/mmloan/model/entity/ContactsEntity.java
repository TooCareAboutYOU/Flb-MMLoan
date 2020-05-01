package com.mmkjflb.mmloan.model.entity;

public class ContactsEntity {
    /**
     * "id": 29,
     * "userId": 201805151154000067,
     * "firstContactName": "ba ba",
     * "firstContactMobile": "15988417054",
     * "firstContactRelation": 0,
     * "firstRemarksName": null,
     * "secondContactName": "曹远征",
     * "secondContactMobile": "13767830162",
     * "secondContactRelation": 2,
     * "secondRemarksName": null,
     * "thirdContactName": "陈娇",
     * "thirdContactMobile": "13970885683",
     * "thirdContactRelation": 0,
     * "thirdRemarksName": null,
     * "createTime": 1526362064000,
     * "updateTime": 1526362064000
     */

    private int id;
    private long userId;
    private String firstContactName;
    private String firstContactMobile;
    private int firstContactRelation;
    private String firstRemarksName;
    private String secondContactName;
    private String secondContactMobile;
    private int secondContactRelation;
    private String secondRemarksName;
    private String thirdContactName;
    private String thirdContactMobile;
    private int thirdContactRelation;
    private String thirdRemarksName;
    private long createTime;
    private long updateTime;

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

    public String getFirstContactName() {
        return firstContactName;
    }

    public void setFirstContactName(String firstContactName) {
        this.firstContactName = firstContactName;
    }

    public String getFirstContactMobile() {
        return firstContactMobile;
    }

    public void setFirstContactMobile(String firstContactMobile) {
        this.firstContactMobile = firstContactMobile;
    }

    public int getFirstContactRelation() {
        return firstContactRelation;
    }

    public void setFirstContactRelation(int firstContactRelation) {
        this.firstContactRelation = firstContactRelation;
    }

    public String getFirstRemarksName() {
        return firstRemarksName;
    }

    public void setFirstRemarksName(String firstRemarksName) {
        this.firstRemarksName = firstRemarksName;
    }

    public String getSecondContactName() {
        return secondContactName;
    }

    public void setSecondContactName(String secondContactName) {
        this.secondContactName = secondContactName;
    }

    public String getSecondContactMobile() {
        return secondContactMobile;
    }

    public void setSecondContactMobile(String secondContactMobile) {
        this.secondContactMobile = secondContactMobile;
    }

    public int getSecondContactRelation() {
        return secondContactRelation;
    }

    public void setSecondContactRelation(int secondContactRelation) {
        this.secondContactRelation = secondContactRelation;
    }

    public String getSecondRemarksName() {
        return secondRemarksName;
    }

    public void setSecondRemarksName(String secondRemarksName) {
        this.secondRemarksName = secondRemarksName;
    }

    public String getThirdContactName() {
        return thirdContactName;
    }

    public void setThirdContactName(String thirdContactName) {
        this.thirdContactName = thirdContactName;
    }

    public String getThirdContactMobile() {
        return thirdContactMobile;
    }

    public void setThirdContactMobile(String thirdContactMobile) {
        this.thirdContactMobile = thirdContactMobile;
    }

    public int getThirdContactRelation() {
        return thirdContactRelation;
    }

    public void setThirdContactRelation(int thirdContactRelation) {
        this.thirdContactRelation = thirdContactRelation;
    }

    public String getThirdRemarksName() {
        return thirdRemarksName;
    }

    public void setThirdRemarksName(String thirdRemarksName) {
        this.thirdRemarksName = thirdRemarksName;
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

    @Override
    public String toString() {
        return "ContactsEntity{" +
                "id=" + id +
                ", userId=" + userId +
                ", firstContactName='" + firstContactName + '\'' +
                ", firstContactMobile='" + firstContactMobile + '\'' +
                ", firstContactRelation=" + firstContactRelation +
                ", firstRemarksName='" + firstRemarksName + '\'' +
                ", secondContactName='" + secondContactName + '\'' +
                ", secondContactMobile='" + secondContactMobile + '\'' +
                ", secondContactRelation=" + secondContactRelation +
                ", secondRemarksName='" + secondRemarksName + '\'' +
                ", thirdContactName='" + thirdContactName + '\'' +
                ", thirdContactMobile='" + thirdContactMobile + '\'' +
                ", thirdContactRelation=" + thirdContactRelation +
                ", thirdRemarksName='" + thirdRemarksName + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
