package com.mmkjflb.mmloan.viewmodel.fragment.payroll;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bigkoo.pickerview.OptionsPickerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.mmkj.baselibrary.RouteConstant;
import com.mmkj.baselibrary.annotation.ActivityFragmentInject;
import com.mmkj.baselibrary.model.entitry.BaseBean;
import com.mmkj.baselibrary.util.EventConstants;
import com.mmkj.baselibrary.util.PreferenceUtils;
import com.mmkj.baselibrary.util.RxBus;
import com.mmkj.baselibrary.util.RxViewUtil;
import com.mmkjflb.mmloan.R;
import com.mmkjflb.mmloan.app.BaseApplication;
import com.mmkjflb.mmloan.app.Constants;
import com.mmkjflb.mmloan.app.PermissionGroup;
import com.mmkjflb.mmloan.base.BaseFragment;
import com.mmkjflb.mmloan.databinding.FragmentPayrollBinding;
import com.mmkjflb.mmloan.model.entity.ClickEvent;
import com.mmkjflb.mmloan.model.entity.CompanyNatureEntity;
import com.mmkjflb.mmloan.model.entity.PositionEntity;
import com.mmkjflb.mmloan.model.entity.SalariesEntity;
import com.mmkjflb.mmloan.model.http.DataResult;
import com.mmkjflb.mmloan.utils.UiUtils;
import com.mmkjflb.mmloan.utils.location.LocalUtils;
import com.mmkjflb.mmloan.utils.location.databeans.RegionsBean;
import com.mmkjflb.mmloan.viewmodel.chooseposition.ChoosePositionActivity;
import com.mmkjflb.mmloan.viewmodel.enploymentinformation.ImageActivity;
import com.syhmmfqphl.xyxlibrary.utils.ExtraKeys;
import com.syhmmfqphl.xyxlibrary.utils.JumpManager;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import me.nereo.multi_image_selector.MultiImageSelector;

/**
 * Created by
 * Date: On 2018/3/5
 * Author: wangjieyan
 * Email: wangjieyan9308@163.com
 */
