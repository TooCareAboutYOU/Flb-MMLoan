package com.mmkjflb.mmloan.viewmodel.baseinform;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jakewharton.rxbinding2.widget.RxCompoundButton;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.mmkj.baselibrary.RouteConstant;
import com.mmkj.baselibrary.annotation.ActivityFragmentInject;
import com.mmkj.baselibrary.model.entitry.BaseBean;
import com.mmkj.baselibrary.util.BitmapUtils;
import com.mmkj.baselibrary.util.DateUtils;
import com.mmkj.baselibrary.util.PreferenceUtils;
import com.mmkj.baselibrary.util.RxViewUtil;
import com.mmkj.baselibrary.util.StringUtils;
import com.mmkj.baselibrary.util.callback.DealPicCallBack;
import com.mmkjflb.mmloan.R;
import com.mmkjflb.mmloan.app.BaseApplication;
import com.mmkjflb.mmloan.app.PermissionGroup;
import com.mmkjflb.mmloan.base.BaseActivity;
import com.mmkjflb.mmloan.databinding.ActivityBaseInformBinding;
import com.mmkjflb.mmloan.model.entity.BaseInfoEntity;
import com.mmkjflb.mmloan.model.http.DataResult;
import com.mmkjflb.mmloan.utils.UiUtils;
import com.mmkjflb.mmloan.utils.location.LocalUtils;
import com.mmkjflb.mmloan.utils.location.databeans.RegionsBean;
import com.mmkjflb.mmloan.viewmodel.emergencycontact.EmergencyContactActivity;
import com.syhmmfqphl.xyxlibrary.utils.JumpManager;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.core.content.ContextCompat;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import me.nereo.multi_image_selector.MultiImageSelector;

/**
 * 基本信息
 */
@Route(path = RouteConstant.BASE_INFORM)
@ActivityFragmentInject(contentViewId = R.layout.activity_base_inform, toolbarTitle = R.string.basic_information)
public class BaseInformActivity extends BaseActivity<BaseInformPresenter, ActivityBaseInformBinding> implements BaseInformContract.View {
    private List<String> sexData, educationData, marryData, addressTypeList, usePhoneTimeList;
    private OptionsPickerView sexPickerView, educationPickerView, marryPickerView, addressTypeOpv, usePhoneTimeOpv;
    private OptionsPickerView islandPickerView = null;
    private OptionsPickerView provincePickerView = null;
    private OptionsPickerView cityPickerView = null;
    private TimePickerView timePickerView;
    private List<String> islandProvinceCityEntityList = null;
    private List<RegionsBean> provinceListBeanList = null;
    private List<String> cityListBeanList = null;
    private RxPermissions rxPermissions;
    private int IMG_CALLBACK_CODE = 0;
    private String mPhone;
    private Disposable disposable = null;
    private String addressProof = "";
    private long logLastTime = 0L;

    @Override
    protected void initViews() {
        getActivityComponent().inject(this);
        rxPermissions = new RxPermissions(this);
        mDataBinding.tvName.setText(PreferenceUtils.getString(PreferenceUtils.USER_REALNAME, ""));
        mDataBinding.tvIdcardNumb.setText(PreferenceUtils.getString(PreferenceUtils.USER_IDCARD, ""));
        mPhone = PreferenceUtils.getString(PreferenceUtils.USER_PHONE, "");
        initPickerView();
        initClicks();
        listenEditTextEvents();
        mPresenter.getBaseInfo(PreferenceUtils.getUserSessionid());
//        mDataBinding.acTvHint.setText(Html.fromHtml("<font color='#333333' size=''>Optional</font><font color='#999999'>(Uploading related document will make you get quota more easily.)</font>"));
    }

