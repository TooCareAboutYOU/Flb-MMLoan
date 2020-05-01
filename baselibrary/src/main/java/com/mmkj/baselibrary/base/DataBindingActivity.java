package com.mmkj.baselibrary.base;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.alibaba.android.arouter.launcher.ARouter;
import com.google.android.material.snackbar.Snackbar;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.syhmmfqphl.xyxlibrary.loading.LoadingTargetViewHelper;
import com.syhmmfqphl.xyxlibrary.progress.LoadingDialogManager;
import com.syhmmfqphl.xyxlibrary.toast.ToastUtil;
import com.mmkj.baselibrary.R;
import com.jaeger.library.StatusBarUtil;
import com.mmkj.baselibrary.annotation.ActivityFragmentInject;
import com.mmkj.baselibrary.annotation.HeadToolbar;
import com.mmkj.baselibrary.app.ActivityStack;
import com.tbruyelle.rxpermissions2.RxPermissions;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by Administrator on 2016/10/17.
 * 所有activity的基类 (P 是为了在destroy的时候请求数据解绑)
 */
public abstract class DataBindingActivity<B extends ViewDataBinding> extends AppCompatActivity implements BaseView {
	public ToastUtil mToast = null;
	public LoadingDialogManager loading;
	protected B mDataBinding;
	protected Activity mContext;
	/**
	 * 布局
	 */
	private int mContentViewId;
	/**
	 * 菜单
	 */
	private int mMenuId;
	/**
	 * 标题
	 */
	private int mToolbarTitle;

	/**
	 * 标题字体大小
	 */
	private int mToolbarTitleSize;
	/**
	 * 返回图标
	 */
	private int mBackDrawable;
	/**
	 * 刚进入界面加载效果绑定view
	 */
	private int mLoadingTargetView = -1;
	/**
	 * statusbar颜色
	 */
	private int mStatusBarColor;
	private LoadingTargetViewHelper mLoadingTargetViewHelper = null;
	private int mToolbarBgColor;
	private TextView tvToolbarTitle;
	private TextView tvToolbarBack;
	private boolean mHideBack = false;
	protected Toolbar mToolbar;
	private int mToolbarTitleColor;
	public RxPermissions rxPermissions;

