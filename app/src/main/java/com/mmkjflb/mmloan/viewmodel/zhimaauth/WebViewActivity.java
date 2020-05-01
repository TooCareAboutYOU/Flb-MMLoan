package com.mmkjflb.mmloan.viewmodel.zhimaauth;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.http.SslError;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.mmkjflb.mmloan.BuildConfig;
import com.mmkjflb.mmloan.R;
import com.mmkjflb.mmloan.databinding.ActivityZhimaauthBinding;
import com.mmkjflb.mmloan.model.entity.QueryInviteActivityEntity;
import com.syhmmfqphl.xyxlibrary.utils.ExtraKeys;
import com.mmkj.baselibrary.RouteConstant;
import com.mmkj.baselibrary.annotation.ActivityFragmentInject;
import com.mmkj.baselibrary.base.DataBindingActivity;
import com.mmkj.baselibrary.util.StringUtils;

import java.util.Map;

/**
 * Created by Administrator on 2017/10/27.
 */
@Route(path = RouteConstant.WEBVIEW)
@ActivityFragmentInject(contentViewId = R.layout.activity_zhimaauth, loadingTargetView = R.id.webview_zhima)
public class WebViewActivity extends DataBindingActivity<ActivityZhimaauthBinding> {

	@Autowired
	public String loadUrl = "";
//	private QueryInviteActivityEntity queryInviteActivityEntity = null;

	@Override
	protected void initViews() {
//		loadUrl = getIntent().getStringExtra(ExtraKeys.Key_Msg1);

//		queryInviteActivityEntity = (QueryInviteActivityEntity) getIntent().getSerializableExtra(ExtraKeys.Key_Msg2);
		if (BuildConfig.DEBUG) {
			Log.i("PRETTY_LOGGER", "打印地址: "+loadUrl);
		}

		WebSettings settings = mDataBinding.webviewZhima.getSettings();
		mDataBinding.webviewZhima.loadUrl(loadUrl);
		mDataBinding.webviewZhima.setOverScrollMode(View.OVER_SCROLL_NEVER);//webview滚动拖动到顶部或底部阴影
		settings.setJavaScriptEnabled(true);
		settings.setLoadWithOverviewMode(true);
		settings.setSupportZoom(true);
		settings.setBuiltInZoomControls(true);   //设置支持缩放
		settings.setDisplayZoomControls(false); // 设置显示缩放按钮(minsdk=11)
		settings.setUseWideViewPort(true);
		settings.setAppCacheEnabled(true);
		settings.setJavaScriptCanOpenWindowsAutomatically(true);
		settings.setBlockNetworkImage(true);
//		mDataBinding.webviewZhima.setWebViewClient(new WebViewClient(){
//			@Override
//			public void onPageFinished(WebView view, String url) {
////				super.onPageFinished(view, url);
//				settings.setBlockNetworkImage(false);
////				if (!settings.getLoadsImagesAutomatically()){
////					settings.setLoadsImagesAutomatically(true);
////				}
//				super.onPageFinished(view, url);
//			}
//			@Override
//			public boolean shouldOverrideUrlLoading(WebView view, String url) {
//				view.loadUrl(url);
//				return true;
//			}
//		});
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
			settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
		mDataBinding.webviewZhima.setWebChromeClient(new MyWebChromeClient());//设置可alert弹窗
		mDataBinding.webviewZhima.setWebViewClient(new MyWebViewClient());

		if (null != mDataBinding.commonincludeToolbar.commonincludeToolbar) {
			setSupportActionBar(mDataBinding.commonincludeToolbar.commonincludeToolbar);
			if (getSupportActionBar() != null) {
				getSupportActionBar().setDisplayHomeAsUpEnabled(true);
				getSupportActionBar().setHomeAsUpIndicator(R.drawable.back_black);
			}
		}
	}


	/**
	 * 设置标题
	 */
	private class MyWebChromeClient extends WebChromeClient {
		@Override
		public void onReceivedTitle(WebView view, String title) {
			super.onReceivedTitle(view, title);
			if (getSupportActionBar() != null && !TextUtils.isEmpty(title)) {
				getSupportActionBar().setTitle(title + "");
			}
		}

		@Override
		public void onProgressChanged(WebView view, int newProgress) {
			super.onProgressChanged(view, newProgress);
			if (newProgress == 100) {
				hideLoading();
			} else {
				showLoading();
			}
		}
	}

	private class MyWebViewClient extends WebViewClient {
		@Override
		public void onReceivedSslError(WebView view, final SslErrorHandler handler, SslError error) {
			final AlertDialog.Builder builder = new AlertDialog.Builder(WebViewActivity.this);
			String message = "SSL Certificate error.";
			switch (error.getPrimaryError()) {
				case SslError.SSL_UNTRUSTED:
					message = "The certificate authority is not trusted.";
					break;
				case SslError.SSL_EXPIRED:
					message = "The certificate has expired.";
					break;
				case SslError.SSL_IDMISMATCH:
					message = "The certificate Hostname mismatch.";
					break;
				case SslError.SSL_NOTYETVALID:
					message = "The certificate is not yet valid.";
					break;
				case SslError.SSL_DATE_INVALID:
					message = "The date of the certificate is invalid";
					break;
				case SslError.SSL_INVALID:
				default:
					message = "A generic error occurred";
					break;
			}
			message += " Do you want to continue anyway?";
			builder.setTitle("SSL Certificate Error");
			builder.setMessage(message);

			builder.setPositiveButton("continue", (dialog, which) -> handler.proceed());
			builder.setNegativeButton("cancel", (dialog, which) -> handler.cancel());
			final AlertDialog dialog = builder.create();
			dialog.show();
		}

		@Override
		public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
			super.onReceivedError(view, errorCode, description, failingUrl);
			loadingError(v -> mDataBinding.webviewZhima.reload());
		}


		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			if (url.contains("www.dhfzhengxin.com/callback")) {
				setResult(RESULT_OK);
				finish();
				return true;
			} else if (url.contains("orderCallbackForFasPay")) {
				Map<String, String> statusMap = StringUtils.decodeUrlParameters(url);
				String status = statusMap != null ? statusMap.get("status") : null;
				Intent intent = getIntent();
				intent.putExtra(ExtraKeys.Key_Msg1, status);
				setResult(RESULT_OK, intent);
				finish();
				return true;
			}
			if (null != url) view.loadUrl(url);
			return true;
		}

		@Override
		public void onPageFinished(WebView view, String url) {
			view.getSettings().setBlockNetworkImage(false);
			//判断webview是否加载了，图片资源
			if (!view.getSettings().getLoadsImagesAutomatically()) {
				//设置wenView加载图片资源
				view.getSettings().setLoadsImagesAutomatically(true);
			}
			super.onPageFinished(view, url);
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (mDataBinding.webviewZhima != null) {
			mDataBinding.webviewZhima.destroy();
		}
	}


}
