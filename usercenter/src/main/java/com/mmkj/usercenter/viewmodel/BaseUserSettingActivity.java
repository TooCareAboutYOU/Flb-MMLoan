package com.mmkj.usercenter.viewmodel;

import androidx.databinding.ViewDataBinding;

import com.mmkj.baselibrary.base.BasePresenter;
import com.mmkj.baselibrary.base.IBaseActivity;
import com.mmkj.usercenter.UserCenterComponent;
import com.mmkj.usercenter.di.component.DaggerUserCenterActivityComponent;
import com.mmkj.usercenter.di.component.UserCenterActivityComponent;
import com.mmkj.usercenter.di.module.UserCenterActivityMoule;

/**
 * Author: wendjia
 * Time: 2018\9\4 0004
 * Description:
 **/
public abstract class BaseUserSettingActivity<P extends BasePresenter, B extends ViewDataBinding> extends IBaseActivity<P, B> {

	private UserCenterActivityComponent mActivityComponent;

	protected UserCenterActivityComponent getActivityComponent() {
		if (mActivityComponent == null) {
			mActivityComponent = DaggerUserCenterActivityComponent.builder()
					.userCenterAppComponent(UserCenterComponent.BaseAppComponent())
					.userCenterActivityMoule(new UserCenterActivityMoule(this))
					.build();
		}
		return mActivityComponent;
	}


}