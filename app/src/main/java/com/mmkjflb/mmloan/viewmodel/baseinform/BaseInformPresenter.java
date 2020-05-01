package com.mmkjflb.mmloan.viewmodel.baseinform;

import com.mmkj.baselibrary.base.RxPresenter;
import com.mmkjflb.mmloan.R;
import com.mmkjflb.mmloan.model.DataManager;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by Administrator on 2017/8/10.
 */

public class BaseInformPresenter extends RxPresenter<BaseInformContract.View> implements BaseInformContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public BaseInformPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void baseinfo(Map<String, Object> map) {
        addSubscribe(mDataManager.baseinfo(map)
                .subscribe(baseBean -> {
                    mView.baseinfoSuc(baseBean);
                    mView.hideProgress();
                }, throwable -> mView.hideProgress(R.string.neterror_tryagain)));
    }

    @Override
    public void getBaseInfo(String sessionId) {
        addSubscribe(mDataManager.getBaseInfo(sessionId).doOnSubscribe(subscription -> mView.showProgress()).subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(baseBean -> {
                    mView.getBaseInfoSuc(baseBean);
                    mView.hideProgress();
                }, throwable -> mView.hideProgress(R.string.neterror_tryagain)));
    }
    @Override
    public void getQiniuToken(String sessionId) {
        addSubscribe(mDataManager.getQiniuToken(sessionId).doOnSubscribe(subscription -> mView.showProgress()).subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(baseBean -> {
                    mView.getQiniuTokenSuc(baseBean);
                }, throwable -> mView.hideProgress(R.string.neterror_tryagain)));
    }
}
