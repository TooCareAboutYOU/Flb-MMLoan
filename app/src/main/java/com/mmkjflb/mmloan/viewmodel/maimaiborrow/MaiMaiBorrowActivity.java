package com.mmkjflb.mmloan.viewmodel.maimaiborrow;


import android.annotation.SuppressLint;

import androidx.recyclerview.widget.GridLayoutManager;

import android.util.Log;
import android.widget.SeekBar;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.mmkj.baselibrary.RouteConstant;
import com.mmkj.baselibrary.annotation.ActivityFragmentInject;
import com.mmkj.baselibrary.model.entitry.BaseBean;
import com.mmkj.baselibrary.util.PreferenceUtils;
import com.mmkj.baselibrary.util.RxViewUtil;
import com.mmkj.baselibrary.util.StringUtils;
import com.mmkjflb.mmloan.BuildConfig;
import com.mmkjflb.mmloan.R;
import com.mmkjflb.mmloan.base.BaseActivity;
import com.mmkjflb.mmloan.databinding.ActivityMaimaiborrowBinding;
import com.mmkjflb.mmloan.model.http.DataResult;
import com.mmkjflb.mmloan.viewmodel.certificationmain.CertificationMainActivity;
import com.syhmmfqphl.xyxlibrary.utils.JumpManager;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 借款意向页
 * Created by Administrator on 2017/8/14.
 */
@Route(path =RouteConstant.MAI_MAI_BORROW)
@ActivityFragmentInject(contentViewId = R.layout.activity_maimaiborrow)
public class MaiMaiBorrowActivity extends BaseActivity<MaiMaiBorrowPresenter, ActivityMaimaiborrowBinding> implements MaiMaiBorrowContract.View {
//    private BorrowAdapter borrowAdapter;
//
//    //是否做过选择
//    private boolean isSelected = false;
//    //意向金额范围
//    private int[] amountScope;
//
//    //选中金额
//    private int amount;
//
//    private int[] dayTag;
//    //选中的期限标识
//    private int selectDays = 0;

    @Override
    protected void initViews() {
        getActivityComponent().inject(this);
        initClicks();
    }

    @SuppressLint("CheckResult")
    private void initClicks() {
        RxViewUtil.clicks(mDataBinding.btnBorrowRightnow).subscribe(o -> {
            Map<String, Object> map = new HashMap<>();
            map.put("sessionId", PreferenceUtils.getUserSessionid());
            map.put("applyAmount", "1500");
            map.put("applyDate", "1");
            map.put("totalRatio", "5.25");//利息=0.0005*7*借款金额
            map.put("loanIntention", 0);
            mPresenter.applyLoan(map);
        });
        RxViewUtil.clicks(mDataBinding.ivUsercenter).subscribe(o -> ARouter.getInstance().build(RouteConstant.USER_CENTER).navigation());
    }
    /**
     * 开始认证
     */
    @Override
    public void applyLoanSuc(BaseBean baseBean) {
        if (DataResult.isSuccessUnToast(this, baseBean)) {
            PreferenceUtils.put(PreferenceUtils.USER_SETP, 1);
//            JumpManager.jumpToClose(this, CertificationMainActivity.class);
            ARouter.getInstance().build(RouteConstant.CERTIFICATION_MAIN).navigation();finish();
        }
    }
}
