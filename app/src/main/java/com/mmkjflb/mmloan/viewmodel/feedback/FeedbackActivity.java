package com.mmkjflb.mmloan.viewmodel.feedback;

import android.text.TextUtils;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.mmkjflb.mmloan.R;
import com.mmkjflb.mmloan.base.BaseActivity;
import com.mmkjflb.mmloan.databinding.ActivityFeedbackBinding;
import com.mmkjflb.mmloan.model.http.DataResult;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.mmkj.baselibrary.RouteConstant;
import com.mmkj.baselibrary.annotation.ActivityFragmentInject;
import com.mmkj.baselibrary.model.entitry.BaseBean;
import com.mmkj.baselibrary.util.PreferenceUtils;
import com.mmkj.baselibrary.util.RxViewUtil;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;

/**
 * Created by Administrator on 2017/10/17.
 */
@Route(path = RouteConstant.FEEDBACK)
@ActivityFragmentInject(contentViewId = R.layout.activity_feedback, toolbarTitle = R.string.feedback)
public class FeedbackActivity extends BaseActivity<FeedbackPresenter, ActivityFeedbackBinding> implements FeedbackContract.View {
    private String suggestStr = "";
    private String contact = "";

    @Override
    protected void initViews() {
        getActivityComponent().inject(this);
        listenEditTextEvents();
        initClicks();

    }

    private void initClicks() {
        RxViewUtil.clicks(mDataBinding.btnSubmit).subscribe(o -> {
            Map<String, String> params = new HashMap<>();
            params.put("sessionId", PreferenceUtils.getString(PreferenceUtils.USER_SESSIONID, ""));
            params.put("content", suggestStr);
            params.put("contactType", contact);
            mPresenter.applySuggest(params);
        });
    }

    private void listenEditTextEvents() {                              //.skip(1) 这里不能设置会出现按钮不可点击
        Flowable<CharSequence> suggestObservable = RxTextView.textChanges(mDataBinding.etSuggest).toFlowable(BackpressureStrategy.LATEST);
        Flowable<CharSequence> contactObservable = RxTextView.textChanges(mDataBinding.etContact).toFlowable(BackpressureStrategy.LATEST);
        Flowable.combineLatest(suggestObservable, contactObservable,
                (charSequence1, charSequence2) -> {
                    suggestStr = charSequence1.toString().trim();
                    contact = charSequence2.toString().trim();
                    return !TextUtils.isEmpty(suggestStr) && !TextUtils.isEmpty(contact);
                }).subscribe(aBoolean -> {
            if (aBoolean) {
                mDataBinding.btnSubmit.setEnabled(true);
            } else {
                mDataBinding.btnSubmit.setEnabled(false);
            }
        }, throwable -> {

        });
    }

    @Override
    public void applySuggestSuc(BaseBean baseBean) {
        if (DataResult.isSuccessUnToast(this, baseBean)) {
            finish();
        }
    }
}
