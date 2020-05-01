package com.mmkj.usercenter.utils;

import android.content.Context;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import com.mmkj.usercenter.R;


/**
 * Author: wendjia
 * Time: 2018\8\13 0013
 * Description:
 **/
public class ImproveLimitProgress extends ConstraintLayout {
	private Context context;
	private TextView tv2;
	private ImageView iv;
	int[] bgRess = new int[]{R.drawable.improve_limit_degree0,
			R.drawable.improve_limit_degree1,
			R.drawable.improve_limit_degree2,
			R.drawable.improve_limit_degree3,
			R.drawable.improve_limit_degree4,
			R.drawable.improve_limit_degree5,};

	public ImproveLimitProgress(Context context) {
		super(context);
		this.context = context;
		initView();
	}


	public ImproveLimitProgress(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		initView();
	}

	public ImproveLimitProgress(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		this.context = context;
		initView();
	}

	private void initView() {
		LayoutInflater.from(context).inflate(R.layout.view_improve_limit_progress, this);
		iv =  findViewById(R.id.iv);
		tv2 = findViewById(R.id.tv2);
	}

	//两个参数是父View给的测量建议值MeasureSpec,代码执行到onMeasure(w,h),说明MyCircleView的measure(w,h)在执行中
//	@Override
//	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//		//super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//		int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);//宽的测量大小，模式
//		int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
//		int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);//高的测量大小，模式
//		int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
//		int w = widthSpecSize;   //定义测量宽，高(不包含测量模式),并设置默认值，查看View#getDefaultSize可知
//		int h = heightSpecSize;
//		//处理wrap_content的几种特殊情况
//		if (widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST) {
//			w = DensityUtil.dp2px(getResources(),280);
//			h = DensityUtil.dp2px(getResources(),160);
//		} else if (widthSpecMode == MeasureSpec.AT_MOST) {
//			//只要宽度布局参数为wrap_content， 宽度给固定值200dp(处理方式不一，按照需求来)
//			w = DensityUtil.dp2px(getResources(),280);
//			//按照View处理的方法，查看View#getDefaultSize可知
//			h = heightSpecSize;
//		} else if (heightSpecMode == MeasureSpec.AT_MOST) {
//			w = widthSpecSize;
//			h = DensityUtil.dp2px(getResources(),160);
//		}
//		//给两个字段设置值，完成最终测量
//		setMeasuredDimension(w, h);
//	}


	public void setProgress(int progress) {
		iv.setImageResource(bgRess[progress]);

	}

	public void setProgress(int progress, String text) {
		setProgress(progress);
		tv2.setText(text);
	}

	public void setProgress(int progress, String text, int sp) {
		setProgress(progress);
		tv2.setText(text);
		tv2.setTextSize(sp);
	}

	public void setTv2Text(String str) {
		tv2.setText(str);
	}
}
