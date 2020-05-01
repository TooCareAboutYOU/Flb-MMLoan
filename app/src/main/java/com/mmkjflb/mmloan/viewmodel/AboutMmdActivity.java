package com.mmkjflb.mmloan.viewmodel;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.mmkjflb.mmloan.R;
import com.mmkjflb.mmloan.databinding.ActivityAboutMmdBinding;
import com.mmkj.baselibrary.RouteConstant;
import com.mmkj.baselibrary.annotation.ActivityFragmentInject;
import com.mmkj.baselibrary.base.DataBindingActivity;
import com.mmkj.baselibrary.util.DeviceUtils;

/**
 * Created by Administrator on 2017/10/16.
 */
@Route(path = RouteConstant.ABOUT_US)
@ActivityFragmentInject(contentViewId = R.layout.activity_about_mmd, toolbarTitle = R.string.about_we)
public class AboutMmdActivity extends DataBindingActivity<ActivityAboutMmdBinding> {
    @Override
    protected void initViews() {
        mDataBinding.tvVersion.setText(getResources().getString(R.string.current_version)+ DeviceUtils.getVersionName(this));
    }
}
