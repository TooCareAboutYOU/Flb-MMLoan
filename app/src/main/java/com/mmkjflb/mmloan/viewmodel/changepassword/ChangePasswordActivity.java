package com.mmkjflb.mmloan.viewmodel.changepassword;

import android.text.TextUtils;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.mmkjflb.mmloan.R;
import com.mmkjflb.mmloan.base.BaseActivity;
import com.mmkjflb.mmloan.databinding.ActivityChangePasswordBinding;
import com.mmkjflb.mmloan.model.http.DataResult;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.mmkj.baselibrary.RouteConstant;
import com.mmkj.baselibrary.annotation.ActivityFragmentInject;
import com.mmkj.baselibrary.model.entitry.BaseBean;
import com.mmkj.baselibrary.util.PreferenceUtils;
import com.mmkj.baselibrary.util.RSAHelper;
import com.mmkj.baselibrary.util.RxViewUtil;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;

/**
 * Created by Administrator on 2017/10/17.
 */
@Route(path = RouteConstant.CHANGE_PASSWORD)
@ActivityFragmentInject(contentViewId = R.layout.activity_change_password, toolbarTitle = R.string.change_password)
public class ChangePasswordActivity extends BaseActivity<ChangePasswordPresenter, ActivityChangePasswordBinding> implements ChangePasswordContract.View {
	private String oldPwdStr="";
	private String newPwdStr="";
	private String reNewPwdStr="";

	@Override
	protected void initViews() {
		getActivityComponent().inject(this);
		listenEditTextEvents();
		initClicks();

	}

	private void initClicks() {
		RxViewUtil.clicks(mDataBinding.btnSubmit).subscribe(o -> {
			Map<String, String> params = new HashMap<String, String>();
			params.put("sessionId", PreferenceUtils.getString(PreferenceUtils.USER_SESSIONID, ""));
			params.put("oldPwd", RSAHelper.getRSAData(oldPwdStr));
			params.put("newPwd", RSAHelper.getRSAData(newPwdStr));
			params.put("reNewPwd", RSAHelper.getRSAData(reNewPwdStr));
			mPresenter.updatePwd(params);
		});
	}

	private void listenEditTextEvents() {
		Flowable<CharSequence> oldPwdObservable = RxTextView.textChanges(mDataBinding.etBeforePwd).skip(6).toFlowable(BackpressureStrategy.LATEST);
		Flowable<CharSequence> newPwdObservable = RxTextView.textChanges(mDataBinding.etNewPwd).skip(6).toFlowable(BackpressureStrategy.LATEST);
		Flowable<CharSequence> reNewPwdObservable = RxTextView.textChanges(mDataBinding.etConfirmNewPwd).skip(6).toFlowable(BackpressureStrategy.LATEST);
		Flowable.combineLatest(oldPwdObservable, newPwdObservable, reNewPwdObservable,
				(charSequence1, charSequence2, charSequence3) -> {
					oldPwdStr = mDataBinding.etBeforePwd.getText().toString().trim();
					boolean oldPwdValid = !TextUtils.isEmpty(oldPwdStr) && oldPwdStr.length() >= 6 && oldPwdStr.length() <= 12;
					newPwdStr = mDataBinding.etNewPwd.getText().toString().trim();
					boolean newPwdValid = !TextUtils.isEmpty(newPwdStr) && newPwdStr.length() >= 6 && newPwdStr.length() <= 12;
					reNewPwdStr = mDataBinding.etConfirmNewPwd.getText().toString().trim();
					boolean reNewPwdValid = !TextUtils.isEmpty(reNewPwdStr) && reNewPwdStr.length() >= 6 && reNewPwdStr.length() <= 12;
					return oldPwdValid && newPwdValid && reNewPwdValid;
				}).subscribe(aBoolean -> {
			if (aBoolean) {
				mDataBinding.btnSubmit.setEnabled(true);
			} else {
				mDataBinding.btnSubmit.setEnabled(false);
			}
		}, throwable -> {

		});
	}
	@Override
	public void updatePwdSuc(BaseBean baseBean) {
		if (DataResult.isSuccessUnToast(ChangePasswordActivity.this, baseBean)) {
			finish();
		}
	}
}
