package com.mmkjflb.mmloan.viewmodel.main;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;

import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.mmkj.baselibrary.RouteConstant;
import com.mmkj.baselibrary.annotation.ActivityFragmentInject;
import com.mmkj.baselibrary.app.ActivityStack;
import com.mmkj.baselibrary.model.entitry.BaseBean;
import com.mmkj.baselibrary.util.Constants;
import com.mmkj.baselibrary.util.PreferenceUtils;
import com.mmkj.baselibrary.util.RxViewUtil;
import com.mmkj.baselibrary.util.StringUtils;
import com.mmkj.baselibrary.util.view.VerificationAction;
import com.mmkj.baselibrary.util.view.VerificationCodeEditText;
import com.mmkjflb.mmloan.R;
import com.mmkjflb.mmloan.base.BaseActivity;
import com.mmkjflb.mmloan.databinding.ActivityMainBinding;
import com.mmkjflb.mmloan.databinding.DialogCashDepositBinding;
import com.mmkjflb.mmloan.model.entity.CashDepositInfoEntity;
import com.mmkjflb.mmloan.model.entity.MainEntity;
import com.mmkjflb.mmloan.model.http.DataResult;
import com.syhmmfqphl.xyxlibrary.utils.ExtraKeys;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import androidx.databinding.DataBindingUtil;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import me.everything.android.ui.overscroll.IOverScrollDecor;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

import static me.everything.android.ui.overscroll.IOverScrollState.STATE_BOUNCE_BACK;
import static me.everything.android.ui.overscroll.IOverScrollState.STATE_DRAG_START_SIDE;

/**
 * Created by Administrator on 2017/10/16.
 */
@Route(path = RouteConstant.MAIN)
@ActivityFragmentInject(contentViewId = R.layout.activity_main, loadingTargetView = R.id.linear_loading)
public class MainActivity extends BaseActivity<MainPresenter, ActivityMainBinding> implements MainContract.View {
    private RotateAnimation rotateAnimation = null;
    private boolean canPromote;//是否有提额资格    isPromote=true,没逾期没需要还款的即可提额
    private MainEntity mMainData;
    private String vertificateCode = "";

    @Override
    protected void initViews() {
        mDataBinding.statusBar.setVisibility(View.GONE);
        mDataBinding.ivUsercenter.setVisibility(View.VISIBLE);
        getActivityComponent().inject(this);
        initClicks();
//        mPresenter.balance(false, PreferenceUtils.getUserSessionid());
        IOverScrollDecor mVertOverScrollEffect = OverScrollDecoratorHelper.setUpOverScroll(mDataBinding.svMain);
        mVertOverScrollEffect.setOverScrollUpdateListener((decor, state, offset) -> {
            if (state == STATE_DRAG_START_SIDE) {
                mDataBinding.ivRefresh.setRotation(offset);
            }
        });
        mVertOverScrollEffect.setOverScrollStateListener((decor, oldState, newState) -> {
            if (newState == STATE_DRAG_START_SIDE) {
                mDataBinding.ivHelp.setVisibility(View.GONE);
                mDataBinding.ivRefresh.setVisibility(View.VISIBLE);
            } else if (newState == STATE_BOUNCE_BACK && oldState == STATE_DRAG_START_SIDE) {
                mPresenter.balance(true, PreferenceUtils.getUserSessionid());
            } else if (oldState == 3 && newState == 0) {
                animationStop();
            }
        });
        initAnimationShow();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.balance(false, PreferenceUtils.getUserSessionid());
    }

