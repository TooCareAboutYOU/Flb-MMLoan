package com.mmkjflb.mmloan.viewmodel.bindgcash;

import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mmkjflb.mmloan.BR;
import com.mmkjflb.mmloan.R;
import com.mmkjflb.mmloan.model.entity.ProductInfoBean;

import java.util.List;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

/**
 * User: Lazy_xu
 * Data: 2019/08/19
 * Description:
 */
public class BindGcashAdapter extends BaseQuickAdapter<ProductInfoBean, BindGcashAdapter.ViewHolder> {
    private int type;
    private int chosePosition = -1;


    public BindGcashAdapter(List<ProductInfoBean> data, int type) {
        super(R.layout.item_productinfo, data);
        this.type = type;
    }

    public void setChosePosition(int choseosition) {
        this.chosePosition = choseosition;
    }

    @Override
    protected void convert(ViewHolder helper, ProductInfoBean item) {
        ViewDataBinding binding = helper.getBinding();
        binding.setVariable(BR.productinfo, item);
        binding.setVariable(BR.canChose, type == 1);
        if (helper.getAdapterPosition() == chosePosition) {
            helper.setImageResource(R.id.ivProductChose, R.drawable.product_selected);
        } else {
            helper.setImageResource(R.id.ivProductChose, R.drawable.product_default);
        }
        helper.addOnClickListener(R.id.ivProductChose);
        binding.executePendingBindings();
    }

    @Override
    protected View getItemView(int layoutResId, ViewGroup parent) {
        ViewDataBinding binding = DataBindingUtil.inflate(mLayoutInflater, layoutResId, parent, false);
        if (binding == null) {
            return super.getItemView(layoutResId, parent);
        }
        View view = binding.getRoot();
        view.setTag(R.id.BaseQuickAdapter_databinding_support, binding);
        return view;
    }

    public static class ViewHolder extends BaseViewHolder {

        public ViewHolder(View view) {
            super(view);
        }

        public ViewDataBinding getBinding() {
            return (ViewDataBinding) itemView.getTag(R.id.BaseQuickAdapter_databinding_support);
        }
    }
}

