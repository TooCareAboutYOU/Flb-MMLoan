package com.mmkjflb.mmloan.viewmodel.refundeposit;

import android.content.Intent;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bigkoo.pickerview.OptionsPickerView;
import com.mmkj.baselibrary.RouteConstant;
import com.mmkj.baselibrary.annotation.ActivityFragmentInject;
import com.mmkj.baselibrary.model.entitry.BaseBean;
import com.mmkj.baselibrary.model.http.DataResult;
import com.mmkj.baselibrary.util.ConvertUtils;
import com.mmkj.baselibrary.util.PreferenceUtils;
import com.mmkj.baselibrary.util.RxViewUtil;
import com.mmkj.baselibrary.util.StringUtils;
import com.mmkjflb.mmloan.R;
import com.mmkjflb.mmloan.base.BaseActivity;
import com.mmkjflb.mmloan.databinding.ActivityReturnDepositBinding;
import com.mmkjflb.mmloan.model.entity.RefundDepositEntity;
import com.mmkjflb.mmloan.model.entity.RefundInfoEntity;
import com.mmkjflb.mmloan.utils.UiUtils;
import com.mmkjflb.mmloan.viewmodel.refundresult.RefundResultActivity;
import com.syhmmfqphl.xyxlibrary.utils.ExtraKeys;

import java.util.List;
import java.util.Map;

/**
 * 退保证金
 */
@Route(path = RouteConstant.REFUND_DEPOSIT)

@ActivityFragmentInject(contentViewId = R.layout.activity_return_deposit, toolbarTitle = R.string.deposit_refund,
        backDrawable = R.drawable.back_white, toolbarTitleColor = R.color.white, toolbarBgColor = R.color.transparent, loadingTargetView = R.id.linear_loading)
public class RefundDepositActivity extends BaseActivity<RefundDepositPresenter, ActivityReturnDepositBinding> implements
        RefundDepositContract.View {
    private List<String> withdrawChannelData = null;
    private OptionsPickerView withdrawChannelView;
    private Map<String, Object> withdrawMap = null;
    private String withdrawChannelCode = "";
    private String mSessionid;

    @Override
    protected void initViews() {
        getActivityComponent().inject(this);
        mSessionid = PreferenceUtils.getUserSessionid();
        mPresenter.getRefundInfo(mSessionid);
        initClicks();
    }

    private void initClicks() {
        RxViewUtil.clicks(mDataBinding.linearWithdrawChannel).subscribe(o -> {
            if (null == withdrawChannelData) {
                mPresenter.getWithdrawChannel();
            } else {
                withdrawChannelView.show(mDataBinding.linearWithdrawChannel);
            }
        });
        RxViewUtil.clicks(mDataBinding.btnLoanRightnow).subscribe(o -> mPresenter.refundDeposit(mSessionid, ConvertUtils.getKey(withdrawMap, withdrawChannelCode)));
    }

    @Override
    public void getWithdrawChannelSuc(BaseBean<Map<String, Object>> baseBean) {
        if (DataResult.isSuccessUnToast(this, baseBean)) {
            withdrawMap = baseBean.getData();
            withdrawChannelData = ConvertUtils.map2List(withdrawMap);
            initPickerView();
        }
    }

    @Override
    public void getRefundInfoSuc(BaseBean<RefundInfoEntity> baseBean) {
        if (DataResult.isSuccessUnToast(this, baseBean)) {
            if (baseBean.getData() != null) {
                mDataBinding.tvLoanAmount.setText(StringUtils.cutOutLastThree(baseBean.getData().getRealRefundAmount() + ""));//实际收到的金额
                mDataBinding.tvReceivedAmount.setText(StringUtils.cutOutLastThree(baseBean.getData().getAmount() + ""));//保证金金额
                mDataBinding.tvPoundageAmount.setText(StringUtils.cutOutLastThree(baseBean.getData().getRepayFee() + ""));//缴纳服务费
                mDataBinding.tvRetrunPoundage.setText(StringUtils.cutOutLastThree(baseBean.getData().getPayFee() + ""));//退款手续费
            }
        }
    }

    @Override
    public void refundDeposit(BaseBean<RefundDepositEntity> baseBean) {
        if (DataResult.isSuccessUnToast(this, baseBean)) {
//            Intent intent = new Intent(RefundDepositActivity.this, RefundResultActivity.class);
//            intent.putExtra(ExtraKeys.Key_Msg1, baseBean.getData().getStatus());
//            intent.putExtra(ExtraKeys.Key_Msg2, baseBean.getData().getMsg());
//            startActivity(intent);
//            finish();
            ARouter.getInstance()
                    .build(RouteConstant.RETURN_RESULT)
                    .withInt("typeCode",baseBean.getData().getStatus())
                    .withString("msg",baseBean.getData().getMsg())
                    .navigation();
            finish();
        }
    }

    /**
     * 提款渠道弹窗设置
     */
    private void initPickerView() {
        withdrawChannelView = UiUtils.initOptionPicker(this, (options1, options2, options3, v) -> {
            withdrawChannelCode = withdrawChannelData.get(options1);
            mDataBinding.tvWithdrawChannel.setText(withdrawChannelCode);
            mDataBinding.btnLoanRightnow.setEnabled(true);
        }, R.string.pls_chose_withdraw_channel);
        withdrawChannelView.setPicker(withdrawChannelData);
        withdrawChannelView.show(mDataBinding.linearWithdrawChannel);
    }
}