@ActivityFragmentInject(contentViewId = R.layout.fragment_payroll)
public class PayrollFragment extends BaseFragment<PayrollPresenter, FragmentPayrollBinding> implements PayrollContract.View {
    private static final String TAG = "PayrollFragment";
    private OptionsPickerView workTimePickerView;
    private String[] worktimeArray;
    private OptionsPickerView payCyclePickerView;
    private String[] payCycleArray;
    private int payType = 0, payDate1 = 0, payDate2 = 0, payDate3 = 0, payDate4 = 0;
    private OptionsPickerView payDatePickerView;
    private List<String> listPayData = null;
    private OptionsPickerView regionPickerView = null;
    private OptionsPickerView provincePickerView = null;
    private OptionsPickerView cityPickerView = null;
    private OptionsPickerView natureView = null;
    private List<CompanyNatureEntity> mNatureEntityList;
    private List<String> islandProvinceCityEntityList = null;
    private List<RegionsBean> regionListBeanList = null;
    private List<String> cityListBeanList = null;
    private RxPermissions rxPermissions;
    private File imageurl1 = null, imageurl2 = null, imageurl3 = null;
    private int workTimeCode = 0;
    private PositionEntity positionEntity = null;
    private Disposable disposable1 = null, disposable2 = null, disposable3 = null;
    private String employmentCertificate = "";
    private String employmentCertificateBack = "";
    private String theLatestPayroll = "";
    private long logLastTime = 0L;
    private int position = 0;
    private OptionsPickerView.OnOptionsSelectListener pickerListener = new OptionsPickerView.OnOptionsSelectListener() {
        @Override
        public void onOptionsSelect(int options1, int options2, int options3, View v) {
            switch (v.getId()) {
                case R.id.tv_company_nature:
                    mDataBinding.tvCompanyNature.setText(mNatureEntityList.get(options1).getPickerViewText());
                    position = options1 + 1;
                    break;
                case R.id.tv_island:
                    mDataBinding.tvIsland.setText(islandProvinceCityEntityList.get(options1));
                    mDataBinding.tvProvince.setText("");
                    mDataBinding.tvCity.setText("");
                    regionListBeanList = null;
                    cityListBeanList = null;
                    regionListBeanList = LocalUtils.getInstance().getRegionsList(mDataBinding.tvIsland.getText().toString());
                    provincePickerView.setPicker(regionListBeanList);
                    break;
                case R.id.tv_province:
                    mDataBinding.tvProvince.setText(regionListBeanList.get(options1).getPickerViewText());
                    mDataBinding.tvCity.setText("");
                    cityListBeanList = null;
                    cityListBeanList = LocalUtils.getInstance().getCitysList(mDataBinding.tvIsland.getText().toString(), mDataBinding.tvProvince.getText().toString());
                    cityPickerView.setPicker(cityListBeanList);
                    break;
                case R.id.tv_city:
                    mDataBinding.tvCity.setText(cityListBeanList.get(options1));
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void initViews() {
        getFragmentComponent().inject(this);
        rxPermissions = new RxPermissions(getActivity());
        initData();
        initClick();
        initPickerView();
    }

    private void initData() {
        // 取xml文件格式的字符数组
        mPresenter.getSalariesInfo(PreferenceUtils.getUserSessionid());
        mPresenter.getCompanyNature();
        listenerAll();
    }

    @Override
    public void getQiniuTokenSuc(BaseBean<String> baseBean) {
        if (DataResult.isSuccessUnToast(getActivity(), baseBean)) {
            employmentCertificate = "";
            employmentCertificateBack = "";
            theLatestPayroll = "";
            logLastTime = System.currentTimeMillis();
            Observable.fromArray(imageurl1, imageurl2, imageurl3)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<File>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                        }

                        @Override
                        public void onNext(File file) {
                            Log.i("onnext", "File=" + file);
                            if (file != null && file == imageurl1) {
                                BaseApplication.getInstance().getQiNiuConfig().put(file, "employmentCertificate" + System.currentTimeMillis(), baseBean.getData(), (key, info, response) -> {
                                    if (info.isOK()) {
                                        employmentCertificate = key;
                                        judgeUpLoadSuc();
                                    } else {
                                        toast(R.string.img_upload_error);
                                        hideProgress();
                                    }
                                }, null);
                            } else if (file != null && file == imageurl2) {
                                BaseApplication.getInstance().getQiNiuConfig().put(file, "employmentCertificateBack" + System.currentTimeMillis(), baseBean.getData(), (key, info, response) -> {
                                    if (info.isOK()) {
                                        employmentCertificateBack = key;
                                        judgeUpLoadSuc();
                                    } else {
                                        toast(R.string.img_upload_error);
                                        hideProgress();
                                    }
                                }, null);
                            } else if (file != null && file == imageurl3) {
                                BaseApplication.getInstance().getQiNiuConfig().put(file, "theLatestPayroll" + System.currentTimeMillis(), baseBean.getData(), (key, info, response) -> {
                                    if (info.isOK()) {
                                        theLatestPayroll = key;
                                        judgeUpLoadSuc();
                                    } else {
                                        toast(R.string.img_upload_error);
                                        hideProgress();
                                    }
                                }, null);
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                        }

                        @Override
                        public void onComplete() {
                        }
                    });
        }
    }

    private void judgeUpLoadSuc() {
        if (!TextUtils.isEmpty(employmentCertificate) && !TextUtils.isEmpty(employmentCertificateBack) && !TextUtils.isEmpty(theLatestPayroll)) {
            upLoadData();
        }
    }

    private void upLoadData() {
        Log.d("qiniuuploadtest", "idcard_upload_time=" + (System.currentTimeMillis() - logLastTime));
        Map<String, Object> request = new HashMap<>();
        request.put("sessionId", PreferenceUtils.getUserSessionid());
        request.put("performance", position);
        request.put("companyName", mDataBinding.etCompanyName.getText().toString().trim());
        request.put("companyMobile", mDataBinding.etCompanyTele.getText().toString().trim());
        request.put("companyNature", mDataBinding.tvCompanyNature.getText().toString().trim());
        request.put("workPost", mDataBinding.tvWorkJobs.getText().toString().trim());
        request.put("workTime", workTimeCode);
        request.put("postalCode", mDataBinding.etCompanyPostcode.getText().toString().trim());
        request.put("islands", mDataBinding.tvIsland.getText().toString().trim());
        request.put("region", mDataBinding.tvProvince.getText().toString().trim());
        request.put("municipality", mDataBinding.tvCity.getText().toString().trim());
//        request.put("address", mDataBinding.etLiveaddress.getText().toString().trim());
        request.put("monthIncome", mDataBinding.tvMonthIncome.getText().toString().trim());
        request.put("occupationId", positionEntity.getId());
        request.put("pId", positionEntity.getPid());
        request.put("employmentCertificate", employmentCertificate);
        request.put("employmentCertificateBack", employmentCertificateBack);
        request.put("theLatestPayroll", theLatestPayroll);
        request.put("salaryMethod", payType);
        if (!TextUtils.isEmpty(mDataBinding.includeView.tvDay1.getText().toString().trim())) {
            request.put("payDayOne", payDate1 + 1);
        }
        if (!TextUtils.isEmpty(mDataBinding.includeView.tvDay2.getText().toString().trim())) {
            request.put("payDayTwo", payDate2 + 1);
        }
        if (!TextUtils.isEmpty(mDataBinding.includeView.tvDay3.getText().toString().trim())) {
            request.put("payDayThree", payDate3 + 1);
        }
        if (!TextUtils.isEmpty(mDataBinding.includeView.tvDay4.getText().toString().trim())) {
            request.put("payDayFour", payDate4 + 1);
        }
        mPresenter.salariesinfo(request);
    }

    @SuppressLint("CheckResult")
    private void initClick() {
        //注册监听者
        RxBus.getDefault().toObservable(ClickEvent.class).observeOn(AndroidSchedulers.mainThread()).subscribe(performance -> {
            if (performance.getStatus() == 0) {
                mPresenter.getQiniuToken(PreferenceUtils.getUserSessionid());
            }
            if (performance.getType() == 1 && performance.getUrl() != null) {
                imageurl1 = performance.getUrl();
                Glide.with(BaseApplication.getInstance()).asBitmap().load(imageurl1).centerCrop().error(R.drawable.data_update_bg).placeholder(R.drawable.data_update_bg).diskCacheStrategy(DiskCacheStrategy.ALL).into(mDataBinding.ivDataUpdateFont);
                mDataBinding.tvDataUpdateHintFont.setVisibility(View.GONE);
                mDataBinding.tvDataHintFont.setVisibility(View.GONE);
            }
            if (performance.getType() == 2 && performance.getUrl() != null) {
                imageurl2 = performance.getUrl();
                Glide.with(BaseApplication.getInstance()).asBitmap().load(imageurl2).centerCrop().error(R.drawable.data_update_bg).placeholder(R.drawable.data_update_bg).diskCacheStrategy(DiskCacheStrategy.ALL).into(mDataBinding.ivDataUpdateBack);
                mDataBinding.tvDataUpdateHintBack.setVisibility(View.GONE);
                mDataBinding.tvDataHintBack.setVisibility(View.GONE);
            }
            if (performance.getType() == 3 && performance.getUrl() != null) {
                imageurl3 = performance.getUrl();
                Glide.with(BaseApplication.getInstance()).asBitmap().load(imageurl3).centerCrop().error(R.drawable.data_update_bg).placeholder(R.drawable.data_update_bg).diskCacheStrategy(DiskCacheStrategy.ALL).into(mDataBinding.ivTheLatestPayroll);
                mDataBinding.tvTheLatestPayrollHint.setVisibility(View.GONE);
                mDataBinding.tvTheLatestPayrollHints.setVisibility(View.GONE);
            }
        });


        //公司性质
        RxViewUtil.clicks(mDataBinding.tvCompanyNature).subscribe(o -> {
            if (mNatureEntityList != null && natureView != null) {
                natureView.show(mDataBinding.tvCompanyNature);
            } else {
                toast(R.string.pls_choose_nature);
            }
        });

        // 从事行业
        RxViewUtil.clicks(mDataBinding.tvWorkJobs).subscribe(o -> {
//            Intent it = new Intent(getActivity(), ChoosePositionActivity.class);
//            it.putExtra(ExtraKeys.Key_Msg1, positionEntity);
//            getActivity().startActivityForResult(it, Constants.CAREERCODE);
            ARouter.getInstance().build(RouteConstant.CHOOSE_POSITION).withParcelable("positionEntity",positionEntity).navigation(getActivity(),Constants.CAREERCODE);
        });

        //从业时长
        RxViewUtil.clicks(mDataBinding.llWorkTime).subscribe(o -> {
            if (workTimePickerView != null && worktimeArray != null) {
                workTimePickerView.show(mDataBinding.llWorkTime);
            }
        });

        islandProvinceCityEntityList = LocalUtils.getInstance().getArchipelagosList();
        regionPickerView = UiUtils.initOptionPicker(getActivity(), pickerListener, R.string.company_address);
        regionPickerView.setPicker(islandProvinceCityEntityList);

        //所在岛屿
        RxViewUtil.clicks(mDataBinding.tvIsland).subscribe(o -> {
            if (regionPickerView != null && islandProvinceCityEntityList != null) {
                regionPickerView.show(mDataBinding.tvIsland);
            }
        });

        provincePickerView = UiUtils.initOptionPicker(getActivity(), pickerListener, R.string.company_province);
        //省
        RxViewUtil.clicks(mDataBinding.tvProvince).subscribe(o -> {
            if (provincePickerView != null && regionListBeanList != null) {
                provincePickerView.show(mDataBinding.tvProvince);
            } else {
                toast(R.string.pls_chose_island);
            }
        });

        cityPickerView = UiUtils.initOptionPicker(getActivity(), pickerListener, R.string.company_city);
        //城市
        RxViewUtil.clicks(mDataBinding.tvCity).subscribe(o -> {
            if (cityPickerView != null && cityListBeanList != null) {
                cityPickerView.show(mDataBinding.tvCity);
            } else {
                toast(R.string.pls_chose_province);
            }
        });

        //发薪日
        RxViewUtil.clicks(mDataBinding.tvPayCycle).subscribe(o -> {
            if (payCyclePickerView != null && payCycleArray != null) {
                payCyclePickerView.show(mDataBinding.tvPayCycle);
            }
        });

        //日期一
        RxViewUtil.clicks(mDataBinding.includeView.tvDay1).subscribe(o -> {
            payDatePickerView = UiUtils.initOptionPicker(getActivity(), (options1, options2, options3, v) -> {
                switch (payType) {
                    case 1: {
                        if (!listPayData.get(options1).equals(mDataBinding.includeView.tvDay2.getText().toString())
                                && !listPayData.get(options1).equals(mDataBinding.includeView.tvDay3.getText().toString())
                                && !listPayData.get(options1).equals(mDataBinding.includeView.tvDay4.getText().toString())) {
                            mDataBinding.includeView.tvDay1.setText(listPayData.get(options1));
                            payDate1 = options1;
                        } else {
                            toast(R.string.same_pay_day);
                        }
                        break;
                    }
                    case 2: {
                        if (!listPayData.get(options1).equals(mDataBinding.includeView.tvDay2.getText().toString())) {
                            mDataBinding.includeView.tvDay1.setText(listPayData.get(options1));
                            payDate1 = options1;
                        } else {
                            toast(R.string.same_pay_day);
                        }
                        break;
                    }
                    case 3: {
                        mDataBinding.includeView.tvDay1.setText(listPayData.get(options1));
                        payDate1 = options1;
                        break;
                    }
                    default:
                        break;
                }
            }, R.string.pay_cycle_hint_day1);
            payDatePickerView.setPicker(listPayData);
            if (payDatePickerView != null && listPayData != null) {
                payDatePickerView.show(mDataBinding.includeView.tvDay1);
            }
        });

        //日期二
        RxViewUtil.clicks(mDataBinding.includeView.tvDay2).subscribe(o -> {
            payDatePickerView = UiUtils.initOptionPicker(getActivity(), (options1, options2, options3, v) -> {
                switch (payType) {
                    case 1: {
                        if (!listPayData.get(options1).equals(mDataBinding.includeView.tvDay1.getText().toString())
                                && !listPayData.get(options1).equals(mDataBinding.includeView.tvDay3.getText().toString())
                                && !listPayData.get(options1).equals(mDataBinding.includeView.tvDay4.getText().toString())) {
                            mDataBinding.includeView.tvDay2.setText(listPayData.get(options1));
                            payDate2 = options1;
                        } else {
                            toast(R.string.same_pay_day);
                        }
                        break;
                    }

                    case 2: {
                        if (!listPayData.get(options1).equals(mDataBinding.includeView.tvDay1.getText().toString())) {
                            mDataBinding.includeView.tvDay2.setText(listPayData.get(options1));
                            payDate2 = options1;
                        } else {
                            toast(R.string.same_pay_day);
                        }
                        break;
                    }
                    default:
                        break;
                }
            }, R.string.pay_cycle_hint_day2);
            payDatePickerView.setPicker(listPayData);
            if (payDatePickerView != null && listPayData != null) {
                payDatePickerView.show(mDataBinding.includeView.tvDay2);
            }
        });

        //日期三
        RxViewUtil.clicks(mDataBinding.includeView.tvDay3).subscribe(o -> {
            payDatePickerView = UiUtils.initOptionPicker(getActivity(), (options1, options2, options3, v) -> {
                switch (payType) {
                    case 1: {
                        if (listPayData.get(options1).equals(mDataBinding.includeView.tvDay1.getText().toString())) {
                            toast(R.string.same_pay_day);
                            break;
                        }
                        if (listPayData.get(options1).equals(mDataBinding.includeView.tvDay2.getText().toString())) {
                            toast(R.string.same_pay_day);
                            break;
                        }
                        if (listPayData.get(options1).equals(mDataBinding.includeView.tvDay4.getText().toString())) {
                            toast(R.string.same_pay_day);
                            break;
                        }

                        mDataBinding.includeView.tvDay3.setText(listPayData.get(options1));
                        payDate3 = options1;
//                        listenerAll();
                        break;
                    }
                    default:
                        break;
                }
            }, R.string.pay_cycle_hint_day3);
            payDatePickerView.setPicker(listPayData);
            if (payDatePickerView != null && listPayData != null) {
                payDatePickerView.show(mDataBinding.includeView.tvDay3);
            }
        });

        //日期四
        RxViewUtil.clicks(mDataBinding.includeView.tvDay4).subscribe(o -> {
            payDatePickerView = UiUtils.initOptionPicker(getActivity(), (options1, options2, options3, v) -> {
                switch (payType) {
                    case 1: {
                        if (listPayData.get(options1).equals(mDataBinding.includeView.tvDay1.getText().toString())) {
                            toast(R.string.same_pay_day);
                            break;
                        }
                        if (listPayData.get(options1).equals(mDataBinding.includeView.tvDay2.getText().toString())) {
                            toast(R.string.same_pay_day);
                            break;
                        }
                        if (listPayData.get(options1).equals(mDataBinding.includeView.tvDay3.getText().toString())) {
                            toast(R.string.same_pay_day);
                            break;
                        }

                        mDataBinding.includeView.tvDay4.setText(listPayData.get(options1));
                        payDate4 = options1;
                        break;
                    }
                    default:
                        break;
                }
            }, R.string.pay_cycle_hint_day4);
            payDatePickerView.setPicker(listPayData);
            if (payDatePickerView != null && listPayData != null) {
                payDatePickerView.show(mDataBinding.includeView.tvDay4);
            }
        });

        //就业证明正面
        RxViewUtil.clicks(mDataBinding.ivDataUpdateFont).subscribe(o -> rxPermissions.request(PermissionGroup.PERMISSION_PHOTO)
                .subscribe(granted -> {
                    if (granted) {
                        //添加图片
                        if (disposable1 != null) {
                            disposable1.dispose();
                        }
                        MultiImageSelector selector = MultiImageSelector.create();
                        selector.showCamera(true);
                        selector.single();
                        selector.start(getActivity(), 0);
                    } else {
                        UiUtils.openSetting(getActivity(), R.string.open_camera_file_permission);
                    }
                }, throwable -> {
                }));

        //就业证明反面
        RxViewUtil.clicks(mDataBinding.ivDataUpdateBack).subscribe(o -> rxPermissions.request(PermissionGroup.PERMISSION_PHOTO)
                .subscribe(granted -> {
                    if (granted) {
                        //添加图片
                        if (disposable2 != null) {
                            disposable2.dispose();
                        }
                        MultiImageSelector selector = MultiImageSelector.create();
                        selector.showCamera(true);
                        selector.single();
                        selector.start(getActivity(), 1);
                    } else {
                        UiUtils.openSetting(getActivity(), R.string.open_camera_file_permission);
                    }
                }, throwable -> {
                }));
        mDataBinding.acIvImg.setOnClickListener(v -> {
//            JumpManager.jumpTo(getActivity(), ImageActivity.class);
            ARouter.getInstance().build(RouteConstant.IMAGE_PAGE).navigation();
        });


        //最新工资单
        RxViewUtil.clicks(mDataBinding.ivTheLatestPayroll).subscribe(o -> rxPermissions.request(PermissionGroup.PERMISSION_PHOTO)
                .subscribe(granted -> {
                    if (granted) {
                        if (disposable3 != null) {
                            disposable3.dispose();
                        }
                        Constants.SUBMIT_STATUS = false;
                        //添加图片
                        MultiImageSelector selector = MultiImageSelector.create();
                        selector.showCamera(true);
                        selector.single();
                        selector.start(getActivity(), 2);
                    } else {
                        UiUtils.openSetting(getActivity(), R.string.open_camera_file_permission);
                    }
                }, throwable -> {
                })
        );
    }

    private void initPickerView() {
        worktimeArray = getResources().getStringArray(R.array.work_time_array);
        workTimePickerView = UiUtils.initOptionPicker(getActivity(), (options1, options2, options3, v) -> {
            switch (options1) {
                case 0:
                case 1:
                case 2:
                    workTimeCode = options1 - 3;
                    break;
                case 3:
                    workTimeCode = 1;
                    break;
                case 4:
                    workTimeCode = 4;
                    break;
                default:
            }
            mDataBinding.tvWorkTime.setText(worktimeArray[options1]);
        }, R.string.work_time_title);
        workTimePickerView.setPicker(Arrays.asList(worktimeArray));

        payCycleArray = getResources().getStringArray(R.array.pay_cycle);
        payCyclePickerView = UiUtils.initOptionPicker(getActivity(), new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                payType = options1;
                mDataBinding.tvPayCycle.setText(payCycleArray[options1]);
                mDataBinding.includeView.tvDay1.setText("");
                mDataBinding.includeView.tvDay2.setText("");
                mDataBinding.includeView.tvDay3.setText("");
                mDataBinding.includeView.tvDay4.setText("");
                switch (options1) {
                    case 0: {
                        mDataBinding.includeView.llDay1.setVisibility(View.GONE);
                        mDataBinding.includeView.llDay2.setVisibility(View.GONE);
                        mDataBinding.includeView.llDay3.setVisibility(View.GONE);
                        mDataBinding.includeView.llDay4.setVisibility(View.GONE);
                        break;
                    }
                    case 1: {
                        mDataBinding.includeView.llDay1.setVisibility(View.VISIBLE);
                        mDataBinding.includeView.llDay2.setVisibility(View.VISIBLE);
                        mDataBinding.includeView.llDay3.setVisibility(View.VISIBLE);
                        mDataBinding.includeView.llDay4.setVisibility(View.VISIBLE);
                        com.hwangjr.rxbus.RxBus.get().post(EventConstants.INFORM_BTN_CHANGE, 0);
                        break;
                    }
                    case 2: {
                        mDataBinding.includeView.llDay1.setVisibility(View.VISIBLE);
                        mDataBinding.includeView.llDay2.setVisibility(View.VISIBLE);
                        mDataBinding.includeView.llDay3.setVisibility(View.GONE);
                        mDataBinding.includeView.llDay4.setVisibility(View.GONE);
                        com.hwangjr.rxbus.RxBus.get().post(EventConstants.INFORM_BTN_CHANGE, 0);
                        break;
                    }
                    case 3: {
                        mDataBinding.includeView.llDay1.setVisibility(View.VISIBLE);
                        mDataBinding.includeView.llDay2.setVisibility(View.GONE);
                        mDataBinding.includeView.llDay3.setVisibility(View.GONE);
                        mDataBinding.includeView.llDay4.setVisibility(View.GONE);
                        com.hwangjr.rxbus.RxBus.get().post(EventConstants.INFORM_BTN_CHANGE, 0);
                        break;
                    }
                    default:
                        break;
                }
            }
        }, R.string.pay_cycle);
        payCyclePickerView.setPicker(Arrays.asList(payCycleArray));

