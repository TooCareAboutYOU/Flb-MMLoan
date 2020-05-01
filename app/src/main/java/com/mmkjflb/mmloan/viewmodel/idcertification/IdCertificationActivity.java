package com.mmkjflb.mmloan.viewmodel.idcertification;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.graphics.Color;
import android.text.Html;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.mmkj.baselibrary.RouteConstant;
import com.mmkj.baselibrary.annotation.ActivityFragmentInject;
import com.mmkj.baselibrary.model.entitry.BaseBean;
import com.mmkj.baselibrary.model.http.DataResult;
import com.mmkj.baselibrary.util.DialogUtils;
import com.mmkj.baselibrary.util.PreferenceUtils;
import com.mmkj.baselibrary.util.RxViewUtil;
import com.mmkj.baselibrary.util.StringUtils;
import com.mmkjflb.mmloan.R;
import com.mmkjflb.mmloan.app.BaseApplication;
import com.mmkjflb.mmloan.app.Constants;
import com.mmkjflb.mmloan.app.PermissionGroup;
import com.mmkjflb.mmloan.base.BaseActivity;
import com.mmkjflb.mmloan.databinding.ActivityIdCertificationBinding;
import com.mmkjflb.mmloan.model.entity.BaseInfoEntity;
import com.mmkjflb.mmloan.model.entity.IdCardCheckEntity;
import com.mmkjflb.mmloan.utils.UiUtils;
import com.mmkjflb.mmloan.viewmodel.enploymentinformation.EnploymentinformationActivity;
import com.orhanobut.logger.Logger;
import com.syhmmfqphl.xyxlibrary.utils.JumpManager;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.jsoup.internal.StringUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import me.nereo.multi_image_selector.MultiImageSelector;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

/**
 * 身份信息
 */
@Route(path = RouteConstant.ID_CERTIFICATION)
@ActivityFragmentInject(contentViewId = R.layout.activity_id_certification, toolbarTitle = R.string.id_card_information)
public class IdCertificationActivity extends BaseActivity<IdCertificationPresenter, ActivityIdCertificationBinding> implements IdCertificationContract.View {
    private RxPermissions rxPermissions;
    private String userName = "";
    private List<File> idCertificateUploadEntityList = null;
    private IdCertificateAdapter idCertificateAdapter;
    private boolean editCheck = false;
    private boolean picEnable1 = false;
    private boolean picEnable2 = false;
    private String uploadName = "";
    private Dialog idcardTypeDialog;
    private int idCardtypeCode = 0;

    private List<String> idType;
    private RadioGroup rgIdType;
    private Disposable disposable = null;
    private String idcardHandKey = "", idcardFrontKey = "";
    private long logLastTime = 0L;

    @Override
    protected void initViews() {
        getActivityComponent().inject(this);
        rxPermissions = new RxPermissions(IdCertificationActivity.this);
        initRecyclerView();
        initClicks();
        listenEditTextEvents();
        mPresenter.getBaseInfo(PreferenceUtils.getUserSessionid());
        initCardData();


    }

    private void initCardData() {
        idType = Arrays.asList(getResources().getStringArray(R.array.id_type));
    }

    /**
     * 设置身份证类型
     */
    private void setIdCardTypeShow(int idcardtypeCode) {
        if (idcardtypeCode >= 1 && idcardtypeCode <= idType.size()) {
            idCardtypeCode = idcardtypeCode;
            mDataBinding.tvIdcardType.setText(idType.get(idCardtypeCode - 1));
        }
    }

    /**
     * radiobutton选中之后的显示处理
     *
     * @param idcardtypeCode 传入的是1、2、3、4、5、6、7
     */
    private void getIdcardTypeName(int idcardtypeCode) {
        if (idcardtypeCode != idCardtypeCode) {
            if (idCertificateUploadEntityList != null) {
                idCertificateUploadEntityList.clear();
                idCertificateUploadEntityList.add(null);
                idCertificateUploadEntityList.add(null);
                mDataBinding.etIdcard.setText("");
                mDataBinding.etSurname.setText("");
                mDataBinding.etMiddleName.setText("");
                mDataBinding.etGivenname.setText("");
                if (idCertificateAdapter != null) {
                    idCertificateAdapter.notifyDataSetChanged();
                }
            }
        }
        setIdCardTypeShow(idcardtypeCode);
    }

