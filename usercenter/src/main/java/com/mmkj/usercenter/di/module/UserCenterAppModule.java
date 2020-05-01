package com.mmkj.usercenter.di.module;

import android.app.Application;

import com.mmkj.baselibrary.model.prefs.AppPreferencesHelper;
import com.mmkj.baselibrary.model.prefs.PreferencesHelper;
import com.mmkj.usercenter.model.UserCenterDataManager;
import com.mmkj.usercenter.model.http.UserCenterHttpHelper;
import com.mmkj.usercenter.model.http.UserCenterRetrofitHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by codeest on 16/8/7.
 */

@Module
public class UserCenterAppModule {
	private final Application application;

	public UserCenterAppModule(Application application) {
		this.application = application;
	}

	@Provides
	@Singleton
	UserCenterHttpHelper provideHttpHelper(UserCenterRetrofitHelper retrofitHelper) {
		return retrofitHelper;
	}


	@Provides
	@Singleton
	PreferencesHelper providePreferencesHelper(AppPreferencesHelper implPreferencesHelper) {
		return implPreferencesHelper;
	}

	@Provides
	@Singleton
	UserCenterDataManager provideDataManager(UserCenterHttpHelper apiService, AppPreferencesHelper preferencesHelper) {
		return new UserCenterDataManager(apiService, preferencesHelper);
	}

}
