package com.mmkjflb.mmloan.viewmodel.fragment.student;

import com.mmkjflb.mmloan.R;
import com.mmkjflb.mmloan.model.DataManager;
import com.mmkj.baselibrary.base.RxPresenter;
import com.orhanobut.logger.Logger;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by
 * Date: On 2018/3/5
 * Author: wangjieyan
 * Email: wangjieyan9308@163.com
 */

public class StudentsPresenter extends RxPresenter<StudentsContract.View> implements StudentsContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public StudentsPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void studentinfo(Map<String, RequestBody> params, MultipartBody.Part studentCard) {
        addSubscribe(mDataManager.studentinfo(params, studentCard).doOnSubscribe(subscription -> {
            Logger.i("===========", mView.toString());
            if (mView != null) {
                mView.showProgress();
            }
        }).subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(baseBean -> {
                    if (mView != null) {
                        mView.studentinfoSuc(baseBean);
                        mView.hideProgress();
                    }
                }, throwable -> {
                    if (mView != null) {
                        mView.hideProgress(R.string.neterror_tryagain);
                    }
                }));
    }

    @Override
    public void getStudentInfo(String sessionId) {
        addSubscribe(mDataManager.getStudentInfo(sessionId).doOnSubscribe(subscription -> {
            if (mView != null) {
                mView.showProgress();
            }
        }).subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(baseBean -> {
                    if (mView != null) {
                        mView.getStudentInfoSuc(baseBean);
                        mView.hideProgress();
                    }
                }, throwable -> {
                    if (mView != null) {
                        mView.hideProgress();
                    }
                }));
    }
}
