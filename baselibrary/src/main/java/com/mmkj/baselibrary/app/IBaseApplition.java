package com.mmkj.baselibrary.app;

import android.app.Application;
import android.content.Context;

import com.mmkj.lib.flb.BaseConfig;
import com.mmkj.lib.flb.BuildConfig;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;


public abstract class IBaseApplition extends MultiDexApplication {
    public static Application iBaseInstance;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);

    }

    @Override
    public void onCreate() {
        super.onCreate();
        iBaseInstance = this;
//        https://flbapi.358fintech.com  //线上
//        http://114.55.90.111:8433/  测试
//        https://47.98.226.225:9443/  //预发布
//        BaseConfig.getInstance().setTestUrl("https://flbapi.358fintech.com");
        BaseConfig.getInstance().setAppRelease(BaseConfig.ApkVersions(this.getApplicationContext()));

    }

    public static Application getiBaseInstance() {
        return iBaseInstance;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        BaseConfig.getInstance().releaseConfig();
    }
}