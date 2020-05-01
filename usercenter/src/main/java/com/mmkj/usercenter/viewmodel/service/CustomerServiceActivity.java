package com.mmkj.usercenter.viewmodel.service;

import android.Manifest;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Html;
import android.text.TextUtils;

import com.afollestad.materialdialogs.MaterialDialog;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mmkj.baselibrary.RouteConstant;
import com.mmkj.baselibrary.annotation.HeadToolbar;
import com.mmkj.baselibrary.annotation.HeaderBuilder;
import com.mmkj.baselibrary.model.entitry.BaseBean;
import com.mmkj.baselibrary.model.http.DataResult;
import com.mmkj.baselibrary.util.PermissionPageUtils;
import com.mmkj.usercenter.R;
import com.mmkj.usercenter.databinding.ActivityCustomerServiceBinding;
import com.mmkj.usercenter.model.entity.CustomerServiceData;
import com.mmkj.usercenter.viewmodel.BaseUserSettingActivity;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.List;

@Route(path = RouteConstant.CUSTOMER_SERVICE)
public class CustomerServiceActivity extends BaseUserSettingActivity<CustomerServicePresenter, ActivityCustomerServiceBinding> implements CustomerServiceContract.View {

    private RxPermissions rxPermissions;
    private List<CustomerServiceData> mServiceDataList;
    private ServicesAdapter mServicesAdapter;
    private ClipboardManager clipboardManager;

    @Override
    protected HeadToolbar getHeadToolbar() {
        return new HeaderBuilder().mContentViewId(R.layout.activity_customer_service).toolbarTitle(R.string.customer_service).build();
    }

    @Override
    protected void initViews() {
        getActivityComponent().inject(this);
        rxPermissions = new RxPermissions(this);

        clipboardManager= (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);

        mServiceDataList = new ArrayList<>();
        mServicesAdapter = new ServicesAdapter(R.layout.item_services,mServiceDataList);

        mDataBinding.rvServices.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        mDataBinding.rvServices.setAdapter(mServicesAdapter);
        mServicesAdapter.setOnItemClickListener((adapter, view, position) -> rxPermissions.request(Manifest.permission.CALL_PHONE).subscribe(aBoolean -> {

            if (aBoolean) {
                switch (position) {
                    case 0:{
                        AlertDialog.Builder alert=new AlertDialog.Builder(CustomerServiceActivity.this);
                        alert.setMessage(mServiceDataList.get(position).getItemValue());
                        alert.setCancelable(false);
                        alert.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());
                        alert.setPositiveButton(Html.fromHtml("<font color='#2C89FF'>Dial</font>"), (dialog, which) -> {
                            if (!TextUtils.isEmpty(mServiceDataList.get(position).getItemValue())) {
                                callPhone(mServiceDataList.get(position).getItemValue());
                                dialog.dismiss();
                            }
                        });
                        alert.show();
                        break;
                    }

                    case 1:{
                        ClipData clipData=ClipData.newPlainText(null,mServiceDataList.get(position).getItemValue());
                        clipboardManager.setPrimaryClip(clipData);
                        if (clipboardManager.hasPrimaryClip()) {
                            toast("Text Copied,Please Contact Us in Facebook");
                        }
                        break;
                    }
                }
            }else {
                openSetting(CustomerServiceActivity.this, R.string.open_camera_file_permission);
            }
        }));

    }

    @Override
    public void getNetData() {
        mPresenter.getServiceInfo();
    }

    @Override
    public void getServiceInfoSuc(BaseBean<List<CustomerServiceData>> data) {
        if (DataResult.isSuccessUnToast(this, data)) {
            if (mServiceDataList == null) {
                mServiceDataList = new ArrayList<>();
            }
            mServiceDataList.clear();
            if (data.getData() != null && data.getData().size() > 0) {
                mServiceDataList.addAll(data.getData());
            }
            mServicesAdapter.notifyDataSetChanged();
        }
    }

    public class ServicesAdapter extends BaseQuickAdapter<CustomerServiceData,BaseViewHolder>{

        public ServicesAdapter(int layoutResId, @Nullable List<CustomerServiceData> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, CustomerServiceData item) {
            if (!TextUtils.isEmpty(item.getItemName()) && !TextUtils.isEmpty(item.getItemValue())) {
                helper.setText(R.id.acTv_title,item.getItemName()).setText(R.id.acTv_msg,item.getItemValue());
            }

        }
    }


    private void callPhone(String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        startActivity(intent);
    }

    /**
     * 设置权限
     */
    public static void openSetting(final Context context, int contentRes) {
        new MaterialDialog.Builder(context)
                .title("Reminder")
                .content(contentRes)
                .positiveText("Sure")
                .onPositive((dialog, which) -> {
                    dialog.dismiss();
                    new PermissionPageUtils(context).jumpPermissionPage();
                })
                .negativeText("Cancel")
                .onNegative((dialog, which) ->dialog.dismiss())
                .show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mServiceDataList != null) {
            mServiceDataList.clear();
            mServiceDataList=null;
        }
        if (mServicesAdapter != null) {
            mServicesAdapter =null;
        }
    }
}
