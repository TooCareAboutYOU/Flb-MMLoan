package com.mmkjflb.mmloan.viewmodel.register;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.InputType;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.TextAppearanceSpan;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.appevents.AppEventsLogger;
import com.jakewharton.rxbinding2.widget.RxCompoundButton;
import com.mmkj.baselibrary.RouteConstant;
import com.mmkj.baselibrary.annotation.ActivityFragmentInject;
import com.mmkj.baselibrary.model.entitry.BaseBean;
import com.mmkj.baselibrary.util.DeviceUtils;
import com.mmkj.baselibrary.util.EncryptUtils;
import com.mmkj.baselibrary.util.PreferenceUtils;
import com.mmkj.baselibrary.util.RSAHelper;
import com.mmkj.baselibrary.util.RxViewUtil;
import com.mmkj.baselibrary.util.StringUtils;
import com.mmkjflb.mmloan.R;
import com.mmkjflb.mmloan.app.Constants;
import com.mmkjflb.mmloan.base.BaseActivity;
import com.mmkjflb.mmloan.databinding.ActivityRegisterBinding;
import com.mmkjflb.mmloan.model.http.DataResult;
import com.mmkjflb.mmloan.utils.FirebaseUtils;
import com.syhmmfqphl.xyxlibrary.utils.ExtraKeys;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

import static com.jakewharton.rxbinding2.widget.RxTextView.textChanges;

/**
 * （注释添加完成）
 */
@Route(path = RouteConstant.REGISTER)
@ActivityFragmentInject(contentViewId = R.layout.activity_register, statusBarColor = R.color.black)
public class RegisterActivity extends BaseActivity<RegisterPresenter, ActivityRegisterBinding> implements RegisterContract.View {
    private String account = "";
    private String password = "";
    private boolean editEnable = false;
    private String imgCode = "";
    private String loginstyle1 = "Already have an account? ";
    private String loginstyle2 = "Sign In";
    private String smsCode = "";

    @Override
    protected void initViews() {
        getActivityComponent().inject(this);
        listenEditTextEvents();
        initClicks();
        showBoldTypeProtect();
        showBoldTypeProtect(loginstyle1, loginstyle2, mDataBinding.btnLogin);
    }

    private void showBoldTypeProtect() {
        mDataBinding.btnRegisterAgreement.setText(getSpan());
        mDataBinding.btnRegisterAgreement.setHighlightColor(Color.TRANSPARENT);//去掉点击效果
        mDataBinding.btnRegisterAgreement.setMovementMethod(LinkMovementMethod.getInstance());//这句话必须有，
    }

