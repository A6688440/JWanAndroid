package com.example.jwanandroid.base;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by SJC on 2020/5/28.
 * Describe：
 */
public abstract class BaseObserver<T> implements Observer<T> {


    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    @Override
    public void onError(Throwable e) {
        onFail(e);
    }

    @Override
    public void onComplete() {

    }

    //子类必须实现的方法
    protected abstract void onSuccess(T t);

    protected abstract void onFail(Throwable e);

}
