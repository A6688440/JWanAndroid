package com.example.jwanandroid.user.login;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.jwanandroid.base.BaseObserver;
import com.example.jwanandroid.base.BasePresenter;
import com.example.jwanandroid.bean.UserBean;
import com.example.jwanandroid.event.EventUserInfo;
import com.example.jwanandroid.utils.AppContext;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by SJC on 2020/5/28.
 * Describe：
 */
public class LoginPresenter extends BasePresenter<LoginActivity, LoginModel> implements MvpLogin.P {

    @Override
    protected LoginModel getInstantiate() {
        return new LoginModel();
    }

    @Override
    public void Login(String username, String password) {

        if (username.equals("") || password.equals("")) {
            mView.getMessage("用户名或密码不能为空");
            return;
        }

        mModel.loginM(username, password, new BaseObserver<UserBean>() {
            @Override
            protected void onSuccess(UserBean userBean) {
                if (userBean.getErrorCode() == 0) {
                    SharedPreferences.Editor editor = AppContext.getInstance().getSharedPreferences("User", Context.MODE_PRIVATE).edit();
                    editor.putString("username", userBean.getData().getUsername());
                    editor.apply();
                    mView.getMessage("登录成功");
                    EventBus.getDefault().postSticky(new EventUserInfo(userBean.getData().getUsername()));
                } else if (userBean.getErrorCode() == -1) {
                    mView.getMessage(userBean.getErrorMsg());
                } else {
                    mView.getMessage("登录失败");
                }
            }

            @Override
            protected void onFail(Throwable e) {
                mView.getMessage("网络异常");
            }
        });
    }


}
