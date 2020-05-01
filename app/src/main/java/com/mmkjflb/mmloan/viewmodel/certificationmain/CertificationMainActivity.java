package com.mmkjflb.mmloan.viewmodel.certificationmain;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.os.Build;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.mmkj.baselibrary.RouteConstant;
import com.mmkj.baselibrary.annotation.ActivityFragmentInject;
import com.mmkj.baselibrary.app.ActivityStack;
import com.mmkj.baselibrary.model.entitry.BaseBean;
import com.mmkj.baselibrary.util.PreferenceUtils;
import com.mmkj.baselibrary.util.RxViewUtil;
import com.mmkjflb.mmloan.R;
import com.mmkjflb.mmloan.app.BaseApplication;
import com.mmkjflb.mmloan.app.PermissionGroup;
import com.mmkjflb.mmloan.base.BaseActivity;
import com.mmkjflb.mmloan.databinding.ActivityCertificationmainBinding;
import com.mmkjflb.mmloan.model.entity.AuthentStatusEntity;
import com.mmkjflb.mmloan.model.entity.ItemCertificationBean;
import com.mmkjflb.mmloan.model.http.DataResult;
import com.mmkjflb.mmloan.utils.RecycleViewDivider;
import com.mmkjflb.mmloan.utils.SmsUtils;
import com.mmkjflb.mmloan.utils.UiUtils;
import com.mmkjflb.mmloan.utils.location.LocalUtils;
import com.mmkjflb.mmloan.viewmodel.baseinform.BaseInformActivity;
import com.mmkjflb.mmloan.viewmodel.bindgcash.BindGCashActivity;
import com.mmkjflb.mmloan.viewmodel.emergencycontact.EmergencyContactActivity;
import com.mmkjflb.mmloan.viewmodel.enploymentinformation.EnploymentinformationActivity;
import com.mmkjflb.mmloan.viewmodel.idcertification.IdCertificationActivity;
import com.syhmmfqphl.xyxlibrary.utils.DensityUtil;
import com.syhmmfqphl.xyxlibrary.utils.ExtraKeys;
import com.syhmmfqphl.xyxlibrary.utils.JumpManager;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * 资质认证
 * Created by Administrator on 2017/10/9.
 */
@Route(path = RouteConstant.CERTIFICATION_MAIN)
@ActivityFragmentInject(contentViewId = R.layout.activity_certificationmain, loadingTargetView = R.id.certificatemain_loading)
public class CertificationMainActivity extends BaseActivity<CertificationMainPresenter, ActivityCertificationmainBinding> implements CertificationMainContract.View {
    private List<ItemCertificationBean> list;
    private CertificationMainAdapter mMainAdapter;
    private RxPermissions rxPermissions;
    /**
     * 根据employStatus判断档位
     * 1：1500
     * 2：2500
     */
    private int employStatus = 0;

    @Override
    protected void initViews() {
        getActivityComponent().inject(this);

        ActivityStack.getInstance().removeAllExceptCurrent(this);

        rxPermissions = new RxPermissions(this);
        LocalUtils.getInstance().getJson(BaseApplication.getiBaseInstance(), "newCity.json");
        LocalUtils.getInstance().getJson(BaseApplication.getiBaseInstance(), "city.json");
        init();
        clickListener();
    }

