package com.mmkjflb.mmloan.model.entity;

/**
 * Created by
 * Date: On 2018/1/15
 * Author: wangjieyan
 * Email: wangjieyan9308@163.com
 */

public class CouponEntity {
    /**
     * userCouponId : 32
     * userId : 201801051831550049
     * id : 3
     * couponName : 优惠
     * couponLogo : http://118.178.105.178:8080/pic/appcou_7bfa7780d5c24833a8a0c3818594a667.jpg
     * couponType : 0
     * couponValue : 10.0
     * couponIntro :
     * couponCondition : 1
     * sort : 1
     * remark : null
     * endTime : 1516607166000
     * status : 0
     * 优惠券状态    0  未使用  1 已使用  2  已过期   3优惠券不能使用只能展示
     */

    private int userCouponId;
    private long userId;
    private int id;
    private String couponName;
    private String couponLogo;
    private int couponType;
    private double couponValue;
    private String couponIntro;
    private int couponCondition;
    private int sort;
    private String remark;
    private long endTime;
    private int status;

    public int getUserCouponId() {
        return userCouponId;
    }

    public void setUserCouponId(int userCouponId) {
        this.userCouponId = userCouponId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public String getCouponLogo() {
        return couponLogo;
    }

    public void setCouponLogo(String couponLogo) {
        this.couponLogo = couponLogo;
    }

    public int getCouponType() {
        return couponType;
    }

    public void setCouponType(int couponType) {
        this.couponType = couponType;
    }

    public double getCouponValue() {
        return couponValue;
    }

    public void setCouponValue(double couponValue) {
        this.couponValue = couponValue;
    }

    public String getCouponIntro() {
        return couponIntro;
    }

    public void setCouponIntro(String couponIntro) {
        this.couponIntro = couponIntro;
    }

    public int getCouponCondition() {
        return couponCondition;
    }

    public void setCouponCondition(int couponCondition) {
        this.couponCondition = couponCondition;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
    /**
     //用户持有优惠券主键
     private Long userCouponId;
     //优惠券持有者
     private Long userId;
     //优惠券ID
     private Long id;
     //优惠券名称
     private String couponName;
     //优惠券图片
     private String couponLogo;
     //优惠券类型
     private Byte couponType;
     //优惠券数值
     private BigDecimal couponValue;
     //优惠券简介
     private String couponIntro;
     //使用规则
     private Byte couponCondition;
     //排序
     private Integer sort;
     //描述
     private String remark;
     //优惠券到期时间
     private Date endTime;
     private Integer status;
     */



    }
