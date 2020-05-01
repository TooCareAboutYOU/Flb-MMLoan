package com.mmkj.usercenter.viewmodel.repaydetail;


import android.util.Log;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.mmkj.baselibrary.RouteConstant;
import com.mmkj.baselibrary.annotation.HeadToolbar;
import com.mmkj.baselibrary.annotation.HeaderBuilder;
import com.mmkj.baselibrary.model.entitry.BaseBean;
import com.mmkj.baselibrary.model.http.DataResult;
import com.mmkj.baselibrary.util.Constants;
import com.mmkj.baselibrary.util.PreferenceUtils;
import com.mmkj.baselibrary.util.RxViewUtil;
import com.mmkj.usercenter.BuildConfig;
import com.syhmmfqphl.xyxlibrary.utils.ExtraKeys;
import com.mmkj.usercenter.R;
import com.mmkj.usercenter.databinding.ActivityRepayDetailBinding;
import com.mmkj.usercenter.model.entity.PaymentListData;
import com.mmkj.usercenter.viewmodel.BaseUserSettingActivity;

/**
 * @author wendjia
 * @date 2017/10/16
 */
@Route(path = RouteConstant.REPAY_DETAIL)
public class RepayDetailActivity extends BaseUserSettingActivity<RepayDetailPresenter, ActivityRepayDetailBinding> implements RepayDetailContract.View {

    @Autowired
    long billId;


    @Override
    protected HeadToolbar getHeadToolbar() {
        return new HeaderBuilder()
                .mContentViewId(R.layout.activity_repay_detail)
                .toolbarTitle(R.string.repay_detail).build();
    }

    @Override
    protected void initViews() {
        getActivityComponent().inject(this);
        ARouter.getInstance().inject(this);
        initClicks();

    }

    private void initClicks() {
        RxViewUtil.clicks(mDataBinding.ivBack).subscribe(o -> finish());
        RxViewUtil.clicks(mDataBinding.ivHelp).subscribe(o -> ARouter.getInstance()
                .build(RouteConstant.WEBVIEW).withString("loadUrl", Constants.FAQ).navigation());
    }

    @Override
    public void getNetData() {
        mPresenter.getBillDetails(PreferenceUtils.getUserSessionid(), billId + "");
    }


    @Override
    public void getBillDetailsSuc(BaseBean<PaymentListData> baseBean) {
        if (DataResult.isSuccessUnToast(this, baseBean)) {

            mDataBinding.lItemInterest.setVisibility(baseBean.getData().getRepaymentInterest() == 0.00 ? View.GONE : View.VISIBLE);
            mPresenter.initPaymentData(baseBean.getData());
            mDataBinding.setDetailPresenter(mPresenter);
            mDataBinding.executePendingBindings();
        }
    }
}
