package com.mmkjflb.mmloan.viewmodel.certificateresult;

import android.annotation.SuppressLint;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

import com.afollestad.materialdialogs.MaterialDialog;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.hwangjr.rxbus.RxBus;
import com.mmkj.baselibrary.RouteConstant;
import com.mmkj.baselibrary.annotation.ActivityFragmentInject;
import com.mmkj.baselibrary.app.ActivityStack;
import com.mmkj.baselibrary.model.entitry.BaseBean;
import com.mmkj.baselibrary.util.EventConstants;
import com.mmkj.baselibrary.util.PreferenceUtils;
import com.mmkj.baselibrary.util.RxViewUtil;
import com.mmkjflb.mmloan.R;
import com.mmkjflb.mmloan.app.Constants;
import com.mmkjflb.mmloan.base.BaseActivity;
import com.mmkjflb.mmloan.databinding.ActivityCertificateResultBinding;
import com.mmkjflb.mmloan.model.entity.ArtificialAuthentStatusEntity;
import com.mmkjflb.mmloan.model.http.DataResult;
import java.util.concurrent.TimeUnit;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * @author Administrator on 2017/10/20.
 */
@Route(path = RouteConstant.CERTIFICATION_RESULT)
@ActivityFragmentInject(contentViewId = R.layout.activity_certificate_result, loadingTargetView = R.id.linear_loading, statusBarColor = R.color.black)
public class CertificationResultActivity extends BaseActivity<CertificationResultPresenter, ActivityCertificateResultBinding> implements CertificationResultContract.View {
    private Disposable mDisposable;
    private RotateAnimation rotateAnimation;
    private MaterialDialog materialDialog;
    private int type = 1, status = -1;

    @Override
    protected void initViews() {
        mDataBinding.ivBack.setVisibility(View.VISIBLE);
        mDataBinding.viewStatus.setVisibility(View.GONE);
        getActivityComponent().inject(this);
//        type = getIntent().getIntExtra(ExtraKeys.Key_Msg1, 1);
        initClicks();
        ActivityStack.getInstance().removeAllExceptCurrent(this);
    }

    public void interval(long milliseconds) {
        Observable.interval(milliseconds, TimeUnit.MINUTES)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable disposable) {
                        mDisposable = disposable;
                    }

                    @Override
                    public void onNext(@NonNull Long number) {
                        mPresenter.queryCredit(PreferenceUtils.getUserSessionid());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.queryCredit(PreferenceUtils.getUserSessionid());
        interval(1);
    }

    @Override
    protected void onPause() {
        super.onPause();
        cancelDisposable();
        animationStop();
    }


    private void cancelDisposable() {
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }

    @SuppressLint("CheckResult")
    private void initClicks() {
        RxViewUtil.clicks(mDataBinding.ivHelp).subscribe(o -> {
//            JumpManager.jumpToKey1(CertificationResultActivity.this, WebViewActivity.class, Constants.FAQ);
            ARouter.getInstance().build(RouteConstant.WEBVIEW).withString("loadUrl",Constants.FAQ).navigation();

        });
        RxViewUtil.clicks(mDataBinding.ivBack).subscribe(o -> ARouter.getInstance().build(RouteConstant.USER_CENTER).navigation());
        RxViewUtil.clicks(mDataBinding.btnZhimaConfirm).subscribe(o -> {
            if (status == 100) {
                ARouter.getInstance().build(RouteConstant.CERTIFICATION_MAIN).navigation();
                finish();
            } else {
                rollback();
            }
        });
    }

    private void rotateAnimationShow() {
        mDataBinding.ivRotateBg.setBackgroundResource(R.drawable.rotate_bg);
        mDataBinding.ivRotate.setVisibility(View.VISIBLE);
        mDataBinding.btnZhimaConfirm.setVisibility(View.INVISIBLE);
        mDataBinding.tvMyLines.setText(getResources().getString(R.string.lines_assessing));
        mDataBinding.tvMyLinesDetails.setText(getResources().getString(R.string.lines_assessing_details));

        rotateAnimation = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(1000);
        rotateAnimation.setInterpolator(new LinearInterpolator());//不设置会停顿
        rotateAnimation.setRepeatMode(Animation.RESTART);
        rotateAnimation.setRepeatCount(Animation.INFINITE);
        rotateAnimation.setFillAfter(true);
        mDataBinding.ivRotate.startAnimation(rotateAnimation);
        rotateAnimation.start();
    }

