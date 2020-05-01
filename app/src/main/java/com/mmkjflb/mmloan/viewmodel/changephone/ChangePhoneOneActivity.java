package com.mmkjflb.mmloan.viewmodel.changephone;

import android.text.TextUtils;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.mmkjflb.mmloan.R;
import com.mmkjflb.mmloan.base.BaseActivity;
import com.mmkjflb.mmloan.databinding.ActivityChangephoneOneBinding;
import com.mmkjflb.mmloan.model.http.DataResult;
import com.syhmmfqphl.xyxlibrary.utils.JumpManager;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.mmkj.baselibrary.RouteConstant;
import com.mmkj.baselibrary.annotation.ActivityFragmentInject;
import com.mmkj.baselibrary.model.entitry.BaseBean;
import com.mmkj.baselibrary.util.PreferenceUtils;
import com.mmkj.baselibrary.util.RxViewUtil;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;

/**
 * Created by Administrator on 2018/7/17.
 */
@Route(path = RouteConstant.CHANGE_PHONE_ONE)
@ActivityFragmentInject(contentViewId = R.layout.activity_changephone_one, toolbarTitle = R.string.change_bind_phonenumb)
public class ChangePhoneOneActivity extends BaseActivity<ChangePhoneOnePresenter, ActivityChangephoneOneBinding> implements ChangePhoneOneContract.View {
	private String idCard = "";
	private String userName = "";
	private String phoneNumb = "";

	@Override
	protected void initViews() {
		getActivityComponent().inject(this);
		listenEditTextEvents();
		initClicks();
	}

	private void listenEditTextEvents() {
		Flowable<CharSequence> idCardObservable = RxTextView.textChanges(mDataBinding.etIdcard).toFlowable(BackpressureStrategy.LATEST);
		Flowable<CharSequence> userNameObservable = RxTextView.textChanges(mDataBinding.etUsername).toFlowable(BackpressureStrategy.LATEST);
		Flowable<CharSequence> phoneNumbObservable = RxTextView.textChanges(mDataBinding.etPhonenumb).toFlowable(BackpressureStrategy.LATEST);
		Flowable.combineLatest(idCardObservable, userNameObservable, phoneNumbObservable,
				(idcard, username, phonenumb) -> {
					idCard = idcard.toString().trim();
					userName = username.toString().trim();
					phoneNumb = phonenumb.toString().trim();
					return !TextUtils.isEmpty(idCard) && !TextUtils.isEmpty(userName) && !TextUtils.isEmpty(phoneNumb) && phoneNumb.length() >= 10;
				}).subscribe(aBoolean -> {
			if (aBoolean) {
				mDataBinding.btnChangephoneOne.setEnabled(true);
			} else {
				mDataBinding.btnChangephoneOne.setEnabled(false);
			}
		}, throwable -> {
		});
	}

	private void initClicks() {
		RxViewUtil.clicks(mDataBinding.btnChangephoneOne).subscribe(o -> judgeChangePhoneOne());
	}

	/**
	 * 更换手机号判断
	 */
	private void judgeChangePhoneOne() {
		Map<String, Object> mapChangePhone = new HashMap<>();
		mapChangePhone.put("sessionId", PreferenceUtils.getString(PreferenceUtils.USER_SESSIONID, ""));
		mapChangePhone.put("idCard", idCard);
		mapChangePhone.put("userName", userName);
		mapChangePhone.put("mobilePhone", phoneNumb);
		mPresenter.checkUserInfo(mapChangePhone);
	}

	@Override
	public void checkUserInfoSuc(BaseBean baseBean) {
		if (DataResult.isSuccessUnToast(ChangePhoneOneActivity.this, baseBean)) {
//			JumpManager.jumpTo(ChangePhoneOneActivity.this, ChangePhoneTwoActivity.class);
			ARouter.getInstance().build(RouteConstant.CHANGE_PHONE_TWO).navigation();

		}
	}
}
