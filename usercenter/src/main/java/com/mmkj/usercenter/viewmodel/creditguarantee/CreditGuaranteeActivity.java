package com.mmkj.usercenter.viewmodel.creditguarantee;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Paint;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.mmkj.baselibrary.RouteConstant;
import com.mmkj.baselibrary.annotation.HeadToolbar;
import com.mmkj.baselibrary.annotation.HeaderBuilder;
import com.mmkj.baselibrary.model.entitry.BaseBean;
import com.mmkj.baselibrary.model.http.DataResult;
import com.mmkj.baselibrary.util.PreferenceUtils;
import com.mmkj.baselibrary.util.RxViewUtil;
import com.mmkj.baselibrary.util.StringUtils;
import com.mmkj.usercenter.R;
import com.mmkj.usercenter.databinding.ActivityCreditGuarantee2Binding;
import com.mmkj.usercenter.databinding.DialogCashDepositInfoManagerBinding;
import com.mmkj.usercenter.model.entity.CashDepositInfoEntity;
import com.mmkj.usercenter.model.entity.CreditManagerEntity;
import com.mmkj.usercenter.viewmodel.BaseUserSettingActivity;
import java.util.Objects;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import io.reactivex.functions.Consumer;

@Route(path = RouteConstant.CREDIT_GUARANTEE)
public class CreditGuaranteeActivity extends BaseUserSettingActivity<GreditGuaranteePresenter, ActivityCreditGuarantee2Binding> implements GreditGuaranteeContract.View {

    private Dialog mNoticeDialog;
    private Dialog mRetrunDialog;
    private Dialog mHintDialog;
    CreditManagerEntity data;
    private boolean canPromote;//是否有提额资格    isPromote=true,没逾期没需要还款的即可提额

    // boolean isVa;  //是否生成Va码
    // String cashDeposit; //保证金额度
    // String feeProcedurePay;//保证金手续费
    // String feeProcedureRepay; //退换保证金手续费
    // String repayAmount;  // 实际退还金额

    @SuppressLint("ResourceType")
    @Override
    protected HeadToolbar getHeadToolbar() {
        return new HeaderBuilder().mContentViewId(R.layout.activity_credit_guarantee2)
                .toolbarTitle(R.string.credit_guarantee)
                .toolbarTitleColor(R.color.white)
                .backDrawable(R.drawable.back_white)
                .toolbarBgColor(R.color.color_global)
                .build();
    }

    @Override
    protected void initViews() {
        getActivityComponent().inject(this);
        mDataBinding.tvReturnTwo.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        initClick();
    }

