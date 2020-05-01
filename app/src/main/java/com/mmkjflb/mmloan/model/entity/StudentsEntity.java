package com.mmkjflb.mmloan.model.entity;

public class StudentsEntity {

    /**        "id": 5,
     "userId": 201805151154000067,
     "createTime": 1526455941000,
     "updateTime": null,
     "school": "fccf",
     "scholar": "xxf",
     "studentCard": "http://116.62.4.87:9333/9,063db7ef2bc6"
     */

    private int id;
    private long userId;
    private long createTime;
    private long updateTime;
    private String school;
    private String scholar;
    private String studentCard;

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

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getScholar() {
        return scholar;
    }

    public void setScholar(String scholar) {
        this.scholar = scholar;
    }

    public String getStudentCard() {
        return studentCard;
    }

    public void setStudentCard(String studentCard) {
        this.studentCard = studentCard;
    }

    @Override
    public String toString() {
        return "StudentsEntity{" +
                "id=" + id +
                ", userId=" + userId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", school='" + school + '\'' +
                ", scholar='" + scholar + '\'' +
                ", studentCard='" + studentCard + '\'' +
                '}';
    }
}
