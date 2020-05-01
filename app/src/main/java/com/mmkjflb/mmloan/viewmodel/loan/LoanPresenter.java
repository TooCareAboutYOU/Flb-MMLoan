package com.mmkjflb.mmloan.viewmodel.loan;

import com.mmkjflb.mmloan.R;
import com.mmkjflb.mmloan.model.DataManager;
import com.mmkj.baselibrary.base.RxPresenter;
import com.mmkj.baselibrary.util.PreferenceUtils;
import com.orhanobut.logger.Logger;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

/**
 * Created by Administrator on 2017/8/10.
 */

public class LoanPresenter extends RxPresenter<LoanContract.View> implements LoanContract.Presenter {

    private DataManager mDataManager;

    @Inject
    LoanPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }


    @Override
    public void loanInfo(String sesstionId) {
        addSubscribe(mDataManager.loanInfo(sesstionId).doOnSubscribe(subscription -> mView.showProgress()).subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(baseBean -> {
                    mView.loanInfoSuc(baseBean);
                    mView.hideProgress();
                }, throwable -> {
//                    mView.loadingError(v -> loanInfo(PreferenceUtils.getUserSessionid()));
                    Logger.e("loanInfo 异常 ： "+throwable.toString());

                    mView.hideProgress(R.string.neterror_tryagain);
                }));
    }

    @Override
    public void getGCashDetail(String sessionId) {
        addSubscribe(mDataManager.getGCashDetail(sessionId).doOnSubscribe(subscription -> mView.showProgress()).subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(baseBean -> {
                    mView.getGCashDetailSuc(baseBean);
                    mView.hideProgress();
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Logger.e("getGCashDetail 异常 ： "+throwable.toString());

                        mView.hideProgress(R.string.neterror_tryagain);
                    }
                }));
    }

    @Override
    public void getPaymentShopListAll() {
        addSubscribe(mDataManager.getPaymentShopListAll().doOnSubscribe(subscription -> mView.showProgress()).subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(baseBean -> {
                    mView.getPaymentShopListAllSuc(baseBean);
                    mView.hideProgress();
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Logger.e("getPaymentShopListAll 异常 ： "+throwable.toString());
                        mView.hideProgress(R.string.neterror_tryagain);
                    }
                }));
    }


    @Override
    public void surely(Map<String, Object> params) {
        addSubscribe(mDataManager.surely(params).doOnSubscribe(subscription -> mView.showProgress()).subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(baseBean -> {
                    mView.surelySuc(baseBean);
                    mView.hideProgress();
                }, throwable -> mView.hideProgress(R.string.neterror_tryagain)));
    }
}
