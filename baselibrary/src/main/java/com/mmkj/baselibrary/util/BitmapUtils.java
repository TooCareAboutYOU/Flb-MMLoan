package com.mmkj.baselibrary.util;

import android.content.Context;
import android.text.TextUtils;

import com.bumptech.glide.Glide;
import com.mmkj.baselibrary.util.callback.DealPicCallBack;

import java.io.File;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

/**
 * date: 2018/3/15 17:23
 * author: xuyexiang
 * title:
 */

public class BitmapUtils {

    public static Disposable downLoadPic(Context context, String imgUrl, DealPicCallBack dealPicCallBack) {
        return Flowable.just(imgUrl)
                .subscribeOn(Schedulers.io())
                .map(url -> Glide.with(context).download(url).submit().get())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(dealPicCallBack::success, throwable -> {
                });
    }
    public static void compressPic(Context context, String imgUrl, DealPicCallBack compressPic){
        Luban.with(context)
                .filter(path -> !TextUtils.isEmpty(path))
                .load(imgUrl)
                .ignoreBy(100)
                .setCompressListener(new OnCompressListener() {
                    @Override
                    public void onStart() {
                    }

                    @Override
                    public void onSuccess(File file) {
                        compressPic.success(file);
                    }

                    @Override
                    public void onError(Throwable e) {
                        compressPic.error();
                    }
                }).launch();
    }
}
