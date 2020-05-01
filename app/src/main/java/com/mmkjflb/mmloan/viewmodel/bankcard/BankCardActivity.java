package com.mmkjflb.mmloan.viewmodel.bankcard;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.mmkj.baselibrary.RouteConstant;
import com.mmkj.baselibrary.util.Constants;
import com.mmkjflb.mmloan.R;
import com.mmkjflb.mmloan.base.BaseActivity;
import com.mmkjflb.mmloan.databinding.ActivityBankcardBinding;
import com.mmkjflb.mmloan.model.entity.CardManagerEntity;
import com.mmkjflb.mmloan.model.entity.QueryRefundInfoEntity;
import com.mmkjflb.mmloan.model.http.DataResult;
import com.mmkjflb.mmloan.viewmodel.bankinform.BankInformActivity;
import com.syhmmfqphl.xyxlibrary.utils.ExtraKeys;
import com.mmkj.baselibrary.annotation.ActivityFragmentInject;
import com.mmkj.baselibrary.model.entitry.BaseBean;
import com.mmkj.baselibrary.util.PreferenceUtils;
import com.mmkj.baselibrary.util.RxViewUtil;
import com.mmkj.baselibrary.util.StringUtils;

/**
 * Created by Administrator on 2017/10/19.
 */
@Route(path = RouteConstant.BANK_CARD)
@ActivityFragmentInject(contentViewId = R.layout.activity_bankcard, loadingTargetView = R.id.linear_loading, toolbarTitle = R.string.bank_card)
public class BankCardActivity extends BaseActivity<BankCardPresenter, ActivityBankcardBinding> implements BankCardContract.View {
    private static final int BINDCARDCODE = 1;
    private QueryRefundInfoEntity bankCardsBean;

    @SuppressLint("CheckResult")
    @Override
    protected void initViews() {
        getActivityComponent().inject(this);
        mPresenter.queryUserBankCard(PreferenceUtils.getString(PreferenceUtils.USER_SESSIONID, ""));
        RxViewUtil.clicks(mDataBinding.linearUnbindcard).subscribe(o -> {
//            Intent intent = new Intent(this, BankInformActivity.class);
//            intent.putExtra(ExtraKeys.Key_Msg1, true);
//            startActivityForResult(intent, BINDCARDCODE);

            ARouter.getInstance().build(RouteConstant.BANK_INFORM).withBoolean("fromBankList",true).navigation(this,BINDCARDCODE);
        });
        RxViewUtil.clicks(mDataBinding.btnChangeBankcard).subscribe(o -> {
//            Intent intent = new Intent(this, BankInformActivity.class);
//            intent.putExtra(ExtraKeys.Key_Msg1, true);
//            intent.putExtra(ExtraKeys.Key_Msg2, bankCardsBean.getAccount());
//            startActivityForResult(intent, BINDCARDCODE);
            ARouter.getInstance().build(RouteConstant.BANK_INFORM).withBoolean("fromBankList",true).withParcelable("account",bankCardsBean).navigation(this,BINDCARDCODE);
        });
    }

    private void unBindCard() {
        mDataBinding.linearUnbindcard.setVisibility(View.VISIBLE);
        mDataBinding.clBindcardSuc.setVisibility(View.GONE);
        mDataBinding.btnChangeBankcard.setVisibility(View.GONE);
    }

    private void bindCardSuc() {
        mDataBinding.linearUnbindcard.setVisibility(View.GONE);
        mDataBinding.clBindcardSuc.setVisibility(View.VISIBLE);
        mDataBinding.btnChangeBankcard.setVisibility(View.GONE);
        mDataBinding.tvBankname.setText(bankCardsBean.getBankName());
        mDataBinding.tvBanknumb.setText("**** **** **** " + StringUtils.lastNumbShow(bankCardsBean.getAccount(), 4));
        mDataBinding.tvContractUsContent.setVisibility(View.VISIBLE);
    }

    @Override
    public void queryUserBankCardSuc(BaseBean<CardManagerEntity> baseBean) {
        if (DataResult.isSuccessUnToastAll(BankCardActivity.this, baseBean)) {
            if (baseBean.getData().getBankList().size() > 0) {
                bankCardsBean = baseBean.getData().getBankList().get(0);
                bindCardSuc();
            }
        } else {
            unBindCard();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case BINDCARDCODE:
                case Constants.CODE_LOGINTIMEOUT:
                    mPresenter.queryUserBankCard(PreferenceUtils.getString(PreferenceUtils.USER_SESSIONID, ""));
                    break;
            }
        }
    }
}
