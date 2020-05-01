package com.mmkjflb.mmloan.viewmodel.repay;

import com.mmkjflb.mmloan.R;
import com.mmkjflb.mmloan.model.DataManager;
import com.mmkj.baselibrary.base.RxPresenter;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by Administrator on 2017/10/29.
 */

public class RepayPresenter extends RxPresenter<RepayContract.View> implements RepayContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public RepayPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void queryBillsInfo(String sessionId) {
        addSubscribe(mDataManager.queryBillsInfo(sessionId)
                .doOnSubscribe(subscription -> mView.showLoading())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(baseBean -> {
                    mView.queryBillsInfoSuc(baseBean);
                    mView.hideLoading();
                }, throwable -> mView.onError(1)));
    }

    @Override
    public void submitActiveRepay(Map<String, Object> params) {
        addSubscribe(mDataManager.submitActiveRepay(params)
                .doOnSubscribe(subscription -> mView.showProgress())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(baseBean -> {
                    mView.submitActiveRepaySuc(baseBean);
                    mView.hideProgress();
                }, throwable -> mView.hideProgress(R.string.neterror_tryagain)));
    }

//    @Override
//    public void updateRepayChannel(Map<String, Object> params) {
//        addSubscribe(mDataManager.updateRepayChannel(params)
//                .doOnSubscribe(subscription -> mView.showProgress())
//                .subscribeOn(AndroidSchedulers.mainThread())
//                .subscribe(baseBean -> {
//                    mView.updateRepayChannelSuc(baseBean);
//                    mView.hideProgress();
//                }, throwable -> mView.hideProgress(R.string.neterror_tryagain)));
//    }

    @Override
    public void getRepaymentShop() {
        addSubscribe(mDataManager.getRepaymentShop()
                .doOnSubscribe(subscription -> mView.showProgress())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(baseBean -> {
                    mView.getRepaymentShopSuc(baseBean);
                    mView.hideProgress();
                }, throwable -> mView.hideProgress(R.string.neterror_tryagain))
        );
    }

//    @Override
//    public void getRepaymentShopNew(String sessionId) {
//        addSubscribe(mDataManager.getRepaymentShopNew(sessionId)
//                .doOnSubscribe(subscription -> mView.showProgress())
//                .subscribeOn(AndroidSchedulers.mainThread())
//                .subscribe(baseBean -> {
//                    mView.getRepaymentShopNewSuc(baseBean);
//                    mView.hideProgress();
//                }, throwable -> mView.hideProgress(R.string.neterror_tryagain))
//        );
//    }

    @Override
    public void getPaymentCode(String sessionId) {
        addSubscribe(mDataManager.getPaymentCode(sessionId)
                .doOnSubscribe(subscription -> mView.showProgress())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(baseBean -> {
                    mView.getPaymentCodeSuc(baseBean);
                    mView.hideProgress();
                }, throwable -> mView.hideProgress(R.string.neterror_tryagain))
        );
    }


}