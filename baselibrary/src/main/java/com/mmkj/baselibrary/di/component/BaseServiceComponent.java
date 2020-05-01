package com.mmkj.baselibrary.di.component;


import com.mmkj.baselibrary.di.module.BaseServiceModule;
import com.mmkj.baselibrary.di.scope.PerScope;

import dagger.Component;

/**
 * Created by Administrator on 2018/6/28.
 */

@PerScope
@Component(dependencies = BaseAppComponent.class, modules = BaseServiceModule.class)
public interface BaseServiceComponent {
//	void inject(UploadService service);
}
