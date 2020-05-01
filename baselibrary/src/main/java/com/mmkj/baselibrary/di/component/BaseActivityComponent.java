package com.mmkj.baselibrary.di.component;

import android.app.Activity;

import com.mmkj.baselibrary.di.module.BaseActivityModule;
import com.mmkj.baselibrary.di.scope.ActivityScope;

import dagger.Component;

/**
 * Created by codeest on 16/8/7.
 */

@ActivityScope
@Component(dependencies = BaseAppComponent.class, modules = BaseActivityModule.class)
public interface BaseActivityComponent {
	Activity getActivity();

}
