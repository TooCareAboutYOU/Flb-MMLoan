package com.mmkjflb.mmloan.viewmodel.enploymentinformation;

import com.mmkj.baselibrary.util.PreferenceUtils;
import com.mmkjflb.mmloan.R;
import com.mmkjflb.mmloan.model.DataManager;
import com.mmkj.baselibrary.base.RxPresenter;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import okhttp3.RequestBody;

/**
 * Created by
 * Date: On 2018/3/5
 * Author: wangjieyan
 * Email: wangjieyan9308@163.com
 */

public class EnploymentinformationPresenter extends RxPresenter<EnploymentInfoContract.View> implements EnploymentInfoContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public EnploymentinformationPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void authentStatus(String sessionId) {
        addSubscribe(mDataManager.authentStatus(sessionId)
                .doOnSubscribe(subscription ->  mView.showProgress()).subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(baseBean -> {
                    mView.authentStatusSuc(baseBean);
                    mView.hideProgress();
                }, throwable -> mView.hideProgress(R.string.neterror_tryagain)));
        //mView.loadingError(v -> authentStatus(PreferenceUtils.getUserSessionid())))
    }
}