    /**
     * 身份证类型的弹窗
     */
    private void showIdcardTypeDialog() {
        if (null == idcardTypeDialog) {
            View view = View.inflate(this, R.layout.dialog_idcard_chose, null);
            rgIdType = view.findViewById(R.id.rg_idtype);
            idcardTypeDialog = DialogUtils.initDialog(this, view);
            view.findViewById(R.id.iv_close).setOnClickListener(o -> idcardTypeDialog.dismiss());
            //默认选中
            rgIdType.setOnCheckedChangeListener((rg, checkedId) -> {
                int selector = 1;
                switch (checkedId) {
                    case R.id.tv_idcard_type1:
                        selector = 1;
                        mDataBinding.etIdcard.setFilters(new InputFilter[]{new InputFilter.LengthFilter(50)});
                        break;
                    case R.id.tv_idcard_type2:
                        selector = 2;
                        mDataBinding.etIdcard.setFilters(new InputFilter[]{new InputFilter.LengthFilter(50)});
                        break;
                    case R.id.tv_idcard_type3:
                        selector = 3;
                        mDataBinding.etIdcard.setFilters(new InputFilter[]{new InputFilter.LengthFilter(50)});
                        break;
                    case R.id.tv_idcard_type4:
                        selector = 4;
                        mDataBinding.etIdcard.setFilters(new InputFilter[]{new InputFilter.LengthFilter(50)});
                        break;
                    case R.id.tv_idcard_type5:
                        selector = 5;
                        mDataBinding.etIdcard.setFilters(new InputFilter[]{new InputFilter.LengthFilter(50)});
                        break;
                    case R.id.tv_idcard_type6:
                        selector = 6;
                        mDataBinding.etIdcard.setFilters(new InputFilter[]{new InputFilter.LengthFilter(14)});
                        break;
                    case R.id.tv_idcard_type7:
                        selector = 7;
                        mDataBinding.etIdcard.setFilters(new InputFilter[]{new InputFilter.LengthFilter(22)});
                        break;
                    default:
                        break;
                }
                Logger.d("checkid=%s", checkedId);
                getIdcardTypeName(selector);
                idcardTypeDialog.dismiss();
            });
            if (idCardtypeCode != 0) {
                ((RadioButton) rgIdType.getChildAt(idCardtypeCode - 1)).setChecked(true);
            }
        } else {
            getIdcardTypeName(idCardtypeCode);
            idcardTypeDialog.show();
        }
    }

    private void initRecyclerView() {
        idCertificateUploadEntityList = new ArrayList<>();
        idCertificateUploadEntityList.add(null);
        idCertificateUploadEntityList.add(null);
        mDataBinding.rvIdcertificate.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        idCertificateAdapter = new IdCertificateAdapter(this, idCertificateUploadEntityList,
                position -> rxPermissions.request(PermissionGroup.PERMISSION_PHOTO)
                        .subscribe(granted -> {
                            if (granted) {
                                //添加图片
                                if (disposable != null) {
                                    disposable.dispose();
                                }
                                chosePic(position);
                            } else {
                                UiUtils.openSetting(IdCertificationActivity.this, R.string.open_camera_file_permission);
                            }
                        }, throwable -> {
                        }));
        mDataBinding.rvIdcertificate.setAdapter(idCertificateAdapter);
    }