        listPayData = new ArrayList<>();
        for (int i = 1; i <= 31; i++) {
            switch (i) {
                case 1: {
                    listPayData.add("1st");
                    break;
                }
                case 2: {
                    listPayData.add("2nd");
                    break;
                }
                case 3: {
                    listPayData.add("3rd");
                    break;
                }
                default: {
                    listPayData.add(i + "th");
                    break;
                }
            }
        }
    }

    private void listenerAll() {
        listenEditTextEvents();
        listenerTwo();
        listenerDate();
    }

    @SuppressLint("CheckResult")
    private void listenEditTextEvents() {
        Flowable<CharSequence> companyObservable = RxTextView.textChanges(mDataBinding.etCompanyName).toFlowable(BackpressureStrategy.LATEST);
        Flowable<CharSequence> companyteleObservable = RxTextView.textChanges(mDataBinding.etCompanyTele).toFlowable(BackpressureStrategy.LATEST);
        Flowable<CharSequence> companypostcodeObservable = RxTextView.textChanges(mDataBinding.etCompanyPostcode).toFlowable(BackpressureStrategy.LATEST);
        Flowable<CharSequence> workjobsObservable = RxTextView.textChanges(mDataBinding.tvWorkJobs).toFlowable(BackpressureStrategy.LATEST);
        Flowable<CharSequence> worktimeObservable = RxTextView.textChanges(mDataBinding.tvWorkTime).toFlowable(BackpressureStrategy.LATEST);
        Flowable<CharSequence> islandObservable = RxTextView.textChanges(mDataBinding.tvIsland).toFlowable(BackpressureStrategy.LATEST);
        Flowable<CharSequence> provinceObservable = RxTextView.textChanges(mDataBinding.tvProvince).toFlowable(BackpressureStrategy.LATEST);
        Flowable<CharSequence> cityObservable = RxTextView.textChanges(mDataBinding.tvCity).toFlowable(BackpressureStrategy.LATEST);
        Flowable.combineLatest(companyObservable, companyteleObservable, workjobsObservable,
                worktimeObservable, companypostcodeObservable, islandObservable, provinceObservable, cityObservable,
                (company, companytele, workjobs, worktime, companypostcode, island, province, city) -> {
                    Log.i("监听一", "监听一 combineLatest: company=" + company.toString().trim() + "、companytele=" + companytele.toString().trim()
                            + "、workjobs=" + workjobs.toString().trim() + "、worktime=" + worktime.toString().trim()
                            + "、companypostcode=" + companypostcode.toString().trim() + "、island=" + island.toString().trim()
                            + "、province=" + province.toString().trim() + "、city=" + city.toString().trim());
                    Constants.PAYROLLETLISTENONE = !TextUtils.isEmpty(company.toString().trim())
                            && !TextUtils.isEmpty(companytele.toString().trim())
                            && !TextUtils.isEmpty(workjobs.toString().trim())
                            && !TextUtils.isEmpty(worktime.toString().trim())
                            && !TextUtils.isEmpty(companypostcode.toString().trim())
                            && !TextUtils.isEmpty(island.toString().trim())
                            && !TextUtils.isEmpty(province.toString().trim())
                            && !TextUtils.isEmpty(city.toString().trim());
                    Log.i("监听一", "监听一listenEditTextEvents: " + Constants.PAYROLLETLISTENONE);
                    return Constants.PAYROLLETLISTENONE
                            && Constants.PAYROLLETLISTENTWO
                            && Constants.PAY_DATE_STATES
                            && Constants.PAYROLLSELECTIMAGE
                            && Constants.PRIVATEOWNERSELECTIMAGE && Constants.STUDENTSELECTIMAGE;

                }).subscribe(aBoolean -> com.hwangjr.rxbus.RxBus.get().post(EventConstants.INFORM_BTN_CHANGE, aBoolean ? 1 : 0), throwable -> {
        });
    }

    @SuppressLint("CheckResult")
    private void listenerTwo() {
        mDataBinding.tvMonthIncome.setFilters(new InputFilter[]{new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                if (source.equals(".") && dest.toString().length() == 0) {
                    return "0.00";
                }
                if (dest.toString().contains(".")) {
                    int index = dest.toString().indexOf(".");
                    int mLength = dest.toString().substring(index).length();
                    if (mLength == 3) {
                        return "";
                    }
                }
                return null;
            }
        }});
        Flowable<CharSequence> natureObservable =
                RxTextView.textChanges(mDataBinding.tvCompanyNature).toFlowable(BackpressureStrategy.LATEST);
        Flowable<CharSequence> monthincomeObservable =
                RxTextView.textChanges(mDataBinding.tvMonthIncome).toFlowable(BackpressureStrategy.LATEST);

        Flowable.combineLatest( monthincomeObservable, natureObservable,
                (charSequence, charSequence2) -> {
                    Constants.PAYROLLETLISTENTWO =
                            !TextUtils.isEmpty(charSequence.toString().trim()) &&
                            !TextUtils.isEmpty(charSequence2.toString().trim());
                    return Constants.PAYROLLETLISTENONE
                            && Constants.PAYROLLETLISTENTWO
                            && Constants.PAY_DATE_STATES
                            && Constants.PAYROLLSELECTIMAGE
                            && Constants.PRIVATEOWNERSELECTIMAGE && Constants.STUDENTSELECTIMAGE;
                }).subscribe(aBoolean -> {
            if (aBoolean) {
                com.hwangjr.rxbus.RxBus.get().post(EventConstants.INFORM_BTN_CHANGE, 1);
            } else {
                com.hwangjr.rxbus.RxBus.get().post(EventConstants.INFORM_BTN_CHANGE, 0);
            }
        }, throwable -> {
        });

    }

    @SuppressLint("CheckResult")
    private void listenerDate() {
        Flowable<CharSequence> payCycleObservable =
                RxTextView.textChanges(mDataBinding.tvPayCycle).toFlowable(BackpressureStrategy.LATEST);
        Flowable<CharSequence> payDate1Observable =
                RxTextView.textChanges(mDataBinding.includeView.tvDay1).toFlowable(BackpressureStrategy.LATEST);
        Flowable<CharSequence> payDate2Observable =
                RxTextView.textChanges(mDataBinding.includeView.tvDay2).toFlowable(BackpressureStrategy.LATEST);
        Flowable<CharSequence> payDate3Observable =
                RxTextView.textChanges(mDataBinding.includeView.tvDay3).toFlowable(BackpressureStrategy.LATEST);
        Flowable<CharSequence> payDate4Observable =
                RxTextView.textChanges(mDataBinding.includeView.tvDay4).toFlowable(BackpressureStrategy.LATEST);
        Flowable.combineLatest(payCycleObservable, payDate1Observable, payDate2Observable, payDate3Observable, payDate4Observable,
                (charSequence, charSequence2, charSequence3, charSequence4, charSequence5) -> {
                    switch (payType) {
                        case 0: {
                            return Constants.PAY_DATE_STATES = !TextUtils.isEmpty(charSequence.toString().trim());
                        }
                        case 1: {
                            return Constants.PAY_DATE_STATES = !TextUtils.isEmpty(charSequence.toString().trim())
                                    && !TextUtils.isEmpty(charSequence2.toString().trim())
                                    && !TextUtils.isEmpty(charSequence3.toString().trim())
                                    && !TextUtils.isEmpty(charSequence4.toString().trim())
                                    && !TextUtils.isEmpty(charSequence5.toString().trim())
                                    && !charSequence2.toString().trim().equals(charSequence3.toString().trim())
                                    && !charSequence2.toString().trim().equals(charSequence4.toString().trim())
                                    && !charSequence2.toString().trim().equals(charSequence5.toString().trim())
                                    && !charSequence3.toString().trim().equals(charSequence4.toString().trim())
                                    && !charSequence3.toString().trim().equals(charSequence5.toString().trim())
                                    && !charSequence4.toString().trim().equals(charSequence5.toString().trim());
                        }
                        case 2: {
                            return Constants.PAY_DATE_STATES = !TextUtils.isEmpty(charSequence.toString().trim())
                                    && !TextUtils.isEmpty(charSequence2.toString().trim())
                                    && !TextUtils.isEmpty(charSequence3.toString().trim())
                                    && !charSequence2.toString().trim().equals(charSequence3.toString().trim());
                        }
                        case 3: {
                            return Constants.PAY_DATE_STATES = !TextUtils.isEmpty(charSequence.toString().trim())
                                    && !TextUtils.isEmpty(charSequence2.toString().trim());
                        }
                        default:
                            return false;
                    }
                }).subscribe(aBoolean -> {
            Constants.PAY_DATE_STATES = aBoolean;
            if (Constants.PAYROLLETLISTENONE && Constants.PAYROLLETLISTENTWO && Constants.PAY_DATE_STATES && Constants.PAYROLLSELECTIMAGE && Constants.PRIVATEOWNERSELECTIMAGE && Constants.STUDENTSELECTIMAGE) {
                if (aBoolean) {
                    com.hwangjr.rxbus.RxBus.get().post(EventConstants.INFORM_BTN_CHANGE, 1);
                } else {
                    com.hwangjr.rxbus.RxBus.get().post(EventConstants.INFORM_BTN_CHANGE, 0);
                }
            }
        }, throwable -> {
        });
    }

    @SuppressLint("CheckResult")
    @Override
    public void salariesinfoSuc(BaseBean baseBean) {
        if (DataResult.isSuccessToast(getActivity(), baseBean)) {
            getActivity().finish();
        }
    }

    @Override
    public void getSalariesInfoSuc(BaseBean<SalariesEntity> baseBean) {
        if (DataResult.isSuccessUnToast(getActivity(), baseBean)) {
            SalariesEntity salariesEntity = baseBean.getData();
            if (salariesEntity != null) {
                Constants.PAYROLLSELECTIMAGE = true;
                Constants.PRIVATEOWNERSELECTIMAGE = true;
                Constants.STUDENTSELECTIMAGE = true;
                mDataBinding.etCompanyName.setText(salariesEntity.getCompanyName());
                mDataBinding.etCompanyTele.setText(salariesEntity.getCompanyMobile());
                mDataBinding.etCompanyPostcode.setText(salariesEntity.getPostalCode());
                if (salariesEntity.getWorkTime() == -3 || salariesEntity.getWorkTime() == -2 || salariesEntity.getWorkTime() == -1) {
                    mDataBinding.tvWorkTime.setText(worktimeArray[salariesEntity.getWorkTime() + 3]);
                } else if (salariesEntity.getWorkTime() == 1) {
                    mDataBinding.tvWorkTime.setText(worktimeArray[3]);
                } else if (salariesEntity.getWorkTime() == 4) {
                    mDataBinding.tvWorkTime.setText(worktimeArray[4]);
                }

                workTimeCode = salariesEntity.getWorkTime();
                position = salariesEntity.getPerformance();
                if (salariesEntity.getOccupationId() > 0) {
                    mDataBinding.tvWorkJobs.setText(salariesEntity.getWorkPost());
                    positionEntity = new PositionEntity();
                    positionEntity.setyName(salariesEntity.getWorkPost());
                    positionEntity.setId(salariesEntity.getOccupationId());
                    positionEntity.setPid(salariesEntity.getpId());
                }
                mDataBinding.tvCompanyNature.setText(salariesEntity.getCompanyNature());
                mDataBinding.tvIsland.setText(salariesEntity.getProvince());
                mDataBinding.tvProvince.setText(salariesEntity.getRegion());
                mDataBinding.tvCity.setText(salariesEntity.getMunicipality());
                mDataBinding.tvMonthIncome.setText(String.valueOf(salariesEntity.getMonthIncome()));
                disposable1 = Flowable.just(Objects.requireNonNull(salariesEntity.getEmploymentCertificate()))
                        .subscribeOn(Schedulers.io())
                        .map(url -> Glide.with(this).download(url).submit().get())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(file -> {
                            imageurl1 = file;
                            Glide.with(BaseApplication.getInstance())
                                    .asBitmap()
                                    .load(imageurl1)
                                    .centerCrop()
                                    .error(R.drawable.data_update_bg)
                                    .placeholder(R.drawable.data_update_bg)
                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                                    .into(mDataBinding.ivDataUpdateFont);
                            mDataBinding.tvDataUpdateHintFont.setVisibility(View.GONE);
                            mDataBinding.tvDataHintFont.setVisibility(View.GONE);
                        }, throwable -> {
                        });
                disposable2 = Flowable.just(Objects.requireNonNull(salariesEntity.getEmploymentCertificateBack()))
                        .subscribeOn(Schedulers.io())
                        .map(url -> Glide.with(this).download(url).submit().get())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(file -> {
                            imageurl2 = file;
                            Glide.with(BaseApplication.getInstance())
                                    .asBitmap()
                                    .load(imageurl2)
                                    .centerCrop()
                                    .error(R.drawable.data_update_bg)
                                    .placeholder(R.drawable.data_update_bg)
                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                                    .into(mDataBinding.ivDataUpdateBack);
                            mDataBinding.tvDataUpdateHintBack.setVisibility(View.GONE);
                            mDataBinding.tvDataHintBack.setVisibility(View.GONE);
                        }, throwable -> {
                        });
                disposable3 = Flowable.just(Objects.requireNonNull(salariesEntity.getTheLatestPayroll()))
                        .subscribeOn(Schedulers.io())
                        .map(url -> Glide.with(this).download(url).submit().get())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(file -> {
                            imageurl3 = file;
                            Glide.with(BaseApplication.getInstance()).asBitmap().load(imageurl3).centerCrop().error(R.drawable.data_update_bg)
                                    .placeholder(R.drawable.data_update_bg).diskCacheStrategy(DiskCacheStrategy.ALL).into(mDataBinding.ivTheLatestPayroll);
                            mDataBinding.tvTheLatestPayrollHint.setVisibility(View.GONE);
                            mDataBinding.tvTheLatestPayrollHints.setVisibility(View.GONE);
                        }, throwable -> {
                        });
                mDataBinding.tvPayCycle.setText(payCycleArray[salariesEntity.getSalaryMethod()]);
                payType = salariesEntity.getSalaryMethod();
                Constants.PAY_DATE_STATES = true;
                if (payType == 1) {
                    mDataBinding.includeView.llDay1.setVisibility(View.VISIBLE);
                    mDataBinding.includeView.llDay2.setVisibility(View.VISIBLE);
                    mDataBinding.includeView.llDay3.setVisibility(View.VISIBLE);
                    mDataBinding.includeView.llDay4.setVisibility(View.VISIBLE);
                    mDataBinding.includeView.tvDay1.setText(listPayData.get(salariesEntity.getPaydayOne() - 1));
                    mDataBinding.includeView.tvDay2.setText(listPayData.get(salariesEntity.getPaydayTwo() - 1));
                    mDataBinding.includeView.tvDay3.setText(listPayData.get(salariesEntity.getPaydayThree() - 1));
                    mDataBinding.includeView.tvDay4.setText(listPayData.get(salariesEntity.getPaydayFour() - 1));
                    payDate1 = salariesEntity.getPaydayOne();
                    payDate2 = salariesEntity.getPaydayTwo();
                    payDate3 = salariesEntity.getPaydayThree();
                    payDate4 = salariesEntity.getPaydayFour();
                }

                if (payType == 2) {
                    mDataBinding.includeView.llDay1.setVisibility(View.VISIBLE);
                    mDataBinding.includeView.llDay2.setVisibility(View.VISIBLE);
                    mDataBinding.includeView.llDay3.setVisibility(View.GONE);
                    mDataBinding.includeView.llDay4.setVisibility(View.GONE);
                    mDataBinding.includeView.tvDay1.setText(listPayData.get(salariesEntity.getPaydayOne() - 1));
                    mDataBinding.includeView.tvDay2.setText(listPayData.get(salariesEntity.getPaydayTwo() - 1));
                    payDate1 = salariesEntity.getPaydayOne();
                    payDate2 = salariesEntity.getPaydayTwo();
//                    Log.i(TAG, "getSalariesInfoSuc: 两周");
                }

                if (payType == 3) {
                    mDataBinding.includeView.llDay1.setVisibility(View.VISIBLE);
                    mDataBinding.includeView.llDay2.setVisibility(View.GONE);
                    mDataBinding.includeView.llDay3.setVisibility(View.GONE);
                    mDataBinding.includeView.llDay4.setVisibility(View.GONE);
                    mDataBinding.includeView.tvDay1.setText(listPayData.get(salariesEntity.getPaydayOne() - 1));
                    payDate1 = salariesEntity.getPaydayOne();
//                    Log.i(TAG, "getSalariesInfoSuc: 一个月");
                }
            }
        }

    }

    @Override
    public void getCompanyNatureSuc(BaseBean<List<CompanyNatureEntity>> baseBean) {
        if (DataResult.isSuccessUnToast(getActivity(), baseBean)) {
            mNatureEntityList = baseBean.getData();
            natureView = UiUtils.initOptionPicker(getActivity(), pickerListener, R.string.company_nature_hint);
            natureView.setPicker(mNatureEntityList);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case Constants.CAREERCODE:
                    if (data.getParcelableExtra(ExtraKeys.Key_Msg1) != null) {
                        positionEntity = data.getParcelableExtra(ExtraKeys.Key_Msg1);
                        mDataBinding.tvWorkJobs.setText(positionEntity.getyName());
                    }
                    break;
                default:
                    break;
            }
        }
    }
}
