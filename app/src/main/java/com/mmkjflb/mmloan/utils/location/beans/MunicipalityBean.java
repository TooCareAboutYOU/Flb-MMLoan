package com.mmkjflb.mmloan.utils.location.beans;

import com.bigkoo.pickerview.model.IPickerViewData;
import com.google.gson.Gson;

import java.util.LinkedList;

/**
 * 城市
 */
public class MunicipalityBean implements IPickerViewData {
    private String municipality;
    private LinkedList<BarangayBean> barangayList;  //村

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public LinkedList<BarangayBean> getBarangayList() {
        return barangayList;
    }

    public void setBarangayList(LinkedList<BarangayBean> barangayList) {
        this.barangayList = barangayList;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    @Override
    public String getPickerViewText() {
        return municipality;
    }
}
