package com.mmkj.usercenter;

import android.app.Application;

import com.mmkj.usercenter.di.component.DaggerUserCenterAppComponent;
import com.mmkj.usercenter.di.component.UserCenterAppComponent;
import com.mmkj.usercenter.di.module.UserCenterAppModule;
import com.mmkj.usercenter.di.module.UserCenterHttpModule;

/**
 * Author: wendjia
 * Time: 2018\8\31 0031
 * Description:
 **/
public class UserCenterComponent {
	private volatile static UserCenterAppComponent userCenterAppComponent;
	public static Application _application;
	/**
	 * 初始化全局AppComponent
	 *
	 * @param application
	 */
	public static void init(Application application) {
		_application = application;
		if (userCenterAppComponent == null) {
			synchronized (UserCenterComponent.class) {
				if (userCenterAppComponent == null) {
					userCenterAppComponent = DaggerUserCenterAppComponent.builder()
							.userCenterAppModule(new UserCenterAppModule(application))
							.userCenterHttpModule(new UserCenterHttpModule()).build();
				}
			}
		}
	}

	public static UserCenterAppComponent BaseAppComponent() {
		if (userCenterAppComponent == null) {
			throw (new NullPointerException("GlobalAppComponent必须在application中进行init初始化"));
		}
		return userCenterAppComponent;
	}


}