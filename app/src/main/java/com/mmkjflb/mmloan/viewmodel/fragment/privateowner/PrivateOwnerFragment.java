package com.mmkjflb.mmloan.viewmodel.fragment.privateowner;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bigkoo.pickerview.OptionsPickerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.mmkj.baselibrary.RouteConstant;
import com.mmkj.baselibrary.annotation.ActivityFragmentInject;
import com.mmkj.baselibrary.model.entitry.BaseBean;
import com.mmkj.baselibrary.util.PreferenceUtils;
import com.mmkj.baselibrary.util.RxBus;
import com.mmkj.baselibrary.util.RxViewUtil;
import com.mmkjflb.mmloan.R;
import com.mmkjflb.mmloan.app.BaseApplication;
import com.mmkjflb.mmloan.app.Constants;
import com.mmkjflb.mmloan.app.PermissionGroup;
import com.mmkjflb.mmloan.base.BaseFragment;
import com.mmkjflb.mmloan.databinding.FragmentPrivateBinding;
import com.mmkjflb.mmloan.model.entity.ClickEvent;
import com.mmkjflb.mmloan.model.entity.IslandProvinceCityEntity;
import com.mmkjflb.mmloan.model.entity.PrivateOwnerEntity;
import com.mmkjflb.mmloan.model.http.DataResult;
import com.mmkjflb.mmloan.utils.UiUtils;
import com.mmkjflb.mmloan.utils.location.LocalUtils;
import com.mmkjflb.mmloan.utils.location.beans.LocalBean;
import com.mmkjflb.mmloan.utils.location.beans.MunicipalityBean;
import com.mmkjflb.mmloan.utils.location.beans.ProvincesBean;
import com.mmkjflb.mmloan.viewmodel.extracredit.ExtraCreditActivity;
import com.syhmmfqphl.xyxlibrary.utils.JumpManager;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import me.nereo.multi_image_selector.MultiImageSelector;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by
 * Date: On 2018/3/5
 * Author: wangjieyan
 * Email: wangjieyan9308@163.com
 */
@ActivityFragmentInject(contentViewId = R.layout.fragment_private)
public class PrivateOwnerFragment extends BaseFragment<PrivateOwnerPresenter, FragmentPrivateBinding> implements PrivateOwnerContract.View {
    private List<LocalBean> islandProvinceCityEntityList = null;
    private List<ProvincesBean> provinceListBeanList = null;
    private List<MunicipalityBean> cityListBeanList = null;
    private OptionsPickerView islandPickerView = null;
    private OptionsPickerView provincePickerView = null;
    private OptionsPickerView cityPickerView = null;
    private RxPermissions rxPermissions;
    private File imageurl = null;
    private Disposable disposable = null;

    @Override
    protected void initViews() {
        getFragmentComponent().inject(this);
//        mPresenter.getIslandsInfo(PreferenceUtils.getString(PreferenceUtils.USER_SESSIONID, ""));
        rxPermissions = new RxPermissions(getActivity());
        initData();
        listenEditTextEvents();
        initClick();
    }

    //初始化回填数据
    private void initData() {
        mPresenter.getSelfemployInfo(PreferenceUtils.getString(PreferenceUtils.USER_SESSIONID, ""));
        islandProvinceCityEntityList = LocalUtils.getInstance().getRegionBeanList();
        islandPickerView = UiUtils.initOptionPicker(getActivity(), pickerListener, R.string.island);
        islandPickerView.setPicker(islandProvinceCityEntityList);
    }

