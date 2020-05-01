package com.mmkj.usercenter.viewmodel.creditguarantee;


import android.util.Log;

import com.mmkj.baselibrary.base.RxPresenter;
import com.mmkj.baselibrary.model.entitry.BaseBean;
import com.mmkj.usercenter.R;
import com.mmkj.usercenter.model.UserCenterDataManager;
import com.mmkj.usercenter.model.entity.CreditManagerEntity;
import javax.inject.Inject;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

/**
 * @author wendjia
 * @date 2017/10/24
 */

public class GreditGuaranteePresenter extends RxPresenter<GreditGuaranteeContract.View> implements GreditGuaranteeContract.Presenter {

    private UserCenterDataManager mDataManager;

    @Inject
    public GreditGuaranteePresenter(UserCenterDataManager mDataManager) {
        this.mDataManager = mDataManager;
    }


    @Override
    public void queryCreditManager(String sessionId) {
        addSubscribe(mDataManager.queryCreditManager(sessionId).doOnSubscribe(subscription -> mView.showProgress()).subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseBean<CreditManagerEntity>>() {
                    @Override
                    public void accept(BaseBean<CreditManagerEntity> baseBean) throws Exception {
                        mView.queryCreditManagerSuc(baseBean);
                        mView.hideProgress();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.hideProgress(R.string.neterror_tryagain);
                    }
                }));
    }

    @Override
    public void confirmIncreaseCashDeposit(String productCode, String sessionId) {
        addSubscribe(mDataManager.confirmIncreaseCashDeposit(productCode, sessionId)
                .doOnSubscribe(subscription -> mView.showProgress())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(baseBean -> {
                    mView.confirmIncreaseCashDepositSuc(baseBean);
                    mView.hideProgress();
                }, throwable -> mView.hideProgress(R.string.neterror_tryagain)));
    }

    @Override
    public void getDepositInfo(String sessionId) {
        addSubscribe(mDataManager.getDepositInfo(sessionId)
                .doOnSubscribe(subscription -> mView.showProgress())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(baseBean -> {
                    mView.getDepositInfoSuc(baseBean);
                    mView.hideProgress();
                }, throwable -> {
                    Log.e("PRETTY_LOGGER", "getDepositInfo: " + throwable.toString());
                    mView.hideProgress(R.string.neterror_tryagain);
                }));
    }
}

