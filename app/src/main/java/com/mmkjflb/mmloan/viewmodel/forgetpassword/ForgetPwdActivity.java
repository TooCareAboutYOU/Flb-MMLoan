package com.mmkjflb.mmloan.viewmodel.forgetpassword;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jakewharton.rxbinding2.widget.RxCompoundButton;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.mmkj.baselibrary.RouteConstant;
import com.mmkj.baselibrary.annotation.ActivityFragmentInject;
import com.mmkj.baselibrary.model.entitry.BaseBean;
import com.mmkj.baselibrary.util.RSAHelper;
import com.mmkj.baselibrary.util.RxViewUtil;
import com.mmkjflb.mmloan.R;
import com.mmkjflb.mmloan.app.Constants;
import com.mmkjflb.mmloan.base.BaseActivity;
import com.mmkjflb.mmloan.databinding.ActivityForgetpasswordBinding;
import com.mmkjflb.mmloan.model.http.DataResult;
import com.syhmmfqphl.xyxlibrary.utils.ExtraKeys;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * 忘记密码
 * Created by Administrator on 2017/8/10.
 */
@Route(path = RouteConstant.FORGET_PASSWORD)
@ActivityFragmentInject(contentViewId = R.layout.activity_forgetpassword, toolbarTitle = R.string.forget_pwd, statusBarColor = R.color.white, toolbarBgColor = R.color.white)
public class ForgetPwdActivity extends BaseActivity<ForgetPwdPresenter, ActivityForgetpasswordBinding> implements ForgetPwdContract.View {
    private String account = "";
    private String smscode = "";
    private String password = "";

    @Override
    protected void initViews() {
        getActivityComponent().inject(this);
        listenEditTextEvents();
        initClicks();
//        sendIvCode();
    }

    private void sendIvCode(String mobile) {
        if (!TextUtils.isEmpty(mobile) && mobile.length() == 11) {
            mDataBinding.ivCode.setVisibility(View.VISIBLE);
            mDataBinding.etImgcode.setText("");
            mPresenter.vCode(mobile);
        }
    }

    private static final String TAG = "showVCode";

    @SuppressLint("CheckResult")
    private void initClicks() {
		RxViewUtil.clicks(mDataBinding.ivCode).subscribe(o -> {
			String mobile = mDataBinding.etForgetpwdAccount.getText().toString().trim().toLowerCase();
			sendIvCode(mobile);
		});
		RxViewUtil.clicks(mDataBinding.tvForgetpwdSendcode).subscribe(o -> sendCode());
		//密码显示与隐藏
		mDataBinding.ckShowPassword.setOnCheckedChangeListener((btn, bool) -> {
			if (btn.isChecked()) {
				mDataBinding.etForgetpwdPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
			} else {
				mDataBinding.etForgetpwdPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
			}
            mDataBinding.etForgetpwdPassword.setTypeface(Typeface.DEFAULT);
            Editable editable = mDataBinding.etForgetpwdPassword.getText();
			mDataBinding.etForgetpwdPassword.setSelection(editable.length());
		});
		RxViewUtil.clicks(mDataBinding.btnForgetpwdConfirm).subscribe(o -> judgeForgetPwd());
    }

    private void judgeForgetPwd() {
        String input = mDataBinding.etImgcode.getText().toString().trim().toLowerCase();
        if (!TextUtils.isEmpty(input)) {
            Map<String, String> params = new HashMap<>();
            params.put("mobile", account);
            params.put("captcha", smscode);
            params.put("password", RSAHelper.getRSAData(password));
            mPresenter.forgotPwd(params);
        } else {
            toast(R.string.iv_verify_error);
        }
    }

    private void sendCode() {
        String account = mDataBinding.etForgetpwdAccount.getText().toString().trim();
        if (TextUtils.isEmpty(account) || account.length() < 10) {
            toast(R.string.pls_input_right_phone);
            return;
        }
        mPresenter.sendCode(account, ExtraKeys.CODETYPE_FROGETPWD);
    }

