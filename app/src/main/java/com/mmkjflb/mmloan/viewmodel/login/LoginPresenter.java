package com.mmkjflb.mmloan.viewmodel.login;

import com.mmkj.baselibrary.util.PreferenceUtils;
import com.mmkjflb.mmloan.R;
import com.mmkjflb.mmloan.model.DataManager;
import com.mmkj.baselibrary.base.RxPresenter;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by Administrator on 2017/8/10.
 */

public class LoginPresenter extends RxPresenter<LoginContract.View> implements LoginContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public LoginPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void userLogin(String tel, String password) {
        addSubscribe(mDataManager.login(tel, password).subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(loginBeanBaseBean -> {
                    mView.loginSuccess(loginBeanBaseBean);
                    mView.hideProgress();
                }, throwable -> mView.hideProgress(R.string.neterror_tryagain)));
    }

    @Override
    public void deviceInfo(Map<String, String> map) {
        mDataManager.deviceInfo(map).doOnSubscribe(subscription -> {
        }).subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(baseBean -> { }, throwable -> { });
    }
}
