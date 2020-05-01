package com.mmkjflb.mmloan.viewmodel.enploymentinformation;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.TextUtils;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bigkoo.pickerview.OptionsPickerView;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;
import com.hwangjr.rxbus.thread.EventThread;
import com.mmkj.baselibrary.RouteConstant;
import com.mmkj.baselibrary.annotation.ActivityFragmentInject;
import com.mmkj.baselibrary.model.entitry.BaseBean;
import com.mmkj.baselibrary.util.EventConstants;
import com.mmkj.baselibrary.util.PreferenceUtils;
import com.mmkj.baselibrary.util.RxBus;
import com.mmkj.baselibrary.util.RxViewUtil;
import com.mmkjflb.mmloan.R;
import com.mmkjflb.mmloan.app.Constants;
import com.mmkjflb.mmloan.base.BaseActivity;
import com.mmkjflb.mmloan.base.BaseFragment;
import com.mmkjflb.mmloan.databinding.ActivityEmploymentInformationBinding;
import com.mmkjflb.mmloan.model.entity.AuthentStatusEntity;
import com.mmkjflb.mmloan.model.entity.ClickEvent;
import com.mmkjflb.mmloan.model.http.DataResult;
import com.mmkjflb.mmloan.viewmodel.bindgcash.BindGCashActivity;
import com.mmkjflb.mmloan.viewmodel.certificationmain.CertificationMainActivity;
import com.mmkjflb.mmloan.viewmodel.fragment.freelance.FreelanceFragment;
import com.mmkjflb.mmloan.viewmodel.fragment.payroll.PayrollFragment;
import com.mmkjflb.mmloan.viewmodel.fragment.privateowner.PrivateOwnerFragment;
import com.mmkjflb.mmloan.viewmodel.fragment.student.StudentsFragment;
import com.syhmmfqphl.xyxlibrary.utils.ExtraKeys;
import com.syhmmfqphl.xyxlibrary.utils.JumpManager;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import me.nereo.multi_image_selector.MultiImageSelector;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

/**
 * 就业信息
 */
@Route(path = RouteConstant.ENPLOYMENT_INFORMATION)
@ActivityFragmentInject(contentViewId = R.layout.activity_employment_information, toolbarTitle = R.string.employment_information)
public class EnploymentinformationActivity extends BaseActivity<EnploymentinformationPresenter, ActivityEmploymentInformationBinding> implements EnploymentInfoContract.View {
    private Fragment currentFragment = null;
    private List<String> workNatureArray;
    private OptionsPickerView workNature;
    private PayrollFragment payrollFragment;
    private FreelanceFragment freelanceFragment;
    private PrivateOwnerFragment privateOwnerFragment;
    private StudentsFragment studentsFragment;
    private int performance = 0;
    //前台
    private File reception = null;
    private RxPermissions mRxPermissions;

    @Autowired
    public int employStatus = 0;

    @Override
    protected void initViews() {
        getActivityComponent().inject(this);
        mRxPermissions = new RxPermissions(this);
//        employStatus = getIntent().getIntExtra(ExtraKeys.Key_Msg1, 0);
        workNatureArray = Arrays.asList(getResources().getStringArray(R.array.work_nature_array));
        initPickerView();
        initClick();
    }

    @SuppressLint("CheckResult")
    public void initClick() {
        //选择就业类型
        RxViewUtil.clicks(mDataBinding.llSelectPosition).subscribe(o -> workNature.show(mDataBinding.llSelectPosition));
        RxViewUtil.clicks(mDataBinding.acBtnJump).subscribe(o -> mPresenter.authentStatus(PreferenceUtils.getUserSessionid()));
        RxViewUtil.clicks(mDataBinding.btnAuthenticationSubmit).subscribe(o -> RxBus.getDefault().post(new ClickEvent(performance)));
        com.hwangjr.rxbus.RxBus.get().register(this);
    }

