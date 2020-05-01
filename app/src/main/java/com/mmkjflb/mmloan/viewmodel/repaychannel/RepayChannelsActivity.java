package com.mmkjflb.mmloan.viewmodel.repaychannel;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.mmkj.baselibrary.RouteConstant;
import com.mmkj.baselibrary.annotation.ActivityFragmentInject;
import com.mmkj.baselibrary.app.ActivityStack;
import com.mmkj.baselibrary.model.entitry.BaseBean;
import com.mmkj.baselibrary.util.PreferenceUtils;
import com.mmkj.baselibrary.util.RxViewUtil;
import com.mmkjflb.mmloan.R;
import com.mmkjflb.mmloan.base.BaseActivity;
import com.mmkjflb.mmloan.databinding.ActivityRepayChannelBinding;
import com.mmkjflb.mmloan.model.entity.PaymentShopListEntity;
import com.mmkjflb.mmloan.model.entity.RepaymentShopEntity;
import com.mmkjflb.mmloan.model.http.DataResult;
import com.mmkjflb.mmloan.utils.GridLayoutMarginDecoration;
import com.mmkjflb.mmloan.viewmodel.bankrepay.BankRepayActivity;
import com.mmkjflb.mmloan.viewmodel.repay.RepayActivity;
import com.mmkjflb.mmloan.viewmodel.repay.RepaymentChannelAdapter;
import com.syhmmfqphl.xyxlibrary.utils.ExtraKeys;
import com.syhmmfqphl.xyxlibrary.utils.JumpManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;

/**
 * @author zhangshuai
 * 还款详情
 */
@Route(path = RouteConstant.REPAY_CHANNELS)
@ActivityFragmentInject(contentViewId = R.layout.activity_repay_channel, loadingTargetView = R.id.linear_loading)
public class RepayChannelsActivity extends BaseActivity<RepayChannelsPresenter, ActivityRepayChannelBinding> implements RepayChannelsContract.View {

    //其他渠道
    List<PaymentShopListEntity> list = null;
    //动态布局渠道
    private RepaymentChannelAdapter mAdapter;
    //渠道对应的位置
    private int mPosition = -1;
    //渠道id
    private int selectChannelId = -1;
    //是否选中了银行转账
    private boolean isClickBank;
    //0：渠道还款；1：银行转账
    private int repayType = -1;
    //渠道描述
    private String channelsDesc = "";
    //提交渠道 传参
    private Map<String, Object> map = null;

    @Autowired
    public int bidId = -1;

    @Autowired
    public int billId = -1;
    //当前支付渠道
    @Autowired
    public int nowChannel = -1;


    /**
     * 设置动态标题
     *
     * @param title
     */
    private void initToolbar(String title) {
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
        initToolbar("Choose repayment channel");

        mDataBinding.includeReplaceChannels.llCNowChannels.setVisibility(View.VISIBLE);
        mDataBinding.includeReplaceChannels.acTvChangeTimes.setVisibility(View.VISIBLE);
        mDataBinding.includeReplaceChannels.clRepayThroughBank.setVisibility(View.VISIBLE);
        mDataBinding.includeReplaceChannels.clLoanBank.setVisibility(View.VISIBLE);
        mDataBinding.llContainer.setVisibility(View.VISIBLE);
        mDataBinding.includeReplaceChannels.acTvToPTitle.setVisibility(View.VISIBLE);

        channelsClassification(mDataBinding.includeReplaceChannels.acImgNowChannel);

        mPresenter.getRepaymentShopNew(PreferenceUtils.getUserSessionid());
        loadOtherChannels();
        initClick();
    }

    @SuppressLint("CheckResult")
    private void initClick() {
        RxViewUtil.clicks(mDataBinding.btnRepayRightnow).subscribe(o -> {
            if (repayType == 0) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                alertDialog.setMessage("Confirm replace payment channel?");
                alertDialog.setCancelable(false);
                alertDialog.setNegativeButton("NO", (dialog, which) -> {
                    dialog.dismiss();
                });
                alertDialog.setPositiveButton("YES", (dialog, which) -> {
                    dialog.dismiss();
                    submitRepay();
                    mPresenter.updateRepayChannel(map);
                });
                alertDialog.create().show();
            } else if (repayType == 1) {
                ARouter.getInstance().build(RouteConstant.BANK_REPAY).withInt("bidId", bidId).withInt("billId", billId).withInt("repayType", repayType).navigation();
//                ActivityStack.getInstance().removeActivityByClass(RepayActivity.class);

                finish();
            }
        });

