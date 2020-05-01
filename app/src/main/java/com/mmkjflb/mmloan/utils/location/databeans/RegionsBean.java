package com.mmkjflb.mmloan.utils.location.databeans;


import com.bigkoo.pickerview.model.IPickerViewData;
import com.google.gson.Gson;

import java.util.LinkedList;

public class RegionsBean implements IPickerViewData{

    private String regionName;
    private LinkedList<String> citysList;

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public LinkedList<String> getCitysList() {
        return citysList;
    }

    public void setCitysList(LinkedList<String> citysList) {
        this.citysList = citysList;
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
