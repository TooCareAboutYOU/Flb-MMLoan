package com.mmkjflb.mmloan.model.entity;

import com.bigkoo.pickerview.model.IPickerViewData;

public class CompanyNatureEntity implements IPickerViewData {

    /**
     * id : 20
     * dictType : company_nature
     * itemName : 1
     * itemValue : BPO Professionals
     * sort : 1
     * status : true
     * expression : null
     * remark : null
     * createTime : 1548404502000
     */

    private int id;
    private String dictType;
    private String itemName;
    private String itemValue;
    private int sort;
    private boolean status;
    private Object expression;
    private Object remark;
    private long createTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Object getExpression() {
        return expression;
    }

    public void setExpression(Object expression) {
        this.expression = expression;
    }

    public Object getRemark() {
        return remark;
    }

    public void setRemark(Object remark) {
        this.remark = remark;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    @Override
    public String getPickerViewText() {
        return itemValue;
    }
}
