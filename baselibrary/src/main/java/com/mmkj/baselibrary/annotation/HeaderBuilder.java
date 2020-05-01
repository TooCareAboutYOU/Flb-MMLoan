package com.mmkj.baselibrary.annotation;

/**
 * Author: wendjia
 * Time: 2018\9\4 0004
 * Description:
 **/
public class HeaderBuilder implements IHeaderBuilder {
	private HeadToolbar headToolbar = new HeadToolbar();


	@Override
	public HeaderBuilder mContentViewId(int layoutId) {
		headToolbar.setLayoutId(layoutId);
		return this;
	}

	@Override
	public HeaderBuilder menuId(int menuId) {
		headToolbar.setMenuId(menuId);
		return this;
	}

	@Override
	public HeaderBuilder toolbarTitle(int toolbarTitle) {
		headToolbar.setToolbarTitle(toolbarTitle);
		return this;
	}

	@Override
	public HeaderBuilder toolbarTitleSize(int textSize) {
		headToolbar.setToolbarTitleSize(textSize);
		return this;
	}

	@Override
	public HeaderBuilder toolbarTitleColor(int toolbarTitleColor) {
		headToolbar.setToolbarTitleColor(toolbarTitleColor);
		return this;
	}

	@Override
	public HeaderBuilder toolbarBgColor(int toolbarBgColor) {
		headToolbar.setToolbarBgColor(toolbarBgColor);
		return this;
	}

	@Override
	public HeaderBuilder backDrawable(int backDrawable) {
		headToolbar.setBackDrawable(backDrawable);
		return this;
	}

	@Override
	public HeaderBuilder loadingTargetView(int loadingTargetView) {
		headToolbar.setLoadingTargetView(loadingTargetView);
		return this;
	}

	@Override
	public HeaderBuilder statusBarColor(int statusBarColor) {
		headToolbar.setStatusBarColor(statusBarColor);
		return this;
	}

	@Override
	public HeaderBuilder hideBack(boolean hideBack) {
		headToolbar.setHideBack(hideBack);
		return this;
	}

	public HeadToolbar build() {
		return headToolbar;
	}
}
