package com.mmkjflb.mmloan.utils.location.beans;

import com.bigkoo.pickerview.model.IPickerViewData;
import com.google.gson.Gson;

import java.util.LinkedList;

/**
 *
 */
public class LocalBean implements IPickerViewData {

    private String regionName ;
    private LinkedList<ProvincesBean> provincesBeanList;

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public LinkedList<ProvincesBean> getProvincesBeanList() {
        return provincesBeanList;
    }

    public void setProvincesBeanList(LinkedList<ProvincesBean> provincesBeanList) {
        this.provincesBeanList = provincesBeanList;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    @Override
    public String getPickerViewText() {
        return regionName;
    }
}
