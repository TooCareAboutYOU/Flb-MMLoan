package com.mmkjflb.mmloan.utils.location.beans;

import com.bigkoo.pickerview.model.IPickerViewData;
import com.google.gson.Gson;

import java.util.LinkedList;

/**
 * 省份
 */
public class ProvincesBean implements IPickerViewData {
    private String province;
    private LinkedList<MunicipalityBean> municipalityBeanList;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public LinkedList<MunicipalityBean> getMunicipalityBeanList() {
        return municipalityBeanList;
    }

    public void setMunicipalityBeanList(LinkedList<MunicipalityBean> municipalityBeanList) {
        this.municipalityBeanList = municipalityBeanList;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    @Override
    public String getPickerViewText() {
        return province;
    }
}
