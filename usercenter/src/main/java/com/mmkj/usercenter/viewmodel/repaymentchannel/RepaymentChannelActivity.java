package com.mmkj.usercenter.viewmodel.repaymentchannel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bigkoo.pickerview.OptionsPickerView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.mmkj.baselibrary.RouteConstant;
import com.mmkj.baselibrary.annotation.HeadToolbar;
import com.mmkj.baselibrary.annotation.HeaderBuilder;
import com.mmkj.baselibrary.model.entitry.BaseBean;
import com.mmkj.baselibrary.model.http.DataResult;
import com.mmkj.baselibrary.util.PreferenceUtils;
import com.mmkj.baselibrary.util.RxViewUtil;
import com.mmkj.usercenter.R;
import com.mmkj.usercenter.databinding.ActivityRepaymentChannelBinding;
import com.mmkj.usercenter.model.RepayChannelUtil;
import com.mmkj.usercenter.model.entity.RepayChannelBean;
import com.mmkj.usercenter.viewmodel.BaseUserSettingActivity;

import java.util.ArrayList;

import androidx.core.content.ContextCompat;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;

@Route(path = RouteConstant.REPAYMENT_CHANNEL)
public class RepaymentChannelActivity extends BaseUserSettingActivity<RepaymentChannelPresenter, ActivityRepaymentChannelBinding> implements RepaymentChannelContract.View {
    private String line1Msg1 = "· Use ";
    private String payChannel = "SKYPAY";
    private String line2Msg1 = "· Your Contract Number: ";
    private String prefix = "SKY07";
    private String line3Msg1 = "· Amount you need to pay back: PHP ";
    private String payCode = "SKY07xxxx";
    private String skyPayNum ="";
    private int searchType;

    private OptionsPickerView regionPickerView = null;
    private OptionsPickerView provincePickerView = null;
    private OptionsPickerView cityPickerView = null;
    private ArrayList<String> regions;
    private ArrayList<String> provinces;
    private ArrayList<String> citys;

    @Autowired
    public int payClient;

    @Override
    protected HeadToolbar getHeadToolbar() {
        return new HeaderBuilder()
                .mContentViewId(R.layout.activity_repayment_channel)
                .toolbarTitle(R.string.repayment_channel)
                .build();
    }

    @Override
    protected void initViews() {
        getActivityComponent().inject(this);
        ARouter.getInstance().inject(this);
        mPresenter.getRepayChannelByUser(PreferenceUtils.getUserSessionid());

        if (payClient == 12) {
            mDataBinding.ll711View.setVisibility(View.VISIBLE);
            RepayChannelUtil.create(this);
            regionPickerView = initOptionPicker(this, pickerListener, R.string.region);
            provincePickerView = initOptionPicker(this, pickerListener, R.string.province);
            cityPickerView = initOptionPicker(this, pickerListener, R.string.city2);
            regions = new ArrayList(RepayChannelUtil.getReginSet());
            regionPickerView.setPicker(regions);
            listenEditTextEvents();
        }


        initClicks();

    }

    public OptionsPickerView initOptionPicker(Context context, OptionsPickerView.OnOptionsSelectListener selectListener, int title) {
        return new OptionsPickerView.Builder(context, selectListener)
                .setTitleText(context.getResources().getString(title))
                .setContentTextSize(18)
                .setTitleSize(18)
                .setDividerColor(ContextCompat.getColor(context, R.color.gray_999999))//设置分割线的颜色
                .setSelectOptions(0)//默认选中项
                .setBgColor(ContextCompat.getColor(context, R.color.white))
                .setTitleBgColor(ContextCompat.getColor(context, R.color.white))
                .setTitleColor(ContextCompat.getColor(context, R.color.black_333333))
                .setCancelColor(ContextCompat.getColor(context, R.color.gray_999999))
                .setSubmitColor(ContextCompat.getColor(context, R.color.color_global))
                .setTextColorCenter(ContextCompat.getColor(context, R.color.black_333333))
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setBackgroundId(0x66000000) //设置外部遮罩颜色
                .build();
    }

    private OptionsPickerView.OnOptionsSelectListener pickerListener = new OptionsPickerView.OnOptionsSelectListener() {
        @Override
        public void onOptionsSelect(int options1, int options2, int options3, View v) {
            if (v.getId() == R.id.tv_region) {
                mDataBinding.tvRegion.setText(regions.get(options1));
                mDataBinding.tvProvince.setText("");
                mDataBinding.tvCity.setText("");
                citys = null;
                cityPickerView = null;
                provincePickerView = initOptionPicker(RepaymentChannelActivity.this, pickerListener, R.string.province);
                provinces = new ArrayList<>(RepayChannelUtil.getProvince(regions.get(options1)));
                provincePickerView.setPicker(provinces);
//                cityPickerView.setPicker(Collections.emptyList());
            } else if (v.getId() == R.id.tv_province) {
                mDataBinding.tvProvince.setText(provinces.get(options1));
                mDataBinding.tvCity.setText("");
                citys = new ArrayList<>(RepayChannelUtil.getCity(mDataBinding.tvRegion.getText().toString(), provinces.get(options1)));
                cityPickerView = initOptionPicker(RepaymentChannelActivity.this, pickerListener, R.string.city2);
                cityPickerView.setPicker(citys);
            } else if (v.getId() == R.id.tv_city) {
                mDataBinding.tvCity.setText(citys.get(options1));
            }
        }
    };

