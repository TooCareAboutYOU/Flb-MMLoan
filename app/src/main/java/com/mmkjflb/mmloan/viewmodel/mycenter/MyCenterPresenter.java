package com.mmkjflb.mmloan.viewmodel.mycenter;

import com.mmkjflb.mmloan.model.DataManager;
import com.mmkj.baselibrary.base.RxPresenter;

import javax.inject.Inject;

/**
 * Date: 2018/1/16 15:28
 * Author: xuyexiang
 * Title:
 */

public class MyCenterPresenter extends RxPresenter<MyCenterContract.View>{

    private DataManager mDataManager;

    @Inject
    public MyCenterPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }


//    @Override
//    public void queryInviteActivity(String sessionId) {
//        addSubscribe(mDataManager.queryInviteActivity(sessionId).doOnSubscribe(new Consumer<Subscription>() {
//            @Override
//            public void accept(@NonNull Subscription subscription) throws Exception {
//                mView.showProgress();
//            }
//        }).subscribeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<BaseBean<QueryInviteActivityEntity>>() {
//                    @Override
//                    public void accept(@NonNull BaseBean<QueryInviteActivityEntity> baseBean) throws Exception {
//                        mView.queryInviteActivitySuc(baseBean);
//                        mView.hideProgress();
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(@NonNull Throwable throwable) throws Exception {
//                        mView.hideProgress(R.string.neterror_tryagain);
//                    }
//                }));
//    }
}