package com.mmkjflb.mmloan.app;

import android.content.Context;
import android.content.pm.PackageInstaller;
import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;
import com.facebook.AccessToken;
import com.facebook.AccessTokenManager;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.LoggingBehavior;
import com.facebook.ads.sdk.APIContext;
import com.facebook.ads.sdk.APINodeList;
import com.facebook.ads.sdk.AdAccount;
import com.facebook.ads.sdk.Campaign;
import com.facebook.ads.sdk.User;
import com.facebook.appevents.AppEventsLogger;
import com.mmkj.baselibrary.GlobalAppComponent;
import com.mmkj.baselibrary.app.IBaseApplition;
import com.mmkj.baselibrary.util.Density;
import com.mmkj.baselibrary.util.DeviceUtils;
import com.mmkj.baselibrary.util.PreferenceUtils;
import com.mmkj.baselibrary.util.StringUtils;
import com.mmkj.usercenter.UserCenterComponent;
import com.mmkjflb.mmloan.BuildConfig;
import com.mmkjflb.mmloan.R;
import com.mmkjflb.mmloan.di.component.AppComponent;
import com.mmkjflb.mmloan.di.component.DaggerAppComponent;
import com.mmkjflb.mmloan.di.module.AppModule;
import com.mmkjflb.mmloan.di.module.HttpModule;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.qiniu.android.common.AutoZone;
import com.qiniu.android.common.FixedZone;
import com.qiniu.android.storage.Configuration;
import com.qiniu.android.storage.UploadManager;
import com.tencent.bugly.crashreport.CrashReport;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Administrator on 2015/8/17 0017.
 */
public class BaseApplication extends IBaseApplition {

    private static final String TAG = "MBaseApplication";

    private static BaseApplication mInstance = null;
    public static AppComponent appComponent;
    public static String cacheDir;
    private UploadManager uploadManager;

    //586114905566365
//    public static final APIContext apiContext=new APIContext("","");

    public static BaseApplication getInstance() {
        return mInstance;
    }

    public static AppComponent getAppComponent() {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(mInstance))
                    .httpModule(new HttpModule())
                    .build();
        }
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        if (BuildConfig.DEBUG) {
            FacebookSdk.setIsDebugEnabled(true);
            FacebookSdk.addLoggingBehavior(LoggingBehavior.APP_EVENTS);
        }
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

//        CallbackManager callbackManager=CallbackManager.Factory.create();
//        AccessTokenTracker accessTokenManager=new AccessTokenTracker(){
//
//            @Override
//            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
//
//            }
//        };
//        AccessToken accessToken=AccessToken.getCurrentAccessToken();

//        APIContext apiContext=new APIContext("","");
//        AdAccount adAccount=new AdAccount("586114905566365",apiContext);
//        try{
//            APINodeList<Campaign> campaigns=adAccount.getCampaigns().requestAllFields().execute();
//            for (Campaign campaign : campaigns) {
//                if (BuildConfig.DEBUG) {
//                    Log.i(TAG, "输出: "+campaign.getFieldName());
//                }
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }



        Density.setDensity(this, 360);
        if (BuildConfig.DEBUG) {
            DeviceUtils.getDisplayMetrics(this);
            // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
            MyCrashHandler.getInstance().init(getApplicationContext());
            Logger.addLogAdapter(new AndroidLogAdapter());//default filter: PRETTY_LOGGER
        }
        ARouter.init(this); // 尽可能早，推荐在Application中初始化
        initCacheDir();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/DroidSansFallback.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());
        PreferenceUtils.init(this);
        GlobalAppComponent.init(this);
        UserCenterComponent.init(this);
        initQiNiu();

        StringUtils.checkInstallReferrer(getApplicationContext());
        loadBugly();

        //BuildConfig.DEBUG ? CountryType.TEST :
//        MmkjDot.getInstance().init(this, CountryType.PHILIPPINES, BuildConfig.DEBUG);
    }

    private void initQiNiu() {
        Configuration config = new Configuration.Builder()
                .connectTimeout(30)           // 链接超时。默认10秒
                .useHttps(true)               // 是否使用https上传域名
                .responseTimeout(30)          // 服务器响应超时。默认60秒
                .zone(FixedZone.zoneAs0)        // 指定区域
                .build();
        uploadManager = new UploadManager(config);
    }

    public UploadManager getQiNiuConfig() {
        return uploadManager;
    }

    private void initCacheDir() {
        cacheDir = getApplicationContext().getFilesDir().getAbsolutePath();
    }

    public void loadBugly() {

        Context context = getApplicationContext();
        String packetName = context.getPackageName();
        String processName = DeviceUtils.getProcessName(android.os.Process.myPid());
        //设置是否上报进程
        CrashReport.UserStrategy userStrategy = new CrashReport.UserStrategy(context);
        userStrategy.setUploadProcess(processName == null || processName.equals(packetName));
        CrashReport.initCrashReport(this, "aa9dd5f517", BuildConfig.DEBUG, userStrategy);
        CrashReport.setAppChannel(this, DeviceUtils.getAppName(this));
        CrashReport.setAppChannel(this, "MMloan");
        CrashReport.setAppVersion(this, String.valueOf(DeviceUtils.getVersionName(this)));
    }
}
