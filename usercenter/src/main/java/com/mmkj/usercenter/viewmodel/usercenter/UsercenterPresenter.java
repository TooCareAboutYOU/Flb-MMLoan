package com.mmkj.usercenter.viewmodel.usercenter;

import com.mmkj.baselibrary.base.RxPresenter;
import com.mmkj.usercenter.model.UserCenterDataManager;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import com.mmkj.usercenter.R;

public class UsercenterPresenter extends RxPresenter<UserCenterContract.View> implements UserCenterContract.Presenter {

    private UserCenterDataManager mDataManager;

    @Inject
    public UsercenterPresenter(UserCenterDataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void getGCashDetail(String sessionId) {
        addSubscribe(mDataManager.getGCashDetail(sessionId)
                .doOnSubscribe(subscription -> mView.showProgress()).subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(baseBean -> {
                    mView.getGCashDetailSuc(baseBean);
                    mView.hideProgress();
                }, throwable -> mView.hideProgress(R.string.neterror_tryagain)));
    }
}
