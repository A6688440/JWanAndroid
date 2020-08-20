package com.example.jwanandroid.navigation;

import com.example.jwanandroid.bean.NavigationProjectBean;

import java.util.List;

import io.reactivex.Observer;

/**
 * Created by SJC on 2020/6/8.
 * Describe：
 */
public interface MvpNavigation {
    interface V {
        void getNavigation(List<NavigationProjectBean.DataBean> beans);
    }

    interface P {
        void getNavigation();
    }

    interface M {
        void getNavigation(Observer<NavigationProjectBean> observer);
    }
}
