package com.mmkjflb.mmloan.utils.view;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import androidx.core.content.ContextCompat;
import androidx.appcompat.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.mmkjflb.mmloan.R;

/**
 * Created by Administrator on 2017/3/21.
 */

public class DeleteEditText extends AppCompatEditText {

    private Context mContext;
    private Drawable imgDel_Bule;

    public DeleteEditText(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public DeleteEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
        init();
    }

    public DeleteEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    public void init() {
        imgDel_Bule = ContextCompat.getDrawable(mContext,R.drawable.icon_delete);
        setDrawble();
        // 对EditText文本状态监听
        this.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                setDrawble();
            }
        });
    }

    /***
     * 设置图片
     */
    public void setDrawble() {
        if (this.length() < 1) {
            this.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        } else {
            this.setCompoundDrawablesWithIntrinsicBounds(null, null, imgDel_Bule, null);
        }
    }

    /***
     * 设置删除事件监听
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (imgDel_Bule != null && event.getAction() == MotionEvent.ACTION_UP) {
            int eventX = (int) event.getRawX();
            int eventY = (int) event.getRawY();
            Rect rect = new Rect();
            getGlobalVisibleRect(rect);
            rect.left = rect.right - 50;
            if (rect.contains(eventX, eventY))
                setText("");
        }
        return super.onTouchEvent(event);
    }
}
