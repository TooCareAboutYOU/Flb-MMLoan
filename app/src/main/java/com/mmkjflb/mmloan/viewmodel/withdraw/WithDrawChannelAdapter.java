package com.mmkjflb.mmloan.viewmodel.withdraw;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mmkjflb.mmloan.R;
import com.mmkjflb.mmloan.model.entity.PaymentShopListEntity;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;


/**
 * @author zhangshuai
 * 动态显示借款渠道分类
 */
public class WithDrawChannelAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    private Context mContext;

    public WithDrawChannelAdapter(Context context, @Nullable List<String> data) {
        super(R.layout.item_withdraw, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        RequestManager manager = Glide.with(mContext);
        RequestBuilder<Drawable> load = null;
        switch (item) {
            case "1": {
                load = manager.load(R.drawable.img_cebuana_lhuillier_1_normal);
                break;
            }
            case "6": {
                load = manager.load(R.drawable.img_gcash_6_normal);
                break;
            }
            case "9": {
                load = manager.load(R.drawable.img_rd_pawnshop_9_normal);
                break;
            }
            case "10": {
                load = manager.load(R.drawable.img_cebuana_lhuillier_10_normal);
                break;
            }
            case "11": {
                load = manager.load(R.drawable.img_palawan_11_normal);
                break;
            }
            default:
                break;
        }
        if (load != null) {
            load.into((AppCompatImageView) helper.getView(R.id.acImg_ChannelIcon));
        }
    }
}