    private void initPickerView() {
        //出生年月日
        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        startDate.set(1937, 0, 1);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2018, 1, 31);
        //时间选择器
        timePickerView = new TimePickerView.Builder(this, (date, v) -> {//选中事件回调
            // 这里回调过来的v,就是show()方法里面所添加的 View 参数，如果show的时候没有添加参数，v则为null
            birthdayCode = DateUtils.showYMDTime(date);
            mDataBinding.tvBirthday.setText(DateUtils.showYMDTime(date));
        })
                .setTitleText(this.getResources().getString(R.string.birthday))
                .setContentSize(18)
                .setTitleSize(18)
                .setDividerColor(ContextCompat.getColor(this, R.color.gray_999999))
                .setBgColor(ContextCompat.getColor(this, R.color.white))
                .setTitleBgColor(ContextCompat.getColor(this, R.color.white))
                .setTitleColor(ContextCompat.getColor(this, R.color.black_333333))
                .setCancelColor(ContextCompat.getColor(this, R.color.gray_999999))
                .setSubmitColor(ContextCompat.getColor(this, R.color.color_global))
                .setTextColorCenter(ContextCompat.getColor(this, R.color.black_333333))
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setBackgroundId(0x66000000) //设置外部遮罩颜色
                //年月日时分秒 的显示与否，不设置则默认全部显示
                .setType(new boolean[]{true, true, true, false, false, false})
                .setLabel("", "", "", "", "", "")
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setDecorView(null)
                .build();
    }


    private int sexCode = -1;
    private String birthdayCode = "";
    private String islandCode = "";
    private String provinceCode = "";
    private String cityCode = "";
    private int addressTypeCode = -1;
    private int usePhoneTimeCode = -1;
    private int educationCode = -1;
    private int maritalCode = -1;
    private boolean isFinishedOne = false, isFinishedTwo = false;
    private int selectedPhoneType = -1;
    private OptionsPickerView.OnOptionsSelectListener pickerListener = new OptionsPickerView.OnOptionsSelectListener() {
        @Override
        public void onOptionsSelect(int options1, int options2, int options3, View v) {
            switch (v.getId()) {
                case R.id.tv_sex:
                    sexCode = options1;
                    mDataBinding.tvSex.setText(sexData.get(options1));
                    break;
                case R.id.tv_island:
                    islandCode = islandProvinceCityEntityList.get(options1);
                    mDataBinding.tvIsland.setText(islandCode);
                    mDataBinding.tvProvince.setText("");
                    mDataBinding.tvCity.setText("");
                    provinceListBeanList = null;
                    cityListBeanList = null;
                    provinceListBeanList = LocalUtils.getInstance().getRegionsList(mDataBinding.tvIsland.getText().toString());
                    provincePickerView.setPicker(provinceListBeanList);
                    break;
                case R.id.tv_province:
                    provinceCode = provinceListBeanList.get(options1).getPickerViewText();
                    mDataBinding.tvProvince.setText(provinceCode);
                    mDataBinding.tvCity.setText("");
                    cityListBeanList = null;
                    cityListBeanList = LocalUtils.getInstance().getCitysList(mDataBinding.tvIsland.getText().toString(), mDataBinding.tvProvince.getText().toString());
                    cityPickerView.setPicker(cityListBeanList);
                    break;
                case R.id.tv_city:
                    cityCode = cityListBeanList.get(options1);
                    mDataBinding.tvCity.setText(cityCode);
                    break;
                case R.id.tv_education_status:
                    educationCode = options1;
                    mDataBinding.tvEducationStatus.setText(educationData.get(options1));
                    break;
                case R.id.tv_marital_status:
                    maritalCode = options1;
                    mDataBinding.tvMaritalStatus.setText(marryData.get(options1));
                    break;
                case R.id.tv_address_type: {
                    addressTypeCode = options1;
                    mDataBinding.tvAddressType.setText(addressTypeList.get(options1));
                    break;
                }
                case R.id.acTv_UsePhoneTime: {
                    usePhoneTimeCode = options1;
                    mDataBinding.acTvUsePhoneTime.setText(usePhoneTimeList.get(options1));
                    break;
                }
                default:
                    break;
            }
        }
    };

    @SuppressLint("CheckResult")
    private void initClicks() {
        //性别
        sexData = Arrays.asList(getResources().getStringArray(R.array.sex_data));
        sexPickerView = UiUtils.initOptionPicker(this, pickerListener, R.string.sex);
        sexPickerView.setPicker(sexData);
        RxViewUtil.clicks(mDataBinding.tvSex).subscribe(o -> sexPickerView.show(mDataBinding.tvSex));

        //出生日期
        RxViewUtil.clicks(mDataBinding.tvBirthday).subscribe(o -> timePickerView.show(mDataBinding.tvBirthday));

        //岛
        islandProvinceCityEntityList = LocalUtils.getInstance().getArchipelagosList();
        islandPickerView = UiUtils.initOptionPicker(this, pickerListener, R.string.islands_region);
        islandPickerView.setPicker(islandProvinceCityEntityList);

        RxViewUtil.clicks(mDataBinding.tvIsland).subscribe(o -> {
            if (islandPickerView != null && islandProvinceCityEntityList != null) {
                islandPickerView.show(mDataBinding.tvIsland);
            }
        });

        //省份
        provincePickerView = UiUtils.initOptionPicker(BaseInformActivity.this, pickerListener, R.string.province);
        RxViewUtil.clicks(mDataBinding.tvProvince).subscribe(o -> {
            if (provincePickerView != null && provinceListBeanList != null) {
                provincePickerView.show(mDataBinding.tvProvince);
            } else {
                toast(R.string.pls_chose_island);
            }
        });

        //城市
        cityPickerView = UiUtils.initOptionPicker(BaseInformActivity.this, pickerListener, R.string.city);
        RxViewUtil.clicks(mDataBinding.tvCity).subscribe(o -> {
            if (cityPickerView != null && cityListBeanList != null) {
                cityPickerView.show(mDataBinding.tvCity);
            } else {
                toast(R.string.pls_chose_province);
            }
        });

        //教育状况
        educationData = Arrays.asList(getResources().getStringArray(R.array.education_data));
        educationPickerView = UiUtils.initOptionPicker(this, pickerListener, R.string.education_msg);
        educationPickerView.setPicker(educationData);
        RxViewUtil.clicks(mDataBinding.tvEducationStatus).subscribe(o -> educationPickerView.show(mDataBinding
                .tvEducationStatus));

        //婚姻状况
        marryData = Arrays.asList(getResources().getStringArray(R.array.marry_data));
        marryPickerView = UiUtils.initOptionPicker(this, pickerListener, R.string.marital_status);
        marryPickerView.setPicker(marryData);
        RxViewUtil.clicks(mDataBinding.tvMaritalStatus).subscribe(o -> marryPickerView.show(mDataBinding.tvMaritalStatus));

        //地址类型
        addressTypeList = Arrays.asList(getResources().getStringArray(R.array.address_type_array));
        addressTypeOpv = UiUtils.initOptionPicker(this, pickerListener, R.string.select_address_type);
        addressTypeOpv.setPicker(addressTypeList);
        RxViewUtil.clicks(mDataBinding.tvAddressType).subscribe(o -> addressTypeOpv.show(mDataBinding.tvAddressType));

        RxCompoundButton.checkedChanges(mDataBinding.tBtnMine).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                if (aBoolean) {
                    selectedPhoneType = 0;
                    mDataBinding.tBtnOther.setChecked(false);
                } else {
                    if (selectedPhoneType != 1) {
                        selectedPhoneType = -1;
                    }
                }

                submitDataEnable();
            }
        });

        RxCompoundButton.checkedChanges(mDataBinding.tBtnOther).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                if (aBoolean) {
                    selectedPhoneType = 1;
                    mDataBinding.tBtnMine.setChecked(false);
                } else {
                    if (selectedPhoneType != 0) {
                        selectedPhoneType = -1;
                    }
                }

                submitDataEnable();
            }
        });

        //用户使用手机时长
        usePhoneTimeList = Arrays.asList(getResources().getStringArray(R.array.use_phone_time_array));
        usePhoneTimeOpv = UiUtils.initOptionPicker(this, pickerListener, R.string.select_answer);
        usePhoneTimeOpv.setPicker(usePhoneTimeList);
        RxViewUtil.clicks(mDataBinding.llcUsePhoneTime).subscribe(o -> usePhoneTimeOpv.show(mDataBinding.acTvUsePhoneTime));

        //图片
