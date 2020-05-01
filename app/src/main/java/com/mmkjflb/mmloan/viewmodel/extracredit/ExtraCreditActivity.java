package com.mmkjflb.mmloan.viewmodel.extracredit;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.mmkj.baselibrary.RouteConstant;
import com.mmkj.baselibrary.annotation.ActivityFragmentInject;
import com.mmkj.baselibrary.model.entitry.BaseBean;
import com.mmkj.baselibrary.model.http.DataResult;
import com.mmkjflb.mmloan.R;
import com.mmkjflb.mmloan.base.BaseActivity;
import com.mmkjflb.mmloan.databinding.ActivityExtraCreditBinding;
import com.mmkjflb.mmloan.model.entity.ExtraCreditBean;


/**
 * @author zhangshuai
 */
@Route(path = RouteConstant.EXTRA_CREDIT)
@ActivityFragmentInject(contentViewId = R.layout.activity_extra_credit, toolbarTitle = R.string.title_extra_credit)
public class ExtraCreditActivity extends BaseActivity<ExtraCreditPresenter, ActivityExtraCreditBinding> implements ExtraCreditContract.View {//, DotListener {

//    private String sessionId = null, idCardNum = null, name = null, mobile = null;
//    private WebsitesType mWebsitesType;

    @Override
    protected void initViews() {
        getActivityComponent().inject(this);

//        sessionId = PreferenceUtils.getString(PreferenceUtils.USER_SESSIONID, "");
//        //证件号
//        idCardNum = PreferenceUtils.getString(PreferenceUtils.USER_IDCARD, "");
//        //申请人姓名
//        name = PreferenceUtils.getString(PreferenceUtils.USER_REALNAME, "");
//        //声请人手机号
//        mobile = PreferenceUtils.getString(PreferenceUtils.USER_PHONE, "");
//
//        clickListener();
    }

//    @SuppressLint("CheckResult")
//    private void clickListener() {
//        mPresenter.getExtraCredit(sessionId);
//
//        MmkjDot.getInstance().setCallBackListener(this);
//
//        RxViewUtil.clicks(mDataBinding.clFacebook).subscribe(o -> {
//            showProgress();
//            mWebsitesType = WebsitesType.FACEBOOK;
//            MmkjDot.getInstance().getSupportDataSource(this);
//        });
//
//        RxViewUtil.clicks(mDataBinding.clGrab).subscribe(o -> {
//            showProgress();
//            mWebsitesType = WebsitesType.GRAB;
//            MmkjDot.getInstance().getSupportDataSource(this);
//        });
//
//        RxViewUtil.clicks(mDataBinding.btnSubmit).subscribe(o -> {
//            finish();
//        });
//    }

    @Override
    public void getExtraCreditSuc(BaseBean<ExtraCreditBean> bean) {
        if (DataResult.isSuccessUnToast(this, bean)) {
            if (bean.getData() != null) {
                mDataBinding.btnSubmit.setEnabled(bean.getData().isFacebookAuth());
                mDataBinding.acTvGoFacebook.setSelected(bean.getData().isFacebookAuth());
                mDataBinding.acTvGoFacebook.setText(bean.getData().isFacebookAuth() ? getResources().getString(R.string.complete) : getResources().getString(R.string.go_to_certification));
                mDataBinding.acTvGoGrab.setSelected(bean.getData().isGrabAuth());
                mDataBinding.acTvGoGrab.setText(bean.getData().isGrabAuth() ? getResources().getString(R.string.complete) : getResources().getString(R.string.go_to_certification));
            }
        }
    }

    @Override
    public void submitReportTokenSuc(BaseBean bean) {
        if (DataResult.isSuccessUnToast(this, bean)) {
//            mPresenter.getExtraCredit(sessionId);
        }
    }


//    @Override
//    public void onSupportDataSourceCallBack(String msg) {
//        MmkjDot.getInstance().createReportGeneratedTask(idCardNum, IdType.ID_CARD, name, mobile, mWebsitesType);
//    }
//
//    @Override
//    public void onGetTokenSuccess() {
//        hideProgress();
//    }
//
//    @Override
//    public void onAccountRunStatusFailed(String msg) {
//
//    }
//
//    @Override
//    public void onFinishSuccess(String token, String website) {
//        mPresenter.submitReportToken(sessionId,token,website);
//    }
//
//
//    @Override
//    public void onFinishFailed(String msg) {
//
//    }
//
//    @Override
//    public void onErrorMsgCallBack(String s) {
//
//    }
//
//    @Override
//    public void onErrorThrowableCallBack(Throwable throwable) {
//
//    }

}
