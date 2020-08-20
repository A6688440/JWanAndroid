package com.example.jwanandroid.user.register;

import com.example.jwanandroid.base.BaseObserver;
import com.example.jwanandroid.base.BasePresenter;
import com.example.jwanandroid.bean.UserBean;

/**
 * Created by SJC on 2020/5/29.
 * Describe：
 */
public class RegisterPresenter extends BasePresenter<RegisterActivity, RegisterModel> implements MvpRegister.P {
    @Override
    protected RegisterModel getInstantiate() {
        return new RegisterModel();
    }

    @Override
    public void Register(String username, String password, String repassword) {
        mModel.Register(username, password, repassword, new BaseObserver<UserBean>() {
            @Override
            protected void onSuccess(UserBean userBean) {
                if (userBean.getErrorCode() == 0) {
                    mView.getMessage("注册成功");
                } else if (userBean.getErrorCode() == -1) {
                    mView.getMessage(userBean.getErrorMsg());
                } else {
                    mView.getMessage("注册失败");
                }
            }

            @Override
            protected void onFail(Throwable e) {
                mView.getMessage("网络异常");
            }
        });
    }
}