    /**
     * 审核结果
     *
     * @param baseBean
     */
    @Override
    public void queryCreditSuc(BaseBean<ArtificialAuthentStatusEntity> baseBean) {
        if (DataResult.isSuccessUnToast(CertificationResultActivity.this, baseBean)) {
            rotateAnimationShow();
            ArtificialAuthentStatusEntity data = baseBean.getData();
//            loadView(baseBean);
            loadViewSwitch(data);
        }
    }

    private void loadViewSwitch(ArtificialAuthentStatusEntity data) {
        status = data.getAuditStatus();
        switch (status) {
            case 0: {
                //认证未完成
                PreferenceUtils.put(PreferenceUtils.USER_SETP, 1);
                RxBus.get().post(EventConstants.ATCHANGE_EVENT, true);
                break;
            }
            case 1: {
                //正在审核-初审
                PreferenceUtils.put(PreferenceUtils.USER_SETP, 3);
                break;
            }
            case 2: {
                //审核成功-复审
                PreferenceUtils.put(PreferenceUtils.USER_SETP, 4);
                RxBus.get().post(EventConstants.ATCHANGE_EVENT, true);
//                    JumpManager.jumpToClose(CertificationResultActivity.this, MainActivity.class);
                ARouter.getInstance().build(RouteConstant.MAIN).navigation();
                finish();
                break;
            }
            case 3: {
                //机审失败  ，需要等待7天
                PreferenceUtils.put(PreferenceUtils.USER_SETP, 5);
                cancelDisposable();
                animationStop();
                mDataBinding.ivRotateBg.setBackgroundResource(R.drawable.lines_recertification_fail);
                mDataBinding.ivRotate.setVisibility(View.GONE);
                mDataBinding.btnZhimaConfirm.setEnabled(false);
                mDataBinding.btnZhimaConfirm.setText(getResources().getString(R.string.fail_again_button));
                mDataBinding.btnZhimaConfirm.setVisibility(View.VISIBLE);
                mDataBinding.clAgree.setVisibility(View.INVISIBLE);
                mDataBinding.tvMyLines.setText(getResources().getString(R.string.fail_again_button_title));
                mDataBinding.tvMyLinesDetails.setText(getResources().getString(R.string.fail_again_button_msg));
                break;
            }
            case 4: {
                //授信满额
                break;
            }
            case 5: {
                //冻结中
                break;
            }
            case 6: {
                //机审核失败
                break;
            }
            case 7: {
                //驳回重填   审核被驳回
                PreferenceUtils.put(PreferenceUtils.USER_SETP, 5);
                cancelDisposable();
                animationStop();
                mDataBinding.ivRotateBg.setBackgroundResource(R.drawable.lines_recertification_fail);
                mDataBinding.ivRotate.setVisibility(View.GONE);
                mDataBinding.btnZhimaConfirm.setEnabled(true);
                mDataBinding.btnZhimaConfirm.setText(getResources().getString(R.string.again_certification));
                mDataBinding.btnZhimaConfirm.setVisibility(View.VISIBLE);
                mDataBinding.clAgree.setVisibility(View.INVISIBLE);
                mDataBinding.tvMyLines.setText(getResources().getString(R.string.lines_assess_refuse));
                mDataBinding.tvMyLinesDetails.setText(data.getAuditSuggest());
                break;
            }
            case 8: {
                //重新提交
                PreferenceUtils.put(PreferenceUtils.USER_SETP, 3);
                break;
            }
            case 9: {
                //失败用户解封  审核失败解封
                PreferenceUtils.put(PreferenceUtils.USER_SETP, 5);
                cancelDisposable();
                animationStop();
                mDataBinding.ivRotateBg.setBackgroundResource(R.drawable.lines_recertification_fail);
                mDataBinding.ivRotate.setVisibility(View.GONE);
                mDataBinding.btnZhimaConfirm.setEnabled(true);
                mDataBinding.btnZhimaConfirm.setText(getResources().getString(R.string.again_certification));
                mDataBinding.btnZhimaConfirm.setVisibility(View.VISIBLE);
                mDataBinding.clAgree.setVisibility(View.INVISIBLE);
                mDataBinding.tvMyLines.setText(getResources().getString(R.string.lines_assess_failure));
                mDataBinding.tvMyLinesDetails.setText(getResources().getString(R.string.fail_again_button_msg));
                if (materialDialog != null && materialDialog.isShowing()) {
                    return;
                }
                materialDialog = new MaterialDialog.Builder(this)
                        .content(getResources().getString(R.string.lines_dialog_msg))
                        .negativeText(R.string.confirm)
                        .canceledOnTouchOutside(false)
                        .onNegative((dialog, which) -> materialDialog.dismiss()).show();
                break;
            }
            case 10: {
                //逾期7天重审
                cancelDisposable();
                animationStop();
                PreferenceUtils.put(PreferenceUtils.USER_SETP, 5);
                mDataBinding.ivRotateBg.setBackgroundResource(R.drawable.lines_retrial);
                mDataBinding.ivRotate.setVisibility(View.GONE);
                mDataBinding.btnZhimaConfirm.setEnabled(false);
                mDataBinding.btnZhimaConfirm.setText(getResources().getString(R.string.fail_again_button));
                mDataBinding.btnZhimaConfirm.setVisibility(View.VISIBLE);
                mDataBinding.clAgree.setVisibility(View.INVISIBLE);
                mDataBinding.tvMyLines.setVisibility(View.GONE);
                mDataBinding.tvMyLinesDetails.setText(getResources().getString(R.string.lines_retrial_msg));
                break;
            }
            case 11: {
                //逾期超25天不重审，永久不能借款
                cancelDisposable();
                animationStop();
                mDataBinding.ivHelp.setVisibility(View.GONE);
                PreferenceUtils.put(PreferenceUtils.USER_SETP, 5);
                mDataBinding.ivRotateBg.setBackgroundResource(R.drawable.lines_fail);
                mDataBinding.ivRotate.setVisibility(View.GONE);
                mDataBinding.btnZhimaConfirm.setVisibility(View.GONE);
                mDataBinding.clAgree.setVisibility(View.INVISIBLE);
                mDataBinding.tvMyLines.setVisibility(View.GONE);
                mDataBinding.tvMyLinesDetails.setText(getResources().getString(R.string.lines_assess_unqualified_msg));
                break;
            }
            case 12: {
                //逾期7天解封
                cancelDisposable();
                animationStop();
                PreferenceUtils.put(PreferenceUtils.USER_SETP, 5);
                mDataBinding.ivRotateBg.setBackgroundResource(R.drawable.lines_retrial);
                mDataBinding.ivRotate.setVisibility(View.GONE);
                mDataBinding.btnZhimaConfirm.setEnabled(true);
                mDataBinding.btnZhimaConfirm.setText(getResources().getString(R.string.again_certification));
                mDataBinding.btnZhimaConfirm.setVisibility(View.VISIBLE);
                mDataBinding.clAgree.setVisibility(View.INVISIBLE);
                mDataBinding.tvMyLines.setVisibility(View.GONE);
                mDataBinding.tvMyLinesDetails.setText(getResources().getString(R.string.lines_retrial_msg));
                materialDialog = new MaterialDialog.Builder(this)
                        .content(getResources().getString(R.string.lines_dialog_msg))
                        .negativeText(R.string.confirm)
                        .canceledOnTouchOutside(false)
                        .onNegative((dialog, which) -> materialDialog.dismiss()).show();
                break;
            }
            case 13: {
                //失败
                PreferenceUtils.put(PreferenceUtils.USER_SETP, 5);
                cancelDisposable();
                animationStop();
                mDataBinding.ivRotateBg.setBackgroundResource(R.drawable.lines_fail);
                mDataBinding.ivRotate.setVisibility(View.GONE);
                mDataBinding.btnZhimaConfirm.setVisibility(View.GONE);
                mDataBinding.clAgree.setVisibility(View.INVISIBLE);
                mDataBinding.tvMyLines.setText(getResources().getString(R.string.lines_assess_failure));
                mDataBinding.tvMyLinesDetails.setText(getResources().getString(R.string.lines_assess_forbid_msg));
                break;
            }
            case 100: {
                //借款成功，但是超过7天取款，取款超时
                PreferenceUtils.put(PreferenceUtils.USER_SETP, 1);
                cancelDisposable();
                animationStop();
                mDataBinding.ivRotateBg.setBackgroundResource(R.drawable.lines_recertification_fail);
                mDataBinding.ivRotate.setVisibility(View.GONE);
                mDataBinding.clAgree.setVisibility(View.INVISIBLE);
                mDataBinding.tvMyLines.setText(getResources().getString(R.string.loaned_lines));
                mDataBinding.tvMyLinesDetails.setText(getResources().getString(R.string.loaned_lines_details));
                mDataBinding.btnZhimaConfirm.setEnabled(true);
                mDataBinding.btnZhimaConfirm.setText(getResources().getString(R.string.resubmit_auth));
                mDataBinding.btnZhimaConfirm.setVisibility(View.VISIBLE);
                break;
            }
            default:
                break;
        }
    }

