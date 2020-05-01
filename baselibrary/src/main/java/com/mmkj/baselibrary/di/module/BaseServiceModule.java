package com.mmkj.baselibrary.di.module;

import android.app.Service;

import dagger.Module;

/**
 * Created by Administrator on 2018/6/28.
 */


@Module
public class BaseServiceModule {

	private final Service mService;

	public BaseServiceModule(Service service) {
		mService = service;
	}
}
