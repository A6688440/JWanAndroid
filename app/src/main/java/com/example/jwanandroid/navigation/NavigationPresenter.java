package com.example.jwanandroid.navigation;

import com.example.jwanandroid.base.BaseObserver;
import com.example.jwanandroid.base.BasePresenter;
import com.example.jwanandroid.bean.NavigationProjectBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SJC on 2020/6/8.
 * Describe：
 */
public class NavigationPresenter extends BasePresenter<NavigationFragment,NavigationModel> implements MvpNavigation.P {

    private List<NavigationProjectBean.DataBean> dataBeans=new ArrayList<>();

    @Override
    protected NavigationModel getInstantiate() {
        return new NavigationModel();
    }

    @Override
    public void getNavigation() {
        mModel.getNavigation(new BaseObserver<NavigationProjectBean>() {
            @Override
            protected void onSuccess(NavigationProjectBean navigationProjectBean) {
                dataBeans= navigationProjectBean.getData();
                mView.getNavigation(dataBeans);
            }

            @Override
            protected void onFail(Throwable e) {

            }
        });
    }
}
