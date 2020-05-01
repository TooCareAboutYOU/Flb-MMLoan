package com.mmkj.baselibrary.model;


import com.mmkj.baselibrary.model.http.HttpHelper;
import com.mmkj.baselibrary.model.prefs.PreferencesHelper;

import javax.inject.Singleton;

@Singleton
public class DataManager implements HttpHelper, PreferencesHelper {

	HttpHelper mHttpHelper;
	private PreferencesHelper mPreferencesHelper;

	public DataManager(HttpHelper httpHelper, PreferencesHelper preferencesHelper) {
		mHttpHelper = httpHelper;
		mPreferencesHelper = preferencesHelper;
	}

	@Override
	public boolean isFirstCome() {
		return mPreferencesHelper.isFirstCome();
	}

	@Override
	public void setFirstCome(boolean state) {
		mPreferencesHelper.setFirstCome(state);
	}


}
