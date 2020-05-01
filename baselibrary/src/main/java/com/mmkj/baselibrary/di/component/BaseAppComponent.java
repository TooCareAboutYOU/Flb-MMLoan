package com.mmkj.baselibrary.di.component;

import com.mmkj.baselibrary.di.module.BaseAppModule;
import com.mmkj.baselibrary.di.module.HttpModule;
import com.mmkj.baselibrary.model.DataManager;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @Component注入器
 */
@Singleton
@Component(modules = {BaseAppModule.class, HttpModule.class})
public interface BaseAppComponent {
	//	BaseApplication getContext();  // 提供App的Context
	DataManager getDataManager(); //数据中心
//	RetrofitHelper retrofitHelper();  //提供http的帮助类
//	AppPreferencesHelper preferencesHelper();
}
