package com.mmkjflb.mmloan.viewmodel.guide;

import android.content.Intent;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.mmkj.baselibrary.RouteConstant;
import com.mmkjflb.mmloan.R;
import com.mmkjflb.mmloan.base.BaseActivity;
import com.mmkjflb.mmloan.databinding.ActivityGuideBinding;
import com.mmkjflb.mmloan.model.entity.AdviseEntity;
import com.mmkjflb.mmloan.viewmodel.login.LoginActivity;
import com.syhmmfqphl.xyxlibrary.utils.DensityUtil;
import com.mmkj.baselibrary.annotation.ActivityFragmentInject;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/8/10.
 */
@Route(path = RouteConstant.GUIDE)
@ActivityFragmentInject(contentViewId = R.layout.activity_guide)
public class GuideActivity extends BaseActivity<GuidePresenter, ActivityGuideBinding> implements GuideContract.View {
    private ArrayList<View> list = new ArrayList<View>();
    private int[] images = {R.drawable.guide1, R.drawable.guide2, R.drawable.guide3, R.drawable.guide4};
    private int lastIndex = 0;

    @Override
    protected void initViews() {
        getActivityComponent().inject(this);
        addViewPagerItem();
        setDots();
        mDataBinding.viewPagerGuide.setAdapter(new GuideAdapter());
        mDataBinding.viewPagerGuide.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            /*
             * 当某一页被选择的时候会运行的方法
             * 参数代表的是被选择的item的索引位置
             * */
            @Override
            public void onPageSelected(int position) {
                if (lastIndex != -1) {
                    mDataBinding.linearlayoutDot.getChildAt(lastIndex).setBackgroundResource(R.drawable.guide_unselected_shape);
                }
                mDataBinding.linearlayoutDot.getChildAt(position).setBackgroundResource(R.drawable.guide_selected_shape);
                lastIndex = position;
            }

            /** 只要页面正在滑动，就会运行此方法
             * 1:当前屏幕上显示的页面的position
             * 2：当前横向移动的百分比，
             * 3:当前拖动的横向移动的像素值
             * */
            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                if (mDataBinding.viewPagerContainer != null) {
                    mDataBinding.viewPagerContainer.invalidate();
                }
            }

            /*
             * 当滑动状态改变的时候，触发
             * 参数代表当前的滑动状态
             * */
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }


    /**
     * 添加viewpager的item
     */
    private void addViewPagerItem() {
        for (int i = 0; i < images.length; i++) {
            View view = getLayoutInflater().inflate(R.layout.item_viewpager_guide, null);
            ImageView iv_guide = view.findViewById(R.id.iv_guide);
            iv_guide.setImageResource(images[i]);
            TextView tv = view.findViewById(R.id.btn_goto_main);
            tv.setVisibility(View.VISIBLE);
            list.add(view);
        }
    }

    private void setDots() {
        for (int i = 0; i < images.length; i++) {
            TextView tv_dot = new TextView(GuideActivity.this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(DensityUtil.dp2px(this.getResources(), 8), DensityUtil.dp2px(this.getResources(), 8));
            layoutParams.setMargins(0, 0, DensityUtil.dp2px(this.getResources(), 8), 0);
            //设置小圆点大小
            tv_dot.setLayoutParams(layoutParams);
            if (i == 0) {
                tv_dot.setBackgroundResource(R.drawable.guide_selected_shape);
            } else {
                tv_dot.setBackgroundResource(R.drawable.guide_unselected_shape);
            }
            mDataBinding.linearlayoutDot.addView(tv_dot);
        }
    }

    @Override
    public void getGoogleAdviseSuc(AdviseEntity baseBean) {

    }

    class GuideAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        /*初始化item时运行，即当前item展示在眼前的时候运行
         *返回值代表着item要显示的view对象
         * 1:当前ViewPager上显示的item的视图集合，视图组，可理解为ViewPager本身
         * 2：当前显示在屏幕上的item的位置
         * */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View v = list.get(position);
            container.addView(v);
            TextView tv = v.findViewById(R.id.btn_goto_main);
            tv.setOnClickListener(v12 -> goToMain());
            if (position == list.size() - 1) {
                ImageView iv_guide = v.findViewById(R.id.iv_guide);
                iv_guide.setOnClickListener(v1 -> goToMain());
            }
            return v;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(list.get(position));
        }
    }

    private void goToMain() {
//        startActivity(new Intent(GuideActivity.this, LoginActivity.class));
        ARouter.getInstance().build(RouteConstant.LOGIN).navigation();
        finish();
    }
}
