package com.mmkjflb.mmloan.viewmodel.loanresult;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.mmkj.baselibrary.RouteConstant;
import com.mmkj.baselibrary.annotation.ActivityFragmentInject;
import com.mmkj.baselibrary.base.DataBindingActivity;
import com.mmkj.baselibrary.util.RxViewUtil;
import com.mmkjflb.mmloan.R;
import com.mmkjflb.mmloan.databinding.ActivityLoanresultBinding;
import com.syhmmfqphl.xyxlibrary.utils.ExtraKeys;

/**
 * Created by Administrator on 2017/10/16.
 */
@Route(path = RouteConstant.LOAN_RESULT)
@ActivityFragmentInject(contentViewId = R.layout.activity_loanresult)
public class LoanResultActivity extends DataBindingActivity<ActivityLoanresultBinding> {

    @Autowired
    public String txtDescribe;

    @Autowired
    public boolean isSucess;

    @Override
    protected void initViews() {
        RxViewUtil.clicks(mDataBinding.ivBack).subscribe(o -> finish());
        RxViewUtil.clicks(mDataBinding.tvOk).subscribe(o -> finish());
//        if (getIntent() != null) {
            mDataBinding.tvDescribeShow.setText(txtDescribe);
//            boolean isSucess = getIntent().getBooleanExtra(ExtraKeys.Key_Msg2, false);
            if (isSucess) {
                mDataBinding.ivResult.setImageResource(R.drawable.loan_result_suc);
                mDataBinding.tvResultShow.setText("Loan Succeed");
                mDataBinding.tvDescribeShow.setText("The loan amount has been credited to your\n GCash account");
            }
//        }
    }
}