        mDataBinding.includeReplaceChannels.clRepayThroughBank.setOnClickListener(v -> {
            isClickBank = !isClickBank;
            repayType = 1;
            changeBank(isClickBank, mDataBinding.includeReplaceChannels.clRepayThroughBank, mDataBinding.includeReplaceChannels.acIvRepayThroughBank);
            if (mPosition != -1) {
                list.get(mPosition).setFlag(false);
                mAdapter.notifyItemChanged(mPosition);
            }
            mDataBinding.btnRepayRightnow.setEnabled(isClickBank);
        });


    }

    private void loadOtherChannels() {
        list = new ArrayList<>();
        mAdapter = new RepaymentChannelAdapter(this, list);
        mDataBinding.includeReplaceChannels.rcChannels.setLayoutManager(new GridLayoutManager(this, 2));
        mDataBinding.includeReplaceChannels.rcChannels.addItemDecoration(new GridLayoutMarginDecoration(this, 2, 5));
        mDataBinding.includeReplaceChannels.rcChannels.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            repayType = 0;
            isClickBank = false;
            mAdapter.multiChoose(position, mDataBinding.btnRepayRightnow);
            changeBank(false, mDataBinding.includeReplaceChannels.clRepayThroughBank, mDataBinding.includeReplaceChannels.acIvRepayThroughBank);
            mPosition = position;
            selectChannelId = list.get(position).getId();
        });
    }

    /**
     * 提交还款
     */
    private void submitRepay() {
        map = new HashMap<>(3);
        map.put("sessionId", PreferenceUtils.getString(PreferenceUtils.USER_SESSIONID, ""));
        map.put("billId", billId);
        map.put("payClient", selectChannelId);
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

    }

    @Override
    public void getRepaymentShopNewSuc(BaseBean<RepaymentShopEntity> data) {
        if (DataResult.isSuccessUnToast(this, data)) {
            list.clear();
            if (data.getData().getShops() != null && data.getData().getShops().size() > 0) {
                for (Integer datum : data.getData().getShops()) {
                    list.add(new PaymentShopListEntity(datum, false));
                    if (datum == 12) {
                        mDataBinding.includeReplaceChannels.acTvChangeTimes.setVisibility(View.VISIBLE);
                        String hintInfo = String.format("After the 7-11 repayment is successful, please wait for  processing. If there is any issue, please contact customer service: %s", PreferenceUtils.getString(PreferenceUtils.SERVICE_PHONE, ""));
                        mDataBinding.includeReplaceChannels.acTvChangeTimes.setText(hintInfo);
                    }
                }
                mAdapter.notifyDataSetChanged();
            }
            mDataBinding.acTvTimes.setText(data.getData().getDesc());
        }
    }

    /**
     * 更新还款渠道
     *
     * @param baseBean
     */
    @Override
    public void updateRepayChannelSuc(BaseBean baseBean) {
        if (baseBean.getCode() == 9999) {
            DataResult.getLoginDialog(this);
        } else if (baseBean.getCode() == -2) {
            // 失败吗 失败弹框，提示更换码达到了上限
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setMessage(baseBean.getMsg());
            alertDialog.setCancelable(false);
            alertDialog.setPositiveButton(R.string.confirm, (dialog, which) -> {
                dialog.dismiss();
            });
            alertDialog.create().show();
        } else {
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
                .withInt("billId", billId)
                .withBoolean("repayOk", DataResult.isSuccessUnToast(this, baseBean)).navigation();
        ActivityStack.getInstance().removeActivityByClass(RepayActivity.class);
        finish();
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
                mDataBinding.includeReplaceChannels.acTvChangeTimes.setVisibility(View.VISIBLE);
                String hintInfo = String.format("After the 7-11 repayment is successful, please wait for  processing. If there is any issue, please contact customer service: %s", PreferenceUtils.getString(PreferenceUtils.SERVICE_PHONE, ""));
                mDataBinding.includeReplaceChannels.acTvChangeTimes.setText(hintInfo);
                break;
            }
            default:
                break;
        }
    }

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
