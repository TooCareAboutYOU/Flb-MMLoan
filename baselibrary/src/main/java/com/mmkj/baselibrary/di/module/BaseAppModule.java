package com.mmkj.baselibrary.di.module;

import android.app.Application;

import com.mmkj.baselibrary.model.DataManager;
import com.mmkj.baselibrary.model.http.HttpHelper;
import com.mmkj.baselibrary.model.http.RetrofitHelper;
import com.mmkj.baselibrary.model.prefs.AppPreferencesHelper;
import com.mmkj.baselibrary.model.prefs.PreferencesHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by codeest on 16/8/7.
 */

@Module
public class BaseAppModule {
	private final Application application;

	public BaseAppModule(Application application) {
		this.application = application;
	}

//	@Provides
//	@Singleton
//	BaseApplication provideApplicationContext() {
//		return application;
//	}

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
