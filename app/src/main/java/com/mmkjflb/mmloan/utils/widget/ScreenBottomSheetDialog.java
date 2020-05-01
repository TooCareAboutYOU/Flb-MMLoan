package com.mmkjflb.mmloan.utils.widget;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.mmkjflb.mmloan.R;

import androidx.annotation.NonNull;


public class ScreenBottomSheetDialog extends BottomSheetDialog {
    int peekHeight = 0;

    public ScreenBottomSheetDialog(@NonNull Context context) {
        super(context);
    }

    public ScreenBottomSheetDialog(@NonNull Context context, int theme) {
        super(context, theme);
    }

    protected ScreenBottomSheetDialog(@NonNull Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    public void show() {
        super.show();
        if (peekHeight == 0) {
            View container = findViewById(R.id.ll_channels);
            container.measure(0, 0);
            peekHeight = container.getMeasuredHeight();

            FrameLayout bottomSheet = findViewById(com.google.android.material.R.id.design_bottom_sheet);
            BottomSheetBehavior behavior = BottomSheetBehavior.from(bottomSheet);
            behavior.setPeekHeight(peekHeight);
        }
    }
}
