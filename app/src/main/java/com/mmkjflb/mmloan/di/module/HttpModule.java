package com.mmkjflb.mmloan.di.module;

import android.util.Log;

import com.google.gson.JsonObject;
import com.mmkj.baselibrary.util.NetUtils;
import com.mmkjflb.mmloan.BuildConfig;
import com.mmkjflb.mmloan.app.Constants;
import com.mmkjflb.mmloan.di.qualifier.LoanMainUrl;
import com.mmkjflb.mmloan.model.http.api.ApiService;
import com.mmkj.lib.flb.BaseConfig;
import com.orhanobut.logger.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;
import okio.BufferedSource;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author zhangshuai
 * 网络初始化
 */
@Module
public class HttpModule {

    @Singleton
    @Provides
    Retrofit.Builder provideRetrofitBuilder() {
        return new Retrofit.Builder();
    }

    @Singleton
    @Provides
    OkHttpClient.Builder provideOkHttpBuilder() {
        return new OkHttpClient.Builder();
    }

    @Singleton
    @Provides
    OkHttpClient provideClient(OkHttpClient.Builder builder) {
        HttpLoggingInterceptor mHttpLoggingInterceptor = new HttpLoggingInterceptor(message -> {
            Log.w("PRETTY_LOGGER", "--->>" + message);
        });
        if (BuildConfig.DEBUG) {
            Log.w("PostData", "初始化 okhttp 拦截: ");
            builder.addInterceptor(mLoggingInterceptor)
                    .addInterceptor(mHttpLoggingInterceptor);
            mHttpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        }

        Cache cache = new Cache(new File(Constants.PATH_CACHE), 1024 * 1024 * 50);
        return builder.cache(cache)//设置缓存
                .connectTimeout(240, TimeUnit.SECONDS)
                .readTimeout(240, TimeUnit.SECONDS)
                .writeTimeout(240, TimeUnit.SECONDS)
//				.addNetworkInterceptor(mRewriteCacheControlInterceptor)
//				.addNetworkInterceptor(new StethoInterceptor())
//                .addInterceptor(mRewriteCacheControlInterceptor)//无网络拦截

//                .retryOnConnectionFailure(true)
                .sslSocketFactory(createSSLSocketFactory(), xtm)
                .hostnameVerifier(new TrustAllHostNameVerifier())
                .build();
    }

    private Retrofit createRetrofit(Retrofit.Builder builder, OkHttpClient client, String url) {
        return builder
                .baseUrl(url)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    @LoanMainUrl
    Retrofit provideDhfRetrofit(Retrofit.Builder builder, OkHttpClient client) {
        return createRetrofit(builder, client, BaseConfig.getInstance().getBaseUrl()); // ApiService.HOST
    }

    @Singleton
    @Provides
    ApiService provideLoanMainService(@LoanMainUrl Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }

    private SSLSocketFactory createSSLSocketFactory() {
        SSLSocketFactory sslSocketFactory = null;
        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[]{xtm}, new SecureRandom());
            sslSocketFactory = sslContext.getSocketFactory();
            return sslSocketFactory;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        return null;
    }

    private X509TrustManager xtm = new X509TrustManager() {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            X509Certificate[] x509Certificates = new X509Certificate[0];
            return x509Certificates;
        }
    };

    private class TrustAllHostNameVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }


    /**
     * 云端响应头拦截器，用来配置缓存策略
     */
    private Interceptor mRewriteCacheControlInterceptor = chain -> {
        Request request = chain.request();
        if (!NetUtils.isNetworkConnected()) {
            request = request.newBuilder().header("version", "m-1.4.2").cacheControl(CacheControl.FORCE_CACHE).build();
        }
        Response response = chain.proceed(request);
        if (NetUtils.isNetworkConnected()) {
            int maxAge = 0;
            // 有网络时, 不缓存, 最大保存时长为0
            response.newBuilder()
                    .header("Cache-Control", "public, max-age=" + maxAge)
                    .removeHeader("Pragma")
                    .build();
        } else {
            // 无网络时，设置超时为1天
            int maxStale = 60 * 60 * 24;
            response.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                    .removeHeader("Pragma")
                    .build();
        }
        return response;
    };
    /**
     * 打印返回的json数据拦截器
     */
    private Interceptor mLoggingInterceptor = chain -> {
        final Request request = chain.request();
        final Response response = chain.proceed(request);
        final ResponseBody responseBody = response.body();
        final long contentLength = responseBody.contentLength();
        BufferedSource source = responseBody.source();
        source.request(Long.MAX_VALUE); // Buffer the entire body.
        Buffer buffer = source.buffer();
        Charset charset = Charset.forName("UTF-8");
        MediaType contentType = responseBody.contentType();
        if (contentType != null) {
            try {
                charset = contentType.charset(charset);
            } catch (UnsupportedCharsetException e) {
                Logger.e("Couldn't decode the response body; charset is likely malformed.");
                return response;
            }
        }
        if (contentLength != 0) {
            Log.i("PRETTY_LOGGER", request.url().toString());
            String jsonString = buffer.clone().readString(Objects.requireNonNull(charset));
            Logger.json(jsonString);
        }
        return response;
    };

}
