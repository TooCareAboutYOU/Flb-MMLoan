package com.mmkjflb.mmloan.model.entity;

public class PrivateOwnerEntity {

    /**        "id":10,
     "userId":201805151154000067,
     "createTime":1526454758000,
     "updateTime":null,
     "companyName":"fbnnn",
     "companyMobile":"88 99",
     "companyEmail":"vvbnn",
     "islands":"Sumatera",
     "province":"Aceh",
     "city":"Kabupaten Aceh Barat",
     "area":null,
     "address":"cbnnmmmm",
     "postalCode":"cbbnnn",
     "businessLicense":"http://116.62.4.87:9333/13,0639e412ae58",
     "companyAddress":"vbbbb"
     */

    private int id;
    private long userId;
    private long createTime;
    private long updateTime;
    private String companyName;
    private String companyMobile;
    private String companyEmail;
    private String islands;
    private String province;
    private String city;
    private String area;
    private String address;
    private String postalCode;
    private String businessLicense;
    private String companyAddress;


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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyMobile() {
        return companyMobile;
    }

    public void setCompanyMobile(String companyMobile) {
        this.companyMobile = companyMobile;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    public String getIslands() {
        return islands;
    }

    public void setIslands(String islands) {
        this.islands = islands;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getBusinessLicense() {
        return businessLicense;
    }

    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    @Override
    public String toString() {
        return "PrivateOwnerEntity{" +
                "id=" + id +
                ", userId=" + userId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", companyName='" + companyName + '\'' +
                ", companyMobile='" + companyMobile + '\'' +
                ", companyEmail='" + companyEmail + '\'' +
                ", islands='" + islands + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", area='" + area + '\'' +
                ", address='" + address + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", businessLicense='" + businessLicense + '\'' +
                ", companyAddress='" + companyAddress + '\'' +
                '}';
    }
}
