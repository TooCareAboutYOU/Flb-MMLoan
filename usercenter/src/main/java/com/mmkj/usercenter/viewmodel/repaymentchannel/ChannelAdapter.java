package com.mmkj.usercenter.viewmodel.repaymentchannel;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mmkj.usercenter.R;
import com.mmkj.usercenter.databinding.ItemRvChannelBinding;
import com.mmkj.usercenter.model.entity.RepayChannelBean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/15.
 */

public class ChannelAdapter extends RecyclerView.Adapter<ChannelAdapter.ViewHolder> {
	private List<RepayChannelBean.DataBean.ChannelBean> mList;

	public ChannelAdapter(List<RepayChannelBean.DataBean.ChannelBean> mList) {
		this.mList = mList;
	}


	@Override
	public ChannelAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		return new ChannelAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_channel, parent, false));
	}

	@Override
	public void onBindViewHolder(final ChannelAdapter.ViewHolder holder, int position) {
		holder.bindItem(mList.get(position).getChannelName());
	}

	@Override
	public int getItemCount() {
		return mList.size();
	}

	public static class ViewHolder extends RecyclerView.ViewHolder {
		private ItemRvChannelBinding mDataBinding;

		public ViewHolder(View itemView) {
			super(itemView);
			mDataBinding = DataBindingUtil.bind(itemView);
		}

		private void bindItem(String applyDataListBean) {
			mDataBinding.tvBorrowPeriod.setText(applyDataListBean);
		}
	}

	public interface RecycleItemClick {
		void itemClick(int position);
	}
}


