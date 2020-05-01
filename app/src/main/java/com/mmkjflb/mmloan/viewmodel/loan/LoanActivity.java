package com.mmkjflb.mmloan.viewmodel.loan;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jaeger.library.StatusBarUtil;
import com.mmkj.baselibrary.RouteConstant;
import com.mmkj.baselibrary.annotation.ActivityFragmentInject;
import com.mmkj.baselibrary.model.entitry.BaseBean;
import com.mmkj.baselibrary.model.entitry.GCashDetailBean;
import com.mmkj.baselibrary.model.http.DataResult;
import com.mmkj.baselibrary.util.Constants;
import com.mmkj.baselibrary.util.DialogUtils;
import com.mmkj.baselibrary.util.PreferenceUtils;
import com.mmkj.baselibrary.util.RxViewUtil;
import com.mmkj.baselibrary.util.StringUtils;
import com.mmkjflb.mmloan.R;
import com.mmkjflb.mmloan.base.BaseActivity;
import com.mmkjflb.mmloan.databinding.ActivityLoanBinding;
import com.mmkjflb.mmloan.model.entity.LoanEntity;
import com.mmkjflb.mmloan.model.entity.PaymentShopListEntity;
import com.mmkjflb.mmloan.model.entity.SurelyEntity;
import com.mmkjflb.mmloan.utils.GridLayoutMarginDecoration;
import com.mmkjflb.mmloan.viewmodel.loanresult.LoanResultActivity;
import com.mmkjflb.mmloan.viewmodel.modifygcash.ModifyGCashActivity;
import com.syhmmfqphl.xyxlibrary.utils.ExtraKeys;
import com.syhmmfqphl.xyxlibrary.utils.JumpManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Administrator on 2017/10/16.
 */
@Route(path = RouteConstant.LOAN)
@ActivityFragmentInject(contentViewId = R.layout.activity_loan, toolbarTitle = R.string.loan, backDrawable = R.drawable.back_white,
        toolbarTitleColor = R.color.white, toolbarBgColor = R.color.transparent)
//, loadingTargetView = R.id.linear_loading
public class LoanActivity extends BaseActivity<LoanPresenter, ActivityLoanBinding> implements LoanContract.View {
    private LoanEntity loanEntity;
    private int checkedNum = -1, userStatus = -1;
    private int channel;
    private String mPaymentPlatform = null;
    private String gCashAccount = null;
    private PaymentShopAdapter mShopAdapter;

    @Override
    protected void initViews() {
        getActivityComponent().inject(this);
        initClicks();
        mPresenter.getPaymentShopListAll();
        mPresenter.loanInfo(PreferenceUtils.getUserSessionid());
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.getGCashDetail(PreferenceUtils.getUserSessionid());
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
        RxViewUtil.clicks(mDataBinding.btnLoanRightnow).subscribe(o -> {

            if (checkedNum == 0) {
                if (!TextUtils.isEmpty(gCashAccount)) {//如果绑定过gcash
                    showDialog();
                } else {//如果没绑定过gcash
                    //直接跳转到绑定gcash页面
//                    JumpManager.jumpTo(LoanActivity.this, ModifyGCashActivity.class);
                    ARouter.getInstance().build(RouteConstant.MODIFY_G_CASH).navigation();
                }
            } else if (checkedNum == 1) {
                Map<String, Object> map = new HashMap<>();
                map.put("sessionId", PreferenceUtils.getUserSessionid());
                map.put("productId", loanEntity.getProductId());//产品id
                map.put("withdrawChannel", channel);//取款渠道 支付渠道改了，固定默认传1
                map.put("paymentPlatform", mPaymentPlatform);
                mPresenter.surely(map);
            }
        });
    }

    /**
     * 身份证类型的弹窗
     */
    private void showDialog() {
        View view = View.inflate(this, R.layout.dialog_commom, null);
        TextView tvAccount = view.findViewById(R.id.tv_account);
        tvAccount.setText("Account: " + gCashAccount);
        Dialog dialog = DialogUtils.initDialog(this, view);
        view.findViewById(R.id.tv_left).setOnClickListener(o -> {
            //跳转到修改页面
//            JumpManager.jumpTo(LoanActivity.this, ModifyGCashActivity.class);
            ARouter.getInstance().build(RouteConstant.MODIFY_G_CASH).navigation();
            if (dialog != null) {
                dialog.dismiss();
            }
        });
        view.findViewById(R.id.tv_right).setOnClickListener(v -> {
            Map<String, Object> map = new HashMap<>();
            map.put("sessionId", PreferenceUtils.getUserSessionid());
            map.put("productId", loanEntity.getProductId());//产品id
            map.put("withdrawChannel", channel);//取款渠道 支付渠道改了，固定默认传1
            map.put("paymentPlatform", mPaymentPlatform);
            mPresenter.surely(map);
            if (dialog != null) {
                dialog.dismiss();
            }
        });
    }

    /**
     * 借款页面数据回调
     */
    @Override
    public void loanInfoSuc(BaseBean<List<LoanEntity>> baseBean) {
        if (DataResult.isSuccessUnToast(LoanActivity.this, baseBean)) {
            loanEntity = baseBean.getData().get(0);
            fillBackLoan();
        }
    }

