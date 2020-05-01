package com.mmkjflb.mmloan.viewmodel.paymentcode;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.jakewharton.rxbinding2.view.RxView;
import com.mmkj.baselibrary.RouteConstant;
import com.mmkj.baselibrary.annotation.HeadToolbar;
import com.mmkj.baselibrary.annotation.HeaderBuilder;
import com.mmkj.baselibrary.model.entitry.BaseBean;
import com.mmkj.baselibrary.util.PreferenceUtils;
import com.mmkj.baselibrary.util.StringUtils;
import com.mmkjflb.mmloan.R;
import com.mmkjflb.mmloan.app.Constants;
import com.mmkjflb.mmloan.base.BaseActivity;
import com.mmkjflb.mmloan.databinding.ActivityPaymentCodeBinding;
import com.mmkjflb.mmloan.model.entity.DepositCodeEntity;
import com.mmkjflb.mmloan.model.http.DataResult;
import com.mmkjflb.mmloan.viewmodel.zhimaauth.WebViewActivity;

@Route(path = RouteConstant.PAYMENT_CODE)
public class PaymentCodeActivity extends BaseActivity<PaymentCodePresenter,ActivityPaymentCodeBinding> implements PaymentCodeContact.View{

    @Autowired(name = "isVa")
    boolean isVa;

    @Override
    protected HeadToolbar getHeadToolbar() {
        return new HeaderBuilder().mContentViewId(R.layout.activity_payment_code).build();
    }

    @SuppressLint("CheckResult")
    @Override
    protected void initViews() {
        getActivityComponent().inject(this);
        ARouter.getInstance().inject(this);

        RxView.clicks(mDataBinding.ivBack).subscribe(o -> finish());
        RxView.clicks(mDataBinding.ivHelp).subscribe(o -> {
//            com.syhmmfqphl.xyxlibrary.utils.JumpManager.jumpToKey1(this, WebViewActivity.class, Constants.FAQ);
            ARouter.getInstance().build(RouteConstant.WEBVIEW).withString("loadUrl", Constants.FAQ).navigation();
        });
        RxView.clicks(mDataBinding.clJumpChangeChannel).subscribe(o -> ARouter.getInstance().build(RouteConstant.REPAY_CHANNEL).navigation());

        Glide.with(this).asGif().load(R.drawable.pay_code_loding).into(mDataBinding.ivCode);

        if (isVa) {
            mPresenter.queryDepositCode(PreferenceUtils.getUserSessionid());
        }else {
            mPresenter.getDepositCode(PreferenceUtils.getUserSessionid());
        }

    }

    @Override
    public void getDepositCodeSuc(BaseBean<DepositCodeEntity> baseBean) {
        if (DataResult.isSuccessUnToast(this,baseBean)) {
            if (!TextUtils.isEmpty(baseBean.getData().getPayCode())) {
                mDataBinding.ivCode.setVisibility(View.GONE);
                mDataBinding.tvCode.setText(baseBean.getData().getPayCode());
            }
            mDataBinding.acTvMoney.setText(StringUtils.cutOutLastThree(StringUtils.doubleZheng(Double.valueOf(baseBean.getData().getAmount()))));
        }
    }

    @Override
    public void queryDepositCodeSuc(BaseBean<DepositCodeEntity> baseBean) {
        if (DataResult.isSuccessUnToast(this,baseBean)) {
            if (!TextUtils.isEmpty(baseBean.getData().getPayCode())) {
                mDataBinding.ivCode.setVisibility(View.GONE);
                mDataBinding.tvCode.setText(baseBean.getData().getPayCode());
            }
            mDataBinding.acTvMoney.setText(StringUtils.cutOutLastThree(StringUtils.doubleZheng(Double.valueOf(baseBean.getData().getAmount()))));
        }
    }
}
