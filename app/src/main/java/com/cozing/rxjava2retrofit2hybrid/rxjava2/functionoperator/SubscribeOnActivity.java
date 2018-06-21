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
 * desc:subscribeOn操作符演示
 * <p>
 *     Observable线程调度器
 *     作用：被观察者（Observable）实现异步操作，在需要耗时操作时，可调度线程，且在需要更新UI时调回主线程更新UI
 *
 *      <-- 使用说明 -->
 *      Observable.subscribeOn（Schedulers.Thread）：指定被观察者 发送事件的线程（传入RxJava内置的线程类型）
 *
 *  注意：
 *  若Observable.subscribeOn（）多次指定被观察者 生产事件的线程，则只有第一次指定有效，其余的指定线程无效
 *  被观察者的线程 = 第一次指定的线程 = 新的工作线程，第二次指定的线程（主线程）无效
 *
 * Author: Cozing
 * GitHub: https://github.com/Cozing
 * Date: 2018/6/20
 */

public class SubscribeOnActivity extends BaseOperatorActivity{

    private String TAG = "Cozing-SubscribeOn";

    @Override
    protected String getOperatorTheme() {
        return "subscribeOn";
    }

    @Override
    protected void clickEvent() {
        Observable observable = Observable.create(new ObservableOnSubscribe() {
            @Override
            public void subscribe(ObservableEmitter e) throws Exception {
                Log.w(TAG, "被观察者工作线程为：" + Thread.currentThread().getName());
                e.onNext("请看log打印的日志");
                e.onComplete();
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
