package com.mmkj.usercenter.viewmodel.repaymentchannel;

import com.mmkj.baselibrary.base.RxPresenter;
import com.mmkj.usercenter.R;
import com.mmkj.usercenter.model.UserCenterDataManager;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class RepaymentChannelPresenter extends RxPresenter<RepaymentChannelContract.View> implements RepaymentChannelContract.Presenter{

    private UserCenterDataManager mDataManager;

    @Inject
    public RepaymentChannelPresenter(UserCenterDataManager mDataManager) {
        this.mDataManager = mDataManager;
    }


    @Override
    public void getRepayChannelByUser(String sessionId) {
        addSubscribe(mDataManager.getRepayChannelByUser(sessionId)
                .doOnSubscribe(subscription -> mView.showProgress()).subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(listBaseBean -> {
                    mView.getRepayChannelByUserSuc(listBaseBean);
                    mView.hideProgress();
                },throwable -> mView.hideProgress(R.string.neterror_tryagain)));
    }
}
