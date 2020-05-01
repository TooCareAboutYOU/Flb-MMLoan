package com.mmkj.usercenter.viewmodel.reypaylist;


import android.text.TextUtils;

import com.mmkj.baselibrary.base.RxPresenter;
import com.mmkj.usercenter.R;
import com.mmkj.usercenter.model.UserCenterDataManager;
import com.mmkj.usercenter.model.entity.CurrentPaymentData;
import com.mmkj.usercenter.model.entity.PaymentData;
import com.mmkj.usercenter.model.entity.PaymentListData;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by Administrator on 2017/10/24.
 */

public class RepayListPresenter extends RxPresenter<RepayListContract.View> implements RepayListContract.Presenter {

    private UserCenterDataManager mDataManager;
    private PaymentData paymentData;

    @Inject
    public RepayListPresenter(UserCenterDataManager mDataManager) {
        this.mDataManager = mDataManager;
    }


    @Override
    public void getPaymentCode(String sessionId) {
        addSubscribe(mDataManager.getPaymentCode(sessionId).doOnSubscribe(subscription -> mView.showProgress()).subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(baseBean -> {
                    mView.getPaymentCodeSuc(baseBean);
                    mView.hideProgress();
                }, throwable -> mView.hideProgress(R.string.neterror_tryagain)));
    }

    public void initPaymentData(PaymentData paymentData) {
        this.paymentData = paymentData;

    }

    public ArrayList<PaymentListData> getPaymentList() {
        if (paymentData != null) {
            return paymentData.getReturnedBills();
        } else {
            return null;
        }
    }

    public CurrentPaymentData getCurrent() {
        if (paymentData != null) {
            return paymentData.getShouldBills();
        } else {
            return new CurrentPaymentData();
        }

    }

    public boolean isNoRepay() {
        return getCurrent().getAmount() == 0;
    }

    public boolean isShwoRepayCode() {
        return  getCurrent().getAmount() > 0 && !TextUtils.isEmpty(getCurrent().getPaymentCode());
    }




}

