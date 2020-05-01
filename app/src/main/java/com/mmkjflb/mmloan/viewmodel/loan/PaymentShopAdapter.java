package com.mmkjflb.mmloan.viewmodel.loan;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mmkjflb.mmloan.R;
import com.mmkjflb.mmloan.model.entity.PaymentShopListEntity;

import java.util.List;
import java.util.Objects;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;


public class PaymentShopAdapter extends BaseQuickAdapter<PaymentShopListEntity, BaseViewHolder> {

    private Context mContext;
    private int lastposition = -1;
    private List<PaymentShopListEntity> list;

    public PaymentShopAdapter(Context context, @Nullable List<PaymentShopListEntity> data) {
        super(R.layout.item_payment_shop_list, data);
        list = data;
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, PaymentShopListEntity item) {
        helper.setBackgroundRes(R.id.cl_ContainerView, item.isFlag() ? R.drawable.select_payment_shop_list_selected : R.drawable.select_payment_shop_list_unselected);
        switchImg(item.isFlag(), item.getWithdrawalsShop(), helper.getView(R.id.acImg_ChannelIcon));
    }

    private void switchImg(boolean isFlag, int shopChannel, AppCompatImageView imgView) {
        RequestManager manager = Glide.with(mContext);
        RequestBuilder<Drawable> load = null;
        switch (shopChannel) {
            case 1: {
                load = manager.load(isFlag ? R.drawable.img_cebuana_lhuillier_1_selected : R.drawable.img_cebuana_lhuillier_1_normal);
                break;
            }
            case 6: {
                load = manager.load(isFlag ? R.drawable.img_gcash_6_selected : R.drawable.img_gcash_6_normal);
                break;
            }
            case 9: {
                load = manager.load(isFlag ? R.drawable.img_rd_pawnshop_9_selected : R.drawable.img_rd_pawnshop_9_normal);
                break;
            }
            case 10: {
                load = manager.load(isFlag ? R.drawable.img_cebuana_lhuillier_10_selected : R.drawable.img_cebuana_lhuillier_10_normal);
                break;
            }
            case 11: {
                load = manager.load(isFlag ? R.drawable.img_palawan_11_selected : R.drawable.img_palawan_11_normal);
                break;
            }
            default:
                break;
        }
        if (load != null) {
            load.into(imgView);
        }
    }

    public void singleChoose(int pos) {
        if (lastposition != pos) {
            list.get(pos).setFlag(true);
            if (lastposition != -1) {
                list.get(lastposition).setFlag(false);
            }
            notifyDataSetChanged();
            lastposition = pos;
        }
    }

}
