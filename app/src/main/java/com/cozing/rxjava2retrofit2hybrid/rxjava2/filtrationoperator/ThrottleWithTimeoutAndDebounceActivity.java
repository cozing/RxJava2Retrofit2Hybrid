package com.cozing.rxjava2retrofit2hybrid.rxjava2.filtrationoperator;

import com.cozing.rxjava2retrofit2hybrid.base.BaseOperatorActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * desc:throttleWithTimeout/debounce
 * <p>
 *
 *     发送数据时，若被发射事件之间发送的事件时间间隔 < 指定的时间间隔，就会丢弃前一次的数据，直到指定的时间内都没有新数据发射才会发送后一次数据
 *
 * Author: Cozing
 * GitHub: https://github.com/Cozing
 * Date: 2018/6/21
 */

public class ThrottleWithTimeoutAndDebounceActivity extends BaseOperatorActivity{
    @Override
    protected String getOperatorTheme() {
        return "throttleWithTimeout/debounce";
    }

    @Override
    protected void clickEvent() {

        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("1");
                Thread.sleep(300);
                e.onNext("2");
                Thread.sleep(100);
                e.onNext("1");
                Thread.sleep(1000);
                e.onNext("1");
                Thread.sleep(1200);
                e.onNext("1");
                Thread.sleep(500);
                e.onNext("1");
                Thread.sleep(100);
                e.onComplete();
            }
        }).throttleWithTimeout(2, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        appendText(" onSubscribe" + "\n");
                    }

                    @Override
                    public void onNext(String s) {
                        appendText(" onNext：" + s + "\n");
                    }

                    @Override
                    public void onError(Throwable e) {
                        appendText(" onError:" + e.getMessage() + "\n");
                    }

                    @Override
                    public void onComplete() {
                        appendText(" onComplete" + "\n");
                    }
                });

    }
}
