package com.mmkjflb.mmloan.viewmodel.idcertification;

import android.content.Context;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.mmkjflb.mmloan.R;
import com.mmkjflb.mmloan.databinding.ItemIdcertificateBinding;
import com.syhmmfqphl.xyxlibrary.utils.DensityUtil;
import com.mmkj.baselibrary.util.DeviceUtils;
import com.mmkj.baselibrary.util.RxViewUtil;

import java.io.File;
import java.util.List;

/**
 * date: 2018/3/2 14:43
 * author: xuyexiang
 * title:
 */

public class IdCertificateAdapter extends RecyclerView.Adapter<IdCertificateAdapter.ViewHolder> {
    private Context mContext;
    private IdCertificateClick idCertificateClick;
    private List<File> idCertificateUploadEntityList;

    public IdCertificateAdapter(Context mContext, List<File> idCertificateUploadEntityList, IdCertificateClick idCertificateClick) {
        this.idCertificateClick = idCertificateClick;
        this.mContext = mContext;
        this.idCertificateUploadEntityList = idCertificateUploadEntityList;
    }


    @Override
    public IdCertificateAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View views = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_idcertificate, parent, false);
        views.getLayoutParams().width = DeviceUtils.getDisplayMetrics(mContext).widthPixels / 2 - DensityUtil.dp2px(mContext.getResources(), 8);
        return new IdCertificateAdapter.ViewHolder(views);
    }

    @Override
    public void onBindViewHolder(final IdCertificateAdapter.ViewHolder holder, int position) {
        holder.bindItem(mContext, idCertificateUploadEntityList, position, idCertificateClick);
    }

    @Override
    public int getItemCount() {
        return (idCertificateUploadEntityList != null && idCertificateUploadEntityList.size() > 0) ? idCertificateUploadEntityList.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ItemIdcertificateBinding mDataBinding;

        public ViewHolder(View itemView) {
            super(itemView);
            mDataBinding = DataBindingUtil.bind(itemView);
        }

        private void bindItem(Context mContext, List<File> idCertificateUploadEntityList, final int position, final IdCertificateClick idCertificateClick) {
            if (position == 0) {
                Glide.with(mContext).asBitmap().load(idCertificateUploadEntityList.get(position)).centerCrop().error(R.drawable.upload_face)
                        .placeholder(R.drawable.upload_face).diskCacheStrategy(DiskCacheStrategy.ALL).into(mDataBinding.ivItemIdcertificate);
                mDataBinding.tvItemIdcertificateMsg.setText(mContext.getResources().getString(R.string.upload_face));
            } else if (position == 1) {
                Glide.with(mContext).asBitmap().load(idCertificateUploadEntityList.get(position)).centerCrop().error(R.drawable.upload_person)
                        .placeholder(R.drawable.upload_person).diskCacheStrategy(DiskCacheStrategy.ALL).into(mDataBinding.ivItemIdcertificate);
                mDataBinding.tvItemIdcertificateMsg.setText(mContext.getResources().getString(R.string.upload_person));
            }
            RxViewUtil.clicks(mDataBinding.ivItemIdcertificate).subscribe(o -> idCertificateClick.itemClick(position));
        }
    }

    public interface IdCertificateClick {
        void itemClick(int position);
    }
}

