package com.mmkjflb.mmloan.viewmodel.socialinform;

import com.mmkjflb.mmloan.R;
import com.mmkjflb.mmloan.model.DataManager;
import com.mmkj.baselibrary.base.RxPresenter;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by Administrator on 2017/8/10.
 */

public class SocialInformPresenter extends RxPresenter<SocialInformContract.View> implements SocialInformContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public SocialInformPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }



    @Override
    public void socialinfo(Map<String, Object> map) {
		addSubscribe(mDataManager.socialinfo(map).doOnSubscribe(subscription -> mView.showProgress()).subscribeOn(AndroidSchedulers.mainThread())
				.subscribe(baseBean -> {
					mView.socialinfoSuc(baseBean);
					mView.hideProgress();
				}, throwable -> mView.hideProgress(R.string.neterror_tryagain)));

    }

    @Override
    public void getSocialInfo(String sessionId) {
		addSubscribe(mDataManager.getSocialInfo(sessionId).doOnSubscribe(subscription -> mView.showProgress()).subscribeOn(AndroidSchedulers.mainThread())
				.subscribe(baseBean -> {
					mView.getSocialInfoSuc(baseBean);
					mView.hideProgress();
				}, throwable -> mView.hideProgress()));
    }
}
