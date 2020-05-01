package com.mmkjflb.mmloan.viewmodel.splash;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.afollestad.materialdialogs.MaterialDialog;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.facebook.FacebookSdk;
import com.facebook.LoggingBehavior;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.appevents.AppEventsLogger;
import com.mmkj.baselibrary.RouteConstant;
import com.mmkj.baselibrary.annotation.ActivityFragmentInject;
import com.mmkj.baselibrary.model.entitry.BaseBean;
import com.mmkj.baselibrary.util.DeviceUtils;
import com.mmkj.baselibrary.util.PermissionPageUtils;
import com.mmkj.baselibrary.util.PreferenceUtils;
import com.mmkj.baselibrary.util.RxViewUtil;
import com.mmkj.baselibrary.util.StringUtils;
import com.mmkj.usercenter.model.entity.CustomerServiceData;
import com.mmkjflb.mmloan.BuildConfig;
import com.mmkjflb.mmloan.R;
import com.mmkjflb.mmloan.app.Constants;
import com.mmkjflb.mmloan.app.PermissionGroup;
import com.mmkjflb.mmloan.base.BaseActivity;
import com.mmkjflb.mmloan.databinding.ActivitySplashBinding;
import com.mmkjflb.mmloan.dialog.ShowUpdateDialog;
import com.mmkjflb.mmloan.model.entity.ChannelGoogleEntity;
import com.mmkjflb.mmloan.model.entity.UpVersionBean;
import com.mmkjflb.mmloan.model.http.DataResult;
import com.orhanobut.logger.Logger;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

import static com.mmkjflb.mmloan.R.id.includeCall;
import static com.mmkjflb.mmloan.R.id.includeCamera;
import static com.mmkjflb.mmloan.R.id.includeContact;
import static com.mmkjflb.mmloan.R.id.includeLocation;

/**
 * 启动页（注释添加完成）
 * 权限处理，VIVO OPPO手机权限通过或拒绝无法区分，全部走的同意方法，后面可以作区分，如果是vivo oppo手机通过检测是否获取到设备信息判断是否获取相应权限，其余手机正常用rxpermission（原生方法也是一样）判断即可
 */
@Route(path = RouteConstant.SPLASH)
@ActivityFragmentInject(contentViewId = R.layout.activity_splash)
public class SplashActivity extends BaseActivity<SplashPresenter, ActivitySplashBinding> implements SplashContract.View {
    private RxPermissions rxPermissions;
    private Disposable disposable;
    private String channelName = "";

    @SuppressLint("CheckResult")
    @Override
    protected void initViews() {
        getActivityComponent().inject(this);
        rxPermissions = new RxPermissions(SplashActivity.this);
        RxViewUtil.clicks(mDataBinding.tvTime).subscribe(o -> goMainActivity());
        if (PreferenceUtils.get(PreferenceUtils.HASALLPERMISSION, false)) {
            hasPermissionGoNext();
        } else {
            showPermissionDialog();
        }
    }

    /**
     * 第一次安装，上传设备信息和渠道
     * 第一次后就不需要
     */
    @SuppressLint("CheckResult")
    private void uploadAdbise() {
        if (PreferenceUtils.getBoolean(PreferenceUtils.FIRST_INSTALL, true)) {
            Log.i("FacebookSDK", "监听首次安装事件: ");
            /**
             * TODO:   faceBook 登录埋点
             */
            AppEventsLogger.newLogger(this).logEvent(AppEventsConstants.EVENT_NAME_ACTIVATED_APP);
            uploadInform();
        } else {
            timeCounter();
        }
    }