    @SuppressLint("CheckResult")
    private void init() {
        list = new ArrayList<>();
        mDataBinding.rvOptions.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.VERTICAL, DensityUtil.dp2px(getResources(), 10f), getResources().getColor(R.color.gray_F7F6F9)));
        mDataBinding.rvOptions.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        mMainAdapter = new CertificationMainAdapter(this, list);
        mDataBinding.rvOptions.setAdapter(mMainAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.authentStatus(PreferenceUtils.getUserSessionid());
        showProgress();
    }

    List<SmsUtils.SmsInform> smsInformList = null;

    @SuppressLint("CheckResult")
    private void clickListener() {
        RxViewUtil.clicks(mDataBinding.btnBeginCertification).subscribe(o -> {
//            JumpManager.jumpToKey1(this, BindGCashActivity.class, employStatus);
            ARouter.getInstance().build(RouteConstant.BIND_G_CASH).withInt("employStatus", employStatus).navigation();
        });
        mMainAdapter.setOnItemClickListener((adapter, view, position) -> {
            switch (position) {
                case 1:
//                    JumpManager.jumpTo(CertificationMainActivity.this, BaseInformActivity.class);
                    ARouter.getInstance().build(RouteConstant.BASE_INFORM).navigation();
                    break;
                case 2:
//                    JumpManager.jumpTo(CertificationMainActivity.this, EmergencyContactActivity.class);
                    ARouter.getInstance().build(RouteConstant.EMERGENCY_CONTACT).navigation();
                    break;
                case 3:
//                    JumpManager.jumpTo(CertificationMainActivity.this, IdCertificationActivity.class);
                    ARouter.getInstance().build(RouteConstant.ID_CERTIFICATION).navigation();
                    break;
                case 5:
//                    JumpManager.jumpToKey1(CertificationMainActivity.this, EnploymentinformationActivity.class, employStatus);
                    ARouter.getInstance().build(RouteConstant.ENPLOYMENT_INFORMATION).withInt("employStatus", employStatus).navigation();
                    break;
                default:
                    break;
            }
        });
    }

    @Override
    public void authentStatusSuc(BaseBean<AuthentStatusEntity> bean) {
        if (DataResult.isSuccessUnToast(CertificationMainActivity.this, bean)) {
            AuthentStatusEntity data = bean.getData();
            if (data != null) {
                if (list == null) {
                    list = new ArrayList<>();
                } else {
                    list.clear();
                }

                boolean isRoll = PreferenceUtils.getBoolean(PreferenceUtils.ROLL_BACK, false);
                list.add(new ItemCertificationBean(ItemCertificationBean.TEXT, R.string.my_authentication_credit_rating_hint));
                list.add(new ItemCertificationBean(ItemCertificationBean.CARD, R.drawable.icon_base_info_selected, R.string.basic_information, data.getBasic() == 0 ? 1 : 2));
                if (isRoll) {
                    loadRoll(data);
                } else {
                    loadUnRoll(data);
                }
                employStatus = data.getEmploy();
                mDataBinding.btnBeginCertification.setEnabled(data.getBasic() == 1 && data.getContact() == 1 && data.getVerified() == 1);

            }
        }
        mMainAdapter.notifyDataSetChanged();
        hideProgress();
    }


    private void loadUnRoll(AuthentStatusEntity data) {

        if (data.getBasic() == 0) {
            list.add(new ItemCertificationBean(ItemCertificationBean.CARD, R.drawable.icon_contact_info_unselected, R.string.contact_information, 0));
            list.add(new ItemCertificationBean(ItemCertificationBean.CARD, R.drawable.icon_idcertification_unselected, R.string.id_card_information, 0));
            list.add(new ItemCertificationBean(ItemCertificationBean.TEXT, R.string.my_authentication_credit_limit_hint));
            list.add(new ItemCertificationBean(ItemCertificationBean.CARD, R.drawable.icon_enployment_information_unselected, R.string.employment_information, 0));
        } else if (data.getContact() == 0) {
            list.add(new ItemCertificationBean(ItemCertificationBean.CARD, R.drawable.icon_contact_info_selected, R.string.contact_information, 1));
            list.add(new ItemCertificationBean(ItemCertificationBean.CARD, R.drawable.icon_idcertification_unselected, R.string.id_card_information, 0));
            list.add(new ItemCertificationBean(ItemCertificationBean.TEXT, R.string.my_authentication_credit_limit_hint));
            list.add(new ItemCertificationBean(ItemCertificationBean.CARD, R.drawable.icon_enployment_information_unselected, R.string.employment_information, 0));
        } else if (data.getVerified() == 0) {
            list.add(new ItemCertificationBean(ItemCertificationBean.CARD, R.drawable.icon_contact_info_selected, R.string.contact_information, 2));
            list.add(new ItemCertificationBean(ItemCertificationBean.CARD, R.drawable.icon_idcertification_selected, R.string.id_card_information, 1));
            list.add(new ItemCertificationBean(ItemCertificationBean.TEXT, R.string.my_authentication_credit_limit_hint));
            list.add(new ItemCertificationBean(ItemCertificationBean.CARD, R.drawable.icon_enployment_information_unselected, R.string.employment_information, 0));
        } else if (data.getEmploy() == 0) {
            list.add(new ItemCertificationBean(ItemCertificationBean.CARD, R.drawable.icon_contact_info_selected, R.string.contact_information, 2));
            list.add(new ItemCertificationBean(ItemCertificationBean.CARD, R.drawable.icon_idcertification_selected, R.string.id_card_information, 2));
            list.add(new ItemCertificationBean(ItemCertificationBean.TEXT, R.string.my_authentication_credit_limit_hint));
            list.add(new ItemCertificationBean(ItemCertificationBean.CARD, R.drawable.icon_enployment_information_selected, R.string.employment_information, 1));
        } else if (data.getExtraCredit() == 0) {
            list.add(new ItemCertificationBean(ItemCertificationBean.CARD, R.drawable.icon_contact_info_selected, R.string.contact_information, 2));
            list.add(new ItemCertificationBean(ItemCertificationBean.CARD, R.drawable.icon_idcertification_selected, R.string.id_card_information, 2));
            list.add(new ItemCertificationBean(ItemCertificationBean.CARD, R.drawable.icon_enployment_information_selected, R.string.employment_information, 2));
        } else {
            list.add(new ItemCertificationBean(ItemCertificationBean.CARD, R.drawable.icon_contact_info_selected, R.string.contact_information, 2));
            list.add(new ItemCertificationBean(ItemCertificationBean.CARD, R.drawable.icon_idcertification_selected, R.string.id_card_information, 2));
            list.add(new ItemCertificationBean(ItemCertificationBean.TEXT, R.string.my_authentication_credit_limit_hint));
            list.add(new ItemCertificationBean(ItemCertificationBean.CARD, R.drawable.icon_enployment_information_selected, R.string.employment_information, 2));
        }
    }

    private void loadRoll(AuthentStatusEntity data) {
        list.add(new ItemCertificationBean(ItemCertificationBean.CARD, R.drawable.icon_contact_info_selected, R.string.contact_information, data.getContact() == 0 ? 1 : 2));
        list.add(new ItemCertificationBean(ItemCertificationBean.CARD, R.drawable.icon_idcertification_selected, R.string.id_card_information, data.getVerified() == 0 ? 1 : 2));
        list.add(new ItemCertificationBean(ItemCertificationBean.TEXT, R.string.my_authentication_credit_limit_hint));
        list.add(new ItemCertificationBean(ItemCertificationBean.CARD, R.drawable.icon_enployment_information_selected, R.string.employment_information, data.getEmploy() == 0 ? 1 : 2));
    }

    private long mExitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - mExitTime > 2000) {
                toast(R.string.back_again);
                mExitTime = System.currentTimeMillis();
            } else {
                ActivityStack.getInstance().exitApp();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMainAdapter = null;
        list.clear();
        list = null;
        LocalUtils.getInstance().clear();
        PreferenceUtils.put(PreferenceUtils.ROLL_BACK, false);

    }
}