    @SuppressLint("CheckResult")
    private void initClicks() {
        RxViewUtil.clicks(mDataBinding.ivCheck).subscribe(o -> ARouter.getInstance().build(RouteConstant.REPAY_CHANNEL).withInt("searchType", searchType).navigation());
        RxViewUtil.clicks(mDataBinding.ivBack).subscribe(o -> finish());
        RxViewUtil.clicks(mDataBinding.tvRegion).subscribe(o -> {
            if (regionPickerView != null && regions != null) {
                regionPickerView.show(mDataBinding.tvRegion);
            }
        });
        RxViewUtil.clicks(mDataBinding.tvProvince).subscribe(o -> {
            if (provincePickerView != null && provinces != null) {
                provincePickerView.show(mDataBinding.tvProvince);
            }
        });
        RxViewUtil.clicks(mDataBinding.tvCity).subscribe(o -> {
            if (cityPickerView != null && citys != null) {
                cityPickerView.show(mDataBinding.tvCity);
            }
        });
        RxViewUtil.clicks(mDataBinding.btnSubmit).subscribe(o -> ARouter.getInstance().build(RouteConstant.REPAY_CHANNEL_RESULT)
                .withString("regino", mDataBinding.tvRegion.getText().toString())
                .withString("province", mDataBinding.tvProvince.getText().toString())
                .withString("city", mDataBinding.tvCity.getText().toString())
                .withInt("searchType", searchType)
                .navigation());

    }

    @Override
    public void getNetData() {

    }

    @SuppressLint("CheckResult")
    private void listenEditTextEvents() {
        Flowable<CharSequence> regionObservable = RxTextView.textChanges(mDataBinding.tvRegion).toFlowable(BackpressureStrategy.LATEST);
        Flowable<CharSequence> provinceObservable = RxTextView.textChanges(mDataBinding.tvProvince).toFlowable(BackpressureStrategy.LATEST);
        Flowable<CharSequence> cityObservable = RxTextView.textChanges(mDataBinding.tvCity).toFlowable(BackpressureStrategy.LATEST);
        Flowable.combineLatest(regionObservable, provinceObservable, cityObservable,
                (region, province, city) -> !TextUtils.isEmpty(region.toString().trim())
                        && !TextUtils.isEmpty(province.toString().trim())
                        && !TextUtils.isEmpty(city.toString().trim())).subscribe(aBoolean ->
                        mDataBinding.btnSubmit.setEnabled(aBoolean)
                , throwable -> {

                });
    }


    @Override
    public void getRepayChannelByUserSuc(BaseBean<RepayChannelBean> data) {
        if (DataResult.isSuccessUnToast(this, data)) {
            RepayChannelBean.DataBean dataBean = data.getData().getData();
            payChannel = data.getData().getPayChannel();
            prefix = data.getData().getPrefix();
            payCode = data.getData().getPayCode();
            skyPayNum = data.getData().getNum();
            searchType = data.getData().getSearchType();

            SpannableString spanString = new SpannableString(line1Msg1 + payChannel + " as merchant");
            ForegroundColorSpan span = new ForegroundColorSpan(Color.parseColor("#378FFF"));
            spanString.setSpan(span, line1Msg1.length(), line1Msg1.length() + payChannel.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
            mDataBinding.tvOne.setText(spanString);

            String line2Msg2 = "(" + prefix + " and " + skyPayNum + " digits\nnumbers which showing on your apps)";
            SpannableString spanString2 = new SpannableString(line2Msg1 + payCode + line2Msg2);
            ForegroundColorSpan span2 = new ForegroundColorSpan(Color.parseColor("#378FFF"));
            ForegroundColorSpan span4 = new ForegroundColorSpan(Color.parseColor("#999999"));
            spanString2.setSpan(span2, line2Msg1.length(), line2Msg1.length() + payCode.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
            spanString2.setSpan(span4, line2Msg1.length() + payCode.length(), spanString2.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
            mDataBinding.tvTwo.setText(spanString2);

            SpannableString spanString3 = new SpannableString(line3Msg1 + payCode);
            ForegroundColorSpan span3 = new ForegroundColorSpan(Color.parseColor("#378FFF"));
            spanString3.setSpan(span3, line3Msg1.length(), spanString3.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
            mDataBinding.tvThree.setText(spanString3);

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (payClient == 12) {
            RepayChannelUtil.clear();
        }
    }
}
