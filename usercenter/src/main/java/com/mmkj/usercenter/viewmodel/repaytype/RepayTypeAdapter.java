package com.mmkj.usercenter.viewmodel.repaytype;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mmkj.usercenter.BR;
import com.mmkj.usercenter.R;
import com.mmkj.usercenter.model.entity.RepayTypeEntity;

import java.util.List;

public class RepayTypeAdapter extends BaseQuickAdapter<RepayTypeEntity, RepayTypeAdapter.RepayTypeHolder> {


    public RepayTypeAdapter(@Nullable List<RepayTypeEntity> data) {
        super(R.layout.item_repaytype, data);
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


    @Override
    protected void convert(RepayTypeHolder helper, RepayTypeEntity item) {
        ViewDataBinding binding = helper.getBinding();
        binding.setVariable(BR.data, item);
        binding.executePendingBindings();
    }

    public class RepayTypeHolder extends BaseViewHolder {

        public RepayTypeHolder(View view) {
            super(view);
        }

        public ViewDataBinding getBinding() {
            return (ViewDataBinding) getConvertView().getTag(R.id.BaseQuickAdapter_databinding_support);
        }
    }
}
