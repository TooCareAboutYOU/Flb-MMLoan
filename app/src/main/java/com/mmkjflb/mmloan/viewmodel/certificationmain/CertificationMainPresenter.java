package com.mmkjflb.mmloan.viewmodel.certificationmain;

import com.mmkjflb.mmloan.R;
import com.mmkjflb.mmloan.model.DataManager;
import com.mmkj.baselibrary.base.RxPresenter;
import com.mmkj.baselibrary.util.PreferenceUtils;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import okhttp3.RequestBody;

/**
 * Created by Administrator on 2017/10/24.
 */

public class CertificationMainPresenter extends RxPresenter<CertificationMainContract.View> implements CertificationMainContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public CertificationMainPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }


    @Override
    public void authentStatus(String sessionId) {
        addSubscribe(mDataManager.authentStatus(sessionId).doOnSubscribe(subscription -> mView.showLoading()).subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(baseBean -> {
                    mView.authentStatusSuc(baseBean);
                    mView.hideLoading();
                }, throwable -> mView.loadingError(v -> authentStatus(PreferenceUtils.getUserSessionid()))));
    }
}
