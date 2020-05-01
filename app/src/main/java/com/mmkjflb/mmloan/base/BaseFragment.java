package com.mmkjflb.mmloan.base;

import androidx.databinding.ViewDataBinding;

import com.mmkjflb.mmloan.app.BaseApplication;
import com.mmkjflb.mmloan.di.component.DaggerFragmentComponent;
import com.mmkjflb.mmloan.di.component.FragmentComponent;
import com.mmkjflb.mmloan.di.module.FragmentModule;
import com.mmkj.baselibrary.base.BasePresenter;
import com.mmkj.baselibrary.base.IBaseFragment;


/**
 * Created by Administrator on 2016/11/3.
 */

public abstract class BaseFragment<P extends BasePresenter, B extends ViewDataBinding> extends IBaseFragment<P, B> {

    private FragmentComponent mFragmentComponent;

    protected FragmentComponent getFragmentComponent() {
        if (mFragmentComponent == null) {
            mFragmentComponent = DaggerFragmentComponent.builder()
                    .appComponent(BaseApplication.getAppComponent())
                    .fragmentModule(new FragmentModule(this))
                    .build();
        }
        return mFragmentComponent;
    }



}
