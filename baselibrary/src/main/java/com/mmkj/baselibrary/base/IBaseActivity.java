package com.mmkj.baselibrary.base;

import androidx.databinding.ViewDataBinding;

import javax.inject.Inject;


public abstract class IBaseActivity<P extends BasePresenter, B extends ViewDataBinding> extends DataBindingActivity<B> {

    @Inject
    protected P mPresenter;

    public abstract void getNetData();


    @Override
    protected void initPresenter() {
        super.initPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
            getNetData();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }


}
