package com.mmkjflb.mmloan.viewmodel.bankrepay;

import com.mmkj.baselibrary.base.RxPresenter;
import com.mmkjflb.mmloan.R;
import com.mmkjflb.mmloan.model.DataManager;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by Administrator on 2017/10/28.
 */

public class BankRepayPresenter extends RxPresenter<BankRepayContract.View> implements BankRepayContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public BankRepayPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void certificateDetail(String sessionId, int bidId, int billId) {
        addSubscribe(mDataManager.certificateDetail(sessionId, bidId, billId).doOnSubscribe(subscription -> mView.showProgress()).subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(baseBean -> {
                    mView.certificateDetailSuc(baseBean);
                    mView.hideProgress();
                }, throwable -> mView.hideProgress()));
    }

    @Override
    public void collectionAccount(String sessionId) {
        addSubscribe(mDataManager.collectionAccount(sessionId).doOnSubscribe(subscription -> mView.showProgress()).subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(baseBean -> {
                    mView.collectionAccountSuc(baseBean);
                    mView.hideProgress();
                }, throwable -> mView.hideProgress(R.string.neterror_tryagain)));
    }

    @Override
    public void certificateUpload(Map<String, Object> map) {
        addSubscribe(mDataManager.certificateUpload(map)
                .subscribe(baseBean -> {
                    mView.certificateUploadSuc(baseBean);
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
