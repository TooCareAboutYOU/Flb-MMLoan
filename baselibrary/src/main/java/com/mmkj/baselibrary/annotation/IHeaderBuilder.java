package com.mmkj.baselibrary.annotation;

import androidx.annotation.ColorRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.StringRes;

/**
 * Author: wendjia
 * Time: 2018\9\4 0004
 * Description:
 *
 * @See com.mmkj.baselibrary.annotation.ActivityFragmentInject
 **/

public interface IHeaderBuilder {
	HeaderBuilder mContentViewId(@LayoutRes int layoutId);

	HeaderBuilder menuId(int menuId);

	HeaderBuilder toolbarTitle(@StringRes int toolbarTitle);

	HeaderBuilder toolbarTitleSize(@StringRes int textSize);

	HeaderBuilder toolbarTitleColor(@ColorRes int toolbarTitleColor);

	HeaderBuilder toolbarBgColor(@ColorRes int toolbarBgColor);

	HeaderBuilder backDrawable(@ColorRes int backDrawable);

	HeaderBuilder loadingTargetView(@LayoutRes int loadingTargetView);

	HeaderBuilder statusBarColor(@ColorRes int statusBarColor);

	HeaderBuilder hideBack(boolean hideBack);

}
