package com.mmkjflb.mmloan.viewmodel.fragment.payroll;

import com.mmkj.baselibrary.base.RxPresenter;
import com.mmkjflb.mmloan.R;
import com.mmkjflb.mmloan.model.DataManager;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by
 * Date: On 2018/3/5
 * Author: wangjieyan
 * Email: wangjieyan9308@163.com
 */

public class PayrollPresenter extends RxPresenter<PayrollContract.View> implements PayrollContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public PayrollPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void getQiniuToken(String sessionId) {
        addSubscribe(mDataManager.getQiniuToken(sessionId).doOnSubscribe(subscription -> {
            if (mView != null) {
                mView.showProgress();
            }
        }).subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(baseBean -> {
                    if (mView != null) {
                        mView.hideProgress();
                    }
                    mView.getQiniuTokenSuc(baseBean);
                }, throwable -> {
                    if (mView != null) {
                        mView.hideProgress(R.string.neterror_tryagain);
                    }
                }));
    }

    @Override
    public void salariesinfo(Map<String, Object> params) {
        addSubscribe(mDataManager.salariesinfo(params).doOnSubscribe(subscription -> {
            if (mView != null) {
                mView.showProgress();
            }

        }).subscribeOn(AndroidSchedulers.mainThread()).subscribe(baseBean -> {
            if (mView != null) {
                mView.hideProgress();
            }
            mView.salariesinfoSuc(baseBean);
        }, throwable -> {
            if (mView != null) {
                mView.hideProgress(R.string.neterror_tryagain);
            }
        }));
    }

    @Override
    public void getSalariesInfo(String sessionId) {
        addSubscribe(mDataManager.getSalariesInfo(sessionId).doOnSubscribe(subscription -> {
            if (mView != null) {
                mView.showProgress();
            }

        }).subscribeOn(AndroidSchedulers.mainThread()).subscribe(baseBean -> {
            if (mView != null) {
                mView.hideProgress();
            }
            mView.getSalariesInfoSuc(baseBean);
        }, throwable -> {
            if (mView != null) {
                mView.hideProgress();
            }
        }));
    }

    @Override
    public void getCompanyNature() {
        addSubscribe(mDataManager.getCompanyNature().doOnSubscribe(subscription -> {
            if (mView != null) {
                mView.showProgress();
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).subscribe(baseBean -> {
            if (mView != null) {
                mView.hideProgress();
            }
            mView.getCompanyNatureSuc(baseBean);
        }, throwable -> {
            if (mView != null) {
                mView.hideProgress();
            }
        }));
    }
}
