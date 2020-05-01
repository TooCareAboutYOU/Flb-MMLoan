package com.mmkjflb.mmloan.utils.location;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.mmkjflb.mmloan.BuildConfig;
import com.mmkjflb.mmloan.utils.location.beans.BarangayBean;
import com.mmkjflb.mmloan.utils.location.beans.LocalBean;
import com.mmkjflb.mmloan.utils.location.beans.MunicipalityBean;
import com.mmkjflb.mmloan.utils.location.beans.ProvincesBean;
import com.mmkjflb.mmloan.utils.location.databeans.ArchipelagosBean;
import com.mmkjflb.mmloan.utils.location.databeans.RegionsBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * 需要释放mRegionBeanList，
 */
public class LocalUtils {

    private static final String TAG = "LocalUtils";

    @Deprecated
    private LinkedList<LocalBean> mRegionBeanList;

    private LinkedList<ArchipelagosBean> mdataList;

    private LocalUtils() {
        mRegionBeanList = new LinkedList<>();
        mdataList = new LinkedList<>();
    }

    @SuppressLint("StaticFieldLeak")
    public static LocalUtils getInstance() {
        return LocalUtilsHolder.instance;

    }

    private static class LocalUtilsHolder {
        @SuppressLint("StaticFieldLeak")
        private static final LocalUtils instance = new LocalUtils();
    }

