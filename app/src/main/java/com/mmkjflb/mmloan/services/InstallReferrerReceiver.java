package com.mmkjflb.mmloan.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.mmkj.baselibrary.util.PreferenceUtils;
import com.orhanobut.logger.Logger;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;


/**
 * @author zhangshuai
 * 跟踪安装渠道
 */
public class InstallReferrerReceiver extends BroadcastReceiver {

    private static final String ACTION_INSTALL_REFERRER = "com.android.vending.INSTALL_REFERRER";
    private static final String KEY_REFERRER = "referrer";

    @Override
    public void onReceive(Context context, Intent intent) {
        Logger.i("InstallReferrerReceiver Begin");
        if (intent == null || !ACTION_INSTALL_REFERRER.equals(intent.getAction())) {
            return;
        }

        if (!ACTION_INSTALL_REFERRER.equals(intent.getAction())) {
            return;
        }


        String referrers = intent.getStringExtra("referrer");
        if (referrers != null) {
            String referrer = "";
            try {
                referrer = URLDecoder.decode(referrers, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            PreferenceUtils.putString(PreferenceUtils.REFERRER, referrer);
            Logger.i("InstallReferrerReceiver referrer="+referrer);
        }
    }
}
