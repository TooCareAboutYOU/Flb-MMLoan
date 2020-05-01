package com.mmkjflb.mmloan.viewmodel.assetinform;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bigkoo.pickerview.OptionsPickerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.mmkj.baselibrary.RouteConstant;
import com.mmkj.baselibrary.annotation.ActivityFragmentInject;
import com.mmkj.baselibrary.model.entitry.BaseBean;
import com.mmkj.baselibrary.util.PreferenceUtils;
import com.mmkj.baselibrary.util.RxViewUtil;
import com.mmkjflb.mmloan.R;
import com.mmkjflb.mmloan.app.BaseApplication;
import com.mmkjflb.mmloan.app.PermissionGroup;
import com.mmkjflb.mmloan.base.BaseActivity;
import com.mmkjflb.mmloan.databinding.ActivityAssetInformBinding;
import com.mmkjflb.mmloan.model.entity.AssertsInfoEntity;
import com.mmkjflb.mmloan.model.http.DataResult;
import com.mmkjflb.mmloan.utils.UiUtils;
import com.mmkjflb.mmloan.viewmodel.emergencycontact.EmergencyContactActivity;
import com.syhmmfqphl.xyxlibrary.utils.JumpManager;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import me.nereo.multi_image_selector.MultiImageSelector;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

/**
 * date: 2018/3/1 16:31
 * author: xuyexiang
 * title:资产信息
 */
@Route(path = RouteConstant.ASSET_INFORM)
@ActivityFragmentInject(contentViewId = R.layout.activity_asset_inform, toolbarTitle = R.string.asset_inform)
public class AssetInformActivity extends BaseActivity<AssetInformPresenter, ActivityAssetInformBinding> implements AssetInformContract.View {

    private static final int UPLOADREQUEST = 100;
    private List<String> trafficData;
    private OptionsPickerView trafficPickerView;
    private int transportationCode = -1;
    private boolean editEnable = false;
    private boolean uploadEnable = false;
    private RxPermissions rxPermissions;
    private File uploadUrl = null;
    private Disposable disposable;
    private long logLastTime = 0L;

    @Override

    protected void initViews() {
        getActivityComponent().inject(this);
        rxPermissions = new RxPermissions(AssetInformActivity.this);
        initPickerView();
        initClicks();
        listenEditTextEvents();
        mPresenter.getAssertsInfo(PreferenceUtils.getString(PreferenceUtils.USER_SESSIONID, ""));
    }

    private void initPickerView() {
        //交通工具
        trafficData = Arrays.asList(getResources().getStringArray(R.array.traffic_data));
        trafficPickerView = UiUtils.initOptionPicker(this, (options1, options2, options3, v) -> {
            transportationCode = options1;
            mDataBinding.tvTrafficTool.setText(trafficData.get(options1));
            judgeShow(options1);
        }, R.string.traffic_tool);
        trafficPickerView.setPicker(trafficData);
    }

    private void judgeShow(int options1) {
        if (options1 == 0) {
            mDataBinding.etLicenseNumb.setText(R.string.without);
            mDataBinding.etLicenseNumb.setEnabled(false);
            mDataBinding.etTraffictoolType.setText(R.string.without);
            mDataBinding.etTraffictoolType.setEnabled(false);
            mDataBinding.ivUploadCar.setVisibility(View.GONE);
            mDataBinding.tvUpdateMsg.setVisibility(View.GONE);
            mDataBinding.btnNext.setEnabled(true);
        } else {
            mDataBinding.etLicenseNumb.setText("");
            mDataBinding.etLicenseNumb.setEnabled(true);
            mDataBinding.etTraffictoolType.setText("");
            mDataBinding.etTraffictoolType.setEnabled(true);
            mDataBinding.ivUploadCar.setVisibility(View.VISIBLE);
            mDataBinding.ivUploadCar.setImageResource(R.drawable.data_update_bg);
            mDataBinding.tvUpdateMsg.setVisibility(View.VISIBLE);
            mDataBinding.btnNext.setEnabled(false);
        }
    }

    @SuppressLint("CheckResult")
    private void initClicks() {
        RxViewUtil.clicks(mDataBinding.tvTrafficTool).subscribe(o -> trafficPickerView.show(mDataBinding.tvTrafficTool));
        RxViewUtil.clicks(mDataBinding.ivUploadCar).subscribe(o -> rxPermissions.request(PermissionGroup.PERMISSION_PHOTO)
                .subscribe(granted -> {
                    if (granted) {
                        //添加图片
                        if (disposable != null) {
                            disposable.dispose();
                        }
                        MultiImageSelector selector = MultiImageSelector.create();
                        selector.showCamera(true);
                        selector.single();
                        selector.start(AssetInformActivity.this, UPLOADREQUEST);
                    } else {
                        UiUtils.openSetting(AssetInformActivity.this, R.string.open_camera_file_permission);
                    }
                }, throwable -> {
                }));
        RxViewUtil.clicks(mDataBinding.btnNext).subscribe(o -> {
            if (transportationCode == 0) {
                Map<String, Object> request = new HashMap<>();
                request.put("sessionId", PreferenceUtils.getUserSessionid());
                request.put("transportation", transportationCode);
                showProgress();
                mPresenter.assertsinfo(request);
            } else {
                mPresenter.getQiniuToken(PreferenceUtils.getUserSessionid());
            }
        });
    }

