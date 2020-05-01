package com.mmkjflb.mmloan.base;

import androidx.databinding.ViewDataBinding;

import com.mmkjflb.mmloan.app.BaseApplication;
import com.mmkjflb.mmloan.di.component.ActivityComponent;
import com.mmkjflb.mmloan.di.component.DaggerActivityComponent;
import com.mmkjflb.mmloan.di.module.ActivityModule;
import com.mmkj.baselibrary.base.BasePresenter;
import com.mmkj.baselibrary.base.IBaseActivity;


public  abstract class BaseActivity<P extends BasePresenter, B extends ViewDataBinding> extends IBaseActivity<P, B> {

	private ActivityComponent mActivityComponent;

	protected ActivityComponent getActivityComponent() {
		if (mActivityComponent == null) {
			mActivityComponent = DaggerActivityComponent.builder()
					.appComponent(BaseApplication.getAppComponent())
					.activityModule(new ActivityModule(this))
					.build();
		}
		return mActivityComponent;
	}


	@Override
	public void getNetData() {

	}
}
