package com.mmkj.usercenter.viewmodel.loanlist;

import android.content.Context;
import android.graphics.drawable.Drawable;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import com.mmkj.usercenter.R;

/**
 * Author: wendjia
 * Time: 2018\9\14 0014
 * Description:
 **/
public class LoanListItemView extends ConstraintLayout {
	private Context context;
	private TextView tvTitle;
	private TextView tvValue;
	private ImageView iv;

	public LoanListItemView(Context context) {
		super(context);
		this.context = context;
		initView();
	}

	public LoanListItemView(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		initView();
	}

	public LoanListItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		this.context = context;
		initView();
	}


	private void initView() {
		LayoutInflater.from(context).inflate(R.layout.view_loanitem_view, this);
		tvTitle = findViewById(R.id.tv_title);
		tvValue = findViewById(R.id.tv_value);
		iv = findViewById(R.id.iv);
	}

	public void setValueSize(float size) {
		tvValue.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
	}

	public void setValueText(String str) {
		tvValue.setText(str);
	}

	public void setValueColor(int color) {
		tvValue.setTextColor(color);
	}

	public void setTitleText(String str) {
		tvTitle.setText(str);
	}

	public void setTitleSize(float size) {
		tvTitle.setTextSize(size);
	}

	public void setTitleColor(int color) {
		tvTitle.setTextColor(color);
	}

	public void setEnable(boolean enable) {
		tvValue.setEnabled(enable);
	}

	public void setImg(Drawable img) {
		iv.setImageDrawable(img);
	}
}