    @Override
    public void getQiniuTokenSuc(BaseBean<String> baseBean) {
        if (DataResult.isSuccessUnToast(this, baseBean)) {
            logLastTime = System.currentTimeMillis();
            BaseApplication.getInstance().getQiNiuConfig().put(uploadUrl, "addressProof" + logLastTime, baseBean.getData(), (key, info, response) -> {
                if (info.isOK()) {
                    Log.d("qiniuuploadtest", "assetinform_upload_time=" + (System.currentTimeMillis() - logLastTime));
                    Map<String, Object> request = new HashMap<>();
                    request.put("sessionId", PreferenceUtils.getUserSessionid());
                    request.put("license", key);
                    request.put("transportation", transportationCode);
                    request.put("model", mDataBinding.etTraffictoolType.getText().toString().trim());
                    request.put("numberPlate", mDataBinding.etLicenseNumb.getText().toString().trim());
                    mPresenter.assertsinfo(request);
                } else {
                    toast(R.string.img_upload_error);
                }

            }, null);
        }
    }

    @SuppressLint("CheckResult")
    private void listenEditTextEvents() {                              //.skip(1) 这里不能设置会出现按钮不可点击
        Flowable<CharSequence> traffictoolObservable = RxTextView.textChanges(mDataBinding.tvTrafficTool).toFlowable(BackpressureStrategy.LATEST);
        Flowable<CharSequence> traffictoolTypeObservable = RxTextView.textChanges(mDataBinding.etTraffictoolType).toFlowable(BackpressureStrategy.LATEST);
        Flowable<CharSequence> licenseNumbObservable = RxTextView.textChanges(mDataBinding.etLicenseNumb).toFlowable(BackpressureStrategy.LATEST);
        Flowable.combineLatest(traffictoolObservable, traffictoolTypeObservable, licenseNumbObservable,
                (traffictool, traffictoolType, licenseNumb) -> {
                    editEnable = !TextUtils.isEmpty(traffictool.toString().trim()) && !TextUtils.isEmpty(traffictoolType.toString().trim()) && !TextUtils.isEmpty(licenseNumb.toString().trim());
                    return editEnable;
                }).subscribe(aBoolean -> judgeClickEnable(), throwable -> {

        });
    }

    private void judgeClickEnable() {
        mDataBinding.btnNext.setEnabled(editEnable && uploadEnable);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == UPLOADREQUEST && resultCode == RESULT_OK && data != null) {
            Luban.with(this)
                    .filter(path -> !TextUtils.isEmpty(path))
                    .load(data.getStringArrayListExtra(MultiImageSelector.EXTRA_RESULT).get(0))
                    .ignoreBy(100)
                    .setCompressListener(new OnCompressListener() {
                        @Override
                        public void onStart() {
                        }

                        @Override
                        public void onSuccess(File file) {
                            uploadUrl = file;
                            uploadEnable = true;
                            judgeClickEnable();
                            mDataBinding.tvUpdateMsg.setVisibility(View.GONE);
                            Glide.with(mContext).asBitmap().load(uploadUrl).centerCrop().error(R.drawable.data_update_bg).placeholder(R.drawable.data_update_bg).diskCacheStrategy(DiskCacheStrategy.ALL).into(mDataBinding.ivUploadCar);

                        }

                        @Override
                        public void onError(Throwable e) {
                            toast(R.string.pls_upload_img);
                        }
                    }).launch();


        }
    }

    @SuppressLint("CheckResult")
    @Override
    public void assertsinfoSuc(BaseBean baseBean) {
        if (DataResult.isSuccessUnToast(this, baseBean)) {
//            JumpManager.jumpToClose(this, EmergencyContactActivity.class);
            ARouter.getInstance().build(RouteConstant.EMERGENCY_CONTACT).navigation();
            finish();
        }
    }

    @Override
    public void getAssertsInfoSuc(BaseBean<AssertsInfoEntity> baseBean) {
        if (DataResult.isSuccessUnToast(this, baseBean)) {
            AssertsInfoEntity assertsInfoEntity = baseBean.getData();
            if (assertsInfoEntity != null) {
                transportationCode = assertsInfoEntity.getTransportation();
                mDataBinding.tvTrafficTool.setText(trafficData.get(transportationCode));
                judgeShow(transportationCode);
                if (transportationCode > 0 && !TextUtils.isEmpty(assertsInfoEntity.getModel()) && !TextUtils.isEmpty(assertsInfoEntity.getNumberPlate()) && !TextUtils.isEmpty(assertsInfoEntity.getLicense())) {
                    mDataBinding.etTraffictoolType.setText(assertsInfoEntity.getModel());
                    mDataBinding.etLicenseNumb.setText(assertsInfoEntity.getNumberPlate());
                    disposable = Flowable.just(assertsInfoEntity.getLicense())
                            .subscribeOn(Schedulers.io())
                            .map(url -> Glide.with(this).download(url).submit().get())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(file -> {
                                uploadUrl = file;
                                uploadEnable = true;
                                judgeClickEnable();
                                mDataBinding.tvUpdateMsg.setVisibility(View.GONE);
                                Glide.with(AssetInformActivity.this).asBitmap().load(uploadUrl).centerCrop().error(R.drawable.data_update_bg).placeholder(R.drawable.data_update_bg).diskCacheStrategy(DiskCacheStrategy.ALL).into(mDataBinding.ivUploadCar);

                            }, throwable -> {

                            });
                }
            }
        }
    }
}