    private void chosePic(int position) {
        if (position == 0 && TextUtils.isEmpty(mDataBinding.tvIdcardType.getText().toString())) {
            toast(R.string.pls_chose_idcard_type);
            return;
        }

        if (position == 1) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_idcard_front, null);
            builder.setView(dialogView);
            AlertDialog dialog = builder.create();
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
            dialogView.findViewById(R.id.AcTvConfirm).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    MultiImageSelector selector = MultiImageSelector.create();
                    selector.showCamera(true);
                    selector.single();
                    selector.start(IdCertificationActivity.this, position);
                }
            });
            AppCompatTextView tv = dialogView.findViewById(R.id.acTvHint);
            getHintText(tv);
            return;
        }

        MultiImageSelector selector = MultiImageSelector.create();
        selector.showCamera(true);
        selector.single();
        selector.start(IdCertificationActivity.this, position);
    }

    /**
     * 指定段落文字颜色+点击事件
     */
    private void getHintText(AppCompatTextView textView) {
        String msg1 = "Take a picture, hold the ID in your hand, place it under your face, ";
        String msg2 = "and make sure the photo and text on the ID are clear";
        String msg3 = " and show all four corners.";
        UiUtils.showBoldType(this, textView, msg1, msg2, msg3);
    }


    @Override
    public void getBaseInfoSuc(BaseBean<BaseInfoEntity> baseBean) throws ExecutionException, InterruptedException {
        if (DataResult.isSuccessUnToast(IdCertificationActivity.this, baseBean)) {
            BaseInfoEntity baseInfoEntity = baseBean.getData();
            if (!TextUtils.isEmpty(baseInfoEntity.getUserName()) && !TextUtils.isEmpty(baseInfoEntity.getIdcard())) {
                setIdCardTypeShow(baseInfoEntity.getIdcardType());

                if (baseInfoEntity.getIdcardType() == 6) {
                    mDataBinding.etIdcard.setFilters(new InputFilter[]{new InputFilter.LengthFilter(14)});
                } else if (baseInfoEntity.getIdcardType() == 7) {
                    mDataBinding.etIdcard.setFilters(new InputFilter[]{new InputFilter.LengthFilter(22)});
                } else {
                    mDataBinding.etIdcard.setFilters(new InputFilter[]{new InputFilter.LengthFilter(50)});
                }

                mDataBinding.etIdcard.setText(baseInfoEntity.getIdcard());
                userName = baseInfoEntity.getUserName();
                mDataBinding.etSurname.setText(baseInfoEntity.getSurName());
                mDataBinding.etGivenname.setText(baseInfoEntity.getGivenName());
                mDataBinding.etMiddleName.setText(baseInfoEntity.getMiddleName());
                disposable = Flowable.just(baseInfoEntity.getIdcardFront(), baseInfoEntity.getIdcardHand())
                        .subscribeOn(Schedulers.io())
                        .map(url -> Glide.with(IdCertificationActivity.this).download(url).submit().get()).toList()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(files -> {
                            idCertificateUploadEntityList.clear();
                            idCertificateUploadEntityList.addAll(files);
                            idCertificateAdapter.notifyDataSetChanged();
                            picEnable1 = true;
                            picEnable2 = true;
                            mDataBinding.btnSubmit.setEnabled(true);
                        }, throwable -> {

                        });

            }
        }
    }

    private void checkEnable() {
        if (idCardtypeCode != 0 && editCheck && picEnable1 && picEnable2) {
            mDataBinding.btnSubmit.setEnabled(true);
        } else {
            mDataBinding.btnSubmit.setEnabled(false);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && null != data) {
            switch (requestCode) {
                case 0:
                    Luban.with(this)
                            .load(data.getStringArrayListExtra(MultiImageSelector.EXTRA_RESULT).get(0))
                            .ignoreBy(100)
                            .setCompressListener(new OnCompressListener() {
                                @Override
                                public void onStart() {
                                }

                                @Override
                                public void onSuccess(File file) {
                                    picEnable1 = true;
                                    idCertificateUploadEntityList.set(0, file);
                                    idCertificateAdapter.notifyDataSetChanged();
                                    checkEnable();
                                }

                                @Override
                                public void onError(Throwable e) {
                                    toast(R.string.pls_upload_img);
                                }
                            }).launch();
                    break;
                case 1:
                    Luban.with(this)
                            .load(data.getStringArrayListExtra(MultiImageSelector.EXTRA_RESULT).get(0))
                            .ignoreBy(100)
                            .setCompressListener(new OnCompressListener() {
                                @Override
                                public void onStart() {
                                }

                                @Override
                                public void onSuccess(File file) {
                                    picEnable2 = true;
                                    idCertificateUploadEntityList.set(requestCode, file);
                                    idCertificateAdapter.notifyDataSetChanged();
                                    checkEnable();
                                }

                                @Override
                                public void onError(Throwable e) {
                                    toast(R.string.pls_upload_img);
                                }
                            }).launch();

                    break;
                default:
                    break;
            }
        } else if (resultCode == RESULT_OK && requestCode == com.mmkj.baselibrary.util.Constants.CODE_LOGINTIMEOUT) {
            mPresenter.getBaseInfo(PreferenceUtils.getUserSessionid());
        }
    }

    @Override
    public void idCardCheckSuc(BaseBean<IdCardCheckEntity> baseBean) {
        if (DataResult.isSuccessUnToast(this, baseBean)) {
            mDataBinding.etSurname.setText(baseBean.getData().getSurName());
            mDataBinding.etGivenname.setText(baseBean.getData().getGivenName());
            mDataBinding.etMiddleName.setText(baseBean.getData().getMiddleName());
            mDataBinding.etIdcard.setText(baseBean.getData().getIdcardNo());
            checkEnable();
        }
    }

    @Override
    public void onError() {
        idCertificateUploadEntityList.clear();
        idCertificateUploadEntityList.add(null);
        idCertificateUploadEntityList.add(null);
        idCertificateAdapter.notifyDataSetChanged();
    }

    @Override
    public void idcheckSuc(BaseBean baseBean) {
        if (DataResult.isSuccessUnToast(IdCertificationActivity.this, baseBean)) {
            PreferenceUtils.put(PreferenceUtils.USER_REALNAME, userName);
            PreferenceUtils.put(PreferenceUtils.USER_IDCARD, mDataBinding.etIdcard.getText().toString().trim());
//            JumpManager.jumpToClose(IdCertificationActivity.this, EnploymentinformationActivity.class);
            ARouter.getInstance().build(RouteConstant.ENPLOYMENT_INFORMATION).navigation();
            finish();
        }
    }


    /**
     * 设置edittext文字改变的监听
     */
    @SuppressLint("CheckResult")
    private void listenEditTextEvents() {                              //.skip(1) 这里不能设置会出现按钮不可点击
        Flowable<CharSequence> surnameObservable =
                RxTextView.textChanges(mDataBinding.etSurname).toFlowable(BackpressureStrategy.LATEST);
        Flowable<CharSequence> givenNameObservable =
                RxTextView.textChanges(mDataBinding.etGivenname).toFlowable(BackpressureStrategy.LATEST);
        Flowable<CharSequence> serverSecretObservable =
                RxTextView.textChanges(mDataBinding.etIdcard).toFlowable(BackpressureStrategy.LATEST);
        Flowable.combineLatest(surnameObservable, givenNameObservable, serverSecretObservable,
                (charSequence1, charSequence2, charSequence4) -> {
                    editCheck = !TextUtils.isEmpty(charSequence1.toString().trim()) && !TextUtils.isEmpty(charSequence2.toString().trim()) && !TextUtils.isEmpty(charSequence4.toString().trim());
                    return editCheck;
                }).subscribe(aBoolean -> checkEnable(), throwable -> {

        });
    }

    @SuppressLint("CheckResult")
    private void initClicks() {
        RxViewUtil.clicks(mDataBinding.tvIdcardType).subscribe(o -> showIdcardTypeDialog());
        RxViewUtil.clicks(mDataBinding.btnSubmit).subscribe(o -> {
            String idNum = mDataBinding.etIdcard.getText().toString().trim();
            if (idCardtypeCode == 4 && (!StringUtils.checkInput(idNum))) {
                toast("Invalid ID Number");
                return;
            }
            if (idCardtypeCode == 5) {
                if (idNum.length() != 11 || !StringUtils.isStartWithAlphabet(idNum) || !(idNum.substring(1).startsWith("01") || idNum.substring(1).startsWith("02") || idNum.substring(1).startsWith("03")) || !StringUtil.isNumeric(idNum.substring(1))) {
                    toast("Invalid ID Number");
                    return;
                }
            }

            if (idCertificateUploadEntityList != null && idCertificateUploadEntityList.size() == 2) {
                new AlertDialog.Builder(IdCertificationActivity.this, R.style.BDAlertDialog)
                        .setMessage(R.string.id_commit_msg)
                        .setPositiveButton(R.string.id_commit_yes, (dialog, which) -> mPresenter.getQiniuToken(PreferenceUtils.getUserSessionid()))
                        .setNegativeButton(R.string.id_commit_no, null)
                        .create().show();
            } else {
                toast(R.string.pls_upload_img);
            }
        });
    }

    @Override
    public void getQiniuTokenSuc(BaseBean<String> baseBean) {
        if (DataResult.isSuccessUnToast(IdCertificationActivity.this, baseBean)) {
            idcardFrontKey = "";
            idcardHandKey = "";
            logLastTime = System.currentTimeMillis();
            Observable.fromArray(idCertificateUploadEntityList.get(0), idCertificateUploadEntityList.get(1))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<File>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                        }

                        @Override
                        public void onNext(File file) {
                            if (file == idCertificateUploadEntityList.get(0)) {
                                BaseApplication.getInstance().getQiNiuConfig().put(file, "idcardFront" + System.currentTimeMillis(), baseBean.getData(), (key, info, response) -> {
                                    if (info.isOK()) {
                                        idcardFrontKey = key;
                                        judgeUpLoadSuc();
                                    } else {
                                        toast(R.string.img_upload_error);
                                        hideProgress();
                                    }
                                }, null);
                            } else if (file == idCertificateUploadEntityList.get(1)) {
                                BaseApplication.getInstance().getQiNiuConfig().put(file, "idcardHand" + System.currentTimeMillis(), baseBean.getData(), (key, info, response) -> {
                                    if (info.isOK()) {
                                        idcardHandKey = key;
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
                            hideProgress();
                            Log.d("qiniuuploadtest", "Throwable=" + e.toString());
                            toast(R.string.img_upload_error);


                        }

                        @Override
                        public void onComplete() {
                        }
                    });
        }
    }

    private void judgeUpLoadSuc() {
        if (!TextUtils.isEmpty(idcardFrontKey) && !TextUtils.isEmpty(idcardHandKey)) {
            Log.d("qiniuuploadtest", "idcard_upload_time=" + (System.currentTimeMillis() - logLastTime));
            upLoadAndData();
        }
    }

    /**
     * surName  对应Last Name
     * givenName字段对应First Name
     * middleName 字段对应 Middle Name
     * name字段对应 surname + "," + givenName + "," + middleName
     */
    private void upLoadAndData() {
        String surname = mDataBinding.etSurname.getText().toString();
        String givenName = mDataBinding.etGivenname.getText().toString();
        String middleName = mDataBinding.etMiddleName.getText().toString();
        userName = surname + "," + givenName + "," + middleName;
        Map<String, Object> request = new HashMap<>();
        request.put("givenName", givenName);
        request.put("idNumber", mDataBinding.etIdcard.getText().toString().trim());
        request.put("idcardFront", idcardFrontKey);
        request.put("idcardHand", idcardHandKey);
        request.put("idcardType", idCardtypeCode);
        request.put("middleName", middleName);
        request.put("name", userName);
        request.put("sessionId", PreferenceUtils.getUserSessionid());
        request.put("surName", surname);
        mPresenter.idcheck(request);

    }
}


