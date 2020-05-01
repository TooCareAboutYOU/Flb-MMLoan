package com.mmkj.usercenter.di.component;

import com.mmkj.usercenter.di.module.UserCenterAppModule;
import com.mmkj.usercenter.di.module.UserCenterHttpModule;
import com.mmkj.usercenter.model.UserCenterDataManager;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @Component注入器
 */
@Singleton
@Component(modules = {UserCenterAppModule.class, UserCenterHttpModule.class})
public interface UserCenterAppComponent {
	//	BaseApplication getContext();  // 提供App的Context
	UserCenterDataManager getDataManager(); //数据中心
//	RetrofitHelper retrofitHelper();  //提供http的帮助类
//	AppPreferencesHelper preferencesHelper();
}
