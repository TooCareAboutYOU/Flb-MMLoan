package com.mmkj.usercenter.viewmodel.loanlist;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by
 * Date: On 2018/1/16
 * Author: wangjieyan
 * Email: wangjieyan9308@163.com
 */

public class OnItemClickLinearLayout {


    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(ViewGroup viewGroup, final OnItemClickListener listener){
        int endIndex =viewGroup.getChildCount();//如果是使用默认底部，则结束的下标是到底部之前
        for (int i = 0; i< endIndex;i++){
            View view = viewGroup.getChildAt(i);
            final int position = i;
			view.setOnClickListener(v -> listener.onItemClick(v, position));
        }
    }
}
