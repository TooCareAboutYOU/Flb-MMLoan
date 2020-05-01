package com.mmkjflb.mmloan.viewmodel.repay;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mmkjflb.mmloan.R;
import com.mmkjflb.mmloan.model.entity.RepayDialogDetailEntity;

import java.util.List;

import androidx.annotation.Nullable;

public class RepayDetailAdapter extends BaseQuickAdapter<RepayDialogDetailEntity, BaseViewHolder> {

    private Context mContext;

    public RepayDetailAdapter(Context context, @Nullable List<RepayDialogDetailEntity> data) {
        super(R.layout.layout_repay_dialog_detail, data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, RepayDialogDetailEntity item) {
        helper.setText(R.id.acTv_Title, item.getTitle());
        helper.setText(R.id.acTv_Number, item.getNum());
        helper.setTextColor(R.id.acTv_Number,item.getTxtColorType() == 1 ? mContext.getResources().getColor(R.color.black) : mContext.getResources().getColor(R.color.gray_d2d4d8));
        helper.setTextColor(R.id.acTv_Title,item.getTxtColorType() == 1 ? mContext.getResources().getColor(R.color.black) : mContext.getResources().getColor(R.color.gray_d2d4d8));
    }
}
