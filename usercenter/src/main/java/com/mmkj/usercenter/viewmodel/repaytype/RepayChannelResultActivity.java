package com.mmkj.usercenter.viewmodel.repaytype;

import com.google.android.material.tabs.TabLayout;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.mmkj.baselibrary.util.Constants;
import com.syhmmfqphl.xyxlibrary.utils.DensityUtil;
import com.syhmmfqphl.xyxlibrary.utils.ExtraKeys;
import com.mmkj.baselibrary.RouteConstant;
import com.mmkj.baselibrary.annotation.HeadToolbar;
import com.mmkj.baselibrary.annotation.HeaderBuilder;
import com.mmkj.baselibrary.base.DataBindingActivity;
import com.mmkj.baselibrary.util.RxViewUtil;
import com.mmkj.usercenter.R;
import com.mmkj.usercenter.databinding.AcRepayChannelBinding;
import com.mmkj.usercenter.model.RepayChannelUtil;
import com.mmkj.usercenter.model.entity.RepayTypeEntity;
import com.mmkj.usercenter.viewmodel.loanlist.OnItemClickLinearLayout;

import java.lang.reflect.Field;
import java.util.ArrayList;


@Route(path = RouteConstant.REPAY_CHANNEL_RESULT)
public class RepayChannelResultActivity extends DataBindingActivity<AcRepayChannelBinding> {
    private OnItemClickLinearLayout onItemClickLinearLayout;

    @Autowired
    int searchType;
    RepayTypeAdapter adapter;
    @Autowired
    String regino;
    @Autowired
    String province;
    @Autowired
    String city;

    private ArrayList<RepayTypeEntity> seveneleven;
    private ArrayList<RepayTypeEntity> other;

    @SuppressLint("ResourceType")
    @Override
    protected HeadToolbar getHeadToolbar() {
        return new HeaderBuilder().mContentViewId(R.layout.ac_repay_channel)
                .backDrawable(R.drawable.back_black)
                .toolbarTitle(R.string.repay_channel_title)
                .build();
    }


    @Override
    protected void initViews() {
        ARouter.getInstance().inject(this);
//        other = new ArrayList<>(RepayChannelUtil.getOther(regino, province, city));
//        initRv();
//        if (searchType == 1) {
        System.out.println("打印："+regino+"\t"+province+"\t"+city);
        seveneleven = new ArrayList<>(RepayChannelUtil.get711(regino, province, city));
        System.out.println("打印："+seveneleven.toString());
        adapter = new RepayTypeAdapter(seveneleven);
//            addTabLayout();
//        }else {
//            adapter = new RepayTypeAdapter(other);
//            mDataBinding.tabLayout.setVisibility(View.GONE);
//        }
        mDataBinding.rvBorrowList.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        mDataBinding.rvBorrowList.setAdapter(adapter);
        RxViewUtil.clicks(mDataBinding.ivBack).subscribe(o -> finish());
//        RxViewUtil.clicks(mDataBinding.ivHelp).subscribe(o -> ARouter.getInstance().build(RouteConstant.WEBVIEW).withString(ExtraKeys.Key_Msg1, Constants.FAQ).navigation());
    }

//    private void initRv() {
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        mDataBinding.rvBorrowList.setLayoutManager(layoutManager);
//    }
//
//    private void initAdapter(int position) {
//        if (position == 0) {
//            adapter.setNewData(seveneleven);
//        } else {
//            adapter.setNewData(other);
//        }
//        adapter.notifyDataSetChanged();
//    }


//    //添加选项卡
//    private void addTabLayout() {
//        mDataBinding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                initAdapter(tab.getPosition());
//
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
//        mDataBinding.tabLayout.addTab(mDataBinding.tabLayout.newTab().setText(getResources().getString(R.string.repay_channel1)), true);
//        mDataBinding.tabLayout.addTab(mDataBinding.tabLayout.newTab().setText(getResources().getString(R.string.repay_channel2)));
//        //设置指示器宽度
//        reflex(mDataBinding.tabLayout);
//
//    }
//
//    //设置指示器宽度
//    private void reflex(final TabLayout tabLayout) {
//        //了解源码得知 线的宽度是根据 tabView的宽度来设置的
//        tabLayout.post(() -> {
//            try {
//                //拿到tabLayout的mTabStrip属性
//                LinearLayout mTabStrip = (LinearLayout) tabLayout.getChildAt(0);
//                int dp10 = DensityUtil.dp2px(getResources(), 30);
//                for (int i = 0; i < mTabStrip.getChildCount(); i++) {
//                    View tabView = mTabStrip.getChildAt(i);
//                    //拿到tabView的mTextView属性  tab的字数不固定一定用反射取mTextView
//                    Field mTextViewField = tabView.getClass().getDeclaredField("mTextView");
//                    mTextViewField.setAccessible(true);
//                    TextView mTextView = (TextView) mTextViewField.get(tabView);
//                    tabView.setPadding(0, 0, 0, 0);
//                    //因为我想要的效果是   字多宽线就多宽，所以测量mTextView的宽度
//                    int width = 0;
//                    width = mTextView.getWidth();
//                    if (width == 0) {
//                        mTextView.measure(0, 0);
//                        width = mTextView.getMeasuredWidth();
//                    }
//                    //设置tab左右间距为10dp  注意这里不能使用Padding 因为源码中线的宽度是根据 tabView的宽度来设置的
//                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tabView.getLayoutParams();
//                    params.width = width;
//                    params.leftMargin = dp10;
//                    params.rightMargin = dp10;
//                    tabView.setLayoutParams(params);
//
//                    tabView.postInvalidate();
//                }
//
//            } catch (NoSuchFieldException e) {
//                e.printStackTrace();
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            }
//        });
//
//    }


}
