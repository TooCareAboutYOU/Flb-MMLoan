package com.mmkj.usercenter.viewmodel.loanlist;

import android.content.Context;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mmkj.usercenter.BR;
import com.mmkj.usercenter.R;
import com.mmkj.usercenter.model.entity.LoanListData;

import java.util.List;

public class LoanListAdapter extends BaseQuickAdapter<LoanListData, LoanListAdapter.LoanListHolder> {
    private Context con;
    private boolean mode;

    public LoanListAdapter(Context con, List<LoanListData> data, boolean mode) {
        super(R.layout.item_my_borrow_detail, data);
        this.con = con;
        this.mode = mode;
    }

    @Override
    protected LoanListHolder createBaseViewHolder(View view) {
        return new LoanListHolder(view);
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
    protected void convert(LoanListHolder helper, LoanListData item) {
        ViewDataBinding binding = helper.getBinding();
        binding.setVariable(BR.data, item);
        binding.setVariable(BR.mode, mode);
        TextView vaCode = binding.getRoot().findViewById(R.id.look_va);
        View view = binding.getRoot().findViewById(R.id.diver);
        if (mode) {
            vaCode.setVisibility(View.VISIBLE);
            view.setVisibility(View.VISIBLE);
            switch (item.getWithdrawalNoStatus()) {
                case -2:
                    //过期
                    hideBottom(vaCode, view);
                    break;
                case -1:
                    //使用过
                    vaCode.setText(con.getString(R.string.loan_success));
                    vaCode.setTextColor(con.getResources().getColor(R.color.gray_d1d1d1));
                    vaCode.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                    showBottom(vaCode, view,item);
                    break;
                case 0:
                    //生成中
                    hideBottom(vaCode, view);
                    break;
                case 1:
                    showBottom(vaCode, view,item);
                    vaCode.setText(con.getString(R.string.check_withdraw_code));
                    vaCode.setTextColor(con.getResources().getColor(R.color.color_global));
                    vaCode.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.repay_look_va, 0);
                    helper.addOnClickListener(R.id.look_va);
                    //生成成功
                    break;
            }
        } else {
            hideBottom(vaCode, view);
        }
        binding.executePendingBindings();
    }

    private void hideBottom(TextView vaCode, View view) {
        vaCode.setVisibility(View.GONE);
        view.setVisibility(View.GONE);
    }

    private void showBottom(TextView vaCode, View view, LoanListData item) {
        vaCode.setVisibility(View.VISIBLE);
        if (item.getWithdrawChannel()==6){
            vaCode.setVisibility(View.GONE);
        }
        view.setVisibility(View.VISIBLE);
    }

    public class LoanListHolder extends BaseViewHolder {

        public LoanListHolder(View view) {
            super(view);
        }

        public ViewDataBinding getBinding() {
            return (ViewDataBinding) getConvertView().getTag(R.id.BaseQuickAdapter_databinding_support);
        }
    }
}