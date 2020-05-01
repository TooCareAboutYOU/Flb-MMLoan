package com.mmkjflb.mmloan.viewmodel.modifygcash;

import android.annotation.SuppressLint;
import android.text.TextUtils;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.mmkj.baselibrary.RouteConstant;
import com.mmkj.baselibrary.annotation.ActivityFragmentInject;
import com.mmkj.baselibrary.model.entitry.BaseBean;
import com.mmkj.baselibrary.util.PreferenceUtils;
import com.mmkj.baselibrary.util.RxViewUtil;
import com.mmkjflb.mmloan.R;
import com.mmkjflb.mmloan.app.Constants;
import com.mmkjflb.mmloan.base.BaseActivity;
import com.mmkjflb.mmloan.databinding.ActivityModifyGcashBinding;
import com.mmkjflb.mmloan.model.http.DataResult;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2017/10/17.
 */
@Route(path = RouteConstant.MODIFY_G_CASH)
@ActivityFragmentInject(contentViewId = R.layout.activity_modify_gcash, toolbarTitle = R.string.update_gcash)
public class ModifyGCashActivity extends BaseActivity<ModifyGCashPresenter, ActivityModifyGcashBinding> implements ModifyGCashContract.View {

    @Override
    protected void initViews() {
        getActivityComponent().inject(this);
        initClicks();
    }

    @SuppressLint("CheckResult")
    private void initClicks() {
        RxViewUtil.clicks(mDataBinding.tvGetCode).subscribe(o -> {
            if (isPhone(mDataBinding.etGCashAccount.getText().toString())) {
                mPresenter.sendSms(mDataBinding.etGCashAccount.getText().toString(), "5");
            }
        });
        RxViewUtil.clicks(mDataBinding.tvLinkAccount).subscribe(o -> {
            if (isPhone(mDataBinding.etGCashAccount.getText().toString()) && !TextUtils.isEmpty(mDataBinding.etSmsCode.getText())) {
                mPresenter.updateGCash(PreferenceUtils.getUserSessionid(), mDataBinding.etGCashAccount.getText().toString(), mDataBinding.etSmsCode.getText().toString());
            }
        });
    }

    public boolean isPhone(String num) {
        if (!TextUtils.isEmpty(num)) {
            if (num.startsWith("09") && num.length() == 11) {
                return true;
            }
            if (num.startsWith("9") && num.length() == 10) {
                return true;
            }
        }
        toast("Pease enter the correct GCash accout");
        return false;
    }


    @Override
    public void sendSmsSuc(BaseBean baseBean) {
        if (DataResult.isSuccessUnToast(this, baseBean)) {
            Observable.interval(0, 1, TimeUnit.SECONDS)
                    .take(Constants.TIMECOUNT + 1)
                    .map(aLong -> Constants.TIMECOUNT - aLong)
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe(disposable -> {
                        //进入倒计时
                        mDataBinding.tvGetCode.setEnabled(false);
                    })
                    .subscribe(new Observer<Long>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                        }

                        @Override
                        public void onNext(Long aLong) {
                            mDataBinding.tvGetCode.setText(aLong + "s");
                        }

                        @Override
                        public void onError(Throwable e) {
                        }

                        @Override
                        public void onComplete() {
                            mDataBinding.tvGetCode.setEnabled(true);
                            mDataBinding.tvGetCode.setText("Get code");
                        }
                    });
        }
    }

    @Override
    public void updateGCashSuc(BaseBean baseBean) {
        if (DataResult.isSuccessUnToast(this, baseBean)) {
            toast("Binding success");
            finish();
        }
    }
}
