package com.mmkj.baselibrary.di.module;

import android.app.Activity;

import com.mmkj.baselibrary.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by codeest on 16/8/7.
 */

@Module
public class BaseActivityModule {
	private Activity mActivity;

	public BaseActivityModule(Activity activity) {
		this.mActivity = activity;
	}

	@Provides
	@ActivityScope
	public Activity provideActivity() {
		return mActivity;
	}
}
