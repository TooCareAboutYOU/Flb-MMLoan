package com.mmkj.usercenter.viewmodel.service;

import com.mmkj.baselibrary.base.RxPresenter;
import com.mmkj.usercenter.R;
import com.mmkj.usercenter.model.UserCenterDataManager;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class CustomerServicePresenter extends RxPresenter<CustomerServiceContract.View> implements CustomerServiceContract.Presenter{

    private UserCenterDataManager mDataManager;

    @Inject
    public CustomerServicePresenter(UserCenterDataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void getServiceInfo() {
        addSubscribe(mDataManager.getCustomerService()
                .doOnSubscribe(subscription -> mView.showProgress()).subscribeOn(AndroidSchedulers.mainThread())
        .subscribe(listBaseBean -> {
            mView.getServiceInfoSuc(listBaseBean);
            mView.hideProgress();
        },throwable -> mView.hideProgress(R.string.neterror_tryagain)));
    }
}
