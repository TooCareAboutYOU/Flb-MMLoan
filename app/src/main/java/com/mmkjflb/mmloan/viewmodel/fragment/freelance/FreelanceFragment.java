package com.mmkjflb.mmloan.viewmodel.fragment.freelance;

import android.annotation.SuppressLint;
import android.text.TextUtils;

import com.alibaba.android.arouter.launcher.ARouter;
import com.mmkj.baselibrary.RouteConstant;
import com.mmkjflb.mmloan.R;
import com.mmkjflb.mmloan.base.BaseFragment;
import com.mmkjflb.mmloan.databinding.FragmentFreelanceBinding;
import com.mmkjflb.mmloan.model.entity.ClickEvent;
import com.mmkjflb.mmloan.model.entity.FreelanceEntity;
import com.mmkjflb.mmloan.model.http.DataResult;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.mmkj.baselibrary.annotation.ActivityFragmentInject;
import com.mmkj.baselibrary.model.entitry.BaseBean;
import com.mmkj.baselibrary.util.PreferenceUtils;
import com.mmkj.baselibrary.util.RxBus;
import com.mmkjflb.mmloan.viewmodel.extracredit.ExtraCreditActivity;
import com.syhmmfqphl.xyxlibrary.utils.JumpManager;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by
 * Date: On 2018/3/5
 * Author: wangjieyan
 * Email: wangjieyan9308@163.com
 */
@ActivityFragmentInject(contentViewId = R.layout.fragment_freelance)
public class FreelanceFragment extends BaseFragment<FreelancePresenter, FragmentFreelanceBinding> implements FreelanceContract.View {
    //repayment source
    private String repaymentSource = "";
    //month income
    private String monthIncome = "";
    @Override
    protected void initViews() {
        getFragmentComponent().inject(this);
//        initData();
        initClick();
        listenEditTextEvents();
    }
    //初始化回填数据
    private void initData(){
        mPresenter.getFreelanceInfo(PreferenceUtils.getUserSessionid());
    }
    @SuppressLint("CheckResult")
    private void initClick() {
        //注册监听者
        RxBus.getDefault().toObservable(ClickEvent.class)
				.observeOn(AndroidSchedulers.mainThread()).subscribe(performance -> {
			if (performance.getStatus() == 1) {
				repaymentSource = mDataBinding.etRepaymentSource.getText().toString().trim();
				monthIncome = mDataBinding.etMonthIncome.getText().toString().trim();
				Map<String, Object> request = new HashMap<>();
				request.put("sessionId", PreferenceUtils.getString(PreferenceUtils.USER_SESSIONID, ""));
				request.put("performance", performance.getStatus());
				request.put("monthIncome", Long.valueOf(monthIncome));
				request.put("repaySource", repaymentSource);
				mPresenter.freelanceinfo(request);
			}
		});
    }

    @Override
    public void freelanceinfoSuc(BaseBean baseBean) {
        if (DataResult.isSuccessToast(getActivity(), baseBean)) {
//            getActivity().setResult(Activity.RESULT_OK);
//            getActivity().finish();
//            JumpManager.jumpToClose(getActivity(), ExtraCreditActivity.class);
            ARouter.getInstance().build( RouteConstant.EXTRA_CREDIT).navigation();getActivity().finish();
        }
    }

    @Override
    public void getFreelanceInfoSuc(BaseBean<FreelanceEntity> baseBean) {
        if (DataResult.isSuccessUnToast(getActivity(), baseBean)) {
            if (baseBean.getData() != null) {
                mDataBinding.etRepaymentSource.setText(baseBean.getData().getRepaySource());
                mDataBinding.etMonthIncome.setText(String.valueOf(baseBean.getData().getMonthIncome()));
            }
        }
    }

    private void listenEditTextEvents() {
        Flowable<CharSequence> repaymentSourceObservable = RxTextView.textChanges(mDataBinding.etRepaymentSource).toFlowable(BackpressureStrategy.LATEST);
        Flowable<CharSequence> monthIncomeObservable = RxTextView.textChanges(mDataBinding.etMonthIncome).toFlowable(BackpressureStrategy.LATEST);
        Flowable.combineLatest(repaymentSourceObservable, monthIncomeObservable,
				(repaymentSource, monthIncome) -> !TextUtils.isEmpty(repaymentSource.toString().trim()) && !TextUtils.isEmpty(monthIncome.toString().trim())).subscribe(aBoolean -> {
//			if (aBoolean) {
//				com.hwangjr.rxbus.RxBus.get().post(EventConstants.INFORM_BTN_CHANGE, 1);
//			} else {
//				com.hwangjr.rxbus.RxBus.get().post(EventConstants.INFORM_BTN_CHANGE, 0);
//			}
		}, throwable -> {

		});
    }
}
