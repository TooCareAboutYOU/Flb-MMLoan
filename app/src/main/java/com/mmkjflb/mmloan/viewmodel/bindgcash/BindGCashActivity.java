package com.mmkjflb.mmloan.viewmodel.bindgcash;

import android.Manifest;
import android.annotation.SuppressLint;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.jaeger.library.StatusBarUtil;
import com.jakewharton.rxbinding2.widget.RxCheckedTextView;
import com.jakewharton.rxbinding2.widget.RxCompoundButton;
import com.mmkj.baselibrary.RouteConstant;
import com.mmkj.baselibrary.annotation.ActivityFragmentInject;
import com.mmkj.baselibrary.model.entitry.BaseBean;
import com.mmkj.baselibrary.model.entitry.GCashDetailBean;
import com.mmkj.baselibrary.util.DeviceUtils;
import com.mmkj.baselibrary.util.PreferenceUtils;
import com.mmkj.baselibrary.util.RxViewUtil;
import com.mmkj.baselibrary.util.StringUtils;
import com.mmkjflb.mmloan.R;
import com.mmkjflb.mmloan.app.Constants;
import com.mmkjflb.mmloan.base.BaseActivity;
import com.mmkjflb.mmloan.databinding.ActivityBindGcashBinding;
import com.mmkjflb.mmloan.model.entity.AmountTypeEntity;
import com.mmkjflb.mmloan.model.entity.ProductInfoBean;
import com.mmkjflb.mmloan.model.http.DataResult;
import com.mmkjflb.mmloan.utils.UiUtils;
import com.mmkjflb.mmloan.viewmodel.certificateresult.CertificationResultActivity;
import com.mmkjflb.mmloan.viewmodel.enploymentinformation.EnploymentinformationActivity;
import com.syhmmfqphl.xyxlibrary.utils.ExtraKeys;
import com.syhmmfqphl.xyxlibrary.utils.JumpManager;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import androidx.recyclerview.widget.LinearLayoutManager;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

@Route(path = RouteConstant.BIND_G_CASH)
@ActivityFragmentInject(contentViewId = R.layout.activity_bind_gcash, toolbarTitle = R.string.bind_gcash, toolbarBgColor = R.color.transparent, toolbarTitleColor = R.color.white, backDrawable = R.drawable.back_white)
public class BindGCashActivity extends BaseActivity<BindGCashPresenter, ActivityBindGcashBinding> implements BindGCashContract.View {

    private String gCashAccount;
    private RxPermissions rxPermissions;
    private String productType = "";//后台应该要优化，现在是直接数据库查找传给我们
    private BindGcashAdapter bindGcashAdapter;
    private List<ProductInfoBean> productInfoBeanList = null;
    private String increaseShow = "Fill up employer info to increase\ncredit limit to";
    private boolean hideBack = false;//小于81

    @Autowired
    public int employStatus = 0;


    @Override
    protected void initViews() {
        getActivityComponent().inject(this);
        rxPermissions = new RxPermissions(this);
//        employStatus = getIntent().getIntExtra(ExtraKeys.Key_Msg1, 0);
        mPresenter.getGCashDetail(PreferenceUtils.getUserSessionid());
        mPresenter.getAmountType(PreferenceUtils.getUserSessionid());
        initClicks();
    }

    /**
     * 设置导航栏
     */
    @Override
    protected void setStatusBar(int statusBarColor) {
        StatusBarUtil.setTranslucentForImageView(this, 0, mDataBinding.linearTop);
    }

