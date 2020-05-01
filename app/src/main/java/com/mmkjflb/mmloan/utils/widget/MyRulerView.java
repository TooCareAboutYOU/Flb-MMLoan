package com.mmkjflb.mmloan.utils.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.mmkjflb.mmloan.R;

/**
 * Author: wendjia
 * Time: 2018\8\13 0013
 * Description:
 **/
public class MyRulerView extends HorizontalScrollView {
	private Context context;
	private int count = 5;

	public MyRulerView(Context context) {
		super(context);
		this.context = context;
		initView(count);
	}

	public MyRulerView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		initView(count);
	}

	public MyRulerView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		this.context = context;
		initView(count);
	}

	private void initView(int size) {
		LinearLayout linearLayout = new LinearLayout(context);
		linearLayout.setOrientation(LinearLayout.HORIZONTAL);

		for (int i = 0; i < size; i++) {
			ImageView imageView = new ImageView(context);
			imageView.setImageResource(R.drawable.rule_item);
			linearLayout.addView(imageView);
		}
		addView(linearLayout);
	}
}
