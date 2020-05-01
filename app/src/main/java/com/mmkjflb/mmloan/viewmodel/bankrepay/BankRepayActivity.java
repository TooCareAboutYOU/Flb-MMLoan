package com.mmkjflb.mmloan.viewmodel.bankrepay;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.mmkj.baselibrary.RouteConstant;
import com.mmkj.baselibrary.annotation.ActivityFragmentInject;
import com.mmkj.baselibrary.app.ActivityStack;
import com.mmkj.baselibrary.model.entitry.BaseBean;
import com.mmkj.baselibrary.util.StringUtils;
import com.mmkjflb.mmloan.BuildConfig;
import com.mmkjflb.mmloan.viewmodel.repay.RepayActivity;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCancellationSignal;
import com.qiniu.android.storage.UpProgressHandler;
import com.qiniu.android.storage.UploadOptions;
import com.syhmmfqphl.xyxlibrary.utils.ExtraKeys;
import com.mmkj.baselibrary.util.PreferenceUtils;
import com.mmkj.baselibrary.util.RxViewUtil;
import com.mmkjflb.mmloan.R;
import com.mmkjflb.mmloan.app.BaseApplication;
import com.mmkjflb.mmloan.app.PermissionGroup;
import com.mmkjflb.mmloan.base.BaseActivity;
import com.mmkjflb.mmloan.databinding.ActivityBankRepayBinding;
import com.mmkjflb.mmloan.model.entity.CertificateDetailEntity;
import com.mmkjflb.mmloan.model.entity.CollectionAccountEntity;
import com.mmkjflb.mmloan.model.http.DataResult;
import com.mmkjflb.mmloan.utils.UiUtils;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.Nullable;
import id.zelory.compressor.Compressor;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import me.nereo.multi_image_selector.MultiImageSelector;
import top.zibin.luban.CompressionPredicate;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

/**
 * date: 2018/3/1 17:36
 * author: xuyexiang
 * title:
 */
@Route(path = RouteConstant.BANK_REPAY)
@ActivityFragmentInject(contentViewId = R.layout.activity_bank_repay)
public class BankRepayActivity extends BaseActivity<BankRepayPresenter, ActivityBankRepayBinding> implements BankRepayContract.View {

    private RxPermissions rxPermissions;
    private int IMG_CALLBACK_CODE = 0;
    private int REPAY_MODE = 0;//0未提交过转账记录，1已上传转账记录（只读状态），2上传还款记录（编辑状态）
    private boolean uploadEnable = false;
    private boolean editEnable = false;

    @Autowired
    public int bidId;
    @Autowired
    public int billId;
    @Autowired
    public int repayType;

    private long logLastTime = 0L;

    @Override
    protected void initViews() {
        getActivityComponent().inject(this);
        if (repayType == 1) {
            ActivityStack.getInstance().removeActivityByClass(RepayActivity.class);
        }

        mPresenter.certificateDetail(PreferenceUtils.getUserSessionid(), bidId, billId);
        mPresenter.collectionAccount(PreferenceUtils.getUserSessionid());
        rxPermissions = new RxPermissions(this);
        initClicks();
        listenEditTextEvents();
    }

    private Disposable disposable;

    @SuppressLint("CheckResult")
    private void initClicks() {
        RxViewUtil.clicks(mDataBinding.tvLookExample).subscribe(o -> {
            showExampleDialog();
        });
        RxViewUtil.clicks(mDataBinding.ivBack).subscribe(o -> finish());
        //图片
        RxViewUtil.clicks(mDataBinding.ivVoucher).subscribe(o -> rxPermissions.request(PermissionGroup.PERMISSION_PHOTO)
                .subscribe(granted -> {
                    if (granted) {
                        //添加图片
                        if (disposable != null) {
                            disposable.dispose();
                        }
                        MultiImageSelector selector = MultiImageSelector.create();
                        selector.showCamera(true);
                        selector.single();
                        selector.start(this, IMG_CALLBACK_CODE);
                    } else {
                        UiUtils.openSetting(this, R.string.open_camera_file_permission);
                    }
                }, throwable -> {
                }));
        RxViewUtil.clicks(mDataBinding.btnUpload).subscribe(o -> {
            if (REPAY_MODE == 0) {
                mPresenter.getQiniuToken(PreferenceUtils.getUserSessionid());
            } else if (REPAY_MODE == 1) {
                //变更为编辑模式
                REPAY_MODE = 2;
                notifyRepayMode();
            }
        });
        RxViewUtil.clicks(mDataBinding.btnCancel).subscribe(o -> {
            //点击取消，变成只读状态
            REPAY_MODE = 1;
            notifyRepayMode();
        });
        RxViewUtil.clicks(mDataBinding.btnConfirm).subscribe(o -> {
            //调用接口修改转账记录
            if (!TextUtils.isEmpty(mPath)) {
                mPresenter.getQiniuToken(PreferenceUtils.getUserSessionid());
            } else if (!TextUtils.isEmpty(backFillPath) && !TextUtils.isEmpty(callKey)) {
                upLoad(callKey);
            }
        });
    }


