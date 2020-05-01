package com.mmkj.usercenter.viewmodel.loanlist;

import com.google.android.material.tabs.TabLayout;

import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.mmkj.baselibrary.databinding.ActivityMyBorrowBinding;
import com.mmkj.baselibrary.RouteConstant;
import com.mmkj.baselibrary.annotation.HeadToolbar;
import com.mmkj.baselibrary.annotation.HeaderBuilder;
import com.mmkj.baselibrary.model.entitry.BaseBean;
import com.mmkj.baselibrary.model.http.DataResult;
import com.mmkj.baselibrary.util.ArrayUtils;
import com.mmkj.baselibrary.util.PreferenceUtils;
import com.mmkj.baselibrary.util.StringUtils;
import com.mmkj.usercenter.R;
import com.mmkj.usercenter.model.entity.LoanListData;
import com.mmkj.usercenter.viewmodel.BaseUserSettingActivity;
import com.syhmmfqphl.xyxlibrary.utils.DensityUtil;
import com.syhmmfqphl.xyxlibrary.utils.ExtraKeys;

import java.lang.reflect.Field;
import java.util.List;


@Route(path = RouteConstant.LOAN_LIST)
public class LoanListActivity extends BaseUserSettingActivity<LoanListPresenter, ActivityMyBorrowBinding> implements LoanListContract.View {
    private OnItemClickLinearLayout onItemClickLinearLayout;

    @Override
    public boolean needCustomDensity() {
        return true;
    }

    @SuppressLint("ResourceType")
    @Override
    protected HeadToolbar getHeadToolbar() {
        return new HeaderBuilder().mContentViewId(R.layout.activity_my_borrow)
                .backDrawable(R.drawable.back_white)
                .toolbarTitle(R.string.my_borrow)
                .toolbarTitleColor(R.color.white)
                .toolbarBgColor(R.color.color_global)
                .loadingTargetView(R.id.rv_borrow_list)
                .build();
    }

    @Override
    public void getNetData() {
        mPresenter.getCurrentLoan(PreferenceUtils.getUserSessionid());
    }

    @Override
    protected void initViews() {
        getActivityComponent().inject(this);
        onItemClickLinearLayout = new OnItemClickLinearLayout();
        addTabLayout();
        initRv();
    }

    private void initRv() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mDataBinding.rvBorrowList.setLayoutManager(layoutManager);
    }


    //添加选项卡
    private void addTabLayout() {
        mDataBinding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    mPresenter.getCurrentLoan(PreferenceUtils.getUserSessionid());
                } else {
                    mPresenter.getLoanRecords(PreferenceUtils.getUserSessionid());
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
//                tab.parent
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        mDataBinding.tabLayout.addTab(mDataBinding.tabLayout.newTab().setText(getResources().getString(R.string.not_return_borrow)), true);
        mDataBinding.tabLayout.addTab(mDataBinding.tabLayout.newTab().setText(getResources().getString(R.string.history_borrow)));
        //设置指示器宽度
        reflex(mDataBinding.tabLayout);

    }

    //设置指示器宽度
    private void reflex(final TabLayout tabLayout) {
        //了解源码得知 线的宽度是根据 tabView的宽度来设置的
        tabLayout.post(() -> {
            try {
                //拿到tabLayout的mTabStrip属性
                LinearLayout mTabStrip = (LinearLayout) tabLayout.getChildAt(0);

                int dp10 = DensityUtil.dp2px(getResources(), 30);

                for (int i = 0; i < mTabStrip.getChildCount(); i++) {
                    View tabView = mTabStrip.getChildAt(i);
                    //拿到tabView的mTextView属性  tab的字数不固定一定用反射取mTextView
                    Field mTextViewField = tabView.getClass().getDeclaredField("mTextView");
                    mTextViewField.setAccessible(true);
                    TextView mTextView = (TextView) mTextViewField.get(tabView);
                    tabView.setPadding(0, 0, 0, 0);
                    //因为我想要的效果是   字多宽线就多宽，所以测量mTextView的宽度
                    int width = 0;
                    width = mTextView.getWidth();
                    if (width == 0) {
                        mTextView.measure(0, 0);
                        width = mTextView.getMeasuredWidth();
                    }
                    //设置tab左右间距为10dp  注意这里不能使用Padding 因为源码中线的宽度是根据 tabView的宽度来设置的
                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tabView.getLayoutParams();
                    params.width = width;
                    params.leftMargin = dp10;
                    params.rightMargin = dp10;
                    tabView.setLayoutParams(params);
                    tabView.postInvalidate();
                }

            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
    }


    @Override
    public void getLoanRecordsSuc(BaseBean<List<LoanListData>> baseBean) {
        if (DataResult.isSuccessUnToast(this, baseBean)) {
            LoanListAdapter adapter = new LoanListAdapter(this, baseBean.getData(), false);
            adapter.setEmptyView(getEmptyView(getString(R.string.not_history_borrow)));
            mDataBinding.rvBorrowList.setAdapter(adapter);

        }
    }

    public View getEmptyView(String str) {
        View views = LayoutInflater.from(this).inflate(R.layout.empty_data, null);
        TextView textView = views.findViewById(R.id.tv_empty_data);
        textView.setText(str);
        return views;
    }

    @Override
    public void getCurrentLoanSuc(BaseBean<List<LoanListData>> baseBean) {
        if (DataResult.isSuccessUnToast(this, baseBean)) {
            LoanListAdapter adapter = new LoanListAdapter(this, baseBean.getData(), true);
            adapter.setEmptyView(getEmptyView(getString(R.string.not_borrow)));
            mDataBinding.rvBorrowList.setAdapter(adapter);
            LoanListData current = ArrayUtils.getItem(baseBean.getData(), 0);
            if (current != null) {
                mDataBinding.tvPayment.setText(StringUtils.cutOutLastThree(StringUtils.doubleZheng(current.getDebt())));
            }
            adapter.setOnItemChildClickListener((adapter1, view, position) -> {
                if (view.getId() == R.id.look_va) {
                    LoanListData temp = (LoanListData) adapter1.getItem(position);
                    if (temp != null && temp.getWithdrawalNoStatus() == 1) {
                        ARouter.getInstance().build(RouteConstant.WITHDRAW).withBoolean("isLoanResult", false).navigation();
                    }
                }
            });
        }
    }

}
