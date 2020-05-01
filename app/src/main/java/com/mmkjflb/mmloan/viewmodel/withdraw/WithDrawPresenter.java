package com.mmkjflb.mmloan.viewmodel.withdraw;

import com.mmkj.baselibrary.base.RxPresenter;
import com.mmkjflb.mmloan.model.DataManager;

import javax.inject.Inject;

/**
 * Created by Administrator on 2017/8/10.
 */

public class WithDrawPresenter extends RxPresenter<WithDrawContract.View> implements WithDrawContract.Presenter {
    private DataManager mDataManager;

    @Inject
    WithDrawPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }


    @Override
    public void getWithdrawalNumber(String sesstionId) {
        addSubscribe(mDataManager.getWithdrawalNumber(sesstionId).subscribe(baseBean -> mView.getWithdrawalNumberSuc(baseBean), throwable -> {
        }));
    }
}
