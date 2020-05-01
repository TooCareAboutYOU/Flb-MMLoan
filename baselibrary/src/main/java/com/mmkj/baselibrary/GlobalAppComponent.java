package com.mmkj.baselibrary;

import android.app.Application;

import com.mmkj.baselibrary.di.component.BaseAppComponent;
import com.mmkj.baselibrary.di.component.DaggerBaseAppComponent;
import com.mmkj.baselibrary.di.module.BaseAppModule;
import com.mmkj.baselibrary.di.module.HttpModule;

/**
 * Author: wendjia
 * Time: 2018\8\31 0031
 * Description:
 **/
public class GlobalAppComponent {
	private volatile static BaseAppComponent mBaseAppComponent;
	public static Application _application;

	/**
	 * 初始化全局AppComponent
	 *
	 * @param application
	 */
	public static void init(Application application) {
		_application = application;
		if (mBaseAppComponent == null) {
			synchronized (GlobalAppComponent.class) {
				if (mBaseAppComponent == null) {
					mBaseAppComponent = DaggerBaseAppComponent.builder()
							.baseAppModule(new BaseAppModule(application))
							.httpModule(new HttpModule())
							.build();
				}
			}
		}
	}

	public static BaseAppComponent BaseAppComponent() {
		if (mBaseAppComponent == null) {
			throw (new NullPointerException("GlobalAppComponent必须在application中进行init初始化"));
		}
		return mBaseAppComponent;
	}
}