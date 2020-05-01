/*
 * Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://mindorks.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.mmkjflb.mmloan.model.prefs;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.mmkjflb.mmloan.app.BaseApplication;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by janisharali on 27/01/17.
 */

@Singleton
public class AppPreferencesHelper implements PreferencesHelper {

	private final SharedPreferences sharedPreferences;

	@Inject
	public AppPreferencesHelper() {
		sharedPreferences = PreferenceManager.getDefaultSharedPreferences(BaseApplication.getInstance());
	}


	@Override
	public boolean isFirstCome() {
		return sharedPreferences.getBoolean("dhffirst", true);
	}

	@Override
	public void setFirstCome(boolean state) {
		sharedPreferences.edit().putBoolean("dhffirst", state).apply();
	}
}
