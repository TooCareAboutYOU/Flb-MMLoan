package com.mmkjflb.mmloan.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.mmkjflb.mmloan.R;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;

public class HttpGpsLocation {

    private static final String TAG = "CustomViewActivity";
    private LocListener locListener = null;

    @SuppressLint("StaticFieldLeak")
    private static volatile HttpGpsLocation instance = null;

    private HttpGpsLocation() {
    }

    @SuppressLint("StaticFieldLeak")
    private static Context mContext;

    @SuppressLint("StaticFieldLeak")
    public static synchronized HttpGpsLocation getInstance(Context context) {
        mContext = context.getApplicationContext();
        if (instance == null) {
            synchronized (HttpGpsLocation.class) {
                if (instance == null) {
                    instance = new HttpGpsLocation();
                }
            }
        }
        return instance;
    }

    @SuppressLint("StaticFieldLeak")
    public static HttpGpsLocation peckInstance() {
        return instance;
    }


    /**
     * 获取经纬度
     *
     * @return
     */
    public static void requestLatitudeAndLongtitude(LocationListener locationListener) {
        try {
            final LocationManager locationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
            // 生成一个Criteria对象
            Criteria criteria = new Criteria();
            // 设置查询条件
            criteria.setAccuracy(Criteria.ACCURACY_FINE); // 设置准确而非粗糙的精度
            criteria.setPowerRequirement(Criteria.POWER_LOW); // 设置相对省电而非耗电，一般高耗电量会换来更精确的位置信息
            criteria.setAltitudeRequired(false); // 不需要提供海拔信息
            criteria.setSpeedRequired(false); // 不需要速度信息
            criteria.setCostAllowed(false); // 不能产生费用
            // 第一个参数，传递criteria对象
            // 第二个参数，若为false,在所有Provider中寻找，不管该Provider是否处于可用状态，均使用该Provider。
            // 若为true，则在所有可用的Provider中寻找。比如GPS处于禁用状态，则忽略GPS Provider。
            // 1、可用中最好的
            String locationProvider = locationManager.getBestProvider(criteria, true);
            // 2、所有可用的中的第一个
            if (locationProvider == null) {
                List<String> providers = locationManager.getProviders(true);
                if (providers != null && providers.size() > 0) {
                    locationProvider = providers.get(0);
                }
            }
            // 都不支持则直接返回
            if (TextUtils.isEmpty(locationProvider)) {
                return;
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (mContext.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && mContext.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    Activity#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for Activity#requestPermissions for more details.
                    return;
                }
            }
            Location location = locationManager.getLastKnownLocation(locationProvider);
            Log.i(TAG, "requestLatitudeAndLongtitude: location 1 =" + location);

            if (location != null) {
                //updateCacheLocation(context, location.getLatitude(), location.getLongitude());

                locationListener.onLocationChanged(location);
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (mContext.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && mContext.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    Activity#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for Activity#requestPermissions for more details.
                        return;
                    }
                }
                locationManager.requestLocationUpdates(locationProvider, 1000 * 60 * 60, 1000, locationListener);

            }
        } catch (Exception e) {
            // LogUtils.d("---location--- location : "+e.getMessage());
        }
    }

    /**
     * 获取位置后监听
     */
    private class LocListener implements LocationListener {
        @Override
        public void onLocationChanged(Location location) {
            String locationAddress = getLocationAddress(location);
            //TODO:当前地址为：locationAddress="北京市海淀区华奥饭店公司写字间中关村创业大街"
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

        @Override
        public void onProviderEnabled(String provider) {
        }

        @Override
        public void onProviderDisabled(String provider) {
        }
    }

    /**
     * 将经纬度转换成中文地址
     *
     * @param location
     * @return
     */
    private String getLocationAddress(Location location) {
        String add = "";
        Geocoder geoCoder = new Geocoder(mContext, Locale.CHINESE);
        try {
            List<Address> addresses = geoCoder.getFromLocation(
                    location.getLatitude(), location.getLongitude(),
                    1);
            Address address = addresses.get(0);
            Log.i(TAG, "getLocationAddress: " + address.toString());
            // Address[addressLines=[0:"中国",1:"北京市海淀区",2:"华奥饭店公司写字间中关村创业大街"]latitude=39.980973,hasLongitude=true,longitude=116.301712]
            int maxLine = address.getMaxAddressLineIndex();
            if (maxLine >= 2) {
                add = address.getAddressLine(1) + address.getAddressLine(2);
            } else {
                add = address.getAddressLine(1);
            }
        } catch (IOException e) {
            add = "";
            e.printStackTrace();
        }
        return add;
    }


}
