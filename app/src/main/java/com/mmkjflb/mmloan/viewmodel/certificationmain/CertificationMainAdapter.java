package com.mmkjflb.mmloan.viewmodel.certificationmain;


import android.content.Context;

import androidx.appcompat.widget.AppCompatTextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mmkjflb.mmloan.R;
import com.mmkjflb.mmloan.model.entity.ItemCertificationBean;

import java.util.List;

/**
 * @author zhangshuai
 */
public class CertificationMainAdapter extends BaseMultiItemQuickAdapter<ItemCertificationBean, BaseViewHolder> {

    private Context mContext;

    public CertificationMainAdapter(Context context, List<ItemCertificationBean> list) {
        super(list);
        this.mContext = context;
        addItemType(ItemCertificationBean.TEXT, R.layout.item_authentication_text);
        addItemType(ItemCertificationBean.CARD, R.layout.item_authentication);
    }

    @Override
    protected void convert(BaseViewHolder helper, ItemCertificationBean item) {
        switch (helper.getItemViewType()) {
            case ItemCertificationBean.TEXT: {
                helper.setText(R.id.tv_authentication_title, item.getTitle());
                break;
            }
            case ItemCertificationBean.CARD: {
                helper.setImageResource(R.id.acIv_Icon, item.getIcon())
                        .setText(R.id.acTv_Title, item.getTitle())
                        .setTextColor(R.id.acTv_Title, item.getTxtColor() == 0
                                ? this.mContext.getResources().getColor(R.color.gray_cccccc)
                                : this.mContext.getResources().getColor(R.color.black));

                helper.setText(R.id.acTv_status, item.getTxtColor() == 0 || item.getTxtColor() == 1 ? R.string.go_to_certification : R.string.complete)
                        .setTextColor(R.id.acTv_status, item.getTxtColor() == 0 ? this.mContext.getResources().getColor(R.color.gray_cccccc) :
                                (item.getTxtColor() == 1 ? this.mContext.getResources().getColor(R.color.blue_3b9bff) : this.mContext.getResources().getColor(R.color.green_24c789)));

                helper.itemView.setEnabled(item.getTxtColor() != 0);

                AppCompatTextView statusTv = helper.getView(R.id.acTv_status);
                statusTv.setSelected(item.getTxtColor() == 2);
                break;
            }
            default:
                break;
        }

    }
}
