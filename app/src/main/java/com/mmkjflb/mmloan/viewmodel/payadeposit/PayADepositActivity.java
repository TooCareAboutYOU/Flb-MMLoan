package com.mmkjflb.mmloan.viewmodel.payadeposit;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.jakewharton.rxbinding2.view.RxView;
import com.mmkj.baselibrary.RouteConstant;
import com.mmkj.baselibrary.annotation.HeadToolbar;
import com.mmkj.baselibrary.annotation.HeaderBuilder;
import com.mmkj.baselibrary.base.DataBindingActivity;
import com.mmkj.baselibrary.util.StringUtils;
import com.mmkjflb.mmloan.R;
import com.mmkjflb.mmloan.databinding.ActivityPayAdepositBinding;


@Route(path = RouteConstant.PAY_A_DEPOSIT)
public class PayADepositActivity extends DataBindingActivity<ActivityPayAdepositBinding> {

    public static final String TAG = "PayADepositActivity";

    @Autowired(name = "isVa")
    boolean isVa;

    @Autowired(name = "cashDeposit")
    String cashDeposit;

    @Autowired(name = "feeProcedurePay")
    String feeProcedurePay;

    @Autowired(name = "feeProcedureRepay")
    String feeProcedureRepay;

    @Autowired(name = "repayAmount")
    String repayAmount;

    @Autowired(name = "payments")
    String payments;

    //c测试数据
//    boolean isVa=false;
//    String cashDeposit="2500.00";
//    String feeProcedurePay="140.00";
//    String feeProcedureRepay="25.20";
//    String repayAmount="2334.80";

    @Override
    protected HeadToolbar getHeadToolbar() {
        return new HeaderBuilder().mContentViewId(R.layout.activity_pay_adeposit).build();
    }

    @SuppressLint("CheckResult")
    @Override
    protected void initViews() {
        ARouter.getInstance().inject(this);
        mDataBinding.acTvMoney.setText(StringUtils.cutOutLastThree(StringUtils.doubleZheng(Double.valueOf(payments))));

        RxView.clicks(mDataBinding.ivBack).subscribe(o -> finish());
        RxView.clicks(mDataBinding.acTvDetail).subscribe(o -> showRepayDetail());
        RxView.clicks(mDataBinding.btnPayRightnow).subscribe(o -> {
            if (isVa) {
                ARouter.getInstance().build(RouteConstant.PAYMENT_CODE).withBoolean("isVa",isVa).navigation();
            }else {
                ARouter.getInstance().build(RouteConstant.PAY_A_DEPOSIT_RESULT).withBoolean("isVa",isVa).navigation();
            }
            finish();
        });
    }

    private void showRepayDetail() {
        Dialog dialogDetail = new Dialog(this, R.style.DialogCommon);
        View view = View.inflate(this, R.layout.dialog_payadeposit_detail, null);
        ((TextView) view.findViewById(R.id.tv_the_actual_amount_paid_value)).setText(StringUtils.cutOutLastThree(StringUtils.doubleZheng(Double.valueOf(cashDeposit))));
        ((TextView)view.findViewById(R.id.tv_pay_the_deposit_fee_value)).setText(StringUtils.cutOutLastThree(StringUtils.doubleZheng(Double.valueOf(feeProcedurePay))));
        ((TextView)view.findViewById(R.id.tv_refund_fee_value)).setText(StringUtils.cutOutLastThree(StringUtils.doubleZheng(Double.valueOf(feeProcedureRepay))));
        ((TextView)view.findViewById(R.id.tv_the_actual_refund_amount)).setText(StringUtils.cutOutLastThree(StringUtils.doubleZheng(Double.valueOf(repayAmount))));
        view.findViewById(R.id.iv_repaydetail_dismiss).setOnClickListener(v -> dialogDetail.dismiss());

        dialogDetail.setContentView(view);
        dialogDetail.setCanceledOnTouchOutside(true);
        Window window = dialogDetail.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER); // 此处可以设置dialog显示的位置
        window.setWindowAnimations(R.style.ZoomInZoomOutAnimation); // 添加动画
        dialogDetail.show();
    }



}
