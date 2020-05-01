package com.mmkj.baselibrary.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mmkj.baselibrary.R;


/**
 * Author: wendjia
 * Time: 2018\8\13 0013
 * Description:
 **/
public class LinearItem extends RelativeLayout {
	private Context context;
	private TextView tvKey;
	private TextView tvValue;

	public LinearItem(Context context) {
		super(context);
		this.context = context;
		initView();
	}

	public LinearItem(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		initView();
	}

	public LinearItem(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		this.context = context;
		initView();
	}

	private void initView() {
		LayoutInflater.from(context).inflate(R.layout.view_linear_item, this);
		tvKey = findViewById(R.id.tv_key);
		tvValue = findViewById(R.id.tv_value);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		//获取宽和高的SpecMode和SpecSize
		int mWidth = 0;
		int mHeight = 0;
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);
		if (widthMode == MeasureSpec.EXACTLY) {
			// Parent has told us how big to be. So be it.
			mWidth = widthSize;
		} else {//没有设置宽度，先默认显示四个0的宽度
			mWidth = widthSize;
		}
		if (heightMode == MeasureSpec.EXACTLY) {
			// Parent has told us how big to be. So be it.
			mHeight = getChildAt(0).getMeasuredHeight();
		} else {//没有设置高度，先默认显示40dp
			mHeight = getChildAt(0).getMeasuredHeight();
		}
		setMeasuredDimension(mWidth, mHeight);
	}

	public void setKey(String key) {
		tvKey.setText(key);
	}

	public void setValue(String value) {
		tvValue.setText(value);
	}
}
