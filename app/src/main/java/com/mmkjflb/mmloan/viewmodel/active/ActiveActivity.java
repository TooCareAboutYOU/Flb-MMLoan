package com.mmkjflb.mmloan.viewmodel.active;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.mmkj.baselibrary.RouteConstant;
import com.mmkjflb.mmloan.R;
import com.mmkjflb.mmloan.base.BaseActivity;
import com.mmkjflb.mmloan.databinding.ActivityActiveBinding;
import com.mmkjflb.mmloan.model.entity.QueryActivityEntity;
import com.mmkjflb.mmloan.model.http.DataResult;
import com.mmkj.baselibrary.annotation.ActivityFragmentInject;
import com.mmkj.baselibrary.model.entitry.BaseBean;
import com.mmkj.baselibrary.util.PreferenceUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/30.
 * 无效页面
 */
@Route(path = RouteConstant.ACTIVE)
@ActivityFragmentInject(contentViewId = R.layout.activity_active,toolbarTitle = R.string.active,loadingTargetView = R.id.swipe_refresh_layout)
public class ActiveActivity extends BaseActivity<ActivePresenter, ActivityActiveBinding> implements ActiveContract.View {
    private ActiveAdapter activeAdapter;
    private List<QueryActivityEntity> activeData = new ArrayList<>();

    @Override
    protected void initViews() {
        getActivityComponent().inject(this);
//        mDataBinding.rvActive.setHasFixedSize(true);
//        mDataBinding.rvActive.setLayoutManager(new LinearLayoutManager(this));
//        mDataBinding.swipeRefreshLayout.setColorSchemeResources(R.color.green_24c789, R.color.green_26c5a8);
//        activeAdapter = new ActiveAdapter(this, activeData);
//        mDataBinding.rvActive.setAdapter(activeAdapter);
//        mDataBinding.swipeRefreshLayout.setOnRefreshListener(listener);
//        pullToRefresh();
        loadingEmpty(com.mmkjflb.xyxlibrary.R.string.not_active);
        mDataBinding.swipeRefreshLayout.setEnabled(false);
    }

    private SwipeRefreshLayout.OnRefreshListener listener = () -> mPresenter.queryActivity(PreferenceUtils.getString(PreferenceUtils.USER_SESSIONID, ""));

    @Override
    public void queryActivitySuc(BaseBean<List<QueryActivityEntity>> baseBean) {
        mDataBinding.swipeRefreshLayout.setRefreshing(false);
        if (DataResult.isSuccessUnToast(this, baseBean)) {
            activeData = baseBean.getData();
            activeAdapter.setData(activeData);
        }
    }

    @Override
    public void onError() {
        toast(R.string.neterror_tryagain);
        mDataBinding.swipeRefreshLayout.setRefreshing(false);
    }

    private void pullToRefresh() {
		mDataBinding.swipeRefreshLayout.post((Runnable) () -> {
			if (null != mDataBinding.swipeRefreshLayout) {
				mDataBinding.swipeRefreshLayout.setRefreshing(true);
				listener.onRefresh();
			}
		});
    }
}
