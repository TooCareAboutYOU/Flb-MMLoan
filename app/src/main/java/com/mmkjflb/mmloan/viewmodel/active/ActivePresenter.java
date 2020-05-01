package com.mmkjflb.mmloan.viewmodel.active;

import com.mmkjflb.mmloan.model.DataManager;
import com.mmkj.baselibrary.base.RxPresenter;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Date: 2018/1/16 15:28
 * Author: xuyexiang
 * Title:
 */

public class ActivePresenter extends RxPresenter<ActiveContract.View> implements ActiveContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public ActivePresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void queryActivity(String sessionId) {
        addSubscribe(mDataManager.queryActivity(sessionId)
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(mView::queryActivitySuc, throwable -> mView.onError()));
    }
}