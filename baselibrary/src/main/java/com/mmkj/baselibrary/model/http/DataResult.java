/**
 * Copyright (c) 2013 An Yaming,  All Rights Reserved
 */
package com.mmkj.baselibrary.model.http;

import android.app.Activity;
import android.content.Context;

import com.afollestad.materialdialogs.MaterialDialog;
import com.alibaba.android.arouter.launcher.ARouter;
import com.syhmmfqphl.xyxlibrary.utils.ExtraKeys;
import com.mmkj.baselibrary.R;
import com.mmkj.baselibrary.RouteConstant;
import com.mmkj.baselibrary.base.DataBindingActivity;
import com.mmkj.baselibrary.model.entitry.BaseBean;
import com.mmkj.baselibrary.util.Constants;


public class DataResult {
	public static boolean isSuccessToast(Context context, BaseBean baseBean) {
		if (baseBean == null) {
			return false;
		}
		if (baseBean.getCode() == 1) {
			((DataBindingActivity) context).toast(baseBean.getMsg());
			return true;
		} else if (baseBean.getCode() == 9999) {
			getLoginDialog(context);
		} else {
			((DataBindingActivity) context).toast(baseBean.getMsg());
		}
		return false;
	}


	private static MaterialDialog dialog = null;

	public static void getLoginDialog(final Context context) {
		if (null == dialog || !dialog.isShowing()) {
			dialog = new MaterialDialog.Builder(context)
					.title(context.getResources().getString(R.string.login_error))        //登录失败
					.content(context.getResources().getString(R.string.login_again))        //请重新登录
					.positiveText(R.string.confirm)
					.canceledOnTouchOutside(false)
					.cancelable(false)
					.onPositive((dialog, which) -> {
						if (context instanceof Activity) {
							ARouter.getInstance()
									.build(RouteConstant.LOGIN)
									.withBoolean("isTimeOut", true)
									.navigation((Activity) context, Constants.CODE_LOGINTIMEOUT);
						}


					}).show();
		}

	}

	public static boolean isSuccessUnToast(Context context, BaseBean baseBean) {
		if (baseBean == null) {
			return false;
		}
		if (baseBean.getCode() == 1) {
			return true;
		} else if (baseBean.getCode() == 9999) {
			getLoginDialog(context);
		} else {
			((DataBindingActivity) context).toast(baseBean.getMsg());
		}
		return false;
	}


	public static boolean isLoginUnToastAll(Context context, BaseBean baseBean) {
		if (baseBean == null) {
			return false;
		}
		if (baseBean.getCode() == 9999) {
			getLoginDialog(context);
			return false;
		}
		return true;
	}

	public static boolean isSuccessUnToastAll(Context context, BaseBean baseBean) {
		if (baseBean == null) {
			return false;
		}
		if (baseBean.getCode() == 1) {
			return true;
		} else if (baseBean.getCode() == 9999) {
			getLoginDialog(context);
		}
		return false;
	}

	public static boolean isSuccessDialog(Context context, BaseBean baseBean) {
		if (baseBean == null) {
			return false;
		}
		if (baseBean.getCode() == 1) {
			return true;
		} else if (baseBean.getCode() == 9999) {
			getLoginDialog(context);
		} else {
			new MaterialDialog.Builder(context)
					.content(baseBean.getMsg())
					.positiveText(R.string.iknow)
					.canceledOnTouchOutside(false)
					.cancelable(false)
					.onPositive((dialog, which) -> dialog.dismiss())
					.show();
		}
		return false;
	}
}
