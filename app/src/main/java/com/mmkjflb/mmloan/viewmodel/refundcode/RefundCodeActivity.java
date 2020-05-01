package com.mmkjflb.mmloan.viewmodel.refundcode;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.mmkj.baselibrary.RouteConstant;
import com.mmkj.baselibrary.annotation.ActivityFragmentInject;
import com.mmkj.baselibrary.model.entitry.BaseBean;
import com.mmkj.baselibrary.model.http.DataResult;
import com.mmkj.baselibrary.util.PreferenceUtils;
import com.mmkj.baselibrary.util.RxViewUtil;
import com.mmkj.baselibrary.util.StringUtils;
import com.mmkjflb.mmloan.R;
import com.mmkjflb.mmloan.base.BaseActivity;
import com.mmkjflb.mmloan.databinding.ActivityRefundCodeBinding;
import com.mmkjflb.mmloan.model.entity.RefundCodeEntity;


/**
 * 退还保证金的取款码
 */
@ActivityFragmentInject(contentViewId = R.layout.activity_refund_code, loadingTargetView = R.id.ll_linear_loading)
@Route(path = RouteConstant.REFUND_CODE)
public class RefundCodeActivity extends BaseActivity<RefundCodePresenter, ActivityRefundCodeBinding> implements
        RefundCodeContract.View {

    @Override
    protected void initViews() {
        getActivityComponent().inject(this);
        Glide.with(this).asGif().load(R.drawable.refund_code_loading).into(mDataBinding.ivCodeLoading);
        RxViewUtil.clicks(mDataBinding.ivBack).subscribe(o -> finish());
        String phone = PreferenceUtils.getString(PreferenceUtils.SERVICE_PHONE, "");
        mDataBinding.tvPhone.setText("- Contact Us：" + phone + " -");
        mPresenter.getRefundCode(PreferenceUtils.getUserSessionid());
    }

    @Override
    public void getRefundCodeSuc(BaseBean<RefundCodeEntity> baseBean) {
        if (DataResult.isSuccessUnToast(this, baseBean)) {
            if (baseBean.getData() != null) {
                if (baseBean.getData().getWithdrawalNumberStatus() == 0) {//处理中
                    mDataBinding.ivCodeLoading.setVisibility(View.VISIBLE);
                    mDataBinding.tvRefundCode.setVisibility(View.GONE);
                } else {
                    mDataBinding.ivCodeLoading.setVisibility(View.GONE);
                    mDataBinding.tvRefundCode.setVisibility(View.VISIBLE);
                    mDataBinding.tvRefundCode.setText(baseBean.getData().getWithdrawalNumber());
                }
                mDataBinding.acTvMoney.setText(StringUtils.cutOutLastThree(baseBean.getData().getAmount() + ""));
                mDataBinding.tvName.setText(baseBean.getData().getSender());
                mDataBinding.tvSource.setText(baseBean.getData().getSource());
                mDataBinding.tvChannel.setText(baseBean.getData().getWithdrawChannel());
            }
        }
    }
}
