package com.mmkjflb.mmloan.viewmodel.changephone;

import android.text.TextUtils;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.mmkj.baselibrary.RouteConstant;
import com.mmkjflb.mmloan.R;
import com.mmkjflb.mmloan.app.Constants;
import com.mmkjflb.mmloan.base.BaseActivity;
import com.mmkjflb.mmloan.databinding.ActivityChangePhoneBinding;
import com.mmkjflb.mmloan.model.http.DataResult;
import com.mmkjflb.mmloan.viewmodel.login.LoginActivity;
import com.syhmmfqphl.xyxlibrary.utils.ExtraKeys;
import com.syhmmfqphl.xyxlibrary.utils.JumpManager;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.mmkj.baselibrary.annotation.ActivityFragmentInject;
import com.mmkj.baselibrary.app.ActivityStack;
import com.mmkj.baselibrary.model.entitry.BaseBean;
import com.mmkj.baselibrary.util.PreferenceUtils;
import com.mmkj.baselibrary.util.RxViewUtil;

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
 * Created by Administrator on 2018/7/17.
 */
@Route(path = RouteConstant.CHANGE_PHONE_TWO)
@ActivityFragmentInject(contentViewId = R.layout.activity_change_phone, toolbarTitle = R.string.change_bind_phonenumb)
public class ChangePhoneTwoActivity extends BaseActivity<ChangePhoneTwoPresenter, ActivityChangePhoneBinding> implements ChangePhoneTwoContract.View {
	private String account = "";
	private String imgCode = "";
	private String smsCode = "";

	@Override
	protected void initViews() {
		getActivityComponent().inject(this);
		listenEditTextEvents();
		initClicks();
		sendIvCode();
	}

	private void listenEditTextEvents() {
		Flowable<CharSequence> changePhoneObservable = RxTextView.textChanges(mDataBinding.etChangephone).toFlowable(BackpressureStrategy.LATEST);
		Flowable<CharSequence> imgCodeObservable = RxTextView.textChanges(mDataBinding.etImgcode).toFlowable(BackpressureStrategy.LATEST);
		Flowable<CharSequence> phoneCodeObservable = RxTextView.textChanges(mDataBinding.etChangephoneCode).toFlowable(BackpressureStrategy.LATEST);
		Flowable.combineLatest(changePhoneObservable, imgCodeObservable, phoneCodeObservable,
				(phonenumb, imgcode, smscode) -> {
					account = phonenumb.toString().trim();
					boolean phoneValid = !TextUtils.isEmpty(account) && account.length() >= 10;
					imgCode = imgcode.toString().trim();
					boolean ivCodeValid = !TextUtils.isEmpty(imgCode) && imgCode.length() == 4;
					smsCode = smscode.toString().trim();
					boolean smsCodeValid = !TextUtils.isEmpty(smsCode) && smsCode.length() >= 4;
					return phoneValid && ivCodeValid && smsCodeValid;
				}).subscribe(aBoolean -> {
			if (aBoolean) {
				mDataBinding.btnChangephone.setEnabled(true);
			} else {
				mDataBinding.btnChangephone.setEnabled(false);
			}
		}, throwable -> {
		});
	}


	private void initClicks() {
		RxViewUtil.clicks(mDataBinding.ivCode).subscribe(o -> sendIvCode());
		RxViewUtil.clicks(mDataBinding.tvChangephoneSendcode).subscribe(o -> sendCode());
		RxViewUtil.clicks(mDataBinding.btnChangephone).subscribe(o -> judgeChangePhone());

	}

	/**
	 * 更换手机号判断
	 */
	private void judgeChangePhone() {
		String input = mDataBinding.etImgcode.getText().toString().trim().toLowerCase();
		String code = mDataBinding.ivCode.getvCode().toLowerCase();
		if (!TextUtils.isEmpty(input) && input.equals(code)) {
			Map<String, Object> mapChangePhone = new HashMap<>();
			mapChangePhone.put("sessionId", PreferenceUtils.getString(PreferenceUtils.USER_SESSIONID, ""));
			mapChangePhone.put("mobile", account);
			mapChangePhone.put("captcha", smsCode);
			mPresenter.renewalMobilePhone(mapChangePhone);
		} else {
			toast(R.string.iv_verify_error);
		}
	}

	/**
	 * 更换图形验证码
	 */
	private void sendIvCode() {
		mDataBinding.etImgcode.setText("");
		mDataBinding.ivCode.refreshCode();
	}

	/**
	 * 发送短信验证码
	 */
	private void sendCode() {
		String account = mDataBinding.etChangephone.getText().toString().trim();
		if (TextUtils.isEmpty(account) || account.length() < 10) {
			toast(R.string.pls_input_right_phone);
			return;
		}
		if (PreferenceUtils.getString(PreferenceUtils.USER_PHONE, "").equals(account)) {
			toast(R.string.pls_input_diff_phone);
			return;
		}
		mPresenter.sendCode(account, ExtraKeys.CODETYPE_CHANGEPHONE);
	}

	@Override
	public void sendCodeSuc(BaseBean baseBean) {
		if (DataResult.isSuccessUnToast(ChangePhoneTwoActivity.this, baseBean)) {
			Observable.interval(0, 1, TimeUnit.SECONDS)
					.take(Constants.TIMECOUNT + 1)
					.map(aLong -> Constants.TIMECOUNT - aLong)
					.observeOn(AndroidSchedulers.mainThread())
					.doOnSubscribe(disposable -> {
						//进入倒计时
						mDataBinding.tvChangephoneSendcode.setEnabled(false);
					})
					.subscribe(new Observer<Long>() {
						@Override
						public void onSubscribe(Disposable d) {
						}

						@Override
						public void onNext(Long aLong) {
							mDataBinding.tvChangephoneSendcode.setText(aLong + "s");
						}

						@Override
						public void onError(Throwable e) {
						}

						@Override
						public void onComplete() {
							mDataBinding.tvChangephoneSendcode.setEnabled(true);
							mDataBinding.tvChangephoneSendcode.setText(R.string.get);
						}
					});
		}
	}

	@Override
	public void renewalMobilePhoneSuc(BaseBean baseBean) {
		if (DataResult.isSuccessToast(this, baseBean)) {
			ActivityStack.getInstance().removeAllExceptCurrent(this);
//			JumpManager.jumpToClose(this, LoginActivity.class);
			ARouter.getInstance().build(RouteConstant.LOGIN).navigation();finish();
		}
	}
}