//        RxViewUtil.clicks(mDataBinding.rlAddress).subscribe(o -> rxPermissions.request(PermissionGroup.PERMISSION_PHOTO)
//                .subscribe(granted -> {
//                    if (granted) {
//                        //添加图片
//                        if (disposable != null) {
//                            disposable.dispose();
//                        }
//                        MultiImageSelector selector = MultiImageSelector.create();
//                        selector.showCamera(true);
//                        selector.single();
//                        selector.start(this, IMG_CALLBACK_CODE);
//                    } else {
//                        UiUtils.openSetting(this, R.string.open_camera_file_permission);
//                    }
//                }, throwable -> {
//                }));

        //下一步
        RxViewUtil.clicks(mDataBinding.btnNext).subscribe(o -> {
            if (StringUtils.judgeEmail(mDataBinding.etEmailAccount.getText().toString().trim())) {
                if (!TextUtils.isEmpty(mDataBinding.etOtherPhone.getText().toString()) && mPhone.equals(mDataBinding.etOtherPhone.getText().toString())) {
                    toast(getResources().getString(R.string.pls_chose_other_phone_hint));
                }else{
                    baseinfo();
                }
//                else if (imgUrl == null) {
//                    showProgress();
//                    baseinfo();
//                } else {
//                    mPresenter.getQiniuToken(PreferenceUtils.getUserSessionid());
//                }
            } else {
                toast(R.string.pls_input_right_email);
            }

        });
    }

    @Override
    public void getQiniuTokenSuc(BaseBean<String> baseBean) {
//        if (DataResult.isSuccessUnToast(BaseInformActivity.this, baseBean)) {
//            logLastTime = System.currentTimeMillis();
//            BaseApplication.getInstance().getQiNiuConfig().put(imgUrl, "addressProof" + System.currentTimeMillis(), baseBean.getData(), (key, info, response) -> {
//                if (info.isOK()) {
//                    Log.d("qiniuuploadtest", "baseinform_upload_time=" + (System.currentTimeMillis() - logLastTime));
//                    addressProof = key;
//                    baseinfo();
//                } else {
//                    toast(R.string.img_upload_error);
//                }
//            }, null);
//        }
    }

    private void baseinfo() {
        Map<String, Object> map = new HashMap<>();
        map.put("sessionId", PreferenceUtils.getUserSessionid());
        map.put("name", PreferenceUtils.getString(PreferenceUtils.USER_REALNAME, ""));
        map.put("idNumber", PreferenceUtils.getString(PreferenceUtils.USER_IDCARD, ""));
        map.put("sex", sexCode);
        map.put("birthday", birthdayCode);
        map.put("islands", islandCode);
        map.put("region", provinceCode);
        map.put("municipality", cityCode);
        map.put("address", mDataBinding.etLiveaddress.getText().toString().trim());
        map.put("education", educationCode);
        map.put("addressType", addressTypeCode);
        map.put("ownMobile", selectedPhoneType);
        map.put("mobileDuration", usePhoneTimeCode);
        map.put("marriage", maritalCode);
        map.put("email", mDataBinding.etEmailAccount.getText().toString().trim());
//        map.put("addressProof", addressProof);
        if (!TextUtils.isEmpty(mDataBinding.etOtherPhone.getText().toString().trim())) {
            map.put("otherMobilePhone", mDataBinding.etOtherPhone.getText().toString().trim());
        }
        mPresenter.baseinfo(map);
    }

    @SuppressLint("CheckResult")
    private void listenEditTextEvents() {                              //.skip(1) 这里不能设置会出现按钮不可点击
        Flowable<CharSequence> sexObservable = RxTextView.textChanges(mDataBinding.tvSex).toFlowable(BackpressureStrategy.LATEST);
        Flowable<CharSequence> birthdayObservable = RxTextView.textChanges(mDataBinding.tvBirthday).toFlowable(BackpressureStrategy.LATEST);
        Flowable<CharSequence> islandObservable = RxTextView.textChanges(mDataBinding.tvIsland).toFlowable(BackpressureStrategy.LATEST);
        Flowable<CharSequence> provinceObservable = RxTextView.textChanges(mDataBinding.tvProvince).toFlowable(BackpressureStrategy.LATEST);
        Flowable<CharSequence> cityObservable = RxTextView.textChanges(mDataBinding.tvCity).toFlowable(BackpressureStrategy.LATEST);
        Flowable<CharSequence> liveAddressObservable = RxTextView.textChanges(mDataBinding.etLiveaddress).toFlowable(BackpressureStrategy.LATEST);
        Flowable<CharSequence> educationObservable = RxTextView.textChanges(mDataBinding.tvEducationStatus).toFlowable(BackpressureStrategy.LATEST);
        Flowable<CharSequence> maritalObservable = RxTextView.textChanges(mDataBinding.tvMaritalStatus).toFlowable(BackpressureStrategy.LATEST);
        Flowable<CharSequence> emailObservable = RxTextView.textChanges(mDataBinding.etEmailAccount).toFlowable(BackpressureStrategy.LATEST);
        Flowable.combineLatest(sexObservable, birthdayObservable, islandObservable, provinceObservable,
                cityObservable, liveAddressObservable, educationObservable, maritalObservable, emailObservable,
                (sex, birthday, island, province, city, liveAddress, education, marital, email) ->
                        !TextUtils.isEmpty(sex.toString().trim())
                                && !TextUtils.isEmpty(birthday.toString().trim())
                                && !TextUtils.isEmpty(island.toString().trim())
                                && !TextUtils.isEmpty(province.toString().trim())
                                && !TextUtils.isEmpty(city.toString().trim())
                                && !TextUtils.isEmpty(liveAddress.toString().trim())
                                && !TextUtils.isEmpty(education.toString().trim())
                                && !TextUtils.isEmpty(marital.toString().trim())
                                && !TextUtils.isEmpty(email.toString().trim())
        ).subscribe(aBoolean -> {
            isFinishedOne = aBoolean;
            submitDataEnable();
        }, throwable -> {
        });

        Flowable<CharSequence> addressTypeObservable = RxTextView.textChanges(mDataBinding.tvAddressType).toFlowable(BackpressureStrategy.LATEST);
        Flowable<CharSequence> usePhoneTimeObservable = RxTextView.textChanges(mDataBinding.acTvUsePhoneTime).toFlowable(BackpressureStrategy.LATEST);
        Flowable.combineLatest(addressTypeObservable, usePhoneTimeObservable, new BiFunction<CharSequence, CharSequence, Boolean>() {
            @Override
            public Boolean apply(CharSequence charSequence, CharSequence charSequence2) throws Exception {

                return !TextUtils.isEmpty(charSequence.toString().trim()) && !TextUtils.isEmpty(charSequence2.toString().trim());
            }
        }).subscribe(aBoolean -> {
            isFinishedTwo = aBoolean;
            submitDataEnable();
        }, throwable -> {

        });

    }

    private boolean isCheckedPhoneType() {
        return selectedPhoneType != -1;
    }

    private void submitDataEnable() {
        mDataBinding.btnNext.setEnabled((isCheckedPhoneType() && isFinishedOne && isFinishedTwo));
    }

    @Override
    public void baseinfoSuc(BaseBean baseBean) {
        if (DataResult.isSuccessUnToast(this, baseBean)) {
//            JumpManager.jumpToClose(BaseInformActivity.this, EmergencyContactActivity.class);
            ARouter.getInstance().build(RouteConstant.EMERGENCY_CONTACT).navigation();finish();
        }
    }

    @Override
    public void getBaseInfoSuc(BaseBean<BaseInfoEntity> baseBean) {
        if (DataResult.isSuccessUnToast(this, baseBean)) {
            BaseInfoEntity baseInfoEntity = baseBean.getData();
            sexCode = baseInfoEntity.getSex();
            mDataBinding.tvSex.setText(baseInfoEntity.getSexValue());
            birthdayCode = DateUtils.showYMDTime(baseInfoEntity.getBirthday());
            if (baseInfoEntity.getBirthday() != -30609820800000L) {
                mDataBinding.tvBirthday.setText(birthdayCode);
            }
            switch (baseInfoEntity.getIdcardType()) {
                case 1:
                    mDataBinding.tvIdinformKey.setText(getResources().getString(R.string.idcard_type1));
                    break;
                case 2:
                    mDataBinding.tvIdinformKey.setText(getResources().getString(R.string.idcard_type2));
                    break;
                case 3:
                    mDataBinding.tvIdinformKey.setText(getResources().getString(R.string.idcard_type3));
                    break;
                case 4:
                    mDataBinding.tvIdinformKey.setText(getResources().getString(R.string.idcard_type4));
                    break;
                case 5:
                    mDataBinding.tvIdinformKey.setText(getResources().getString(R.string.idcard_type5));
                    break;
                default:
                    break;
            }
            islandCode = baseInfoEntity.getProvince();
            mDataBinding.tvIsland.setText(baseInfoEntity.getProvince());

            provinceCode = baseInfoEntity.getRegion();
            mDataBinding.tvProvince.setText(baseInfoEntity.getRegion());
            cityCode = baseInfoEntity.getMunicipality();
            mDataBinding.tvCity.setText(baseInfoEntity.getMunicipality());
            mDataBinding.etLiveaddress.setText(baseInfoEntity.getAddress());
            educationCode = baseInfoEntity.getEducation();
            mDataBinding.tvEducationStatus.setText(baseInfoEntity.getEducationValue());
            maritalCode = baseInfoEntity.getMarriage();
            mDataBinding.tvMaritalStatus.setText(baseInfoEntity.getMarriageValue());
            if (!TextUtils.isEmpty(baseInfoEntity.getEmail())) {
                mDataBinding.etEmailAccount.setText(baseInfoEntity.getEmail());
            }
            if (!TextUtils.isEmpty(baseInfoEntity.getOtherMobilePhone())) {
                mDataBinding.etOtherPhone.setText(baseInfoEntity.getOtherMobilePhone());
            }

            addressTypeCode = baseInfoEntity.getAddressType();
            mDataBinding.tvAddressType.setText(baseInfoEntity.getAddressTypeValue());

            selectedPhoneType = baseInfoEntity.getOwnMobile();

            if (selectedPhoneType == 0) {
                mDataBinding.tBtnMine.setChecked(true);
            } else if (selectedPhoneType == 1) {
                mDataBinding.tBtnOther.setChecked(true);
            }

            usePhoneTimeCode = baseInfoEntity.getMobileDuration();
            mDataBinding.acTvUsePhoneTime.setText(baseInfoEntity.getMobileDurationValue());


//            if (baseInfoEntity.getAddressProof() != null) {
//                disposable = BitmapUtils.downLoadPic(this, baseInfoEntity.getAddressProof(), new DealPicCallBack() {
//                    @Override
//                    public void success(File file) {
//                        imgUrl = file;
//                        Glide.with(BaseInformActivity.this).asBitmap().load(imgUrl).centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL).into(mDataBinding.ivAddress);
//                        mDataBinding.tvAddressBlackHint.setVisibility(View.GONE);
//                        mDataBinding.tvAddressGrayHint.setVisibility(View.GONE);
//                    }
//
//                    @Override
//                    public void error() {
//
//                    }
//                });
//            }
        }

    }


//    private File imgUrl = null;
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (RESULT_OK == resultCode && data != null) {
//            BitmapUtils.compressPic(this, data.getStringArrayListExtra(MultiImageSelector.EXTRA_RESULT).get(0), new DealPicCallBack() {
//                @Override
//                public void success(File file) {
//                    imgUrl = file;
//                    Glide.with(BaseInformActivity.this).asBitmap().load(imgUrl).centerCrop().error(R.drawable.mis_default_error).placeholder(R.drawable.mis_default_error).diskCacheStrategy(DiskCacheStrategy.ALL).into(mDataBinding.ivAddress);
//                    mDataBinding.tvAddressBlackHint.setVisibility(View.GONE);
//                    mDataBinding.tvAddressGrayHint.setVisibility(View.GONE);
//                }
//
//                @Override
//                public void error() {
//                    toast(R.string.pls_upload_img);
//                }
//            });
//        }
//    }
}
