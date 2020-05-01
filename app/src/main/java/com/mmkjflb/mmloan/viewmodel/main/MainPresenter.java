package com.mmkjflb.mmloan.viewmodel.main;

import android.util.Log;

import com.mmkj.baselibrary.base.RxPresenter;
import com.mmkj.baselibrary.util.PreferenceUtils;
import com.mmkjflb.mmloan.BuildConfig;
import com.mmkjflb.mmloan.R;
import com.mmkjflb.mmloan.model.DataManager;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by Administrator on 2017/8/10.
 */

public class MainPresenter extends RxPresenter<MainContract.View> implements MainContract.Presenter {
    private DataManager mDataManager;

    @Inject
    MainPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void balance(final boolean unfirst, String sesstionId) {
        addSubscribe(mDataManager.balance(sesstionId).doOnSubscribe(subscription -> {
            if (!unfirst) {
                mView.showLoading();
            }
        }).subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(baseBean -> {
                    mView.balanceSuc(baseBean);
                    if (!unfirst) {
                        mView.hideLoading();
                    }
                }, throwable -> mView.loadingError(v -> balance(false, PreferenceUtils.getUserSessionid()))));
    }

    @Override
    public void confirmIncreaseCashDeposit(String productCode, String sessionId) {
        addSubscribe(mDataManager.confirmIncreaseCashDeposit(productCode, sessionId)
                .doOnSubscribe(subscription -> mView.showProgress())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(baseBean -> {
                    mView.confirmIncreaseCashDepositSuc(baseBean);
                    mView.hideProgress();
                }, throwable -> mView.hideProgress(R.string.neterror_tryagain)));
    }

    @Override
    public void sendCode(String mobile, String smsCode) {
        addSubscribe(mDataManager.sendSms(mobile, smsCode).doOnSubscribe(subscription -> mView.showProgress()).subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(baseBean -> {
                    mView.sendCodeSuc(baseBean);
                    mView.hideProgress();
                }, throwable -> mView.hideProgress(R.string.neterror_tryagain)));
    }

    @Override
    public void afterBorrowCheck(Map<String, Object> map) {
        addSubscribe(mDataManager.afterBorrowCheck(map).doOnSubscribe(subscription -> mView.showProgress()).subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(baseBean -> {
                    mView.afterBorrowCheckSuc(baseBean);
                    mView.hideProgress();
                }, throwable -> mView.hideProgress(R.string.neterror_tryagain)));
    }

    @Override
    public void getDepositInfo(String sessionId) {
        addSubscribe(mDataManager.getDepositInfo(sessionId).doOnSubscribe(subscription -> mView.showProgress()).subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(baseBean -> {
                    mView.getDepositInfoSuc(baseBean);
                    mView.hideProgress();
                }, throwable -> {
                    if (BuildConfig.DEBUG) {
                        Log.e("PRETTY_LOGGER", "getDepositInfo: "+throwable.toString());
                    }
                    mView.hideProgress(R.string.neterror_tryagain);
                }));
    }
}
