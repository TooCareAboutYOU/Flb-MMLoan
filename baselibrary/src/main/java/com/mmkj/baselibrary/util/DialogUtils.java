package com.mmkj.baselibrary.util;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.mmkj.baselibrary.R;


/**
 * Data: 2018/9/5 16:10
 * Author: Xuyexiang
 * Title:
 */
public class DialogUtils {
    public static Dialog initDialog(Context context, View view){
        Dialog commonDialog = new Dialog(context, R.style.DialogCommon);
        commonDialog.setContentView(view);
        commonDialog.setCanceledOnTouchOutside(false);
        Window window = commonDialog.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER); // 此处可以设置dialog显示的位置
        window.setWindowAnimations(R.style.ZoomInZoomOutAnimation); // 添加动画
        commonDialog.show();
        return commonDialog;
    }
}
