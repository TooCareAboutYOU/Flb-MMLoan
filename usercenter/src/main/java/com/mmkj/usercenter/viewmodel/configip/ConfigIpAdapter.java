package com.mmkj.usercenter.viewmodel.configip;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mmkj.usercenter.R;

import java.util.List;

/**
 * Author: wendjia
 * Time: 2018\9\12 0012
 * Description:
 **/
public class ConfigIpAdapter extends BaseQuickAdapter<IPEntity, ConfigIpAdapter.ConfigIpHolder> {


	public ConfigIpAdapter(@Nullable List<IPEntity> data) {
		super(R.layout.item_configip, data);
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
	protected void convert(ConfigIpHolder helper, IPEntity item) {
		ViewDataBinding binding = helper.getBinding();

		binding.executePendingBindings();
	}

	public class ConfigIpHolder extends BaseViewHolder {

		public ConfigIpHolder(View view) {
			super(view);
		}

		public ViewDataBinding getBinding() {
			return (ViewDataBinding) getConvertView().getTag(R.id.BaseQuickAdapter_databinding_support);
		}
	}
}