    private void initAnimationShow() {
        rotateAnimation = new RotateAnimation(0f, 1080f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(1000);
        rotateAnimation.setInterpolator(new LinearInterpolator());//不设置会停顿
        rotateAnimation.setRepeatMode(Animation.RESTART);
        rotateAnimation.setRepeatCount(Animation.INFINITE);
        rotateAnimation.setFillAfter(true);
    }


    private long mExitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - mExitTime > 2000) {
                toast(R.string.back_again);
                mExitTime = System.currentTimeMillis();
            } else {
                ActivityStack.getInstance().exitApp();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @SuppressLint({"CheckResult", "ClickableViewAccessibility"})
    private void initClicks() {
        RxViewUtil.clicks(mDataBinding.ivHelp).subscribe(o -> {
//            JumpManager.jumpToKey1(this, WebViewActivity.class, FAQ);
            ARouter.getInstance().build(RouteConstant.WEBVIEW).withString("loadUrl", Constants.FAQ).navigation();
        });

        RxViewUtil.clicks(mDataBinding.ivUsercenter).subscribe(o -> ARouter.getInstance()
                .build(RouteConstant.USER_CENTER)
                .withTransition(R.anim.down_to_up_open, android.R.anim.fade_out)
                .navigation(this));
        RxViewUtil.clicks(mDataBinding.btnGotoRepay).subscribe(o -> {
//            JumpManager.jumpToKey1(this, RepayActivity.class, mMainData.getBidId()); //, mMainData.getBillId()
            ARouter.getInstance().build(RouteConstant.REPAYS).withInt("bidId", mMainData.getBidId()).navigation();
        });
        RxViewUtil.clicks(mDataBinding.btnGotoBorrow).subscribe(o -> {
            if (mMainData.isLastTimeBorrow()) {
                loanVerificationDialog();
            } else {
//                JumpManager.jumpTo(MainActivity.this, LoanActivity.class);
                ARouter.getInstance().build(RouteConstant.LOAN).navigation();
            }
        });
        //点击提额
        RxViewUtil.clicks(mDataBinding.cardViewAscendingLine).subscribe(o -> mPresenter.getDepositInfo(PreferenceUtils.getUserSessionid()));

        RxViewUtil.clicks(mDataBinding.llBankRepay).subscribe(o ->
//                JumpManager.jumpToKey1(this, BankRepayActivity.class, mMainData.getBidId())； //, mMainData.getBillId()
                        ARouter.getInstance()
                                .build(RouteConstant.BANK_REPAY)
                                .withInt("bidId", mMainData.getBidId())
                                .withInt("billId", mMainData.getBillId())
                                .navigation()

        );
    }

    private Dialog mHintDialog;

    /**
     * 无欠款方可提额弹窗
     */
    private void showCreditHintDialog(String str) {
        if (mHintDialog != null) {
            mHintDialog.dismiss();
        }
        View view = View.inflate(this, R.layout.dialog_credit_hint, null);
        TextView tvmsg = view.findViewById(com.mmkj.usercenter.R.id.tv_hint_msg);
        tvmsg.setText(str);
        view.findViewById(com.mmkj.usercenter.R.id.tv_confirm).setOnClickListener(v -> mHintDialog.dismiss());
        mHintDialog = new Dialog(this, com.mmkj.usercenter.R.style.DialogCommon);
        mHintDialog.setContentView(view);
        mHintDialog.setCanceledOnTouchOutside(false);
        Window window = mHintDialog.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER); // 此处可以设置dialog显示的位置
        window.setWindowAnimations(com.mmkj.usercenter.R.style.ZoomInZoomOutAnimation); // 添加动画
        mHintDialog.show();
    }

    private Dialog dialogImproveAmount = null;

