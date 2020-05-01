package com.syhmmfqphl.xyxlibrary.toast;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.mmkjflb.xyxlibrary.R;
import com.syhmmfqphl.xyxlibrary.utils.DensityUtil;

public class ToastUtil {
	private Context context;
	private TextView tvToastView;
	private static ToastUtil toastUtil = null;
	private Toast toast = null;
	private View view;

	private ToastUtil(Context context) {
		this.context = context;
		view = LayoutInflater.from(context).inflate(R.layout.toast_main, null);
		tvToastView = (TextView) view.findViewById(R.id.tv_toast);
		toast = new Toast(context);
		toast.setDuration(Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, DensityUtil.dp2px(context.getResources(), 80));
		toast.setView(view);
	}

	public static ToastUtil initToast(Context context) {
		if (toastUtil == null) {
			toastUtil = new ToastUtil(context);
		}
		return toastUtil;
	}

	public void showToast(String tvString) {
		tvToastView.setText(tvString);
		toast.show();
	}

	public void showToast(int resId) {
		showToast(context.getResources().getString(resId));
	}

	public void showSuccess(String tvString) {
		tvToastView.setText(tvString);
		toast.show();
	}

	public void showSuccess(int resId) {
		showSuccess(context.getResources().getString(resId));
	}

	public void showError(String tvString) {
		tvToastView.setText(tvString);
		toast.show();
	}

	public void showError(int resId) {
		showError(context.getResources().getString(resId));
	}


}

