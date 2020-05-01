package com.mmkj.usercenter.viewmodel;

import android.annotation.SuppressLint;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.mmkj.baselibrary.RouteConstant;
import com.mmkj.baselibrary.annotation.HeadToolbar;
import com.mmkj.baselibrary.annotation.HeaderBuilder;
import com.mmkj.baselibrary.base.DataBindingActivity;
import com.mmkj.baselibrary.util.RxViewUtil;
import com.mmkj.usercenter.R;
import com.mmkj.usercenter.databinding.ActivityRepayresultBinding;

@Route(path = RouteConstant.REPAY_RESULT)
public class RepayResultActivity extends DataBindingActivity<ActivityRepayresultBinding> {
    @Autowired
    int billId;
    @Autowired
    boolean repayOk;

    @Override
    protected HeadToolbar getHeadToolbar() {
        return new HeaderBuilder().mContentViewId(R.layout.activity_repayresult).build();
    }

    @Override
    protected void initViews() {
//        ARouter.getInstance().inject(this);
        if (repayOk) {
            mDataBinding.ivResult.setImageResource(R.drawable.success);
            mDataBinding.tvResultShow.setText(R.string.accept_success);
            mDataBinding.ivBack.setVisibility(View.GONE);
            mDataBinding.tvFinish.setVisibility(View.VISIBLE);

        } else {//其余为成功(处理中)
            mDataBinding.ivResult.setImageResource(R.drawable.loan_fail);
            mDataBinding.tvResultShow.setText(R.string.accept_fail);
            mDataBinding.ivBack.setVisibility(View.VISIBLE);
            mDataBinding.tvFinish.setVisibility(View.GONE);
        }
        initClicks();
    }

    @SuppressLint("CheckResult")
    private void initClicks() {
        RxViewUtil.clicks(mDataBinding.ivBack).subscribe(o -> finish());
        RxViewUtil.clicks(mDataBinding.tvFinish).subscribe(o -> finish());
		RxViewUtil.clicks(mDataBinding.tvViewVacode).subscribe(o -> {
			ARouter.getInstance().build(RouteConstant.REPAY_LIST).navigation();
			finish();
		});
    }
}
