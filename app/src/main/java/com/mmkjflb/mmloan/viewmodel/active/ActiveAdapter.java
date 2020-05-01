package com.mmkjflb.mmloan.viewmodel.active;

import android.app.Activity;
import android.content.Context;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.mmkj.baselibrary.RouteConstant;
import com.mmkjflb.mmloan.R;
import com.mmkjflb.mmloan.app.Constants;
import com.mmkjflb.mmloan.databinding.ItemActiveBinding;
import com.mmkjflb.mmloan.model.entity.QueryActivityEntity;
import com.mmkjflb.mmloan.viewmodel.zhimaauth.WebViewActivity;
import com.syhmmfqphl.xyxlibrary.utils.JumpManager;
import com.mmkj.baselibrary.util.RxViewUtil;

import java.util.List;

/**
 * Created by Administrator on 2017/8/15.
 */

public class ActiveAdapter extends RecyclerView.Adapter<ActiveAdapter.ViewHolder> {
    private Context mContext;
    private List<QueryActivityEntity> mList;

    public ActiveAdapter(Context mContext, List<QueryActivityEntity> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    public void setData(List<QueryActivityEntity> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    @Override
    public ActiveAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ActiveAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_active,  parent, false));
    }

    @Override
    public void onBindViewHolder(final ActiveAdapter.ViewHolder holder, int position) {
        holder.bindItem(mContext, mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ItemActiveBinding mDataBinding;
        private Context mContext;

        public ViewHolder(View itemView) {
            super(itemView);
            mDataBinding = DataBindingUtil.bind(itemView);
        }

        private void bindItem(final Context context, final QueryActivityEntity queryActivityEntity) {
            this.mContext = context;
            Glide.with(mContext).asBitmap().load(queryActivityEntity.getActivityLogo()).into(mDataBinding.ivItemFound);
            mDataBinding.tvTitle.setText(queryActivityEntity.getRemark());
			RxViewUtil.clicks(mDataBinding.ivItemFound).subscribe(o -> {
//			    JumpManager.jumpToKey1((Activity) mContext, WebViewActivity.class, queryActivityEntity.getAddress())
                ARouter.getInstance().build(RouteConstant.WEBVIEW).withString("loadUrl", queryActivityEntity.getAddress()).navigation();
            });
        }

    }
}


