package com.mmkjflb.mmloan.viewmodel.extracredit;

import com.mmkj.baselibrary.base.RxPresenter;
import com.mmkjflb.mmloan.R;
import com.mmkjflb.mmloan.model.DataManager;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class ExtraCreditPresenter extends RxPresenter<ExtraCreditContract.View> implements ExtraCreditContract.Presenter{

    private DataManager mDataManager;

    @Inject
    public ExtraCreditPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void getExtraCredit(String sessionId) {
        addSubscribe(mDataManager.getExtraCredit(sessionId)
                .doOnSubscribe(subscription -> mView.showProgress())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(bean -> {
                    mView.getExtraCreditSuc(bean);
                    mView.hideProgress();
                }, throwable -> mView.hideProgress(R.string.neterror_tryagain)));
    }

    @Override
    public void submitReportToken(String sessionId, String reportTaskToken, String website) {
        addSubscribe(
                mDataManager.submitReportToken(sessionId, reportTaskToken ,website)
                        .doOnSubscribe(subscription -> mView.showProgress())
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .subscribe(bean -> {
                            mView.submitReportTokenSuc(bean);
                            mView.hideProgress();
                        }, throwable -> mView.hideProgress(R.string.neterror_tryagain))
        );
    }
}
