package com.mmkjflb.mmloan.viewmodel.login;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.jakewharton.rxbinding2.widget.RxCompoundButton;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.mmkj.baselibrary.RouteConstant;
import com.mmkj.baselibrary.annotation.ActivityFragmentInject;
import com.mmkj.baselibrary.app.ActivityStack;
import com.mmkj.baselibrary.model.entitry.BaseBean;
import com.mmkj.baselibrary.util.DeviceUtils;
import com.mmkj.baselibrary.util.PreferenceUtils;
import com.mmkj.baselibrary.util.RSAHelper;
import com.mmkj.baselibrary.util.RxViewUtil;
import com.mmkjflb.mmloan.BuildConfig;
import com.mmkjflb.mmloan.R;
import com.mmkjflb.mmloan.app.Constants;
import com.mmkjflb.mmloan.app.PermissionGroup;
import com.mmkjflb.mmloan.base.BaseActivity;
import com.mmkjflb.mmloan.databinding.ActivityLoginBinding;
import com.mmkjflb.mmloan.model.entity.LoginBean;
import com.mmkjflb.mmloan.model.entity.UserBean;
import com.mmkjflb.mmloan.model.http.DataResult;
import com.mmkjflb.mmloan.utils.FirebaseUtils;
import com.mmkjflb.mmloan.utils.HttpGpsLocation;
import com.mmkjflb.mmloan.utils.UiUtils;
import com.mmkjflb.mmloan.viewmodel.register.RegisterActivity;
import com.syhmmfqphl.xyxlibrary.utils.ExtraKeys;
import com.syhmmfqphl.xyxlibrary.utils.JumpManager;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.IOException;
import java.util.EventListener;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PermissionChecker;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;


/**
 * （注释添加完成）
 */
@Route(path = RouteConstant.LOGIN)
@ActivityFragmentInject(contentViewId = R.layout.activity_login, statusBarColor = R.color.white)
public class LoginActivity extends BaseActivity<LoginPresenter, ActivityLoginBinding> implements LoginContract.View {
    private static final int REGISTERCODE = 1;
    private String account = "";

    //    private RxPermissions rxPermissions;
    private LocationManager locManager;
    private String accessPosition = "";
    private String address = null;
//    private double positionLatitude;//维度
//    private double positionLongitude;//经度


    private static final String TAG = "MLoginActivity";

    @Autowired
    public boolean isTimeOut;