    @Override
    public void getGCashDetailSuc(BaseBean<GCashDetailBean> baseBean) {
        if (DataResult.isSuccessUnToast(this, baseBean)) {
            GCashDetailBean bean = baseBean.getData();
            if (!TextUtils.isEmpty(bean.getGCashAccount())) {
                gCashAccount = bean.getGCashAccount();
            }
        }
    }

    List<PaymentShopListEntity> list = null;

    private void initShopChannel() {
        mShopAdapter = new PaymentShopAdapter(this, list);
        mDataBinding.rcChannels.setLayoutManager(new GridLayoutManager(this, 2));
        mDataBinding.rcChannels.addItemDecoration(new GridLayoutMarginDecoration(this, 2, 4));
        mDataBinding.rcChannels.setAdapter(mShopAdapter);
        mShopAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mShopAdapter.singleChoose(position);
                if (list.get(position).getWithdrawalsShop() == 6) {
                    checkedNum = 0;
                } else {
                    checkedNum = 1;
                }
                if (!TextUtils.isEmpty(list.get(position).getPaymentPlatform())) {
                    mPaymentPlatform = list.get(position).getPaymentPlatform();
                }
                channel = list.get(position).getWithdrawalsShop();
                mDataBinding.btnLoanRightnow.setEnabled(true);
            }
        });
    }


    @Override
    public void getPaymentShopListAllSuc(BaseBean<List<PaymentShopListEntity>> baseBean) {
        if (DataResult.isSuccessUnToast(this, baseBean) && baseBean.getData() != null && baseBean.getData().size() > 0) {
            list=baseBean.getData();
            initShopChannel();
        }
    }

    /**
     * 超时回调
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == Constants.CODE_LOGINTIMEOUT) {
            mPresenter.loanInfo(PreferenceUtils.getUserSessionid());
        }
    }

    /**
     * 借款回调
     */
    @Override
    public void surelySuc(BaseBean<SurelyEntity> baseBean) {
        if (DataResult.isSuccessUnToast(LoanActivity.this, baseBean)) {
            if ("0".equals(baseBean.getData().getUserStatus())) {
                if (baseBean.getData().getStatus() == 1) {
                    if (checkedNum == 0) {//gcash
                        //跳转到gcash借款成功页面
//                        Intent intent = new Intent(this, LoanResultActivity.class);
//                        intent.putExtra(ExtraKeys.Key_Msg1, baseBean.getData().getMsg());
//                        intent.putExtra(ExtraKeys.Key_Msg2, true);
//                        startActivity(intent);
                        ARouter.getInstance().build(RouteConstant.LOAN_RESULT).withString("txtDescribe", baseBean.getData().getMsg()).withBoolean("isSucess", true).navigation();
                    } else {
                        ARouter.getInstance().build(RouteConstant.WITHDRAW).withBoolean("isLoanResult", true).navigation();
                    }

                } else {
//                    Intent intent = new Intent(this, LoanResultActivity.class);
//                    intent.putExtra(ExtraKeys.Key_Msg1, baseBean.getData().getMsg());
//                    intent.putExtra(ExtraKeys.Key_Msg2, false);
//                    startActivity(intent);
                    ARouter.getInstance().build(RouteConstant.LOAN_RESULT).withString("txtDescribe", baseBean.getData().getMsg()).withBoolean("isSucess", false).navigation();

                }
            } else if ("1".equals(baseBean.getData().getUserStatus())) {
                ARouter.getInstance().build(RouteConstant.CERTIFICATION_RESULT).navigation();
            }
            finish();
        }
    }

    /**
     * 借款页面展示
     */
    private void fillBackLoan() {
        mDataBinding.tvLoanAmount.setText(StringUtils.cutOutLastThree(StringUtils.doubleZheng(loanEntity.getAmount())));
        mDataBinding.tvLoanDeadline.setText(String.valueOf(loanEntity.getLoanDate()));
        mDataBinding.tvLoanRate.setText(StringUtils.cutOutLastThree(StringUtils.doubleZheng(loanEntity.getFeeRate())));
        mDataBinding.tvRepayFee.setText(StringUtils.cutOutLastThree(StringUtils.doubleZheng(loanEntity.getFeeProcedureRepay())));
        if (loanEntity.getGrossInterest() == 0.00) {
            mDataBinding.tvLoanFeeMsg.setVisibility(View.GONE);
            mDataBinding.tvLoanFee.setVisibility(View.GONE);
        }
        mDataBinding.tvLoanFee.setText(StringUtils.cutOutLastThree(StringUtils.doubleZheng(loanEntity.getGrossInterest())));
        mDataBinding.tvWithdrawFee.setText(StringUtils.cutOutLastThree(StringUtils.doubleZheng(loanEntity.getFeeProcedurePay())));
        mDataBinding.tvActualAccount.setText(StringUtils.cutOutLastThree(StringUtils.doubleZheng(loanEntity.getRealAmount())));
        mDataBinding.tvRepayDate.setText(loanEntity.getRepaymentTime());

    }

    @Override
    protected void onDestroy() {
        if (list != null) {
            list.clear();
            list = null;
        }
        if (mShopAdapter != null) {
            mShopAdapter = null;
        }
        if (loanEntity != null) {
            loanEntity = null;
        }
        super.onDestroy();
    }
}
