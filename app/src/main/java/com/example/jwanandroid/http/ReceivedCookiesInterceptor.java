package com.example.jwanandroid.http;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.jwanandroid.utils.AppContext;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by SJC on 2020/6/14.
 * Describe：获取网络的请求的cookie
 */
public class ReceivedCookiesInterceptor implements Interceptor {


    private static final String TAG = "ReceivedCookiesInterceptor";


    @Override
    public Response intercept(Chain chain) throws IOException {

        Response originalResponse = chain.proceed(chain.request());

        //这里获取请求返回的cookie
        if (!originalResponse.headers("Set-Cookie").isEmpty()) {

            HashSet<String> cookies = new HashSet<>();
            for (String header : originalResponse.headers("Set-Cookie")) {
                Log.e(TAG, "拦截的cookie是：" + header);
                cookies.add(header);
            }
            //保存的sharedPreferences文件名为cookieData
            SharedPreferences sharedPreferences = AppContext.getInstance().getSharedPreferences("cookieData", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putStringSet("cookie", cookies);
            editor.apply();
        }

        return originalResponse;
    }
}
