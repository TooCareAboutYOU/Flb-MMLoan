package com.mmkjflb.mmloan.di.module;

import android.app.Service;
import dagger.Module;

/**
 * Created by Administrator on 2018/6/28.
 */


@Module
public class ServiceModule {

	private final Service mService;

	public ServiceModule(Service service) {
		mService = service;
	}
}