    public void getJson(Context context, String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        AssetManager assetManager = context.getAssets();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(assetManager.open(fileName), "utf-8"));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            if (fileName.equals("newCity.json")) {
                ParseJSONString(stringBuilder.toString());
                return;
            }
            JsonToObjects(stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void ParseJSONString(String jsonStr) {
        try {
            JSONObject rootObj = new JSONObject(jsonStr);
            mdataList.clear();
            Iterator<String> archipalagosKeys = rootObj.keys();
            while (archipalagosKeys.hasNext()) {
                String archipalagosName = archipalagosKeys.next();
                ArchipelagosBean archipelagosBean = new ArchipelagosBean();
                LinkedList<RegionsBean> regionsList = new LinkedList<>();
                archipelagosBean.setArchiplagosName(archipalagosName);  //群岛

                JSONObject archipalagosObj = rootObj.getJSONObject(archipalagosName);
                JSONObject regionsObj = archipalagosObj.getJSONObject("region_list");
                Iterator<String> regionsKey = regionsObj.keys();
                while (regionsKey.hasNext()) {
                    String regionsName = regionsKey.next();
                    LinkedList<String> citysList = new LinkedList<>();
                    RegionsBean regionsBean = new RegionsBean();
                    regionsBean.setRegionName(regionsName);   //区域

                    JSONObject citysObj = regionsObj.getJSONObject(regionsName);
                    JSONArray cityJsonArray = citysObj.getJSONArray("city_list");
                    int cityLength = cityJsonArray.length();
                    if (cityLength > 0) {
                        for (int i = 0; i < cityLength; i++) {
                            citysList.add(cityJsonArray.getString(i));
                        }
                    }
                    regionsBean.setCitysList(citysList);
                    regionsList.add(regionsBean);
                }
                archipelagosBean.setRegionsList(regionsList);
                mdataList.add(archipelagosBean);
            }
            if (BuildConfig.DEBUG) {
                Log.e(TAG, "ParseJSONString: " + mdataList.size() + "\n" + mdataList.toString());
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    //返回总集合
    public LinkedList<ArchipelagosBean> getAllList() {
        if (mdataList != null && mdataList.size() > 0) {
            return mdataList;
        }
        return null;
    }

    //获取群岛
    public LinkedList<String> getArchipelagosList() {
        if (getAllList() != null) {
            LinkedList<String> data = new LinkedList<>();
            if (mdataList != null && mdataList.size() > 0) {
                for (int i = 0; i < mdataList.size(); i++) {
                    data.add(mdataList.get(i).getArchipelagosName());
                }
                return data;
            }
        }
        return null;
    }

    //获取区域
    public LinkedList<RegionsBean> getRegionsList(String archipelagosName) {
        if (getAllList() != null) {
            int size = getAllList().size();
            for (int i = 0; i < size; i++) {
                if (mdataList.get(i).getArchipelagosName().equals(archipelagosName) && mdataList.get(i).getRegionsList() != null) {
                    return mdataList.get(i).getRegionsList();
                }
            }
        }
        return null;
    }

    //获取城市
    public LinkedList<String> getCitysList(String archipelagosName, String regionsName) {
        if (getRegionsList(archipelagosName) != null) {
            int size = getRegionsList(archipelagosName).size();
            LinkedList<RegionsBean> regionList = getRegionsList(archipelagosName);
            for (int i = 0; i < size; i++) {
                if (regionList.get(i).getRegionName().equals(regionsName) && regionList.get(i).getCitysList() != null) {
                    return regionList.get(i).getCitysList();
                }
            }
        }
        return null;
    }


    /**
     * -------------------------------------------------
     */


    @Deprecated
    private void JsonToObjects(String jsonStr) {
        JSONObject jsonObject = null;
        mRegionBeanList.clear();
        try {
            jsonObject = new JSONObject(jsonStr);
            Iterator<String> keys = jsonObject.keys();
            while (keys.hasNext()) {
                JSONObject regionObj = jsonObject.getJSONObject(keys.next()); //编号
                LocalBean localBean = new LocalBean();
                String regionName = regionObj.getString("region_name");
                localBean.setRegionName(regionName);  //地区

                JSONObject provinceListObj = regionObj.getJSONObject("province_list");  //省份
                Iterator<String> provinceKeys = provinceListObj.keys();
                LinkedList<ProvincesBean> provincesList = new LinkedList<>();
                while (provinceKeys.hasNext()) {
                    final String provinceName = provinceKeys.next();
                    ProvincesBean provincesBean = new ProvincesBean();
                    provincesBean.setProvince(provinceName);

                    JSONObject municipalityObj = provinceListObj.getJSONObject(provinceName);
                    JSONObject munChildObj = municipalityObj.getJSONObject("municipality_list");
                    Iterator<String> municipalityKeys = munChildObj.keys();
                    LinkedList<MunicipalityBean> municipalityList = new LinkedList<>();
                    while (municipalityKeys.hasNext()) {
                        final String municipalityName = municipalityKeys.next();
                        MunicipalityBean municipalityBean = new MunicipalityBean();
                        municipalityBean.setMunicipality(municipalityName);

                        //村
                        JSONObject barangayObj = munChildObj.getJSONObject(municipalityName);
                        JSONArray barangayChildArray = barangayObj.getJSONArray("barangay_list");
                        int barangayLength = barangayChildArray.length();
                        LinkedList<BarangayBean> barangaylist = new LinkedList<>();
                        for (int i = 0; i < barangayLength; i++) {
                            barangaylist.add(new BarangayBean(barangayChildArray.getString(i)));
                        }

                        municipalityBean.setBarangayList(barangaylist);
                        municipalityList.add(municipalityBean);
                    }

                    provincesBean.setMunicipalityBeanList(municipalityList);
                    provincesList.add(provincesBean);
                }
                localBean.setProvincesBeanList(provincesList);
                mRegionBeanList.add(localBean);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (BuildConfig.DEBUG) {
            Log.i(TAG, "总数: " + mRegionBeanList.size());
        }
    }

    //获取地区
    @Deprecated
    public LinkedList<LocalBean> getRegionBeanList() {
        return mRegionBeanList;
    }

    public void clear() {
        if (mRegionBeanList != null) {
            mRegionBeanList.clear();
        }
    }

    //获取省份
    @Deprecated
    public LinkedList<ProvincesBean> getLocalProvince(String regionName) {
        for (LocalBean localBean : getRegionBeanList()) {
            if (localBean.getRegionName().equals(regionName)) {
                return localBean.getProvincesBeanList();
            }
        }
        return null;
    }

    //获取城市
    @Deprecated
    public LinkedList<MunicipalityBean> getLocalMunicipality(String regionName, String provinceName) {
        for (LocalBean localBean : getRegionBeanList()) {
            if (localBean.getRegionName().equals(regionName)) {
                for (ProvincesBean provincesBean : localBean.getProvincesBeanList()) {
                    if (provincesBean.getProvince().equals(provinceName)) {
                        return provincesBean.getMunicipalityBeanList();
                    }
                }
            }
        }
        return null;
    }

    //获取村
    @Deprecated
    public LinkedList<BarangayBean> getLocalBarangay(String regionName, String provinceName, String municipalityName) {
        for (LocalBean localBean : getRegionBeanList()) {
            if (localBean.getRegionName().equals(regionName)) {
                for (ProvincesBean provincesBean : localBean.getProvincesBeanList()) {
                    if (provincesBean.getProvince().equals(provinceName)) {
                        for (MunicipalityBean municipalityBean : provincesBean.getMunicipalityBeanList()) {
                            if (municipalityBean.getMunicipality().equals(municipalityName)) {
                                return municipalityBean.getBarangayList();
                            }
                        }
                    }
                }
            }
        }
        return null;
    }


}
