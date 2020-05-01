package com.mmkjflb.mmloan.di.module;


import com.mmkjflb.mmloan.app.BaseApplication;
import com.mmkjflb.mmloan.model.DataManager;
import com.mmkjflb.mmloan.model.http.HttpHelper;
import com.mmkjflb.mmloan.model.http.RetrofitHelper;
import com.mmkjflb.mmloan.model.prefs.AppPreferencesHelper;
import com.mmkjflb.mmloan.model.prefs.PreferencesHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by codeest on 16/8/7.
 */

@Module
public class AppModule {
	private final BaseApplication application;

	public AppModule(BaseApplication application) {
		this.application = application;
	}


	@Provides
	@Singleton
	HttpHelper provideHttpHelper(RetrofitHelper retrofitHelper) {
		return retrofitHelper;
	}


	@Provides
	@Singleton
	PreferencesHelper providePreferencesHelper(AppPreferencesHelper implPreferencesHelper) {
		return implPreferencesHelper;
	}

	@Provides
	@Singleton
	DataManager provideDataManager(HttpHelper apiService, PreferencesHelper preferencesHelper) {
		return new DataManager(apiService, preferencesHelper);
	}
}
