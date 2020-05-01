package com.mmkjflb.mmloan.viewmodel.bindgcash;

import com.mmkj.baselibrary.base.RxPresenter;
import com.mmkj.baselibrary.model.entitry.BaseBean;
import com.mmkjflb.mmloan.R;
import com.mmkjflb.mmloan.model.DataManager;
import com.mmkjflb.mmloan.model.entity.ProductInfoBean;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

/**
 * Created by Administrator on 2017/10/28.
 */

public class BindGCashPresenter extends RxPresenter<BindGCashContract.View> implements BindGCashContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public BindGCashPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void getAmountType(String sessionId) {
        addSubscribe(mDataManager.getAmountType(sessionId).doOnSubscribe(subscription -> mView.showProgress()).subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(baseBean -> {
                    mView.getAmountTypeSuc(baseBean);
                    mView.hideProgress();
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.hideProgress(R.string.neterror_tryagain);
                    }
                }));
    }
    @Override
    public void sendSms(String mobile, String smsCode) {
        addSubscribe(mDataManager.sendSms(mobile, smsCode).doOnSubscribe(subscription -> mView.showProgress()).subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(baseBean -> {
                    mView.sendSmsSuc(baseBean);
                    mView.hideProgress();
                }, throwable -> mView.hideProgress(R.string.neterror_tryagain)));
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
                        mView.hideProgress(R.string.neterror_tryagain);
                    }
                }));
    }

    @Override
    public void updateGCash(String sessionId, String userGCashAccount, String captcha) {
        addSubscribe(mDataManager.updateGCash(sessionId, userGCashAccount, captcha).doOnSubscribe(subscription -> mView.showProgress()).subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(baseBean -> {
                    mView.updateGCashSuc(baseBean);
                    mView.hideProgress();
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.hideProgress(R.string.neterror_tryagain);
                    }
                }));
    }

    @Override
    public void deviceInfo(Map<String, String> map) {
        addSubscribe(mDataManager.deviceInfo(map).doOnSubscribe(subscription -> mView.showProgress()).subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(baseBean -> {
                    mView.deviceInfoSuc(baseBean);
                    mView.hideProgress();
                }, throwable -> mView.hideProgress(R.string.neterror_tryagain))
        );
    }

    @Override
    public void getProductInfo(String sessionId, int type) {
        addSubscribe(mDataManager.getProductInfo(sessionId, type).doOnSubscribe(subscription -> mView.showProgress()).subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseBean<List<ProductInfoBean>>>() {
                    @Override
                    public void accept(BaseBean<List<ProductInfoBean>> baseBean) throws Exception {
                        mView.getProductInfoSuc(baseBean);
                        mView.hideProgress();
                    }
                }, throwable -> mView.hideProgress(R.string.neterror_tryagain))
        );
    }

    @Override
    public void updateProductCode(String sessionId, int productType) {
        addSubscribe(mDataManager.updateProductCode(sessionId, productType).doOnSubscribe(subscription -> mView.showProgress()).subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseBean>() {
                    @Override
                    public void accept(BaseBean baseBean) throws Exception {
                        mView.updateProductCodeSuc(baseBean);
                        mView.hideProgress();
                    }
                }, throwable -> mView.hideProgress(R.string.neterror_tryagain))
        );
    }
}

