package com.mmkjflb.mmloan.utils.location.beans;


import com.bigkoo.pickerview.model.IPickerViewData;
import com.google.gson.Gson;

/**
 * Code of ZHANG/ 2018/9/6
 */
public class BarangayBean implements IPickerViewData {

    private String barangayName;

    public BarangayBean(String barangayName) {
        this.barangayName = barangayName;
    }

    public String getBarangayName() {
        return barangayName;
    }

    public void setBarangayName(String barangayName) {
        this.barangayName = barangayName;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    @Override
    public String getPickerViewText() {
        return barangayName;
    }
}
