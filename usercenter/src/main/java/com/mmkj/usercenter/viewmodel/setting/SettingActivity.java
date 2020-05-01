package com.mmkj.usercenter.viewmodel.setting;


import android.os.Bundle;

import com.afollestad.materialdialogs.MaterialDialog;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.mmkj.baselibrary.RouteConstant;
import com.mmkj.baselibrary.annotation.HeadToolbar;
import com.mmkj.baselibrary.annotation.HeaderBuilder;
import com.mmkj.baselibrary.app.ActivityStack;
import com.mmkj.baselibrary.model.entitry.BaseBean;
import com.mmkj.baselibrary.util.PreferenceUtils;
import com.mmkj.baselibrary.util.RxViewUtil;
import com.mmkj.usercenter.R;
import com.mmkj.usercenter.databinding.ActivitySettingMmdBinding;
import com.mmkj.usercenter.viewmodel.BaseUserSettingActivity;

/**
 * Created by Administrator on 2017/10/16.
 */
@Route(path = RouteConstant.SETTING)
public class SettingActivity extends BaseUserSettingActivity<SettingPresenter, ActivitySettingMmdBinding> implements SettingContract.View {
    @Override
    protected HeadToolbar getHeadToolbar() {
        return new HeaderBuilder().mContentViewId(R.layout.activity_setting_mmd).toolbarTitle(R.string.setting).build();
    }

    @Override
    protected void initViews() {
        getActivityComponent().inject(this);
        initClicks();
    }

    private void initClicks() {
        RxViewUtil.clicks(mDataBinding.tvChangepassword).subscribe(o ->
                ARouter.getInstance().build(RouteConstant.CHANGE_PASSWORD).navigation());
        RxViewUtil.clicks(mDataBinding.tvAboutus).subscribe(o ->
                ARouter.getInstance().build(RouteConstant.ABOUT_US).navigation());
        RxViewUtil.clicks(mDataBinding.tvFeedback).subscribe(o ->
                ARouter.getInstance().build(RouteConstant.FEEDBACK).navigation());
        RxViewUtil.clicks(mDataBinding.tvLoginout).subscribe(o -> new MaterialDialog.Builder(SettingActivity.this)
                .content(R.string.login_out)
                .positiveText(R.string.cancel)
                .negativeText(R.string.confirm)
                .onNegative((dialog, which) -> mPresenter.loginOut(PreferenceUtils.getString(PreferenceUtils.USER_SESSIONID, ""))).show());
        RxViewUtil.clicks(mDataBinding.tvChangephone).subscribe(o ->
                ARouter.getInstance().build(RouteConstant.CHANGE_PHONE_ONE).navigation());
    }

    @Override
    public void loginOutSuc(BaseBean baseBean) {
        //FireBase记录事件
//		Bundle bundle = new Bundle();
//		bundle.putString(FirebaseAnalytics.Param.METHOD, method);
//		FirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SIGN_UP, bundle);

        PreferenceUtils.remove(PreferenceUtils.USER_SESSIONID);
        PreferenceUtils.remove(PreferenceUtils.USER_SETP);
        ActivityStack.getInstance().removeAllExceptCurrent(this);
        ARouter.getInstance()
                .build(RouteConstant.LOGIN)
                .navigation();
        finish();
    }

    @Override
    public void getNetData() {

    }
}
