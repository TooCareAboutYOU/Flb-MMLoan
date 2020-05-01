package com.mmkj.baselibrary.base;


import android.content.Context;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.syhmmfqphl.xyxlibrary.loading.LoadingTargetViewHelper;
import com.syhmmfqphl.xyxlibrary.progress.LoadingDialogManager;
import com.mmkj.baselibrary.annotation.ActivityFragmentInject;
import com.syhmmfqphl.xyxlibrary.loading.LoadingTargetViewHelper;

import javax.inject.Inject;


/**
 * Created by Administrator on 2016/11/3.
 */

public abstract class IBaseFragment<P extends BasePresenter, B extends ViewDataBinding> extends Fragment implements BaseView {
	protected View mFragmentRootView;
	private int mLoadingTargetView;
	private LoadingTargetViewHelper mLoadingTargetViewHelper = null;
	protected LoadingDialogManager loadingToast;
	/**
	 * 布局
	 */
	private int mContentViewId;

	/**
	 * statusbar颜色
	 */
	private int mStatusBarColor;
	/**
	 * 将代理类通用行为抽出来
	 */
	@Inject
	protected P mPresenter;
	private IBaseActivity mActivity;
	protected B mDataBinding;

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		this.mActivity = (IBaseActivity) context;
		this.loadingToast = ((IBaseActivity) context).loading;
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		if (null == mFragmentRootView) {
			if (getClass().isAnnotationPresent(ActivityFragmentInject.class)) {
				ActivityFragmentInject annotation = getClass().getAnnotation(ActivityFragmentInject.class);
				mContentViewId = annotation.contentViewId();
				mLoadingTargetView = annotation.loadingTargetView();
				mStatusBarColor = annotation.statusBarColor();
			} else {
				throw new RuntimeException("Class must add annotations of ActivityFragmentInitParams.class");
			}
			mFragmentRootView = initDataBinding(inflater, mContentViewId, container).getRoot();
		}
		return mFragmentRootView;
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		if (mLoadingTargetView != -1) {
			mLoadingTargetViewHelper = new LoadingTargetViewHelper((View) view.findViewById(mLoadingTargetView));
		}
		initViews();
		initPresenter();
	}

	protected void initPresenter() {
		if (mPresenter != null)
			mPresenter.attachView(this);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		if (mPresenter != null) {
			mPresenter.detachView();
		}
	}


	private B initDataBinding(LayoutInflater inflater, int mContentViewId, ViewGroup container) {
		mDataBinding = DataBindingUtil.inflate(inflater, mContentViewId, container, false);
//        setStatusBar(mStatusBarColor);
		return mDataBinding;
	}

	/**
	 * 设置导航栏颜色
	 *
	 * @param
	 */
//    protected void setStatusBar(int statusBarColor) {
//        if (statusBarColor == -1) {
//            StatusBarUtil.setColor(getActivity(), ContextCompat.getColor(getContext(), R.color.statusbar_maincolor), 0);
//        } else {
//            StatusBarUtil.setColor(getActivity(), ContextCompat.getColor(getContext(), statusBarColor), 0);
//        }
//    }
	protected void toggleShowLoading(boolean toggle) {
		if (toggle) {
			mLoadingTargetViewHelper.showLoading();
		} else {
			mLoadingTargetViewHelper.hideLoading();
		}
	}

	protected abstract void initViews();

	@Override
	public void showProgress() {
		if (mActivity != null) {
			mActivity.showProgress();
		}
	}

	@Override
	public void hideProgress(int msg) {
		if (mActivity != null) {
			mActivity.hideProgress(msg);
		}
	}

	@Override
	public void hideProgress(String msg) {
		if (mActivity != null) {
			mActivity.hideProgress(msg);
		}
	}

	@Override
	public void hideProgress() {
		if (mActivity != null) {
			mActivity.hideProgress();
		}
	}

	@Override
	public void showLoading() {
		toggleShowLoading(true);
	}

	@Override
	public void hideLoading() {
		toggleShowLoading(false);
	}

	@Override
	public void loadingError(View.OnClickListener onClickListener) {
		if (null == mLoadingTargetViewHelper) {
			throw new IllegalArgumentException("You must return a right target view for loading");
		}
		try {
			mLoadingTargetViewHelper.loadingError(onClickListener);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void loadingEmpty(int emptyImg) {
		if (null == mLoadingTargetViewHelper) {
			throw new IllegalArgumentException("You must return a right target view for loading");
		}
		try {
			//mLoadingTargetViewHelper.loadingEmpty(R.drawable.);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void toast(int msg) {
		mActivity.toast(msg);
	}

	@Override
	public void toast(String msg) {
		mActivity.toast(msg);
	}

	@Override
	public void showProgress(int msg) {

	}

	@Override
	public void snackbar(int msg) {

	}

	@Override
	public void snackbar(String msg) {

	}

	@Override
	public void onError(int resId) {

	}

	@Override
	public void onError() {

	}
}
