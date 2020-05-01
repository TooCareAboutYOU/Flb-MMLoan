package com.mmkjflb.mmloan.utils.view;

import android.content.Context;
import androidx.appcompat.widget.AppCompatTextView;
import android.text.TextUtils;
import android.util.AttributeSet;


/**
 * @author zhangshuai
 * hint属性文字长度 大于 text属性文字长度，去掉多余长度
 *
 */
public class SmartHintTextView extends AppCompatTextView {

    private CharSequence mSmartHint;

    public SmartHintTextView(Context context) {
        super(context);
    }

    public SmartHintTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SmartHintTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(text, type);
        if (TextUtils.isEmpty(text)
                && TextUtils.isEmpty(getHint())
                && !TextUtils.isEmpty(mSmartHint)) {
            setHint(mSmartHint);
        } else if (!TextUtils.isEmpty(text)
                && !TextUtils.isEmpty(getHint())) {
            mSmartHint = getHint();
            setHint(null);
        }
    }
}
