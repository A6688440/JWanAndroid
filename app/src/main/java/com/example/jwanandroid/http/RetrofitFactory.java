package com.example.jwanandroid.http;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by SJC on 2020/5/27.
 * Describe：
 */
public class RetrofitFactory {

    private static final String TAG = "RetrofitFactory";


    private static Retrofit retrofit;
    private static Retrofit retrofitAddCookie;
    private static Retrofit retrofitReadCookie;



    private static OkHttpClient okHttpClient;
    private static OkHttpClient okHttpClientAddCookie;
    private static OkHttpClient okHttpClientReadCookie;


    public static RetrofitService Http() {
        return getHttp().create(RetrofitService.class);
    }

    public static RetrofitService HttpAddCookie() { return getHttpAddCookie().create(RetrofitService.class); }

    public static RetrofitService HttpReadCookie() { return getHttpReadCookie().create(RetrofitService.class); }


    private synchronized static OkHttpClient getOkHttpClient() {
        if (okHttpClient == null) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {

                    Log.e(TAG, "retrofitBack" + message);
                }
            });
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
            okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(100, TimeUnit.SECONDS)
                    .readTimeout(100, TimeUnit.SECONDS)
                    .writeTimeout(100, TimeUnit.SECONDS)
                    .addInterceptor(loggingInterceptor)  //日志
                    .build();
        }
        return okHttpClient;
    }

    private synchronized static OkHttpClient getOkHttpClientAddCookie(){
        if (okHttpClientAddCookie == null) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {

                    Log.e(TAG, "retrofitBack" + message);
                }
            });
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
            okHttpClientAddCookie = new OkHttpClient.Builder()
                    .connectTimeout(100, TimeUnit.SECONDS)
                    .readTimeout(100, TimeUnit.SECONDS)
                    .writeTimeout(100, TimeUnit.SECONDS)
                    .addInterceptor(loggingInterceptor)  //日志
                    .addInterceptor(new AddCookiesInterceptor())
                    .build();
        }
        return okHttpClientAddCookie;
    }

    private synchronized static OkHttpClient getOkHttpClientReadCookie() {
        if (okHttpClientReadCookie == null) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {

                    Log.e(TAG, "retrofitBack" + message);
                }
            });
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
            okHttpClientReadCookie = new OkHttpClient.Builder()
                    .connectTimeout(100, TimeUnit.SECONDS)
                    .readTimeout(100, TimeUnit.SECONDS)
                    .writeTimeout(100, TimeUnit.SECONDS)
                    .addInterceptor(loggingInterceptor)  //日志
                    .addInterceptor(new ReceivedCookiesInterceptor())
                    .build();
        }
        return okHttpClientReadCookie;
    }


    private synchronized static Retrofit getHttp() {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Api.BaseUrl)
                    .client(getOkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }

    private synchronized static Retrofit getHttpAddCookie() {

        if (retrofitAddCookie == null) {
            retrofitAddCookie = new Retrofit.Builder()
                    .baseUrl(Api.BaseUrl)
                    .client(getOkHttpClientAddCookie())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofitAddCookie;
    }

    private synchronized static Retrofit getHttpReadCookie() {

        if (retrofitReadCookie == null) {
            retrofitReadCookie = new Retrofit.Builder()
                    .baseUrl(Api.BaseUrl)
                    .client(getOkHttpClientReadCookie())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofitReadCookie;
    }

}
