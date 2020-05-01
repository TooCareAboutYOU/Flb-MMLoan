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
public class PositionInfoAdapter extends BaseQuickAdapter<PositionEntity, PositionInfoAdapter.PositionTypeHolder> {

	private long selectId = -1;

	public PositionInfoAdapter(List<PositionEntity> data) {
		super(R.layout.item_position_info, data);

	}

	@Override
	protected void convert(PositionTypeHolder helper, PositionEntity item) {
		ViewDataBinding binding = helper.getBinding();
		helper.addOnClickListener(R.id.checkbox);
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

	public PositionEntity getSelectItemt() {
		List<PositionEntity> list = getData();
		if (selectId > 0 && !ArrayUtils.isEmpty(list)) {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getId() == selectId) {
					return list.get(i);
				}
			}
		}
		return null;
	}

	public void setSelectItemAndNotify(int selectItem) {
		PositionEntity entity = getItem(selectItem);
		if (entity != null) {
			selectId = entity.getId();
		}
		notifyDataSetChanged();
	}


	public void setNewData(@Nullable List<PositionEntity> data, PositionEntity positionEntity) {
		super.setNewData(data);
		if (positionEntity == null) {
			setSelectItemAndNotify(-1);
		} else {
			selectId = positionEntity.getId();
			notifyDataSetChanged();
		}

	}
}


