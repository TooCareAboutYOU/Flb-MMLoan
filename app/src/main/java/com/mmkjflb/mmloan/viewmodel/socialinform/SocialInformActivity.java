package com.mmkjflb.mmloan.viewmodel.socialinform;

import android.text.TextUtils;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.mmkj.baselibrary.RouteConstant;
import com.mmkj.baselibrary.annotation.ActivityFragmentInject;
import com.mmkj.baselibrary.model.entitry.BaseBean;
import com.mmkj.baselibrary.util.PreferenceUtils;
import com.mmkj.baselibrary.util.RxViewUtil;
import com.mmkj.baselibrary.util.StringUtils;
import com.mmkjflb.mmloan.R;
import com.mmkjflb.mmloan.base.BaseActivity;
import com.mmkjflb.mmloan.databinding.ActivitySocialInformBinding;
import com.mmkjflb.mmloan.model.entity.SocialInfoEntity;
import com.mmkjflb.mmloan.model.http.DataResult;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;

/**
 * date: 2018/3/1 16:20
 * author: xuyexiang
 * title:
 */
@Route(path = RouteConstant.SOCIAL_INFORM)
@ActivityFragmentInject(contentViewId = R.layout.activity_social_inform, toolbarTitle = R.string.social_inform)
public class SocialInformActivity extends BaseActivity<SocialInformPresenter, ActivitySocialInformBinding> implements SocialInformContract.View {

    @Override
    protected void initViews() {
        getActivityComponent().inject(this);
        initClicks();
        listenEditTextEvents();
        mPresenter.getSocialInfo(PreferenceUtils.getString(PreferenceUtils.USER_SESSIONID, ""));
    }

    private void initClicks() {
        RxViewUtil.clicks(mDataBinding.btnNext).subscribe(o -> judgeNext());
    }

    private void judgeNext() {
        if (StringUtils.judgeEmail(mDataBinding.etEmailAccount.getText().toString().trim())) {
            Map<String, Object> map = new HashMap<>();
            map.put("sessionId", PreferenceUtils.getString(PreferenceUtils.USER_SESSIONID, ""));
            map.put("facebook", mDataBinding.etFacebookAccount.getText().toString().trim());
            map.put("email", mDataBinding.etEmailAccount.getText().toString().trim());
            map.put("instagram", mDataBinding.etInstagramAccount.getText().toString().trim());
            map.put("whatapp", mDataBinding.etWhatsappAccount.getText().toString().trim());
            mPresenter.socialinfo(map);
        } else {
            toast(R.string.pls_input_right_email);
        }

    }

    private void listenEditTextEvents() {                              //.skip(1) 这里不能设置会出现按钮不可点击
        Flowable<CharSequence> facebookAccountObservable = RxTextView.textChanges(mDataBinding.etFacebookAccount).toFlowable(BackpressureStrategy.LATEST);
        Flowable<CharSequence> emailAccountObservable = RxTextView.textChanges(mDataBinding.etEmailAccount).toFlowable(BackpressureStrategy.LATEST);
        Flowable.combineLatest(facebookAccountObservable, emailAccountObservable,
                (facebookaccount, emailaccount) -> !TextUtils.isEmpty(facebookaccount.toString().trim()) && !TextUtils.isEmpty(emailaccount.toString().trim())).subscribe(aBoolean -> {
            if (aBoolean) {
                mDataBinding.btnNext.setEnabled(true);
            } else {
                mDataBinding.btnNext.setEnabled(false);
            }
        }, throwable -> {

        });
    }

    @Override
    public void socialinfoSuc(BaseBean baseBean) {
        if (DataResult.isSuccessUnToast(this, baseBean)) {

        }
    }

    @Override
    public void getSocialInfoSuc(BaseBean<SocialInfoEntity> baseBean) {
        if (DataResult.isSuccessUnToast(this, baseBean)) {
            if (baseBean.getData() != null) {
                SocialInfoEntity socialInfoEntity = baseBean.getData();
                mDataBinding.etFacebookAccount.setText(socialInfoEntity.getFacebook());
                mDataBinding.etEmailAccount.setText(socialInfoEntity.getEmail());
                mDataBinding.etInstagramAccount.setText(socialInfoEntity.getInstagram());
                mDataBinding.etWhatsappAccount.setText(socialInfoEntity.getWhatapp());
            }
        }
    }
}
