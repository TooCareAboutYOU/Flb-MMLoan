package com.mmkj.baselibrary.annotation;

/**
 * Author: wendjia
 * Time: 2018\9\4 0004
 * Description:
 **/
public class HeadToolbar {
	private int layoutId = -1;
	private int menuId = -1;
	private int toolbarTitle = -1;
	private int toolbarTitleSize = -1;
	private int toolbarTitleColor = -1;
	private int toolbarBgColor = -1;
	private int backDrawable = -1;
	private int loadingTargetView = -1;
	private int statusBarColor = -1;
	private boolean hideBack = false;

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {

		this.menuId = menuId;
	}
	public void setToolbarTitleSize(int size) {
		this.toolbarTitleSize = size;
	}

	public int getToolbarTitleSize() {
		return toolbarTitleSize;
	}
	public int getToolbarTitle() {
		return toolbarTitle;
	}

	public void setToolbarTitle(int toolbarTitle) {
		this.toolbarTitle = toolbarTitle;
	}

	public int getToolbarTitleColor() {
		return toolbarTitleColor;
	}

	public void setToolbarTitleColor(int toolbarTitleColor) {
		this.toolbarTitleColor = toolbarTitleColor;
	}

	public int getToolbarBgColor() {
		return toolbarBgColor;
	}

	public void setToolbarBgColor(int toolbarBgColor) {
		this.toolbarBgColor = toolbarBgColor;
	}

	public int getBackDrawable() {
		return backDrawable;
	}

	public void setBackDrawable(int backDrawable) {
		this.backDrawable = backDrawable;
	}

	public int getLoadingTargetView() {
		return loadingTargetView;
	}

	public void setLoadingTargetView(int loadingTargetView) {
		this.loadingTargetView = loadingTargetView;
	}

	public int getStatusBarColor() {
		return statusBarColor;
	}

	public void setStatusBarColor(int statusBarColor) {
		this.statusBarColor = statusBarColor;
	}

	public boolean isHideBack() {
		return hideBack;
	}

	public void setHideBack(boolean hideBack) {
		this.hideBack = hideBack;
	}

	public int getLayoutId() {
		return layoutId;
	}

	public void setLayoutId(int layoutId) {
		this.layoutId = layoutId;
	}
}
