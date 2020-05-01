package com.mmkj.usercenter.di.module;

import android.util.Log;

import com.mmkj.baselibrary.app.Constants;
import com.mmkj.baselibrary.di.qualifier.LoanMainUrl;
import com.mmkj.baselibrary.util.NetUtils;
import com.mmkj.lib.flb.BaseConfig;
import com.orhanobut.logger.Logger;
import com.mmkj.usercenter.BuildConfig;
import com.mmkj.usercenter.model.http.api.UserCenterApiService;

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
import okio.Buffer;
import okio.BufferedSource;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by codeest on 2017/2/26.
 * //        Interceptor apikey = new Interceptor() {
 * //            @Override
 * //            public Response intercept(Chain chain) throws IOException {
 * //                Request request = chain.request();
 * //                request = request.newBuilder()
 * //                        .addHeader("apikey",Constants.KEY_API)
 * //                        .build();
 * //                return chain.proceed(request);
 * //            }
 * //        }
 * //        设置统一的请求头部参数
 * //        builder.addInterceptor(apikey);
 */

@Module
public class UserCenterHttpModule {

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
    @LoanMainUrl
    Retrofit provideDhfRetrofit(Retrofit.Builder builder, OkHttpClient client) {
        return createRetrofit(builder, client, BaseConfig.getInstance().getBaseUrl());
    }

    @Singleton
    @Provides
    UserCenterApiService provideLoanMainService(@LoanMainUrl Retrofit retrofit) {
        return retrofit.create(UserCenterApiService.class);
    }

    @Singleton
    @Provides
    OkHttpClient provideClient(OkHttpClient.Builder builder) {
        try {
            sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, new TrustManager[]{xtm}, new SecureRandom());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(mLoggingInterceptor);
        }
        Cache cache = new Cache(new File(Constants.PATH_CACHE), 1024 * 1024 * 50);
        return builder.cache(cache)//设置缓存
                .connectTimeout(240, TimeUnit.SECONDS)
                .readTimeout(240, TimeUnit.SECONDS)
                .writeTimeout(240, TimeUnit.SECONDS)

                .addNetworkInterceptor(mRewriteCacheControlInterceptor)
//				.addNetworkInterceptor(new StethoInterceptor())

                .addInterceptor(mRewriteCacheControlInterceptor)//无网络拦截
                .retryOnConnectionFailure(true)
                .sslSocketFactory(sslContext.getSocketFactory())
                .hostnameVerifier(DO_NOT_VERIFY).build();
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
    private SSLContext sslContext = null;
    private HostnameVerifier DO_NOT_VERIFY = (hostname, session) -> {
        //check if SSL is really valid
        return session.isValid();
    };

    private Retrofit createRetrofit(Retrofit.Builder builder, OkHttpClient client, String url) {
        return builder
                .baseUrl(url)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
