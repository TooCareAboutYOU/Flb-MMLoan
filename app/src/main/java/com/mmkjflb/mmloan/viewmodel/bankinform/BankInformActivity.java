package com.mmkjflb.mmloan.viewmodel.bankinform;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.text.TextUtils;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.bigkoo.pickerview.OptionsPickerView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.mmkj.baselibrary.RouteConstant;
import com.mmkj.baselibrary.annotation.ActivityFragmentInject;
import com.mmkj.baselibrary.model.entitry.BaseBean;
import com.mmkj.baselibrary.util.PreferenceUtils;
import com.mmkj.baselibrary.util.RxViewUtil;
import com.mmkjflb.mmloan.R;
import com.mmkjflb.mmloan.base.BaseActivity;
import com.mmkjflb.mmloan.databinding.ActivityBankInformBinding;
import com.mmkjflb.mmloan.model.entity.BankInfoEntity;
import com.mmkjflb.mmloan.model.entity.BankListEntity;
import com.mmkjflb.mmloan.model.entity.QueryRefundInfoEntity;
import com.mmkjflb.mmloan.model.http.DataResult;
import com.mmkjflb.mmloan.utils.UiUtils;
import com.syhmmfqphl.xyxlibrary.utils.ExtraKeys;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;

/**
 * date: 2018/3/1 17:36
 * author: xuyexiang
 * title:
 */
@Route(path = RouteConstant.BANK_INFORM)
@ActivityFragmentInject(contentViewId = R.layout.activity_bank_inform)
public class BankInformActivity extends BaseActivity<BankInformPresenter, ActivityBankInformBinding> implements BankInformContract.View {
	private OptionsPickerView bankPickerView;
	private List<BankListEntity> bankListEntityList = null;
	private String bankName = "";
	private String bankCode = "";
	private QueryRefundInfoEntity bankCardsBean = null;

	@Autowired
	public boolean fromBankList;

	@Autowired
	public QueryRefundInfoEntity account;


	@Override
	protected void initViews() {
		getActivityComponent().inject(this);
		String userName = PreferenceUtils.getString(PreferenceUtils.USER_REALNAME, "");
		mDataBinding.etUsername.setEnabled(userName.length()==0);
		mDataBinding.etUsername.setText(userName);
//		bankCardsBean = getIntent().getParcelableExtra(ExtraKeys.Key_Msg1);
//		fromBankList = getIntent().getBooleanExtra(ExtraKeys.Key_Msg1,false);
		if (bankCardsBean != null) {
			mDataBinding.etUsername.setEnabled(true);
			mDataBinding.etReservedPhonenumb.setText(bankCardsBean.getBankReservedPhone());
			mDataBinding.etBanknumb.setText(bankCardsBean.getAccount());
			bankName = bankCardsBean.getBankName();
			bankCode = bankCardsBean.getBankCode();
			mDataBinding.tvBankname.setText(bankName);
			mDataBinding.btnNext.setText(R.string.finish);
			setToolBar(getResources().getString(R.string.change_bankinform));
		} else {
			setToolBar(getResources().getString(R.string.bank_inform));
			mPresenter.getBankInfo(PreferenceUtils.getString(PreferenceUtils.USER_SESSIONID, ""));
		}
		initClicks();
		listenEditTextEvents();
	}

	@SuppressLint("CheckResult")
	private void initClicks() {
		RxViewUtil.clicks(mDataBinding.tvBankname).subscribe(o -> {
			if (bankListEntityList == null) {
				mPresenter.bankList(PreferenceUtils.getUserSessionid());
			} else {
				bankPickerView.show(mDataBinding.tvBankname);
			}
		});
		RxViewUtil.clicks(mDataBinding.btnNext).subscribe(o -> {
			Map<String, Object> map = new HashMap<>();
			map.put("sessionId", PreferenceUtils.getUserSessionid());
			map.put("accountName", mDataBinding.etUsername.getText().toString().trim());
			map.put("account", mDataBinding.etBanknumb.getText().toString().trim());
			map.put("bankReservedPhone", mDataBinding.etReservedPhonenumb.getText().toString().trim());
			map.put("bankName", bankName);
			map.put("bankCode", bankCode);
			mPresenter.bankinfo(map);
		});
	}

