package com.cozing.rxjava2retrofit2hybrid.rxjava2.functionoperator;

import android.util.Log;

import com.cozing.rxjava2retrofit2hybrid.base.BaseOperatorActivity;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * desc:observerOn操作符演示
 * <p>
 *     observer线程调度器
 *     观察者（observer）实现异步操作，在需要
 *
 *     <-- 使用说明 -->
 *     Observable.observeOn（Schedulers.Thread）：指定观察者 接收 & 响应事件的线程（传入RxJava内置的线程类型）
 *
 * 注意：
 * 若Observable.observeOn（）多次指定观察者 接收 & 响应事件的线程，则每次指定均有效，即每指定一次，就会进行一次线程的切换
 * 每调用一次observeOn()，观察者的线程就会切换一次
 *
 * Author: Cozing
 * GitHub: https://github.com/Cozing
 * Date: 2018/6/20
 */

public class ObserveOnActivity extends BaseOperatorActivity{

    private String TAG = "Cozing-ObserveOn";

    @Override
    protected String getOperatorTheme() {
        return "observerOn";
    }

    @Override
    protected void clickEvent() {

        Observable observable = Observable.create(new ObservableOnSubscribe() {
            @Override
            public void subscribe(ObservableEmitter e) throws Exception {
                Log.w(TAG, "被观察者工作线程为：" + Thread.currentThread().getName());
                e.onNext("请看log打印的日志");
            }
        });

        Observer observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.w(TAG, "onSubscribe");
                Log.w(TAG, "观察者工作线程是：" + Thread.currentThread().getName());
            }

            @Override
            public void onNext(String s) {
                Log.w(TAG, "onNext:" + s);
                appendText(s + "\n");
            }

            @Override
            public void onError(Throwable e) {
                Log.w(TAG, "onError:" + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.w(TAG, "onComplete:");
            }
        };

        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

    }
}
