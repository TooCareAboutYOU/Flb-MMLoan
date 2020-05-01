package com.mmkj.baselibrary.util;

import androidx.annotation.NonNull;

import android.view.View;

import com.jakewharton.rxbinding2.view.RxView;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class RxViewUtil {
    @NonNull
    public static Observable<Object> clicks(@NonNull View view) {
        return RxView.clicks(view).throttleFirst(1, TimeUnit.SECONDS, AndroidSchedulers.mainThread());
    }


    /**
     * 倒计时
     *
     * @param time
     * @return
     */
    public static Observable<Integer> countdown(int time) {
        if (time < 0) {
            time = 0;
        }
        final int countTime = time;
        return Observable.interval(0, 1, TimeUnit.SECONDS)
                .map(aLong -> countTime - aLong.intValue())
                .take(countTime + 1)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
