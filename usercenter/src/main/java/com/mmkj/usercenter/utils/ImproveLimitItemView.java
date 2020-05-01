package com.mmkj.usercenter.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import com.mmkj.usercenter.R;


/**
 * Author: wendjia
 * Time: 2018\8\10 0010
 * Description:
 **/
public class ImproveLimitItemView extends ConstraintLayout {
	private Context context;
	private ImageView imageView;
	private TextView tv1, tv2;


	public ImproveLimitItemView(Context context) {
		super(context);
		this.context = context;
		initView();
	}

	public ImproveLimitItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		initView();
	}

	public ImproveLimitItemView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		this.context = context;
		initView();
	}

	private void initView() {
//		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//		ViewImproveLimitItemBinding mBinding = DataBindingUtil.inflate(inflater, R.layout.view_improve_limit_item, null, false);
//		addView(mBinding.getRoot());
		LayoutInflater.from(context).inflate(R.layout.view_improve_limit_item, this);
		imageView = findViewById(R.id.iv);
		tv1 = findViewById(R.id.tv1);
		tv2 = findViewById(R.id.tv2);
	}


	public void setTvOneText(@NonNull final String tv1Text) {

		tv1.setText(tv1Text);
	}


	public void setTvTwoText(@NonNull final String tv2Text) {
		tv2.setText(tv2Text);
	}

	public void setImgRes(@NonNull final Drawable drawable) {
		imageView.setImageDrawable(drawable);
	}
}
