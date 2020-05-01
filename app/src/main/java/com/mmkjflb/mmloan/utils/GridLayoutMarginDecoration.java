package com.mmkjflb.mmloan.utils;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;

import com.syhmmfqphl.xyxlibrary.utils.DensityUtil;

import androidx.recyclerview.widget.RecyclerView;


/**
 * @author zhangshuai
 *
 */
public class GridLayoutMarginDecoration extends RecyclerView.ItemDecoration {
    private int mCount, mMargin;

    /**
     * 间隔
     * @param count 每行个数
     * @param space 间隔
     */
    public GridLayoutMarginDecoration(Context context, int count, int space) {
        mCount = count;
        mMargin = DensityUtil.dp2px(context.getResources(), space);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = mMargin;
        outRect.bottom = mMargin;
        //由于每行都只有2个，所以第一个都是2的倍数，把左边距设为0   
        if (parent.getChildLayoutPosition(view) % mCount == 0) {
            outRect.left = 0;
        }
    }
}