    private void showBoldTypeProtect(String protectstr1, String protectstr2, TextView textView) {
        //为TextView设置不同的字体大小和颜色
        SpannableString styledText = new SpannableString(protectstr1 + protectstr2);
        styledText.setSpan(new TextAppearanceSpan(this, R.style.TvSecurityBlackStyle), 0, protectstr1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        styledText.setSpan(new TextAppearanceSpan(this, R.style.TvSecurityOrangeStyle), protectstr1.length(), protectstr1.length() + protectstr2.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(styledText, TextView.BufferType.SPANNABLE);
    }

    private SpannableString getSpan() {
        final View.OnClickListener click1Listener = v -> {
//            JumpManager.jumpToKey1(RegisterActivity.this, WebViewActivity.class, Constants.PRIVATE_AGREEMENT);
            ARouter.getInstance().build(RouteConstant.WEBVIEW).withString("loadUrl", Constants.PRIVATE_AGREEMENT).navigation();

        };
        final View.OnClickListener click2Listener = v -> {
//            JumpManager.jumpToKey1(RegisterActivity.this, WebViewActivity.class, Constants.SERVICE_AGREEMENT);
            ARouter.getInstance().build(RouteConstant.WEBVIEW).withString("loadUrl", Constants.SERVICE_AGREEMENT).navigation();

        };
        String one = "By clicking \"Sign Up\", you agree to our ";
        String two = "User's Privacy Policy";
        String and = " and ";
        String three = "Service Agreement";
        SpannableString spnableInfo = new SpannableString(one + two + and + three);
        spnableInfo.setSpan(new ForegroundColorSpan(Color.parseColor("#333333")), 0, one.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spnableInfo.setSpan(new ClickableSpan() {
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);//去掉下划线
                ds.setColor(Color.parseColor("#2FC4CA"));
            }

            @Override
            public void onClick(View widget) {
                click1Listener.onClick(widget);
            }
        }, one.length(), one.length() + two.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spnableInfo.setSpan(new ForegroundColorSpan(Color.parseColor("#333333")), one.length() + two.length(), one.length() + two.length() + and.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spnableInfo.setSpan(new ClickableSpan() {
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
                ds.setColor(Color.parseColor("#2FC4CA"));
            }

            @Override
            public void onClick(View widget) {
                click2Listener.onClick(widget);
            }
        }, one.length() + two.length() + and.length(), one.length() + two.length() + and.length() + three.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spnableInfo;
    }

    @SuppressLint("CheckResult")
    private void listenEditTextEvents() {
//        textChanges(mDataBinding.etRegiseterAccount).subscribe(charSequence -> {
//            String mobile = String.valueOf(charSequence);
//            mDataBinding.ivCode.setVisibility(View.GONE);//先设置图形验证码不可见，直到手机号满足要求才可见
//            sendIvCode(mobile);
//        });

        Flowable<CharSequence> registerAccountObservable = textChanges(mDataBinding.etRegiseterAccount).toFlowable(BackpressureStrategy.LATEST);
        Flowable<CharSequence> registerImgCodeObservable = textChanges(mDataBinding.etImgcode).toFlowable(BackpressureStrategy.LATEST);
        Flowable<CharSequence> registerCodeObservable = textChanges(mDataBinding.etRegisterCode).toFlowable(BackpressureStrategy.LATEST);
        Flowable<CharSequence> registerPasswordObservable = textChanges(mDataBinding.etRegisterPassword).skip(6).toFlowable(BackpressureStrategy.LATEST);
        Flowable.combineLatest(registerAccountObservable, registerImgCodeObservable, registerCodeObservable, registerPasswordObservable,
                (phonenumb, imgcode, smscode, passwords) -> {
                    account = phonenumb.toString().trim();
                    boolean phoneValid = !TextUtils.isEmpty(account) && account.length() >= 10;
                    imgCode = imgcode.toString().trim();
                    boolean ivCodeValid = !TextUtils.isEmpty(imgCode) && imgCode.length() == 4;
                    smsCode = smscode.toString().trim();
                    boolean smsCodeValid = !TextUtils.isEmpty(smsCode) && smsCode.length() >= 4;
                    password = passwords.toString().trim();
                    boolean passwordValid = !TextUtils.isEmpty(password) && password.length() >= 6;
                    editEnable = phoneValid && ivCodeValid && smsCodeValid && passwordValid;
                    return editEnable;
                }).subscribe(aBoolean -> {
            if (aBoolean) {
                mDataBinding.btnRegister.setEnabled(true);
            } else {
                mDataBinding.btnRegister.setEnabled(false);
            }
        }, throwable -> {
        });
        RxCompoundButton.checkedChanges(mDataBinding.ckRegisterShowpwd).subscribe(aBoolean -> {
            if (aBoolean) {
                mDataBinding.etRegisterPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            } else {
                mDataBinding.etRegisterPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            }
            mDataBinding.etRegisterPassword.setTypeface(Typeface.DEFAULT);
            Editable editable = mDataBinding.etRegisterPassword.getText();
            mDataBinding.etRegisterPassword.setSelection(editable.length());
        });
    }


    @SuppressLint("CheckResult")
    private void initClicks() {
        RxViewUtil.clicks(mDataBinding.ivCode).subscribe(o -> {
            String mobile = Objects.requireNonNull(mDataBinding.etRegiseterAccount.getText()).toString().trim().toLowerCase();
            sendIvCode(mobile);
        });
        RxViewUtil.clicks(mDataBinding.btnRegisterSendcode).subscribe(o -> {
            String mobile = Objects.requireNonNull(mDataBinding.etRegiseterAccount.getText()).toString().trim().toLowerCase();
            boolean condition1 = (mobile.length() == 11) && mobile.substring(0, 2).equals("09");
            boolean condition2 = (mobile.length() == 10) && mobile.substring(0, 1).equals("9");
            if (condition1) {
                sendCode();
            } else if (condition2) {
                sendCode();
            } else {
//                Toast.makeText(this, "please enter the phone number with 09 or 9 in front!", Toast.LENGTH_SHORT).show();
                toast("please enter the phone number with 09 or 9 in front!");
            }
        });
        RxViewUtil.clicks(mDataBinding.btnRegister).subscribe(o -> {
            String mobile = Objects.requireNonNull(mDataBinding.etRegiseterAccount.getText()).toString().trim().toLowerCase();
            boolean condition1 = (mobile.length() == 11) && mobile.substring(0, 2).equals("09");
            boolean condition2 = (mobile.length() == 10) && mobile.substring(0, 1).equals("9");
            if (condition1) {
                judgeRegister();
            } else if (condition2) {
                judgeRegister();
            } else {
//                Toast.makeText(this, "please enter the phone number with 09 or 9 in front!", Toast.LENGTH_SHORT).show();
                toast("please enter the phone number with 09 or 9 in front!");
            }
        });
        RxViewUtil.clicks(mDataBinding.btnLogin).subscribe(o -> finish());
    }

    private void judgeRegister() {
        String input = mDataBinding.etImgcode.getText().toString().trim().toLowerCase();
        if (!TextUtils.isEmpty(input)) {
            String referrer = PreferenceUtils.getString(PreferenceUtils.REFERRER, "");
            String installReferrer = PreferenceUtils.getString(PreferenceUtils.INSTALL_REFERRER, "");
            String usedReferrer = (!TextUtils.isEmpty(referrer)) ? referrer : installReferrer;
            String click_id = "";
            if (!TextUtils.isEmpty(usedReferrer)) {
                Map<String, String> referrerMap = StringUtils.decodeUrlParameters(usedReferrer);
                click_id = referrerMap != null ? referrerMap.get("utm_source") : null;
                if (click_id == null) {
                    click_id = "";
                }
            }
            Map<String, Object> mapRegister = new HashMap<>();
            mapRegister.put("mobile", account);
            mapRegister.put("password", RSAHelper.getRSAData(password));
            mapRegister.put("captcha", smsCode);
            mapRegister.put("mStr", EncryptUtils.md5(click_id, Constants.MD5_KEY));
            mapRegister.put("referrer", usedReferrer);
            mapRegister.put("source", DeviceUtils.getAppName(this) + "&" + DeviceUtils.getPackageName(this) + "&AndroidGoogle");
            mapRegister.put("channelId", PreferenceUtils.getInt(PreferenceUtils.CHANNELNAME, 0));
            mPresenter.register(mapRegister);
        } else {
            toast(R.string.iv_verify_error);
        }
    }

    private void sendIvCode(String mobile) {
        if (!TextUtils.isEmpty(mobile) && mobile.length() >= 10) {
            mDataBinding.ivCode.setVisibility(View.VISIBLE);
            mDataBinding.etImgcode.setText("");
            mPresenter.vCode(mobile);
        }
    }

    private void sendCode() {
        String account = mDataBinding.etRegiseterAccount.getText().toString().trim();
        if (TextUtils.isEmpty(account) || account.length() < 10) {
            toast(R.string.pls_input_right_phone);
            return;
        }
        if (mDataBinding.ivCode.getVisibility() == View.GONE) {
            toast(R.string.pls_input_right_phone);
            return;
        }
        mPresenter.sendCode(account, ExtraKeys.CODETYPE_REGISTER);
        sendIvCode(account);
    }

    @Override
    public void sendCodeSuc(BaseBean baseBean) {
        if (DataResult.isSuccessUnToast(RegisterActivity.this, baseBean)) {
            Observable.interval(0, 1, TimeUnit.SECONDS)
                    .take(Constants.TIMECOUNT + 1)
                    .map(aLong -> Constants.TIMECOUNT - aLong)
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe(disposable -> {
                        //进入倒计时
                        mDataBinding.btnRegisterSendcode.setEnabled(false);
                    })
                    .subscribe(new Observer<Long>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                        }

                        @Override
                        public void onNext(Long aLong) {
                            mDataBinding.btnRegisterSendcode.setText(aLong + "s");
                        }

                        @Override
                        public void onError(Throwable e) {
                        }

                        @Override
                        public void onComplete() {
                            mDataBinding.btnRegisterSendcode.setEnabled(true);
                            mDataBinding.btnRegisterSendcode.setText(R.string.get);
                        }
                    });
        }
    }

    @Override
    public void registerSuc(BaseBean baseBean) {
        if (DataResult.isSuccessToast(RegisterActivity.this, baseBean)) {
            FirebaseUtils.RegisterEvent(this, account);
            /**
             * 注册埋点
             */
            AppEventsLogger.newLogger(this).logEvent(AppEventsConstants.EVENT_NAME_COMPLETED_REGISTRATION);

            PreferenceUtils.put(PreferenceUtils.USER_PHONE, account);
            Intent intent = getIntent();
            intent.putExtra(ExtraKeys.Key_Msg1, account);
            intent.putExtra(ExtraKeys.Key_Msg2, password);
            setResult(RESULT_OK, intent);
            RegisterActivity.this.finish();
        }
    }

    @Override
    public void showVCode(Bitmap bitmap) {
        mDataBinding.ivCode.setImageBitmap(bitmap);
    }
}