    private String localKey;
    private String callKey;

    @SuppressLint("CheckResult")
    @Override
    public void getQiniuTokenSuc(BaseBean<String> baseBean) {
        if (DataResult.isSuccessUnToast(this, baseBean)) {
            logLastTime = System.currentTimeMillis();
            localKey = "transferCertificate" + logLastTime;

            new Compressor(this)
                    .compressToFileAsFlowable(new File(mPath))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<File>() {
                        @Override
                        public void accept(File file) {
                            BaseApplication.getInstance().getQiNiuConfig().put(file, localKey, baseBean.getData(), (key, info, response) -> {
                                if (info.isOK()) {
                                    upLoad(key);
                                } else {
                                    toast(R.string.img_upload_error);
                                    hideProgress();
                                }
                            }, new UploadOptions(null, null, false, new UpProgressHandler() {
                                @Override
                                public void progress(String key, double percent) {
                                    if (BuildConfig.DEBUG) {
                                        Log.i("qiniuuploadtest", "当前进度：" + percent);
                                    }
                                }
                            }, null));
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) {
                            throwable.printStackTrace();
                        }
                    });
        }
    }

    private void upLoad(String key) {
        Map<String, Object> map = new HashMap<>();
        map.put("sessionId", PreferenceUtils.getUserSessionid());
        map.put("bidId", bidId);
        map.put("billId", billId);
        map.put("collectionAccountNumber", mDataBinding.tvBankNumberValue.getText().toString());
        map.put("collectionBank", mDataBinding.tvBankNameValue.getText().toString());
        map.put("owner", mDataBinding.tvUserNameValue.getText().toString());
        map.put("payoutBank", mDataBinding.etBankName.getText().toString());
        map.put("payoutBankName", mDataBinding.etBranchName.getText().toString());
        map.put("transferCertificate", key);
        mPresenter.certificateUpload(map);
    }

    /**
     * 更新底部按钮
     */
    private void notifyRepayMode() {
        switch (REPAY_MODE) {
            case 0://0未提交过转账记录
                if (editEnable && uploadEnable) {
                    mDataBinding.btnUpload.setEnabled(true);
                } else {
                    mDataBinding.btnUpload.setEnabled(false);
                }
                mDataBinding.btnUpload.setVisibility(View.VISIBLE);
                mDataBinding.llGroupBottom.setVisibility(View.GONE);
                mDataBinding.btnUpload.setText(getResources().getString(R.string.bank_repay_upload));
                mDataBinding.etBranchName.setEnabled(true);
                mDataBinding.etBankName.setEnabled(true);
                mDataBinding.ivVoucher.setEnabled(true);
                break;
            case 1://已上传转账记录（只读状态）
                if (editEnable && uploadEnable) {
                    mDataBinding.btnUpload.setEnabled(true);
                } else {
                    mDataBinding.btnUpload.setEnabled(false);
                }
                mDataBinding.btnUpload.setVisibility(View.VISIBLE);
                mDataBinding.llGroupBottom.setVisibility(View.GONE);
                mDataBinding.btnUpload.setText(getResources().getString(R.string.bank_repay_modify));
                mDataBinding.btnUpload.setEnabled(true);
                mDataBinding.etBranchName.setEnabled(false);
                mDataBinding.etBankName.setEnabled(false);
                mDataBinding.ivVoucher.setEnabled(false);
//                mDataBinding.ivVoucher.setImageDrawable(getResources().getDrawable(R.drawable.bank_repay_upload));
                break;
            case 2://上传还款记录（编辑状态）
                mDataBinding.btnUpload.setVisibility(View.GONE);
                mDataBinding.llGroupBottom.setVisibility(View.VISIBLE);
                mDataBinding.etBranchName.setEnabled(true);
                mDataBinding.etBankName.setEnabled(true);
                mDataBinding.ivVoucher.setEnabled(true);
                break;
            default:
                break;
        }
    }

    @SuppressLint("CheckResult")
    private void listenEditTextEvents() {
        Flowable<CharSequence> bankNameObservable =
                RxTextView.textChanges(mDataBinding.etBankName).toFlowable(BackpressureStrategy.LATEST);
        Flowable<CharSequence> branchNameObservable =
                RxTextView.textChanges(mDataBinding.etBranchName).toFlowable(BackpressureStrategy.LATEST);
        Flowable.combineLatest(bankNameObservable, branchNameObservable,
                (bankName, branchName) -> {
                    editEnable = !TextUtils.isEmpty(bankName.toString().trim()) && !TextUtils.isEmpty(branchName.toString().trim());
                    return editEnable;
                }).subscribe(aBoolean ->
                        notifyRepayMode()
                , throwable -> {
                });
    }

    /**
     * 示例弹窗
     */
    private void showExampleDialog() {
        Dialog dialogDetail = new Dialog(this, R.style.DialogCommon);
        View view = View.inflate(this, R.layout.dialog_example, null);
        view.findViewById(R.id.iv_repaydetail_dismiss).setOnClickListener(v -> dialogDetail.dismiss());
        dialogDetail.setContentView(view);
        dialogDetail.setCanceledOnTouchOutside(true);
        Window window = dialogDetail.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER); // 此处可以设置dialog显示的位置
        window.setWindowAnimations(R.style.ZoomInZoomOutAnimation); // 添加动画
        dialogDetail.show();
    }

    private String mPath, backFillPath;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (RESULT_OK == resultCode && data != null) {
            final String path = data.getStringArrayListExtra(MultiImageSelector.EXTRA_RESULT).get(0);
            Glide.with(BaseApplication.getInstance())
                    .load(path)
                    .centerCrop()
                    .placeholder(R.drawable.data_update_bg)
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            mPath = path;
                            uploadEnable = true;
                            notifyRepayMode();
                            return false;
                        }
                    }).into(mDataBinding.ivVoucher);
        }
    }

    @Override
    public void certificateDetailSuc(BaseBean<CertificateDetailEntity> baseBean) {
        if (DataResult.isSuccessUnToast(this, baseBean)) {
            CertificateDetailEntity bean = baseBean.getData();
            if (!TextUtils.isEmpty(bean.getPayoutBank())) {
                mDataBinding.etBankName.setText(bean.getPayoutBank());
            }
            if (!TextUtils.isEmpty(bean.getPayoutBankName())) {
                mDataBinding.etBranchName.setText(bean.getPayoutBankName());
            }
            if (!TextUtils.isEmpty(bean.getTransferCertificate())) {
                disposable = Flowable.just(bean.getTransferCertificate())
                        .subscribeOn(Schedulers.io())
                        .map(url -> Glide.with(this).download(url).submit().get())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(file -> {
                            backFillPath = bean.getTransferCertificate();
                            callKey = StringUtils.subStringToEndExincludeStart(backFillPath, "/");
                            if (BuildConfig.DEBUG) {
                                Log.i("MyPrint", "图片地址: " + bean.getTransferCertificate() + "\nkey=" + callKey);
                            }
                            Glide.with(BaseApplication.getInstance()).load(file).placeholder(R.drawable.data_update_bg).into(mDataBinding.ivVoucher);
                            REPAY_MODE = 1;
                            notifyRepayMode();
                        }, throwable -> {
                        });
            }
        }
    }

    @Override
    public void collectionAccountSuc(BaseBean<CollectionAccountEntity> baseBean) {
        if (DataResult.isSuccessUnToast(this, baseBean)) {
            mDataBinding.tvBankNameValue.setText(baseBean.getData().getCollectionBank());
            mDataBinding.tvBankNumberValue.setText(baseBean.getData().getCollectionAccountNumber());
            mDataBinding.tvUserNameValue.setText(baseBean.getData().getOwner());
        }
    }


    @Override
    public void certificateUploadSuc(BaseBean baseBean) {
        if (DataResult.isSuccessUnToast(this, baseBean)) {
            REPAY_MODE = 1;
            notifyRepayMode();
        }
    }
}
