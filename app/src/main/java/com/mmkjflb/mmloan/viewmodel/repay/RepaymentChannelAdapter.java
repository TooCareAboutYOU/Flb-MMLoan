package com.mmkjflb.mmloan.viewmodel.repay;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mmkjflb.mmloan.R;
import com.mmkjflb.mmloan.model.entity.PaymentShopListEntity;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;


public class RepaymentChannelAdapter extends BaseQuickAdapter<PaymentShopListEntity, BaseViewHolder> {

    private static final String TAG = "MRepaymentChanne";

    private Context mContext;
    private int lastposition = -1;
    private List<PaymentShopListEntity> list;

    public RepaymentChannelAdapter(Context context, @Nullable List<PaymentShopListEntity> data) {
        super(R.layout.item_repayment_channels, data);
        list = data;
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, PaymentShopListEntity item) {
        helper.setBackgroundRes(R.id.cl_ContainerView, item.isFlag() ? R.drawable.shape_radius5_frame_2fc4ca : R.drawable.shape_radius5_stroke_efefef);
        helper.setImageResource(R.id.acImg_Channel_Statues, item.isFlag() ? R.drawable.cb_position_select : R.drawable.cb_position_unselect);
        switchImg(mContext, item.getId(), helper.getView(R.id.acImg_ChannelIcon));
    }

    /**
     * 没有占位图 ，故选用最基本方法是实现
     *
     * @param context
     * @param shopChannel
     * @param imgView
     */
    public void switchImg(Context context, int shopChannel, AppCompatImageView imgView) {
//        RequestManager manager = Glide.with(mContext);
//        RequestBuilder<Drawable> load = null;
        switch (shopChannel) {
            case 1: {
//                load = manager.load(R.drawable.img_channels_ml_1);
                imgView.setImageResource(R.drawable.img_channels_ml_1);
                break;
            }
            case 6: {
//                load = manager.load(R.drawable.img_channels_gcash_6);
                imgView.setImageResource(R.drawable.img_channels_gcash_6);
                break;
            }
            case 9: {
//                load = manager.load(R.drawable.img_channels_rd_9);
                imgView.setImageResource(R.drawable.img_channels_rd_9);
                break;
            }
            case 12: {
//                load = manager.load(R.drawable.img_channels_711_12);
                imgView.setImageResource(R.drawable.img_channels_711_12);
                break;
            }
            default:
                break;
        }
//        if (load != null) {
//            load.into(imgView);
//        }
    }

    public void multiChoose(int pos, @Nullable AppCompatTextView rightNow) {
        if (lastposition != pos) {
            list.get(pos).setFlag(true);
            if (lastposition != -1) {
                list.get(lastposition).setFlag(false);
            }
        } else {
            list.get(pos).setFlag(!list.get(pos).isFlag());
        }
        lastposition = pos;
        if (rightNow != null) {
            rightNow.setEnabled(list.get(pos).isFlag());
        }
        notifyDataSetChanged();
    }

    public void singleChoose(int pos) {
        if (lastposition != pos) {
            list.get(pos).setFlag(true);
            if (lastposition != -1) {
                list.get(lastposition).setFlag(false);
            }
        } else {
            list.get(pos).setFlag(true);
        }
        lastposition = pos;
        notifyDataSetChanged();
    }
}
