package com.mmkjflb.mmloan.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import androidx.core.content.ContextCompat;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.TextAppearanceSpan;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.bigkoo.pickerview.OptionsPickerView;
import com.mmkj.baselibrary.util.PermissionPageUtils;
import com.mmkjflb.mmloan.R;

/**
 * Created by Administrator on 2017/7/25.
 */

public class UiUtils {
    public static void tvDifferStyle(Context context, TextView textView, String str1, String str2, int style1, int style2) {
        SpannableString styledText = new SpannableString(str1 + str2);
        styledText.setSpan(new TextAppearanceSpan(context, style1), 0, str1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        styledText.setSpan(new TextAppearanceSpan(context, style2), str1.length(), str1.length() + str2.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(styledText, TextView.BufferType.SPANNABLE);
    }

    public static void showBoldType(Context context, TextView textView, String str1, String str2) {
        SpannableString styledText = new SpannableString(str1 + str2);
        styledText.setSpan(new TextAppearanceSpan(context, R.style.TvSecurityBlackStyle), 0, str1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        styledText.setSpan(new TextAppearanceSpan(context, R.style.TvSecurityOrangeStyle), str1.length(), str1.length() + str2.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(styledText, TextView.BufferType.SPANNABLE);
    }

    public static void showBoldType(Context context, TextView textView, String str1, String str2,String str3) {
        String allStr=str1+str2+str3;
        SpannableString styledText = new SpannableString(allStr);
        styledText.setSpan(new TextAppearanceSpan(context, R.style.TvColor666666Theme), 0, str1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        styledText.setSpan(new TextAppearanceSpan(context, R.style.TvColorFF931FTheme), str1.length(), allStr.length()-str3.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        styledText.setSpan(new TextAppearanceSpan(context, R.style.TvColor666666Theme), allStr.length()-str3.length(), allStr.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(styledText, TextView.BufferType.SPANNABLE);
    }

    /**
     * 选择器样式设置
     */
    public static OptionsPickerView initOptionPicker(Context context, OptionsPickerView.OnOptionsSelectListener selectListener, int title) {
        return new OptionsPickerView.Builder(context, selectListener)
                .setTitleText(context.getResources().getString(title))
                .setContentTextSize(18)
                .setTitleSize(18)
                .setDividerColor(ContextCompat.getColor(context, R.color.gray_999999))//设置分割线的颜色
                .setSelectOptions(0)//默认选中项
                .setBgColor(ContextCompat.getColor(context, R.color.white))
                .setTitleBgColor(ContextCompat.getColor(context, R.color.white))
                .setTitleColor(ContextCompat.getColor(context, R.color.black_333333))
                .setCancelColor(ContextCompat.getColor(context, R.color.gray_999999))
                .setSubmitColor(ContextCompat.getColor(context, R.color.color_global))
                .setTextColorCenter(ContextCompat.getColor(context, R.color.black_333333))
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setBackgroundId(0x66000000) //设置外部遮罩颜色
                .build();

    }

    /**
     * 设置权限
     */
    public static void openSetting(final Context context, int contentRes) {
        new MaterialDialog.Builder(context)
                .title(R.string.warm_tips)
                .content(contentRes)
                .positiveText(R.string.dialog_sure)
                .onPositive((dialog, which) -> {
                    dialog.dismiss();
                    new PermissionPageUtils(context).jumpPermissionPage();
                })
                .negativeText(R.string.dialog_cancel)
                .onNegative((dialog, which) ->dialog.dismiss())
                .show();
    }

    public static void setDrawPadding(Drawable drawable, TextView textView, int position) {//0左 1上 2 右    3 下
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight()); // 这一步必须要做,否则不会显示下一步箭头图片.
        if (position == 0) {
            textView.setCompoundDrawables(drawable, null, null, null);
        } else if (position == 1) {
            textView.setCompoundDrawables(null, drawable, null, null);
        } else if (position == 2) {
            textView.setCompoundDrawables(null, null, drawable, null);
        } else {
            textView.setCompoundDrawables(null, null, null, drawable);
        }
    }


    public static String getPeriod(Context context, int periodvalue) {
        String periodstring = "";
        switch (periodvalue) {
            case 0:
                periodstring = context.getResources().getString(R.string.day);
                break;
            case 1:
                periodstring = context.getResources().getString(R.string.borrow_month);
                break;
            case 2:
                periodstring = context.getResources().getString(R.string.borrow_year);
                break;
            default:
                break;
        }
        return periodstring;
    }
}