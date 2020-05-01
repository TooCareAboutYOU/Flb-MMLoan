package com.mmkjflb.mmloan.viewmodel.withdraw;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.mmkj.baselibrary.databinding.ActivityWithdrawBinding;
import com.mmkj.baselibrary.RouteConstant;
import com.mmkj.baselibrary.annotation.HeadToolbar;
import com.mmkj.baselibrary.annotation.HeaderBuilder;
import com.mmkj.baselibrary.model.entitry.BaseBean;
import com.mmkj.baselibrary.model.entitry.WithdrawalNumberEntity;
import com.mmkj.baselibrary.util.PreferenceUtils;
import com.mmkjflb.mmloan.R;
import com.mmkjflb.mmloan.base.BaseActivity;
import com.mmkjflb.mmloan.model.http.DataResult;
import com.mmkjflb.mmloan.utils.GridLayoutMarginDecoration;
import com.syhmmfqphl.xyxlibrary.utils.ExtraKeys;

import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;


@Route(path = RouteConstant.WITHDRAW)
public class WithDrawActivity extends BaseActivity<WithDrawPresenter, ActivityWithdrawBinding> implements WithDrawContract.View {

    private WithDrawChannelAdapter mDrawChannelAdapter;

    @Autowired
    public boolean isLoanResult;

    @Override
    protected HeadToolbar getHeadToolbar() {
        return new HeaderBuilder().mContentViewId(R.layout.activity_withdraw).statusBarColor(R.color.gray_3A3E4F).build();
    }

    @Override
    protected void initViews() {
        getActivityComponent().inject(this);
        mPresenter.getWithdrawalNumber(PreferenceUtils.getUserSessionid());
        mDataBinding.ivBack.setOnClickListener(v -> finish());
        mDataBinding.tvFinish.setOnClickListener(v -> finish());
        mDataBinding.btnViewloan.setOnClickListener(v -> ARouter.getInstance().build(RouteConstant.LOAN_LIST).navigation());
    }

    @Override
    public void getWithdrawalNumberSuc(BaseBean<WithdrawalNumberEntity> baseBean) {
        if (DataResult.isSuccessUnToastAll(this, baseBean)) {
            WithdrawalNumberEntity withdrawalNumberEntity = baseBean.getData();
            mDataBinding.setWithdrawNumber(withdrawalNumberEntity);
            mDataBinding.setPhone(PreferenceUtils.getString(PreferenceUtils.SERVICE_PHONE, ""));
            mDataBinding.setIsTIN("3".equals(PreferenceUtils.getString(PreferenceUtils.USER_IDCARDTYPE, "")));
            mDataBinding.setIsLoanResult(isLoanResult);
            if (baseBean.getData().getImgList() != null && baseBean.getData().getImgList().size() > 0) {
                loadShopChannel(baseBean.getData().getImgCode());
            }
        }
    }

    private void loadShopChannel(List<String> list) {
        mDrawChannelAdapter = new WithDrawChannelAdapter(this, list);
        mDataBinding.rvChannels.setLayoutManager(new GridLayoutManager(this, 2));
        mDataBinding.rvChannels.addItemDecoration(new GridLayoutMarginDecoration(this,2, 8));
        mDataBinding.rvChannels.setAdapter(mDrawChannelAdapter);
    }
}
