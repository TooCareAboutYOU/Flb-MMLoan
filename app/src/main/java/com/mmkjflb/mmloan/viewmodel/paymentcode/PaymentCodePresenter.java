package com.mmkjflb.mmloan.viewmodel.paymentcode;

import com.google.gson.Gson;
import com.mmkj.baselibrary.base.RxPresenter;
import com.mmkjflb.mmloan.R;
import com.mmkjflb.mmloan.model.DataManager;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class PaymentCodePresenter extends RxPresenter<PaymentCodeContact.View> implements PaymentCodeContact.Presenter{

    private DataManager mDataManager;

    @Inject
    public PaymentCodePresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }


    @Override
    public void getDepositCode(String sessionId) {
        Map<String, Object> map = new HashMap<>();
        map.put("sessionId", sessionId);
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(map));

        addSubscribe(mDataManager.getDepositCode(requestBody).doOnSubscribe(subscription -> mView.showProgress()).subscribeOn(AndroidSchedulers.mainThread())
        .subscribe(baseBean -> {
            mView.getDepositCodeSuc(baseBean);
            mView.hideProgress();
        },throwable -> mView.hideProgress(R.string.neterror_tryagain)));
    }

    @Override
    public void queryDepositCode(String sessionId) {
        addSubscribe(mDataManager.queryDepositCode(sessionId).doOnSubscribe(subscription -> mView.showProgress()).subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(baseBean -> {
                    mView.queryDepositCodeSuc(baseBean);
                    mView.hideProgress();
                },throwable -> mView.hideProgress(R.string.neterror_tryagain)));
    }
}