    private void loadView(ArtificialAuthentStatusEntity data) {
        if (data.getAuditStatus() == 0) {
            //认证未完成
            PreferenceUtils.put(PreferenceUtils.USER_SETP, 1);
            RxBus.get().post(EventConstants.ATCHANGE_EVENT, true);
        } else if (data.getAuditStatus() == 1) {
            //正在审核-初审
            PreferenceUtils.put(PreferenceUtils.USER_SETP, 3);

        } else if (data.getAuditStatus() == 2) {
            //审核成功-复审
            PreferenceUtils.put(PreferenceUtils.USER_SETP, 4);
            RxBus.get().post(EventConstants.ATCHANGE_EVENT, true);
//            JumpManager.jumpToClose(this, MainActivity.class);
            ARouter.getInstance().build(RouteConstant.MAIN).navigation();finish();
        } else if (data.getAuditStatus() == 3) {
            //机审失败  ，需要等待7天
            PreferenceUtils.put(PreferenceUtils.USER_SETP, 5);
            cancelDisposable();
            animationStop();
            mDataBinding.ivRotateBg.setBackgroundResource(R.drawable.lines_recertification_fail);
            mDataBinding.ivRotate.setVisibility(View.GONE);
            mDataBinding.btnZhimaConfirm.setEnabled(false);
            mDataBinding.btnZhimaConfirm.setText(getResources().getString(R.string.fail_again_button));
            mDataBinding.btnZhimaConfirm.setVisibility(View.VISIBLE);
            mDataBinding.clAgree.setVisibility(View.INVISIBLE);
            mDataBinding.tvMyLines.setText(getResources().getString(R.string.fail_again_button_title));
            mDataBinding.tvMyLinesDetails.setText(getResources().getString(R.string.fail_again_button_msg));
        } else if (data.getAuditStatus() == 4) {
            //授信满额

        } else if (data.getAuditStatus() == 5) {
            //冻结中

        } else if (data.getAuditStatus() == 6) {
            //机审核失败

        } else if (data.getAuditStatus() == 7) {
            //驳回重填   审核被驳回
            PreferenceUtils.put(PreferenceUtils.USER_SETP, 5);
            cancelDisposable();
            animationStop();
            mDataBinding.ivRotateBg.setBackgroundResource(R.drawable.lines_recertification_fail);
            mDataBinding.ivRotate.setVisibility(View.GONE);
            mDataBinding.btnZhimaConfirm.setEnabled(true);
            mDataBinding.btnZhimaConfirm.setText(getResources().getString(R.string.again_certification));
            mDataBinding.btnZhimaConfirm.setVisibility(View.VISIBLE);
            mDataBinding.clAgree.setVisibility(View.INVISIBLE);
            mDataBinding.tvMyLines.setText(getResources().getString(R.string.lines_assess_refuse));
            mDataBinding.tvMyLinesDetails.setText(data.getAuditSuggest());
        } else if (data.getAuditStatus() == 13) { //失败
            PreferenceUtils.put(PreferenceUtils.USER_SETP, 5);
            cancelDisposable();
            animationStop();
            mDataBinding.ivRotateBg.setBackgroundResource(R.drawable.lines_fail);
            mDataBinding.ivRotate.setVisibility(View.GONE);
            mDataBinding.btnZhimaConfirm.setVisibility(View.GONE);
            mDataBinding.clAgree.setVisibility(View.INVISIBLE);
            mDataBinding.tvMyLines.setText(getResources().getString(R.string.lines_assess_failure));
            mDataBinding.tvMyLinesDetails.setText(getResources().getString(R.string.lines_assess_forbid_msg));
        } else if (data.getAuditStatus() == 8) {
            //重新提交
            PreferenceUtils.put(PreferenceUtils.USER_SETP, 3);
        } else if (data.getAuditStatus() == 9) {
            //失败用户解封  审核失败解封
            PreferenceUtils.put(PreferenceUtils.USER_SETP, 5);
            cancelDisposable();
            animationStop();
            mDataBinding.ivRotateBg.setBackgroundResource(R.drawable.lines_recertification_fail);
            mDataBinding.ivRotate.setVisibility(View.GONE);
            mDataBinding.btnZhimaConfirm.setEnabled(true);
            mDataBinding.btnZhimaConfirm.setText(getResources().getString(R.string.again_certification));
            mDataBinding.btnZhimaConfirm.setVisibility(View.VISIBLE);
            mDataBinding.clAgree.setVisibility(View.INVISIBLE);
            mDataBinding.tvMyLines.setText(getResources().getString(R.string.lines_assess_failure));
            mDataBinding.tvMyLinesDetails.setText(getResources().getString(R.string.fail_again_button_msg));
            if (materialDialog != null && materialDialog.isShowing()) {
                return;
            }
            materialDialog = new MaterialDialog.Builder(this)
                    .content(getResources().getString(R.string.lines_dialog_msg))
                    .negativeText(R.string.confirm)
                    .canceledOnTouchOutside(false)
                    .onNegative((dialog, which) -> materialDialog.dismiss()).show();
        } else if (data.getAuditStatus() == 11) {
            //逾期超25天不重审
            cancelDisposable();
            animationStop();
            PreferenceUtils.put(PreferenceUtils.USER_SETP, 5);
            mDataBinding.ivRotateBg.setBackgroundResource(R.drawable.lines_fail);
            mDataBinding.ivRotate.setVisibility(View.GONE);
            mDataBinding.btnZhimaConfirm.setVisibility(View.GONE);
            mDataBinding.clAgree.setVisibility(View.INVISIBLE);
            mDataBinding.tvMyLines.setVisibility(View.GONE);
            mDataBinding.tvMyLinesDetails.setText(getResources().getString(R.string.lines_assess_unqualified_msg));
        } else if (data.getAuditStatus() == 10) {
            //逾期7天重审
            cancelDisposable();
            animationStop();
            PreferenceUtils.put(PreferenceUtils.USER_SETP, 5);
            mDataBinding.ivRotateBg.setBackgroundResource(R.drawable.lines_retrial);
            mDataBinding.ivRotate.setVisibility(View.GONE);
            mDataBinding.btnZhimaConfirm.setEnabled(false);
            mDataBinding.btnZhimaConfirm.setText(getResources().getString(R.string.fail_again_button));
            mDataBinding.btnZhimaConfirm.setVisibility(View.VISIBLE);
            mDataBinding.clAgree.setVisibility(View.INVISIBLE);
            mDataBinding.tvMyLines.setVisibility(View.GONE);
            mDataBinding.tvMyLinesDetails.setText(getResources().getString(R.string.lines_retrial_msg));
        } else if (data.getAuditStatus() == 12) {
            //逾期7天解封
            cancelDisposable();
            animationStop();
            PreferenceUtils.put(PreferenceUtils.USER_SETP, 5);
            mDataBinding.ivRotateBg.setBackgroundResource(R.drawable.lines_retrial);
            mDataBinding.ivRotate.setVisibility(View.GONE);
            mDataBinding.btnZhimaConfirm.setEnabled(true);
            mDataBinding.btnZhimaConfirm.setText(getResources().getString(R.string.again_certification));
            mDataBinding.btnZhimaConfirm.setVisibility(View.VISIBLE);
            mDataBinding.clAgree.setVisibility(View.INVISIBLE);
            mDataBinding.tvMyLines.setVisibility(View.GONE);
            mDataBinding.tvMyLinesDetails.setText(getResources().getString(R.string.lines_retrial_msg));
            materialDialog = new MaterialDialog.Builder(this)
                    .content(getResources().getString(R.string.lines_dialog_msg))
                    .negativeText(R.string.confirm)
                    .canceledOnTouchOutside(false)
                    .onNegative((dialog, which) -> materialDialog.dismiss()).show();
        }
    }

    private void rollback() {
        mPresenter.rollbackStatus(PreferenceUtils.getString(PreferenceUtils.USER_SESSIONID, ""));
    }

    @Override
    public void rollbackStatusSuc(BaseBean baseBean) {
        if (DataResult.isSuccessUnToast(this, baseBean)) {
            PreferenceUtils.put(PreferenceUtils.USER_SETP, 1);
            PreferenceUtils.put(PreferenceUtils.ROLL_BACK, true);
//            JumpManager.jumpToClose(this, CertificationMainActivity.class);
            ARouter.getInstance().build(RouteConstant.CERTIFICATION_MAIN).navigation();finish();

        }
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


    private void animationStop() {
        if (rotateAnimation != null) {
            rotateAnimation.cancel();
        }
        mDataBinding.ivRotate.clearAnimation();
    }
}
