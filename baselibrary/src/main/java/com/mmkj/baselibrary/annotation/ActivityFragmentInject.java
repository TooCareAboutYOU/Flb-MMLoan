package com.mmkj.baselibrary.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Activity、Fragment初始化的用到的注解
 */
@Retention(RetentionPolicy.RUNTIME)//表示在生命周期是运行时
@Target({ElementType.TYPE})
public @interface ActivityFragmentInject {
	/**
	 * 顶部局的id
	 */
	int contentViewId() default -1;

	/**
	 * 菜单id
	 */
	int menuId() default -1;

	/**
	 * toolbar的标题id
	 */
	int toolbarTitle() default -1;

	/**
	 * 标题的字体大小
	 * @return
	 */
	int toolbarTitleSize() default -1;

	int toolbarTitleColor() default -1;

	/**
	 * toolbar的背景颜色
	 */
	int toolbarBgColor() default -1;

	/**
	 * 返回图标 id
	 */
	int backDrawable() default -1;

	/**
	 * loading id
	 */
	int loadingTargetView() default -1;

	/**
	 * 状态栏颜色 (默认设置透明)
	 */
	int statusBarColor() default -1;

	boolean hideBack() default false;
}
