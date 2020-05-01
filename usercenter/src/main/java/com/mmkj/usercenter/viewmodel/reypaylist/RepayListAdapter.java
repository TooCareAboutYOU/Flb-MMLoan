package com.mmkj.usercenter.viewmodel.reypaylist;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mmkj.baselibrary.util.DateUtils;
import com.mmkj.baselibrary.util.StringUtils;
import com.mmkj.usercenter.BR;
import com.mmkj.usercenter.R;
import com.mmkj.usercenter.model.entity.PaymentListData;

import java.util.List;

public class RepayListAdapter extends BaseQuickAdapter<PaymentListData,RepayListAdapter.RepayListHolder>{

    public RepayListAdapter(List<PaymentListData> data) {
		super(R.layout.item_repaylist, data);

    }

    @Override
    protected RepayListHolder createBaseViewHolder(View view) {
        return new RepayListHolder(view);
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
    protected void convert(RepayListHolder helper, PaymentListData item) {
        ViewDataBinding binding = helper.getBinding();
        binding.setVariable(BR.amount,StringUtils.cutOutLastThree(StringUtils.doubleZheng(item.getRealRepaymentCorpus()+item.getRealRepaymentInterest())));
        binding.setVariable(BR.repay_date, DateUtils.showYMDTime(item.getRealRepaymentTime()));
        binding.executePendingBindings();
    }
    public class RepayListHolder extends BaseViewHolder {

        public RepayListHolder(View view) {
            super(view);
        }

        public ViewDataBinding getBinding() {
            return (ViewDataBinding) getConvertView().getTag(R.id.BaseQuickAdapter_databinding_support);
        }
    }
}
