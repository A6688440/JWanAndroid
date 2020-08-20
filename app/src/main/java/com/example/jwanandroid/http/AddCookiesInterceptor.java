package com.example.jwanandroid.http;

import android.content.Context;
import android.util.Log;

import com.example.jwanandroid.utils.AppContext;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by SJC on 2020/6/14.
 * Describe：网络请求中添加cookie
 */
public  class  AddCookiesInterceptor implements Interceptor {
    private static final String TAG = "AddCookiesInterceptor";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        HashSet<String> stringSet = (HashSet) AppContext.getInstance().getSharedPreferences("cookieData", Context.MODE_PRIVATE).getStringSet("cookie", null);
        if (stringSet != null) {
            Log.e(TAG, "intercept: "+stringSet.toString() );
            for (String cookie : stringSet) {
                builder.addHeader("Cookie", cookie);
            }
        }
        return chain.proceed(builder.build());

    }
}
