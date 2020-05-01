package com.mmkjflb.mmloan.viewmodel.maimaiborrow;

import android.content.Context;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mmkjflb.mmloan.R;
import com.mmkjflb.mmloan.databinding.ItemRvMmborrowBinding;
import com.mmkj.baselibrary.util.RxViewUtil;

import java.util.List;

/**
 * Created by Administrator on 2017/8/15.
 */

public class BorrowAdapter extends RecyclerView.Adapter<BorrowAdapter.ViewHolder> {
	private Context mContext;
	private List<String> mList;
	private RecycleItemClick recycleItemClick;
	private int selectPosition = 0;

	public BorrowAdapter(Context mContext, List<String> mList, RecycleItemClick recycleItemClick) {
		this.mContext = mContext;
		this.mList = mList;
		this.recycleItemClick = recycleItemClick;
	}

	public void setSelectPosition(int selectPosition) {
		this.selectPosition = selectPosition;
		notifyDataSetChanged();
	}

	@Override
	public BorrowAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		return new BorrowAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_mmborrow, parent, false));
	}

	@Override
	public void onBindViewHolder(final BorrowAdapter.ViewHolder holder, int position) {
		holder.bindItem(position, selectPosition, mList.get(position), recycleItemClick);
	}

	@Override
	public int getItemCount() {
		return mList.size();
	}

	public static class ViewHolder extends RecyclerView.ViewHolder {
		private ItemRvMmborrowBinding mDataBinding;

		public ViewHolder(View itemView) {
			super(itemView);
			mDataBinding = DataBindingUtil.bind(itemView);
		}

		private void bindItem(final int position, final int selectionPositon, String applyDataListBean, final RecycleItemClick recycleItemClick) {
			mDataBinding.tvBorrowPeriod.setText(applyDataListBean);
			if (selectionPositon == position) {
				mDataBinding.tvBorrowPeriod.setSelected(true);
			} else {
				mDataBinding.tvBorrowPeriod.setSelected(false);
			}
			RxViewUtil.clicks(mDataBinding.tvBorrowPeriod)
					.subscribe(o -> recycleItemClick.itemClick(position));
		}
	}

	public interface RecycleItemClick {
		void itemClick(int position);
	}
}


