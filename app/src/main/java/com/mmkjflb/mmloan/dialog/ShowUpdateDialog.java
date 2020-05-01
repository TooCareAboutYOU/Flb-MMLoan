package com.mmkjflb.mmloan.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;

import androidx.databinding.DataBindingUtil;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.webkit.URLUtil;
import android.widget.LinearLayout;

import com.mmkjflb.mmloan.BuildConfig;
import com.mmkjflb.mmloan.R;
import com.mmkjflb.mmloan.app.BaseApplication;
import com.mmkjflb.mmloan.databinding.DialogUpdateBinding;
import com.mmkjflb.mmloan.model.entity.UpVersionBean;
import com.mmkjflb.mmloan.viewmodel.splash.SplashActivity;
import com.mmkj.baselibrary.app.ActivityStack;
import com.mmkj.baselibrary.util.RxViewUtil;

public class ShowUpdateDialog extends Dialog {
    private Context mContext;
    private UpVersionBean upVersionBean;
    private DialogUpdateBinding mDataBinding;
    private DialogCallBack mCallBack;

    public ShowUpdateDialog(SplashActivity context, int theme, int versioncode, UpVersionBean upVersionBean, DialogCallBack callBack) {
        super(context, theme);
        this.mContext = context;
        this.upVersionBean = upVersionBean;
        this.mCallBack = callBack;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.dialog_update, null, false);
        setContentView(mDataBinding.getRoot());

//		mDataBinding = DialogUpdateBinding.bind(LayoutInflater.from(getContext()).inflate(R.layout.dialog_update, null, false));
        initData();
        Window window = getWindow();
        window.setGravity(Gravity.CENTER);
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        setCanceledOnTouchOutside(!this.upVersionBean.isForceUpdate());
        initClicks();
    }

    @SuppressLint("CheckResult")
    private void initClicks() {
        RxViewUtil.clicks(mDataBinding.ivClose).subscribe(o -> cancelMethod());
        RxViewUtil.clicks(mDataBinding.btnUpdate).subscribe(o -> {
//				try {
//					mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
//				} catch (android.content.ActivityNotFoundException anfe) {
//					mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
//				}
            moveToGooglePlay();
        });
    }

    // https://www.jianshu.com/p/79f1eb6cf32e
    private void moveToGooglePlay() {    //appPkg 是应用的包名
        final String GOOGLE_PLAY = "com.android.vending";//这里对应的是谷歌商店，跳转别的商店改成对应的即可
        try {
            Log.i("moveToGooglePlay", "打印: " + this.upVersionBean.getDownloadUrl() +"\t\t"+this.mContext.getPackageName());
//            String url = "";
//            if (!TextUtils.isEmpty(this.upVersionBean.getDownloadUrl()) && (Patterns.WEB_URL.matcher(this.upVersionBean.getDownloadUrl()).matches() || URLUtil.isValidUrl(this.upVersionBean.getDownloadUrl()))) {
//                url = this.upVersionBean.getDownloadUrl();
//            } else {
            String url = "market://details?id=" + this.mContext.getPackageName();
//            }

            Uri uri = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setPackage(GOOGLE_PLAY);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            this.mContext.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置数据
     */
    private void initData() {
        mDataBinding.tvVersion.setText("v" + upVersionBean.getLatestVersion());
        mDataBinding.tvUpdateMsg.setText(upVersionBean.getUpdateDesc());
        if (upVersionBean.isForceUpdate()) {
            mDataBinding.ivClose.setVisibility(View.GONE);
        } else {
            mDataBinding.ivClose.setVisibility(View.VISIBLE);
        }
    }

    private void cancelMethod() {
        if (upVersionBean.isForceUpdate()) {
            ActivityStack.getInstance().exitApp();
        } else {
            dismiss();
            mCallBack.buttonClick();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            cancelMethod();
        }
        return super.onKeyDown(keyCode, event);
    }

    public interface DialogCallBack {
        void buttonClick();
    }
}