    @SuppressLint("CheckResult")
    private void initClick() {
        //注册监听者
        RxBus.getDefault().toObservable(ClickEvent.class)
                .observeOn(AndroidSchedulers.mainThread()).subscribe(performance -> {
            if (performance.getStatus() == 2) {
                Flowable.fromArray(imageurl)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(file -> {
                            RequestBody faceRequestBody = RequestBody.create(MediaType.parse("image/jpg"), file);
                            MultipartBody.Part part = MultipartBody.Part.createFormData("businessLicense", "businessLicense" + System.currentTimeMillis() + ".jpg", faceRequestBody);

                            Map<String, RequestBody> request = new HashMap<>();
                            request.put("sessionId", RequestBody.create(null, PreferenceUtils.getString(PreferenceUtils.USER_SESSIONID, "")));
                            request.put("performance", RequestBody.create(null, String.valueOf(performance.getStatus())));
                            request.put("companyName", RequestBody.create(null, mDataBinding.etCompanyName.getText().toString().trim()));
                            request.put("companyMobile", RequestBody.create(null, mDataBinding.etCompanyTele.getText().toString().trim()));
                            request.put("companyEmail", RequestBody.create(null, mDataBinding.etCompanyEmail.getText().toString().trim()));
                            request.put("postalCode", RequestBody.create(null, mDataBinding.etCompanyPostcode.getText().toString().trim()));
                            request.put("companyAddress", RequestBody.create(null, mDataBinding.etCompanyAddress.getText().toString().trim()));
                            request.put("islands", RequestBody.create(null, mDataBinding.tvIsland.getText().toString().trim()));
                            request.put("province", RequestBody.create(null, mDataBinding.tvProvince.getText().toString().trim()));
                            request.put("city", RequestBody.create(null, mDataBinding.tvCity.getText().toString().trim()));
                            //request.put("address", RequestBody.create(null,mDataBinding.etLiveaddress.getText().toString().trim()));
                            mPresenter.selfemployinfo(request, part);
                        }, throwable -> toast(R.string.img_upload_error));
            }
            if (performance.getType() == 2 && performance.getUrl() != null) {
                imageurl = performance.getUrl();
                Glide.with(BaseApplication.getInstance()).load(imageurl).error(R.drawable.data_update_bg)
                        .placeholder(R.drawable.data_update_bg)
                        .format(DecodeFormat.PREFER_ARGB_8888).into(mDataBinding.ivDataUpdate);
                mDataBinding.tvDataUpdateHint.setVisibility(View.GONE);
            }
        });
        RxViewUtil.clicks(mDataBinding.tvIsland).subscribe(o -> {
            if (islandPickerView != null && islandProvinceCityEntityList != null) {
                islandPickerView.show(mDataBinding.tvIsland);
            }
        });
        RxViewUtil.clicks(mDataBinding.tvProvince).subscribe(o -> {
            if (provincePickerView != null && provinceListBeanList != null) {
                provincePickerView.show(mDataBinding.tvProvince);
            } else {
                toast(R.string.pls_chose_island);
            }
        });

        RxViewUtil.clicks(mDataBinding.tvCity).subscribe(o -> {
            if (cityPickerView != null && cityListBeanList != null) {
                cityPickerView.show(mDataBinding.tvCity);
            } else {
                toast(R.string.pls_chose_province);
            }
        });

        RxViewUtil.clicks(mDataBinding.ivDataUpdate).subscribe(o -> rxPermissions.request(PermissionGroup.PERMISSION_PHOTO)
                .subscribe(granted -> {
                    if (granted) {
                        Constants.SUBMIT_STATUS = true;
                        //添加图片
                        if (disposable != null) {
                            disposable.dispose();
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
    }

    @Override
    public void getIslandsInfoSuc(BaseBean<List<IslandProvinceCityEntity>> baseBean) {
//        if (DataResult.isSuccessUnToast(getActivity(), baseBean)) {
//            islandProvinceCityEntityList = baseBean.getData();
//            islandPickerView = UiUtils.initOptionPicker(getActivity(), pickerListener, R.string.island);
//            islandPickerView.setPicker(islandProvinceCityEntityList);
//        }
    }

    @Override
    public void selfemployinfoSuc(BaseBean baseBean) {
        if (DataResult.isSuccessToast(getActivity(), baseBean)) {
//            getActivity().setResult(Activity.RESULT_OK);
//            getActivity().finish();
//            JumpManager.jumpToClose(getActivity(), ExtraCreditActivity.class);
            ARouter.getInstance().build( RouteConstant.EXTRA_CREDIT).navigation();getActivity().finish();

        }
    }

    @Override
    public void getSelfemployInfoSuc(BaseBean<PrivateOwnerEntity> baseBean) {
        if (DataResult.isSuccessUnToast(getActivity(), baseBean)) {
            if (baseBean.getData() != null) {
                mDataBinding.etCompanyName.setText(baseBean.getData().getCompanyName());
                mDataBinding.etCompanyTele.setText(baseBean.getData().getCompanyMobile());
                mDataBinding.etCompanyEmail.setText(baseBean.getData().getCompanyEmail());
                mDataBinding.etCompanyPostcode.setText(baseBean.getData().getPostalCode());
                mDataBinding.etCompanyAddress.setText(baseBean.getData().getCompanyAddress());
                mDataBinding.tvIsland.setText(baseBean.getData().getIslands());
                mDataBinding.tvProvince.setText(baseBean.getData().getProvince());
                mDataBinding.tvCity.setText(baseBean.getData().getCity());
                //mDataBinding.etLiveaddress.setText(baseBean.getData().getAddress());
                disposable = Flowable.just(baseBean.getData().getBusinessLicense())
                        .subscribeOn(Schedulers.io())
                        .map(url -> Glide.with(this).download(url).submit().get())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(file -> {
                            imageurl = file;
                            Glide.with(BaseApplication.getInstance()).asBitmap().load(imageurl).centerCrop().error(R.drawable.data_update_bg)
                                    .placeholder(R.drawable.data_update_bg).diskCacheStrategy(DiskCacheStrategy.ALL).into(mDataBinding.ivDataUpdate);
                            mDataBinding.tvDataUpdateHint.setVisibility(View.GONE);
                        }, throwable -> {
                        });
            }
        }
    }

    @SuppressLint("CheckResult")
    private void listenEditTextEvents() {
        Flowable<CharSequence> companyObservable = RxTextView.textChanges(mDataBinding.etCompanyName).toFlowable(BackpressureStrategy.LATEST);
        Flowable<CharSequence> companyteleObservable = RxTextView.textChanges(mDataBinding.etCompanyTele).toFlowable(BackpressureStrategy.LATEST);
        Flowable<CharSequence> companyemailObservable = RxTextView.textChanges(mDataBinding.etCompanyEmail).toFlowable(BackpressureStrategy.LATEST);
        Flowable<CharSequence> companypostcodeObservable = RxTextView.textChanges(mDataBinding.etCompanyPostcode).toFlowable(BackpressureStrategy.LATEST);
        Flowable<CharSequence> companyaddressObservable = RxTextView.textChanges(mDataBinding.etCompanyAddress).toFlowable(BackpressureStrategy.LATEST);
        Flowable<CharSequence> islandObservable = RxTextView.textChanges(mDataBinding.tvIsland).toFlowable(BackpressureStrategy.LATEST);
        Flowable<CharSequence> provinceObservable = RxTextView.textChanges(mDataBinding.tvProvince).toFlowable(BackpressureStrategy.LATEST);
        Flowable<CharSequence> cityObservable = RxTextView.textChanges(mDataBinding.tvCity).toFlowable(BackpressureStrategy.LATEST);
        // Flowable<CharSequence> liveaddressObservable = RxTextView.textChanges(mDataBinding.etLiveaddress).toFlowable(BackpressureStrategy.LATEST);
        Flowable.combineLatest(companyObservable, companyteleObservable, companyemailObservable, companypostcodeObservable, companyaddressObservable, islandObservable
                , provinceObservable, cityObservable,
                (company, companytele, companyemail, companypostcode, companyaddress, island, province, city) -> {
                    Constants.PRIVATEOWNERETLISTENONE = !TextUtils.isEmpty(company.toString().trim()) && !TextUtils.isEmpty(companytele.toString().trim()) && !TextUtils.isEmpty(companyemail.toString().trim())
                            && !TextUtils.isEmpty(companypostcode.toString().trim()) && !TextUtils.isEmpty(companyaddress.toString().trim())
                            && !TextUtils.isEmpty(island.toString().trim()) && !TextUtils.isEmpty(province.toString().trim())
                            && !TextUtils.isEmpty(city.toString().trim());
                    return Constants.PRIVATEOWNERETLISTENONE && Constants.PRIVATEOWNERSELECTIMAGE;
                }).subscribe(aBoolean -> {
//			if (aBoolean) {
//				com.hwangjr.rxbus.RxBus.get().post(EventConstants.INFORM_BTN_CHANGE, 1);
//			} else {
//				com.hwangjr.rxbus.RxBus.get().post(EventConstants.INFORM_BTN_CHANGE, 0);
//			}
        }, throwable -> {

        });
    }

    private OptionsPickerView.OnOptionsSelectListener pickerListener = new OptionsPickerView.OnOptionsSelectListener() {
        @Override
        public void onOptionsSelect(int options1, int options2, int options3, View v) {
            switch (v.getId()) {
                case R.id.tv_island:
                    mDataBinding.tvIsland.setText(islandProvinceCityEntityList.get(options1).getPickerViewText());
                    mDataBinding.tvProvince.setText("");
                    mDataBinding.tvCity.setText("");
                    provinceListBeanList = null;
                    cityListBeanList = null;
                    provincePickerView = UiUtils.initOptionPicker(getActivity(), pickerListener, R.string.province);
                    provinceListBeanList = islandProvinceCityEntityList.get(options1).getProvincesBeanList();
                    provincePickerView.setPicker(provinceListBeanList);
                    break;
                case R.id.tv_province:
                    mDataBinding.tvProvince.setText(provinceListBeanList.get(options1).getPickerViewText());
                    mDataBinding.tvCity.setText("");
                    cityPickerView = UiUtils.initOptionPicker(getActivity(), pickerListener, R.string.city);
                    cityListBeanList = null;
                    cityListBeanList = provinceListBeanList.get(options1).getMunicipalityBeanList();
                    cityPickerView.setPicker(cityListBeanList);
                    break;
                case R.id.tv_city:
                    mDataBinding.tvCity.setText(cityListBeanList.get(options1).getPickerViewText());
                    break;
                default:
                    break;
            }
        }
    };
}
