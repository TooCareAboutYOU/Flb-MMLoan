package com.mmkjflb.mmloan.viewmodel.repaychannel;

import com.mmkj.baselibrary.base.RxPresenter;
import com.mmkjflb.mmloan.R;
import com.mmkjflb.mmloan.model.DataManager;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class RepayChannelsPresenter extends RxPresenter<RepayChannelsContract.View> implements RepayChannelsContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public RepayChannelsPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void getRepaymentShopNew(String sessionId) {
        addSubscribe(mDataManager.getRepaymentShopNew(sessionId)
                .doOnSubscribe(subscription -> mView.showProgress())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(baseBean -> {
                    mView.getRepaymentShopNewSuc(baseBean);
                    mView.hideProgress();
                }, throwable -> mView.hideProgress(R.string.neterror_tryagain))
        );
    }

    @Override
    public void updateRepayChannel(Map<String, Object> params) {
        addSubscribe(mDataManager.updateRepayChannel(params)
                .doOnSubscribe(subscription -> mView.showProgress())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(baseBean -> {
                    mView.updateRepayChannelSuc(baseBean);
                    mView.hideProgress();
                }, throwable -> mView.hideProgress(R.string.neterror_tryagain)));
    }
}
