package com.mmkjflb.mmloan.viewmodel.repay;

import android.annotation.SuppressLint;
import android.app.Dialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.functions.Consumer;

import android.content.DialogInterface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.jakewharton.rxbinding2.widget.RxCheckedTextView;
import com.jakewharton.rxbinding2.widget.RxCompoundButton;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.mmkj.usercenter.model.entity.PaymentData;
import com.mmkjflb.mmloan.R;
import com.mmkjflb.mmloan.base.BaseActivity;
import com.mmkjflb.mmloan.databinding.ActivityRepayBinding;
import com.mmkjflb.mmloan.databinding.DialogRepayDetailBinding;
import com.mmkjflb.mmloan.databinding.IncludeRepaymentChannelsBinding;
import com.mmkjflb.mmloan.model.entity.PaymentShopListEntity;
import com.mmkjflb.mmloan.model.entity.RepayDialogDetailEntity;
import com.mmkjflb.mmloan.model.entity.RepayEntity;
import com.mmkjflb.mmloan.model.entity.RepaymentShopEntity;
import com.mmkjflb.mmloan.model.http.DataResult;
import com.mmkj.baselibrary.RouteConstant;
import com.mmkj.baselibrary.annotation.ActivityFragmentInject;
import com.mmkj.baselibrary.model.entitry.BaseBean;
import com.mmkj.baselibrary.util.DateUtils;
import com.mmkj.baselibrary.util.PreferenceUtils;
import com.mmkj.baselibrary.util.RxViewUtil;
import com.mmkj.baselibrary.util.StringUtils;
import com.mmkjflb.mmloan.utils.GridLayoutMarginDecoration;
import com.mmkjflb.mmloan.utils.widget.ScreenBottomSheetDialog;
import com.mmkjflb.mmloan.viewmodel.bankrepay.BankRepayActivity;
import com.mmkjflb.mmloan.viewmodel.main.MainActivity;
import com.syhmmfqphl.xyxlibrary.utils.ExtraKeys;
import com.syhmmfqphl.xyxlibrary.utils.JumpManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 还款页面
 * 建议用RecyclerView多布局优化界面
 */
@Route(path = RouteConstant.REPAYS)
@ActivityFragmentInject(contentViewId = R.layout.activity_repay, loadingTargetView = R.id.linear_loading)
public class RepayActivity extends BaseActivity<RepayPresenter, ActivityRepayBinding> implements RepayContract.View {
    private RepayEntity.InfoBean billsBean;
    private RepayEntity.PayDetail payDetail;
    private double feeProcedurePay;
    private double feeProcedureRepay;
    private RepaymentChannelAdapter mAdapter;  //动态布局渠道
    private int mPosition = -1;  //渠道对应的位置
    private int selectChannelId = -1; //渠道id
    private int nowChannel = -1;   //当前支付渠道
    private boolean isClick;   //是否选中了银行转账
    private int repayType = -1;  //0：渠道还款；1：银行转账
    private Map<String, Object> map = null;  //传参
    private static final String SKY_PAY = "SKYPAY";

    private String hintInfo;


    //借款标
    @Autowired
    public int bidId;

