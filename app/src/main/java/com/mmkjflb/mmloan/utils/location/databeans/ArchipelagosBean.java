package com.mmkjflb.mmloan.utils.location.databeans;


import com.bigkoo.pickerview.model.IPickerViewData;
import com.google.gson.Gson;

import java.util.LinkedList;

public class ArchipelagosBean implements IPickerViewData{

    private String archipelagosName;
    private LinkedList<RegionsBean> regionsList;

    public String getArchipelagosName() {
        return archipelagosName;
    }

    public void setArchiplagosName(String archipelagosName) {
        this.archipelagosName = archipelagosName;
    }

    public LinkedList<RegionsBean> getRegionsList() {
        return regionsList;
    }

    public void setRegionsList(LinkedList<RegionsBean> regionsList) {
        this.regionsList = regionsList;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    @Override
    public String getPickerViewText() {
        return archipelagosName;
    }
}