    private void timeCounter() {
        mDataBinding.tvTime.setVisibility(View.VISIBLE);
        Observable.interval(0, 1, TimeUnit.SECONDS)
                //执行count次（没有该方法不会停止）
                .take(Constants.SPLASH_TIMECOUNT + 1)
                .map(aLong -> Constants.SPLASH_TIMECOUNT - aLong)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(@NonNull Long aLong) {
                        if (aLong != 0) {
                            mDataBinding.tvTime.setText(aLong + getResources().getString(R.string.s_skip));
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) { }

                    @Override
                    public void onComplete() {
                        //第一次安装上传设备信息和渠道供运营统计推广渠道量
                        goMainActivity();
                    }
                });
    }

    private void uploadInform() {
        String referrer = PreferenceUtils.getString(PreferenceUtils.REFERRER, "");
        String installReferrer = PreferenceUtils.getString(PreferenceUtils.INSTALL_REFERRER, "");
        String usedReferrer = (!TextUtils.isEmpty(referrer)) ? referrer : installReferrer;
        if (!TextUtils.isEmpty(usedReferrer)) {
            Map<String, String> referrerMap = StringUtils.decodeUrlParameters(usedReferrer);
            channelName = referrerMap != null ? referrerMap.get("utm_source") : null;
            if (channelName == null) {
                channelName = "";
            }
        }
        Map<String, Object> map = new HashMap<>(3);
        map.put("channelName", channelName);
        map.put("androidId", DeviceUtils.getAndroidId(this));
        map.put("imei", DeviceUtils.getUniqueDeviceId(this));
        mPresenter.insertChannelGoogle(map);
    }

    @Override
    public void insertChannelGoogleSuc(BaseBean<ChannelGoogleEntity> baseBean) {
        PreferenceUtils.put(PreferenceUtils.FIRST_INSTALL, false);
        if (baseBean.getCode() == 1) {
            PreferenceUtils.put(PreferenceUtils.CHANNELNAME, baseBean.getData().getChannelId());
        } else {
            PreferenceUtils.remove(PreferenceUtils.CHANNELNAME);
        }
        timeCounter();
    }

    /**
     * 根据本地sp跳转至下一个页面
     */
    private void goMainActivity() {
        if (!TextUtils.isEmpty(PreferenceUtils.getUserSessionid()) && PreferenceUtils.get(PreferenceUtils.USER_SETP, 0) != 0) {
            if (PreferenceUtils.getInt(PreferenceUtils.USER_SETP, 0) == 0) {
                ARouter.getInstance().build(RouteConstant.MAI_MAI_BORROW).navigation();
            } else if (PreferenceUtils.getInt(PreferenceUtils.USER_SETP, 0) == 1 || PreferenceUtils.getInt(PreferenceUtils.USER_SETP, 0) == 2) {
                ARouter.getInstance().build(RouteConstant.CERTIFICATION_MAIN).navigation();
            } else if (PreferenceUtils.getInt(PreferenceUtils.USER_SETP, 0) == 3 || PreferenceUtils.getInt(PreferenceUtils.USER_SETP, 0) == 5 || PreferenceUtils.getInt(PreferenceUtils.USER_SETP, 0) == 7) {
                ARouter.getInstance().build(RouteConstant.CERTIFICATION_RESULT).navigation();
            } else if (PreferenceUtils.getInt(PreferenceUtils.USER_SETP, 0) == 4 || PreferenceUtils.getInt(PreferenceUtils.USER_SETP, 0) == 6) {
                ARouter.getInstance().build(RouteConstant.MAIN).navigation();
            }
        } else {
            ARouter.getInstance().build(RouteConstant.LOGIN).navigation();
        }
        finish();
    }

    /**
     * 在获取了权限之后
     * 获取版本信息
     * 获取服务信息（客服电话）
     */
    private void hasPermissionGoNext() {
        mPresenter.getAppVersion(Constants.VERSION_TYPE, getPackageName());
        mPresenter.getServiceInfo();
    }

    /**
     * 获取版本信息 版本号是通过获取系统versionName和后台设置的versionName作比较（不是通过versionCode）
     */
    @Override
    public void getAppVersionSuc(BaseBean<UpVersionBean> baseBean) {
        if (DataResult.isSuccessUnToast(this, baseBean)) {
            String versionjudge = "";
            UpVersionBean upVersionBean = baseBean.getData();
            String[] versioncodes = upVersionBean.getLatestVersion().split("[.]");
            for (String item : versioncodes) {
                Logger.t("UpdateVersion").d(item);
                versionjudge = versionjudge + item;
            }
            String[] versionName = DeviceUtils.getVersionName(this).split("[.]");
            String localVersion = "";
            for (String item : versionName) {
                Logger.t("UpdateVersion").d(item);
                localVersion = localVersion + item;
            }
            if (Integer.parseInt(localVersion) < Integer.parseInt(versionjudge)) {  //有更新版本，当前不是最新版本
                ShowUpdateDialog builder = new ShowUpdateDialog(this, R.style.DialogCommon, Integer.parseInt(versionjudge), baseBean.getData(), null);
                builder.show();
                builder.setOnDismissListener(dialog -> {
                    if (mDataBinding.tvTime.getVisibility() == View.GONE) {
                        uploadAdbise();
                    }
                });
            } else {
                uploadAdbise();
            }
        }
    }

    /**
     * 保存服务电话
     *
     * @param data
     */
    @Override
    public void getServiceInfoSuc(BaseBean<List<CustomerServiceData>> data) {
        if (DataResult.isSuccessUnToast(this, data)) {
            PreferenceUtils.put(PreferenceUtils.SERVICE_PHONE, data.getData().get(0).getItemValue());
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        cancelDisposable();
    }

    private void cancelDisposable() {
        if (disposable != null) {
            disposable.dispose();
            disposable = null;
        }
    }

    /**
     * 权限说明弹窗
     */
    Dialog dialogPermission = null;


    /**
     * 权限引导页第一页
     */
    @SuppressLint("CutPasteId")
    private void showPermissionDialog() {
        dialogPermission = new Dialog(this, R.style.DialogCommon);
        View view = View.inflate(this, R.layout.dialog_permission, null);

        TextView tvCallTitle = view.findViewById(includeCall).findViewById(R.id.tvPermissionTitle);
        TextView tvContactTitle = view.findViewById(includeContact).findViewById(R.id.tvPermissionTitle);
        TextView tvLocationTitle = view.findViewById(includeLocation).findViewById(R.id.tvPermissionTitle);
        TextView tvCameraTitle = view.findViewById(includeCamera).findViewById(R.id.tvPermissionTitle);

        ImageView ivCall = view.findViewById(includeCall).findViewById(R.id.ivPermission);
        ImageView ivContact = view.findViewById(includeContact).findViewById(R.id.ivPermission);
        ImageView ivLocation = view.findViewById(includeLocation).findViewById(R.id.ivPermission);
        ImageView ivCamera = view.findViewById(includeCamera).findViewById(R.id.ivPermission);

        TextView tvCallMsg = view.findViewById(includeCall).findViewById(R.id.tvPermissionMsg);
        TextView tvContactMsg = view.findViewById(includeContact).findViewById(R.id.tvPermissionMsg);
        TextView tvLocationMsg = view.findViewById(includeLocation).findViewById(R.id.tvPermissionMsg);
        TextView tvCameraMsg = view.findViewById(includeCamera).findViewById(R.id.tvPermissionMsg);

        TextView tvAcceptMsg = view.findViewById(R.id.tvAcceptMsg);
        tvCallTitle.setText("Manage Calls");
        tvContactTitle.setText("Contacts");
        tvLocationTitle.setText("Location");
        tvCameraTitle.setText("Camera");

        ivCall.setImageResource(R.drawable.permission_call);
        ivContact.setImageResource(R.drawable.permission_contact);
        ivLocation.setImageResource(R.drawable.permission_location);
        ivCamera.setImageResource(R.drawable.permission_camera);

        tvCallMsg.setText("Used to securely link your account to your device.We will never send or receive calls form your device");
        tvContactMsg.setText("Used to establish your network");
        tvLocationMsg.setText("Used to ensure you’re the only person that can apply for a loan form your device");
        tvCameraMsg.setText("Userd to submit your certification");

        view.findViewById(R.id.btnContinue).setOnClickListener(v -> requestPermissions());


        //设置Textview中包含不同颜色+下划线+点击
        tvAcceptMsg.setText(getSpan());
        tvAcceptMsg.setHighlightColor(Color.TRANSPARENT);//去掉点击效果
        tvAcceptMsg.setMovementMethod(LinkMovementMethod.getInstance());//这个必须要
        dialogPermission.setContentView(view);
        dialogPermission.setCanceledOnTouchOutside(false);
        dialogPermission.setCancelable(false);
        Window window = dialogPermission.getWindow();
        assert window != null;
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        dialogPermission.show();
    }

    private SpannableString getSpan() {
        final View.OnClickListener click1Listener = v -> {
            ARouter.getInstance().build(RouteConstant.WEBVIEW).withString("loadUrl", Constants.PRIVATE_AGREEMENT).navigation();

        };
        String one = "By tapping \"Continue\" I accept and agree to the ";
        String two = "User's Privacy Policy";
        SpannableString spnableInfo = new SpannableString(one + two);
        spnableInfo.setSpan(new ForegroundColorSpan(Color.parseColor("#444444")), 0, one.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spnableInfo.setSpan(new ClickableSpan() {
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(true);
                ds.setColor(Color.parseColor("#25C69B"));
            }

            @Override
            public void onClick(@NotNull View widget) {
                click1Listener.onClick(widget);
            }
        }, one.length(), one.length() + two.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spnableInfo;
    }



    /**
     * 检查权限
     */
    @SuppressLint("CheckResult")
    private void requestPermissions() {
        rxPermissions.requestEachCombined(PermissionGroup.PERMISSION_GROUP)
                .subscribe(permission -> {
                    Log.i("permissiontest", "permission=" + permission.name);
                    //全部同意后调用
                    if (permission.granted) {
                        PreferenceUtils.put(PreferenceUtils.HASALLPERMISSION, true);
                        if (dialogPermission != null) {
                            dialogPermission.dismiss();
                        }
                        hasPermissionGoNext();
                    }
                    //只要有一个选择：禁止，但没有选择“以后不再询问”，以后申请权限，会继续弹出提示
                    else if (permission.shouldShowRequestPermissionRationale) {
                        dialogGotoSettings();
                    }
                    //只要有一个选择：禁止，但选择“以后不再询问”，以后申请权限，不会继续弹出提示
                    else {
                        permissionGuideDialog();
                    }
                });
    }

    /**
     * 未授权提示弹窗
     */
    private void dialogGotoSettings() {
        new MaterialDialog.Builder(this)
                .title("Allow permissions")
                .content("MMloan requires these permissions to determine if we can give you a loan. Your information is kept 100% secure.")
                .positiveText("YES,CONTINUE")
                .negativeColor(ContextCompat.getColor(this, R.color.color_global))
                .positiveColor(ContextCompat.getColor(this, R.color.color_global))
                .onPositive((dialog, which) -> {
                    dialog.dismiss();
                    requestPermissions();
                })
                .negativeText("NO THANKS")
                .onNegative((dialog, which) -> {
                    dialog.dismiss();
                    permissionGuideDialog();
                })
                .cancelable(false)
                .canceledOnTouchOutside(false)
                .show();
    }


    /**
     * 权限有拒绝之后的引导页
     */
    private void permissionGuideDialog() {
        //这个弹窗用recyclerview优化吧，之前是少，后来慢慢item加多了
        Dialog dialog = new Dialog(this, R.style.DialogCommon);
        View view = View.inflate(this, R.layout.dialog_permission_guide, null);
        view.findViewById(R.id.ivBack).setOnClickListener(v -> dialog.dismiss());
        view.findViewById(R.id.btnGoSetting).setOnClickListener(v -> new PermissionPageUtils(this).jumpPermissionPage());
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(true);
        Window window = dialog.getWindow();
        assert window != null;
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        window.setWindowAnimations(R.style.SlideLeftInRightOutAnimation); // 添加动画
        dialog.show();
    }

}

