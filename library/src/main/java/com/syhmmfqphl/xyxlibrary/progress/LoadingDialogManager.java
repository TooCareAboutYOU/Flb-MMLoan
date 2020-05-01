package com.syhmmfqphl.xyxlibrary.progress;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import com.mmkjflb.xyxlibrary.R;

public class LoadingDialogManager {
	private int index = 0;
	public ProgressDialog mProgressDialog;
	private boolean mCancellable = false;

	public LoadingDialogManager(Context context) {
		mProgressDialog = new ProgressDialog(context);
		this.mProgressDialog.setCancelable(mCancellable);
		this.mProgressDialog.setCanceledOnTouchOutside(false);
	}

	public static LoadingDialogManager create(Context context) {
		return new LoadingDialogManager(context);
	}
	public void setCancellable(boolean isCancelable) {
		this.mCancellable = isCancelable;
	}

	public void show() {
		if (this.index == 0) {
			if (this.mProgressDialog != null) {
				this.mProgressDialog.show();
			}
		}
		++this.index;
	}

	public void dismiss() {
		try {
			if (this.index <= 1) {
				if (this.mProgressDialog != null) {
					this.mProgressDialog.dismiss();
				}
				this.index = 0;
			} else {
				--this.index;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void destroy() {
		if (this.mProgressDialog != null) {
			this.mProgressDialog.dismiss();
			this.mProgressDialog = null;
		}
	}

	private class ProgressDialog extends Dialog {
		private Context context;
		private float mDimAmount;

		public ProgressDialog(Context context) {
			super(context);
			this.context = context;
		}

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			setContentView(R.layout.loading);
			Window window = getWindow();
			window.setBackgroundDrawable(new ColorDrawable(0));
			window.setGravity(Gravity.CENTER);
			window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
			WindowManager.LayoutParams layoutParams = window.getAttributes();
			mDimAmount = 0.5f;
			layoutParams.dimAmount = mDimAmount;
			window.setAttributes(layoutParams);
			setCanceledOnTouchOutside(false);
			setCancelable(false);
//			window.setWindowAnimations(R.style.ZoomInZoomOutAnimation); // 添加动画
			window.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
		}
	}
}
