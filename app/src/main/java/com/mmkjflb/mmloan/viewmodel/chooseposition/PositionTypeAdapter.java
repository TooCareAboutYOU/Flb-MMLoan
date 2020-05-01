package com.mmkjflb.mmloan.viewmodel.chooseposition;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mmkjflb.mmloan.BR;
import com.mmkjflb.mmloan.R;
import com.mmkjflb.mmloan.model.entity.PositionEntity;
import com.mmkj.baselibrary.util.ArrayUtils;

import java.util.List;


/**
 * Author: wendjia
 * Time: 2018\7\31
 * Description:
 **/
public class PositionTypeAdapter extends BaseQuickAdapter<PositionEntity, PositionTypeAdapter.PositionTypeHolder> {

	private long selectId = 0L;

	public PositionTypeAdapter(List<PositionEntity> data) {
		super(R.layout.item_positiontype, data);

	}

	@Override
	protected void convert(PositionTypeHolder helper, PositionEntity item) {
		ViewDataBinding binding = helper.getBinding();
		binding.setVariable(BR.name, item.getyName());
		binding.setVariable(BR.select, selectId == item.getId());
		binding.executePendingBindings();
	}

	@Override
	protected PositionTypeHolder createBaseViewHolder(View view) {
		return new PositionTypeHolder(view);
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

	public class PositionTypeHolder extends BaseViewHolder {

		public PositionTypeHolder(View view) {
			super(view);
		}

		public ViewDataBinding getBinding() {
			return (ViewDataBinding) getConvertView().getTag(R.id.BaseQuickAdapter_databinding_support);
		}
	}

	public long getSelectId() {
		return selectId;
	}

	public void setSelectId(long selectId) {
		this.selectId = selectId;
		notifyDataSetChanged();
	}


	public void setNewData(@Nullable List<PositionEntity> data, PositionEntity positionEntity) {
		super.setNewData(data);
		if (positionEntity == null) {
			PositionEntity entity = ArrayUtils.getItem(data, 0);
			if (entity != null) {
				selectId = entity.getId();
			} else {
				selectId = 0L;
			}
		} else {
			selectId = positionEntity.getPid();
		}
		notifyDataSetChanged();
	}

}