    @SuppressLint("CheckResult")
    private void initClick() {
        //退保证金入口（新用户不需要交保证金即可提额，所以后面该功能可以关闭）
        RxViewUtil.clicks(mDataBinding.tvReturnOne).subscribe(o -> {
            if (data != null) {
                if (data.isHasWithdrawalCode()) {//是否有生成取款码
                    ARouter.getInstance().build(RouteConstant.REFUND_CODE).navigation();
                } else {//没有生成取款码
                    if (data.getAuditStatus() == 2) {//审核成功
                        if (data.isIsDebt()) {//有欠款
                            showCreditHintDialog(getResources().getString(R.string.credit_hint_msg));//有欠款不可退保证金弹窗
                        } else {//没有欠款直接走退款流程
                            showReturnDialog();
                        }
                    } else {
                        if (data.isIsRefund()) {
                            showReturnDialog();
                        } else {
                            showCreditHintDialog(getResources().getString(R.string.credit_hint_msg2));//审核中不可退保证金弹窗
                        }
                    }
                }
            }
        });
        //退保证金规则
        RxViewUtil.clicks(mDataBinding.tvReturnTwo).subscribe(o -> showNoticeDialog());

//        RxViewUtil.clicks(mDataBinding.tvCreditTv2).subscribe(o -> {
//            if (data != null) {
//                if (data.isIsDebt()) {//是否有欠款
//                    showCreditHintDialog(getResources().getString(R.string.credit_hint_msg));//显示弹窗
//                } else {//无欠款
//                    if (data.isIsPromote() && !data.isIsPaid()) {//有提额资格并且没有缴纳过保证金
//                        ARouter.getInstance().build(RouteConstant.PAY_A_DEPOSIT)
//                                .withBoolean("isVa", data.isIsVa())
//                                .withString("cashDeposit", data.getCashDeposit())
//                                .withString("feeProcedurePay", data.getFeeProcedurePay())
//                                .withString("feeProcedureRepay", data.getFeeProcedureRepay())
//                                .withString("repayAmount", data.getRepayAmount())
//                                .withString("payments", data.getPayments())
//                                .navigation();
//                    }
//                }
//            }
//        });
        //确认提额
        RxViewUtil.clicks(mDataBinding.tvCreditTv1).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) {
                if (data != null) {
                    if (data.isIsDebt()) {//是否有欠款
                        showCreditHintDialog(getResources().getString(R.string.credit_hint_msg));//显示弹窗
                    } else {//无欠款
                        if (data.isWithdrawal() && !data.isIsPaid()) {//有提额资格并且没有缴纳过保证金
                            mPresenter.getDepositInfo(PreferenceUtils.getUserSessionid());
                        }
                    }
                }
            }
        });

        RxViewUtil.clicks(mDataBinding.tvCodeVa).subscribe(o -> {
            if (data != null) {
                if (data.isIsVa()) {//是否生成还款码
                    ARouter.getInstance().build(RouteConstant.PAYMENT_CODE).navigation();
                } else if (data.isHasWithdrawalCode()) {//是否生成取款码
                    ARouter.getInstance().build(RouteConstant.REFUND_CODE).navigation();
                }
            }
        });
    }

    @Override
    public void getNetData() {
        mPresenter.queryCreditManager(PreferenceUtils.getUserSessionid());
    }

    @Override
    public void queryCreditManagerSuc(BaseBean<CreditManagerEntity> baseBean) {
        if (DataResult.isSuccessUnToast(this, baseBean)) {
            data = baseBean.getData();
            if (data.getAuditStatus() == 2) {//0.未审核  1.初审成功  2 .复审成功（复审） 3.审核不通过  4.授信满额
                // 5.冻结中 6.机审失败 7.驳回重填 8.重填提交 9.失败用户解封
                // 10.逾期超过7天还款 11.逾期超过25天还款后的状态 15.永久失败只可交纳信审金''',
                setProgress();
                // 判断是否缴纳保证金(这是为了兼容老用户，以后不需要判断是否缴纳保证金，因为后面提额不需要缴纳保证金)
                // 如果缴纳了 判断是否有欠款  如果有欠款 不能退保证金 如果没有欠款 可以退保证金
                // 如果没缴纳保证金 判断是否提额过（有不需要交保证金的提额） 如果提额了提额按钮不可见 如果没提额 再判断是否有提额资格 有就去正常提额 没有就是不满足提额要求
                if (data.isIsPaid()) {//判断是否缴纳了保证金
                    //点击退款的时候做了判断 是显示有欠款弹窗提示 还是去退保证金
                    mDataBinding.groupReturn.setVisibility(View.VISIBLE);
                    mDataBinding.tvCreditTv1.setVisibility(View.VISIBLE);
                    mDataBinding.tvCreditTv1.setText(getString(R.string.cant_improve_limit));
                    mDataBinding.tvCreditTv1.setEnabled(false);
                } else {
                    mDataBinding.groupReturn.setVisibility(View.GONE);
                    //1 未提额 2 已提额
                    //已提额
                    if (data.getCurrentIndex() == 2) {
                        mDataBinding.tvCreditTv1.setVisibility(View.GONE);
                    } else { //未提额
                        //是否有提额资格
                        canPromote = data.isWithdrawal();
                        mDataBinding.tvCreditTv1.setVisibility(View.VISIBLE);
                        if (canPromote) {
                            mDataBinding.tvCreditTv1.setText(R.string.confirm_increase);
                            mDataBinding.tvCreditTv1.setEnabled(true);
                        } else {
                            mDataBinding.tvCreditTv1.setText(R.string.paid_deposit);
                            mDataBinding.tvCreditTv1.setEnabled(false);
                        }
                    }
                }

            } else {//审核中
                mDataBinding.improveLimitProgress.setProgress(0, getResources().getString(R.string.audit_fail), 19);
                if (data.isIsRefund()) {//如果能退款
                    mDataBinding.groupReturn.setVisibility(View.VISIBLE);
                    mDataBinding.tvCreditTv1.setText(getString(R.string.cant_improve_limit));
                } else {
                    mDataBinding.groupReturn.setVisibility(View.GONE);
                    mDataBinding.tvCreditTv1.setText(getString(R.string.paid_deposit));
                }
                mDataBinding.tvCreditTv1.setEnabled(false);
            }
            if (data.isIsVa()) {//是否生成va码
                mDataBinding.tvCodeVa.setVisibility(View.VISIBLE);
                mDataBinding.tvCodeVa.setText(getResources().getString(R.string.code_va));
            } else {
                mDataBinding.tvCodeVa.setVisibility(View.GONE);
            }
            if (data.isHasWithdrawalCode()) {//如果已经生成了取款码
                mDataBinding.tvCodeVa.setVisibility(View.VISIBLE);
                mDataBinding.tvCodeVa.setText(getResources().getString(R.string.withdraw_code));
                mDataBinding.tvCreditTv1.setText(getString(R.string.credit_hint_msg3));
                mDataBinding.tvCreditTv1.setEnabled(false);
                mDataBinding.groupReturn.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void confirmIncreaseCashDepositSuc(BaseBean baseBean) {
        if (baseBean != null) {
            switch (baseBean.getCode()) {
                case -2:{
                    ARouter.getInstance().build(RouteConstant.CERTIFICATION_RESULT).navigation();
                    finish();
                    break;
                }
                case -1: {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Operation failed, please try again");
                    builder.setPositiveButton("Confirm", (dialog, which) -> dialog.dismiss());
                    builder.create().show();
                    break;
                }
                case 1: {
                    dialogImproveSuc();
                    mPresenter.queryCreditManager(PreferenceUtils.getUserSessionid());
                    break;
                }
                case 9999: {
                    DataResult.getLoginDialog(this);
                    break;
                }
                default:
                    break;
            }
        }
    }

    @Override
    public void getDepositInfoSuc(BaseBean<CashDepositInfoEntity> data) {
        if (DataResult.isSuccessUnToast(this, data) && data.getData() != null) {
            showCashDepositInfoDialog(data.getData());
        }
    }


    /**
     * 提额详情他那矿
     *
     * @param data
     */
    @SuppressLint("CheckResult")
    private void showCashDepositInfoDialog(CashDepositInfoEntity data) {
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_cash_deposit_info_manager, null);
        DialogCashDepositInfoManagerBinding binding = DataBindingUtil.bind(view);
        binding.setData(data);
        Dialog dialog = new Dialog(this, R.style.DialogCommon);
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(false);
        RxViewUtil.clicks(binding.acIvClose).subscribe(o -> dialog.dismiss());
        RxViewUtil.clicks(binding.acTvConfirmLimitIncrease).subscribe(o -> {
            if (canPromote) {
                mPresenter.confirmIncreaseCashDeposit(data.getCode(), PreferenceUtils.getUserSessionid());
            } else {
                showCreditHintDialog(getResources().getString(com.mmkj.usercenter.R.string.credit_hint_msg));
            }
            dialog.dismiss();
        });
        dialog.show();
    }

    /**
     * 规则详情弹窗
     */
    private void showNoticeDialog() {
        if (mNoticeDialog != null) {
            mNoticeDialog.dismiss();
        }
        View view = View.inflate(this, R.layout.dialog_return_notice, null);
        view.findViewById(R.id.iv_back).setOnClickListener(v -> mNoticeDialog.dismiss());
        mNoticeDialog = new Dialog(this, R.style.DialogCommon);
        mNoticeDialog.setContentView(view);
        mNoticeDialog.setCanceledOnTouchOutside(false);
        Window window = mNoticeDialog.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER); // 此处可以设置dialog显示的位置
        window.setWindowAnimations(R.style.ZoomInZoomOutAnimation); // 添加动画
        mNoticeDialog.show();
    }

    /**
     * 无欠款方可提额弹窗
     */
    private void showCreditHintDialog(String str) {
        if (mHintDialog != null) {
            mHintDialog.dismiss();
        }
        View view = View.inflate(this, R.layout.dialog_credit_hint, null);
        TextView tvmsg = view.findViewById(R.id.tv_hint_msg);
        tvmsg.setText(str);
        view.findViewById(R.id.tv_confirm).setOnClickListener(v -> mHintDialog.dismiss());
        mHintDialog = new Dialog(this, R.style.DialogCommon);
        mHintDialog.setContentView(view);
        mHintDialog.setCanceledOnTouchOutside(false);
        Window window = mHintDialog.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER); // 此处可以设置dialog显示的位置
        window.setWindowAnimations(R.style.ZoomInZoomOutAnimation); // 添加动画
        mHintDialog.show();
    }

    /**
     * 确认退保证金弹窗
     */
    private void showReturnDialog() {
        if (mRetrunDialog != null) {
            mRetrunDialog.dismiss();
        }
        View view = View.inflate(this, R.layout.dialog_return_gredit, null);
        view.findViewById(R.id.tv_confirm).setOnClickListener(v -> {
            ARouter.getInstance().build(RouteConstant.REFUND_DEPOSIT).navigation();
            mRetrunDialog.dismiss();
        });
        view.findViewById(R.id.tv_cancel).setOnClickListener(v -> mRetrunDialog.dismiss());
        mRetrunDialog = new Dialog(this, R.style.DialogCommon);
        mRetrunDialog.setContentView(view);
        mRetrunDialog.setCanceledOnTouchOutside(false);
        Window window = mRetrunDialog.getWindow();
        Objects.requireNonNull(window).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        Objects.requireNonNull(window).setGravity(Gravity.CENTER); // 此处可以设置dialog显示的位置
        Objects.requireNonNull(window).setWindowAnimations(R.style.ZoomInZoomOutAnimation); // 添加动画
        mRetrunDialog.show();
    }

    private Dialog dialogImproveAmount = null;

    /**
     * 提额成功
     */
    private void dialogImproveSuc() {
        View view = View.inflate(this, R.layout.dialog_improve_amount, null);
        view.findViewById(R.id.btn_know).setOnClickListener(v -> {
            dialogImproveAmount.dismiss();
        });
        TextView tvTitle = view.findViewById(R.id.tv_title);
        tvTitle.setGravity(Gravity.CENTER_HORIZONTAL);
        dialogImproveAmount = new Dialog(this, R.style.DialogCommon);
        dialogImproveAmount.setContentView(view);
        dialogImproveAmount.setCanceledOnTouchOutside(false);
        Window window = dialogImproveAmount.getWindow();
        Objects.requireNonNull(window).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        Objects.requireNonNull(window).setGravity(Gravity.CENTER); // 此处可以设置dialog显示的位置
        Objects.requireNonNull(window).setWindowAnimations(R.style.ZoomInZoomOutAnimation); // 添加动画
        dialogImproveAmount.show();
    }

    private void setProgress() {
        switch ((int) data.getCreditBalance()) {
            case 1000: {
                mDataBinding.improveLimitProgress.setProgress(1, StringUtils.cutOutLastThree(StringUtils.doubleZheng(data.getCreditBalance())));
                break;
            }
            case 1500: {
                mDataBinding.improveLimitProgress.setProgress(2, StringUtils.cutOutLastThree(StringUtils.doubleZheng(data.getCreditBalance())));
                break;
            }
            case 1800: {
                mDataBinding.improveLimitProgress.setProgress(3, StringUtils.cutOutLastThree(StringUtils.doubleZheng(data.getCreditBalance())));
                break;
            }
            case 2500:
                mDataBinding.improveLimitProgress.setProgress(4, StringUtils.cutOutLastThree(StringUtils.doubleZheng(data.getCreditBalance())));
                break;
            case 3500:
                mDataBinding.improveLimitProgress.setProgress(5, StringUtils.cutOutLastThree(StringUtils.doubleZheng(data.getCreditBalance())));
                break;
//            case 5000:
//                mDataBinding.improveLimitProgress.setProgress(5, StringUtils.cutOutLastThree(StringUtils.doubleZheng(data.getCreditBalance())));
//                break;
            default:
                break;
        }
    }
}
