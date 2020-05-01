package com.mmkj.usercenter.model.entity;

public class CustomerServiceData {

    /**
     * dictType : customer_service
     * itemName : service_hotline
     * itemValue : 09955598396
     * sort : 1
     */

    private String dictType;
    private String itemName;
    private String itemValue;
    private int sort;

    public CustomerServiceData(String dictType, String itemName, String itemValue, int sort) {
        this.dictType = dictType;
        this.itemName = itemName;
        this.itemValue = itemValue;
        this.sort = sort;
    }

    public String getDictType() {
        return dictType;
    }

    public void setDictType(String dictType) {
        this.dictType = dictType;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemValue() {
        return itemValue;
    }

    public void setItemValue(String itemValue) {
        this.itemValue = itemValue;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

}
