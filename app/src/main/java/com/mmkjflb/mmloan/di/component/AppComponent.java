package com.mmkjflb.mmloan.di.component;

import com.mmkjflb.mmloan.di.module.AppModule;
import com.mmkjflb.mmloan.di.module.HttpModule;
import com.mmkjflb.mmloan.model.DataManager;
import dagger.Component;

import javax.inject.Singleton;

/**
 * @Component注入器
 */
@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {
//	BaseApplication getContext();  // 提供App的Context
	DataManager getDataManager(); //数据中心
//	RetrofitHelper retrofitHelper();  //提供http的帮助类
//	AppPreferencesHelper preferencesHelper();
}
