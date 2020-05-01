package com.mmkjflb.mmloan.viewmodel.splash;

import com.mmkjflb.mmloan.R;
import com.mmkjflb.mmloan.model.DataManager;
import com.mmkj.baselibrary.base.RxPresenter;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by Administrator on 2017/8/16.
 */

public class SplashPresenter extends RxPresenter<SplashContract.View> implements SplashContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public SplashPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }


    /**
     * 获取app版本号
     * @param platformType
     * @param packageType   包名
     */
    @Override
    public void getAppVersion(String platformType, String packageType) {
		addSubscribe(mDataManager.getAppVersion(platformType,packageType).doOnSubscribe(subscription -> mView.showProgress()).subscribeOn(AndroidSchedulers.mainThread())
				.subscribe(baseBean -> {
					mView.getAppVersionSuc(baseBean);
					mView.hideProgress();
				}, throwable -> mView.hideProgress(R.string.neterror_tryagain)));
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

    @Override
    public void insertChannelGoogle(Map<String, Object> map) {
        addSubscribe(mDataManager.insertChannelGoogle(map)
                .doOnSubscribe(subscription -> mView.showProgress())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(baseBean -> {
                    mView.insertChannelGoogleSuc(baseBean);
                    mView.hideProgress();
                },throwable -> mView.hideProgress(R.string.neterror_tryagain)));
    }
}