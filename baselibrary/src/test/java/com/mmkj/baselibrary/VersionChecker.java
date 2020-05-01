package com.mmkj.baselibrary;

import android.accounts.NetworkErrorException;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.TextUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * 获取Google Play上应用版本，作对比
 * 使用方式： VersionChecker.getInstance().jumpToGooglePlay(this);
 */
public class VersionChecker extends AsyncTask<String, String, Boolean> {

    @SuppressLint("StaticFieldLeak")
    private static volatile VersionChecker instance=null;
    private VersionChecker(){}
    @SuppressLint("StaticFieldLeak")
    public static synchronized VersionChecker getInstance(){
        if(instance==null){
            synchronized(VersionChecker.class){
                if(instance==null){
                    instance=new VersionChecker();
                }
            }
        }
        return instance;
    }


    private boolean isUpdateApk = false;


    @Override
    protected Boolean doInBackground(String... strings) {
        String versionName = null;
        int googleVersion = 0;
        try {
            Document document = Jsoup.connect("https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "&hl=en")
                    .timeout(30000)
                    .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                    .referrer("http://www.google.com")
                    .get();
            if (document != null) {
                Elements element = document.getElementsContainingOwnText("Current Version");
                for (Element ele : element) {
                    if (ele.siblingElements() != null) {
                        Elements sibElemets = ele.siblingElements();
                        for (Element sibElemet : sibElemets) {
                            versionName = sibElemet.text();
                        }
                    }
                }
            }

            if (!TextUtils.isEmpty(versionName)) {
                String vn = versionName.replaceAll("\\.", "").trim();
                googleVersion = Integer.parseInt(vn);
                if (BuildConfig.VERSION_CODE < googleVersion) {
                    this.isUpdateApk = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return this.isUpdateApk;
    }

    public void jumpToGooglePlay(Activity activity) {
        try {
            if (get()) {
                //perform your task here like show alert dialogue "Need to upgrade app"
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("market://details?id=" + activity.getPackageName()));
                if (intent.resolveActivity(activity.getPackageManager()) != null) { //可以接收
                    activity.startActivity(intent);
                } else { //没有应用市场，我们通过浏览器跳转到Google Play
                    intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=" + activity.getPackageName()));
                    activity.startActivity(intent);
                }
            } else {
                throw new NetworkErrorException("谷歌获取版本名失败！，亲切换自家渠道");
            }
        } catch (NetworkErrorException e) {
            e.printStackTrace();
        } catch (
                ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