    @Subscribe(thread = EventThread.MAIN_THREAD, tags = {@Tag(EventConstants.INFORM_BTN_CHANGE)})
    public void updateBtnChange(Integer isupdate) {
        if (isupdate == 0) {
            mDataBinding.btnAuthenticationSubmit.setEnabled(false);
        } else if (isupdate == 1) {
            mDataBinding.btnAuthenticationSubmit.setEnabled(true);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        com.hwangjr.rxbus.RxBus.get().unregister(this);
    }

    private void initPickerView() {
        payrollFragment = new PayrollFragment();
        replaceView(payrollFragment);
    }

    /**
     * 底部显示与隐藏
     */
    private void replaceView(BaseFragment fragment) {
        if (currentFragment != fragment) {
            FragmentTransaction transaction = this.getSupportFragmentManager().beginTransaction();
            if (currentFragment != null) {
                // 隐藏当前的fragment，add下一个到Activity中
                transaction.remove(currentFragment).add(R.id.fl_employment_information_content, fragment).commit();
            } else {
                // add下一个到Activity中
                transaction.add(R.id.fl_employment_information_content, fragment).commit();
            }
            currentFragment = fragment;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            switch (requestCode) {
                case Constants.CAREERCODE:
                    try {
                        payrollFragment.onActivityResult(requestCode, resultCode, data);
                    }catch (NullPointerException e){
                        e.printStackTrace();
                    }
                    break;
                case 0:
                    Luban.with(this)
                            .filter(path -> !TextUtils.isEmpty(path))
                            .load(data.getStringArrayListExtra(MultiImageSelector.EXTRA_RESULT).get(0))
                            .ignoreBy(100)
                            .setCompressListener(new OnCompressListener() {
                                @Override
                                public void onStart() { }

                                @Override
                                public void onSuccess(File file) {
                                    reception = file;
                                    RxBus.getDefault().post(new ClickEvent(1, reception));
                                    Constants.PAYROLLSELECTIMAGE = true;
                                    if (Constants.PAYROLLETLISTENONE && Constants.PAYROLLETLISTENTWO && Constants.PAY_DATE_STATES && Constants.PAYROLLSELECTIMAGE && Constants.PRIVATEOWNERSELECTIMAGE && Constants.STUDENTSELECTIMAGE) {
                                        mDataBinding.btnAuthenticationSubmit.setEnabled(true);
                                    }
                                }

                                @Override
                                public void onError(Throwable e) {
                                    toast(R.string.pls_upload_img);
                                }
                            }).launch();
                    break;
                case 1:
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
                                    reception = file;
                                    RxBus.getDefault().post(new ClickEvent(2, reception));
                                    Constants.PRIVATEOWNERSELECTIMAGE = true;
                                    if (Constants.PAYROLLETLISTENONE && Constants.PAYROLLETLISTENTWO && Constants.PAY_DATE_STATES && Constants.PAYROLLSELECTIMAGE && Constants.PRIVATEOWNERSELECTIMAGE && Constants.STUDENTSELECTIMAGE) {
                                        mDataBinding.btnAuthenticationSubmit.setEnabled(true);
                                    }
                                    if (Constants.SUBMIT_STATUS) {
                                        if (Constants.PRIVATEOWNERETLISTENONE && Constants.PRIVATEOWNERSELECTIMAGE) {
                                            mDataBinding.btnAuthenticationSubmit.setEnabled(true);
                                        }
                                    }
                                }

                                @Override
                                public void onError(Throwable e) {
                                    toast(R.string.pls_upload_img);
                                }
                            }).launch();


                    break;
                case 2:
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
                                    reception = file;
                                    RxBus.getDefault().post(new ClickEvent(3, reception));
                                    Constants.STUDENTSELECTIMAGE = true;
                                    if (Constants.PAYROLLETLISTENONE && Constants.PAYROLLETLISTENTWO && Constants.PAY_DATE_STATES && Constants.PAYROLLSELECTIMAGE && Constants.PRIVATEOWNERSELECTIMAGE && Constants.STUDENTSELECTIMAGE) {
                                        mDataBinding.btnAuthenticationSubmit.setEnabled(true);
                                    }
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
        }
    }

    @Override
    public void authentStatusSuc(BaseBean<AuthentStatusEntity> bean) {
        if (DataResult.isSuccessUnToast(this, bean)) {
            AuthentStatusEntity data = bean.getData();
            if (data.getBasic() == 1 && data.getContact() == 1 && data.getVerified() == 1) {
//                JumpManager.jumpToKey1Close(EnploymentinformationActivity.this, BindGCashActivity.class, employStatus);
                ARouter.getInstance().build(RouteConstant.BIND_G_CASH).withInt("employStatus",employStatus).navigation();finish();
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(this)
                        .setMessage("Please complete the required fields before you can go to the certification.")
                        .setCancelable(false)
                        .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        });
                builder.create().show();
            }
        }
    }
}