    @Override
    public void sendCodeSuc(BaseBean baseBean) {
        if (DataResult.isSuccessUnToast(ForgetPwdActivity.this, baseBean)) {
            Observable.interval(0, 1, TimeUnit.SECONDS)
                    .take(Constants.TIMECOUNT + 1)
					.map(aLong -> Constants.TIMECOUNT - aLong)
                    .observeOn(AndroidSchedulers.mainThread())
					.doOnSubscribe(disposable -> {
						//进入倒计时
						mDataBinding.tvForgetpwdSendcode.setEnabled(false);
					})
                    .subscribe(new Observer<Long>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                        }

                        @Override
                        public void onNext(Long aLong) {
                            mDataBinding.tvForgetpwdSendcode.setText(aLong + "s");
                        }

                        @Override
                        public void onError(Throwable e) {
                        }

                        @Override
                        public void onComplete() {
                            mDataBinding.tvForgetpwdSendcode.setEnabled(true);
                            mDataBinding.tvForgetpwdSendcode.setText(R.string.get);
                        }
                    });
        }
    }

    @Override
    public void forgotPwdSuc(BaseBean baseBean) {
        if (DataResult.isSuccessToast(ForgetPwdActivity.this, baseBean)) {
            ForgetPwdActivity.this.finish();
        }
    }

    @Override
    public void showVCode(Bitmap bitmap) {
        mDataBinding.ivCode.setImageBitmap(bitmap);
    }

    @SuppressLint("CheckResult")
    private void listenEditTextEvents() {
		RxTextView.textChanges(mDataBinding.etForgetpwdAccount).subscribe(charSequence -> {
			String mobile = String.valueOf(charSequence);
			mDataBinding.ivCode.setVisibility(View.GONE);
			sendIvCode(mobile);
		});


        Flowable<CharSequence> forgetpwdAccountObservable = RxTextView.textChanges(mDataBinding.etForgetpwdAccount).toFlowable(BackpressureStrategy.LATEST);
        Flowable<CharSequence> forgetpwdCodeObservable = RxTextView.textChanges(mDataBinding.etForgetpwdCode).toFlowable(BackpressureStrategy.LATEST);
        Flowable<CharSequence> forgetpwdPasswordObservable = RxTextView.textChanges(mDataBinding.etForgetpwdPassword).skip(6).toFlowable(BackpressureStrategy.LATEST);
        Flowable.combineLatest(forgetpwdAccountObservable, forgetpwdCodeObservable, forgetpwdPasswordObservable,
				(charSequence1, charSequence2, charSequence3) -> {
					account = mDataBinding.etForgetpwdAccount.getText().toString().trim();
					boolean phoneValid = !TextUtils.isEmpty(account) && account.length() == 11;
					smscode = mDataBinding.etForgetpwdCode.getText().toString().trim();
					boolean codeValid = !TextUtils.isEmpty(smscode) && smscode.length() >= 4;
					password = mDataBinding.etForgetpwdPassword.getText().toString().trim();
					boolean passwordValid = !TextUtils.isEmpty(password) && password.length() >= 6;
					return phoneValid && codeValid && passwordValid;
				}).subscribe(aBoolean -> {
			if (aBoolean) {
				mDataBinding.btnForgetpwdConfirm.setEnabled(true);
			} else {
				mDataBinding.btnForgetpwdConfirm.setEnabled(false);
			}
		}, throwable -> {

		});
		RxCompoundButton.checkedChanges(mDataBinding.ckShowPassword).subscribe(aBoolean -> {
			if (aBoolean) {
				mDataBinding.etForgetpwdPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
			} else {
				mDataBinding.etForgetpwdPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
			}
			Editable editable = mDataBinding.etForgetpwdPassword.getText();
			mDataBinding.etForgetpwdPassword.setSelection(editable.length());

		});
    }
}
