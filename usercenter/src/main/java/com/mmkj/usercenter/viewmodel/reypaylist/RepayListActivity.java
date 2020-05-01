package com.mmkj.usercenter.viewmodel.reypaylist;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

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
import com.mmkj.usercenter.viewmodel.repaymentchannel.RepaymentChannelActivity;
import com.syhmmfqphl.xyxlibrary.utils.ExtraKeys;
import com.mmkj.usercenter.R;
import com.mmkj.usercenter.databinding.ActivityRepayListBinding;
import com.mmkj.usercenter.model.entity.PaymentData;
import com.mmkj.usercenter.viewmodel.BaseUserSettingActivity;
import com.syhmmfqphl.xyxlibrary.utils.JumpManager;

/**
 * @author wendjia
 * @date 2017/10/16
 */
@Route(path = RouteConstant.REPAY_LIST)
public class RepayListActivity extends BaseUserSettingActivity<RepayListPresenter, ActivityRepayListBinding> implements RepayListContract.View {

    private int payClient;

    @Override
    protected HeadToolbar getHeadToolbar() {
        return new HeaderBuilder().mContentViewId(R.layout.activity_repay_list)
                .toolbarTitle(R.string.myrepay).build();
    }

    @Override
    protected void initViews() {
        getActivityComponent().inject(this);
        initClicks();
        initRv();
    }

    @SuppressLint("CheckResult")
    private void initClicks() {
        RxViewUtil.clicks(mDataBinding.repayType).subscribe(o -> ARouter.getInstance().build(RouteConstant.REPAYMENT_CHANNEL).withInt("payClient", payClient).navigation()); //JumpManager.jumpToKey1(this, RepaymentChannelActivity.class, vaCode));
        RxViewUtil.clicks(mDataBinding.ivBack).subscribe(o -> finish());
        RxViewUtil.clicks(mDataBinding.ivHelp).subscribe(o -> ARouter.getInstance().build(RouteConstant.WEBVIEW).withString("loadUrl", Constants.FAQ).navigation());
        RxViewUtil.clicks(mDataBinding.clRepayLoadingView).subscribe(o -> mPresenter.getPaymentCode(PreferenceUtils.getUserSessionid()));
    }

    private void initRv() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        mDataBinding.rvRepayList.setLayoutManager(layoutManager);
    }

    @Override
    public void getNetData() {
        mPresenter.getPaymentCode(PreferenceUtils.getUserSessionid());
    }

    @Override
    public void getPaymentCodeSuc(BaseBean<PaymentData> baseBean) {
        if (DataResult.isSuccessUnToast(this, baseBean)) {
            mPresenter.initPaymentData(baseBean.getData());
            mDataBinding.setPaymentData(mPresenter.getCurrent());

//            vaCode = baseBean.getData().getShouldBills().getPaymentCode();
            payClient = baseBean.getData().getShouldBills().getPayClient();


            mDataBinding.setNoRepay(mPresenter.isNoRepay());

            mDataBinding.setIsShowCode(mPresenter.isShwoRepayCode());

            mDataBinding.acTvEmpty.setVisibility(mPresenter.getPaymentList().size() > 0 ? View.GONE : View.VISIBLE);
            mDataBinding.rvRepayList.setVisibility(mPresenter.getPaymentList().size() > 0 ? View.VISIBLE : View.GONE);

            RepayListAdapter adapter = new RepayListAdapter(mPresenter.getPaymentList());
            mDataBinding.rvRepayList.setAdapter(adapter);

            adapter.setOnItemClickListener((adapter1, view, position) -> ARouter.getInstance().build(RouteConstant.REPAY_DETAIL).withLong("billId", adapter.getItem(position).getId()).navigation());
            if (baseBean.getData().getShouldBills() != null) {
                if (!TextUtils.isEmpty(baseBean.getData().getShouldBills().getBiller())) {
                    mDataBinding.acImgPayIcon.setImageResource(baseBean.getData().getShouldBills().getBiller().equals("SKYPAY") ? R.drawable.img_skypay : R.drawable.img_secondpay);
                }
                switch (payClient) {
                    case 1: {
                        mDataBinding.acImgChannelIcon.setImageResource(R.drawable.img_channels_ml_1);
                        break;
                    }
                    case 6: {
                        mDataBinding.acImgChannelIcon.setImageResource(R.drawable.img_channels_gcash_6);
                        break;
                    }
                    case 9: {
                        mDataBinding.acImgChannelIcon.setImageResource(R.drawable.img_channels_rd_9);
                        break;
                    }
                    case 12: {
                        mDataBinding.acImgChannelIcon.setImageResource(R.drawable.img_channels_711_12);
                        break;
                    }
                    default:
                        break;
                }
            }

        }
    }
}
