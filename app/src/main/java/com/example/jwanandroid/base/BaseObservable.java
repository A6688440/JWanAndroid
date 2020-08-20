package com.example.jwanandroid.base;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by SJC on 2020/5/28.
 * Describe：
 */
public  class  BaseObservable<T> extends Observable<T> {

    @Override
    protected void subscribeActual(Observer<? super T> observer) {

    }

    protected Observable<T> HttpTool(){
       return this.subscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
