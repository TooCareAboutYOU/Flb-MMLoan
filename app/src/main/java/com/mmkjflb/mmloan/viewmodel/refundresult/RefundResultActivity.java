package com.mmkjflb.mmloan.viewmodel.refundresult;

import android.annotation.SuppressLint;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.mmkj.baselibrary.RouteConstant;
import com.mmkj.baselibrary.annotation.HeadToolbar;
import com.mmkj.baselibrary.annotation.HeaderBuilder;
import com.mmkj.baselibrary.base.DataBindingActivity;
import com.mmkj.baselibrary.util.RxViewUtil;
import com.mmkjflb.mmloan.R;
import com.mmkjflb.mmloan.databinding.ActivityRefundResultBinding;
import com.mmkjflb.mmloan.viewmodel.refundcode.RefundCodeActivity;
import com.syhmmfqphl.xyxlibrary.utils.ExtraKeys;
import com.syhmmfqphl.xyxlibrary.utils.JumpManager;


/**
 * 退保证金结果页面
 */
@Route(path = RouteConstant.RETURN_RESULT)
public class RefundResultActivity extends DataBindingActivity<ActivityRefundResultBinding>{

    @Autowired
    public int typeCode;

    @Autowired
    public String msg;

    @Override
    protected HeadToolbar getHeadToolbar() {
        return new HeaderBuilder().mContentViewId(R.layout.activity_refund_result).build();
    }

    @Override
    protected void initViews() {
//        int typeCode = getIntent().getIntExtra(ExtraKeys.Key_Msg1, 1);
        praseLoan(typeCode);
        initClicks();
    }

    private void praseLoan(int typeCode) {
        if (typeCode == -1 || typeCode == -2) {//-1为失败
            mDataBinding.ivResult.setImageResource(R.drawable.loan_fail);
            mDataBinding.tvResultShow.setText(R.string.refund_failed);
            mDataBinding.ivBack.setVisibility(View.VISIBLE);
            mDataBinding.tvFinish.setVisibility(View.GONE);
            mDataBinding.tvTime.setVisibility(View.GONE);
            mDataBinding.tvFailedHint.setText(getResources().getString(R.string.refund_result_fail));
        } else {//其余为成功(处理中)
            mDataBinding.ivResult.setImageResource(R.drawable.success);
            mDataBinding.tvResultShow.setText(R.string.refund_processing);
            mDataBinding.ivBack.setVisibility(View.GONE);
            mDataBinding.tvFinish.setVisibility(View.VISIBLE);
            mDataBinding.tvViewVacode.setVisibility(View.VISIBLE);
            mDataBinding.tvTime.setVisibility(View.VISIBLE);
            mDataBinding.tvFailedHint.setText(msg);
        }
    }

    @SuppressLint("CheckResult")
    private void initClicks() {
        RxViewUtil.clicks(mDataBinding.ivBack).subscribe(o -> finish());
        RxViewUtil.clicks(mDataBinding.tvFinish).subscribe(o -> finish());
        RxViewUtil.clicks(mDataBinding.tvViewVacode).subscribe(o -> {
//            JumpManager.jumpTo(RefundResultActivity.this, RefundCodeActivity.class);
            ARouter.getInstance().build(RouteConstant.REFUND_CODE).navigation();
        });
    }
}