    @Override
    protected void initViews() {
        this.getActivityComponent().inject(this);

//        isTimeOut = getIntent().getBooleanExtra(ExtraKeys.Key_Msg1, false);

        this.account = PreferenceUtils.getString(PreferenceUtils.USER_PHONE, "");
        this.loginAccountShow(this.account, "");
        this.listenEditTextEvents();

        //为TextView设置不同的字体大小和颜色
        UiUtils.showBoldType(this, this.mDataBinding.btnRegister, Constants.LOGIN_BOLD_STR1, Constants.LOGIN_BOLD_STR2);
//        rxPermissions = new RxPermissions(this);

        if (BuildConfig.DEBUG) {
//            this.mDataBinding.etLoginAccount.setText("09665550000");
//            this.mDataBinding.etLoginPassword.setText("lxx123456");
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        this.initClicks();
    }

    @SuppressLint("CheckResult")
    private void initClicks() {
        this.locManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        RxViewUtil.clicks(this.mDataBinding.btnLogin).subscribe(o -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, 100);
//                } else {
//                    Log.i("onRequest", "权限通过 " + Manifest.permission.READ_PHONE_STATE);
//                }
//                if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
//                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 101);
//                } else {
//                    Log.i("onRequest", "权限通过 " + Manifest.permission.ACCESS_FINE_LOCATION);
//                }
            } else {
                agreePermissonNext();
            }

//            rxPermissions.request(PermissionGroup.PERMISSION_LOGIN).subscribe(aBoolean -> {
//                if (aBoolean) {
////                    agreePermissonNext();
//                    Toast.makeText(this, "通过", Toast.LENGTH_SHORT).show();
//                } else {
//                    UiUtils.openSetting(this, R.string.open_login_permission);
//                }
//            }, throwable -> {
//                Log.i("PermissionGroup", "throwable" + throwable);
//            });

        });
        RxViewUtil.clicks(this.mDataBinding.btnLoginForgetpwd).subscribe(o -> {
//            JumpManager.jumpTo(this, ForgetPwdActivity.class);
            ARouter.getInstance().build(RouteConstant.FORGET_PASSWORD).navigation();
        });
        RxViewUtil.clicks(this.mDataBinding.btnRegister).subscribe(o -> {
//            startActivityForResult(new Intent(this, RegisterActivity.class), REGISTERCODE);
            ARouter.getInstance().build(RouteConstant.REGISTER).navigation(this, LoginActivity.REGISTERCODE);
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {
            if (grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                Log.e("onRequest", "权限未通过1 " + permissions[0]);
                UiUtils.openSetting(this, R.string.open_login_permission);
            } else {
                Log.i("onRequest", "权限通过 1");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 101);
            }

        } else if (requestCode == 101) {
            if (grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                Log.e("onRequest", "权限未通过2 " + permissions[0]);
                UiUtils.openSetting(this, R.string.open_login_permission);
            } else {
                Log.i("onRequest", "权限通过 2");
                agreePermissonNext();
            }
        }
    }

    private void agreePermissonNext() {
        showProgress();
        if (!this.locManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            this.goLogin();
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (this.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && this.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            }
        }
        this.locManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this.locListener);
    }

    private final LocationListener locListener = new LocationListener() {
        public void onLocationChanged(final Location loc) {
            if (loc != null) {
                getLocationAddress(loc);
//                LoginActivity.this.positionLatitude = loc.getLatitude();
//                LoginActivity.this.positionLongitude = loc.getLongitude();
            }
            LoginActivity.this.locManager.removeUpdates(LoginActivity.this.locListener);
            LoginActivity.this.goLogin();
        }

        public void onProviderEnabled(final String provider) {
            LoginActivity.this.goLogin();
        }

        public void onProviderDisabled(final String provider) {
            LoginActivity.this.goLogin();
        }

        public void onStatusChanged(final String provider, final int status, final Bundle extras) {
        }
    };

    private void goLogin() {
        final String phone = this.mDataBinding.etLoginAccount.getText().toString().trim();
        this.mPresenter.userLogin(Objects.requireNonNull(phone), RSAHelper.getRSAData(this.mDataBinding.etLoginPassword.getText().toString().trim()));
    }

    @SuppressLint("CheckResult")
    private void listenEditTextEvents() {                              //.skip(1) 这里不能设置会出现按钮不可点击
        final Flowable<CharSequence> loginAccountObservable = RxTextView.textChanges(this.mDataBinding.etLoginAccount).toFlowable(BackpressureStrategy.LATEST);
        final Flowable<CharSequence> loginCodeObservable = RxTextView.textChanges(this.mDataBinding.etLoginPassword).toFlowable(BackpressureStrategy.LATEST);
        Flowable.combineLatest(loginAccountObservable, loginCodeObservable,
                (charSequence1, charSequence2) -> {
                    boolean phoneValid = !TextUtils.isEmpty(charSequence1.toString().trim()) && charSequence1.toString().trim().length() >= 10;
                    boolean passwordValid = !TextUtils.isEmpty(charSequence2.toString().trim()) && charSequence2.toString().trim().length() >= 6;
                    return phoneValid && passwordValid;
                }).subscribe(aBoolean -> {

            this.mDataBinding.btnLogin.setEnabled(aBoolean);
        }, throwable -> {

        });
        RxCompoundButton.checkedChanges(this.mDataBinding.ckLoginPassword).subscribe(aBoolean -> {
            if (aBoolean) {
                this.mDataBinding.etLoginPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            } else {
                this.mDataBinding.etLoginPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            }
            this.mDataBinding.etLoginPassword.setTypeface(Typeface.DEFAULT);
            final Editable editable = this.mDataBinding.etLoginPassword.getText();
            this.mDataBinding.etLoginPassword.setSelection(editable.length());
        });
    }

    @Override
    public void loginSuccess(final BaseBean<LoginBean> baseBean) {
        if (DataResult.isSuccessToast(this, baseBean)) {
            final UserBean userBean = baseBean.getData().getUser();
            FirebaseUtils.LoginEvent(this, userBean.getUserName());

//            if (!this.account.equals(PreferenceUtils.getString(PreferenceUtils.USER_PHONE, ""))) {
//                this.judgeLoginJump(baseBean, userBean);
//            } else {
//                if (this.isTimeOut) {
//                    //登陆的时候没有判断认证状态发生改变的可能
//                    if (userBean != null && userBean.getAuditStep() == PreferenceUtils.getInt(PreferenceUtils.USER_SETP, 0)) {
//                        this.preferenceSave(baseBean, userBean);
//                        this.setResult(Activity.RESULT_OK);
//                        this.finish();
//                    } else {
//                        this.judgeLoginJump(baseBean, userBean);
//                    }
//                } else {
//                    this.judgeLoginJump(baseBean, userBean);
//                }
//            }

            if (this.account.equals(PreferenceUtils.getString(PreferenceUtils.USER_PHONE, "")) && this.isTimeOut) {
                if (userBean.getAuditStep() == PreferenceUtils.getInt(PreferenceUtils.USER_SETP, 0)) {
                    this.preferenceSave(baseBean, userBean);
                    this.setResult(Activity.RESULT_OK);
                    this.finish();
                }
            }
            this.judgeLoginJump(baseBean, userBean);
        }
    }

    private void preferenceSave(final BaseBean<LoginBean> baseBean, final UserBean userBean) {
        PreferenceUtils.put(PreferenceUtils.USER_SESSIONID, baseBean.getData().getSessionId());
        PreferenceUtils.put(PreferenceUtils.USER_PHONE, userBean.getMobilePhone());
        PreferenceUtils.put(PreferenceUtils.USER_REALNAME, userBean.getUserName());
        PreferenceUtils.put(PreferenceUtils.USER_IDCARD, userBean.getIdcard());
        PreferenceUtils.put(PreferenceUtils.USER_SETP, userBean.getAuditStep());
        PreferenceUtils.put(PreferenceUtils.USER_ID, userBean.getId());
    }

    private void judgeLoginJump(final BaseBean<LoginBean> baseBean, final UserBean userBean) {
        this.preferenceSave(baseBean, userBean);
        final Map<String, String> params = new HashMap<>();
        params.put("sessionId", baseBean.getData().getSessionId());
        params.put("deviceType", Constants.DEVICE_TYPE);
        params.put("appVersion", DeviceUtils.getVersionName(this));
        params.put("deviceModel", DeviceUtils.getModel());
        params.put("deviceOs", DeviceUtils.getVersionOs());
        params.put("deviceImei", DeviceUtils.getDeviceImei(this));
        params.put("deviceToken", "");
        params.put("accessPosition", address);
        params.put("deviceSerialNumber", DeviceUtils.getSerialNumber());
        params.put("deviceAndroidId", DeviceUtils.getAndroidId(this));
        params.put("deviceMacAddress", DeviceUtils.getMacFromFile(this));

        System.out.println("输出参数："+params.toString());
        this.mPresenter.deviceInfo(params);
        if (PreferenceUtils.getInt(PreferenceUtils.USER_SETP, 0) == 0) {
//            JumpManager.jumpToClose(this, MaiMaiBorrowActivity.class);
            ARouter.getInstance().build(RouteConstant.MAI_MAI_BORROW).navigation();
        } else if (PreferenceUtils.getInt(PreferenceUtils.USER_SETP, 0) == 1 || PreferenceUtils.getInt(PreferenceUtils.USER_SETP, 0) == 2) {
//            JumpManager.jumpToClose(this, CertificationMainActivity.class);
            ARouter.getInstance().build(RouteConstant.CERTIFICATION_MAIN).navigation();
        } else if (PreferenceUtils.getInt(PreferenceUtils.USER_SETP, 0) == 3 || PreferenceUtils.getInt(PreferenceUtils.USER_SETP, 0) == 5 || PreferenceUtils.getInt(PreferenceUtils.USER_SETP, 0) == 7) {
//            JumpManager.jumpToClose(this, CertificationResultActivity.class);
            ARouter.getInstance().build(RouteConstant.CERTIFICATION_RESULT).navigation();
        } else if (PreferenceUtils.getInt(PreferenceUtils.USER_SETP, 0) == 4 || PreferenceUtils.getInt(PreferenceUtils.USER_SETP, 0) == 6) {
//            JumpManager.jumpToClose(this, MainActivity.class);
            ARouter.getInstance().build(RouteConstant.MAIN).navigation();
        }
        ActivityStack.getInstance().removeAllExceptCurrent(this);
        this.finish();
    }

    @Override
    public boolean onKeyDown(final int keyCode, final KeyEvent event) {
        return keyCode == KeyEvent.KEYCODE_BACK || super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == LoginActivity.REGISTERCODE) {
            this.mDataBinding.etLoginPassword.setText("");
            if (null != data) {
                this.loginAccountShow(data.getStringExtra(ExtraKeys.Key_Msg1), data.getStringExtra(ExtraKeys.Key_Msg2));
            }
        }
    }

    private void loginAccountShow(final String phone, final String backPassword) {
        this.mDataBinding.etLoginAccount.setText(phone);
        this.mDataBinding.etLoginPassword.setText(backPassword);
        final Editable editableAccount = this.mDataBinding.etLoginAccount.getText();
        this.mDataBinding.etLoginAccount.setSelection(editableAccount.length());
        final Editable editablePwd = this.mDataBinding.etLoginPassword.getText();
        this.mDataBinding.etLoginPassword.setSelection(editablePwd.length());
    }

    /**
     * 解析经纬度之后的实际地址
     *
     * @return
     */
    private String getLocationAddress(Location location) {

        final Geocoder geoCoder = new Geocoder(getBaseContext(), Locale.CHINESE);
        try {
            final List<Address> addresses = geoCoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            for (Address address1 : addresses) {
                if (BuildConfig.DEBUG) {
                    Log.i(TAG, "getLocationAddress: " + address1);
                }
            }
            Address item = addresses.get(0);
            if (addresses != null && addresses.size() > 0) {
                final StringBuilder stringBuilder = new StringBuilder();
                if (!TextUtils.isEmpty(item.getCountryName())) {
                    stringBuilder.append(item.getCountryName());
                }
                if (!TextUtils.isEmpty(item.getAddressLine(0))) {
                    stringBuilder.append(item.getAddressLine(0));
                }
                if (!TextUtils.isEmpty(item.getAddressLine(1))) {
                    stringBuilder.append(item.getAddressLine(1));
                }
                if (!TextUtils.isEmpty(item.getAddressLine(2))) {
                    stringBuilder.append(item.getAddressLine(2));
                }
                accessPosition = stringBuilder.toString();
            }
        } catch (final IOException e) {
            accessPosition = "";
            Log.i("getLocationAddress", "异常：" + e.getMessage());
            e.printStackTrace();

        } finally {
            address = this.accessPosition + "&(" + location.getLatitude() + "," + location.getLongitude() + ")";
            if (BuildConfig.DEBUG) {
                Log.i("getLocationAddress", "解析定位: " + address);
            }
        }
        return address;
    }

}
