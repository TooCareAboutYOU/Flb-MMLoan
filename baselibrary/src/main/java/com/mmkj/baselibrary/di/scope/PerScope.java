package com.mmkj.baselibrary.di.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by Administrator on 2018/6/28.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerScope {
}