    private void canImproveAmountDialog() {
        if (dialogImproveAmount != null) {
            dialogImproveAmount.show();
        } else {
            View view = View.inflate(this, R.layout.dialog_improve_amount, null);
            view.findViewById(R.id.btn_know).setOnClickListener(v -> {
                mPresenter.balance(false, PreferenceUtils.getUserSessionid());
                dialogImproveAmount.dismiss();
//                ARouter.getInstance().build(RouteConstant.CREDIT_GUARANTEE).navigation();
            });
            TextView tvTitle = view.findViewById(R.id.tv_title);
            tvTitle.setGravity(Gravity.CENTER_HORIZONTAL);
            dialogImproveAmount = new Dialog(this, R.style.DialogCommon);
            dialogImproveAmount.setContentView(view);
            dialogImproveAmount.setCanceledOnTouchOutside(false);
            Window window = dialogImproveAmount.getWindow();
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            window.setGravity(Gravity.CENTER); // 此处可以设置dialog显示的位置
            window.setWindowAnimations(R.style.ZoomInZoomOutAnimation); // 添加动画
            dialogImproveAmount.show();
            mPresenter.balance(false, PreferenceUtils.getUserSessionid());
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        animationStop();
    }

    public void animationStop() {
        if (rotateAnimation != null) {
            rotateAnimation.cancel();
        }
        mDataBinding.ivRefresh.clearAnimation();
        mDataBinding.ivRefresh.setVisibility(View.GONE);
        mDataBinding.ivHelp.setVisibility(View.VISIBLE);
    }

    @Override
    public void balanceSuc(BaseBean<MainEntity> baseBean) {

        animationStop();

        if (DataResult.isSuccessUnToast(this, baseBean)) {
            mMainData = null;
            mMainData = baseBean.getData();
            if (mMainData.getCreditStatus() == 9 || mMainData.getCreditStatus() == 10 || mMainData.getCreditStatus() == 11) {
//                JumpManager.jumpToClose(this, CertificationResultActivity.class);
                ARouter.getInstance().build(RouteConstant.CERTIFICATION_RESULT).navigation();
                finish();
            }
            if (mMainData.getBidsStatus() == 1) {
                mDataBinding.loanMsg.setText(getResources().getString(R.string.loansuc_gocash));
                mDataBinding.loanMsg.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.orange_ff8a00));
            } else {
                mDataBinding.loanMsg.setText(getResources().getString(R.string.repay_regain_credit));
                mDataBinding.loanMsg.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.gray_d1d1d1));
            }
            mDataBinding.tvCreditLimit.setText(StringUtils.cutOutLastThree(StringUtils.doubleZheng(mMainData.getCreditBalance())));
            mDataBinding.tvUsableAmount.setText(StringUtils.cutOutLastThree(StringUtils.doubleZheng(mMainData.getAvailableBalance())));
            mDataBinding.tvRepayAmount.setText(StringUtils.cutOutLastThree(StringUtils.doubleZheng(mMainData.getRepaymentAmount())));
            if (mMainData.getOverdueFine() == 0) {
                setStatusBar(R.color.statusbar_maincolor);
                mDataBinding.linearTop.setBackgroundResource(R.drawable.main_top);
                mDataBinding.btnGotoRepay.setBackgroundResource(R.drawable.radius5_btn_main);
                if (mMainData.getRepaymentAmount() == 0) {
                    if (mMainData.getAvailableBalance() == 0) {
                        //不能借款
                        loanUnable();
                    } else {
                        //可以借款
                        loanable();
                    }
                    repayUnable();
                } else {
                    if (mMainData.getButtonDisabled() == 0) {
                        loanUnable();
                        if (mMainData.isBeingProcessed()) { //	if (mainData.getBidsStatus() == 1) {
                            mDataBinding.tvLastRepaytime.setText(R.string.bank_detailing);
                            repayUnable();
                        } else {
                            mDataBinding.btnGotoRepay.setEnabled(true);
                            mDataBinding.tvLastRepaytime.setText(String.format(getResources().getString(R.string
                                    .recent_repayday), mMainData.getRepaymentTime()));
                            repayEnable();
                        }
                    } else {
                        mDataBinding.tvLastRepaytime.setText(String.format(getResources().getString(R.string.recent_repayday),
                                mMainData.getRepaymentTime()));
                        loanUnable();
                        repayUnable();
                    }
                }
            } else {   //逾期
                setStatusBar(R.color.overdue_orange);
                mDataBinding.linearTop.setBackgroundResource(R.drawable.overdue_top);
                loanUnable();
                repayEnable();
                mDataBinding.tvLastRepaytime.setTextColor(ContextCompat.getColor(this, R.color.orange_fe7e00));
                mDataBinding.tvLastRepaytime.setText(String.format(getResources().getString(R.string.overdue_day), mMainData
                        .getOverdueTime()));
                mDataBinding.btnGotoRepay.setEnabled(true);
                mDataBinding.btnGotoRepay.setBackgroundResource(R.drawable.radius3_orangefe7e00_btn_main);
            }
            //isPromote=true,没逾期没需要还款的即可提额
            if (mMainData.isWithdrawal()) {  //是否有提额资格
                mDataBinding.cardViewAscendingLine.setVisibility(View.VISIBLE);
                if (mMainData.getOverdueFine() != 0 || mMainData.getRepaymentAmount() != 0) {
                    canPromote = false;
                } else {
                    canPromote = true;
                }
            } else {
                mDataBinding.cardViewAscendingLine.setVisibility(View.INVISIBLE);
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
                    builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.create().show();
                    break;
                }
                case 1: {
                    canImproveAmountDialog();
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

    private void loanUnable() {
        mDataBinding.tvLoanShow1.setEnabled(false);
//        mDataBinding.llBankRepay.setVisibility(View.GONE);
        mDataBinding.tvAvailableLimit.setTextColor(ContextCompat.getColor(this, R.color.gray_d1d1d1));
        mDataBinding.tvUsableAmount.setTextColor(ContextCompat.getColor(this, R.color.gray_d1d1d1));
        mDataBinding.btnGotoBorrow.setEnabled(false);
    }

    private void loanable() {
        mDataBinding.tvLoanShow1.setEnabled(true);
//        mDataBinding.llBankRepay.setVisibility(View.GONE);
        mDataBinding.tvAvailableLimit.setTextColor(ContextCompat.getColor(this, R.color.gray_9b9b9b));
        mDataBinding.tvUsableAmount.setTextColor(ContextCompat.getColor(this, R.color.black_584f60));
        mDataBinding.btnGotoBorrow.setEnabled(true);
    }

    private void repayUnable() {
        mDataBinding.tvRepayShow1.setEnabled(false);
//        mDataBinding.llBankRepay.setVisibility(View.GONE);
        mDataBinding.tvRepayDesc.setTextColor(ContextCompat.getColor(this, R.color.gray_d1d1d1));
        mDataBinding.tvRepayAmount.setTextColor(ContextCompat.getColor(this, R.color.gray_d1d1d1));
        mDataBinding.tvLastRepaytime.setTextColor(ContextCompat.getColor(this, R.color.gray_d1d1d1));
        mDataBinding.btnGotoRepay.setEnabled(false);
    }

    private void repayEnable() {
        mDataBinding.tvRepayShow1.setEnabled(true);
//        mDataBinding.llBankRepay.setVisibility(View.VISIBLE);
        mDataBinding.tvRepayDesc.setTextColor(ContextCompat.getColor(this, R.color.gray_9b9b9b));
        mDataBinding.tvRepayAmount.setTextColor(ContextCompat.getColor(this, R.color.black_584f60));
        mDataBinding.tvLastRepaytime.setTextColor(ContextCompat.getColor(this, R.color.black_584f60));
        mDataBinding.btnGotoRepay.setEnabled(true);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == Constants.CODE_LOGINTIMEOUT && resultCode == RESULT_OK) {
//            mPresenter.balance(false, PreferenceUtils.getUserSessionid());
//        }
    }

    @Override
    public void sendCodeSuc(BaseBean baseBean) {
        if (DataResult.isSuccessUnToast(this, baseBean)) {
            Observable.interval(0, 1, TimeUnit.SECONDS)
                    .take(com.mmkjflb.mmloan.app.Constants.TIMECOUNT + 1)
                    .map(aLong -> com.mmkjflb.mmloan.app.Constants.TIMECOUNT - aLong)
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe(disposable -> {
                        //进入倒计时
                        btnGetCode.setEnabled(false);
                    })
                    .subscribe(new Observer<Long>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                        }

                        @Override
                        public void onNext(Long aLong) {
                            btnGetCode.setText(aLong + "S");
                        }

                        @Override
                        public void onError(Throwable e) {
                        }

                        @Override
                        public void onComplete() {
                            btnGetCode.setEnabled(true);
                            btnGetCode.setText(R.string.get);
                        }
                    });
        }

    }

    @Override
    public void afterBorrowCheckSuc(BaseBean baseBean) {
        if (DataResult.isSuccessUnToastAll(this, baseBean)) {
//            JumpManager.jumpTo(MainActivity.this, LoanActivity.class);
            ARouter.getInstance().build(RouteConstant.LOAN).navigation();
            if (dialogLoanVerificate != null) {
                dialogLoanVerificate.dismiss();
            }
        } else {
            vciCode.setText("");
            tvErrorMsg.setVisibility(View.VISIBLE);
        }
    }

    private String productCode = null;

    @Override
    public void getDepositInfoSuc(BaseBean<CashDepositInfoEntity> data) {
        if (DataResult.isSuccessUnToast(this, data) && data.getData() != null) {
            productCode = data.getData().getCode();
            showCashDepositInfoDialog(data.getData());
        }
    }

    /**
     * 提额详情弹框
     *
     * @param data dialog_cash_deposit_info_manager
     */
    @SuppressLint("CheckResult")
    private void showCashDepositInfoDialog(CashDepositInfoEntity data) {
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_cash_deposit, null);
        DialogCashDepositBinding binding = DataBindingUtil.bind(view);
        binding.setData(data);
        Dialog dialog = new Dialog(this, R.style.DialogCommon);
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(false);
        RxViewUtil.clicks(binding.acIvClose).subscribe(o -> dialog.dismiss());
        RxViewUtil.clicks(binding.acTvConfirmLimitIncrease).subscribe(o -> {
            if (canPromote) {
                mPresenter.confirmIncreaseCashDeposit(productCode, PreferenceUtils.getUserSessionid());
            } else {
                showCreditHintDialog(getResources().getString(com.mmkj.usercenter.R.string.credit_hint_msg));
            }
            dialog.dismiss();
        });
        dialog.show();
    }


    private Dialog dialogLoanVerificate = null;
    private TextView btnGetCode;
    private TextView tvErrorMsg;
    private VerificationCodeEditText vciCode;

    /**
     * 输入借款短信验证码
     */
    private void loanVerificationDialog() {
        View view = View.inflate(this, R.layout.dialog_loan_verification, null);
        TextView tvPhone = view.findViewById(R.id.tvPhone);
        tvPhone.setText(PreferenceUtils.getString(PreferenceUtils.USER_PHONE, ""));
        ImageView ivClose = view.findViewById(R.id.ivClose);
        ivClose.setOnClickListener(v -> dialogLoanVerificate.dismiss());
        vciCode = view.findViewById(R.id.vciCode);
        TextView btnConfirm = view.findViewById(R.id.btnConfirm);
        tvErrorMsg = view.findViewById(R.id.tvErrorMsg);
        vciCode.setOnVerificationCodeChangedListener(new VerificationAction.OnVerificationCodeChangedListener() {
            @Override
            public void onVerCodeChanged(CharSequence s, int start, int before, int count) {
                if (s.length() < 6) {
                    btnConfirm.setEnabled(false);
                }
                tvErrorMsg.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onInputCompleted(CharSequence s) {
                btnConfirm.setEnabled(true);
                vertificateCode = s.toString();
            }
        });
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> map = new HashMap<>();
                map.put("mobile", PreferenceUtils.getString(PreferenceUtils.USER_PHONE, ""));
                map.put("captcha", vertificateCode);
                mPresenter.afterBorrowCheck(map);
            }
        });
        btnGetCode = view.findViewById(R.id.btnGetCode);
        btnGetCode.setOnClickListener(v -> mPresenter.sendCode(PreferenceUtils.getString(PreferenceUtils.USER_PHONE, ""), ExtraKeys.CODETYPE_LOANVERIFICATE));
        dialogLoanVerificate = new Dialog(this, R.style.DialogCommon);
        dialogLoanVerificate.setContentView(view);
        dialogLoanVerificate.setCanceledOnTouchOutside(false);
        Window window = dialogLoanVerificate.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER); // 此处可以设置dialog显示的位置
        window.setWindowAnimations(R.style.ZoomInZoomOutAnimation); // 添加动画
        dialogLoanVerificate.show();
    }
}
