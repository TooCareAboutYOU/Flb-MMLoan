package com.mmkjflb.mmloan.viewmodel.payadepositresult;

import android.annotation.SuppressLint;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.jakewharton.rxbinding2.view.RxView;
import com.mmkj.baselibrary.RouteConstant;
import com.mmkj.baselibrary.annotation.HeadToolbar;
import com.mmkj.baselibrary.annotation.HeaderBuilder;
import com.mmkj.baselibrary.base.DataBindingActivity;
import com.mmkjflb.mmloan.R;
import com.mmkjflb.mmloan.databinding.ActivityPayAdepositResultBinding;

@Route(path = RouteConstant.PAY_A_DEPOSIT_RESULT)
public class PayADepositResultActivity extends DataBindingActivity<ActivityPayAdepositResultBinding> {

    @Autowired(name = "isVa")
    boolean isVa;


    @Override
    protected HeadToolbar getHeadToolbar() {
        return new HeaderBuilder().mContentViewId(R.layout.activity_pay_adeposit_result).build();
    }

    @SuppressLint("CheckResult")
    @Override
    protected void initViews() {
        ARouter.getInstance().inject(this);
        mDataBinding.setIsVa(true);

        RxView.clicks(mDataBinding.ivBack).subscribe(o -> finish());
        RxView.clicks(mDataBinding.tvFinish).subscribe(o -> finish());
        RxView.clicks(mDataBinding.tvViewVacode).subscribe(o -> {
            ARouter.getInstance().build(RouteConstant.PAYMENT_CODE).withBoolean("isVa",isVa).navigation();
            finish();
        });
    }


}
