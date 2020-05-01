package com.mmkjflb.mmloan.di.component;


import com.mmkjflb.mmloan.di.module.ServiceModule;
import com.mmkjflb.mmloan.di.scope.PerScope;

import dagger.Component;

/**
 * Created by Administrator on 2018/6/28.
 */

@PerScope
@Component(dependencies = AppComponent.class, modules = ServiceModule.class)
public interface ServiceComponent {

}
