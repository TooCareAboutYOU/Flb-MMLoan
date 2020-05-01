package com.mmkj.usercenter.viewmodel.usercenter;

import android.text.TextUtils;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.mmkj.baselibrary.RouteConstant;
import com.mmkj.baselibrary.annotation.HeadToolbar;
import com.mmkj.baselibrary.annotation.HeaderBuilder;
import com.mmkj.baselibrary.model.entitry.BaseBean;
import com.mmkj.baselibrary.model.entitry.GCashDetailBean;
import com.mmkj.baselibrary.model.http.DataResult;
import com.mmkj.baselibrary.util.PreferenceUtils;
import com.mmkj.baselibrary.util.RxViewUtil;
import com.mmkj.baselibrary.util.StringUtils;
import com.mmkj.usercenter.R;
import com.mmkj.usercenter.databinding.ActivityUserCenterBinding;
import com.mmkj.usercenter.viewmodel.BaseUserSettingActivity;


@Route(path = RouteConstant.USER_CENTER)
public class UserCenterActivity extends BaseUserSettingActivity<UsercenterPresenter, ActivityUserCenterBinding> implements UserCenterContract.View {

	@Override
	protected HeadToolbar getHeadToolbar() {
		return new HeaderBuilder().mContentViewId(R.layout.activity_user_center).statusBarColor(R.color.black).build();
	}


	@Override
	protected void initViews() {
		getActivityComponent().inject(this);
		mDataBinding.setPhone(StringUtils.secureShow(PreferenceUtils.getString(PreferenceUtils.USER_PHONE, ""), 3, 4, 4));
		initClicks();

	}

	@Override
	protected void onResume() {
		super.onResume();
		mPresenter.getGCashDetail(PreferenceUtils.getUserSessionid());
	}

	private void initClicks() {
        RxViewUtil.clicks(mDataBinding.tvMyloan).subscribe(o -> ARouter.getInstance().build(RouteConstant.LOAN_LIST).navigation());
		RxViewUtil.clicks(mDataBinding.tvMyrepay).subscribe(o-> ARouter.getInstance().build(RouteConstant.REPAY_LIST).navigation());
		RxViewUtil.clicks(mDataBinding.tvCustomerService).subscribe(o->ARouter.getInstance().build(RouteConstant.CUSTOMER_SERVICE).navigation());
		RxViewUtil.clicks(mDataBinding.tvSetting).subscribe(o -> ARouter.getInstance().build(RouteConstant.SETTING).navigation());
		RxViewUtil.clicks(mDataBinding.tvCredit).subscribe(o -> ARouter.getInstance().build(RouteConstant.CREDIT_GUARANTEE).navigation());
		RxViewUtil.clicks(mDataBinding.ivMycenterClose).subscribe(o -> finish());
	}

	@Override
	public void getNetData() {

	}

	@Override
	public void getGCashDetailSuc(BaseBean<GCashDetailBean> baseBean) {
		if (DataResult.isSuccessUnToast(this, baseBean)) {
			GCashDetailBean bean = baseBean.getData();
			if (!TextUtils.isEmpty(bean.getGCashAccount())) {
				mDataBinding.tvGcash.setText("GCash:"+bean.getGCashAccount());
				mDataBinding.tvGcash.setVisibility(View.VISIBLE);
			}else {
				mDataBinding.tvGcash.setVisibility(View.GONE);
			}
		}
	}
}