    @SuppressLint("CheckResult")
    private void initClicks() {
        RxViewUtil.clicks(mDataBinding.tvGetCode).subscribe(o -> {
            if (isPhone(mDataBinding.etGCashAccount.getText().toString())) {//如果是手机号
                mPresenter.sendSms(mDataBinding.etGCashAccount.getText().toString(), "5");
            }
        });
        RxViewUtil.clicks(mDataBinding.tvLinkAccount).subscribe(o -> {
            if (isPhone(mDataBinding.etGCashAccount.getText().toString()) && !TextUtils.isEmpty(mDataBinding.etSmsCode.getText())) {
                mPresenter.updateGCash(PreferenceUtils.getUserSessionid(), mDataBinding.etGCashAccount.getText().toString(),
                        mDataBinding.etSmsCode.getText().toString());
            }
        });

        RxViewUtil.clicks(mDataBinding.tvNext).subscribe(o -> {

            if (TextUtils.isEmpty(productType)) {
                toast(R.string.pls_choose_product);
            } else {
                rxPermissions.request(Manifest.permission.READ_PHONE_STATE).subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        //更新设备信息
                        if (aBoolean) {
                            Map<String, String> params = new HashMap<>();
                            params.put("sessionId", PreferenceUtils.getUserSessionid());
                            params.put("deviceType", Constants.DEVICE_TYPE);
                            params.put("appVersion", DeviceUtils.getVersionName(BindGCashActivity.this));
                            params.put("deviceModel", DeviceUtils.getModel());
                            params.put("deviceOs", DeviceUtils.getVersionOs());
                            params.put("deviceImei", DeviceUtils.getDeviceImei(BindGCashActivity.this));
                            params.put("deviceToken", "");
                            params.put("accessPosition", "");
                            params.put("deviceSerialNumber", DeviceUtils.getSerialNumber());
                            params.put("deviceAndroidId", DeviceUtils.getAndroidId(BindGCashActivity.this));
                            params.put("deviceMacAddress", DeviceUtils.getMacFromFile(BindGCashActivity.this));
                            mPresenter.deviceInfo(params);
                        } else {
                            UiUtils.openSetting(BindGCashActivity.this, R.string.open_phonestate_permission);
                        }
                    }
                });
            }
        });

        RxViewUtil.clicks(mDataBinding.tvModify).subscribe(o -> {
            mDataBinding.llBindGcash.setVisibility(View.VISIBLE);
            mDataBinding.llShowGcash.setVisibility(View.GONE);
        });
        RxViewUtil.clicks(mDataBinding.tvFillUp).subscribe(o -> {
//            JumpManager.jumpToKey1Close(this, EnploymentinformationActivity.class, employStatus);
            ARouter.getInstance().build(RouteConstant.ENPLOYMENT_INFORMATION).withInt("employStatus",employStatus).navigation();finish();
        });
    }

    @Override
    public void getAmountTypeSuc(BaseBean<AmountTypeEntity> baseBean) {
        if (DataResult.isSuccessUnToast(this, baseBean)) {
            if (baseBean.getData().getIsFlag() == 1) {//0：大于等于81，1：小于81
                hideBack = true;
                employStatus = 0;
            }
            mPresenter.getProductInfo(PreferenceUtils.getUserSessionid(), employStatus + 1);
        }
    }

    private Disposable timerDisposable = null;

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
                            timerDisposable = d;
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

    private void disposeTimer() {
        if (timerDisposable != null && !timerDisposable.isDisposed()) {
            mDataBinding.tvGetCode.setEnabled(true);
            mDataBinding.tvGetCode.setText("Get code");
            timerDisposable.dispose();
            timerDisposable = null;
        }
    }

    @Override
    public void getGCashDetailSuc(BaseBean<GCashDetailBean> baseBean) {
        if (DataResult.isSuccessUnToast(this, baseBean)) {
            GCashDetailBean bean = baseBean.getData();
            if (TextUtils.isEmpty(bean.getGCashAccount())) {
                mDataBinding.llBindGcash.setVisibility(View.VISIBLE);
                mDataBinding.llShowGcash.setVisibility(View.GONE);
            } else {
                mDataBinding.llBindGcash.setVisibility(View.GONE);
                mDataBinding.llShowGcash.setVisibility(View.VISIBLE);
                gCashAccount = bean.getGCashAccount();
                mDataBinding.tvGCashAccount.setText(bean.getGCashAccount());
            }

        }
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
    public void updateGCashSuc(BaseBean baseBean) {
        if (DataResult.isSuccessUnToast(this, baseBean)) {

            mDataBinding.llShowGcash.setVisibility(View.VISIBLE);
            mDataBinding.llBindGcash.setVisibility(View.GONE);
            gCashAccount = mDataBinding.etGCashAccount.getText().toString();
            mDataBinding.tvGCashAccount.setText(gCashAccount);
            mDataBinding.etGCashAccount.setText("");
            mDataBinding.etSmsCode.setText("");

            disposeTimer();
        }
    }

    @Override
    public void deviceInfoSuc(BaseBean baseBean) {
        if (DataResult.isSuccessUnToast(this, baseBean)) {
            mPresenter.updateProductCode(PreferenceUtils.getUserSessionid(), "fifteencode".equals(productType) ? 1 : 2);
        }
    }

    @Override
    public void getProductInfoSuc(BaseBean<List<ProductInfoBean>> baseBean) {
        if (DataResult.isSuccessUnToast(this, baseBean)) {
            productInfoBeanList = baseBean.getData();
            bindGcashAdapter = new BindGcashAdapter(productInfoBeanList, employStatus);
            mDataBinding.rvBindGcash.setLayoutManager(new LinearLayoutManager(this));
            mDataBinding.rvBindGcash.setAdapter(bindGcashAdapter);
            if (hideBack) {
                mDataBinding.llIncrease.setVisibility(View.GONE);
                productType = productInfoBeanList.get(0).getCode();
            } else {
                if (employStatus == 0) {
                    productType = productInfoBeanList.get(0).getCode();
                    mDataBinding.llIncrease.setVisibility(View.VISIBLE);
                    SpannableString styledText = new SpannableString(increaseShow + StringUtils.cutAmount(2500));
                    styledText.setSpan(new TextAppearanceSpan(this, R.style.TvIncreaseShowStyle), 0, increaseShow.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    styledText.setSpan(new TextAppearanceSpan(this, R.style.TvIncreaseAmountStyle), increaseShow.length(), styledText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    mDataBinding.tvIncreaseShow.setText(styledText, TextView.BufferType.SPANNABLE);
                } else if (employStatus == 1) {
                    mDataBinding.llIncrease.setVisibility(View.GONE);
                    bindGcashAdapter.setOnItemChildClickListener((adapter1, view, position) -> {
                        if (view.getId() == R.id.ivProductChose) {
                            productType = productInfoBeanList.get(position).getCode();
                            bindGcashAdapter.setChosePosition(position);
                            bindGcashAdapter.notifyDataSetChanged();
                        }
                    });
                }
            }
        }
    }

    @Override
    public void updateProductCodeSuc(BaseBean baseBean) {
        if (DataResult.isSuccessUnToast(this, baseBean)) {
//            JumpManager.jumpToClose(this, CertificationResultActivity.class);
            ARouter.getInstance().build(RouteConstant.CERTIFICATION_RESULT).navigation();finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposeTimer();
    }

}

