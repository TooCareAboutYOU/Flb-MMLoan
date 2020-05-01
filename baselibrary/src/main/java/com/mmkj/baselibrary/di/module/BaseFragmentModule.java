package com.mmkj.baselibrary.di.module;

import android.app.Activity;

import com.mmkj.baselibrary.di.scope.FragmentScope;

import androidx.fragment.app.Fragment;
import dagger.Module;
import dagger.Provides;

/**
 * Created by codeest on 16/8/7.
 */

@Module
public class BaseFragmentModule {

	private Fragment fragment;

	public BaseFragmentModule(Fragment fragment) {
		this.fragment = fragment;
	}

	@Provides
	@FragmentScope
	public Activity provideActivity() {
		return fragment.getActivity();
	}
}
