package com.mmkj.usercenter.di.module;

import android.app.Activity;

import com.mmkj.baselibrary.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Author: wendjia
 * Time: 2018\9\4 0004
 * Description:
 **/
@Module
public class UserCenterActivityMoule {
	private Activity mActivity;

	public UserCenterActivityMoule(Activity activity) {
		this.mActivity = activity;
	}

	@Provides
	@ActivityScope
	public Activity provideActivity() {
		return mActivity;
	}
}

