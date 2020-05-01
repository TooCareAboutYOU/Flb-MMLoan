package com.mmkj.baselibrary.app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;
import com.mmkj.baselibrary.BuildConfig;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Author: wendjia
 * Time: 2018\8\31 0031
 * Description:
 **/
public class ActivityStack {

    private static final String TAG = "ActivityStacks";

    protected List<Activity> activitys = new LinkedList<Activity>();

    private volatile static ActivityStack mInstance = null;

    public static synchronized ActivityStack getInstance() {
        if (mInstance == null) {
            synchronized (ActivityStack.class) {
                if (mInstance == null) {
                    mInstance = new ActivityStack();
                }
            }
        }
        return mInstance;
    }


    public void addActivity(Activity activity) {
        if (activitys == null) {
            activitys = new LinkedList<>();
        }
        activitys.add(activity);
    }

    public void removeActivity(Activity activity) {
        if (activitys != null && activitys.contains(activity)) {
            activity.finish();
            activitys.remove(activity);
        }
    }


    public void removeActivityByClass(Class<?> cls) {
        Iterator<Activity> iterator = activitys.iterator();
        while (iterator.hasNext()) {
            Activity activity = iterator.next();
            if (activity.getClass().getName().equals(cls.getName())) {
                activity.finish();
                iterator.remove();
            }
        }
    }

    public void removeAllExceptCurrent(Activity current) {
        if (activitys != null && current != null) {
            Iterator<Activity> iterator = activitys.iterator();
            while (iterator.hasNext()) {
                Activity activity = iterator.next();
                if (!activity.getClass().getName().equals(current.getClass().getName())) {
                    activity.finish();
                    iterator.remove();
                }
            }
        }
    }

    public void exitApp() {
        if (activitys != null) {
            for (Activity activity : activitys) {
                if (activity != null && !activity.isFinishing()) {
                    activity.finish();
                }
            }
        }
        //Destroy arouter, it can be used only in debug mode.
        if (BuildConfig.DEBUG) {
            ARouter.getInstance().destroy();
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }
}
