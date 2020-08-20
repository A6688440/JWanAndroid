package com.example.jwanandroid.main;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.jwanandroid.base.BaseObserver;
import com.example.jwanandroid.base.BasePresenter;
import com.example.jwanandroid.bean.UserBean;
import com.example.jwanandroid.utils.AppContext;

/**
 * Created by SJC on 2020/6/15.
 * Describe：
 */
public class MainPresenter extends BasePresenter<MainActivity, MainModel> implements MvpMain.P {
    @Override
    protected MainModel getInstantiate() {
        return new MainModel();
    }

    @Override
    public void getUserName() {
        SharedPreferences sharedPreferences = AppContext.getInstance().getSharedPreferences("User", Context.MODE_PRIVATE);
        mView.getUserName(sharedPreferences.getString("username", "点击登录"));
    }

    @Override
    public void exitLogin() {
        mModel.exitLogin(new BaseObserver<UserBean>() {
            @Override
            protected void onSuccess(UserBean userBean) {
                if (userBean.getData() == null) {
                    SharedPreferences.Editor cookieData = AppContext.getInstance().getSharedPreferences("cookieData", Context.MODE_PRIVATE).edit();
                    SharedPreferences.Editor user = AppContext.getInstance().getSharedPreferences("User", Context.MODE_PRIVATE).edit();
                    cookieData.clear();
                    user.clear();
                    cookieData.apply();
                    user.apply();
                    mView.getMessage("已经退出登录");
                    mView.getExitLogin();
                }
            }

            @Override
            protected void onFail(Throwable e) {
                mView.getMessage("网络异常");

            }
        });
    }
}