    private void initToolbar(String title) {
        //设置动态标题
        setSupportActionBar(mDataBinding.includeTitle.commonincludeToolbar);
        if (getSupportActionBar() != null) {
            TextView tvTitleMain = findViewById(com.mmkj.baselibrary.R.id.tv_title_main);
            if (tvTitleMain != null) {
                tvTitleMain.setText(title);
            }
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.back_black);
            getSupportActionBar().setTitle("");
        }
    }

    @Override
    protected void initViews() {
        getActivityComponent().inject(this);
        initClicks();
        hintInfo = String.format("After the 7-11 repayment is successful, please wait for  processing. If there is any issue, please contact customer service: %s", PreferenceUtils.getString(PreferenceUtils.SERVICE_PHONE, ""));
        initToolbar(getResources().getString(R.string.repay));

        mDataBinding.includeRepayChannels.clLoanBank.setVisibility(View.VISIBLE);

        mPresenter.queryBillsInfo(PreferenceUtils.getUserSessionid());

        list = new ArrayList<>();
        mAdapter = new RepaymentChannelAdapter(this, list);

        loadChannels(mDataBinding.includeRepayChannels.rcChannels);
    }

    private void showAmount(double amount) {
        mDataBinding.tvRepayAmount.setText(StringUtils.cutOutLastThree(StringUtils.doubleZheng(amount)));
    }

    @SuppressLint("CheckResult")
    private void initClicks() {
        RxViewUtil.clicks(mDataBinding.tvRepayDetail).subscribe(o -> showRepayDetail());
        RxViewUtil.clicks(mDataBinding.btnRepayRightnow).subscribe(o -> {
            if (repayType == 0) {
                submitRepay();
                mPresenter.submitActiveRepay(map);
            } else if (repayType == 1) {
                ARouter.getInstance().build(RouteConstant.BANK_REPAY).withInt("bidId", bidId).withInt("billId", billsBean.getId()).navigation();
                finish();
            }
        });
        RxViewUtil.clicks(mDataBinding.includeType1.acTvReplaceChannel).subscribe(o -> {
            /**
             * 更换渠道
             */
            ARouter.getInstance()
                    .build(RouteConstant.REPAY_CHANNELS)
                    .withInt("bidId", bidId)
                    .withInt("billId", billsBean.getId())
                    .withInt("nowChannel", nowChannel)
                    .navigation();
        });
        mDataBinding.includeRepayChannels.clRepayThroughBank.setOnClickListener(v -> {
            isClick = !isClick;
            repayType = 1;
            changeBank(isClick, mDataBinding.includeRepayChannels.clRepayThroughBank, mDataBinding.includeRepayChannels.acIvRepayThroughBank);
            if (mPosition != -1) {
                list.get(mPosition).setFlag(false);
                mAdapter.notifyItemChanged(mPosition);
            }
        });
    }

    /**
     * 改变银行转账背景和图标
     */
    private void changeBank(final boolean status, ConstraintLayout containerView, AppCompatImageView img) {
        containerView.setBackground(
                status ? getResources().getDrawable(R.drawable.shape_radius5_frame_2fc4ca) :
                        getResources().getDrawable(R.drawable.shape_radius5_frame_efefef));

        img.setImageDrawable(
                status ? getResources().getDrawable(R.drawable.cb_position_select) :
                        getResources().getDrawable(R.drawable.cb_position_unselect));
        mDataBinding.btnRepayRightnow.setEnabled(status);

    }

    /**
     * 提交还款
     */
    private void submitRepay() {
        map = new HashMap<>(3);
        map.put("sessionId", PreferenceUtils.getString(PreferenceUtils.USER_SESSIONID, ""));
        map.put("billId", billsBean.getId());
        map.put("payClient", selectChannelId);
    }

    private Dialog dialogDetail;

    @Override
    public void onError(int res) {
        loadingError(view -> mPresenter.queryBillsInfo(PreferenceUtils.getString(PreferenceUtils.USER_SESSIONID, "")));
    }

    /**
     * 显示还款详情弹框
     */
    private void showRepayDetail() {
        View view = View.inflate(this, R.layout.dialog_repay_detail, null);
        DialogRepayDetailBinding repayDetailBinding = DataBindingUtil.bind(view);
        repayDetailBinding.rvDetail.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        List<RepayDialogDetailEntity> data = new ArrayList<>();
        data.add(new RepayDialogDetailEntity(R.string.corpus, StringUtils.cutOutLastThree(StringUtils.doubleZheng(billsBean.getRepaymentCorpus())), 1));
        if (billsBean.getRepaymentInterest() != 0.00) {
            data.add(new RepayDialogDetailEntity(R.string.interest, StringUtils.cutOutLastThree(StringUtils.doubleZheng(billsBean.getRepaymentInterest())), 1));
        }
        if (billsBean.getOverdueFine() > 0) {
            data.add(new RepayDialogDetailEntity(R.string.overdue_penalty, StringUtils.cutOutLastThree(StringUtils.doubleZheng(billsBean.getOverdueFine())), 1));
        }
        double repaid = billsBean.getRealRepaymentCorpus() + billsBean.getRealRepaymentInterest();
        if (repaid > 0) {
            data.add(new RepayDialogDetailEntity(R.string.repaid, "-" + StringUtils.cutOutLastThree(StringUtils.doubleZheng(repaid)), 1));
        }
        data.add(new RepayDialogDetailEntity(R.string.service_fee, StringUtils.cutOutLastThree(StringUtils.doubleZheng(billsBean.getFeeRate())), 2));
        data.add(new RepayDialogDetailEntity(R.string.withdraw_fee, StringUtils.cutOutLastThree(StringUtils.doubleZheng(feeProcedurePay)), 2));
        data.add(new RepayDialogDetailEntity(R.string.repay_fee, StringUtils.cutOutLastThree(StringUtils.doubleZheng(feeProcedureRepay)), 2));

        RepayDetailAdapter repayDetailAdapter = new RepayDetailAdapter(this, data);
        repayDetailBinding.rvDetail.setAdapter(repayDetailAdapter);

        repayDetailBinding.tvLoanAmount.setText(StringUtils.cutOutLastThree(StringUtils.doubleZheng(billsBean.getPayableAmout())));
        view.findViewById(R.id.iv_repaydetail_dismiss).setOnClickListener(v -> dialogDetail.dismiss());
        dialogDetail = new Dialog(this, R.style.DialogCommon);
        dialogDetail.setContentView(view);
        dialogDetail.setCanceledOnTouchOutside(true);
        Window window = dialogDetail.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER); // 此处可以设置dialog显示的位置
        window.setWindowAnimations(R.style.ZoomInZoomOutAnimation); // 添加动画
        dialogDetail.show();
    }

    /**
     * 查询还款详情
     *
     * @param baseBean
     */
    @Override
    public void queryBillsInfoSuc(BaseBean<RepayEntity> baseBean) {
        if (DataResult.isSuccessUnToast(this, baseBean)) {
            billsBean = baseBean.getData().getInfo().get(0);
            payDetail = baseBean.getData().getProducts();
            if (payDetail != null) {
                feeProcedurePay = payDetail.getFeeProcedurePay();
                feeProcedureRepay = payDetail.getFeeProcedureRepay();
            }
            mDataBinding.tvDeadline.setText(String.format("Lastest repayment date %s", DateUtils.showYMDTime(billsBean.getRepaymentTime())));

            showAmount(billsBean.getPayableAmout());

            if (baseBean.getData().getIsCode() == 0) {
                mPresenter.getRepaymentShop();
                mDataBinding.setShowChannelsView(true);
            } else if (baseBean.getData().getIsCode() == 1) {
                mDataBinding.setShowChannelsView(false);
                mPresenter.getPaymentCode(PreferenceUtils.getUserSessionid());
                mDataBinding.btnRepayRightnow.setVisibility(View.GONE);
            }
        }
    }

    /**
     * 提交还款
     *
     * @param baseBean
     */
    @Override
    public void submitActiveRepaySuc(BaseBean baseBean) {
        if (DataResult.isSuccessUnToast(this, baseBean)) {
            toRepayResult(baseBean);
        }
    }

    /**
     * 跳转到还款结果页
     *
     * @param baseBean
     */
    private void toRepayResult(BaseBean baseBean) {
        ARouter.getInstance()
                .build(RouteConstant.REPAY_RESULT)
                .withInt("billId", billsBean.getId())
                .withBoolean("repayOk", DataResult.isSuccessUnToast(this, baseBean))
                .navigation();
        finish();
    }

    /**
     * 获取首次选择还款渠道
     *
     * @param baseBean
     */
    @Override
    public void getRepaymentShopSuc(BaseBean<List<Integer>> baseBean) {
        if (DataResult.isSuccessUnToast(this, baseBean)) {
            list.clear();
            if (baseBean.getData() != null && baseBean.getData().size() > 0) {
                for (Integer datum : baseBean.getData()) {
                    list.add(new PaymentShopListEntity(datum, false));
                    if (datum==12){
                        mDataBinding.includeRepayChannels.acTvChangeTimes.setVisibility(View.VISIBLE);
                        mDataBinding.includeRepayChannels.acTvChangeTimes.setText(hintInfo);
                    }
                }
                mAdapter.notifyDataSetChanged();
            }
        }
    }

    /**
     * 获取还款码
     *
     * @param baseBean
     */
    @Override
    public void getPaymentCodeSuc(BaseBean<PaymentData> baseBean) {
        if (DataResult.isSuccessUnToast(RepayActivity.this, baseBean) && baseBean.getData().getShouldBills() != null) {
            mDataBinding.includeType1.acTvRepayCode.setText(baseBean.getData().getShouldBills().getPaymentCode());
            //SecondPay,SKYPAY
            mDataBinding.includeType1.acImgPayIcon.setImageResource(SKY_PAY.equals(baseBean.getData().getShouldBills().getBiller()) ? R.drawable.img_skypay : R.drawable.img_secondpay);
            nowChannel = baseBean.getData().getShouldBills().getPayClient();
            channelsClassification(mDataBinding.includeType1.acImgChannelIcon);
        }
    }

    /**
     * 还款渠道本地图片化
     *
     * @param imgView
     */
    private void channelsClassification(AppCompatImageView imgView) {
        switch (nowChannel) {
            case 1: {
                imgView.setImageResource(R.drawable.img_channels_ml_1);
                break;
            }
            case 6: {
                imgView.setImageResource(R.drawable.img_channels_gcash_6);
                break;
            }
            case 9: {
                imgView.setImageResource(R.drawable.img_channels_rd_9);
                break;
            }
            case 12: {
                imgView.setImageResource(R.drawable.img_channels_711_12);
                mDataBinding.includeType1.acTvChangeTimes.setVisibility(View.VISIBLE);
                mDataBinding.includeType1.acTvChangeTimes.setText(hintInfo);
                break;
            }
            default:
                break;
        }
    }

    List<PaymentShopListEntity> list = null;

    /**
     * 加载网络渠道到列表
     */
    private void loadChannels(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.addItemDecoration(new GridLayoutMarginDecoration(this, 2, 5));
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            repayType = 0;
            isClick = false;
            mAdapter.singleChoose(position);
            changeBank(false, mDataBinding.includeRepayChannels.clRepayThroughBank, mDataBinding.includeRepayChannels.acIvRepayThroughBank);
            mDataBinding.btnRepayRightnow.setEnabled(true);
            mPosition = position;
            selectChannelId = list.get(position).getId();
        });
    }

    /**
     * 内存释放
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (list != null) {
            list.clear();
            list = null;
        }
        if (mAdapter != null) {
            mAdapter = null;
        }
    }
}