	@SuppressLint("CheckResult")
	private void listenEditTextEvents() {                              //.skip(1) 这里不能设置会出现按钮不可点击
		Flowable<CharSequence> usernameObservable = RxTextView.textChanges(mDataBinding.etUsername).toFlowable(BackpressureStrategy.LATEST);
		Flowable<CharSequence> banknumbObservable = RxTextView.textChanges(mDataBinding.etBanknumb).toFlowable(BackpressureStrategy.LATEST);
		Flowable<CharSequence> banknameObservable = RxTextView.textChanges(mDataBinding.tvBankname).toFlowable(BackpressureStrategy.LATEST);
		Flowable<CharSequence> reservedPhoneObservable = RxTextView.textChanges(mDataBinding.etReservedPhonenumb).toFlowable(BackpressureStrategy.LATEST);
		Flowable.combineLatest(usernameObservable, banknumbObservable, banknameObservable, reservedPhoneObservable,
				(username, banknumb, bankname, reservedPhone) ->
						!TextUtils.isEmpty(username.toString().trim())
						&& !TextUtils.isEmpty(bankname.toString().trim())
						&& !TextUtils.isEmpty(reservedPhone.toString().trim())
		).subscribe(aBoolean -> {
			if (aBoolean) {
				mDataBinding.btnNext.setEnabled(true);
			} else {
				mDataBinding.btnNext.setEnabled(false);
			}
		}, throwable -> {

		});
	}


	@Override
	public void bankListSuc(BaseBean<List<BankListEntity>> baseBean) {
		if (DataResult.isSuccessUnToast(this, baseBean)) {
			bankListEntityList = baseBean.getData();
			bankPickerView = UiUtils.initOptionPicker(this, (options1, options2, options3, v) -> {
				bankName = bankListEntityList.get(options1).getPickerViewText();
				bankCode = bankListEntityList.get(options1).getBankCode();
				mDataBinding.tvBankname.setText(bankName);
			}, R.string.sendcard_bank);
			bankPickerView.setPicker(bankListEntityList);
			bankPickerView.show(mDataBinding.tvBankname);
		}
	}

	@Override
	public void bankinfoSuc(BaseBean baseBean) {
		if (DataResult.isSuccessUnToast(this, baseBean)) {
			if (bankCardsBean != null) {
				PreferenceUtils.put(PreferenceUtils.USER_REALNAME, mDataBinding.etUsername.getText().toString().trim());
				bankCardsBean.setAccountName(mDataBinding.etUsername.getText().toString().trim());
				bankCardsBean.setAccount(mDataBinding.etBanknumb.getText().toString().trim());
				bankCardsBean.setBankReservedPhone(mDataBinding.etReservedPhonenumb.getText().toString().trim());
				bankCardsBean.setBankName(bankName);
				bankCardsBean.setBankCode(bankCode);
				Intent intent = getIntent();
				intent.putExtra(ExtraKeys.Key_Msg1, bankCardsBean);
				setResult(RESULT_OK, intent);

			} else {
//				RxBus.get().post(EventConstants.CERTIFICATEMAIN_EVENT, 1);
				PreferenceUtils.putBoolean(PreferenceUtils.JUMPTHREE, true);
				if (fromBankList){
					Intent intent = getIntent();
					setResult(RESULT_OK, intent);
				}
			}
			finish();
		}
	}

	@Override
	public void getBankInfoSuc(BaseBean<BankInfoEntity> baseBean) {
		if (DataResult.isSuccessUnToast(this, baseBean)) {
			BankInfoEntity bankInfoEntity = baseBean.getData();
			if (bankInfoEntity != null){
				mDataBinding.etBanknumb.setText(bankInfoEntity.getAccount());
				mDataBinding.etReservedPhonenumb.setText(bankInfoEntity.getBankReservedPhone());
				bankName=bankInfoEntity.getBankName();
				mDataBinding.tvBankname.setText(bankName);
				bankCode=bankInfoEntity.getBankCode();
			}
		}
	}
}