	protected HeadToolbar getHeadToolbar() {
		return null;
	}
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ARouter.getInstance().inject(this);
		rxPermissions = new RxPermissions(this);
		mContext = this;
		mToast = ToastUtil.initToast(getApplicationContext());
		loading = LoadingDialogManager.create(this);
		ActivityStack.getInstance().addActivity(this);
		if (!isTaskRoot()) { //防止首次安装按home键重新启动
			Intent intent = getIntent();
			String action = intent.getAction();
			if (intent.hasCategory(Intent.CATEGORY_LAUNCHER) && action.equals(Intent.ACTION_MAIN)) {
				finish();
				return;
			}
		}
		HeadToolbar headToolbar = getHeadToolbar();
		if (headToolbar == null) {
			if (getClass().isAnnotationPresent(ActivityFragmentInject.class)) {
				ActivityFragmentInject annotation = getClass().getAnnotation(ActivityFragmentInject.class);
				mContentViewId = annotation.contentViewId();
				mMenuId = annotation.menuId();
				mToolbarTitle = annotation.toolbarTitle();
				mToolbarTitleSize = annotation.toolbarTitleSize();
				mToolbarTitleColor = annotation.toolbarTitleColor();
				mToolbarBgColor = annotation.toolbarBgColor();
				mBackDrawable = annotation.backDrawable();
				mLoadingTargetView = annotation.loadingTargetView();
				mStatusBarColor = annotation.statusBarColor();
				mHideBack = annotation.hideBack();
			} else {
				throw new RuntimeException("Class must add annotations of ActivityFragmentInitParams.class");
			}
		} else {
			mContentViewId = headToolbar.getLayoutId();
			mMenuId = headToolbar.getMenuId();
			mToolbarTitle = headToolbar.getToolbarTitle();
			mToolbarTitleSize = headToolbar.getToolbarTitleSize();
			mToolbarTitleColor = headToolbar.getToolbarTitleColor();
			mToolbarBgColor = headToolbar.getToolbarBgColor();
			mBackDrawable = headToolbar.getBackDrawable();
			mLoadingTargetView = headToolbar.getLoadingTargetView();
			mStatusBarColor = headToolbar.getStatusBarColor();
			mHideBack = headToolbar.isHideBack();
		}
		initDataBinding(mContentViewId); //设置界面
		initToolbar();
		initViews();
		initPresenter(); // 在initInject初始化之后
	}

	public void setToolBar(String title) {
		mToolbar = findViewById(R.id.commoninclude_toolbar);
		if (null != mToolbar) {
			TextView tvTitleMain = findViewById(R.id.tv_title_main);
			if (tvTitleMain != null) {
				tvTitleMain.setText(title);
			}
			setSupportActionBar(mToolbar);
			if (getSupportActionBar() != null) {
				getSupportActionBar().setDisplayHomeAsUpEnabled(true);
				getSupportActionBar().setHomeAsUpIndicator(R.drawable.back_black);
				getSupportActionBar().setTitle("");
			}
		}
	}


	private void initDataBinding(int mContentViewId) {
		mDataBinding = DataBindingUtil.setContentView(this, mContentViewId);
		setStatusBar(mStatusBarColor);
		if (mLoadingTargetView != -1) {
			mLoadingTargetViewHelper = new LoadingTargetViewHelper((View) findViewById(mLoadingTargetView));
		}
		if (needCustomDensity()) {
			setCustomDensity(this, getApplication());
		}
	}

	/**
	 * 是否需要今日头条适配方案，如果不需要请重写为false
	 *
	 * @return
	 */
	public boolean needCustomDensity() {
		return true;
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (mDataBinding != null) {
			mDataBinding.unbind();
		}

		ActivityStack.getInstance().removeActivity(this);
		if (loading != null) {
			loading.destroy();
		}
	}

	/**
	 * 设置标题栏
	 */
	private void initToolbar() {
		if (mToolbarTitle != -1) {
			mToolbar = findViewById(R.id.commoninclude_toolbar);
			if (null != mToolbar) {
				TextView tvTitleMain = findViewById(R.id.tv_title_main);
				setSupportActionBar(mToolbar);
				if (getSupportActionBar() != null) {
					getSupportActionBar().setDisplayHomeAsUpEnabled(true);
					if (mBackDrawable != -1) { //设置返回图标
						getSupportActionBar().setHomeAsUpIndicator(mBackDrawable);
					} else {    //默认返回图标（大部分界面统一返回图标）
						getSupportActionBar().setHomeAsUpIndicator(R.drawable.back_black);
					}
				}
				getSupportActionBar().setTitle("");
				if (mToolbarTitle != -1) {
					tvTitleMain.setText(mToolbarTitle);
				}
				if (mToolbarTitleSize!=-1){
					tvTitleMain.setTextSize(mToolbarTitleSize);
				}
				if (mToolbarTitleColor != -1) {
					tvTitleMain.setTextColor(ContextCompat.getColor(this, mToolbarTitleColor));
				}
				if (mToolbarBgColor != -1) {
					mToolbar.setBackgroundColor(ContextCompat.getColor(this, mToolbarBgColor));
				}
				mToolbar.setNavigationOnClickListener(v -> onToolbarNavigationClick());
			}
		}
	}

	public void onToolbarNavigationClick() {
		finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (mMenuId != -1) {
			getMenuInflater().inflate(mMenuId, menu);
		}
		return true;
	}

	/**
	 * 系统返回键
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home && mBackDrawable == -1) {
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * 设置导航栏颜色
	 */
	protected void setStatusBar(int statusBarColor) {
		if (statusBarColor == -1) {
			StatusBarUtil.setColor(this, ContextCompat.getColor(this, R.color.statusbar_maincolor), 0);
		} else {
			StatusBarUtil.setColor(this, ContextCompat.getColor(this, statusBarColor), 0);
		}

	}

	protected void initPresenter() {
	}

	/**
	 * 初始化数据
	 */
	protected abstract void initViews();

	@Override
	public void showLoading() {
		if (null == mLoadingTargetViewHelper) {
			throw new IllegalArgumentException("You must return a right target ui for loading");
		}
		try {
			mLoadingTargetViewHelper.showLoading();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void hideLoading() {
		if (null == mLoadingTargetViewHelper) {
			throw new IllegalArgumentException("You must return a right target ui for loading");
		}
		try {
			mLoadingTargetViewHelper.hideLoading();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void loadingError(View.OnClickListener onClickListener) {
		if (null == mLoadingTargetViewHelper) {
			throw new IllegalArgumentException("You must return a right target ui for loading");
		}
		try {
			mLoadingTargetViewHelper.loadingError(onClickListener);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void loadingEmpty(int msg) {
		if (null == mLoadingTargetViewHelper) {
			throw new IllegalArgumentException("You must return a right target ui for loading");
		}
		try {
			mLoadingTargetViewHelper.loadingEmpty(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void showProgress() {
		if (loading != null) {
			loading.show();
		}
	}

	@Override
	public void showProgress(int msg) {
	}

	@Override
	public void hideProgress() {
		if (loading != null) {
			loading.dismiss();
		}
	}

	@Override
	public void hideProgress(int msg) {
		hideProgress(getResources().getString(msg));
	}

	@Override
	public void hideProgress(String msg) {
		hideProgress();
		toast(msg);
	}

	@Override
	public void toast(int msg) {
		toast(getResources().getString(msg));
	}

	@Override
	public void toast(String msg) {
		if (mToast != null && !TextUtils.isEmpty(msg)) {
			mToast.showToast(msg);
		}
	}

	@Override
	public void snackbar(int msg) {
		snackbar(getResources().getString(msg));
	}

	@Override
	public void snackbar(String msg) {
		Snackbar.make(((ViewGroup) findViewById(android.R.id.content)).getChildAt(0), msg, Snackbar.LENGTH_LONG).show();
	}

	@Override
	public void onError() {
	}

	@Override
	public void onError(int resId) {

	}

	@Override
	protected void attachBaseContext(Context newBase) {
		super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
	}

	/**
	 * 点击非edittext处软键盘消失
	 */
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		if (ev.getAction() == MotionEvent.ACTION_DOWN) {
			View v = getCurrentFocus();
			if (isShouldHideInput(v, ev)) {
				InputMethodManager imm = (InputMethodManager)
						getSystemService(Context.INPUT_METHOD_SERVICE);
				if (imm != null) {
					imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
				}
			}
			return super.dispatchTouchEvent(ev);
		}        // 必不可少，否则所有的组件都不会有TouchEvent了
		if (getWindow().superDispatchTouchEvent(ev)) {
			return true;
		}
		return onTouchEvent(ev);
	}

	public boolean isShouldHideInput(View v, MotionEvent event) {
		if (v != null && (v instanceof EditText)) {
			int[] leftTop = {0, 0};        //获取输入框当前的location位置
			v.getLocationInWindow(leftTop);
			int left = leftTop[0];
			int top = leftTop[1];
			int bottom = top + v.getHeight();
			int right = left + v.getWidth();
			return !(event.getX() > left) || !(event.getX() < right)
					|| !(event.getY() > top) || !(event.getY() < bottom);
		}
		return false;
	}

	private static int uiWidth = 360;
	private static float sNoncompatDensity;
	private static float sNoncompatScaledDensity;

	private static void setCustomDensity(@NonNull Activity activity, @NonNull final Application application) {
		final DisplayMetrics appDisplayMetrics = application.getResources().getDisplayMetrics();
		if (sNoncompatDensity == 0) {
			sNoncompatDensity = appDisplayMetrics.density;
			sNoncompatScaledDensity = appDisplayMetrics.scaledDensity;
			application.registerComponentCallbacks(new ComponentCallbacks() {
				@Override
				public void onConfigurationChanged(Configuration configuration) {
					if (configuration != null && configuration.fontScale > 0) {
						sNoncompatScaledDensity = application.getResources().getDisplayMetrics().scaledDensity;
					}
				}

				@Override
				public void onLowMemory() {
				}
			});
		}
		final float targetDensity = appDisplayMetrics.widthPixels / uiWidth;
		final float targetScaledDensity = targetDensity * (sNoncompatScaledDensity / sNoncompatDensity);
		final int targetDensityDpi = (int) (160 * targetDensity);

		appDisplayMetrics.density = appDisplayMetrics.scaledDensity = targetDensity;
		appDisplayMetrics.scaledDensity = targetScaledDensity;
		appDisplayMetrics.densityDpi = targetDensityDpi;

		final DisplayMetrics activityDisplayMetrics = activity.getResources().getDisplayMetrics();
		activityDisplayMetrics.density = activityDisplayMetrics.scaledDensity = targetDensity;
		activityDisplayMetrics.scaledDensity = targetScaledDensity;
		activityDisplayMetrics.densityDpi = targetDensityDpi;
	}


}
