package com.mmkjflb.mmloan.utils;

import android.content.Context;
import android.os.Bundle;

import com.crashlytics.android.Crashlytics;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.mmkjflb.mmloan.BuildConfig;

/**
 * FireBase统计
 *
 * @author zhangshuai
 */
public class FirebaseUtils {

    /**
     * 统计注册事件
     */
    public static void RegisterEvent(Context context, String uId) {
        if (!BuildConfig.DEBUG) {
            FirebaseAnalytics mFirebaseAnalytics = FirebaseAnalytics.getInstance(context);
            mFirebaseAnalytics.setUserProperty(FirebaseAnalytics.Event.SIGN_UP, uId);
            Bundle bundle = new Bundle();
            bundle.putString(FirebaseAnalytics.Param.METHOD, FirebaseAnalytics.UserProperty.SIGN_UP_METHOD);
            mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SIGN_UP, bundle);
            setCrashlytics(uId);
        }
    }

    /**
     * 统计登录事件
     */
    public static void LoginEvent(Context context, String uId) {
        if (!BuildConfig.DEBUG) {
            Bundle bundle = new Bundle();
            bundle.putString(FirebaseAnalytics.Param.METHOD, "login_method");
            FirebaseAnalytics.getInstance(context).logEvent(FirebaseAnalytics.Event.LOGIN, bundle);
            setCrashlytics(uId);
        }
    }

    /*
     * ******************************Crashlytics*****************************
     */

    /**
     * 自定义日志消息
     * 1、崩溃报告和 Log.println
     * 2、仅崩溃报告
     */
    public static void printAndReportCrashlog(int logType, String tag, String message) {
        Crashlytics.log(logType, tag, message);
    }

    public static void reportCrashLog(String message) {
        Crashlytics.log(message);
    }

    /**
     * 自定义键
     * 获取导致崩溃的应用的特定状态,将任意键值对与您的崩溃报告相关联，
     * 然后在 Firebase 控制台中查看这些键值对。
     */
    public static void setStatus(String key, Object value) {
        if (value instanceof String) {
            Crashlytics.setString(key, (String) value);
        } else if (value instanceof Boolean) {
            Crashlytics.setBool(key, (Boolean) value);
        } else if (value instanceof Double) {
            Crashlytics.setDouble(key, (Double) value);
        } else if (value instanceof Float) {
            Crashlytics.setFloat(key, (Float) value);
        } else if (value instanceof Integer) {
            Crashlytics.setInt(key, (Integer) value);
        } else if (value instanceof Long) {
            Crashlytics.setLong(key, (Long) value);
        }
    }


    /**
     * 设置用户标识符
     * 要将用户 ID 添加到报告中，请以 ID 编号、令牌或哈希值的形式为每个用户分配一个唯一标识符
     * uId: 自定义参数
     */
    public static void setCrashlytics(String uId) {
        Crashlytics.setUserIdentifier(uId);
    }

    /*
     * 记录非严重异常
     * 除了自动报告您的应用中出现的崩溃，Crashlytics 还可让您记录非严重异常。
     * 在 Android 上，这意味着您可以在应用的 catch 块中记录已捕获到的异常：
     * try {
     *             methodThatThrows();
     *         } catch (Exception e) {
     *             Crashlytics.logException(e);
     *             // handle your exception here
     *         }
     */


    /*
     * 默认情况下，Firebase Crashlytics 会自动为您的应用的所有用户收集崩溃报告。为了让用户对其发送的数据有更多的控制权，您可以启用自选式报告功能
     * <meta-data android:name="firebase_crashlytics_collection_enabled" android:value="false" />
     * 使用 AndroidManifest.xml 文件中的 meta-data 标记来关闭自动收集功能：
     */

    /*
     * 在应用的某个 Activity 中初始化 Crashlytics，从而为选定的用户启用收集功能：
     * Fabric.with(this, new Crashlytics());
     */

}
